package com.ejunhai.junhaimall.order.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 罗正加
 * 
 * @date 2012-06-20 23:21:14
 */
public class OrderRepl implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String id;

    /**
     * 补货订单号
     */
    private String orderReplNo;

    /**
     * 主订单号
     */
    private String orderMainNo;

    /**
     * 下单时间
     */
    private Date createTime;

    /**
     * 筛选条件：开始下单时间
     */
    private Date startOrderTime;

    /**
     * 筛选条件：结束下单时间
     */
    private Date endOrderTime;

    /**
     * 订单状态
     */
    private Integer state;

    /**
     * 订单金额
     */
    private Double payAmount;

    /**
     * 预定时间
     */
    private String orderDate;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 联系电话
     */
    private String mobilePhone;

    /**
     * 省编码
     */
    private String provinceCode;

    /**
     * 市编码
     */
    private String cityCode;

    /**
     * 区编码
     */
    private String areaCode;

    /**
     * 省市区
     */
    private String provinceCityArea;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 物流公司编码
     */
    private String logisticsCompanyCode;

    /**
     * 物流公司
     */
    private String logisticsCompany;

    /**
     * 快递单号
     */
    private String expressOrderNo;

    /**
     * 是否已发短信
     */
    private Integer isSendSms;

    /**
     * 补货说明
     */
    private String remark;

    /**
     * 是否已打快递单
     */
    private Integer isPrintExpress;

    /**
     * 补货单日志列表
     */
    private List<OrderLog> orderLogList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderReplNo() {
        return orderReplNo;
    }

    public void setOrderReplNo(String orderReplNo) {
        this.orderReplNo = orderReplNo;
    }

    public String getOrderMainNo() {
        return orderMainNo;
    }

    public void setOrderMainNo(String orderMainNo) {
        this.orderMainNo = orderMainNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartOrderTime() {
        return startOrderTime;
    }

    public void setStartOrderTime(Date startOrderTime) {
        this.startOrderTime = startOrderTime;
    }

    public Date getEndOrderTime() {
        return endOrderTime;
    }

    public void setEndOrderTime(Date endOrderTime) {
        this.endOrderTime = endOrderTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getProvinceCityArea() {
        return provinceCityArea;
    }

    public void setProvinceCityArea(String provinceCityArea) {
        this.provinceCityArea = provinceCityArea;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getLogisticsCompanyCode() {
        return logisticsCompanyCode;
    }

    public void setLogisticsCompanyCode(String logisticsCompanyCode) {
        this.logisticsCompanyCode = logisticsCompanyCode;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getExpressOrderNo() {
        return expressOrderNo;
    }

    public void setExpressOrderNo(String expressOrderNo) {
        this.expressOrderNo = expressOrderNo;
    }

    public Integer getIsSendSms() {
        return isSendSms;
    }

    public void setIsSendSms(Integer isSendSms) {
        this.isSendSms = isSendSms;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsPrintExpress() {
        return isPrintExpress;
    }

    public void setIsPrintExpress(Integer isPrintExpress) {
        this.isPrintExpress = isPrintExpress;
    }

    public List<OrderLog> getOrderLogList() {
        return orderLogList;
    }

    public void setOrderLogList(List<OrderLog> orderLogList) {
        this.orderLogList = orderLogList;
    }
}
