package com.ejunhai.junhaimall.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 罗正加
 * 
 * @date 2012-06-20 18:14:42
 */
public class OrderLog implements Serializable {

	/***/
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	private String id;

	/**
     * 
     */
	private String behavioutDescribe;

	/**
     * 
     */
	private Date createTime;

	/**
     * 
     */
	private Integer logType;

	/**
     * 
     */
	private String operateUser;

	/**
     * 
     */
	private String orderNo;

	/**
     * 
     */
	private String remark;

	/**
	 * 前台是否显示，0-不显示，1-显示
	 */
	private String showFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBehavioutDescribe() {
		return behavioutDescribe;
	}

	public void setBehavioutDescribe(String behavioutDescribe) {
		this.behavioutDescribe = behavioutDescribe;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public String getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

}
