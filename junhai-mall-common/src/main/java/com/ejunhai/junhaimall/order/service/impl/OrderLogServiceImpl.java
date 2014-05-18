package com.ejunhai.junhaimall.order.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejunhai.junhaimall.framework.base.KeyGenerator;
import com.ejunhai.junhaimall.framework.core.BaseSpringService;
import com.ejunhai.junhaimall.order.dao.OrderLogMapper;
import com.ejunhai.junhaimall.order.model.OrderLog;
import com.ejunhai.junhaimall.order.service.IOrderLogService;

/**
 * 订单日志service
 * 
 * @author 罗正加
 * @history 2011-12-12 罗正加 新建
 */
@Service
public class OrderLogServiceImpl extends BaseSpringService implements IOrderLogService {
	@Resource
	private OrderLogMapper orderLogMapper;

	@Override
	public List<OrderLog> queryOrderLogList(OrderLog orderLog, int pageNo, int pageSize) {
		RowBounds rowBounds = new RowBounds((pageNo - 1) * pageSize, pageSize - 1);
		return orderLogMapper.queryOrderLogList(orderLog, rowBounds);
	}

	@Override
	public Integer queryOrderLogCount(OrderLog orderLog) {
		return orderLogMapper.queryOrderLogCount(orderLog);
	}

	@Override
	@Transactional
	public int insertOrderLog(OrderLog orderLog) {
		orderLog.setId(KeyGenerator.getUUID());
		orderLog.setCreateTime(new Date());
		orderLog.setOperateUser("system");
		orderLog.setShowFlag("1");
		return orderLogMapper.insertOrderLog(orderLog);
	}

	@Override
	public void removeOrderLog(String orderLogId) {
		orderLogMapper.removeOrderLog(orderLogId);
	}
}
