package com.ejunhai.junhaimall.system.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.ejunhai.junhaimall.system.model.Config;

/**
 * 系统配置项
 * 
 * @author 罗正加
 * @create 2011-12-15 下午3:38:47
 * @history
 */
public interface ConfigMapper {

	/**
	 * 获取系统配置列表
	 * 
	 * @param config
	 * @param rowBounds
	 * @return
	 */
	public List<Config> queryConfigList(Config config, RowBounds rowBounds);

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