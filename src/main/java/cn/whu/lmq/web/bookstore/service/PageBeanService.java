package cn.whu.lmq.web.bookstore.service;

import cn.whu.lmq.web.bookstore.bean.BaseBean;
import cn.whu.lmq.web.bookstore.dao.BaseDao;
import cn.whu.lmq.web.bookstore.helper.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.function.Consumer;

public interface PageBeanService {
    <T extends BaseBean> PageBean<T> listByPage(BaseDao<T> dao, Consumer<DetachedCriteria> consumer, int page,
                                                int pageSize);
}
