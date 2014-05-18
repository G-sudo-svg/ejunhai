package com.ejunhai.junhaimall.aftersale.service;

import java.util.List;

import com.ejunhai.junhaimall.aftersale.model.AfterSaleCons;

/**
 * 售后服务咨询service
 * 
 * @author 吴阳
 * @history 2012-06-25 吴阳 新建
 */
public interface IAfterSaleConsService {

	/**
	 * 查询指定id单条咨询记录
	 * @param id
	 * @return
	 */
	public AfterSaleCons readAfterSaleCons(String id);
	
	/**
	 * 新增咨询
	 * @param afterSaleCons
	 * @return
	 */
	public void insertAfterSaleCons(AfterSaleCons afterSaleCons);
	
	/**
	 * 获取售后资讯数量
	 * 
	 * @param afterSaleCons
	 * @return
	 */
	public Integer queryAfterSaleConsCount(AfterSaleCons afterSaleCons);
	
	/**
	 * 获取咨询列表
	 * @param afterSaleCons
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<AfterSaleCons> queryAfterSaleConsList(AfterSaleCons afterSaleCons, int pageNo, int pageSize);
	
	/**
	 * 回复咨询
	 * @param afterSaleCons
	 */
	public void doAfterSaleConsReply(AfterSaleCons afterSaleCons);
}
