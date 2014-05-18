package com.ejunhai.junhaimall.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ejunhai.junhaimall.framework.base.BaseController;
import com.ejunhai.junhaimall.framework.base.PageFinder;
import com.ejunhai.junhaimall.framework.base.Query;
import com.ejunhai.junhaimall.system.model.Config;
import com.ejunhai.junhaimall.system.service.IConfigService;

/**
 * 配置项管理controller
 * 
 * @author 罗正加
 * @history 2011-12-16 罗正加 新建
 */
@Controller
@RequestMapping("config")
public class ConfigController extends BaseController {

	@Autowired
	private IConfigService configService;

	@RequestMapping("/configList")
	public String configList(Config config, Query query, ModelMap modelMap) throws Exception {
		int pageNo = query.getPage();
		int pageSize = query.getPageSize();
		int count = configService.queryConfigCount(config);

		List<Config> configList = new ArrayList<Config>(0);
		if (count > 0) {
			configList = configService.queryConfigList(config, pageNo, pageSize);
			PageFinder<Config> pageFinder = new PageFinder<Config>(pageNo, pageSize, count);
			pageFinder.setData(configList);
			modelMap.addAttribute("pageFinder", pageFinder);
		}

		modelMap.addAttribute("config", config);
		modelMap.addAttribute("configList", configList);
		return "system/configList";
	}

	@RequestMapping("/toConfig")
	public String toConfig(String configId, ModelMap modelMap) throws Exception {
		if (StringUtils.isNotBlank(configId)) {
			Config config = configService.readConfig(configId);
			modelMap.addAttribute("config", config);
		}
		return "system/editConfig";
	}

	@RequestMapping("/updateConfig")
	public ModelAndView updateConfig(Config config, ModelMap modelMap) throws Exception {
		if (StringUtils.isNotBlank(config.getId())) {
			configService.updateConfig(config);
		} else {
			configService.insertConfig(config);
		}
		return new ModelAndView(new RedirectView("/config/configList.sc", true));
	}

	@RequestMapping("/removeConfig")
	public ModelAndView removeConfig(String configId, ModelMap modelMap) throws Exception {
		this.configService.removeConfig(configId);
		return new ModelAndView(new RedirectView("/config/configList.sc", true));
	}

	@RequestMapping("/redirect")
	public String redirect(String url) {
		return url;
	}
}
