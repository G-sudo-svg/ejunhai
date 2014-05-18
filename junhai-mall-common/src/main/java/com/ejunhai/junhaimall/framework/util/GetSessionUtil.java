package com.ejunhai.junhaimall.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取保存在Session的所有信息
 * 
 * @author wang.M
 */
public final class GetSessionUtil {

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	// 判断浏览器类型
	public static Integer getBrowingType(HttpServletRequest request) {
		String agent = request.getHeader("USER-AGENT");
		if (null != agent && -1 != agent.indexOf("MSIE")) {
			return 1;
		} else if (null != agent && -1 != agent.indexOf("Firefox")) {
			return 2;
		} else if (null != agent && -1 != agent.indexOf("Safari")) {
			return 3;
		} else {
			return 4;
		}
	}

	// 获取来源头信息，获取域名
	public static String getDomain(HttpServletRequest request) {
		String domain = null;

		String domainreferer = request.getHeader("referer"); // request.getRequestURL().toString();
																// //获取头信息（原请求的URL)
		if (domainreferer != null) {
			String replaceReferer = domainreferer.replace("http://", "");
			if (domainreferer != null && replaceReferer.indexOf("/") > 0) {
				domain = replaceReferer.substring(0, replaceReferer.indexOf("/"));// 获取原URL域名
			} else {
				domain = replaceReferer.substring(0, replaceReferer.length());
			}

		}
		return domain;
	}

	/**
	 * 获取服务器路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getServiceName(HttpServletRequest request) {
		String path = request.getContextPath();
		String servicePath = request.getServerName() + ":" + String.valueOf(request.getServerPort());
		return "http://" + servicePath + path + "/";
	}

	/**
	 * 封装转码方法
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String getEncoding(String EncondingName) throws UnsupportedEncodingException {
		return URLEncoder.encode(URLEncoder.encode(EncondingName, "UTF-8"), "UTF-8");
	}

}
