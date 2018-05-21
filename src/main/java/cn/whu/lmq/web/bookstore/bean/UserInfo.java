package cn.whu.lmq.web.bookstore.bean;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
@DynamicInsert
@DynamicUpdate
public class UserInfo extends BaseBean {
	// 身份证号
	@Column(name = "id_card")
	private String idCard;
	// 真实姓名
	@Column(name = "true_name")
	private String trueName;
	// 电话
	private String phone;
	// 邮箱
	private String email;
	// 对应的用户
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
