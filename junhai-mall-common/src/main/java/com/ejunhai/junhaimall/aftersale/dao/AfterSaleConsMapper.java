package com.ejunhai.junhaimall.aftersale.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.ejunhai.junhaimall.aftersale.model.AfterSaleCons;

/**
 * 售后服务咨询Dao
 * 
 * @author 吴阳
 * @history 2012-06-25 吴阳 新建
 */
public interface AfterSaleConsMapper {
	
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
	 * 获取咨询列表
	 * @param afterSaleCons
	 * @param rowBounds
	 * @return
	 */
	public List<AfterSaleCons> queryAfterSaleConsList(AfterSaleCons afterSaleCons,RowBounds rowBounds);
	
	
	/**
	 * 获取售后资讯数量
	 * 
	 * @param afterSaleCons
	 * @return
	 */
	public Integer queryAfterSaleConsCount(AfterSaleCons afterSaleCons);
	
	/**
	 * 回复咨询
	 * @param afterSaleCons
	 * @return
	 */
	public void doAfterSaleConsReply(AfterSaleCons afterSaleCons);
	
	/**
	 * 客户留言
	 * @param afterSaleCons
	 */
	public void doAfterSaleCons(AfterSaleCons afterSaleCons);
}
