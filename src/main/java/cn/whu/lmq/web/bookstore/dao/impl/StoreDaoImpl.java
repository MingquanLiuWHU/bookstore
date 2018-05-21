package cn.whu.lmq.web.bookstore.dao.impl;

import java.util.List;
import java.util.function.Consumer;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.whu.lmq.web.bookstore.bean.Store;
import cn.whu.lmq.web.bookstore.bean.User;
import cn.whu.lmq.web.bookstore.dao.StoreDao;

@Repository("storeDao")
public class StoreDaoImpl extends BaseDaoImpl<Store> implements StoreDao {



}
