package com.ejunhai.junhaimall.framework.html;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ejunhai.junhaimall.framework.util.SpringUtil;

/**
 * 工具类 - 模板配置 
 * @author ZhuangRuibo
 *
 */
public class TemplateConfigUtil {

    public static final String CONFIG_FILE_NAME = "template.xml"; // 模板配置文件名称
    


    /**
     * 根据生成静态模板配置名称获取HtmlConfig对象
     * 
     * @return HtmlConfig对象
     */
    public static HtmlConfig getHtmlConfig(String name) {
        Document document = null;
        try {
        	String configFilePath = SpringUtil.getServletContext().getRealPath("/") + "/template/cms/" + CONFIG_FILE_NAME;
            File configFile = new File(configFilePath);
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Element element = (Element) document.selectSingleNode("yougou/htmlConfig/" + name);
        if(element == null){
        	return null;
        }
        String description = element.element("description").getTextTrim();
        String templateFilePath = element.element("templateFilePath").getTextTrim();
        String htmlFilePath = element.element("htmlFilePath").getTextTrim();
        HtmlConfig htmlConfig = new HtmlConfig();
        htmlConfig.setName(element.getName());
        htmlConfig.setDescription(description);
        htmlConfig.setTemplateFilePath(templateFilePath);
        htmlConfig.setHtmlFilePath(htmlFilePath);
        return htmlConfig;
    }

}
