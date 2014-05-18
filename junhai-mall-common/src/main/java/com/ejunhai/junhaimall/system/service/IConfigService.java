package com.ejunhai.junhaimall.system.service;

import java.util.List;

import com.ejunhai.junhaimall.system.model.Config;

/**
 * 系统配置service
 * 
 * @author 罗正加
 * @history 2011-12-12 罗正加 新建
 */
public interface IConfigService {

	/**
	 * 获取系统配置列表
	 * 
	 * @param config
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Config> queryConfigList(Config config, int pageNo, int pageSize);

	/**
	 * 获取配置项数量
	 * 
	 * @param config
	 * @return
	 */
	public int queryConfigCount(Config config);

	/**
	 * 读取配置项
	 * 
	 * @param configId
	 * @return
	 */
	public Config readConfig(String configId);

	/**
	 * 读取配置项
	 * 
	 * @param configKey
	 * @return
	 */
	public Config getConfigByKey(String configKey);

	/**
	 * 插入配置项
	 * 
	 * @param config
	 * @return
	 */
	public void insertConfig(Config config);

	/**
	 * 更新配置项
	 * 
	 * @param config
	 * @return
	 */
	public void updateConfig(Config config);

	/**
	 * 删除配置项
	 * 
	 * @param configId
	 */
	public void removeConfig(String configId);

}
