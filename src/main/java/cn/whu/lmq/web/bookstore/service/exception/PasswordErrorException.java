package cn.whu.lmq.web.bookstore.service.exception;

import java.text.MessageFormat;

public class PasswordErrorException extends Exception {
    private static final String error = "账户{0}密码错误";
    /**
     * 密码错误
     * @param account 密码错误的账户
     */
    public PasswordErrorException(String account) {
        super(MessageFormat.format(error,account));
    }
}
