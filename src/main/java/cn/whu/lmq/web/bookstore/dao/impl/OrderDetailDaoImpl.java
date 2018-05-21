package cn.whu.lmq.web.bookstore.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import cn.whu.lmq.web.bookstore.bean.OrderDetail;
import cn.whu.lmq.web.bookstore.dao.OrderDetailDao;

import java.util.List;
import java.util.function.Consumer;

@Repository("orderDetailDao")
public class OrderDetailDaoImpl extends BaseDaoImpl<OrderDetail> implements OrderDetailDao {


}
