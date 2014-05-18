package com.ejunhai.junhaimall.mall.client;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ejunhai.junhaimall.aftersale.model.AfterSaleCons;
import com.ejunhai.junhaimall.aftersale.model.AfterSaleRequ;
import com.ejunhai.junhaimall.aftersale.service.IAfterSaleConsService;
import com.ejunhai.junhaimall.aftersale.service.IAfterSaleRequService;
import com.ejunhai.junhaimall.coupon.model.Coupon;
import com.ejunhai.junhaimall.framework.base.BaseController;
import com.ejunhai.junhaimall.mall.util.LoginUtil;
import com.ejunhai.junhaimall.order.constant.OrderConstant;
import com.ejunhai.junhaimall.order.model.OrderLog;
import com.ejunhai.junhaimall.order.model.OrderMain;
import com.ejunhai.junhaimall.order.model.OrderRepl;
import com.ejunhai.junhaimall.order.service.IOrderLogService;
import com.ejunhai.junhaimall.order.service.IOrderMainService;
import com.ejunhai.junhaimall.order.service.IOrderReplService;
import com.ejunhai.junhaimall.system.model.Config;
import com.ejunhai.junhaimall.system.service.IConfigService;

/**
 * after sale Controller
 * 
 * @author parcel
 * @history 2014-05-04 parcel 新建
 */
@Controller
@RequestMapping("")
public class AfterSaleController extends BaseController {

	@Autowired
	private IOrderMainService orderMainService;

	@Autowired
	private IAfterSaleConsService afterSaleConsService;

	@Autowired
	private IAfterSaleRequService afterSaleRequService;

	@Autowired
	private IOrderLogService orderLogService;

	@Autowired
	private IOrderReplService orderReplService;

	@Autowired
	private IConfigService configService;

	@RequestMapping("/logistics")
	public String toLogistics(ModelMap modelMap, HttpServletRequest request) {
		Coupon coupon = LoginUtil.getLoginUser(request);
		if (coupon == null) {
			return "index";
		}

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
		if (CollectionUtils.isNotEmpty(orderReplList)) {
			OrderRepl orderRepl = orderReplList.get(0);
			orderLog = new OrderLog();
			orderLog.setOrderNo(orderRepl.getOrderReplNo());
			orderRepl.setOrderLogList(orderLogService.queryOrderLogList(orderLog, 0, Integer.MAX_VALUE));
			modelMap.addAttribute("orderRepl", orderRepl);
		}

		return "logistics";
	}

	@RequestMapping("/afterSaleCons")
	public String toAfterSaleCons(ModelMap modelMap, HttpServletRequest request) {
		Coupon coupon = LoginUtil.getLoginUser(request);
		if (coupon == null) {
			return "index";
		}

		OrderMain orderMain = orderMainService.getOrdermainByOrderMainNo(coupon.getUseOrderNumber());
		AfterSaleCons queryAfterSaleCons = new AfterSaleCons();
		queryAfterSaleCons.setOrderMainNo(orderMain.getOrderMainNo());
		List<AfterSaleCons> afterSaleConsList = null;
		afterSaleConsList = this.afterSaleConsService.queryAfterSaleConsList(queryAfterSaleCons, 0, Integer.MAX_VALUE);
		modelMap.addAttribute("afterSaleConsList", afterSaleConsList);
		modelMap.addAttribute("orderMain", orderMain);
		return "remark";
	}

	@RequestMapping(value = "/submitRemark", method = RequestMethod.POST)
	public String saveRemark(String consultation, HttpServletRequest request) throws Exception {
		Coupon coupon = LoginUtil.getLoginUser(request);
		if (coupon == null) {
			return "index";
		}

		if (StringUtils.isNotBlank(consultation)) {
			AfterSaleCons afterSaleCons = new AfterSaleCons();
			afterSaleCons.setConsultation(consultation);
			afterSaleCons.setOrderMainNo(coupon.getUseOrderNumber());
			afterSaleConsService.insertAfterSaleCons(afterSaleCons);
		}

		return "redirect:afterSaleCons.jhtml";
	}

	@RequestMapping("/afterSaleRequ")
	public String toAfterSaleRequ(ModelMap modelMap, HttpServletRequest request) {
		Coupon coupon = LoginUtil.getLoginUser(request);
		if (coupon == null) {
			return "index";
		}

		OrderMain orderMain = orderMainService.getOrdermainByOrderMainNo(coupon.getUseOrderNumber());
		AfterSaleRequ afterSaleRequ = new AfterSaleRequ();
		afterSaleRequ.setOrderMainNo(orderMain.getOrderMainNo());
		List<AfterSaleRequ> afterSaleRequList = null;
		afterSaleRequList = afterSaleRequService.queryAfterSaleRequList(afterSaleRequ, 0, Integer.MAX_VALUE);
		if (afterSaleRequList.size() > 0) {
			modelMap.addAttribute("afterSaleRequ", afterSaleRequList.get(0));
		}
		modelMap.addAttribute("orderMain", orderMain);
		return "afterSale";
	}

	@RequestMapping("/savaAfterSaleRequ")
	public String afterSaleRequ(AfterSaleRequ afterSaleRequ, ModelMap modelMap, HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String orderMainNo = afterSaleRequ.getOrderMainNo();
		String description = afterSaleRequ.getDescription();

		// 系统参数出错,跳转至错误页面
		if (StringUtils.isBlank(orderMainNo) || StringUtils.isBlank(description)) {
			return "redirect:afterSaleRequ.jhtml";
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
		String defaultPicUrl = "http://www.ejunhai.com/uploads/" + currMonth;
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
		afterSaleRequService.insertAfterSaleRequ(afterSaleRequ);
		return "redirect:afterSaleRequ.jhtml";
	}
}
