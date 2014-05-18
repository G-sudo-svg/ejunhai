package com.ejunhai.junhaimall.aftersale.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 罗正加
 * 
 * @date 2012-06-20 17:28:20
 */
public class AfterSaleCons implements Serializable {

	/***/
	private static final long serialVersionUID = 1L;

	/** */
	private String id;

	/**
	 * 咨询内容
	 */
	private String consultation;

	/**
	 * 咨询时间
	 */
	private Date createTime;

	/**
	 * 状态
	 */
	private int state;

	/**
	 * 回复内容
	 */
	private String reply;

	/**
	 * 回复时间
	 */
	private Date replyTime;

	/**
	 * 主订单号
	 */
	private String orderMainNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConsultation() {
		return consultation;
	}

	public void setConsultation(String consultation) {
		this.consultation = consultation;
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

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public String getOrderMainNo() {
		return orderMainNo;
	}

	public void setOrderMainNo(String orderMainNo) {
		this.orderMainNo = orderMainNo;
	}

}
