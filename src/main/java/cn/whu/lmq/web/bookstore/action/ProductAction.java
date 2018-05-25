package cn.whu.lmq.web.bookstore.action;

import cn.whu.lmq.web.bookstore.bean.Product;
import cn.whu.lmq.web.bookstore.helper.PageBean;
import cn.whu.lmq.web.bookstore.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
    //对product操作的service
    private ProductService productService;
    //传递的product参数
    private Product product;
    //页码
    private int page = 0;
    //最低价格
    private int minPrice = 0;
    //最高价格
    private int maxPrice = 0;
    //列表页面
    private static final String LIST = "list";
    //商品详情页面
    private static final String DETAIL = "detail";
    //编辑页面
    private static final String EDIT = "edit";
    //新建页面
    private static final String NEW = "new";

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 根据商品对应书本的名称模糊查询
     */
    public String findByNameLike() {
        PageBean<Product> pageBean = productService
                .findByNameLike(product.getBook().getBookName(), page);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return LIST;
    }

    /**
     * 根据商品的价格区间查询
     */
    public String findByPriceBetween() {
        PageBean<Product> pageBean = productService
                .findByPriceBetween(minPrice, maxPrice, page);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return LIST;
    }



    /**
     * 管理员查询所有商品
     */
    public String findAll() {
        PageBean<Product> pageBean = productService.findAll(page);
        //将pageBean放入值栈
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return LIST;
    }

    /**
     * 对save方法的校验，可以直接放在save方法里
     */
    public void validateNewOne() {
        if (product == null) {
            this.addFieldError("product", "不能为空");
            return;
        }
        if (product.getQuantity() == null || product.getQuantity() <= 0) {
            this.addFieldError("product", "数量输入错误");
            return;
        }
        if (product.getBook() == null) {
            this.addFieldError("", "");
        }
        // TODO product属性的校验
    }


    /**
     * 删除商品
     * @return 商品列表
     */
    public String delete(){
        productService.delete(product);
        return this.findAll();
    }

    /**
     * 更新product的非空字段，需要admin校验，非空字段合理性校验
     */
    public String update(){
        productService.update(product);
        return NONE;
    }

    /**
     * 查询商品详情
     * @return 编辑页面
     */
    public String edit(){
        product = productService.findById(product);
        //TODO 需不需要把product手动放入值栈？
        return EDIT;
    }

    /**
     * @return 新建商品页面
     */
    public String newOne(){
        return NEW;
    }

    /**
     * 保存商品
     * @return 商品详情页面
     */
    public String save(){
        productService.save(product);
        return DETAIL;
    }



    @Override
    public Product getModel() {
        return product;
    }
}
