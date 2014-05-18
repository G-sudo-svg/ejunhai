<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>骏海水产管理后台</title>
	<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-base.css"/>
	<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-global.css"/>
</head>
<body>
<form id="orderMainForm"  name="orderMainForm" action="deliverOrderMain.sc"  method="post" >
<input type="hidden" id="id" name="id" value="<#if orderMain??>${orderMain.id?default("")}</#if>"/>
<div class="container"> 
    <!--工具栏start--> 
    <div class="toolbar">
			<div class="t-content"> 
				<div class="btn" onclick="goback()">
					<span class="btn-back"></span>
					<b class="ico_btn back"></b>
					<span class="btn_txt">返回</span>
					<span class="btn_r"></span> 
				</div>
			</div>
	</div>
    <!--工具栏end-->
    
    <div id="modify" class="modify">
		<h3 style="margin-top:0px">订单信息 </h3>
        <div class="detail_top_box">
			<table>
				<tr>
					<th width="80px">订单编号:</th>
					<td width="320px">${orderMain.orderMainNo?default("")}</td>
					<th width="80px">下单日期:</th>
					<td width="420px">${orderMain.createTime?string('yyyy-MM-dd HH:mm')}</td>
				</tr>
				<tr>
					<th width="80px">订单金额:</th>
					<td width="320px">${orderMain.payAmount?c}</td>
					<th width="80px">预约日期:</th>
					<td width="420px">${orderMain.orderDate?default("")}</td>
				</tr>
			</table>
		</div>
		
		<h3>礼品券信息</h3>
        <div class="detail_top_box">
			<table>
				<tr>
					<th width="80px">礼品券名称:</th>
					<td width="600px" colspan="3">${couponScheme.couponName?default("")}</td>
				</tr>
				<tr>
					<th width="80px">礼品券编号:</th>
					<td width="320px">${coupon.couponNumber?default("")}</td>
					<th width="80px">礼品券密码:</th>
					<td width="420px">${coupon.couponPassword?default("")}</td>
				</tr>
				<tr>
					<th width="80px">礼品券描述:</th>
					<td width="600px" colspan="3">${couponScheme.remark?default("")}</td>
				</tr>
			</table>
		</div>
		
		<h3>收货人信息</h3>
		<table class="com_modi_table">
			<tbody>
				<tr>
					<th>收货人：</th>
					<td>
						<input type="text" name="consignee" size="15" id="consignee" value="${orderMain.consignee?default("")}"
							class=validate[required] data-rel="请输入收货人姓名" />
						<span id="consigneeTip"></span>
	            	</td>
	            	<th>手机：</th>
					<td>
						<input type="text" name="mobilePhone" size="15" id="mobilePhone" value="${orderMain.mobilePhone?default("")}"
							class=validate[required,telephone] data-rel="请输入手机号码,请输入合法手机号码"/>
	            		<span id="mobilePhoneTip"></span>
	            	</td>
				</tr>
				<tr>
					<th>省市区：</th>
					<td colspan="3">
						<span id="district"></span>
						<input type="hidden" name="provinceCode" id="provinceCode" value="${orderMain.provinceCode?default("")}" />
						<input type="hidden" name="cityCode" id="cityCode" value="${orderMain.cityCode?default("")}"/>
						<input type="hidden" name="areaCode" id="areaCode" value="${orderMain.areaCode?default("")}"/>
	            	</td>
				</tr>
				<tr>
	            	<th>详细地址：</th>
					<td colspan="3">
						<input type="text" name="detailAddress" size="60" id="detailAddress" value="${orderMain.detailAddress?default("")}"
							class=validate[required] data-rel="请输入详细地址"/>
	            		<span id="detailAddressTip"></span>
	            	</td>
				</tr>
			</tbody>
		</table>
		
		<h3>物流信息</h3>
		<table class="com_modi_table">
			<tbody>
				<tr>
					<th>物流公司：</th>
					<td>
	           			<select id="logisticsCompanyCode" name="logisticsCompanyCode" class=validate[required] data-rel="请选择物流公司">
	           				<option value="">请选择</option>
	            			<#list logisticCompanyList as item >
	            				<option value="${item.logisticsCompanyCode}"  <#if orderMain.logisticsCompanyCode?? && orderMain.logisticsCompanyCode = item.logisticsCompanyCode >selected</#if> >${item.logisticsCompany}</option>
	            			</#list>
	        			</select>
						<span id="logisticsCompanyCodeTip"></span>
	            	</td>
	            	<th>运单号：</th>
					<td>
						<input type="text" name="expressOrderNo" size="15" id="expressOrderNo" value="${orderMain.expressOrderNo?default("")}"
							class=validate[required] data-rel="请输入快递单号"/>
	            		<span id="mobilePhoneTip"></span>
	            	</td>
				</tr>
				<tr>
					<th></th>
					<td><input type="button" value="发货" onclick="deliverOrderMain();" class="btn-save"/>
	    				<input type="button" class="btn-back" onClick="goback()" value="返回"/></td>
				</tr>
			</tbody>
		</table>
    </div>
</div>
</form>
</body>
<script type="text/javascript" src="${BasePath}/js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/common.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/calendar/lhgcalendar.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/ygdialog/lhgdialog.min.js?s=chrome"></script> 
<script type="text/javascript" src="${BasePath}/js/validator/formValidator.js"></script>
<script type="text/javascript" src="${BasePath}/js/validator/formValidator-4.1.1.js"></script> 
<script type="text/javascript" src="${BasePath}/js/areaselecor.js"></script> 

<script type="text/javascript">
	$(function(){
		$("#orderMainForm").validation();
	});
	
	
	$("#district").areaSelecor({valueType:'id',province:'${orderMain.provinceCode?default("")}',city:'${orderMain.cityCode?default("")}',
		area:'${orderMain.areaCode?default("")}',change:function(province,city,area){
		if(area==''){
			$("#areaCode").val('');
			$("#provinceCityArea_tip").show();
		}else{
			$("#provinceCode").val(province);
			$("#cityCode").val(city);
			$("#areaCode").val(area);
			$("#provinceCityArea_tip").hide();
		}
	}});
	
	function deliverOrderMain(){
		if($("#areaCode").val()==''){
			alert('请输入选择收货地址');
			return;
		}
		
		$("#orderMainForm").validation();
		$("#orderMainForm").submit();
	}
</script>
</html>
