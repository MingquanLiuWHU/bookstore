package cn.whu.lmq.web.bookstore.dao.impl;

import java.util.List;
import java.util.function.Consumer;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.whu.lmq.web.bookstore.bean.User;
import cn.whu.lmq.web.bookstore.dao.UserDao;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	

	@Override
	public User getByAccount(String account) {
		List<User> users = findByCriteria(criteria->{
			criteria.add(Restrictions.eq("account", account));
		});
		if (users != null && !users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}


}
