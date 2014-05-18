package com.ejunhai.junhaimall.framework.exception;

/**
 * 未登陆异常类
 * 
 * @author 罗正加
 * @history 2012-04-28 罗正加 新建
 */
public class NoLoginException extends Exception {
	/***/
	private static final long serialVersionUID = 1L;

	public Exception ex = null;

	public String className = "";

	public NoLoginException() {
	}

	public NoLoginException(String message) {
		super(message);
	}

	public NoLoginException(Exception ex) {
		super(ex);
		this.ex = ex;
	}

	public NoLoginException(Exception ex, String className) {
		super(ex);
		this.ex = ex;
	}
}
