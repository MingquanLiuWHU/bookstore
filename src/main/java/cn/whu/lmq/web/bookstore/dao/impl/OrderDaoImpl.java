package cn.whu.lmq.web.bookstore.dao.impl;

import org.springframework.stereotype.Repository;

import cn.whu.lmq.web.bookstore.bean.UserOrder;
import cn.whu.lmq.web.bookstore.dao.OrderDao;

@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl<UserOrder> implements OrderDao {


}
