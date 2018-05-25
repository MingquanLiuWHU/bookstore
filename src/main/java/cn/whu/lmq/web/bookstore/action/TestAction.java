package cn.whu.lmq.web.bookstore.action;

import cn.whu.lmq.web.bookstore.bean.Book;
import cn.whu.lmq.web.bookstore.bean.Product;
import cn.whu.lmq.web.bookstore.helper.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller("testAction")
@Scope("prototype")
public class TestAction extends ActionSupport {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private File image;
    private String imageFileName;
    private String imageContentType;

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String test() {
        PageBean<Product> pageBean = new PageBean<>();
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        Book book = new Book();
        product.setBook(book);
        book.setBookName("书名");
        product.setQuantity(10);
        product.setPrice(BigDecimal.TEN);
        product.setImageUrl("html模板/1.jpg");
        product.setSoldQuantity(3);
        products.add(product);
        pageBean.setList(products);
        pageBean.setCurrentPage(1);
        pageBean.setCount(6);
        pageBean.setTotalPages(3);
        product.setId(10);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return SUCCESS;
    }

    public String detail(){
        String id = ServletActionContext.getRequest().getParameter("id");
        System.out.println("----------");
        System.out.println("id="+id);
        return NONE;
    }

    public String upload(){
        String realPath = ServletActionContext.getServletContext().getRealPath("/images");
        System.out.println(realPath);
        if(image != null){
            File saveFile = new File(new File(realPath), "newImage.jpg");
            if(!saveFile.getParentFile().exists()){
               saveFile.getParentFile().mkdirs();
            }
            try {
                FileUtils.copyFile(image, saveFile);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        ServletActionContext.getRequest().setAttribute("image","newImage.jpg");
        return SUCCESS;
    }

    public String url(){
        return SUCCESS;
    }
}
