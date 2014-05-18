package com.ejunhai.junhaimall.web;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

import com.ejunhai.junhaimall.coupon.constant.CouponConstant;
import com.ejunhai.junhaimall.coupon.model.Coupon;
import com.ejunhai.junhaimall.coupon.model.CouponScheme;
import com.ejunhai.junhaimall.coupon.service.ICouponSchemeService;
import com.ejunhai.junhaimall.coupon.service.ICouponService;
import com.ejunhai.junhaimall.framework.base.BaseController;
import com.ejunhai.junhaimall.framework.base.PageFinder;
import com.ejunhai.junhaimall.framework.base.Query;

/**
 * 优惠券管理controller
 * 
 * @author 罗正加
 * @history 2011-12-16 罗正加 新建
 */
@Controller
@RequestMapping("coupon")
public class CouponController extends BaseController {

	@Autowired
	private ICouponService couponService;

	@Autowired
	private ICouponSchemeService couponSchemeService;

	@RequestMapping("/couponList")
	public String couponList(Coupon coupon, Query query, ModelMap modelMap) throws Exception {
		int pageNo = query.getPage();
		int pageSize = query.getPageSize();
		int count = couponService.queryCouponCount(coupon);

		List<Coupon> couponList = new ArrayList<Coupon>(0);
		if (count > 0) {
			couponList = couponService.queryCouponList(coupon, pageNo, pageSize);
			PageFinder<Coupon> pageFinder = new PageFinder<Coupon>(pageNo, pageSize, count);
			pageFinder.setData(couponList);
			modelMap.addAttribute("pageFinder", pageFinder);
		}

		List<CouponScheme> couponSchemeList = couponSchemeService.queryCouponSchemeList(null, 0, Integer.MAX_VALUE);
		modelMap.addAttribute("coupon", coupon);
		modelMap.addAttribute("couponSchemeList", couponSchemeList);
		return "coupon/couponList";
	}

	@RequestMapping("/generateCoupon")
	public ModelAndView generateCoupon(String couponSchemeId, ModelMap modelMap) throws Exception {
		CouponScheme couponScheme = couponSchemeService.readCouponScheme(couponSchemeId);

		// 每次最多生成1000张优惠券
		int oneMaxGenerateNum = 1000;
		int canGenerateAmount = couponScheme.getIssueAmount() - couponScheme.getHasIssueNum();
		int generateNum = canGenerateAmount > oneMaxGenerateNum ? oneMaxGenerateNum : canGenerateAmount;
		couponService.batchGenerateCoupon(couponSchemeId, generateNum);
		return new ModelAndView(new RedirectView("/couponScheme/couponSchemeList.sc", true));
	}

	@RequestMapping("/discardCoupon")
	public ModelAndView discardCoupon(String couponId, ModelMap modelMap) throws Exception {
		Coupon coupon = new Coupon();
		coupon.setId(couponId);
		coupon.setState(CouponConstant.COUPON_STATE_DISCARD);
		this.couponService.updateCoupon(coupon);
		return new ModelAndView(new RedirectView("couponList.sc"));
	}
	
	@RequestMapping("/activateCoupon")
	public ModelAndView activateCoupon(String couponId, ModelMap modelMap) throws Exception {
		Coupon coupon = new Coupon();
		coupon.setId(couponId);
		coupon.setState(CouponConstant.COUPON_STATE_ACTIVATE);
		this.couponService.updateCoupon(coupon);
		return new ModelAndView(new RedirectView("couponList.sc"));
	}
	
	
	@RequestMapping("/disturbCoupon")
	public ModelAndView disturbCoupon(String couponSchemeId, ModelMap modelMap) throws Exception {
		this.couponService.disturbCoupon(couponSchemeId);
		return new ModelAndView(new RedirectView("/couponScheme/couponSchemeList.sc",true));
	}

	@RequestMapping("/checkCoupon")
	@ResponseBody
	public String checkCoupon(String couponNumber, String couponPassword) throws Exception {
		String msg[] = this.couponService.checkCoupon(couponNumber, couponPassword);
		return msg[0].equals("0") ? "0" : msg[1];
	}

	@RequestMapping("/exportCoupon")
	public String exportCoupon(String couponSchemeId, HttpServletResponse response) throws Exception {
		CouponScheme couponScheme = couponSchemeService.readCouponScheme(couponSchemeId);
		Coupon queryCoupon = new Coupon();
		queryCoupon.setCouponSchemeId(couponSchemeId);
		List<Coupon> couponList = this.couponService.queryCouponList(queryCoupon, 0, Integer.MAX_VALUE);

		OutputStream outputStream = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet();
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("优惠券码");
			cell = row.createCell(1, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("优惠券密码");
			cell = row.createCell(2, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("开始日期");
			cell = row.createCell(3, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("结束日期");
			cell = row.createCell(4, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("状态");

			for (int i = 0; i < couponList.size(); i++) {
				Coupon coupon = couponList.get(i);
				row = sheet.createRow(i + 1);
				cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(coupon.getCouponNumber());
				cell = row.createCell(1, HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(coupon.getCouponPassword());
				cell = row.createCell(2, HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(simpleDateFormat.format(coupon.getUseStartdate()));
				cell = row.createCell(3, HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(simpleDateFormat.format(coupon.getUseEnddate()));
				cell = row.createCell(4, HSSFCell.CELL_TYPE_STRING);
				if (coupon.getState() == CouponConstant.COUPON_STATE_ACTIVATE) {
					cell.setCellValue("未使用");
				} else if (coupon.getState() == CouponConstant.COUPON_STATE_USED) {
					cell.setCellValue("已使用");
				} else if (coupon.getState() == CouponConstant.COUPON_STATE_EXPIRE) {
					cell.setCellValue("已过期");
				} else if (coupon.getState() == CouponConstant.COUPON_STATE_DISCARD) {
					cell.setCellValue("已作废");
				}
			}

			response.reset();
			response.setContentType("application/x-msdownload ");
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String fileName = couponScheme.getCouponName() + "明细单" + simpleDateFormat.format(new Date()) + ".xls";
			String encodeFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment;filename=" + encodeFileName);
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
		} catch (Exception e) {
			logger.error("导出优惠券失败", e);
		} finally {
			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}
		}
		return null;
	}
}
