package cn.whu.lmq.web.bookstore.bean;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
@Entity
@Table(name = "cart_detail")
@DynamicInsert
@DynamicUpdate
public class ShoppingCart extends BaseBean {
    // 商品的数量
    private Integer quantity;
    // 对商品的一对一映射
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    // 所属的用户
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ShoppingCart() {
        super();
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
