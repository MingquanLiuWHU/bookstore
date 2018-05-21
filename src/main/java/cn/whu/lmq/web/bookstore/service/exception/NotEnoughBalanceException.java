package cn.whu.lmq.web.bookstore.service.exception;

import java.text.MessageFormat;

/**
 * 账户余额不足抛出该异常
 */
public class NotEnoughBalanceException extends Exception {
    private static final String error = "账户{0}没有足够余额";

    /**
     * 附加错误提示的构造方法
     * @param account 余额不足的账户名称
     */
    public NotEnoughBalanceException(String account) {
        super(MessageFormat.format(error,account));
    }


}
