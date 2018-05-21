package cn.whu.lmq.web.bookstore.bean;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 书城的信息,实际上只储存一条唯一的记录
 */
@Entity
@Table(name = "store")
@DynamicInsert
@DynamicUpdate
public class Store extends BaseBean {
	// 店名
	private String storeName;
	// 商店描述
	private String description;

	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
