package cn.whu.lmq.web.bookstore.service;

import cn.whu.lmq.web.bookstore.bean.User;
import cn.whu.lmq.web.bookstore.bean.UserOrder;
import cn.whu.lmq.web.bookstore.helper.PageBean;

import java.util.Map;

public interface OrderService  {

	//分页页面大小
	int PAGE_SIZE = 4;
	//订单超时的时间,15分钟
    long TIME = 15 * 60 * 1000;

    /**
     * 获取所有订单
     */
    PageBean<UserOrder> findAll(User user,int page);
    /**
     *对订单状态的更新
     */
	void updateState(UserOrder userOrder);

    /**
     * 删除订单，用户的操作
     */
    void delete(User user,UserOrder userOrder);

    /**
     *保存订单
     */
    UserOrder save(UserOrder userOrder);



    /**
     * 对购物车生成的未保存的订单结算
     */
    void balance(User user,UserOrder userOrder);

    /**
     * 用户确认收货
     * @param user 用户
     * @param userOrder 对应的订单
     */
    void confirmReceived(User user,UserOrder userOrder);

    /**
     * 保存订单
     * 保存前先根据map更新order里对应的商品数量
     * @param order 订单
     * @param product2Quantity （商品,数量)
     */
    void save(UserOrder order, Map<Integer,Integer> product2Quantity);

    /**
     * 保存并且对订单结算支付
     * @param order 订单
     * @param product2Quantity （商品，数量）
     * @param addressId 关联的收货地址id
     */
    void saveAndBalance(UserOrder order,Map<Integer,Integer> product2Quantity,int addressId);
}
