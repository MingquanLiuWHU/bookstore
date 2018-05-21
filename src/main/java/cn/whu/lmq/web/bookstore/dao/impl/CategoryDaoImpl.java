package cn.whu.lmq.web.bookstore.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import cn.whu.lmq.web.bookstore.bean.Category;
import cn.whu.lmq.web.bookstore.dao.CategoryDao;

import java.util.List;
import java.util.function.Consumer;

@Repository("categoryDao")
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {



}
