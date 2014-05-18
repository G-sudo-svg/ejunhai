package com.ejunhai.junhaimall.coupon.model;

public class CouponUseDataVo {
	
	public String couponSchemeId;
	
	public String useDate;
	
	public Integer useCount;
	
	public Integer sumCount;
	
	public String getCouponSchemeId() {
		return couponSchemeId;
	}

	public void setCouponSchemeId(String couponSchemeId) {
		this.couponSchemeId = couponSchemeId;
	}

	public String getUseDate() {
		return useDate;
	}

	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}

	public Integer getSumCount() {
		return sumCount;
	}

	public void setSumCount(Integer sumCount) {
		this.sumCount = sumCount;
	}
}
