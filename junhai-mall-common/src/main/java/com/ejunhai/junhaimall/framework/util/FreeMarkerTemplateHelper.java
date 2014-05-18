package com.ejunhai.junhaimall.framework.util;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

/**
 * 模板转换数据帮助类
 * 
 * @author 罗正加
 * @history 2011-12-10 罗正加 新建
 */

public class FreeMarkerTemplateHelper {

	/**
	 * logger
	 */
	private static final Logger logger = Logger.getLogger(FreeMarkerTemplateHelper.class);

	/**
	 * 错误信息模板URL
	 */
	private static String errorTemplateURL = "template/common/error.ftl";

	/**
	 * 提示信息模板URL
	 */
	private static String infoTemplateURL = "template/common/info.ftl";

	/**
	 * @param templateURL
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	public static String parseTemplateToJson(String templateURL, Object modelMap) throws Exception {
		FreeMarkerConfigurer freeMarkerConfigurer = ServiceLocator.getBean("freemarkerConfig");
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateURL);
		String returnStr = FreeMarkerTemplateUtils.processTemplateIntoString(template, modelMap);
		returnStr = returnStr.replaceAll("\\s", " ");
		if (logger.isInfoEnabled()) {
			logger.info("转换JSON数据成功：" + returnStr);
		}
		return returnStr;
	}

	/**
	 * 错误模板
	 * 
	 * @param modelMap
	 * @return
	 */
	public static String parseErrorTemplateToJson(Object modelMap) {
		String htmlText = null;
		Object errorDesc = ((HashMap<?, ?>) modelMap).get("errorDesc");
		if (errorDesc != null) {
			logger.error(errorDesc);
		}

		try {
			htmlText = parseTemplateToJson(errorTemplateURL, modelMap);
			htmlText = htmlText.replaceAll("\\s", " ");
		} catch (Exception ex) {
			htmlText = "{\"response\":\"error\",\"error\":{\"text\":\"系统繁忙，请稍后再试！\"}}";
		}
		if (logger.isInfoEnabled()) {
			logger.info("转换异常JSON数据成功：" + htmlText);
		}
		return htmlText;
	}

	/**
	 * 信息提示模板
	 * 
	 * @param modelMap
	 * @return
	 */
	public static String parseInfoTemplateToJson(Object modelMap) {
		String htmlText = null;
		try {
			htmlText = parseTemplateToJson(errorTemplateURL, modelMap);
			htmlText = htmlText.replaceAll("\\s", " ");
		} catch (Exception ex) {
			htmlText = "{\"response\":\"error\",\"error\":{\"text\":\"系统繁忙，请稍后再试！\"}}";
		}
		if (logger.isInfoEnabled()) {
			logger.info("转换异常JSON数据成功：" + htmlText);
		}
		return htmlText;
	}
}
