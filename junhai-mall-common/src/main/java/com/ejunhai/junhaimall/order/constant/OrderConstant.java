package com.ejunhai.junhaimall.order.constant;

/**
 * 订单常量类
 * 
 * @author 罗正加
 * @date 2011-5-18
 * 
 */
public class OrderConstant {
    /**
     * 订单状态：待发货
     */
    public static final Integer ORDER_STATE_WAIT_DELIVER = 0;

    /**
     * 订单状态：已发货
     */
    public static final Integer ORDER_STATE_HAVE_DELIVER = 1;

    /**
     * 订单发送过短信：no
     */
    public static final Integer ORDER_SEND_SMS_NO = 0;

    /**
     * 订单发送过短信：yes
     */
    public static final Integer ORDER_SEND_SMS_YES = 1;

    /**
     * 订单日志类型：订单
     */
    public static final Integer ORDER_LOG_TYPE_MAIN = 0;

    /**
     * 订单日志类型：补货单
     */
    public static final Integer ORDER_LOG_TYPE_REPL = 1;

    /**
     * 服务器图片存放地址
     */
    public static final String SEVER_IMAGE_UPLOAD_PATH = "SEVER_IMAGE_UPLOAD_PATH";

    /**
     * 快递单：寄件公司
     */
    public static final String EXPRESS_DELIVERY_COMPANY = "EXPRESS_DELIVERY_COMPANY";

    /**
     * 快递单：寄件人
     */
    public static final String EXPRESS_DELIVERY_SENDER = "EXPRESS_DELIVERY_SENDER";

    /**
     * 快递单：寄件省市区
     */
    public static final String EXPRESS_DELIVERY_PROCITYAREA = "EXPRESS_DELIVERY_PROCITYAREA";

    /**
     * 快递单：寄件详细地址
     */
    public static final String EXPRESS_DELIVERY_DETAIADDRESS = "EXPRESS_DELIVERY_DETAIADDRESS";

    /**
     * 快递单：寄件人电话
     */
    public static final String EXPRESS_DELIVERY_TELEPHONE = "EXPRESS_DELIVERY_TELEPHONE";

    /**
     * 快递单：寄件人移动电话
     */
    public static final String EXPRESS_DELIVERY_MOBILE_PHONE = "EXPRESS_DELIVERY_MOBILE_PHONE";

    /**
     * 快递单：寄件内容
     */
    public static final String EXPRESS_DELIVERY_CONTENT = "EXPRESS_DELIVERY_CONTENT";

    /**
     * 快递单：寄件人签署
     */
    public static final String EXPRESS_DELIVERY_SENDER_SIGN = "EXPRESS_DELIVERY_SENDER_SIGN";

    /**
     * 快递单：客户编码
     */
    public static final String EXPRESS_DELIVERY_CUSTOMER_CODE = "EXPRESS_DELIVERY_CUSTOMER_CODE";

    /**
     * 快递单：原寄地
     */
    public static final String EXPRESS_DELIVERY_ORIGN_ADDRESS = "EXPRESS_DELIVERY_ORIGN_ADDRESS";

    /**
     * 快递单：月结账号
     */
    public static final String EXPRESS_DELIVERY_MONTHLY_PAYMENT = "EXPRESS_DELIVERY_MONTHLY_PAYMENT";

    /**
     * 订单日预订最大数量
     */
    public static final String ORDER_RESERVE_MAX_COUNT = "ORDER_RESERVE_MAX_COUNT";
}
