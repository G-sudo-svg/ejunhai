package com.ejunhai.junhaimall.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.ejunhai.junhaimall.order.constant.OrderConstant;
import com.ejunhai.junhaimall.order.model.OrderMain;
import com.ejunhai.junhaimall.order.model.OrderRepl;
import com.ejunhai.junhaimall.order.service.IOrderMainService;
import com.ejunhai.junhaimall.order.service.IOrderReplService;
import com.ejunhai.junhaimall.order.util.OrderUtil;
import com.ejunhai.junhaimall.system.model.Config;
import com.ejunhai.junhaimall.system.service.IConfigService;

/**
 * 优惠券管理controller
 * 
 * @author 罗正加
 * @history 2011-12-16 罗正加 新建
 */
@Controller
@RequestMapping("order")
public class OrderReplController extends BaseController {

    @Autowired
    private IOrderReplService orderReplService;

    @Autowired
    private IOrderMainService orderMainService;

    @Autowired
    private ICouponService couponService;

    @Autowired
    private ICouponSchemeService couponSchemeService;

    @Autowired
    private IConfigService configService;

    @RequestMapping("/orderReplList")
    public String orderReplList(OrderRepl orderRepl, Query query, ModelMap modelMap) throws Exception {
        int pageNo = query.getPage();
        int pageSize = query.getPageSize();
        int count = orderReplService.queryOrderReplCount(orderRepl);

        List<OrderRepl> orderReplList = new ArrayList<OrderRepl>(0);
        if (count > 0) {
            orderReplList = orderReplService.queryOrderReplList(orderRepl, pageNo, pageSize);
        }
        PageFinder<OrderRepl> pageFinder = new PageFinder<OrderRepl>(pageNo, pageSize, count);
        pageFinder.setData(orderReplList);

        modelMap.addAttribute("pageFinder", pageFinder);
        modelMap.addAttribute("orderRepl", orderRepl);
        return "order/orderReplList";
    }

    @RequestMapping("/toOrderRepl")
    public String toOrderMain(String orderReplId, ModelMap modelMap) throws Exception {
        if (StringUtils.isNotBlank(orderReplId)) {
            OrderRepl orderRepl = orderReplService.readOrderRepl(orderReplId);
            modelMap.addAttribute("orderRepl", orderRepl);
            Coupon coupon = couponService.getCouponByOrderNumber(orderRepl.getOrderMainNo());
            modelMap.addAttribute("coupon", coupon);
            CouponScheme couponScheme = couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
            modelMap.addAttribute("couponScheme", couponScheme);
        }
        return "order/editOrderRepl";
    }

    @RequestMapping("/toAddOrderRepl")
    public String toAddOrderRepl(String orderMainNo, ModelMap modelMap) throws Exception {
        OrderMain orderMain = this.orderMainService.getOrdermainByOrderMainNo(orderMainNo);
        modelMap.addAttribute("orderMain", orderMain);
        Coupon coupon = couponService.getCouponByOrderNumber(orderMain.getOrderMainNo());
        modelMap.addAttribute("coupon", coupon);
        CouponScheme couponScheme = couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
        modelMap.addAttribute("couponScheme", couponScheme);
        return "order/addOrderRepl";
    }

    @RequestMapping("/addOrderRepl")
    public ModelAndView addOrderRepl(String orderMainId, OrderRepl orderRepl, ModelMap modelMap) throws Exception {
        OrderMain orderMain = this.orderMainService.readOrderMain(orderMainId);
        this.orderReplService.createOrderRepl(orderMain, orderRepl);
        return new ModelAndView(new RedirectView("/order/orderReplList.sc?state=0",true));
    }

    @RequestMapping("/toDeliverOrderRepl")
    public String toDeliverOrderRepl(String orderReplId, ModelMap modelMap) throws Exception {
        if (StringUtils.isNotBlank(orderReplId)) {
            OrderRepl orderRepl = orderReplService.readOrderRepl(orderReplId);
            modelMap.addAttribute("orderRepl", orderRepl);
            OrderMain orderMain = orderMainService.getOrdermainByOrderMainNo(orderRepl.getOrderMainNo());
            modelMap.addAttribute("orderMain", orderMain);
            Coupon coupon = couponService.getCouponByOrderNumber(orderRepl.getOrderMainNo());
            modelMap.addAttribute("coupon", coupon);
            CouponScheme couponScheme = couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
            modelMap.addAttribute("couponScheme", couponScheme);
        }
        List<Map<String, String>> logisticCompanyList = OrderUtil.getLogisticCompanyList();
        modelMap.addAttribute("logisticCompanyList", logisticCompanyList);
        return "order/deliverOrderRepl";
    }

    @RequestMapping("/updateConsigneeInfo")
    public ModelAndView changeConsigneeInfo(OrderRepl orderRepl, ModelMap modelMap) throws Exception {
        if (StringUtils.isBlank(orderRepl.getAreaCode()) || StringUtils.isBlank(orderRepl.getDetailAddress())) {
            throw new Exception("发货地址信息有错");
        }
        orderReplService.changeConsigneeInfo(orderRepl);
        return new ModelAndView(new RedirectView("/order/orderReplList.sc?state=0",true));
    }

    @RequestMapping("/deliverOrderRepl")
    public ModelAndView deliverOrderMain(OrderRepl orderRepl, ModelMap modelMap) throws Exception {
        if (StringUtils.isBlank(orderRepl.getExpressOrderNo()) || StringUtils.isBlank(orderRepl.getMobilePhone())) {
            throw new Exception("发货信息有错");
        }

        // 根据物流公司编码获取物流公司名称
        if (StringUtils.isNotBlank(orderRepl.getLogisticsCompanyCode())) {
            orderRepl.setLogisticsCompany(OrderUtil.getLogisticCompany(orderRepl.getLogisticsCompanyCode()));
        }

        this.orderReplService.deliverOrderRepl(orderRepl);
        return new ModelAndView(new RedirectView("/order/orderReplList.sc?state=0",true));
    }

    @RequestMapping("/toPrintReplExpress")
    public String toPrintReplExpress(String orderReplId, ModelMap modelMap) throws Exception {
        OrderRepl orderRepl = orderReplService.readOrderRepl(orderReplId);
        modelMap.put("orderRepl", orderRepl);

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

    @RequestMapping("/toBatchPrintReplExpress")
    public String toBatchPrintMainExpress(String orderReplIds, int state, ModelMap modelMap) throws Exception {
        if (StringUtils.isBlank(orderReplIds)) {
            logger.error("需打印的补货订单号不能为空");
        }

        // 查询需要打印的快递单数据
        String[] arrOrderReplId = orderReplIds.split(",");
        List<OrderRepl> orderReplList = new ArrayList<OrderRepl>(arrOrderReplId.length);
        for (String orderReplId : arrOrderReplId) {
            OrderRepl orderRepl = orderReplService.readOrderRepl(orderReplId);
            if (orderRepl != null) {
                orderReplList.add(orderRepl);
            }
        }
        modelMap.put("state", state);
        modelMap.put("orderReplIds", orderReplIds);
        modelMap.put("orderList", orderReplList);

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

    @RequestMapping("/printReplExpress")
    @ResponseBody
    public String printExpress(String orderReplIds, ModelMap modelMap) throws Exception {
        if (StringUtils.isBlank(orderReplIds)) {
            modelMap.put("state", "1");
            modelMap.put("msg", "需打印的订单参数有误");
            return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
        }
        this.orderReplService.printExpress(orderReplIds);
        modelMap.put("state", "0");
        return FreeMarkerTemplateHelper.parseTemplateToJson("common/state.ftl", modelMap);
    }
}
