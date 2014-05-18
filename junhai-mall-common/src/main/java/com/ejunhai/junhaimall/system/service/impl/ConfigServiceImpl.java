package com.ejunhai.junhaimall.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejunhai.junhaimall.framework.base.KeyGenerator;
import com.ejunhai.junhaimall.framework.core.BaseSpringService;
import com.ejunhai.junhaimall.system.dao.ConfigMapper;
import com.ejunhai.junhaimall.system.model.Config;
import com.ejunhai.junhaimall.system.service.IConfigService;

/**
 * 系统配置service
 * 
 * @author 罗正加
 * @history 2011-12-12 罗正加 新建
 */
@Service
public class ConfigServiceImpl extends BaseSpringService implements IConfigService {
	@Resource
	private ConfigMapper configMapper;

	@Override
	public List<Config> queryConfigList(Config config, int pageNo, int pageSize) {
		RowBounds rowBounds = new RowBounds((pageNo - 1) * pageSize, pageSize - 1);
		return configMapper.queryConfigList(config, rowBounds);
	}

	@Override
	public int queryConfigCount(Config config) {
		return configMapper.queryConfigCount(config);
	}

	@Override
	public Config readConfig(String configId) {
		return configMapper.readConfig(configId);
	}

	@Override
	public Config getConfigByKey(String configKey) {
		return configMapper.getConfigByKey(configKey);
	}

	@Override
	@Transactional
	public void insertConfig(Config config) {
		config.setId(KeyGenerator.getUUID());
		configMapper.insertConfig(config);
	}

	@Override
	@Transactional
	public void updateConfig(Config config) {
		configMapper.updateConfig(config);
	}

	@Override
	@Transactional
	public void removeConfig(String configId) {
		configMapper.removeConfig(configId);
	}

}
