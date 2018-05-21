package cn.whu.lmq.web.bookstore.action;

import cn.whu.lmq.web.bookstore.bean.Product;
import cn.whu.lmq.web.bookstore.helper.PageBean;
import cn.whu.lmq.web.bookstore.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class ProductAction extends ActionSupport {
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

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
        ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
        return NONE;
    }

    /**
     * 根据商品的价格区间查询
     */
    public String findByPriceBetween() {
        PageBean<Product> pageBean = productService
                .findByPriceBetween(minPrice, maxPrice, page);
        ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
        return NONE;
    }

    /**
     * 根据商品的类别查询
     */
    public String findByCategory() {
        PageBean<Product> pageBean = productService
                .findByCategory(product.getCategories().get(0), page);
        ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
        return NONE;
    }

    /**
     * 管理员查询所有商品
     */
    public String findAll() {
        PageBean<Product> pageBean = productService.findAll(page);
        ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
        return NONE;
    }

    /**
     * 对save方法的校验，可以直接放在save方法里
     */
    public void validateSave() {
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
     * 保存product,需要admin校验
     */
    public String save() {
        productService.save(product);
        return NONE;
    }

    /**
     * 删除product，需要admin校验
     */
    public String delete(){
        productService.delete(product);
        return NONE;
    }

    /**
     * 更新product的非空字段，需要admin校验，非空字段合理性校验
     */
    public String update(){
        productService.update(product);
        return NONE;
    }
}
