package cn.whu.lmq.web.bookstore.service.impl;

import cn.whu.lmq.web.bookstore.bean.*;
import cn.whu.lmq.web.bookstore.dao.ShoppingCartDao;
import cn.whu.lmq.web.bookstore.helper.PageBean;
import cn.whu.lmq.web.bookstore.service.CartService;
import cn.whu.lmq.web.bookstore.service.PageBeanService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@Service("cartService")
@Transactional
public class CartServiceImpl implements CartService {
    //分页服务
    private PageBeanService pageBeanService;
    //dao层引用
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    public void setPageBeanService(PageBeanService pageBeanService) {
        this.pageBeanService = pageBeanService;
    }

    @Autowired
    public void setShoppingCartDao(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    public void addProduct(User user, Product product) {
        //先查询
        ShoppingCart cart = shoppingCartDao.getByUserAndProduct(user, product);
        if (cart == null) {//不存在则新建保存
            cart = new ShoppingCart();
            cart.setProduct(product);
            cart.setUser(user);
            cart.setQuantity(1);
            shoppingCartDao.save(cart);
            return;
        }
        //存在cart数量+1
        cart.setQuantity(cart.getQuantity() + 1);
        shoppingCartDao.update(cart);
    }

    /**
     * 从购物车移除某件商品
     *
     * @param user    用户
     * @param product 商品
     */
    @Override
    public void removeProduct(User user, Product product) {
        //校验用户购物车是否含该商品
        ShoppingCart shoppingCart = checkUserWithProduct(user, product);
        //删除
        shoppingCartDao.delete(shoppingCart);
    }

    @Override
    public void removeProducts(User user, Set<Product> products) {
        //校验是否每项商品都在购物车
        List<ShoppingCart> shoppingCarts = checkUserWithProducts(user, products);
        //全部删除
        shoppingCartDao.deleteAll(shoppingCarts);
    }

    /**
     * 检查用户的购物车是否含有该项商品
     *
     * @param user    用户
     * @param product 商品
     * @return 用户含该商品的购物车（细则），商品不在购物车抛出异常
     */
    private ShoppingCart checkUserWithProduct(User user, Product product) {
        ShoppingCart existCart = shoppingCartDao.getByUserAndProduct(user, product);
        if (existCart == null) {
            throw new IllegalStateException("用户购物车并没有该商品");
        }
        return existCart;
    }

    /**
     * 检查购物车是否含有多项商品
     *
     * @param user     用户
     * @param products 商品集合
     * @return 购物车集合，当有一项商品不在购物车抛出异常
     */
    private List<ShoppingCart> checkUserWithProducts(User user, Set<Product> products) {
        //确定查询条件
        Consumer<DetachedCriteria> consumer = criteria -> {
            criteria.add(Restrictions.eq("user", user));
            criteria.add(Restrictions.in("product", products));
        };
        //查询数量
        int count = shoppingCartDao.count(consumer);
        if(count!=products.size()){
            //数量不相等意味着有商品不在购物车
            throw new IllegalStateException("有商品不在用户的购物车");
        }
        //查询商品对应的购物车集合
        return shoppingCartDao.findByCriteria(consumer);
    }


    /**
     * 用户分页查看购物车
     *
     * @param user 用户
     * @param page 页码
     * @return 分页列表
     */
    @Override
    public PageBean<ShoppingCart> findByUser(User user, int page) {
        return pageBeanService.listByPage(shoppingCartDao, criteria ->
                        criteria.add(Restrictions.eq("user", user))
                , page, PAGE_SIZE);
    }

    /**
     * 更新购物车商品的数量
     *
     * @param user     用户
     * @param product  商品
     * @param quantity 新的数量
     */
    @Override
    public void updateProductQuantity(User user, Product product, int quantity) {
        ShoppingCart shoppingCart = checkUserWithProduct(user, product);
        //设置新数量并更新
        shoppingCart.setQuantity(quantity);
        shoppingCartDao.update(shoppingCart);
    }

    /**
     * 根据购物车里选择的商品生成订单
     *
     * @param user     用户
     * @param products 选中的商品
     * @return 用户订单，并没有保存在数据库，id为空
     */
    @Override
    public UserOrder generateOrder(User user, Set<Product> products) {
        //做用户商品校验
        List<ShoppingCart> existCarts = checkUserWithProducts(user, products);
        //新建空订单
        UserOrder userOrder = new UserOrder();
        //遍历购物车细则生成订单细则
        existCarts.forEach(cart -> {
            OrderDetail orderDetail = new OrderDetail();
            //设置订单和订单细则双方的关联关系
            orderDetail.setOrder(userOrder);
            userOrder.getOrderDetails().add(orderDetail);
            //设置关联的商品和数量
            orderDetail.setProduct(cart.getProduct());
            orderDetail.getProduct().setQuantity(cart.getQuantity());
        });
        //遍历计算总共的原价
        Optional<BigDecimal> totalOriginalMoney = existCarts.stream()
                .map(ShoppingCart::getProduct)
                .map(Product::getOriginalPrice)
                .reduce(BigDecimal::add);
        //遍历计算总售价
        Optional<BigDecimal> totalMoney = existCarts.stream()
                .map(ShoppingCart::getProduct)
                .map(Product::getPrice)
                .reduce(BigDecimal::add);
        //原价可能为空
        userOrder.setOriginalTotalMoney(totalOriginalMoney.orElse(null));
        //现价如果为空抛出异常
        userOrder.setOriginalTotalMoney(totalMoney.orElseThrow(NullPointerException::new));
        userOrder.setUser(user);
        //TODO 是否在该处查询地址列表并设置？
        return userOrder;
    }
}
