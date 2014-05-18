package com.ejunhai.junhaimall.framework.html;

/**
 * 静态生成模板配置
 * @author ZhuangRuibo
 *
 */
public class HtmlConfig {

    public static HtmlConfig htmlConfig = null;

	public static final String HELP = "Help";// 帮助页

	private String name;// 配置名称
	private String description;// 描述
	private String templateFilePath;// Freemarker模板文件路径
	private String htmlFilePath;// 生成HTML静态文件存放路径

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTemplateFilePath() {
		return templateFilePath;
	}

	public void setTemplateFilePath(String templateFilePath) {
		this.templateFilePath = templateFilePath;
	}

	public String getHtmlFilePath() {
		return htmlFilePath;
	}
	public void setHtmlFilePath(String htmlFilePath) {
		this.htmlFilePath = htmlFilePath;
	}

    /**
     * 单例
     * @return
     */
    public synchronized static HtmlConfig getInstance(){
        if(null == htmlConfig){
            htmlConfig = new HtmlConfig();
        }
        return htmlConfig;

    }
    /**
     * 防止重复生成Html
     * @param htmlFilePath
     * @param uuid
     * @return
     */
    public String getHtmlFilePath(String htmlFilePath, String uuid) {
        StringBuffer buffer = new StringBuffer();
        int beginIndex = htmlFilePath.indexOf("/");
        int endIndex = htmlFilePath.lastIndexOf("/");
        buffer.append(htmlFilePath.substring(beginIndex, endIndex + 1));
        buffer.append(uuid).append(".html");
        return buffer.toString();
    }
}
