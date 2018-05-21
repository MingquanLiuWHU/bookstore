package cn.whu.lmq.web.bookstore.bean;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "shipment")
@DynamicInsert
@DynamicUpdate
public class Shipment extends BaseBean {
	// 货运单号
	@Column(name = "ship_number")
	private String shipNumber;
	// 快递费用
	private BigDecimal cost;
	// 快递方式
	private String method;
	// 发货时间
	@Column(name = "ship_at")
	private Date shipAt;
	// 对应的订单
	@OneToOne
	@JoinColumn(name = "order_id")
	private UserOrder userOrder;
	// 状态
	private String state;

	public String getShipNumber() {
		return shipNumber;
	}

	public void setShipNumber(String shipNumber) {
		this.shipNumber = shipNumber;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Date getShipAt() {
		return shipAt;
	}

	public void setShipAt(Date shipAt) {
		this.shipAt = shipAt;
	}

	public UserOrder getOrder() {
		return userOrder;
	}

	public void setOrder(UserOrder userOrder) {
		this.userOrder = userOrder;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
