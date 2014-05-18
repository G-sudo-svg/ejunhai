package com.ejunhai.junhaimall.order.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejunhai.junhaimall.aftersale.constant.AfterSaleConstant;
import com.ejunhai.junhaimall.aftersale.model.AfterSaleRequ;
import com.ejunhai.junhaimall.aftersale.service.IAfterSaleRequService;
import com.ejunhai.junhaimall.framework.base.KeyGenerator;
import com.ejunhai.junhaimall.framework.core.BaseSpringService;
import com.ejunhai.junhaimall.order.constant.OrderConstant;
import com.ejunhai.junhaimall.order.dao.OrderReplMapper;
import com.ejunhai.junhaimall.order.model.OrderLog;
import com.ejunhai.junhaimall.order.model.OrderMain;
import com.ejunhai.junhaimall.order.model.OrderRepl;
import com.ejunhai.junhaimall.order.service.IOrderLogService;
import com.ejunhai.junhaimall.order.service.IOrderReplService;
import com.ejunhai.junhaimall.order.util.OrderUtil;
import com.ejunhai.junhaimall.system.model.Area;
import com.ejunhai.junhaimall.system.service.IAreaService;

/**
 * 补货单服务service
 * 
 * @author 罗正加
 * @history 2011-12-12 罗正加 新建
 */
@Service
public class OrderReplServiceImpl extends BaseSpringService implements IOrderReplService {
	@Resource
	private OrderReplMapper orderReplMapper;

	@Resource
	private IAreaService areaService;

	@Resource
	private IAfterSaleRequService afterSaleRequService;

	@Resource
	private IOrderLogService orderLogService;

	@Override
	public List<OrderRepl> queryOrderReplList(OrderRepl orderRepl, int pageNo, int pageSize) {
		RowBounds rowBounds = new RowBounds((pageNo - 1) * pageSize, pageSize);
		return orderReplMapper.queryOrderReplList(orderRepl, rowBounds);
	}

	@Override
	public Integer queryOrderReplCount(OrderRepl orderRepl) {
		return orderReplMapper.queryOrderReplCount(orderRepl);
	}

	@Override
	public OrderRepl readOrderRepl(String orderReplId) {
		return orderReplMapper.readOrderRepl(orderReplId);
	}

	@Override
	public OrderRepl getOrderReplByOrderReplNo(String orderReplNo) {
		return orderReplMapper.getOrderReplByOrderReplNo(orderReplNo);
	}

	@Override
	public OrderRepl createOrderRepl(OrderMain orderMain, OrderRepl orderRepl) {
		orderRepl.setId(KeyGenerator.getUUID());
		orderRepl.setOrderReplNo(OrderUtil.createOrderReplNo());
		orderRepl.setOrderMainNo(orderMain.getOrderMainNo());
		orderRepl.setCreateTime(new Date());
		orderRepl.setOrderDate(orderMain.getOrderDate());
		orderRepl.setPayAmount(orderMain.getPayAmount());
		orderRepl.setLogisticsCompany(orderMain.getLogisticsCompany());

		// 封装省市区数据
		Area area = areaService.getAreaByNo(orderRepl.getProvinceCode());
		String provinceCityArea = area.getName();
		area = areaService.getAreaByNo(orderRepl.getCityCode());
		provinceCityArea += "  " + area.getName();
		area = areaService.getAreaByNo(orderRepl.getAreaCode());
		provinceCityArea += "  " + area.getName();
		orderRepl.setProvinceCityArea(provinceCityArea);

		orderRepl.setState(OrderConstant.ORDER_STATE_WAIT_DELIVER);
		orderRepl.setIsSendSms(OrderConstant.ORDER_SEND_SMS_NO);
		orderReplMapper.insertOrderRepl(orderRepl);

		// 更新售后请求状态
		AfterSaleRequ afterSaleRequ = afterSaleRequService.getAfterSaleRequByOrderMainNo(orderMain.getOrderMainNo());
		if (afterSaleRequ != null) {
			afterSaleRequ.setDealTime(new Date());
			afterSaleRequ.setDealInfo(orderRepl.getRemark());
			afterSaleRequ.setState(AfterSaleConstant.AFTER_SALE_REQU_HAVE_DEAL);
			afterSaleRequService.updateAfterSaleRequ(afterSaleRequ);
		}

		// 记录订单处理日志
		OrderLog orderLog = new OrderLog();
		orderLog.setBehavioutDescribe("补货单已生成，正在给您备货。");
		orderLog.setOrderNo(orderRepl.getOrderReplNo());
		orderLog.setLogType(OrderConstant.ORDER_LOG_TYPE_REPL);
		orderLogService.insertOrderLog(orderLog);
		return orderRepl;
	}

	@Override
	public void changeConsigneeInfo(OrderRepl orderRepl) {
		Area area = areaService.getAreaByNo(orderRepl.getProvinceCode());
		String provinceCityArea = area.getName();
		area = areaService.getAreaByNo(orderRepl.getCityCode());
		provinceCityArea += "  " + area.getName();
		area = areaService.getAreaByNo(orderRepl.getAreaCode());
		provinceCityArea += "  " + area.getName();
		orderRepl.setProvinceCityArea(provinceCityArea);
		this.orderReplMapper.updateOrderRepl(orderRepl);
	}

	@Override
	@Transactional
	public void deliverOrderRepl(OrderRepl orderRepl) {
		Area area = areaService.getAreaByNo(orderRepl.getProvinceCode());
		String provinceCityArea = area.getName();
		area = areaService.getAreaByNo(orderRepl.getCityCode());
		provinceCityArea += "  " + area.getName();
		area = areaService.getAreaByNo(orderRepl.getAreaCode());
		provinceCityArea += "  " + area.getName();
		orderRepl.setProvinceCityArea(provinceCityArea);

		orderRepl.setState(OrderConstant.ORDER_STATE_HAVE_DELIVER);
		this.orderReplMapper.updateOrderRepl(orderRepl);

		// 记录订单处理日志
		orderRepl = this.readOrderRepl(orderRepl.getId());
		OrderLog orderLog = new OrderLog();
		String logiInfo = orderRepl.getLogisticsCompany() + ",快递单号：" + orderRepl.getExpressOrderNo();
		orderLog.setBehavioutDescribe("补货单已出库，请您留意签收。" + logiInfo);
		orderLog.setOrderNo(orderRepl.getOrderReplNo());
		orderLog.setLogType(OrderConstant.ORDER_LOG_TYPE_REPL);
		orderLogService.insertOrderLog(orderLog);
	}

	@Override
	public void removeOrderRepl(String orderReplId) {
		orderReplMapper.removeOrderRepl(orderReplId);
	}

	@Override
	public void printExpress(String orderReplIds) {
		String[] orderReplIdArr = orderReplIds.split(",");
		for (String orderReplId : orderReplIdArr) {
			OrderRepl orderRepl = this.readOrderRepl(orderReplId);
			if (orderRepl.getIsPrintExpress() == 0) {
				this.orderReplMapper.printExpress(orderReplId);

				// 记录订单处理日志
				OrderLog orderLog = new OrderLog();
				orderLog.setBehavioutDescribe("已打印订单，准备出库。");
				orderLog.setOrderNo(orderRepl.getOrderReplNo());
				orderLog.setLogType(OrderConstant.ORDER_LOG_TYPE_REPL);
				orderLogService.insertOrderLog(orderLog);
			}
		}
	}

}
