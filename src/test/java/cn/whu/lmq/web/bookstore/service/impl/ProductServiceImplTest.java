package cn.whu.lmq.web.bookstore.service.impl;

import cn.whu.lmq.web.bookstore.bean.Book;
import cn.whu.lmq.web.bookstore.bean.Product;
import cn.whu.lmq.web.bookstore.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class ProductServiceImplTest {
    private ProductService productService;
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 测试保存
     * 第一次测试失败，检验发现数据库创建时少了isbn列
     *         修改后测试成功
     */
    @Rollback(false)
    @Test
    public void save1() {
        Product product = new Product();
        product.setQuantity(10);
        product.setPrice(BigDecimal.TEN);
        Book book = new Book();
        book.setAuthor("作者");
        book.setBookName("书名");
        book.setIsbn("12345567");
        product.setBook(book);
        productService.save(product);
        System.out.println(product.getId());
        System.out.println(product.getCreateTime());

    }

    /**
     * 测试product存在id时的保存
     * 测试结果:
     *          save直接忽略存在的id
     */
    @Test
    public void save2(){
        Product product = new Product();
        product.setId(2);
        product.setQuantity(10);
        product.setPrice(BigDecimal.TEN);
        Book book = new Book();
        book.setAuthor("作者2");
        book.setBookName("书名2");
        book.setIsbn("123455674");
        product.setBook(book);
        productService.save(product);
        System.out.println(product.getId());
        System.out.println(product.getCreateTime());
    }
}
