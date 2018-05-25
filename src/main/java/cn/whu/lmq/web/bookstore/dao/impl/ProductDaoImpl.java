package cn.whu.lmq.web.bookstore.dao.impl;

import cn.whu.lmq.web.bookstore.bean.Product;
import cn.whu.lmq.web.bookstore.dao.ProductDao;
import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {


}
