package com.ejunhai.junhaimall.coupon.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejunhai.junhaimall.coupon.constant.CouponConstant;
import com.ejunhai.junhaimall.coupon.dao.CouponMapper;
import com.ejunhai.junhaimall.coupon.model.Coupon;
import com.ejunhai.junhaimall.coupon.model.CouponScheme;
import com.ejunhai.junhaimall.coupon.model.CouponUseDataVo;
import com.ejunhai.junhaimall.coupon.service.ICouponSchemeService;
import com.ejunhai.junhaimall.coupon.service.ICouponService;
import com.ejunhai.junhaimall.coupon.util.CouponUtil;
import com.ejunhai.junhaimall.framework.base.KeyGenerator;
import com.ejunhai.junhaimall.framework.core.BaseSpringService;
import com.ejunhai.junhaimall.framework.util.Md5Encrypt;

/**
 * 优惠券服务service
 * 
 * @author 罗正加
 * @history 2011-12-12 罗正加 新建
 */
@Service
public class CouponServiceImpl extends BaseSpringService implements ICouponService {

	@Autowired
	private CouponMapper couponMapper;

	@Autowired
	private ICouponSchemeService couponSchemeService;

	@Override
	public List<Coupon> queryCouponList(Coupon coupon, int pageNo, int pageSize) {
		RowBounds rowBounds = new RowBounds((pageNo - 1) * pageSize, pageSize);
		return this.couponMapper.queryCouponList(coupon, rowBounds);
	}

	@Override
	public int queryCouponCount(Coupon coupon) {
		return this.couponMapper.queryCouponCount(coupon);
	}

	@Override
	public Coupon readCoupon(String couponId) {
		return this.couponMapper.readCoupon(couponId);
	}

	@Override
	public Coupon getCouponByNo(String couponNumber) {
		return this.couponMapper.getCouponByNo(couponNumber);
	}

	@Override
	public Coupon getCouponByOrderNumber(String orderNumber) {
		return this.couponMapper.getCouponByOrderNumber(orderNumber);
	}

	@Override
	@Transactional
	public void updateCoupon(Coupon coupon) {
		this.couponMapper.updateCoupon(coupon);
	}

	@Override
	@Transactional
	public List<Coupon> batchGenerateCoupon(String couponSchemeId, int num) throws Exception {
		CouponScheme couponScheme = this.couponSchemeService.readCouponScheme(couponSchemeId);
		if (couponScheme == null) {
			throw new Exception("无法生成优惠券，系统不存在该优惠券模板" + couponSchemeId);
		}

		List<Coupon> couponList = new ArrayList<Coupon>(num);
		for (int i = 0; i < num; i++) {
			Coupon coupon = this.generateCoupon(couponScheme);
			couponList.add(coupon);
		}
		couponScheme.setHasIssueNum(couponScheme.getHasIssueNum() + num);
		couponSchemeService.updateCouponScheme(couponScheme);
		return couponList;
	}

	@Override
	public void disturbCoupon(String couponSchemeId) throws Exception {
		CouponScheme couponScheme = couponSchemeService.readCouponScheme(couponSchemeId);
		if (couponScheme.getHasDisturb() == CouponConstant.COUPON_PASSWORD_DISTURB_NO) {
			couponMapper.disturbCoupon(couponSchemeId);
			couponScheme.setHasDisturb(CouponConstant.COUPON_PASSWORD_DISTURB_YES);
			couponSchemeService.updateCouponScheme(couponScheme);
		}
	}

	/**
	 * @param couponScheme
	 * @return
	 */
	private Coupon generateCoupon(CouponScheme couponScheme) {
		Coupon coupon = new Coupon();
		coupon.setId(KeyGenerator.getUUID());
		coupon.setCouponNumber(CouponUtil.generateCouponNumber());
		coupon.setCouponPassword(CouponUtil.generateCouponPassword());
		coupon.setCouponSchemeId(couponScheme.getId());
		if (couponScheme.getInitActivate() == CouponConstant.COUPON_INIT_ACTIVFATE_YES) {
			coupon.setState(CouponConstant.COUPON_STATE_ACTIVATE);
		} else {
			coupon.setState(CouponConstant.COUPON_STATE_NO_ACTIVATE);
		}
		coupon.setUseStartdate(couponScheme.getUseStartdate());
		coupon.setUseEnddate(couponScheme.getUseEnddate());
		coupon.setGenerateTime(new Date(System.currentTimeMillis()));
		this.couponMapper.insertCoupon(coupon);
		return coupon;
	}

	@Override
	public String[] checkCoupon(String couponNumber, String couponPassword) {
		Coupon coupon = this.couponMapper.getCouponByNo(couponNumber);
		if (coupon == null) {
			return new String[] { "1", "抱歉，该礼品卡无效" };
		}

		CouponScheme couponScheme = this.couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
		if (couponScheme == null) {
			logger.error("未找到该礼品券:" + couponNumber + "相关方案信息");
			return new String[] { "1", "抱歉，该礼品卡无效" };
		}

		// 优惠券密码是否扰乱
		String realPassword = coupon.getCouponPassword();
		if (couponScheme.getHasDisturb() == CouponConstant.COUPON_PASSWORD_DISTURB_NO) {
			realPassword = Md5Encrypt.md5(coupon.getCouponPassword());
		}

		// 验证账号密码
		if (!realPassword.equalsIgnoreCase(couponPassword)) {
			return new String[] { "1", "抱歉，该礼品卡密码不对" };
		}

		// 验证礼品券状态
		if (coupon.getState() == CouponConstant.COUPON_STATE_NO_ACTIVATE) {
			return new String[] { "1", "抱歉，该礼品卡未激活" };
		}
		
		if (coupon.getState() == CouponConstant.COUPON_STATE_USED) {
			return new String[] { "1", "抱歉，该礼品卡已使用" };
		}

		if (coupon.getState() == CouponConstant.COUPON_STATE_EXPIRE) {
			return new String[] { "1", "抱歉，该礼品卡已过期" };
		}

		if (coupon.getState() == CouponConstant.COUPON_STATE_DISCARD) {
			return new String[] { "1", "抱歉，该礼品卡已作废" };
		}

		// 验证有效期
		if (coupon.getUseStartdate().after(new Date())) {
			return new String[] { "1", "抱歉，该礼品卡未到使用期" };
		}

		if (coupon.getUseEnddate().before(new Date())) {
			return new String[] { "1", "抱歉，该礼品卡已过期" };
		}

		return new String[] { "0", "" };
	}

	@Override
	@Transactional
	public Coupon useCoupon(String couponNumber, String useOrderNumber) throws Exception {
		Coupon coupon = this.couponMapper.getCouponByNo(couponNumber);
		if (coupon == null) {
			throw new Exception("无法使用优惠券，系统不存在该优惠券" + couponNumber);
		}

		coupon.setUseOrderNumber(useOrderNumber);
		coupon.setUseTime(new Date(System.currentTimeMillis()));
		this.updateCoupon(coupon);

		CouponScheme couponScheme = this.couponSchemeService.readCouponScheme(coupon.getCouponSchemeId());
		couponScheme.setHasUseNum(couponScheme.getHasUseNum() + 1);
		this.couponSchemeService.updateCouponScheme(couponScheme);
		return coupon;
	}
	
	@Override
	public int usedCouponCountByDate(CouponUseDataVo couponUseDataVo)
			throws Exception {
		return couponMapper.usedCouponCountByDate(couponUseDataVo);
	}
}
