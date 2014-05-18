package com.ejunhai.junhaimall.system.model;

import java.io.Serializable;

/**
 * @author 罗正加
 * 
 * @date 2012-06-20 23:22:35
 */
public class Sms implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	private String id;

	/**
     * 
     */
	private String telphone;

	/**
     * 
     */
	private String content;

	/**
     * 
     */
	private String sendTime;

	/**
     * 
     */
	private String type;

	/**
     * 
     */
	private String state;

	/**
     * 
     */
	private String count;

	/**
     * 
     */
	private String createTime;

	/**
     * 
     */
	private Long sendNow;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getSendNow() {
		return sendNow;
	}

	public void setSendNow(Long sendNow) {
		this.sendNow = sendNow;
	}

}
