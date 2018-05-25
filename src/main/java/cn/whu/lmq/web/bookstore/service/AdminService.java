package cn.whu.lmq.web.bookstore.service;

import cn.whu.lmq.web.bookstore.bean.Admin;

public interface AdminService {

    /**
     * 根据账号和密码登录
     * @param account 账号
     * @param password 密码
     * @return 管理员账户
     */
    Admin login(String account,String password);
}
