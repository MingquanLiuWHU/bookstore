package cn.whu.lmq.web.bookstore.action;

import cn.whu.lmq.web.bookstore.bean.Store;
import cn.whu.lmq.web.bookstore.service.StoreService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 店铺的信息查看修改
 * 此Action后台才可以访问
 *
 * @author LMQ
 */

@Controller("storeAction")
@Scope("prototype")
public class StoreAction extends ActionSupport implements ModelDriven<Store> {
    private static final long serialVersionUID = 1L;
    //店铺对象，传输参数
    private Store store = new Store();
    //店铺对应的服务层
    private StoreService storeService;
    //编辑店铺信息的页面
    private static final String EDIT = "edit";

    @Autowired
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public Store getModel() {
        return store;
    }


    public String get() {
        store = storeService.getUniqueStore();
        if (store == null) {
            //初始化店铺
            return EDIT;
        }
        return SUCCESS;
    }


    public String saveOrUpdate() {
        storeService.saveOrUpdate(store);
        return SUCCESS;
    }

    public String edit() {
        store = storeService.getUniqueStore();
        return EDIT;
    }
}
