package com.ejunhai.junhaimall.aftersale.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.ejunhai.junhaimall.aftersale.model.AfterSaleRequ;

/**
 * 售后服务申请service
 * 
 * @author 吴阳
 * @history 2012-06-25 吴阳 新建
 */
public interface AfterSaleRequMapper {

	/**
	 * 查询指定id单条申请记录
	 * 
	 * @param id
	 * @return
	 */
	public AfterSaleRequ readAfterSaleRequ(String id);
	
	/**
	 * 根据订单号读取售后申请
	 * 
	 * @param orderMainNo
	 * @return
	 */
	public AfterSaleRequ getAfterSaleRequByOrderMainNo(String orderMainNo);

	/**
	 * 新增申请
	 * 
	 * @param AfterSaleRequ
	 * @return
	 */
	public void insertAfterSaleRequ(AfterSaleRequ AfterSaleRequ);

	/**
	 * 更新申请
	 * 
	 * @param AfterSaleRequ
	 * @return
	 */
	public void updateAfterSaleRequ(AfterSaleRequ afterSaleRequ);

	/**
	 * 获取售后申请数量
	 * 
	 * @param AfterSaleRequ
	 * @return
	 */
	public Integer queryAfterSaleRequCount(AfterSaleRequ AfterSaleRequ);

	/**
	 * 获取申请列表
	 * 
	 * @param AfterSaleRequ
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<AfterSaleRequ> queryAfterSaleRequList(AfterSaleRequ afterSaleRequ, RowBounds rowBounds);

}
