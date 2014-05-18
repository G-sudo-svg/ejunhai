package com.ejunhai.junhaimall.order.model;

import java.io.Serializable;

/**
 * 跟踪进度项
 * @author wuyang
 *
 */
public class LogisticsTrackItem implements Serializable{
	/***/
	private static final long serialVersionUID = 1L;
	
	/** 时间 */
	private String time;
	
	/** 内容 */
	private String context;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
}
