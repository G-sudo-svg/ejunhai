package com.ejunhai.junhaimall.web;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ejunhai.junhaimall.coupon.model.Coupon;
import com.ejunhai.junhaimall.coupon.model.CouponScheme;
import com.ejunhai.junhaimall.coupon.service.ICouponSchemeService;
import com.ejunhai.junhaimall.coupon.service.ICouponService;
import com.ejunhai.junhaimall.framework.base.BaseController;
import com.ejunhai.junhaimall.framework.base.PageFinder;
import com.ejunhai.junhaimall.framework.base.Query;
import com.ejunhai.junhaimall.framework.util.FreeMarkerTemplateHelper;
import com.ejunhai.junhaimall.framework.util.Md5Encrypt;
import com.ejunhai.junhaimall.order.constant.OrderConstant;
import com.ejunhai.junhaimall.order.model.OrderLog;
import com.ejunhai.junhaimall.order.model.OrderMain;
import com.ejunhai.junhaimall.order.service.IOrderLogService;
import com.ejunhai.junhaimall.order.service.IOrderMainService;
import com.ejunhai.junhaimall.order.util.OrderUtil;
import com.ejunhai.junhaimall.system.model.Config;
import com.ejunhai.junhaimall.system.service.IConfigService;

/**
 * 订单管理controller
 * 
 * @author 罗正加
 * @history 2011-12-16 罗正加 新建
 */
@Controller
@RequestMapping("order")
public class OrderMainController extends BaseController {

	@Autowired
	private IOrderMainService orderMainService;

	@Autowired
	private ICouponService couponService;

	@Autowired
	private ICouponSchemeService couponSchemeService;

	@Autowired
	private IConfigService configService;

	@Autowired
	private IOrderLogService orderLogService;

	@RequestMapping("/orderMainList")
	public String orderMainList(OrderMain orderMain, Query query, ModelMap modelMap) throws Exception {
		int pageNo = query.getPage();
		int pageSize = query.getPageSize();
		int count = orderMainService.queryOrderMainCount(orderMain);

		List<OrderMain> orderMainList = new ArrayList<OrderMain>(0);
		if (count > 0) {
			orderMainList = orderMainService.queryOrderMainList(orderMain, pageNo, pageSize);
		}
		PageFinder<OrderMain> pageFinder = new PageFinder<OrderMain>(pageNo, pageSize, count);
		pageFinder.setData(orderMainList);
		modelMap.addAttribute("pageFinder", pageFinder);

		modelMap.addAttribute("orderMain", orderMain);
		return "order/orderMainList";
	}

	@RequestMapping("/toOrderMain")
	public String toOrderMain(String orderMainId, ModelMap modelMap) throws Exception {
		if (StringUtils.isNotBlank(orderMainId)) {
			OrderMain orderMain = orderMainService.readOrderMain(orderMainId);
			modelMap.addAttribute("orderMain", orderMain);
			Coupon coupon = couponService.getCouponByOrderNumber(orderMain.getOrderMainNo());
			modelMap.addAttribute("coupon", coupon);
			CouponScheme couponScheme = couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
			modelMap.addAttribute("couponScheme", couponScheme);

			// 查询物流信息
			OrderLog orderLog = new OrderLog();
			orderLog.setOrderNo(orderMain.getOrderMainNo());
			modelMap.put("orderLogList", orderLogService.queryOrderLogList(orderLog, 0, Integer.MAX_VALUE));
			return "order/editOrderMain";
		}
		return "order/addOrderMain";
	}

	@RequestMapping("/createOrderMain")
	public ModelAndView createOrderMain(Coupon coupon, OrderMain orderMain, ModelMap modelMap) throws Exception {
		if (StringUtils.isBlank(coupon.getCouponNumber()) || StringUtils.isBlank(coupon.getCouponPassword())) {
			throw new Exception("创建订单参数出错");
		}

		String couponPassword = Md5Encrypt.md5(coupon.getCouponPassword());
		String[] msg = this.couponService.checkCoupon(coupon.getCouponNumber(), couponPassword);
		if (msg[0].equals("0")) {
			coupon = couponService.getCouponByNo(coupon.getCouponNumber());
			orderMainService.createOrderMain(coupon, orderMain);
		}
		return new ModelAndView(new RedirectView("/order/orderMainList.sc?state=0", true));
	}

	@RequestMapping("/toDeliverOrderMain")
	public String toDeliver(String orderMainId, ModelMap modelMap) throws Exception {
		if (StringUtils.isNotBlank(orderMainId)) {
			OrderMain orderMain = orderMainService.readOrderMain(orderMainId);
			modelMap.addAttribute("orderMain", orderMain);
			Coupon coupon = couponService.getCouponByOrderNumber(orderMain.getOrderMainNo());
			modelMap.addAttribute("coupon", coupon);
			CouponScheme couponScheme = couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
			modelMap.addAttribute("couponScheme", couponScheme);
		}
		List<Map<String, String>> logisticCompanyList = OrderUtil.getLogisticCompanyList();
		modelMap.addAttribute("logisticCompanyList", logisticCompanyList);
		return "order/deliverOrderMain";
	}

	@RequestMapping("/changeConsigneeInfo")
	public ModelAndView changeConsigneeInfo(OrderMain orderMain, ModelMap modelMap) throws Exception {
		if (StringUtils.isBlank(orderMain.getAreaCode()) || StringUtils.isBlank(orderMain.getDetailAddress())) {
			throw new Exception("发货地址信息有错");
		}
		orderMainService.changeConsigneeInfo(orderMain);
		return new ModelAndView(new RedirectView("/order/orderMainList.sc?state=0", true));
	}

	@RequestMapping("/deliverOrderMain")
	public ModelAndView deliverOrderMain(OrderMain orderMain, ModelMap modelMap) throws Exception {
		if (StringUtils.isBlank(orderMain.getExpressOrderNo()) || StringUtils.isBlank(orderMain.getMobilePhone())) {
			throw new Exception("发货信息有错");
		}

		// 根据物流公司编码获取物流公司名称
		if (StringUtils.isNotBlank(orderMain.getLogisticsCompanyCode())) {
			orderMain.setLogisticsCompany(OrderUtil.getLogisticCompany(orderMain.getLogisticsCompanyCode()));
		}

		orderMainService.deliverOrderMain(orderMain);
		return new ModelAndView(new RedirectView("/order/orderMainList.sc?state=0", true));
	}

	@RequestMapping("/toPrintMainExpress")
	public String toPrintMainExpress(String orderMainId, ModelMap modelMap) throws Exception {
		if (StringUtils.isBlank(orderMainId)) {
			logger.error("需打印的订单号不能为空");
		}

		// 查询需要打印的快递单数据
		modelMap.put("orderMainIds", orderMainId);
		OrderMain orderMain = orderMainService.readOrderMain(orderMainId);
		modelMap.put("orderMain", orderMain);

		Config config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_COMPANY);
		modelMap.put("deliveryCompany", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_SENDER);
		modelMap.put("deliverySender", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_PROCITYAREA);
		modelMap.put("deliveryProCityArea", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_DETAIADDRESS);
		modelMap.put("deliveryDetailAddress", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_TELEPHONE);
		modelMap.put("deliveryTelphone", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_MOBILE_PHONE);
		modelMap.put("deliveryMobilePhone", config.getConfigValue());

		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_CONTENT);
		modelMap.put("deliveryContent", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_SENDER_SIGN);
		modelMap.put("deliverySenderSign", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_CUSTOMER_CODE);
		modelMap.put("deliveryCustomerCode", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_ORIGN_ADDRESS);
		modelMap.put("deliveryOrignAddress", config.getConfigValue());

		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_MONTHLY_PAYMENT);
		modelMap.put("monthlyPayment", config.getConfigValue());
		return "order/printExpressTemplate";
	}

	@RequestMapping("/toBatchPrintMainExpress")
	public String toBatchPrintMainExpress(String orderMainIds, int state, String logisticsCompany, ModelMap modelMap)
			throws Exception {
		if (StringUtils.isBlank(orderMainIds)) {
			logger.error("需打印的订单号不能为空");
		}

		// 查询需要打印的快递单数据
		String[] arrOrderMainId = orderMainIds.split(",");
		List<OrderMain> orderMainList = new ArrayList<OrderMain>(arrOrderMainId.length);
		for (String orderMainId : arrOrderMainId) {
			OrderMain orderMain = orderMainService.readOrderMain(orderMainId);
			if (orderMain != null) {
				orderMainList.add(orderMain);
			}
		}
		modelMap.put("state", state);
		modelMap.put("orderMainIds", orderMainIds);
		modelMap.put("logisticsCompany", logisticsCompany);
		modelMap.put("orderList", orderMainList);

		Config config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_COMPANY);
		modelMap.put("deliveryCompany", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_SENDER);
		modelMap.put("deliverySender", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_PROCITYAREA);
		modelMap.put("deliveryProCityArea", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_DETAIADDRESS);
		modelMap.put("deliveryDetailAddress", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_TELEPHONE);
		modelMap.put("deliveryTelphone", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_MOBILE_PHONE);
		modelMap.put("deliveryMobilePhone", config.getConfigValue());

		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_CONTENT);
		modelMap.put("deliveryContent", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_SENDER_SIGN);
		modelMap.put("deliverySenderSign", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_CUSTOMER_CODE);
		modelMap.put("deliveryCustomerCode", config.getConfigValue());
		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_ORIGN_ADDRESS);
		modelMap.put("deliveryOrignAddress", config.getConfigValue());

		config = configService.getConfigByKey(OrderConstant.EXPRESS_DELIVERY_MONTHLY_PAYMENT);
		modelMap.put("monthlyPayment", config.getConfigValue());
		return "order/batchPrintExpressTemplate";
	}

	@RequestMapping("/printMainExpress")
	@ResponseBody
	public String printMainExpress(String orderMainIds, ModelMap modelMap) throws Exception {
		if (StringUtils.isBlank(orderMainIds)) {
			modelMap.put("state", "1");
			modelMap.put("msg", "需打印的订单参数有误");
			return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
		}
		this.orderMainService.printExpress(orderMainIds);
		modelMap.put("state", "0");
		return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
	}

	@RequestMapping("/exportOrderList")
	public String exportOrderList(OrderMain queryCondition, HttpServletResponse response) throws Exception {
		List<OrderMain> orderMainList = orderMainService.queryOrderMainList(queryCondition, 0, Integer.MAX_VALUE);

		OutputStream outputStream = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet();
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("订单号");
			cell = row.createCell(1, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("券号");
			cell = row.createCell(2, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("订单金额");
			cell = row.createCell(3, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("收货人姓名");
			cell = row.createCell(4, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("电话");

			for (int i = 0; i < orderMainList.size(); i++) {
				OrderMain orderMain = orderMainList.get(i);
				row = sheet.createRow(i + 1);
				cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(orderMain.getOrderMainNo());
				cell = row.createCell(1, HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(orderMain.getCouponNumber());
				cell = row.createCell(2, HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(orderMain.getPayAmount());
				cell = row.createCell(3, HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(orderMain.getConsignee());
				cell = row.createCell(4, HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(orderMain.getMobilePhone());
			}

			response.reset();
			response.setContentType("application/x-msdownload ");
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String fileName = "订单明细单" + simpleDateFormat.format(new Date()) + ".xls";
			String encodeFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment;filename=" + encodeFileName);
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
		} catch (Exception e) {
			logger.error("导出订单列表失败", e);
		} finally {
			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}
		}
		return null;
	}

}
