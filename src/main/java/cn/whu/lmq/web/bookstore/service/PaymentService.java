package cn.whu.lmq.web.bookstore.service;

import cn.whu.lmq.web.bookstore.bean.PaymentAccount;
import cn.whu.lmq.web.bookstore.bean.User;
import cn.whu.lmq.web.bookstore.service.exception.NoSuchAccountException;
import cn.whu.lmq.web.bookstore.service.exception.NotEnoughBalanceException;
import cn.whu.lmq.web.bookstore.service.exception.PasswordErrorException;

import java.math.BigDecimal;

public interface PaymentService {
    /**
     * 从pay转账到receive
     *
     * @param pay     支付的账户，账户名称不能为空，密码不能为空
     * @param receive 接收的账户,账户名称不能为空
     * @param money   转账的金额
     * @throws NoSuchAccountException 账户不存在
     * @throws PasswordErrorException 密码错误
     * @throws NotEnoughBalanceException 余额不足
     */
    void pay(PaymentAccount pay, PaymentAccount receive, BigDecimal money)
            throws NoSuchAccountException, PasswordErrorException, NotEnoughBalanceException;


    /**
     * 获取用户对应的支付账户
     */
    PaymentAccount findByUser(User user);

    /**
     * 根据账户名称获取支付账户
     */
    PaymentAccount findByAccount(String account);
}
