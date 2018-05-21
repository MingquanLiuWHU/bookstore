package cn.whu.lmq.web.bookstore.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.whu.lmq.web.bookstore.bean.Store;
import cn.whu.lmq.web.bookstore.service.StoreService;

/**
 *
 * 店铺的信息查看修改
 * 此Action后台才可以访问
 * @author LMQ
 *
 */

@Controller("storeAction")
@Scope("prototype")
public class StoreAction extends ActionSupport implements ModelDriven<Store> {
	private static final long serialVersionUID = 1L;
	private Store store = new Store();
	private StoreService storeService;
	// private Address address;

	@Autowired
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	@Override
	public Store getModel() {
		return store;
	}


	public String get() {
		//Admin admin = (Admin) ActionContext.getContext().getSession().get("admin");
		store = storeService.getUniqueStore();

		// store打开成功,放入session
		ActionContext.getContext().getSession().put("store", store);
		return "storeView";
	}



	/**
	 * TODO store.storename,store.address.id非空检验
	 */
	public String saveOrUpdate() {
		//Admin admin = (Admin) ActionContext.getContext().getSession().get("admin");
        storeService.saveOrUpdate(store);
		return NONE;
	}

}
