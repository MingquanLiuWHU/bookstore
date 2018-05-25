package cn.whu.lmq.web.bookstore.service.impl;

import cn.whu.lmq.web.bookstore.bean.Admin;
import cn.whu.lmq.web.bookstore.dao.AdminDao;
import cn.whu.lmq.web.bookstore.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao;

    //注入admin对应的dao
    @Autowired
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }


    private Admin findByAccount(String account) {
        if (StringUtils.isAllBlank(account)) {
            return null;
        }
        List<Admin> admins = adminDao.findByCriteria(
                criteria -> criteria.add(Restrictions.eq("account", account)));
        if (admins == null || admins.isEmpty()) {
            System.out.println("测试");
            return null;
        }
        return admins.get(0);
    }

    /**
     * 根据账号和密码登录
     *
     * @param account  账号
     * @param password 密码
     * @return 管理员账户
     */
    @Override
    public Admin login(String account, String password) {
        Admin existAdmin = findByAccount(account);
        if (existAdmin == null) {
            return null;
        }
        if (existAdmin.getPassword().equals(password)) {
            return existAdmin;
        }
        return null;
    }
}
