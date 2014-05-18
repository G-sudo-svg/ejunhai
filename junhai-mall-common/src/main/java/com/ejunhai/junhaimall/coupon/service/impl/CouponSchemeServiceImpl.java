package com.ejunhai.junhaimall.coupon.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejunhai.junhaimall.coupon.dao.CouponSchemeMapper;
import com.ejunhai.junhaimall.coupon.model.CouponScheme;
import com.ejunhai.junhaimall.coupon.service.ICouponSchemeService;
import com.ejunhai.junhaimall.framework.base.KeyGenerator;
import com.ejunhai.junhaimall.framework.core.BaseSpringService;

/**
 * 优惠券模板服务service
 * 
 * @author 罗正加
 * @history 2011-12-12 罗正加 新建
 */
@Service
public class CouponSchemeServiceImpl extends BaseSpringService implements ICouponSchemeService {
	@Autowired
	private CouponSchemeMapper couponSchemeMapper;

	@Override
	public List<CouponScheme> queryCouponSchemeList(CouponScheme couponScheme, int pageNo, int pageSize) {
		RowBounds rowBounds = new RowBounds((pageNo - 1) * pageSize, pageSize - 1);
		return couponSchemeMapper.queryCouponSchemeList(couponScheme, rowBounds);
	}

	@Override
	public Integer queryCouponSchemeCount(CouponScheme couponScheme) {
		return this.couponSchemeMapper.queryCouponSchemeCount(couponScheme);
	}

	@Override
	public CouponScheme readCouponScheme(String couponSchemeId) {
		return this.couponSchemeMapper.readCouponScheme(couponSchemeId);
	}

	@Override
	public CouponScheme insertCouponScheme(CouponScheme couponScheme) {
		couponScheme.setId(KeyGenerator.getUUID());
		couponScheme.setCreateTime(new Date(System.currentTimeMillis()));
		this.couponSchemeMapper.insertCouponScheme(couponScheme);
		return couponScheme;
	}

	@Override
	public void updateCouponScheme(CouponScheme couponScheme) {
		this.couponSchemeMapper.updateCouponScheme(couponScheme);
	}

	@Override
	public void removeCouponScheme(String couponSchemeId) {
		this.couponSchemeMapper.removeCouponScheme(couponSchemeId);
	}
}
