package com.ejunhai.junhaimall.order.model;

import java.io.Serializable;


/**
 * 订单物流跟踪
 * @author wuyang
 *
 */
public class OrderLogisticsTrack implements Serializable {

	/***/
	private static final long serialVersionUID = 1L;

	/** 查询结果状态，0|1|2|3|4，0表示查询失败，1正常，2派送中，3已签收，4退回； */
	private int status;
	
	/** 错误代码，0无错误，1单号不存在，2验证码错误，3链接查询服务器失败，4程序内部错误，5程序执行错误，6快递单号格式错误，7快递公司错误，10未知错误。 */
	private int errCode;
	
	/** 错误消息 */
	private String message;
	
	/** 其他HTML */
	private String html;
	
	/** 快递单号 */
	private String mailNo;
	
	/** 快递公司英文代码 */
	private String expSpellName;
	
	/** 快递公司中文名 */
	private String expTextName;
	
	/** 跟踪进度 */
	private LogisticsTrackItem[] data;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getMailNo() {
		return mailNo;
	}

	public void setMailNo(String mailNo) {
		this.mailNo = mailNo;
	}

	public String getExpSpellName() {
		return expSpellName;
	}

	public void setExpSpellName(String expSpellName) {
		this.expSpellName = expSpellName;
	}

	public String getExpTextName() {
		return expTextName;
	}

	public void setExpTextName(String expTextName) {
		this.expTextName = expTextName;
	}

	public LogisticsTrackItem[] getData() {
		return data;
	}

	public void setData(LogisticsTrackItem[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "OrderLogisticsTrack [status=" + status + ", errCode=" + errCode
				+ ", message=" + message + ", html=" + html + ", mailNo="
				+ mailNo + ", expSpellName=" + expSpellName + ", expTextName="
				+ expTextName + ", data=" + data
				+ "]";
	}
}
