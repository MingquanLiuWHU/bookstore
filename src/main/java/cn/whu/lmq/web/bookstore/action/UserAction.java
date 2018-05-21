package cn.whu.lmq.web.bookstore.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.whu.lmq.web.bookstore.bean.User;
import cn.whu.lmq.web.bookstore.service.UserService;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user = new User();
	private UserService userService;
	@Override
	public User getModel() {
		return user;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 处理用户登录操作
	 */
	public String login() {
		user = userService.login(user);
		if(user==null) {
			this.addActionError("用户名或者密码错误");
			return INPUT;
		}
		ActionContext.getContext().getSession().put("user", user);
		return NONE;
	}
	
	/**
	 * 用户注册
	 */
	public String register() {
		user = userService.register(user);
		if(user == null) {
			this.addActionError("用户已存在");
			return "register";
		}
		ActionContext.getContext().getSession().put("user",user);
		return NONE;
	}
	/**
	 * 修改用户名和密码
	 */
	public String update() {
		user = userService.updateUsernameAndPassword(user);
		ActionContext.getContext().getSession().put("user", user);
		return NONE;
	}
	/**
	 * 用户退出清空session
	 */
	public String exit() {
		ActionContext.getContext().getSession().clear();
		return NONE;
	}
}
