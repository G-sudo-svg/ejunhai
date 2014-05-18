package com.ejunhai.junhaimall.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejunhai.junhaimall.framework.core.BaseSpringService;
import com.ejunhai.junhaimall.system.dao.AreaMapper;
import com.ejunhai.junhaimall.system.model.Area;
import com.ejunhai.junhaimall.system.service.IAreaService;

@Service
public class AreaServiceImpl extends BaseSpringService implements IAreaService{

	@Autowired
	private AreaMapper areaMapper;
	
	@Override
	public Area getAreaByNo(String no) {
		return areaMapper.getAreaByNo(no);
	}

}
