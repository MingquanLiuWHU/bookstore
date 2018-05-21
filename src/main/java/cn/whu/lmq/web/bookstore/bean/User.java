package cn.whu.lmq.web.bookstore.bean;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@DynamicInsert
@DynamicUpdate
public class User extends BaseBean {
    // 帐号
    @Column(unique = true)
    private String account;
    // 密码
    private String password;
    // 用户名
    private String username;
    //表示该用户是不是管理员
    @Column(name="is_admin")
    private boolean admin = false;
    // 拥有的收货地址列表
    @OneToMany
    @JoinTable(name = "user_has_address", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {
            @JoinColumn(name = "address_id")})
    private List<Address> addresses = new ArrayList<>();

    public User() {

    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
