package com.ejunhai.junhaimall.coupon.service;

import java.util.List;

import com.ejunhai.junhaimall.coupon.model.Coupon;
import com.ejunhai.junhaimall.coupon.model.CouponUseDataVo;

/**
 * 优惠券服务接口
 * 
 * @author 罗正加
 * @history 2011-12-12 罗正加 新建
 */
public interface ICouponService {

	/**
	 * 获取优惠券列表
	 * 
	 * @param coupon
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Coupon> queryCouponList(Coupon coupon, int pageNo, int pageSize);

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
	 * 更新优惠券
	 * 
	 * @param coupon
	 * @return
	 */
	public void updateCoupon(Coupon coupon);

	/**
	 * 批量生成优惠券
	 * 
	 * @param couponSchemeId
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public List<Coupon> batchGenerateCoupon(String couponSchemeId, int num) throws Exception;

	/**
	 * 扰乱优惠券
	 * 
	 * @param couponSchemeId
	 * @throws Exception
	 */
	public void disturbCoupon(String couponSchemeId) throws Exception;

	/**
	 * 验证优惠券可用性
	 * 
	 * @param couponNumber
	 * @param couponPassword
	 * @return
	 */
	public String[] checkCoupon(String couponNumber, String couponPassword);

	/**
	 * 使用优惠券
	 * 
	 * @param couponNumber
	 * @param useOrderNumber
	 * @return
	 */
	public Coupon useCoupon(String couponNumber, String useOrderNumber) throws Exception;
	
	/**
	 * 查询指定模板某天使用情况
	 * @return
	 * @throws Exception
	 */
	public int usedCouponCountByDate(CouponUseDataVo couponUseDataVo) throws Exception;
}
