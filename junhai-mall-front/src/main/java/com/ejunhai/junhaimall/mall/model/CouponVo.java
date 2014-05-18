package com.ejunhai.junhaimall.mall.model;



/**
 * CouponVo
 * 
 * @author 吴阳
 * @history 2012-07-21 吴阳 新建
 */
public class CouponVo{

	/** 券id */
	private String couponId;
	
	/** 券编码 */
	private String couponNumber;
	
	/** 面额 */
	private int parValue;
	
	/** 券内容 */
	private String remark;
	
	/** 状态 */
	private int state;
	
	/**
	 * 优惠券名称
	 */
	private String couponName;
	
	/** 使用的订单 */
	private String useOrderNumber;
	
	/**
	 * 开始时间
	 */
	private String useStartdate;

	/**
	 * 结束时间
	 */
	private String useEnddate;
	
	/**
	 * 有效剩余天数
	 */
	private int expiryDate;

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getCouponNumber() {
		return couponNumber;
	}

	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}

	public int getParValue() {
		return parValue;
	}

	public void setParValue(int parValue) {
		this.parValue = parValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUseStartdate() {
		return useStartdate;
	}

	public void setUseStartdate(String useStartdate) {
		this.useStartdate = useStartdate;
	}

	public String getUseEnddate() {
		return useEnddate;
	}

	public void setUseEnddate(String useEnddate) {
		this.useEnddate = useEnddate;
	}

	public int getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(int expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getUseOrderNumber() {
		return useOrderNumber;
	}

	public void setUseOrderNumber(String useOrderNumber) {
		this.useOrderNumber = useOrderNumber;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	@Override
	public String toString() {
		return "CouponVo [couponId=" + couponId + ", couponNumber="
				+ couponNumber + ", parValue=" + parValue + ", remark="
				+ remark + ", state=" + state + ", useOrderNumber="
				+ useOrderNumber + ", useStartdate=" + useStartdate
				+ ", useEnddate=" + useEnddate + ", expiryDate=" + expiryDate
				+ "]";
	}
}
