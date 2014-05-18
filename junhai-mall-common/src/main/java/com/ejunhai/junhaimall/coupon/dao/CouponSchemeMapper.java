package com.ejunhai.junhaimall.coupon.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.ejunhai.junhaimall.coupon.model.CouponScheme;

/**
 * 优惠券模板服务接口
 * 
 * @author 罗正加
 * @history 2012-06-24 罗正加 新建
 */
public interface CouponSchemeMapper {

	/**
	 * 获取优惠券模板列表
	 * 
	 * @param couponScheme
	 * @param rowBounds
	 * @return
	 */
	public List<CouponScheme> queryCouponSchemeList(CouponScheme couponScheme, RowBounds rowBounds);

	/**
	 * 获取优惠券模板数量
	 * 
	 * @param couponScheme
	 * @return
	 */
	public Integer queryCouponSchemeCount(CouponScheme couponScheme);

	/**
	 * 读取优惠券模板
	 * 
	 * @param couponSchemeId
	 * @return
	 */
	public CouponScheme readCouponScheme(String couponSchemeId);

	/**
	 * 新增优惠券模板
	 * 
	 * @param couponScheme
	 * @return
	 */
	public int insertCouponScheme(CouponScheme couponScheme);

	/**
	 * 更新优惠券模板
	 * 
	 * @param couponScheme
	 */
	public void updateCouponScheme(CouponScheme couponScheme);

	/**
	 * 删除优惠券模板
	 * 
	 * @param couponSchemeId
	 */
	public void removeCouponScheme(String couponSchemeId);

}