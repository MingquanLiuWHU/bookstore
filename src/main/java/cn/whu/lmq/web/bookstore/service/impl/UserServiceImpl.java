package cn.whu.lmq.web.bookstore.service.impl;

import cn.whu.lmq.web.bookstore.bean.User;
import cn.whu.lmq.web.bookstore.dao.UserDao;
import cn.whu.lmq.web.bookstore.service.UserService;
import cn.whu.lmq.web.bookstore.util.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 用户登录
	 */
	@Override
	public User login(User user) {
		User exist = userDao.findByAccount(user.getAccount());
		if(exist.getPassword().equals(user.getPassword())) {
			return exist;
		}
		return null;
	}

	/**
	 * 帐号注册,必须先检验user.username,password,account不空
	 */
	@Override
	public User register(User user) {
		
		// 查看用户是否已存在
		User existUser = userDao.findByAccount(user.getAccount());
		if (existUser == null) {
			userDao.save(user);
			return user;
		}
		return null;
	}

	/**
	 * 更新用户名和密码,之前检验id,username,password不空
	 */
	@Override
	public User updateUsernameAndPassword(User user) {
		User existUser = userDao.findById(user);
		if (existUser == null) {
			return null;
		}
		//帐号不能更新
		CopyUtil.copyNotNullFields(user, existUser,"account");
		userDao.update(existUser);
		return existUser;
	}



}
