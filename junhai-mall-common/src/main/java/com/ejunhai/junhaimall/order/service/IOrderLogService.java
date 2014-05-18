package com.ejunhai.junhaimall.order.service;

import java.util.List;

import com.ejunhai.junhaimall.order.model.OrderLog;

/**
 * 订单日志服务接口
 * 
 * @author 罗正加
 * @history 2011-12-12 罗正加 新建
 */
public interface IOrderLogService {

	/**
	 * 获取订单日志列表
	 * 
	 * @param orderLog
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<OrderLog> queryOrderLogList(OrderLog orderLog, int pageNo, int pageSize);

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
