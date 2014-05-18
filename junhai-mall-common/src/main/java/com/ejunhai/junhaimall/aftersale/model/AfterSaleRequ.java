package com.ejunhai.junhaimall.aftersale.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 罗正加
 * 
 * @date 2012-06-20 18:12:49
 */
public class AfterSaleRequ implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;

	/** */
	private String id;

	/**
	 * 图片1路径
	 */
	private String pic1Url;

	/**
	 * 图片2路径
	 */
	private String pic2Url;

	/**
	 * 图片3路径
	 */
	private String pic3Url;

	/**
	 * 申请描述
	 */
	private String description;

	/**
	 * 申请时间
	 */
	private Date createTime;

	/**
	 * 状态：1：未处理 2：已下补货单 3：已拒绝
	 */
	private int state;

	/**
	 * 处理时间
	 */
	private Date dealTime;

	/**
	 * 处理说明
	 */
	private String dealInfo;

	/**
	 * 主订单号
	 */
	private String orderMainNo;

	/**
	 * 优惠券名称
	 */
	private String couponName;

	/**
	 * 优惠券码
	 */
	private String couponNumber;

	/**
	 * 申请人
	 */
	private String consignee;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPic1Url() {
		return pic1Url;
	}

	public void setPic1Url(String pic1Url) {
		this.pic1Url = pic1Url;
	}

	public String getPic2Url() {
		return pic2Url;
	}

	public void setPic2Url(String pic2Url) {
		this.pic2Url = pic2Url;
	}

	public String getPic3Url() {
		return pic3Url;
	}

	public void setPic3Url(String pic3Url) {
		this.pic3Url = pic3Url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getDealInfo() {
		return dealInfo;
	}

	public void setDealInfo(String dealInfo) {
		this.dealInfo = dealInfo;
	}

	public String getOrderMainNo() {
		return orderMainNo;
	}

	public void setOrderMainNo(String orderMainNo) {
		this.orderMainNo = orderMainNo;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponNumber() {
		return couponNumber;
	}

	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

}
