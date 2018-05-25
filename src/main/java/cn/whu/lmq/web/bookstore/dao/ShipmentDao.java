package cn.whu.lmq.web.bookstore.dao;

import cn.whu.lmq.web.bookstore.bean.UserOrder;
import cn.whu.lmq.web.bookstore.bean.Shipment;

public interface ShipmentDao extends BaseDao<Shipment> {
	

	Shipment findByOrder(UserOrder userOrder);
}
