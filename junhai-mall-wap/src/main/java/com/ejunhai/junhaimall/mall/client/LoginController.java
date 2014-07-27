package com.ejunhai.junhaimall.mall.client;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejunhai.junhaimall.coupon.constant.CouponConstant;
import com.ejunhai.junhaimall.coupon.model.Coupon;
import com.ejunhai.junhaimall.coupon.model.CouponScheme;
import com.ejunhai.junhaimall.coupon.service.ICouponSchemeService;
import com.ejunhai.junhaimall.coupon.service.ICouponService;
import com.ejunhai.junhaimall.framework.base.BaseController;
import com.ejunhai.junhaimall.framework.constant.Constant;
import com.ejunhai.junhaimall.framework.util.Md5Encrypt;
import com.ejunhai.junhaimall.mall.util.LoginUtil;

/**
 * Login Controller
 * 
 * @author parcel
 * @history 2014-04-29 parcel 新建
 */
@Controller
@RequestMapping("")
public class LoginController extends BaseController {

    @Autowired
    private ICouponService couponService;

    @Autowired
    private ICouponSchemeService couponSchemeService;

    private static final String COOKIE_COUPON_NUMBER = "coupon_number";

    @RequestMapping("/index")
    public String index(HttpServletRequest request, ModelMap modelMap) throws Exception {
        // 从cookie中获取couponNumber
        Cookie[] cookies = request.getCookies();
        String couponNumber = null;
        for (Cookie cookie : cookies) {
            if (COOKIE_COUPON_NUMBER.equals(cookie.getName())) {
                couponNumber = cookie.getValue();
            }
        }

        modelMap.put("couponNumber", couponNumber);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(String couponNumber, String couponPassword, String validateCode, HttpServletRequest request,
            HttpServletResponse response) {
        String sValidateCode = (String) request.getSession().getAttribute(Constant.LOGIN_VALIDATE_IMAGE);
        if (StringUtils.isBlank(couponNumber) || StringUtils.isBlank(couponPassword)) {
            return LoginUtil.validateResponseJson(false, "优惠券号码无效");
        }

        // 验证验证码
        if (StringUtils.isBlank(validateCode) || !validateCode.equals(sValidateCode)) {
            return LoginUtil.validateResponseJson(false, "验证码无效");
        }

        // 无效礼品券处理
        Coupon coupon = couponService.getCouponByNo(couponNumber);
        if (coupon == null || coupon.getState() == CouponConstant.COUPON_STATE_DISCARD) {
            return LoginUtil.validateResponseJson(false, "优惠券号码无效");
        }

        // 由于客户端过来时使用md5加密，要根据服务端是否扰乱来判断礼品卡的有效性
        String realCouponPassword = coupon.getCouponPassword();
        CouponScheme couponScheme = couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
        if (couponScheme.getHasDisturb() == CouponConstant.COUPON_PASSWORD_DISTURB_NO) {
            realCouponPassword = Md5Encrypt.md5(realCouponPassword);
        }

        // 验证礼品券和密码
        if (!couponPassword.equalsIgnoreCase(realCouponPassword)) {
            return LoginUtil.validateResponseJson(false, "优惠券密码无效");
        }

        // 礼品券验证通过，保存至session中
        request.getSession().setAttribute(LoginUtil.LOGIN_USER, coupon);
        Cookie cookie = new Cookie(COOKIE_COUPON_NUMBER, couponNumber);
        cookie.setMaxAge(60 * 60 * 24 * 120);
        cookie.setPath("/");
        response.addCookie(cookie);

        return LoginUtil.validateResponseJson(true, "");
    }

    @RequestMapping("/dispatchCenter")
    public String dispatchCenter(ModelMap modelMap, HttpServletRequest request) {
        // 验证用户是否登录
        Coupon coupon = LoginUtil.getLoginUser(request);
        if (coupon == null) {
            return "index";
        }

        // 如果礼品券已使用则跳转到用户主页面
        modelMap.put("coupon", coupon);
        if (coupon.getState() == CouponConstant.COUPON_STATE_USED) {
            return "profile";
        }

        // 礼品券不可用状态跳转至notice页面
        if (coupon.getState().intValue() != CouponConstant.COUPON_STATE_ACTIVATE
                || coupon.getUseEnddate().before(new Date())) {
            modelMap.put("curTime", new Date());
            return "notice";
        }

        // 礼品券未使用则跳转到下单预约页面
        return "redirect:toSubscribe.jhtml";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest req) {
        LoginUtil.logout(req);
        return "redirect:index.jhtml";
    }
}
