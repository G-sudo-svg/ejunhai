package com.ejunhai.junhaimall.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ejunhai.junhaimall.aftersale.constant.AfterSaleConstant;
import com.ejunhai.junhaimall.aftersale.model.AfterSaleRequ;
import com.ejunhai.junhaimall.aftersale.service.IAfterSaleRequService;
import com.ejunhai.junhaimall.coupon.model.Coupon;
import com.ejunhai.junhaimall.coupon.model.CouponScheme;
import com.ejunhai.junhaimall.coupon.service.ICouponSchemeService;
import com.ejunhai.junhaimall.coupon.service.ICouponService;
import com.ejunhai.junhaimall.framework.base.BaseController;
import com.ejunhai.junhaimall.framework.base.PageFinder;
import com.ejunhai.junhaimall.framework.base.Query;
import com.ejunhai.junhaimall.order.model.OrderMain;
import com.ejunhai.junhaimall.order.service.IOrderMainService;

/**
 * 售后申请管理controller
 * 
 * @author 吴阳
 * @history 2012-7-1 吴阳 新建
 */
@Controller
@RequestMapping("afterSaleRequ")
public class AfterSaleRequController extends BaseController {

	@Autowired
	private IAfterSaleRequService afterSaleRequService;

	@Autowired
	private IOrderMainService orderMainService;

	@Autowired
	private ICouponService couponService;

	@Autowired
	private ICouponSchemeService couponSchemeService;

	/**
	 * 售后申请管理列表
	 * 
	 * @param afterSaleRequ
	 * @param query
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/afterSaleRequList")
	public String afterSaleRequList(AfterSaleRequ afterSaleRequ, Query query, ModelMap modelMap) throws Exception {
		int pageNo = query.getPage();
		int pageSize = query.getPageSize();
		int count = afterSaleRequService.queryAfterSaleRequCount(afterSaleRequ);

		List<AfterSaleRequ> lstAfterSaleRequ = new ArrayList<AfterSaleRequ>(0);
		if (count > 0) {
			lstAfterSaleRequ = afterSaleRequService.queryAfterSaleRequList(afterSaleRequ, pageNo, pageSize);
		}
		PageFinder<AfterSaleRequ> pageFinder = new PageFinder<AfterSaleRequ>(pageNo, pageSize, count);
		pageFinder.setData(lstAfterSaleRequ);

		modelMap.addAttribute("pageFinder", pageFinder);
		modelMap.addAttribute("afterSaleRequ", afterSaleRequ);
		return "afterSale/afterSaleRequList";
	}

	@RequestMapping("/toAfterSaleRequ")
	public String toAfterSaleRequ(String afterSaleRequId, ModelMap modelMap) throws Exception {
		AfterSaleRequ afterSaleRequ = this.afterSaleRequService.readAfterSaleRequ(afterSaleRequId);
		OrderMain orderMain = orderMainService.getOrdermainByOrderMainNo(afterSaleRequ.getOrderMainNo());
		Coupon coupon = couponService.getCouponByOrderNumber(orderMain.getOrderMainNo());
		CouponScheme couponScheme = couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
		modelMap.addAttribute("afterSaleRequ", afterSaleRequ);
		modelMap.addAttribute("orderMain", orderMain);
		modelMap.addAttribute("couponScheme", couponScheme);
		return "afterSale/editAfterSaleRequ";
	}

	@RequestMapping("/refuseAfterSaleRequ")
	public ModelAndView refuseAfterSaleRequ(AfterSaleRequ afterSaleRequ, ModelMap modelMap) throws Exception {
		afterSaleRequ.setState(AfterSaleConstant.AFTER_SALE_REQU_REFUSE);
		afterSaleRequ.setDealTime(new Date());
		afterSaleRequService.updateAfterSaleRequ(afterSaleRequ);
		return new ModelAndView(new RedirectView("/afterSaleRequ/afterSaleRequList.sc",true));
	}
}
