package com.ejunhai.junhaimall.framework.html;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

/**
 * 网站静态化接口
 * @author	ZhuangRuibo
 * @history	2011-12-20
 */
@Component
public interface IBuildStaticHtml {


    /**
     * 根据Freemarker模板文件路径、Map数据生成HTML静态文件
     *
     * @param templateFilePath Freemarker模板文件路径
     * @param htmlFilePath 生成HTML静态文件存放路径
     * @param data Map数据
     *
     */
    public void buildHtml(String templateFilePath, String htmlFilePath, ModelMap data);
    /**
     * 生成HTML静态文件
     *
     */
    public void staticBuildHtml(ModelMap data, String uuid);
}
