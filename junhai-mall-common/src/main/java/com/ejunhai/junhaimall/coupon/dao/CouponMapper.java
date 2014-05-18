package com.ejunhai.junhaimall.coupon.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.ejunhai.junhaimall.coupon.model.Coupon;
import com.ejunhai.junhaimall.coupon.model.CouponUseDataVo;

/**
 * 优惠券服务接口
 * 
 * @author 罗正加
 * @history 2011-12-12 罗正加 新建
 */
public interface CouponMapper {

	/**
	 * 获取优惠券列表
	 * 
	 * @param coupon
	 * @param rowBounds
	 * @return
	 */
	public List<Coupon> queryCouponList(Coupon coupon, RowBounds rowBounds);

	/**
	 * 获取优惠券数量
	 * 
	 * @param coupon
	 * @return
	 */
	public int queryCouponCount(Coupon coupon);

	/**
	 * 读取优惠券
	 * 
	 * @param couponId
	 * @return
	 */
	public Coupon readCoupon(String couponId);

	/**
	 * 读取优惠券
	 * 
	 * @param couponNumber
	 * @return
	 */
	public Coupon getCouponByNo(String couponNumber);

	/**
	 * 根据订单号读取优惠券
	 * 
	 * @param orderNumber
	 * @return
	 */
	public Coupon getCouponByOrderNumber(String orderNumber);

	/**
	 * 生成优惠券
	 * 
	 * @param coupon
	 * @return
	 */
	public void insertCoupon(Coupon coupon);

	/**
	 * 扰乱优惠券
	 * 
	 * @param couponSchemeId
	 */
	public void disturbCoupon(String couponSchemeId);

	/**
	 * 更新优惠券
	 * 
	 * @param coupon
	 * @return
	 */
	public void updateCoupon(Coupon coupon);
	
	/**
	 * 查询指定模板某天使用情况
	 * @return
	 * @throws Exception
	 */
	public int usedCouponCountByDate(CouponUseDataVo couponUseDataVo);

}