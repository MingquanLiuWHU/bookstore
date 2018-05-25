package cn.whu.lmq.web.bookstore.dao;

import cn.whu.lmq.web.bookstore.bean.User;
import cn.whu.lmq.web.bookstore.bean.UserInfo;

public interface UserInfoDao extends BaseDao<UserInfo> {
	

	UserInfo findByUser(User user);
}
