package com.ejunhai.junhaimall.framework.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 系统默认异常处理类
 * 
 * @author 罗正加
 * @history 2011-12-22 罗正加 新建
 */
public class DefaultHandlerExceptionResolver extends SimpleMappingExceptionResolver {

	/** Logger available to subclasses */
	protected final Logger logger = Logger.getLogger(this.getClass().getName());

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		if (shouldApplyTo(request, handler)) {
			request.setAttribute("errorDesc", ex);
			StackTraceElement[] stacks = ex.getStackTrace();
			for (int i = 0; i < stacks.length; i++) {
				String exceptionPath = ex.getStackTrace()[i].getClassName();
				if (StringUtils.isNotBlank(exceptionPath) && exceptionPath.indexOf(".client.") >= 0) {
					// 客户端异常处理
					request.setAttribute("errorDesc", ex.getMessage());
					ex = new ClientException(ex);
					break;
				}
			}
			return doResolveException(request, response, handler, ex);
		}
		return null;
	}
}
