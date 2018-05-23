package cn.whu.lmq.web.bookstore.action;

import cn.whu.lmq.web.bookstore.bean.User;
import cn.whu.lmq.web.bookstore.bean.UserOrder;
import cn.whu.lmq.web.bookstore.helper.PageBean;
import cn.whu.lmq.web.bookstore.service.OrderService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * 对订单的处理
 * 所有操作都需要登录
 */
@Controller("orderAction")
@Scope("prototype")
public class OrderAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    //商品id和对应购买数量的映射
    private Map<Integer, Integer> map = new HashMap<>();
    private UserOrder userOrder;
    //分页页面
    private int page = 0;
    //订单对应的地址id,-1默认值保证不会有真实地址对应
    private int addressId = -1;
    private User user;

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Integer> map) {
        this.map = map;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 登录校验，给user赋值
     */
    @Override
    public void validate() {
        user = (User) ActionContext.getContext().get("user");
        if (user == null) {
            addFieldError("login", "没有登录");
        }
    }

    /**
     * 店主发货，修改发货状态
     *
     * @return 成功则跳转回订单处理的页面
     */
    public String sent() {
        //需要再修改，因为还需要更新该订单的物流信息
        orderService.updateState(userOrder);
        return NONE;
    }

    /**
     * 查看所有订单
     *
     * @return 跳转到订单显示页面
     */
    public String findAll() {
        PageBean<UserOrder> pageBean = orderService.findAll(user, page);
        ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
        return NONE;
    }

    /**
     * 对订单进行结算
     *
     * @return 结算成功到显示订单详情的页面
     * 失败可能跳转到关联支付账户的页面
     * 余额不足显示提示信息页面
     */
    public String balance() {
        //如果处理未保存的订单，从session取出订单，根据页面传递的参数修改数量
        if (userOrder == null) {
            //如果页面没有传递
            userOrder = (UserOrder) ActionContext.getContext().get("userOrder");
        }
        orderService.balance(user, userOrder);
        return NONE;
    }

    /**
     * 用户确认收货
     */
    public String received() {
        orderService.confirmReceived(user, userOrder);
        return NONE;
    }

    /**
     * 对session里订单保存结算,需要上传确定关联的地址
     */
    public String saveAndBalance() {
        //从session取出订单
        UserOrder order = (UserOrder) ActionContext.getContext().get("userOrder");
        if (order == null) {
            return ERROR;
        }
        //清空session里的订单
        ActionContext.getContext().getSession().put("userOrder", null);
        //保存并尝试结算
       // orderService.saveAndBalance(user,order,map,addressId);
        return NONE;
    }


}
