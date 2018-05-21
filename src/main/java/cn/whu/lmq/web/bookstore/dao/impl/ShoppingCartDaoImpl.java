package cn.whu.lmq.web.bookstore.dao.impl;

import cn.whu.lmq.web.bookstore.bean.Product;
import cn.whu.lmq.web.bookstore.bean.ShoppingCart;
import cn.whu.lmq.web.bookstore.bean.User;
import cn.whu.lmq.web.bookstore.dao.ShoppingCartDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("shoppingCartDetailDao")
public class ShoppingCartDaoImpl extends BaseDaoImpl<ShoppingCart> implements ShoppingCartDao {


    @Override
    public List<ShoppingCart> findByIds(List<Integer> ids) {
        return findByCriteria(criteria ->
                criteria.add(Restrictions.in("id", ids))
        );
    }

    @Override
    public ShoppingCart getByUserAndProduct(User user, Product product) {
        List<ShoppingCart> carts = findByCriteria(criteria -> {
            criteria.add(Restrictions.eq("user", user));
            criteria.add(Restrictions.eq("product", product));
        });
        if (carts == null || carts.isEmpty()) {
            return null;
        }
        return carts.get(0);
    }

    /**
     * 根据用户和商品列表得到多项购物车（细则）
     *
     * @param user     用户
     * @param products 商品集合
     * @return 商品集合对应的购物车集合，可能有部分商品没有对应
     */
    @Override
    public List<ShoppingCart> findByUserAndProducts(User user, Set<Product> products) {
        return findByCriteria(criteria->{
            criteria.add(Restrictions.eq("user",user));
            criteria.add(Restrictions.in("product",products));
        });
    }
}
