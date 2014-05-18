<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-base.css"/>
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-global.css"/>
<script type="text/javascript" src="${BasePath}/js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/common.min.js"></script> 
<title>骏海水产管理后台</title>

</head>
<body>
<form id="afterSaleRequForm"  name="afterSaleRequForm" action="refuseAfterSaleRequ.sc"  method="post" >
<input type="hidden" id="id" name="id" value="<#if afterSaleRequ??>${afterSaleRequ.id?default("")}</#if>"/>
<input type="hidden" id="orderMainNo" name="orderMainNo" value="<#if afterSaleRequ??>${afterSaleRequ.orderMainNo?default("")}</#if>"/>
<div class="container">
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
					<th width="80px">礼品券名称:</th>
					<td width="420px">${couponScheme.couponName?default("")}</td>
				</tr>
			</table>
		</div>
		
		<h3>收货人信息</h3>
 		<div class="detail_top_box">
			<table>
				<tr>
					<th width="80px">收货人：</th>
					<td width="320px">${orderMain.consignee?default("")}</td>
					<th width="80px">手机：</th>
					<td width="420px">${orderMain.mobilePhone?default("")}</td>
				</tr>
				<tr>
					<th width="80px">地区：</th>
					<td width="320px">${orderMain.provinceCityArea!''}</td>
					<th width="80px">地址：</th>
					<td width="420px">${orderMain.detailAddress?default("")}</td>
				</tr>
			</table>
		</div>
		
		<h3>情况说明</h3>
		<div class="detail_top_box">
			<table>
				<tr>
					<th width="80px">申请时间：</th>
					<td>${afterSaleRequ.createTime?string('yyyy-MM-dd HH:mm')}</td>
				</tr>
				<tr>
					<th width="80px">说明：</th>
					<td>${afterSaleRequ.description?default("")}</td>
				</tr>
				<tr>
					<th width="80px">图片：</th>
					<td>
						<#if afterSaleRequ.pic1Url??><a href="${afterSaleRequ.pic1Url!''}" target=_blank><img src="${afterSaleRequ.pic1Url!''}" style="width:160px;height:160px;padding:5px;"/></a></#if>
						<#if afterSaleRequ.pic2Url??><a href="${afterSaleRequ.pic2Url!''}" target=_blank><img src="${afterSaleRequ.pic2Url!''}" style="width:160px;height:160px;padding:5px;"/></#if>
						<#if afterSaleRequ.pic3Url??><a href="${afterSaleRequ.pic3Url!''}" target=_blank><img src="${afterSaleRequ.pic3Url!''}" style="width:160px;height:160px;padding:5px;"/></#if>
					</td>
				</tr>
			</table>
		</div>
		
		<h3>申请处理</h3>
		<#if afterSaleRequ.state==1>
		<div class="com_modi_table">
			<table>
				<tr>
					<th width="80px">处理说明：</th>
					<td width="800px">
						<textarea rows="6" cols="100" name="dealInfo" id="dealInfo" class=validate[required] data-rel="请输入处理说明"></textarea>
					</td>
				</tr>
			</table>
		</div>
		<div class="com_modi_table">
			<input type="button" value="拒绝申请" class="btn-add-normal-4ft" onclick="refuseAfterSaleRequ();" />
	    	<input type="button" value="下补货单" class="btn-add-normal-4ft" onClick="toAddOrderRepl();" /></td>
			<input type="button" value="返回" class="btn-add-normal-4ft" onClick="goback()" />
		</div>
		<#else>
		 <div class="detail_top_box">
			<table>
				<tr>
					<th width="80px">处理状态：</th>
					<td><#if afterSaleRequ.state==2>已下补货单<#elseif afterSaleRequ.state==3>已拒绝</#if></td>
				</tr>
				<tr>
					<th width="80px">处理时间：</th>
					<td>${afterSaleRequ.createTime?string('yyyy-MM-dd HH:mm')}</td>
				</tr>
				<tr>
					<th width="80px">处理说明：</th>
					<td>${afterSaleRequ.dealInfo?default("")}</td>
				</tr>
			</table>
		</div>
		<div>
			<input type="button" class="btn-back" onClick="goback()" value="返回"/>
		</div>
		</#if>
    </div>
</div>
</form>
<script type="text/javascript" src="${BasePath}/js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/common.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/calendar/lhgcalendar.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/ygdialog/lhgdialog.min.js?s=chrome"></script> 
<script type="text/javascript" src="${BasePath}/js/validator/formValidator.js"></script>
<script type="text/javascript" src="${BasePath}/js/validator/formValidator-4.1.1.js"></script> 
</body>
<script type="text/javascript">
	$(function(){
		$("#afterSaleRequForm").validation();
	});
	
	function toAddOrderRepl(){
		gotolink('${BasePath}/order/toAddOrderRepl.sc?orderMainNo=${afterSaleRequ.orderMainNo?default("")}');
	}

	function refuseAfterSaleRequ(){
		$("#afterSaleRequForm").validation();
		ygdg.dialog.confirm('你确定要改售后申请吗？',function(){
			$("#afterSaleRequForm").submit();
		});
	}
</script>
</html>
