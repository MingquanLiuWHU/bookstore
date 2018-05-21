package cn.whu.lmq.web.bookstore.dao.impl;

import cn.whu.lmq.web.bookstore.bean.Address;
import cn.whu.lmq.web.bookstore.bean.OrderState;
import cn.whu.lmq.web.bookstore.bean.User;
import cn.whu.lmq.web.bookstore.bean.UserOrder;
import cn.whu.lmq.web.bookstore.dao.OrderDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class OrderDaoImplTest {
    private OrderDao orderDao;
    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * 测试订单保存，成功
     */
    @Test
    @Rollback(false)
    @Transactional
    public void save() {
        UserOrder order = new UserOrder();
        order.setState(OrderState.CANCELED);
        User user = new User();
        user.setId(1);
        order.setUser(user);
        Address address = new Address();
        address.setId(1);
        order.setAddress(address);
        order.setTotalMoney(BigDecimal.TEN);
        orderDao.save(order);
    }
}