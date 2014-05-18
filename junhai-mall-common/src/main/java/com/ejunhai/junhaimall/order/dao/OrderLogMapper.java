package com.ejunhai.junhaimall.order.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.ejunhai.junhaimall.order.model.OrderLog;


/**
 * 订单日志
 * 
 * @author 罗正加
 * @history 2012-06-24 罗正加 新建
 */
public interface OrderLogMapper {

	/**
	 * 获取订单日志列表
	 * 
	 * @param OrderLog
	 * @param rowBounds
	 * @return
	 */
	public List<OrderLog> queryOrderLogList(OrderLog orderLog, RowBounds rowBounds);

	/**
	 * 获取订单日志数量
	 * 
	 * @param OrderLog
	 * @return
	 */
	public Integer queryOrderLogCount(OrderLog orderLog);

	/**
	 * 新增订单日志
	 * 
	 * @param OrderLog
	 * @return
	 */
	public int insertOrderLog(OrderLog orderLog);

	/**
	 * 删除订单日志
	 * 
	 * @param OrderLogId
	 */
	public void removeOrderLog(String orderLogId);

}