package cn.whu.lmq.web.bookstore.bean;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "address")
@DynamicInsert
@DynamicUpdate
public class Address extends BaseBean {
    // 省或直辖市自治区等
    private String province;
    // 城市
    private String city;
    // 详细地址
    private String detail;
    // 邮编
    private String postcode;
    // 收货人电话
    private String phone;
    // 收货人姓名
    @Column(name = "receiver_name")
    private String receiverName;

    public Address() {

    }

    public Address(int addressId) {
        super.setId(addressId);
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }



}
