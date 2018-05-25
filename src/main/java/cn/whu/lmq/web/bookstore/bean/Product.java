package cn.whu.lmq.web.bookstore.bean;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 商品信息
 */
@Entity
@Table(name = "product")
@DynamicInsert
@DynamicUpdate
public class Product extends BaseBean {
    //售价
    private BigDecimal price;
    //剩余的数量
    private Integer quantity;
    //销量
    @Column(name = "sold_quantity")
    private Integer soldQuantity;
    //对应的图片
    @Column(name = "image_url")
    private String imageUrl;
    //对应的书本
    @Embedded
    private Book book;
    //商品是否下架,下架对于用户不可见,true表示在售,默认true
    private boolean onSale = true;

    public Product() {
    }

    public Product(Integer id) {
        super.setId(id);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
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

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }
}
