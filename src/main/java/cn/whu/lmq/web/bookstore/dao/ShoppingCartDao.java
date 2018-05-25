package cn.whu.lmq.web.bookstore.dao;

import cn.whu.lmq.web.bookstore.bean.Product;
import cn.whu.lmq.web.bookstore.bean.ShoppingCart;
import cn.whu.lmq.web.bookstore.bean.User;

import java.util.List;
import java.util.Set;

public interface ShoppingCartDao extends BaseDao<ShoppingCart> {
	
	List<ShoppingCart> findByIds(List<Integer> ids);

	/**
	 * 根据用户和商品唯一确定购物车（细则）
	 * @param user 用户
	 * @param product 商品
	 * @return 用户名下对应该商品的购物车
	 */
	ShoppingCart findByUserAndProduct(User user, Product product);

	/**
	 * 根据用户和商品列表得到多项购物车（细则）
	 * @param user 用户
	 * @param products 商品集合
	 * @return 商品集合对应的购物车集合，可能有部分商品没有对应
	 */
	List<ShoppingCart> findByUserAndProducts(User user,Set<Product> products);
}
