package cn.whu.lmq.web.bookstore.service.impl;

import cn.whu.lmq.web.bookstore.bean.PaymentAccount;
import cn.whu.lmq.web.bookstore.bean.User;
import cn.whu.lmq.web.bookstore.dao.PaymentAccountDao;
import cn.whu.lmq.web.bookstore.service.PaymentService;
import cn.whu.lmq.web.bookstore.service.exception.NoSuchAccountException;
import cn.whu.lmq.web.bookstore.service.exception.NotEnoughBalanceException;
import cn.whu.lmq.web.bookstore.service.exception.PasswordErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service("paymentService")
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private PaymentAccountDao paymentAccountDao;
    @Autowired
    public void setPaymentAccountDao(PaymentAccountDao paymentAccountDao) {
        this.paymentAccountDao = paymentAccountDao;
    }

    /**
     * 从pay转账到receive
     * @param pay 支付的账户，账户名称不能为空，密码不能为空
     * @param receive 接收的账户,账户名称不能为空
     * @param money 转账的金额
     */
    @Override
    public void pay(PaymentAccount pay, PaymentAccount receive, BigDecimal money)
            throws NoSuchAccountException,PasswordErrorException ,NotEnoughBalanceException{
        PaymentAccount existPay = paymentAccountDao.findById(pay);
        PaymentAccount existReceive = paymentAccountDao.findById(receive);
        //若有账户不存在
        if(existPay == null){
            throw new NoSuchAccountException(pay.getAccount());
        }
        if(existReceive == null){
            throw new NoSuchAccountException(receive.getAccount());
        }

        //密码错误
        if(!existPay.getPaymentPassword().equals(pay.getPaymentPassword())){
            throw new PasswordErrorException(existPay.getAccount());
        }

        if(existPay.getBalance().compareTo(money)<0){
            //余额不足无法支付
            throw new NotEnoughBalanceException(existPay.getAccount());
        }
        //转移money并更新
        existPay.setBalance(existPay.getBalance().subtract(money));
        paymentAccountDao.update(existPay);
        existReceive.setBalance(existReceive.getBalance().add(money));
        paymentAccountDao.update(existReceive);
    }

    @Override
    public PaymentAccount findByUser(User user) {
        return paymentAccountDao.findByUser(user);
    }

    @Override
    public PaymentAccount findByAccount(String account) {
        return paymentAccountDao.findByAccount(account);
    }
}
