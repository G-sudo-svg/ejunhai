package com.ejunhai.junhaimall.mall.client;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ejunhai.junhaimall.aftersale.model.AfterSaleCons;
import com.ejunhai.junhaimall.aftersale.model.AfterSaleRequ;
import com.ejunhai.junhaimall.aftersale.service.IAfterSaleConsService;
import com.ejunhai.junhaimall.aftersale.service.IAfterSaleRequService;
import com.ejunhai.junhaimall.coupon.constant.CouponConstant;
import com.ejunhai.junhaimall.coupon.model.Coupon;
import com.ejunhai.junhaimall.coupon.model.CouponScheme;
import com.ejunhai.junhaimall.coupon.model.CouponUseDataVo;
import com.ejunhai.junhaimall.coupon.service.ICouponSchemeService;
import com.ejunhai.junhaimall.coupon.service.ICouponService;
import com.ejunhai.junhaimall.framework.base.BaseController;
import com.ejunhai.junhaimall.framework.constant.Constant;
import com.ejunhai.junhaimall.framework.util.DateUtil;
import com.ejunhai.junhaimall.framework.util.FreeMarkerTemplateHelper;
import com.ejunhai.junhaimall.framework.util.GetSessionUtil;
import com.ejunhai.junhaimall.framework.util.Md5Encrypt;
import com.ejunhai.junhaimall.mall.constant.MallConstant;
import com.ejunhai.junhaimall.mall.util.LoginUtil;
import com.ejunhai.junhaimall.order.constant.OrderConstant;
import com.ejunhai.junhaimall.order.model.OrderLog;
import com.ejunhai.junhaimall.order.model.OrderLogisticsTrack;
import com.ejunhai.junhaimall.order.model.OrderMain;
import com.ejunhai.junhaimall.order.model.OrderRepl;
import com.ejunhai.junhaimall.order.service.IOrderLogService;
import com.ejunhai.junhaimall.order.service.IOrderMainService;
import com.ejunhai.junhaimall.order.service.IOrderReplService;
import com.ejunhai.junhaimall.order.util.LogisticsTrackUtil;
import com.ejunhai.junhaimall.system.model.Config;
import com.ejunhai.junhaimall.system.service.IConfigService;

/**
 * Login Controller
 * 
 * @author 吴阳
 * @history 2012-07-19 吴阳 新建
 */
@Controller
@RequestMapping("")
public class IndexController extends BaseController {
	private Map<String, Integer> userMap = new HashMap<String, Integer>();

	@Autowired
	private ICouponService couponService;

	@Autowired
	private ICouponSchemeService couponSchemeService;

	@Autowired
	private IOrderMainService orderMainService;

	@Autowired
	private IAfterSaleConsService afterSaleConsService;

	@Autowired
	private IAfterSaleRequService afterSaleRequService;

	@Autowired
	private IConfigService configService;

	@Autowired
	private IOrderLogService orderLogService;

	@Autowired
	private IOrderReplService orderReplService;

	@RequestMapping("/index")
	public String index(ModelMap modelMap) throws Exception {
		return "index";
	}

	@RequestMapping("/logout")
	public String logout(ModelMap modelMap, HttpServletRequest req) throws Exception {
		LoginUtil.logout(req);
		return "redirect:index.sc";
	}

	@RequestMapping(value = "/checkCoupon", method = RequestMethod.POST)
	@ResponseBody
	public String checkCoupon(String couponNumber, String couponPassword, String validateCode, ModelMap modelMap,
			HttpServletRequest request) throws Exception {
		if (StringUtils.isBlank(couponNumber) || StringUtils.isBlank(couponPassword)) {
			modelMap.put("state", "1");
			modelMap.put("msg", "礼品卡或密码不能为空");
			return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
		}

		if (StringUtils.isBlank(validateCode)) {
			modelMap.put("state", "1");
			modelMap.put("msg", "验证码不能为空");
			return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
		}

		String sValidateCode = (String) request.getSession().getAttribute(Constant.LOGIN_VALIDATE_IMAGE);
		if (!validateCode.equals(sValidateCode)) {
			modelMap.put("state", "1");
			modelMap.put("msg", "验证码不对");
			return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
		}

		Coupon coupon = couponService.getCouponByNo(couponNumber);
		if (coupon == null || coupon.getState() == CouponConstant.COUPON_STATE_NO_ACTIVATE
				|| coupon.getState() == CouponConstant.COUPON_STATE_DISCARD) {
			modelMap.put("state", "1");
			modelMap.put("msg", "抱歉，该礼品卡无效");
			return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
		}

		// 由于客户端过来时使用md5加密，要根据服务端是否扰乱来判断礼品卡的有效性
		String realCouponPassword = coupon.getCouponPassword();
		CouponScheme couponScheme = couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
		if (couponScheme.getHasDisturb() == CouponConstant.COUPON_PASSWORD_DISTURB_NO) {
			realCouponPassword = Md5Encrypt.md5(realCouponPassword);
		}

		if (!couponPassword.equalsIgnoreCase(realCouponPassword)) {
			modelMap.put("state", "1");
			modelMap.put("msg", "抱歉，该礼品卡无效");
			return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
		}

		modelMap.put("state", "0");
		return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(String couponNumber, String couponPassword, String validateCode, ModelMap modelMap,
			HttpServletRequest request) throws Exception {
		String sValidateCode = (String) request.getSession().getAttribute(Constant.LOGIN_VALIDATE_IMAGE);
		if (StringUtils.isBlank(couponNumber) || StringUtils.isBlank(couponPassword)
				|| StringUtils.isBlank(validateCode) || !validateCode.equals(sValidateCode)) {
			return new ModelAndView(new RedirectView("/index.sc", true));
		}

		// 礼品券已作废重定向到登陆页面
		Coupon coupon = couponService.getCouponByNo(couponNumber);
		if (coupon == null || coupon.getState() == CouponConstant.COUPON_STATE_DISCARD) {
			return new ModelAndView(new RedirectView("/index.sc", true));
		}

		// 由于客户端过来时使用md5加密，要根据服务端是否扰乱来判断礼品卡的有效性
		String realCouponPassword = coupon.getCouponPassword();
		CouponScheme couponScheme = couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
		if (couponScheme.getHasDisturb() == CouponConstant.COUPON_PASSWORD_DISTURB_NO) {
			realCouponPassword = Md5Encrypt.md5(realCouponPassword);
		}

		// 二次验证礼品券和密码，如不一致则重定向到登陆页面
		if (!couponPassword.equalsIgnoreCase(realCouponPassword)) {
			return new ModelAndView(new RedirectView("/index.sc", true));
		}

		// 礼品券验证通过，保存至session中
		request.getSession().setAttribute(LoginUtil.LOGIN_USER, coupon);

		// 如果礼品券已使用则跳转到查看物流信息页面
		if (coupon.getState() == CouponConstant.COUPON_STATE_USED) {
			return new ModelAndView(new RedirectView("/toOrderInfo.sc", true));
		}

		// 礼品券未使用和已过期折跳转到下单预约页面
		return new ModelAndView(new RedirectView("/toOrder.sc", true));
	}

	@RequestMapping("/toOrder")
	public String toOrder(ModelMap modelMap, HttpServletRequest request) throws Exception {
		Coupon coupon = LoginUtil.getLoginUser(request);
		if (coupon == null) {
			return "index";
		}
		coupon = this.couponService.getCouponByNo(coupon.getCouponNumber());
		OrderMain orderMain = orderMainService.getOrdermainByOrderMainNo(coupon.getUseOrderNumber());
		if (orderMain != null) {
			return "redirect:toOrderInfo.sc";
		}

		CouponScheme couponScheme = couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
		modelMap.put("coupon", coupon);
		modelMap.put("couponScheme", couponScheme);
		modelMap.put("today", DateUtil.format(new Date(), "yyyy-MM-dd"));

		// 延迟时间
		Config config = configService.getConfigByKey(CouponConstant.KEY_COUPON_DEFERDATE);
		int deferDate = config == null ? 2 : Integer.parseInt(config.getConfigValue());
		modelMap.put("deferDate", deferDate);
		modelMap.put("startDate", DateUtil.format(coupon.getUseStartdate(), "yyyy-MM-dd"));
		modelMap.put("endDate", DateUtil.format(coupon.getUseEnddate(), "yyyy-MM-dd"));
		modelMap.put(
				"expiryDate",
				DateUtil.diffDate(DateUtil.getdate(DateUtil.format(coupon.getUseEnddate(), "yyyy-MM-dd")),
						DateUtil.getdate(DateUtil.format(new Date(), "yyyy-MM-dd"))));
		return "order";
	}

	@RequestMapping("/toOrderInfo")
	public String toOrderInfo(ModelMap modelMap, String isJustNowOrder, HttpServletRequest request) {
		Coupon coupon = LoginUtil.getLoginUser(request);
		if (coupon == null) {
			return "index";
		}

		CouponScheme couponScheme = couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
		coupon = this.couponService.getCouponByNo(coupon.getCouponNumber());
		modelMap.put("coupon", coupon);
		modelMap.put("couponScheme", couponScheme);

		OrderMain orderMain = orderMainService.getOrdermainByOrderMainNo(coupon.getUseOrderNumber());
		modelMap.put("orderMain", orderMain);
		modelMap.put("isJustNowOrder", StringUtils.equals(isJustNowOrder, "true") ? true : false);
		modelMap.put("action", MallConstant.PAGE_ACTION_ORDERINFO);
		return "orderInfo";
	}

	@RequestMapping("/toLogistics")
	public String toLogistics(ModelMap modelMap, HttpServletRequest request) {
		Coupon coupon = LoginUtil.getLoginUser(request);
		if (coupon == null) {
			return "index";
		}
		coupon = this.couponService.getCouponByNo(coupon.getCouponNumber());
		OrderMain orderMain = orderMainService.getOrdermainByOrderMainNo(coupon.getUseOrderNumber());
		modelMap.addAttribute("orderMain", orderMain);

		// 查询订单处理日志
		OrderLog orderLog = new OrderLog();
		orderLog.setOrderNo(orderMain.getOrderMainNo());
		modelMap.put("orderLogList", orderLogService.queryOrderLogList(orderLog, 0, Integer.MAX_VALUE));

		// 查询补货单列表
		OrderRepl queryOrderRepl = new OrderRepl();
		queryOrderRepl.setOrderMainNo(orderMain.getOrderMainNo());
		List<OrderRepl> orderReplList = orderReplService.queryOrderReplList(queryOrderRepl, 0, Integer.MAX_VALUE);
		for (OrderRepl orderRepl : orderReplList) {
			// 查询补货单处理日志
			orderLog = new OrderLog();
			orderLog.setOrderNo(orderRepl.getOrderReplNo());
			orderRepl.setOrderLogList(orderLogService.queryOrderLogList(orderLog, 0, Integer.MAX_VALUE));
		}
		modelMap.put("orderReplList", orderReplList);

		modelMap.put("action", MallConstant.PAGE_ACTION_LOGISTICSTRACK);
		return "logisticsTrack";
	}

	@RequestMapping("/toAfterSaleCons")
	public String toAfterSaleCons(ModelMap modelMap, HttpServletRequest request) {
		Coupon coupon = LoginUtil.getLoginUser(request);
		if (coupon == null) {
			return "index";
		}
		coupon = this.couponService.getCouponByNo(coupon.getCouponNumber());
		OrderMain orderMain = orderMainService.getOrdermainByOrderMainNo(coupon.getUseOrderNumber());
		AfterSaleCons queryAfterSaleCons = new AfterSaleCons();
		queryAfterSaleCons.setOrderMainNo(orderMain.getOrderMainNo());
		List<AfterSaleCons> lstAfterSaleCons = this.afterSaleConsService.queryAfterSaleConsList(queryAfterSaleCons, 0,
				Integer.MAX_VALUE);
		modelMap.addAttribute("lstAfterSaleCons", lstAfterSaleCons);
		modelMap.addAttribute("orderMain", orderMain);
		modelMap.put("action", MallConstant.PAGE_ACTION_REMARK);
		return "remark";
	}

	@RequestMapping("/toAfterSaleRequ")
	public String toAfterSaleRequ(ModelMap modelMap, HttpServletRequest request) {
		Coupon coupon = LoginUtil.getLoginUser(request);
		if (coupon == null) {
			return "index";
		}
		coupon = this.couponService.getCouponByNo(coupon.getCouponNumber());
		OrderMain orderMain = orderMainService.getOrdermainByOrderMainNo(coupon.getUseOrderNumber());
		AfterSaleRequ afterSaleRequ = new AfterSaleRequ();
		afterSaleRequ.setOrderMainNo(orderMain.getOrderMainNo());
		List<AfterSaleRequ> lstAfterSaleRequ = null;
		try {
			lstAfterSaleRequ = afterSaleRequService.queryAfterSaleRequList(afterSaleRequ, 0, Integer.MAX_VALUE);
		} catch (Exception ex) {
			logger.error("查询售后记录失败", ex);
		}
		if (lstAfterSaleRequ.size() > 0) {
			modelMap.addAttribute("afterSaleRequ", lstAfterSaleRequ.get(0));
		}
		modelMap.addAttribute("orderMain", orderMain);
		modelMap.put("action", MallConstant.PAGE_ACTION_SALESERVICE);
		return "saleService";
	}

	@RequestMapping("/createOrder")
	public String createOrder(OrderMain orderMain, ModelMap modelMap, HttpServletRequest request) throws Exception {
		Coupon coupon = LoginUtil.getLoginUser(request);
		if (coupon == null) {
			return "redirect:index.sc";
		}

		Assert.notNull(orderMain.getConsignee(), "consignee is null");
		Assert.notNull(orderMain.getProvinceCode(), "provinceCode is null");
		Assert.notNull(orderMain.getCityCode(), "cityCode is null");
		Assert.notNull(orderMain.getAreaCode(), "areaCode is null");
		Assert.notNull(orderMain.getOrderDate(), "orderDate is null");
		Assert.notNull(orderMain.getDetailAddress(), "detailAddress is null");
		Assert.notNull(orderMain.getMobilePhone(), "mobilePhone is null");

		coupon = couponService.getCouponByNo(coupon.getCouponNumber());
		// 非法情况
		if (coupon.getState().intValue() != CouponConstant.COUPON_STATE_ACTIVATE) {
			// throw new Exception("优惠券不是已生效状态,couponNumber=" +
			// coupon.getCouponNumber() + ",state=" + coupon.getState());
			// 已使用跳转到订单页面
			if (coupon.getState().intValue() == CouponConstant.COUPON_STATE_USED) {
				return "redirect:toOrderInfo.sc";
			} else {
				return "redirect:index.sc";
			}
		}
		orderMain = orderMainService.createOrderMain(coupon, orderMain);
		return "redirect:toOrderInfo.sc?isJustNowOrder=true";
	}

	@RequestMapping(value = "/getLogisticsTrack")
	@ResponseBody
	public String getLogisticsTrack(String expressOrderNo, ModelMap modelMap) throws Exception {
		if (StringUtils.isBlank(expressOrderNo)) {
			return "";
		}

		OrderLogisticsTrack orderLogisticsTrack = LogisticsTrackUtil.getLogisticsTrack(expressOrderNo);
		modelMap.addAttribute("orderLogisticsTrack", orderLogisticsTrack);
		return FreeMarkerTemplateHelper.parseTemplateToJson("common/orderLogisticsTrack.ftl", modelMap);
	}

	@RequestMapping(value = "/saveRemark", method = RequestMethod.POST)
	@ResponseBody
	public String saveRemark(String consultation, String orderMainNo, ModelMap modelMap, HttpServletRequest request)
			throws Exception {
		if (StringUtils.isBlank(consultation)) {
			return "";
		}

		Coupon coupon = LoginUtil.getLoginUser(request);
		if (coupon == null) {
			return "noLogin";
		}

		OrderMain orderMain = orderMainService.getOrdermainByOrderMainNo(orderMainNo);
		if (orderMain == null) {
			return "";
		}

		AfterSaleCons afterSaleCons = new AfterSaleCons();
		afterSaleCons.setConsultation(consultation);
		afterSaleCons.setOrderMainNo(orderMainNo);
		afterSaleConsService.insertAfterSaleCons(afterSaleCons);
		return "true";
	}

	@RequestMapping("/afterSaleRequ")
	public ModelAndView afterSaleRequ(AfterSaleRequ afterSaleRequ, ModelMap modelMap, HttpServletRequest request)
			throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String orderMainNo = afterSaleRequ.getOrderMainNo();
		String description = afterSaleRequ.getDescription();

		// 系统参数出错,跳转至错误页面
		if (StringUtils.isBlank(orderMainNo) || StringUtils.isBlank(description)) {
			return new ModelAndView(new RedirectView("/toAfterSaleRequ.sc", true));
		}

		// 系统参数出错,跳转至错误页面
		OrderMain orderMain = orderMainService.getOrdermainByOrderMainNo(orderMainNo);
		if (orderMain == null) {
			return new ModelAndView(new RedirectView("/toAfterSaleRequ.sc", true));
		}

		// 按月存放图片
		Config config = configService.getConfigByKey(OrderConstant.SEVER_IMAGE_UPLOAD_PATH);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		String currMonth = simpleDateFormat.format(new Date(System.currentTimeMillis()));

		// 目录不存在则创建目录
		String picServerPicPath = config.getConfigValue() + "/" + currMonth;
		if (!new File(picServerPicPath).exists()) {
			new File(picServerPicPath).mkdirs();
		}

		// 将图片保存至图片服务器
		String defaultPicUrl = GetSessionUtil.getServiceName(multipartRequest) + "uploads/" + currMonth;
		CommonsMultipartFile pic1File = (CommonsMultipartFile) multipartRequest.getFile("pic1File");
		String pic1Url = picServerPicPath + "/" + orderMainNo + "_pic1.jpg";
		if (pic1File != null && pic1File.getSize() > 0 && LoginUtil.savePic(pic1File, pic1Url) == 1) {
			afterSaleRequ.setPic1Url(defaultPicUrl + "/" + orderMainNo + "_pic1.jpg");
		}

		CommonsMultipartFile pic2File = (CommonsMultipartFile) multipartRequest.getFile("pic2File");
		String pic2Url = picServerPicPath + "/" + orderMainNo + "_pic2.jpg";
		if (pic2File != null && pic2File.getSize() > 0 && LoginUtil.savePic(pic2File, pic2Url) == 1) {
			afterSaleRequ.setPic2Url(defaultPicUrl + "/" + orderMainNo + "_pic2.jpg");
		}

		CommonsMultipartFile pic3File = (CommonsMultipartFile) multipartRequest.getFile("pic3File");
		String pic3Url = picServerPicPath + "/" + orderMainNo + "_pic3.jpg";
		if (pic3File != null && pic3File.getSize() > 0 && LoginUtil.savePic(pic3File, pic3Url) == 1) {
			afterSaleRequ.setPic3Url(defaultPicUrl + "/" + orderMainNo + "_pic3.jpg");
		}

		// 将图片保存至服务器
		try {
			afterSaleRequService.insertAfterSaleRequ(afterSaleRequ);
		} catch (Exception e) {
			logger.error("", e);
		}

		return new ModelAndView(new RedirectView("/toAfterSaleRequ.sc", true));
	}

	@RequestMapping(value = "/getCouponData", method = RequestMethod.POST)
	@ResponseBody
	public String getCouponData(String startDate, String endDate, ModelMap modelMap, HttpServletRequest request)
			throws Exception {
		String message = "";
		List<CouponUseDataVo> lstCouponUseDataVos = new ArrayList<CouponUseDataVo>();
		Coupon coupon = LoginUtil.getLoginUser(request);
		// CouponScheme couponScheme =
		// couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
		// 延迟时间
		Config config = configService.getConfigByKey(OrderConstant.ORDER_RESERVE_MAX_COUNT);
		int orderMaxCount = config == null ? 1000 : Integer.parseInt(config.getConfigValue());
		try {
			if (coupon == null) {
				message = "noLogin";
			} else {
				Date tempDate = DateUtil.getdate(startDate);
				for (int i = 1; i <= 42; i++) {
					CouponUseDataVo couponUseDataVo = new CouponUseDataVo();
					couponUseDataVo.setCouponSchemeId(coupon.getCouponSchemeId());
					couponUseDataVo.setUseDate(DateUtil.getYear(tempDate) + "-" + DateUtil.getMonth(tempDate) + "-"
							+ DateUtil.getDay(tempDate));
					// couponUseDataVo.setSumCount(couponScheme.getDayLimitNum());
					couponUseDataVo.setSumCount(orderMaxCount);
					tempDate = DateUtil.addDate(tempDate, 1);
					couponUseDataVo.setUseCount(couponService.usedCouponCountByDate(couponUseDataVo));
					lstCouponUseDataVos.add(couponUseDataVo);
				}
				message = "ok";
			}
		} catch (Exception ex) {
			logger.error("查询某天优惠券使用次数时失败", ex);
			message = "error";
		}

		modelMap.addAttribute("message", message);
		modelMap.addAttribute("result", lstCouponUseDataVos);
		modelMap.addAttribute("couponSchemeId", coupon.getCouponSchemeId());
		return FreeMarkerTemplateHelper.parseTemplateToJson("common/couponData.ftl", modelMap);
	}

	@RequestMapping("/clientException")
	public String clientException() {
		return null;
	}

	@RequestMapping(value = "/coupon")
	public String queryCouponInfo(HttpServletRequest request) throws Exception {
		return "coupon";
	}

	@RequestMapping(value = "/getCouponState")
	@ResponseBody
	public String getCouponState(String couponNumber, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String strKey = dateformat.format(new Date()) + "_" + getUserId(request, response);
		Object objMinOperteCount = userMap.get(strKey);
		int minOperteCount = objMinOperteCount == null ? 0 : (Integer) objMinOperteCount;
		if (minOperteCount >= 10) {
			modelMap.put("state", "1");
			modelMap.put("msg", "不要太频繁操作，请稍后再试");
			return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
		}

		if (StringUtils.isEmpty(couponNumber)) {
			modelMap.put("state", "1");
			modelMap.put("msg", "优惠券码不能为空");
			return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
		}

		Coupon coupon = couponService.getCouponByNo(couponNumber);
		userMap.put(strKey, ++minOperteCount);
		if (coupon == null || coupon.getState() == CouponConstant.COUPON_STATE_NO_ACTIVATE
				|| coupon.getState() == CouponConstant.COUPON_STATE_DISCARD) {
			modelMap.put("state", "1");
			modelMap.put("msg", "抱歉，该礼品卡无效");
			return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
		}

		if (coupon.getState() == CouponConstant.COUPON_STATE_EXPIRE) {
			modelMap.put("state", "1");
			modelMap.put("msg", "抱歉，该礼品卡已过期");
			return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
		}

		if (coupon.getState() == CouponConstant.COUPON_STATE_USED) {
			modelMap.put("state", "1");
			modelMap.put("msg", "该礼品卡已使用");
			return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
		}

		modelMap.put("state", "0");
		modelMap.put("msg", "该礼品卡未使用，赶紧预定吧");
		return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
	}
}
