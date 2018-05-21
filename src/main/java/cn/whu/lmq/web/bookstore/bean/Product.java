package cn.whu.lmq.web.bookstore.bean;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品信息
 */
@Entity
@Table(name = "product")
@DynamicInsert
@DynamicUpdate
public class Product extends BaseBean {
    //原价
    @Column(name="original_price")
    private BigDecimal originalPrice;
    //售价
    private BigDecimal price;
    //剩余的数量或者售出的数量
    private Integer quantity;
    //多个类别
    @ManyToMany
    @JoinTable(name="product_with_category",joinColumns = {@JoinColumn(name="category_id")},
    inverseJoinColumns = {@JoinColumn(name="product_id")}
    )
    private List<Category> categories = new ArrayList<>();
    //对应的书本
    @Embedded
    private Book book;
    //表示该商品的售出状态，已售出的用于关联order，true表示售出,默认false
    private boolean sold = false;
    //商品是否下架,下架对于用户不可见,true表示在售,默认true
    private boolean onSale = true;

    public Product(){}
    public Product(Integer id){
        super.setId(id);
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
