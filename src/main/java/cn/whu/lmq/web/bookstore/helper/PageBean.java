package cn.whu.lmq.web.bookstore.helper;

import java.util.List;

public class PageBean<T> {
	// 总页数
	private int totalPages;
	//总记录数
	private int count;
	// 当前页,从1开始计数
	private int currentPage;
	// 每页大小
	private int pageSize = 5;
	// 记录开始数
	private List<T> list;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getCurrentPage() {

		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


}
