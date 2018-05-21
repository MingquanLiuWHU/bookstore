package cn.whu.lmq.web.bookstore.service.constant;

public enum PayReturnKey {
    NO_PAY_ACCOUNT,//缺少支付账户
    NO_RECEIVE_ACCOUNT,//缺少收款账户
    NOT_EXIST_PAY_ACCOUNT,//支付账户不存在
    NOT_EXIST_RECEIVE_ACCOUNT,//收款账户不存在
    WRONG_PASSWORD,//密码错误
    NOT_ENOUGH_BALANCE,//没有足够余额
    SUCCESS//成功支付
}
