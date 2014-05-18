package com.ejunhai.junhaimall.order.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejunhai.junhaimall.coupon.constant.CouponConstant;
import com.ejunhai.junhaimall.coupon.model.Coupon;
import com.ejunhai.junhaimall.coupon.model.CouponScheme;
import com.ejunhai.junhaimall.coupon.service.ICouponSchemeService;
import com.ejunhai.junhaimall.coupon.service.ICouponService;
import com.ejunhai.junhaimall.framework.base.KeyGenerator;
import com.ejunhai.junhaimall.framework.core.BaseSpringService;
import com.ejunhai.junhaimall.order.constant.OrderConstant;
import com.ejunhai.junhaimall.order.dao.OrderMainMapper;
import com.ejunhai.junhaimall.order.model.OrderLog;
import com.ejunhai.junhaimall.order.model.OrderMain;
import com.ejunhai.junhaimall.order.service.IOrderLogService;
import com.ejunhai.junhaimall.order.service.IOrderMainService;
import com.ejunhai.junhaimall.order.util.OrderUtil;
import com.ejunhai.junhaimall.system.model.Area;
import com.ejunhai.junhaimall.system.service.IAreaService;

/**
 * 订单服务service
 * 
 * @author 罗正加
 * @history 2011-12-12 罗正加 新建
 */
@Service
public class OrderMainServiceImpl extends BaseSpringService implements IOrderMainService {
	@Resource
	private OrderMainMapper orderMainMapper;

	@Resource
	private ICouponService couponService;

	@Resource
	private ICouponSchemeService couponSchemeService;

	@Resource
	private IAreaService areaService;

	@Resource
	private IOrderLogService orderLogService;

	@Override
	public List<OrderMain> queryOrderMainList(OrderMain orderMain, int pageNo, int pageSize) {
		RowBounds rowBounds = new RowBounds((pageNo - 1) * pageSize, pageSize);
		return orderMainMapper.queryOrderMainList(orderMain, rowBounds);
	}

	@Override
	public Integer queryOrderMainCount(OrderMain orderMain) {
		return orderMainMapper.queryOrderMainCount(orderMain);
	}

	@Override
	public OrderMain readOrderMain(String orderMainId) {
		return orderMainMapper.readOrderMain(orderMainId);
	}

	@Override
	public OrderMain getOrdermainByOrderMainNo(String orderMainNo) {
		return orderMainMapper.getOrdermainByOrderMainNo(orderMainNo);
	}

	@Override
	@Transactional
	public OrderMain createOrderMain(Coupon coupon, OrderMain orderMain) throws Exception {
		// 创建订单数据
		orderMain.setId(KeyGenerator.getUUID());
		String orderMainNo = OrderUtil.createOrderMainNo();
		orderMain.setOrderMainNo(orderMainNo);
		CouponScheme couponScheme = couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
		orderMain.setPayAmount(Double.valueOf(couponScheme.getParValue()));
		orderMain.setCouponNumber(coupon.getCouponNumber());
		orderMain.setCreateTime(new Date());
		orderMain.setState(OrderConstant.ORDER_STATE_WAIT_DELIVER);
		orderMain.setIsSendSms(OrderConstant.ORDER_SEND_SMS_NO);

		// 封装省市区数据
		Area area = areaService.getAreaByNo(orderMain.getProvinceCode());
		String provinceCityArea = area.getName();
		area = areaService.getAreaByNo(orderMain.getCityCode());
		provinceCityArea += "  " + area.getName();
		area = areaService.getAreaByNo(orderMain.getAreaCode());
		provinceCityArea += "  " + area.getName();
		orderMain.setProvinceCityArea(provinceCityArea);
		this.orderMainMapper.insertOrderMain(orderMain);

		// 更新优惠券状态
		coupon.setUseOrderNumber(orderMainNo);
		coupon.setUseTime(new Date());
		coupon.setState(CouponConstant.COUPON_STATE_USED);
		couponService.updateCoupon(coupon);

		// 记录订单处理日志
		OrderLog orderLog = new OrderLog();
		orderLog.setBehavioutDescribe("订单已预定成功，正在给您备货。");
		orderLog.setOrderNo(orderMainNo);
		orderLog.setLogType(OrderConstant.ORDER_LOG_TYPE_MAIN);
		orderLogService.insertOrderLog(orderLog);
		return orderMain;
	}

	@Override
	public void changeConsigneeInfo(OrderMain orderMain) {
		Area area = areaService.getAreaByNo(orderMain.getProvinceCode());
		String provinceCityArea = area.getName();
		area = areaService.getAreaByNo(orderMain.getCityCode());
		provinceCityArea += "  " + area.getName();
		area = areaService.getAreaByNo(orderMain.getAreaCode());
		provinceCityArea += "  " + area.getName();
		orderMain.setProvinceCityArea(provinceCityArea);
		this.orderMainMapper.updateOrderMain(orderMain);
	}

	@Override
	@Transactional
	public void deliverOrderMain(OrderMain orderMain) {
		Area area = areaService.getAreaByNo(orderMain.getProvinceCode());
		String provinceCityArea = area.getName();
		area = areaService.getAreaByNo(orderMain.getCityCode());
		provinceCityArea += "  " + area.getName();
		area = areaService.getAreaByNo(orderMain.getAreaCode());
		provinceCityArea += "  " + area.getName();
		orderMain.setProvinceCityArea(provinceCityArea);

		orderMain.setState(OrderConstant.ORDER_STATE_HAVE_DELIVER);
		orderMain.setDeliverTime(new Date());
		this.orderMainMapper.updateOrderMain(orderMain);

		// 记录订单处理日志
		orderMain = this.readOrderMain(orderMain.getId());
		OrderLog orderLog = new OrderLog();
		String logiInfo = orderMain.getLogisticsCompany() + ",快递单号：" + orderMain.getExpressOrderNo();
		orderLog.setBehavioutDescribe("订单已出库，请您留意签收。" + logiInfo);
		orderLog.setOrderNo(orderMain.getOrderMainNo());
		orderLog.setLogType(OrderConstant.ORDER_LOG_TYPE_MAIN);
		orderLogService.insertOrderLog(orderLog);
	}

	@Override
	@Transactional
	public void removeOrderMain(String orderMainId) {
		this.orderMainMapper.removeOrderMain(orderMainId);
	}

	@Override
	public void printExpress(String orderMainIds) {
		try {
			String[] orderMainIdArr = orderMainIds.split(",");
			for (String orderMainId : orderMainIdArr) {
				OrderMain orderMain = this.readOrderMain(orderMainId);
				if (orderMain != null && orderMain.getIsPrintExpress() == 0) {
					this.orderMainMapper.printExpress(orderMainId);

					// 记录订单处理日志
					OrderLog orderLog = new OrderLog();
					orderLog.setBehavioutDescribe("已打印订单，准备出库。");
					orderLog.setOrderNo(orderMain.getOrderMainNo());
					orderLog.setLogType(OrderConstant.ORDER_LOG_TYPE_MAIN);
					orderLogService.insertOrderLog(orderLog);
				}
			}
		} catch (Exception e) {
			logger.error("更新订单是否打印记录出错", e);
		}
	}
}
