package com.ejunhai.junhaimall.order.service;

import java.util.List;

import com.ejunhai.junhaimall.order.model.OrderMain;
import com.ejunhai.junhaimall.order.model.OrderRepl;

/**
 * 补货单服务接口
 * 
 * @author 罗正加
 * @history 2011-12-12 罗正加 新建
 */
public interface IOrderReplService {

	/**
	 * 获取补货单列表
	 * 
	 * @param OrderRepl
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<OrderRepl> queryOrderReplList(OrderRepl orderRepl, int pageNo, int pageSize);

	/**
	 * 获取补货单数量
	 * 
	 * @param OrderRepl
	 * @return
	 */
	public Integer queryOrderReplCount(OrderRepl orderRepl);

	/**
	 * 读取补货单
	 * 
	 * @param OrderReplId
	 * @return
	 */
	public OrderRepl readOrderRepl(String orderReplId);

	/**
	 * 根据补货单号获取补货单
	 * 
	 * @param orderReplNo
	 * @return
	 */
	public OrderRepl getOrderReplByOrderReplNo(String orderReplNo);

	/**
	 * 新增补货单
	 * 
	 * @param orderMain
	 * @return
	 */
	public OrderRepl createOrderRepl(OrderMain orderMain, OrderRepl orderRepl);

	/**
	 * 更改收货人地址
	 * 
	 * @param orderRepl
	 */
	public void changeConsigneeInfo(OrderRepl orderRepl);

	/**
	 * 补货单发货
	 * 
	 * @param orderMain
	 */
	public void deliverOrderRepl(OrderRepl orderRepl);

	/**
	 * 删除补货单
	 * 
	 * @param OrderReplId
	 */
	public void removeOrderRepl(String orderReplId);

	/**
	 * 打印补货单
	 * 
	 * @param orderReplIds
	 */
	public void printExpress(String orderReplIds);
}
