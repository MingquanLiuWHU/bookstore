package cn.whu.lmq.web.bookstore.bean;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "user_order")
@DynamicInsert
@DynamicUpdate
public class UserOrder extends BaseBean {
    // 订单所属的顾客
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    //订单状态
    @Enumerated(EnumType.STRING)
    private OrderState state;
    // 总原价
    @Column(name = "original_total_money")
    private BigDecimal originalTotalMoney;
    // 总价
    @Column(name = "total_money")
    private BigDecimal totalMoney;
    // 关联的收货地址
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    // 订单的详情
    @OneToMany(mappedBy = "userOrder")
    private List<OrderDetail> orderDetails = new ArrayList<>();


    public UserOrder() {
        super();
    }


    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public BigDecimal getOriginalTotalMoney() {
        return originalTotalMoney;
    }

    public void setOriginalTotalMoney(BigDecimal originalTotalMoney) {
        this.originalTotalMoney = originalTotalMoney;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}
