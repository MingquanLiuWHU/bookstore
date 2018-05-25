package cn.whu.lmq.web.bookstore.dao.impl;

import cn.whu.lmq.web.bookstore.bean.User;
import cn.whu.lmq.web.bookstore.dao.UserDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	

	@Override
	public User findByAccount(String account) {
		List<User> users = findByCriteria(criteria->{
			criteria.add(Restrictions.eq("account", account));
		});
		if (users != null && !users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}


}
