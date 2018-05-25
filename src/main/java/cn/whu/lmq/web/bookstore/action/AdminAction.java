package cn.whu.lmq.web.bookstore.action;

import cn.whu.lmq.web.bookstore.bean.Admin;
import cn.whu.lmq.web.bookstore.service.AdminService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class AdminAction extends ActionSupport implements ModelDriven<Admin> {
    private AdminService adminService;

    //注入admin对应的Service
    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    private Admin admin = new Admin();



    /**
     * 管理员登录
     *
     * @return 登录成功进入主页，失败返回登录页面
     */
    public String login() {
        Admin existAdmin = adminService.login(admin.getAccount(), admin.getPassword());
        if (existAdmin == null) {
            System.out.println(admin.getAccount());
            System.out.println(admin.getPassword());
            this.addFieldError("accountError","用户不存在或密码错误");
            return INPUT;
        }
        ActionContext.getContext().getSession().put("admin",admin);
        return SUCCESS;
    }

    public String exit() {
        ActionContext.getContext().getSession().clear();
        return LOGIN;
    }

    @Override
    public Admin getModel() {
        return admin;
    }
}
