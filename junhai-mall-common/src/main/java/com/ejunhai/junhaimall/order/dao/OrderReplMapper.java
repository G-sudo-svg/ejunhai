package com.ejunhai.junhaimall.order.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.ejunhai.junhaimall.order.model.OrderRepl;

/**
 * 补货单
 * 
 * @author 罗正加
 * @history 2012-06-24 罗正加 新建
 */
public interface OrderReplMapper {
	/**
	 * 获取补货单列表
	 * 
	 * @param OrderRepl
	 * @param rowBounds
	 * @return
	 */
	public List<OrderRepl> queryOrderReplList(OrderRepl orderRepl, RowBounds rowBounds);

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
	 * @param OrderRepl
	 * @return
	 */
	public int insertOrderRepl(OrderRepl orderRepl);

	/**
	 * 更新补货单
	 * 
	 * @param OrderRepl
	 */
	public void updateOrderRepl(OrderRepl orderRepl);

	/**
	 * 删除补货单
	 * 
	 * @param OrderReplId
	 */
	public void removeOrderRepl(String orderReplId);

	/**
	 * 打印快递单
	 * 
	 * @param orderReplIds
	 */
	public void printExpress(String orderReplIds);
}