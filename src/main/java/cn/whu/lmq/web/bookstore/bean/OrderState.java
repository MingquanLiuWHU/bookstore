package cn.whu.lmq.web.bookstore.bean;

public enum OrderState {
    /**
     *
     */
    CLOSED,//订单已关闭，交易失败
    CANCELED,//订单未支付，取消
    PAIED,//订单已支付,该状态表示用户对应的支付账户已扣去订单的费用
    SENT ,//订单已发货
    RECEIVED//用户确认收货,交易完成，店主账户进账
}
