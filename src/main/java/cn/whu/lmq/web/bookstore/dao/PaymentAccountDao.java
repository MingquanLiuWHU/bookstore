package cn.whu.lmq.web.bookstore.dao;

import cn.whu.lmq.web.bookstore.bean.PaymentAccount;
import cn.whu.lmq.web.bookstore.bean.User;

public interface PaymentAccountDao extends BaseDao<PaymentAccount>{
	
	PaymentAccount findByUser(User user);

	/**
	 * 根据账户名获取支付账户
	 * @param account 账户名称
	 * @return 支付的账户
	 */
	PaymentAccount findByAccount(String account);
}
