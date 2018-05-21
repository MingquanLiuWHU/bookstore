package cn.whu.lmq.web.bookstore.service.exception;

import java.text.MessageFormat;

/**
 * 支付账户不存在抛出的异常
 */
public class NoSuchAccountException extends Exception{
    private static final String error = "账户{0}不存在";
    /**
     *
     * @param account 用户输入的错误账户名称
     */
    public NoSuchAccountException(String account) {
        super(MessageFormat.format(error,account));
    }
}
