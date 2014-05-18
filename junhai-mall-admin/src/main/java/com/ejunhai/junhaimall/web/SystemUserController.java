package com.ejunhai.junhaimall.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ejunhai.junhaimall.filter.SystemSessionManager;
import com.ejunhai.junhaimall.framework.constant.Constant;
import com.ejunhai.junhaimall.system.model.Config;
import com.ejunhai.junhaimall.system.model.SystemMgtUser;
import com.ejunhai.junhaimall.system.service.IConfigService;

@Controller
@RequestMapping("")
public class SystemUserController {
	@Autowired
	private IConfigService configService;

	@RequestMapping("toLoginSystem")
	public String toLogin(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping("toForbidden")
	public ModelAndView toForbidden(HttpServletRequest request) {
		return new ModelAndView(new RedirectView("403.html"));
	}

	@RequestMapping("loginSystem")
	public String login(String username, String password, String validateCode, HttpServletRequest request) {
		String sValidateCode = (String) request.getSession().getAttribute(Constant.LOGIN_VALIDATE_IMAGE);
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return "login";
		}

		Config config = configService.getConfigByKey(username);
		if (config == null) {
			return "login";
		}

		if (validateCode.equals(sValidateCode) && password.equals(config.getConfigValue())) {
			String role = "ejunhai".equals(username) ? "superadmin" : "admin";
			SystemMgtUser sessionMgtUser = new SystemMgtUser(username, password, role);
			sessionMgtUser.setUsername(username);
			SystemSessionManager.set(sessionMgtUser, request);
			return "ejunhai".equals(username) ? "index_admin" : "index";
		}

		return "login";
	}

	@RequestMapping("/systemError")
	public ModelAndView error(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("errorDesc", request.getAttribute("errorDesc"));
		return new ModelAndView("error", modelMap);
	}

	/**
	 * 注销
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		SystemSessionManager.clear(request);
		return "login";
	}
}
