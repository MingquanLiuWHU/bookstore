package cn.whu.lmq.web.bookstore.dao.impl;

import cn.whu.lmq.web.bookstore.bean.PaymentAccount;
import cn.whu.lmq.web.bookstore.bean.User;
import cn.whu.lmq.web.bookstore.dao.PaymentAccountDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("paymentAccountDao")
public class PaymentAccountDaoImpl extends BaseDaoImpl<PaymentAccount> implements PaymentAccountDao {

    @Override
    public PaymentAccount getByUser(User user) {
        List<PaymentAccount> paymentAccounts = findByCriteria(criteria -> {
            criteria.add(Restrictions.eq("user", user));
        });
        if (paymentAccounts == null || paymentAccounts.isEmpty()) {
            return null;
        }
        return paymentAccounts.get(0);
    }

    @Override
    public PaymentAccount getByAccount(String account) {
        List<PaymentAccount> paymentAccounts = findByCriteria(criteria ->
                criteria.add(Restrictions.eq("account", account))
        );
        if (paymentAccounts == null || paymentAccounts.isEmpty()) {
            return null;
        }
        return paymentAccounts.get(0);
    }


}
