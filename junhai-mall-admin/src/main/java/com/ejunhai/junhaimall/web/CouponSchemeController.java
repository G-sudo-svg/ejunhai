package com.ejunhai.junhaimall.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ejunhai.junhaimall.coupon.constant.CouponConstant;
import com.ejunhai.junhaimall.coupon.model.CouponScheme;
import com.ejunhai.junhaimall.coupon.service.ICouponSchemeService;
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
@RequestMapping("couponScheme")
public class CouponSchemeController extends BaseController {

	@Autowired
	private ICouponSchemeService couponSchemeService;

	@RequestMapping("/couponSchemeList")
	public String couponSchemeList(CouponScheme couponScheme, Query query, ModelMap modelMap) throws Exception {
		int pageNo = query.getPage();
		int pageSize = query.getPageSize();
		int count = couponSchemeService.queryCouponSchemeCount(couponScheme);

		if (count > 0) {
			List<CouponScheme> couponSchemeList = new ArrayList<CouponScheme>(0);
			couponSchemeList = couponSchemeService.queryCouponSchemeList(couponScheme, pageNo, pageSize);
			for (CouponScheme tempCouponScheme : couponSchemeList) {
				Date useStartdate = tempCouponScheme.getUseStartdate();
				Date useEnddate = tempCouponScheme.getUseEnddate();
				Date currSysTime = new Date();
				if (currSysTime.compareTo(useStartdate) < 0) {
					tempCouponScheme.setState(CouponConstant.COUPON_SCHEME_STATE_WAIT);
				} else if (currSysTime.compareTo(useStartdate) > 0 && currSysTime.compareTo(useEnddate) < 0) {
					tempCouponScheme.setState(CouponConstant.COUPON_SCHEME_STATE_OK);
				} else if (currSysTime.compareTo(useEnddate) > 0) {
					tempCouponScheme.setState(CouponConstant.COUPON_SCHEME_STATE_STOP);
				}
			}
			PageFinder<CouponScheme> pageFinder = new PageFinder<CouponScheme>(pageNo, pageSize, count);
			pageFinder.setData(couponSchemeList);
			modelMap.addAttribute("pageFinder", pageFinder);
		}

		modelMap.addAttribute("systime", new Date());
		modelMap.addAttribute("couponScheme", couponScheme);
		return "coupon/couponSchemeList";
	}

	@RequestMapping("/toCouponScheme")
	public String toCouponScheme(String couponSchemeId, String readOnly, ModelMap modelMap) throws Exception {
		if (StringUtils.isNotBlank(couponSchemeId)) {
			CouponScheme couponScheme = couponSchemeService.readCouponScheme(couponSchemeId);
			modelMap.addAttribute("readOnly", readOnly);
			modelMap.addAttribute("couponScheme", couponScheme);
		}
		return "coupon/editCouponScheme";
	}

	@RequestMapping("/updateCouponScheme")
	public ModelAndView updateCouponScheme(CouponScheme couponScheme, ModelMap modelMap) throws Exception {
		if (StringUtils.isNotBlank(couponScheme.getId())) {
			couponSchemeService.updateCouponScheme(couponScheme);
		} else {
			couponSchemeService.insertCouponScheme(couponScheme);
		}
		return new ModelAndView(new RedirectView("/couponScheme/couponSchemeList.sc", true));
	}

	@RequestMapping("/removeCouponScheme")
	public ModelAndView removeCouponScheme(String couponSchemeId, ModelMap modelMap) throws Exception {
		this.couponSchemeService.removeCouponScheme(couponSchemeId);
		return new ModelAndView(new RedirectView("/couponScheme/couponSchemeList.sc", true));
	}
}
