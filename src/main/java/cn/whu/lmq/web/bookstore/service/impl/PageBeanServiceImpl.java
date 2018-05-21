package cn.whu.lmq.web.bookstore.service.impl;

import java.util.List;
import java.util.function.Consumer;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import cn.whu.lmq.web.bookstore.bean.BaseBean;
import cn.whu.lmq.web.bookstore.dao.BaseDao;
import cn.whu.lmq.web.bookstore.helper.PageBean;
import cn.whu.lmq.web.bookstore.service.PageBeanService;

@Service("pageBeanService")
public class PageBeanServiceImpl implements PageBeanService {
	/**
	 * 处理分页查询
	 */
	@Override
	public <T extends BaseBean> PageBean<T> listByPage(BaseDao<T> dao,
			Consumer<DetachedCriteria> consumer, int page, int pageSize) {
		PageBean<T> pageBean = new PageBean<>();
		// 记录总数
		int count = dao.count( consumer);
		pageBean.setCount(count);
		// 设置页面大小

		pageBean.setPageSize(pageSize);
		// 计算总页数
		int floor = count % pageBean.getPageSize() == 0 ? 0 : 1;
		int totalPages = count / pageBean.getPageSize() + floor;
		// 设置总页数
		pageBean.setTotalPages(totalPages);
		// page参数校验
		if (page <= 0) {
			page = 1;
		}
		if (page > totalPages) {
			page = totalPages;
		}
		// 设置当前页
		pageBean.setCurrentPage(page);
		// 计算查询开始记录数
		int firstResult = (pageBean.getCurrentPage() - 1) * pageBean.getPageSize();
		// 分页查询
		List<T> list = dao.findByCriteria( consumer, firstResult, pageBean.getPageSize());
		// 设置记录集合
		pageBean.setList(list);
		return pageBean;
	}

}
