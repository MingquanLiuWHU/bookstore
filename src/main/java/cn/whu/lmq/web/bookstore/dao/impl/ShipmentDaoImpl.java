package cn.whu.lmq.web.bookstore.dao.impl;

import cn.whu.lmq.web.bookstore.bean.Shipment;
import cn.whu.lmq.web.bookstore.bean.UserOrder;
import cn.whu.lmq.web.bookstore.dao.ShipmentDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("shipmentDao")
public class ShipmentDaoImpl extends BaseDaoImpl<Shipment> implements ShipmentDao {

	@Override
	public Shipment findByOrder(UserOrder userOrder) {
		List<Shipment> shipments = findByCriteria(criteria->{
			criteria.add(Restrictions.eq("userOrder", userOrder));
		});
		if (shipments == null || shipments.isEmpty()) {
			return null;
		}
		return shipments.get(0);
	}


}
