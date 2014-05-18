package com.ejunhai.junhaimall.mall.client;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejunhai.junhaimall.coupon.constant.CouponConstant;
import com.ejunhai.junhaimall.coupon.model.Coupon;
import com.ejunhai.junhaimall.coupon.model.CouponScheme;
import com.ejunhai.junhaimall.coupon.service.ICouponSchemeService;
import com.ejunhai.junhaimall.coupon.service.ICouponService;
import com.ejunhai.junhaimall.framework.base.BaseController;
import com.ejunhai.junhaimall.framework.util.DateUtil;
import com.ejunhai.junhaimall.mall.util.LoginUtil;
import com.ejunhai.junhaimall.order.model.OrderMain;
import com.ejunhai.junhaimall.order.service.IOrderMainService;
import com.ejunhai.junhaimall.system.model.Config;
import com.ejunhai.junhaimall.system.service.IConfigService;

/**
 * order Controller
 * 
 * @author parcel
 * @history 2014-05-04 parcel 新建
 */
@Controller
@RequestMapping("")
public class OrderController extends BaseController {

	@Autowired
	private ICouponService couponService;

	@Autowired
	private ICouponSchemeService couponSchemeService;

	@Autowired
	private IOrderMainService orderMainService;

	@Autowired
	private IConfigService configService;

	@RequestMapping("/toSubscribe")
	public String toSubscribe(ModelMap modelMap, HttpServletRequest request) {
		Coupon coupon = LoginUtil.getLoginUser(request);
		if (coupon == null) {
			return "index";
		}

		CouponScheme couponScheme = couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
		modelMap.put("coupon", coupon);
		modelMap.put("couponScheme", couponScheme);

		// 提前预订时间
		Config config = configService.getConfigByKey(CouponConstant.KEY_COUPON_DEFERDATE);
		int deferDate = config == null ? 2 : Integer.parseInt(config.getConfigValue());
		modelMap.put("startDate", DateUtil.format(DateUtil.addDate(new Date(), deferDate), "yyyy-MM-dd"));
		modelMap.put("endDate", DateUtil.format(DateUtil.addDate(coupon.getUseEnddate(), deferDate), "yyyy-MM-dd"));

		return "subscribe";
	}

	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	public String createOrder(OrderMain orderMain, ModelMap modelMap, HttpServletRequest request) throws Exception {
		Coupon coupon = LoginUtil.getLoginUser(request);
		if (coupon == null) {
			return "index";
		}

		// 验证礼品卡的可用性
		coupon = couponService.getCouponByNo(coupon.getCouponNumber());
		if (coupon.getState().intValue() != CouponConstant.COUPON_STATE_ACTIVATE) {
			return "redirect:toSubscribe.jhtml";
		}

		Assert.notNull(orderMain.getConsignee(), "consignee is null");
		Assert.notNull(orderMain.getProvinceCode(), "provinceCode is null");
		Assert.notNull(orderMain.getCityCode(), "cityCode is null");
		Assert.notNull(orderMain.getAreaCode(), "areaCode is null");
		Assert.notNull(orderMain.getOrderDate(), "orderDate is null");
		Assert.notNull(orderMain.getDetailAddress(), "detailAddress is null");
		Assert.notNull(orderMain.getMobilePhone(), "mobilePhone is null");

		orderMain = orderMainService.createOrderMain(coupon, orderMain);

		// 更新coupon状态
		request.getSession().setAttribute(LoginUtil.LOGIN_USER, coupon);
		modelMap.put("orderMain", orderMain);
		modelMap.put("createOrderSuccess", true);
		return "profile";
	}

	@RequestMapping("/orderInfo")
	public String orderInfo(ModelMap modelMap, HttpServletRequest request) {
		Coupon coupon = LoginUtil.getLoginUser(request);
		if (coupon == null) {
			return "index";
		}

		coupon = this.couponService.getCouponByNo(coupon.getCouponNumber());
		CouponScheme couponScheme = couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
		modelMap.put("coupon", coupon);
		modelMap.put("couponScheme", couponScheme);

		OrderMain orderMain = orderMainService.getOrdermainByOrderMainNo(coupon.getUseOrderNumber());
		modelMap.put("orderMain", orderMain);
		return "orderInfo";
	}
}
