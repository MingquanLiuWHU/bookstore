package cn.whu.lmq.web.bookstore.service;

import cn.whu.lmq.web.bookstore.bean.User;

public interface UserService {

	User login(User user);

	User register(User user);

	User updateUsernameAndPassword(User user);


}
