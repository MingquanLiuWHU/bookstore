package cn.whu.lmq.web.bookstore.service;

import cn.whu.lmq.web.bookstore.bean.Product;
import cn.whu.lmq.web.bookstore.helper.PageBean;

public interface ProductService {
    //分页大小
    int PAGE_SIZE = 4;

    /**
     *根据书名模糊查询
     */
    PageBean<Product> findByNameLike(String bookName,int page);

    /**
     *根据价格区间查询
     */
    PageBean<Product> findByPriceBetween(int min,int max,int page);



    /**
     * 查找全部
     */
    PageBean<Product> findAll(int page);

    /**
     * 保存product
     * @param product 商品
     */
    void save(Product product);

    /**
     * 删除product
     * @param product 商品
     */
    void delete(Product product);

    /**
     * 更新product
     * @param product 商品
     */
    void update(Product product);

    Product findById(Product product);
}
