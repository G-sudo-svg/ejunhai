package com.ejunhai.junhaimall.system.model;

import java.io.Serializable;

/**
 * @author 罗正加
 * 
 * @date 2012-06-20 23:21:45
 */
public class Area implements Serializable {

	/***/
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	private String id;

	/**
     * 
     */
	private String name;

	/**
     * 
     */
	private String no;

	/**
     * 
     */
	private String isleaf;

	/**
     * 
     */
	private Integer child;

	/**
     * 
     */
	private Integer level;

	/**
     * 
     */
	private Integer sort;

	/**
     * 
     */
	private String post;

	/**
	 * 区号
	 */
	private String code;

	/**
	 * 时间戳
	 */
	private Long timestamp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}

	public Integer getChild() {
		return child;
	}

	public void setChild(Integer child) {
		this.child = child;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
