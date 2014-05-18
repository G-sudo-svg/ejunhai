package com.ejunhai.junhaimall.system.model;

import java.io.Serializable;

/**
 * @author 罗正加
 * 
 * @date 2012-06-20 23:22:09
 */
public class Config implements Serializable {

	/***/
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	private String id;

	/**
	 * 配置项名称
	 */
	private String configName;

	/**
	 * 配置项key
	 */
	private String configKey;

	/**
	 * 配置项值
	 */
	private String configValue;

	/**
	 * 备注
	 */
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
