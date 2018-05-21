package cn.whu.lmq.web.bookstore.action;

import cn.whu.lmq.web.bookstore.bean.Product;
import cn.whu.lmq.web.bookstore.bean.ShoppingCart;
import cn.whu.lmq.web.bookstore.bean.User;
import cn.whu.lmq.web.bookstore.bean.UserOrder;
import cn.whu.lmq.web.bookstore.helper.PageBean;
import cn.whu.lmq.web.bookstore.service.CartService;
import cn.whu.lmq.web.bookstore.service.PaymentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller("cartAction")
@Scope("prototype")
public class CartAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    //提供购物车操作的服务
    private CartService cartService;
    //页面传递的商品id
    private List<Integer> productId = new ArrayList<>();
    //分页时的页码
    private int page = 0;
    //购物车里商品的新数量
    private int quantity = 0;
    //操作的用户
    private User user;
    //支付服务
    private PaymentService paymentService;
    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public List<Integer> getProductId() {
        return productId;
    }

    public void setProductId(List<Integer> productId) {
        this.productId = productId;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * 获取整个购物车内容
     */
    public String get() {
        PageBean<ShoppingCart> pageBean = cartService.findByUser(user, page);
        ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
        return NONE;
    }

    /**
     * 添加商品到购物车
     */
    public String addProduct() {
        cartService.addProduct(user, new Product(productId.get(0)));
        return NONE;
    }

    /**
     * 从购物车移出一件商品
     */
    public String removeProduct() {
        cartService.removeProduct(user, new Product(productId.get(0)));
        return NONE;
    }

    /**
     * 修改购物车里商品的数量
     */
    public String updateProductQuantity() {
        cartService.updateProductQuantity(user, new Product(productId.get(0)),quantity);
        return NONE;
    }

    /**
     * 移出购物车多件商品
     */
    public String removeProducts() {
        //将id集合封装成product集合
        Set<Product> products = productId.stream().map(Product::new).collect(Collectors.toSet());
        cartService.removeProducts(user, products);
        return NONE;
    }

    /**
     * 从购物车生成订单,此处订单并没有保存在数据库,而是保存在session
     * @return 成功则跳转到确认订单结算的页面
     */
    public String generateOrder() {
        //先检查是否关联支付账户
        if(paymentService.getByUser(user) == null){
            //没有前去关联账户的页面
            return INPUT;
        }
        //将id集合封装成product集合,对Id去重
        Set<Product> products = productId.stream().map(Product::new).collect(Collectors.toSet());
        UserOrder userOrder = cartService.generateOrder(user, products);
        //将userOrder放入session
        //TODO 订单的地址设置
        ActionContext.getContext().getSession().put("userOrder",userOrder);
        return NONE;
    }

    /**
     * 校验user方法
     */
    @Override
    public void validate(){
        //从session取出user
        user = (User)ActionContext.getContext().get("user");
        if(user == null){
            this.addFieldError("user","没有登录");
        }
    }
}
