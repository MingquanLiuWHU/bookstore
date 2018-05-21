package cn.whu.lmq.web.bookstore.service.impl;

import cn.whu.lmq.web.bookstore.bean.*;
import cn.whu.lmq.web.bookstore.dao.AddressDao;
import cn.whu.lmq.web.bookstore.dao.OrderDao;
import cn.whu.lmq.web.bookstore.dao.OrderDetailDao;
import cn.whu.lmq.web.bookstore.helper.PageBean;
import cn.whu.lmq.web.bookstore.service.OrderService;
import cn.whu.lmq.web.bookstore.service.PageBeanService;
import cn.whu.lmq.web.bookstore.service.PaymentService;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
    //订单的dao层
    private OrderDao orderDao;
    //分页服务
    private PageBeanService pageBeanService;
    //获取address的dao层
    private AddressDao addressDao;
    //订单细则的dao层
    private OrderDetailDao orderDetailDao;
    //支付服务
    private PaymentService paymentService;

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Autowired
    public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
        this.orderDetailDao = orderDetailDao;
    }

    @Autowired
    public void setPageBeanService(PageBeanService pageBeanService) {
        this.pageBeanService = pageBeanService;
    }

    @Autowired
    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    /**
     * 用户和店主都可以修改订单不同的状态，需要考虑如何区分
     *
     * @param userOrder 订单
     */
    @Override
    public void updateState(UserOrder userOrder) {
        if (userOrder.getState() == null) {
            //新状态不能为空
            throw new NullPointerException();
        }
        //根据order.id查询实体order
        UserOrder existOrder = orderDao.getById(userOrder);

        if (existOrder.getState() == OrderState.CLOSED || existOrder.getState() == OrderState.RECEIVED) {
            //关闭和交易成功的订单不能再修改状态
            throw new IllegalStateException();
        }

        if (userOrder.getState().compareTo(existOrder.getState()) <= 0) {
            //订单的状态不能逆转
            throw new IllegalArgumentException();
        }

        existOrder.setState(userOrder.getState());
        orderDao.update(existOrder);

    }

    private UserOrder checkUserWithOrder(User user, UserOrder userOrder) {
        UserOrder existOrder = orderDao.getById(userOrder);
        if (existOrder == null) {
            throw new NullPointerException("order不存在");
        }
        if (!existOrder.getUser().equals(user)) {
            throw new IllegalArgumentException("user与order不一致");
        }
        return existOrder;
    }

    @Override
    public void delete(User user, UserOrder userOrder) {
        UserOrder existOrder = checkUserWithOrder(user, userOrder);
        orderDao.delete(existOrder);
    }

    @Override
    public UserOrder save(UserOrder userOrder) {
        //每次保存都需要重新计算总售价和原价，保证一致
        BigDecimal totalMoney = userOrder.getOrderDetails()
                .stream()
                .map(OrderDetail::getProduct)
                .map(Product::getPrice)
                .reduce(BigDecimal::add)
                .orElseThrow(IllegalStateException::new); //总价计算不出抛出异常
        userOrder.setTotalMoney(totalMoney);
        BigDecimal originalTotalMoney = userOrder.getOrderDetails()
                .stream()
                .map(OrderDetail::getProduct)
                .map(Product::getOriginalPrice)
                .reduce(BigDecimal::add)
                .orElse(null);//原价允许为空
        userOrder.setOriginalTotalMoney(originalTotalMoney);
        if(originalTotalMoney!=null&&originalTotalMoney.compareTo(totalMoney)<0){
            //原价低于售价
            throw new IllegalStateException("原价不能低于售价");
        }
        //校验
        orderDao.save(userOrder);
        userOrder.setState(OrderState.CANCELED);
        //循环保存订单细则，数据量大时效率低需要再度修改
        userOrder.getOrderDetails().forEach(orderDetailDao::save);
        return userOrder;
    }

    /**
     * @param user 如果是管理员可以查看所有订单
     *             一般用户只能查看自己的订单
     * @param page 页码
     * @return 订单的分页包装
     */
    @Override
    public PageBean<UserOrder> findAll(User user, int page) {
        return pageBeanService.listByPage(orderDao,
                criteria -> {
                    criteria.addOrder(Order.desc("updateTime"));
                    if (!user.isAdmin()) {
                        criteria.add(Restrictions.eq("user", user));
                    }

                }
                , page, PAGE_SIZE);
    }

    /**
     * 对订单的支付
     *
     * @param user      用户
     * @param userOrder 需要支付的订单
     */

    private void pay(User user, UserOrder userOrder) {
        //对用户和订单的校验
        UserOrder existOrder = checkUserWithOrder(user, userOrder);
        //检查该订单是否有效的的未支付订单
        if (existOrder.getState() != OrderState.CANCELED) {
            throw new IllegalStateException();//抛出异常
        }
        //计算与上次支付取消的时间差
        long delta = new Date().getTime() - existOrder.getUpdateTime().getTime();
        if (delta > TIME) {
            //超时关闭订单，并抛出异常
            existOrder.setState(OrderState.CLOSED);
            orderDao.update(existOrder);
            throw new IllegalStateException();
        }
        //调用支付接口
        try {
            //获取支付账户
            PaymentAccount userAccount = paymentService.getByUser(user);

            paymentService.pay(userAccount, new PaymentAccount(),existOrder.getTotalMoney());
            //此处的异常需要直接捕获处理
        } catch (Exception e) {
            //支付失败，订单状态设置成取消
            //existOrder.setState(OrderState.CANCELED);
            //转换成最后页面处理的异常,也可以不包装
            throw new IllegalStateException(e);
        }
        //订单修改为已支付的状态
        existOrder.setState(OrderState.PAIED);
        //更新订单
        orderDao.update(existOrder);
    }

    /**
     * 对购物车生成的未保存的订单结算
     *
     * @param user      用户
     * @param userOrder 用户的新订单
     */
    @Override
    public void balance(User user, UserOrder userOrder) {
        //设置关联
        userOrder.setUser(user);
        //保存订单
        this.save(userOrder);
        //尝试支付
        this.pay(user, userOrder);
    }

    /**
     * 用户确认收货
     *
     * @param user      用户
     * @param userOrder 对应的订单
     */
    @Override
    public void confirmReceived(User user, UserOrder userOrder) {
        UserOrder existOrder = checkUserWithOrder(user, userOrder);
        if (existOrder.getState() != OrderState.PAIED) {
            throw new IllegalStateException();
        }
        existOrder.setState(OrderState.RECEIVED);
        orderDao.update(existOrder);
        //还需要完成实际支付
    }


    /**
     * 保存订单
     * 保存前先根据map更新order里对应的商品数量
     *
     * @param order            订单
     * @param product2Quantity （商品,数量)
     */
    @Override
    public void save(UserOrder order, Map<Integer, Integer> product2Quantity) {
        //先更新订单
        order.getOrderDetails()
                .stream()
                .map(OrderDetail::getProduct)
                .forEach(p -> {
                    //更新订单数量
                    if (product2Quantity.keySet().contains(p.getId())) {
                        p.setQuantity(product2Quantity.get(p.getId()));
                    }
                    //设置product为售出
                    p.setSold(true);
                    p.setOnSale(false);
                });
        this.save(order);
    }

    /**
     * 保存并且对订单结算支付
     *
     * @param userOrder            订单
     * @param product2Quantity （商品，数量）
     * @param addressId        关联的收货地址id
     */
    @Override
    public void saveAndBalance(UserOrder userOrder, Map<Integer, Integer> product2Quantity, int addressId) {
        //获取地址
        Address add = addressDao.getById(new Address(addressId));
        if (add == null) {
            throw new IllegalStateException();
        }
        //关联地址
        userOrder.setAddress(add);
        //保存订单
        this.save(userOrder, product2Quantity);
        //TODO 尝试结算

    }

}
