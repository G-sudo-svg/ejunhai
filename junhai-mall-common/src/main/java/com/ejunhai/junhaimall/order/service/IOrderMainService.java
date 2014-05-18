package com.ejunhai.junhaimall.order.service;

import java.util.List;

import com.ejunhai.junhaimall.coupon.model.Coupon;
import com.ejunhai.junhaimall.order.model.OrderMain;

/**
 * 订单服务接口
 * 
 * @author 罗正加
 * @history 2011-12-12 罗正加 新建
 */
public interface IOrderMainService {

	/**
	 * 获取订单列表
	 * 
	 * @param orderMain
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<OrderMain> queryOrderMainList(OrderMain orderMain, int pageNo, int pageSize);

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
	 * 创建订单
	 * 
	 * @param coupon
	 * @param orderMain
	 * @return
	 * @throws Exception
	 */
	public OrderMain createOrderMain(Coupon coupon, OrderMain orderMain) throws Exception;

	/**
	 * 更新收货地址
	 * 
	 * @param orderMain
	 */
	public void changeConsigneeInfo(OrderMain orderMain);

	/**
	 * 发货
	 * 
	 * @param orderMain
	 * @return
	 */
	public void deliverOrderMain(OrderMain orderMain);

	/**
	 * 删除订单
	 * 
	 * @param OrderMainId
	 */
	public void removeOrderMain(String orderMainId);

	/**
	 * 打印快递单
	 * 
	 * @param orderMainIds
	 */
	public void printExpress(String orderMainIds);

}
