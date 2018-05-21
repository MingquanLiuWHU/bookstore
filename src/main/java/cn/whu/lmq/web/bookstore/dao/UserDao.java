package cn.whu.lmq.web.bookstore.dao;

import cn.whu.lmq.web.bookstore.bean.User;

public interface UserDao extends BaseDao<User>{
	
	
	User getByAccount(String account);
}
