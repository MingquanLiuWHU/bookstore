package cn.whu.lmq.web.bookstore.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * 商品对应的具体图书信息
 */
@Embeddable
public class Book implements Serializable {
    // isbn号
    private String isbn;
    // 书名
    @Column(name = "book_name")
    private String bookName;
    // 作者
    private String author;


    public Book() {
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}
