package cn.whu.lmq.web.bookstore.bean;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    //地址
    private String address;
    //收款账户
    @Column(name = "receive_account")
    private String receiveAccount;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiveAccount() {
        return receiveAccount;
    }

    public void setReceiveAccount(String receiveAccount) {
        this.receiveAccount = receiveAccount;
    }
}
