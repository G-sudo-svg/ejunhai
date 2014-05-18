package com.ejunhai.junhaimall.coupon.constant;

/**
 * 优惠券常量类
 * 
 * @author 罗正加
 * @date 2011-5-18
 * 
 */
public class CouponConstant {
	/**
	 * 优惠券类型：代金券
	 */
	public static final Short COUPON_TYPE_D = 1;

	/**
	 * 优惠券方案状态：未开始
	 */
	public static final Short COUPON_SCHEME_STATE_WAIT = 0;

	/**
	 * 优惠券方案状态：已开始
	 */
	public static final Short COUPON_SCHEME_STATE_OK = 1;

	/**
	 * 优惠券方案状态：已结束
	 */
	public static final Short COUPON_SCHEME_STATE_STOP = 2;

	/**
	 * 优惠券状态：未激活/未生效
	 */
	public static final Short COUPON_STATE_NO_ACTIVATE = 0;

	/**
	 * 优惠券状态：已生效
	 */
	public static final Short COUPON_STATE_ACTIVATE = 1;

	/**
	 * 优惠券状态：已使用
	 */
	public static final Short COUPON_STATE_USED = 2;

	/**
	 * 优惠券状态：已过期
	 */
	public static final Short COUPON_STATE_EXPIRE = 3;

	/**
	 * 优惠券状态：已作废
	 */
	public static final Short COUPON_STATE_DISCARD = 4;

	/**
	 * 优惠券密码扰乱：未扰乱
	 */
	public static final Integer COUPON_PASSWORD_DISTURB_NO = 0;

	/**
	 * 优惠券密码扰乱：已扰乱
	 */
	public static final Integer COUPON_PASSWORD_DISTURB_YES = 1;

	/**
	 * 优惠券初始化不激活
	 */
	public static final Integer COUPON_INIT_ACTIVFATE_NO = 0;

	/**
	 * 优惠券初始化激活
	 */
	public static final Integer COUPON_INIT_ACTIVFATE_YES = 1;
	
	/**
	 * 预订延迟时间
	 */
	public static final String KEY_COUPON_DEFERDATE = "KEY_COUPON_DEFERDATE";
}
