package com.ejunhai.junhaimall.aftersale.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejunhai.junhaimall.aftersale.constant.AfterSaleConstant;
import com.ejunhai.junhaimall.aftersale.dao.AfterSaleRequMapper;
import com.ejunhai.junhaimall.aftersale.model.AfterSaleRequ;
import com.ejunhai.junhaimall.aftersale.service.IAfterSaleRequService;
import com.ejunhai.junhaimall.framework.base.KeyGenerator;
import com.ejunhai.junhaimall.framework.core.BaseSpringService;

@Service
public class AfterSaleRequServiceImpl extends BaseSpringService implements IAfterSaleRequService {

	@Autowired
	private AfterSaleRequMapper afterSaleRequMapper;

	public AfterSaleRequ readAfterSaleRequ(String id) {
		return this.afterSaleRequMapper.readAfterSaleRequ(id);
	}

	@Override
	public AfterSaleRequ getAfterSaleRequByOrderMainNo(String orderMainNo) {
		return afterSaleRequMapper.getAfterSaleRequByOrderMainNo(orderMainNo);
	}

	@Override
	public void insertAfterSaleRequ(AfterSaleRequ afterSaleRequ) {
		afterSaleRequ.setId(KeyGenerator.getUUID());
		afterSaleRequ.setCreateTime(new Date(System.currentTimeMillis()));
		afterSaleRequ.setState(AfterSaleConstant.AFTER_SALE_REQU_WAIT_DEAL);
		this.afterSaleRequMapper.insertAfterSaleRequ(afterSaleRequ);
	}

	@Override
	public void updateAfterSaleRequ(AfterSaleRequ afterSaleRequ) {
		this.afterSaleRequMapper.updateAfterSaleRequ(afterSaleRequ);
	}

	@Override
	public List<AfterSaleRequ> queryAfterSaleRequList(AfterSaleRequ afterSaleRequ, int pageNo, int pageSize) {
		RowBounds rowBounds = new RowBounds((pageNo - 1) * pageSize, pageSize - 1);
		return this.afterSaleRequMapper.queryAfterSaleRequList(afterSaleRequ, rowBounds);
	}

	@Override
	public Integer queryAfterSaleRequCount(AfterSaleRequ afterSaleRequ) {
		return this.afterSaleRequMapper.queryAfterSaleRequCount(afterSaleRequ);
	}

}
