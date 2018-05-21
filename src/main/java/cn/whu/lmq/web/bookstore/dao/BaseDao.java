package cn.whu.lmq.web.bookstore.dao;

import java.util.List;
import java.util.function.Consumer;

import org.hibernate.criterion.DetachedCriteria;

import cn.whu.lmq.web.bookstore.bean.BaseBean;

public interface BaseDao<T extends BaseBean> {
	T getById(T t);

	List<T> findByCriteria(Consumer<DetachedCriteria> consumer);

	List<T> findByCriteria(Consumer<DetachedCriteria> consumer, int firstResult, int maxResults);

	int count(Consumer<DetachedCriteria> consumer);

	void save(T t);

	void delete(T t);

	void deleteAll(List<T> list);

	void update(T t);
}
