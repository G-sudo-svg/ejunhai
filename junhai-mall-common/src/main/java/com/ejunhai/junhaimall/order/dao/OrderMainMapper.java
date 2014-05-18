package com.ejunhai.junhaimall.order.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.ejunhai.junhaimall.order.model.OrderMain;

/**
 * 订单
 * 
 * @author 罗正加
 * @history 2011-12-12 罗正加 新建
 */
public interface OrderMainMapper {
	/**
	 * 获取订单列表
	 * 
	 * @param OrderMain
	 * @param rowBounds
	 * @return
	 */
	public List<OrderMain> queryOrderMainList(OrderMain orderMain, RowBounds rowBounds);

	/**
	 * 获取订单数量
	 * 
	 * @param OrderMain
	 * @return
	 */
	public Integer queryOrderMainCount(OrderMain orderMain);

	/**
	 * 读取订单
	 * 
	 * @param OrderMainId
	 * @return
	 */
	public OrderMain readOrderMain(String orderMainId);

	/**
	 * 根据订单号获取订单
	 * 
	 * @param orderMainNo
	 * @return
	 */
	public OrderMain getOrdermainByOrderMainNo(String orderMainNo);

	/**
	 * 新增订单
	 * 
	 * @param OrderMain
	 * @return
	 */
	public int insertOrderMain(OrderMain orderMain);

	/**
	 * 更新订单
	 * 
	 * @param OrderMain
	 */
	public void updateOrderMain(OrderMain orderMain);

	/**
	 * 删除订单
	 * 
	 * @param OrderMainId
	 */
	public void removeOrderMain(String orderMainId);
	
	/**
	 * 打印快递单
	 * 
	 * @param OrderMainId
	 */
	public void printExpress(String orderMainId);
	
	
}