package com.ejunhai.junhaimall.framework.html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.ejunhai.junhaimall.framework.util.DateUtil;
import com.ejunhai.junhaimall.framework.util.SpringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 网站静态化接口
 * @author	ZhuangRuibo
 * @history	2011-12-20
 */
@Component
public class BuildStaticHtml implements IBuildStaticHtml {

    /**
     * 根据Freemarker模板文件路径、Map数据生成HTML静态文件
     *
     * @param templateFilePath Freemarker模板文件路径
     * @param htmlFilePath 生成HTML静态文件存放路径
     * @param data Map数据
     *
     */
    public void buildHtml(String templateFilePath, String htmlFilePath, ModelMap data) {
    	Writer out = null;
		try {
			// url路径
			data.put("createTime", DateUtil.getDateTime(new Date()));
			// 模板文件配置
			Configuration configuration = SpringUtil.getConfiguration();
			// 模板文件
			Template template = configuration.getTemplate(templateFilePath, "UTF8");
			// html输出路径
			File htmlFile = new File(SpringUtil.getServletContextPath()
					+ htmlFilePath);
			// 文件夹不存在就创建
			File htmlDirectory = htmlFile.getParentFile();
			if (!htmlDirectory.exists()) {
				htmlDirectory.mkdirs();
			}
			// 写对象
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
			// 数据写入模板操作
			template.process(data, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(out != null){
					out.flush();// 刷新流
					out.close();// 关闭流
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

    }

    /**
     * 生成HTML静态文件
     *
     */
    public void staticBuildHtml(ModelMap data, String uuid) {
        commonBuildHtml(data, uuid, HtmlConfig.HELP);
    }
    /**
     * 基础构建
     * 
     * @param data               数据
     * @param uuid               主键
     * @param templatePath       模板路径
     */
    public void commonBuildHtml(ModelMap data, String uuid, String templatePath) {
        HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(templatePath);
        if(htmlConfig == null){
        	return;
        }
        String htmlFilePath = replaceHtmlName(htmlConfig.getHtmlFilePath(), uuid);
        String templateFilePath = htmlConfig.getTemplateFilePath();
        buildHtml(templateFilePath, htmlFilePath, data);
    }
    /**
     * 防止重复生成Html
     * @param htmlFilePath
     * @param uuid
     * @return
     */
    public String replaceHtmlName(String htmlFilePath, String uuid) {
        StringBuffer buffer = new StringBuffer();
        int beginIndex = htmlFilePath.indexOf("/");
        int endIndex = htmlFilePath.lastIndexOf("/");
        buffer.append(htmlFilePath.substring(beginIndex, endIndex + 1));
        buffer.append(uuid).append(".html");
        return buffer.toString();
    }
}
