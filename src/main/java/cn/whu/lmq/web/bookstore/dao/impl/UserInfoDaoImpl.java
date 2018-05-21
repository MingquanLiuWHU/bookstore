package cn.whu.lmq.web.bookstore.dao.impl;

import java.util.List;
import java.util.function.Consumer;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.whu.lmq.web.bookstore.bean.User;
import cn.whu.lmq.web.bookstore.bean.UserInfo;
import cn.whu.lmq.web.bookstore.dao.UserInfoDao;

@Repository("userInfoDao")
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo> implements UserInfoDao {

	@Override
	public UserInfo getByUser(User user) {
		List<UserInfo> userInfos = findByCriteria(criteria->{
			criteria.add(Restrictions.eq("user", user));
		});
		if (userInfos == null || userInfos.isEmpty()) {
			return null;
		}
		return userInfos.get(0);
	}


}
