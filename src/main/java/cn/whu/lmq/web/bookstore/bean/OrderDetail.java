package cn.whu.lmq.web.bookstore.bean;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 订单细则，对应一件卖出的商品
 */
@Entity
@Table(name = "order_detail")
@DynamicInsert
@DynamicUpdate
public class OrderDetail extends BaseBean{
	// 所属的订单
	@ManyToOne
	@JoinColumn(name = "order_id")
	private UserOrder userOrder;
	//该订单细则对应的卖出的商品
    @OneToOne
	@JoinColumn(name="product_id")
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public UserOrder getOrder() {
		return userOrder;
	}

	public void setOrder(UserOrder userOrder) {
		this.userOrder = userOrder;
	}

}
