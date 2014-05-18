package com.ejunhai.junhaimall.aftersale.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejunhai.junhaimall.aftersale.dao.AfterSaleConsMapper;
import com.ejunhai.junhaimall.aftersale.model.AfterSaleCons;
import com.ejunhai.junhaimall.aftersale.service.IAfterSaleConsService;
import com.ejunhai.junhaimall.framework.base.KeyGenerator;
import com.ejunhai.junhaimall.framework.core.BaseSpringService;

@Service
public class AfterSaleConsServiceImpl extends BaseSpringService implements IAfterSaleConsService{

	@Autowired
	private AfterSaleConsMapper afterSaleConsMapper;
	
	public AfterSaleCons readAfterSaleCons(String id){
		return this.afterSaleConsMapper.readAfterSaleCons(id);
	}
	
	@Override
	public void insertAfterSaleCons(AfterSaleCons afterSaleCons) {
		afterSaleCons.setId(KeyGenerator.getUUID());
		afterSaleCons.setCreateTime(new Date(System.currentTimeMillis()));
		afterSaleCons.setState(1);
		this.afterSaleConsMapper.insertAfterSaleCons(afterSaleCons);
	}

	@Override
	public List<AfterSaleCons> queryAfterSaleConsList(
			AfterSaleCons afterSaleCons, int pageNo, int pageSize) {
		RowBounds rowBounds = new RowBounds((pageNo - 1) * pageSize, pageSize - 1);
		return this.afterSaleConsMapper.queryAfterSaleConsList(afterSaleCons, rowBounds);
	}

	@Override
	public Integer queryAfterSaleConsCount(AfterSaleCons afterSaleCons) {
		return this.afterSaleConsMapper.queryAfterSaleConsCount(afterSaleCons);
	}
	
	@Override
	public void doAfterSaleConsReply(AfterSaleCons afterSaleCons){
		this.afterSaleConsMapper.doAfterSaleConsReply(afterSaleCons);
	}
}
