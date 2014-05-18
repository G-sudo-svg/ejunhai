package com.ejunhai.junhaimall.coupon.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 罗正加
 * 
 * @date 2012-06-20 18:13:55
 */
public class CouponScheme implements Serializable {

	/***/
	private static final long serialVersionUID = 1L;

	/***/
	private String id;

	/**
	 * 优惠券名称
	 */
	private String couponName;

	/**
	 * 状态
	 */
	private Short state;

	/**
	 * 是否初始化激活
	 */
	private Integer initActivate;

	/**
	 * 开始时间
	 */
	private Date useStartdate;

	/**
	 * 结束时间
	 */
	private Date useEnddate;

	/**
	 * 面值
	 */
	private Integer parValue;

	/**
	 * 发行总量
	 */
	private Integer issueAmount;

	/**
	 * 日限发行量
	 */
	private Integer dayLimitNum;

	/**
	 * 已发行数量
	 */
	private Integer hasIssueNum;

	/**
	 * 已使用数量
	 */
	private Integer hasUseNum;

	/**
	 * 是否已扰乱
	 */
	private Integer hasDisturb;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 备注
	 */
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Integer getInitActivate() {
		return initActivate;
	}

	public void setInitActivate(Integer initActivate) {
		this.initActivate = initActivate;
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

	public Integer getParValue() {
		return parValue;
	}

	public void setParValue(Integer parValue) {
		this.parValue = parValue;
	}

	public Integer getIssueAmount() {
		return issueAmount;
	}

	public void setIssueAmount(Integer issueAmount) {
		this.issueAmount = issueAmount;
	}

	public Integer getDayLimitNum() {
		return dayLimitNum;
	}

	public void setDayLimitNum(Integer dayLimitNum) {
		this.dayLimitNum = dayLimitNum;
	}

	public Integer getHasIssueNum() {
		return hasIssueNum;
	}

	public void setHasIssueNum(Integer hasIssueNum) {
		this.hasIssueNum = hasIssueNum;
	}

	public Integer getHasUseNum() {
		return hasUseNum;
	}

	public void setHasUseNum(Integer hasUseNum) {
		this.hasUseNum = hasUseNum;
	}

	public Integer getHasDisturb() {
		return hasDisturb;
	}

	public void setHasDisturb(Integer hasDisturb) {
		this.hasDisturb = hasDisturb;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
