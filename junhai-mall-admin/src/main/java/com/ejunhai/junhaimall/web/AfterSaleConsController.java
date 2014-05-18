package com.ejunhai.junhaimall.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ejunhai.junhaimall.aftersale.model.AfterSaleCons;
import com.ejunhai.junhaimall.aftersale.service.IAfterSaleConsService;
import com.ejunhai.junhaimall.framework.base.BaseController;
import com.ejunhai.junhaimall.framework.base.PageFinder;
import com.ejunhai.junhaimall.framework.base.Query;

/**
 * 售后留言管理controller
 * 
 * @author 吴阳
 * @history 2012-6-25 吴阳 新建
 */
@Controller
@RequestMapping("afterSaleCons")
public class AfterSaleConsController extends BaseController {

	@Autowired
	private IAfterSaleConsService afterSaleConsService;

	/**
	 * 留言管理列表
	 * 
	 * @param afterSaleCons
	 * @param query
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/afterSaleConsList")
	public String afterSaleConsList(AfterSaleCons afterSaleCons, Query query, ModelMap modelMap) throws Exception {
		int pageNo = query.getPage();
		int pageSize = query.getPageSize();
		int count = afterSaleConsService.queryAfterSaleConsCount(afterSaleCons);

		List<AfterSaleCons> lstAfterSaleCons = new ArrayList<AfterSaleCons>(0);
		if (count > 0) {
			lstAfterSaleCons = afterSaleConsService.queryAfterSaleConsList(afterSaleCons, pageNo, pageSize);
		}
		PageFinder<AfterSaleCons> pageFinder = new PageFinder<AfterSaleCons>(pageNo, pageSize, count);
		pageFinder.setData(lstAfterSaleCons);

		modelMap.addAttribute("pageFinder", pageFinder);
		modelMap.addAttribute("afterSaleCons", afterSaleCons);
		return "afterSale/afterSaleList";
	}

	/**
	 * 去留言回复
	 * 
	 * @param orderMainNo
	 * @param type
	 *            1:查看
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toAfterSaleConsReply")
	public String toAfterSaleConsReply(String id, String type, ModelMap modelMap) throws Exception {
		AfterSaleCons afterSaleCons = this.afterSaleConsService.readAfterSaleCons(id);

		AfterSaleCons queryAfterSaleCons = new AfterSaleCons();
		queryAfterSaleCons.setOrderMainNo(afterSaleCons.getOrderMainNo());
		List<AfterSaleCons> lstAfterSaleCons = afterSaleConsService.queryAfterSaleConsList(queryAfterSaleCons, 0,
				Integer.MAX_VALUE);

		modelMap.addAttribute("lstAfterSaleCons", lstAfterSaleCons);
		modelMap.addAttribute("afterSaleCons", afterSaleCons);
		modelMap.addAttribute("type", type);
		return "afterSale/toAfterSaleConsReply";
	}

	/**
	 * 回复留言
	 * 
	 * @param orderMainNo
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doAfterSaleConsReply")
	public ModelAndView doAfterSaleConsReply(AfterSaleCons afterSaleCons, ModelMap modelMap) throws Exception {
		afterSaleConsService.doAfterSaleConsReply(afterSaleCons);
		return new ModelAndView(new RedirectView("/afterSaleCons/toAfterSaleConsReply.sc?id="
				+ afterSaleCons.getId(),true));
	}

	/**
	 * 客户留言
	 * 
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doAfterSaleCons")
	public ModelAndView doAfterSaleCons(AfterSaleCons afterSaleCons, ModelMap modelMap) throws Exception {
		Assert.notNull(afterSaleCons, "afterSaleCons is null");
		Assert.notNull(afterSaleCons.getConsultation(), "consultation is null");
		Assert.notNull(afterSaleCons.getOrderMainNo(), "orderMainNo is null");
		afterSaleConsService.insertAfterSaleCons(afterSaleCons);
		return new ModelAndView(new RedirectView("/afterSaleCons/toAfterSaleConsReply.sc?id="
				+ afterSaleCons.getId(),true));
	}
}
