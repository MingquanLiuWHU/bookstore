package cn.whu.lmq.web.bookstore.dao.impl;

import cn.whu.lmq.web.bookstore.bean.BaseBean;
import cn.whu.lmq.web.bookstore.dao.BaseDao;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Consumer;

public abstract class BaseDaoImpl<T extends BaseBean> extends HibernateDaoSupport implements BaseDao<T> {
	/**
	 * 注入父类sessionFactory
	 */
	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void deleteAll(List<T> list) {
		this.getHibernateTemplate().deleteAll(list);
	}

	@Override
	public  List<T> findByCriteria(Consumer<DetachedCriteria> consumer) {
		return this.findByCriteria( consumer, -1, -1);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(Consumer<DetachedCriteria> consumer, int firstResult,
			int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(this.getClass());
		consumer.accept(criteria);
		//criteria.createAlias("","",JoinType.FULL_JOIN);
		// 此处保证criteria得到的list能被转换成T类型
		criteria.setResultTransformer(Criteria.ROOT_ENTITY);
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
	}

	@Override
	public int count(Consumer<DetachedCriteria> consumer) {
		DetachedCriteria criteria = DetachedCriteria.forClass(this.getClass());
		consumer.accept(criteria);
		Long rowCount = (Long) criteria
				.getExecutableCriteria(this.getHibernateTemplate().getSessionFactory().getCurrentSession())
				.setProjection(Projections.rowCount()).uniqueResult();
		return rowCount.intValue();
	}

	@SuppressWarnings("unchecked")
	public T getById(T t) {
		return (T)this.getHibernateTemplate().get(t.getClass().getName(),t.getId());
	}

}
