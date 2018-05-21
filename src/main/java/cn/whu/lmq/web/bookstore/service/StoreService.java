package cn.whu.lmq.web.bookstore.service;

import cn.whu.lmq.web.bookstore.bean.Store;

public interface StoreService {

	void saveOrUpdate(Store store);

    Store getUniqueStore();
}
