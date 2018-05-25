package cn.whu.lmq.web.bookstore.service.impl;

import cn.whu.lmq.web.bookstore.bean.Product;
import cn.whu.lmq.web.bookstore.dao.ProductDao;
import cn.whu.lmq.web.bookstore.helper.PageBean;
import cn.whu.lmq.web.bookstore.service.PageBeanService;
import cn.whu.lmq.web.bookstore.service.ProductService;
import cn.whu.lmq.web.bookstore.util.CopyUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    private PageBeanService pageBeanService;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Autowired
    public void setPageBeanService(PageBeanService pageBeanService) {
        this.pageBeanService = pageBeanService;
    }

    /**
     * 对于一般用户查询添加限制
     */
    private void addUserAuthority(DetachedCriteria criteria) {
        criteria.add(Restrictions.eqOrIsNull("onSale", false));
        criteria.add(Restrictions.eqOrIsNull("sold", true));
    }

    @Override
    public PageBean<Product> findByNameLike(String bookName, int page) {
        return pageBeanService.listByPage(productDao,
                criteria -> {
                    criteria.add(Restrictions.ilike("book.bookName", bookName, MatchMode.ANYWHERE));
                    addUserAuthority(criteria);
                },
                page, PAGE_SIZE);
    }

    @Override
    public PageBean<Product> findByPriceBetween(int min, int max, int page) {
        return pageBeanService.listByPage(productDao,
                criteria -> {
                    criteria.add(Restrictions.between("price",
                            new BigDecimal(min), new BigDecimal(max)));
                    addUserAuthority(criteria);
                },
                page, PAGE_SIZE
        );
    }



    @Override
    public PageBean<Product> findAll(int page) {
        return pageBeanService.listByPage(productDao, criteria -> {
        }, page, PAGE_SIZE);
    }

    /**
     * 保存product
     *
     * @param product 商品
     */
    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    /**
     * 删除product
     *
     * @param product 商品
     */
    @Override
    public void delete(Product product) {
        //先查询
        Product existProduct = productDao.findById(product);
        if(existProduct == null){
            throw new IllegalStateException("要删除的product不存在");
        }
        productDao.delete(existProduct);
    }

    /**
     * 更新product
     *
     * @param product 商品
     */
    @Override
    public void update(Product product) {
        //先查询
        Product existProduct = productDao.findById(product);
        if(existProduct == null){
            throw new IllegalStateException("要更新的product不存在");
        }
        //复制要更新的字段
        CopyUtil.copyNotNullFields(product,existProduct);
        //更新
        productDao.update(existProduct);
    }

    @Override
    public Product findById(Product product) {
        return productDao.findById(product);
    }
}
