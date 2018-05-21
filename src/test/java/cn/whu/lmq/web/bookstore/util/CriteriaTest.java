package cn.whu.lmq.web.bookstore.util;

import cn.whu.lmq.web.bookstore.bean.Address;
import cn.whu.lmq.web.bookstore.bean.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
@Transactional
public class CriteriaTest {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 测试表明user延迟加载的list并不能分页
     */
    @Test
    public void aliasTest() {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("id", 1));
        List<?> list = criteria.getExecutableCriteria(sessionFactory.getCurrentSession()).list();
        User user = (User) list.get(0);
        List<Address> addresses = user.getAddresses().subList(0,2);
        addresses.forEach(a ->
                System.out.println(a.getDetail())
        );
    }
}
