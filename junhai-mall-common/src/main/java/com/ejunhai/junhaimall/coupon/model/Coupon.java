package com.ejunhai.junhaimall.coupon.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 罗正加
 * 
 * @date 2012-06-20 18:13:28
 */
public class Coupon implements Serializable {

	/***/
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	private String id;

	/**
	 * 优惠券码
	 */
	private String couponNumber;

	/**
	 * 优惠券密码
	 */
	private String couponPassword;

	/**
	 * 优惠券模板ID
	 */
	private String couponSchemeId;

	/**
	 * 优惠券名称
	 */
	private String couponName;

	/**
	 * 面值
	 */
	private Integer parValue;

	/**
	 * 状态
	 */
	private Short state;

	/**
	 * 开始时间
	 */
	private Date useStartdate;

	/**
	 * 结束时间
	 */
	private Date useEnddate;

	/**
	 * 使用时间
	 */
	private Date useTime;

	/**
	 * 使用订单号
	 */
	private String useOrderNumber;

	/**
	 * 生成时间
	 */
	private Date generateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCouponNumber() {
		return couponNumber;
	}

	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}

	public String getCouponPassword() {
		return couponPassword;
	}

	public void setCouponPassword(String couponPassword) {
		this.couponPassword = couponPassword;
	}

	public String getCouponSchemeId() {
		return couponSchemeId;
	}

	public void setCouponSchemeId(String couponSchemeId) {
		this.couponSchemeId = couponSchemeId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Integer getParValue() {
		return parValue;
	}

	public void setParValue(Integer parValue) {
		this.parValue = parValue;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Date getUseStartdate() {
		return useStartdate;
	}

	public void setUseStartdate(Date useStartdate) {
		this.useStartdate = useStartdate;
	}

	public Date getUseEnddate() {
		return useEnddate;
	}

	public void setUseEnddate(Date useEnddate) {
		this.useEnddate = useEnddate;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	public String getUseOrderNumber() {
		return useOrderNumber;
	}

	public void setUseOrderNumber(String useOrderNumber) {
		this.useOrderNumber = useOrderNumber;
	}

	public Date getGenerateTime() {
		return generateTime;
	}

	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}
}
