package cn.whu.lmq.web.bookstore.dao.impl;

import cn.whu.lmq.web.bookstore.bean.User;
import cn.whu.lmq.web.bookstore.bean.UserInfo;
import cn.whu.lmq.web.bookstore.dao.UserInfoDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userInfoDao")
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo> implements UserInfoDao {

	@Override
	public UserInfo findByUser(User user) {
		List<UserInfo> userInfos = findByCriteria(criteria->{
			criteria.add(Restrictions.eq("user", user));
		});
		if (userInfos == null || userInfos.isEmpty()) {
			return null;
		}
		return userInfos.get(0);
	}



}
