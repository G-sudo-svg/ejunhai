package com.ejunhai.junhaimall.filter;

import javax.servlet.http.HttpServletRequest;

import com.ejunhai.junhaimall.system.model.SystemMgtUser;

public class SystemSessionManager {
	
	
	public static void set(SystemMgtUser sessionMgtUser,HttpServletRequest request){
		request.getSession().setAttribute("SYSTEM_USER", sessionMgtUser);
	}
	
	public static SystemMgtUser get(HttpServletRequest request){
		return (SystemMgtUser)request.getSession().getAttribute("SYSTEM_USER");
	}
	
	public static void clear(HttpServletRequest request){
		request.getSession().removeAttribute("SYSTEM_USER");
	}

}
