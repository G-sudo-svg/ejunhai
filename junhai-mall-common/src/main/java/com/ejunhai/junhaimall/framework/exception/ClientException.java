package com.ejunhai.junhaimall.framework.exception;

/**
 * 手机客户端异常类
 * 
 * @author 罗正加
 * @history 2011-12-22 罗正加 新建
 */
public class ClientException extends Exception {
	/***/
	private static final long serialVersionUID = 1L;

	public Exception ex = null;

	public String className = "";

	public ClientException() {
	}

	public ClientException(String message) {
		super(message);
	}

	public ClientException(Exception ex) {
		super(ex);
		this.ex = ex;
	}

	public ClientException(Exception ex, String className) {
		super(ex);
		this.ex = ex;
	}
}
