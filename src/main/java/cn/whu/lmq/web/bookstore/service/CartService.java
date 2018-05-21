package cn.whu.lmq.web.bookstore.service;

import cn.whu.lmq.web.bookstore.bean.Product;
import cn.whu.lmq.web.bookstore.bean.ShoppingCart;
import cn.whu.lmq.web.bookstore.bean.User;
import cn.whu.lmq.web.bookstore.bean.UserOrder;
import cn.whu.lmq.web.bookstore.helper.PageBean;

import java.util.Set;

public interface CartService  {
	/*
	cart分页单页大小
	 */
	int PAGE_SIZE = 5;
	/**
	 * 添加product 到 cart
	 * 
	 * @param user 用户
	 */
	void addProduct(User user ,Product product);

    /**
     * 从购物车移除某件商品
     * @param user 用户
     * @param product 商品
     */
	void removeProduct(User user,Product product);

    /**
     * 从购物车移除多件商品
     * @param user 用户
     * @param products 商品集合
     */
	void removeProducts(User user,Set<Product> products);


    /**
     * 用户分页查看购物车
     * @param user 用户
     * @param page 页码
     * @return 分页列表
     */
	PageBean<ShoppingCart> findByUser(User user, int page);

    /**
     * 根据购物车里选择的商品生成订单
     * @param user 用户
     * @param products 选中的商品
     * @return 用户订单
     */
	UserOrder generateOrder(User user, Set<Product> products);

    /**
     * 更新购物车商品的数量
     * @param user 用户
     * @param product 商品
     * @param quantity 新的数量
     */
    void updateProductQuantity(User user, Product product,int quantity);
}
