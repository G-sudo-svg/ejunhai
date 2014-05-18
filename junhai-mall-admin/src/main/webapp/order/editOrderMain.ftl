<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>骏海水产管理后台</title>
	<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-base.css"/>
	<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-global.css"/>
</head>
<body>
<form id="orderMainForm"  name="orderMainForm" action="changeConsigneeInfo.sc"  method="post" >
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
		<#if orderMain.state==0>
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
						<span id="cprovinceCityAreaTip"></span>
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
				
				<tr>
					<th></th>
					<td><input type="button" value="保存" onclick="saveConsigneeInfo();" class="btn-save"/>
	    				<input type="button" class="btn-back" onClick="goback()" value="返回"/></td>
				</tr>
			</tbody>
		</table>
		<#else>
		 <div class="detail_top_box">
			<table>
				<tr>
					<th width="80px">收货人：</th>
					<td width="320px">${orderMain.consignee?default("")}</td>
					<th width="80px">手机：</th>
					<td width="420px">${orderMain.mobilePhone?default("")}</td>
				</tr>
				<tr>
					<th width="80px">订单金额:</th>
					<td width="320px">${orderMain.payAmount?c}</td>
					<th width="80px">预约日期:</th>
					<td width="420px">${orderMain.orderDate?default("")}</td>
				</tr>
			</table>
		</div>
		</#if>
		
		<#if orderMain.state==1>
		<h3>物流信息</h3>
		<div class="detail_top_box">
			<table>
				<tr>
					<th width="80px">物流公司：</th>
					<td width="320px">${orderMain.logisticsCompany?default("")}</td>
					<th width="80px">运单号：</th>
					<td width="420px">${orderMain.expressOrderNo?default("")}</td>
				</tr>
				<tr>
					<th width="80px">物流信息：</th>
					<td width="600px" colspan="3">
						 <#if orderLogList??>
                			<#list orderLogList as item >
                				${item.createTime?string('yyyy-MM-dd HH:mm')}  ${item.behavioutDescribe!''}<br/>
							</#list>
						</#if>
						<input type="button" id="btn_${orderMain.expressOrderNo!""}" value="获取物流信息" onclick="showOrderLogisticsTrack('${orderMain.logisticsCompanyCode?default('shunfeng')}','${orderMain.expressOrderNo}');"/>
						<div id="logicInfo_${orderMain.expressOrderNo!""}"></div>
					</td>
				</tr>
			</table>
		</div>
		</#if>
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
<script type="text/javascript" src="/js/base.junhai.js?${JS_VERSION}"></script>

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
	
	
	function saveConsigneeInfo(){
		if($("#areaCode").val()==''){
			alert('请输入选择收货地址');
			return;
		}
		
		$("#orderMainForm").validation();
		$("#orderMainForm").submit();
	}
	
	function showOrderLogisticsTrack(logisticsCompanyCode,expressOrderNo){
		$("#btn_"+expressOrderNo).attr("disabled","false");
		$.getJSON("http://api.ickd.cn/?com="+logisticsCompanyCode+"&type=json&encode=utf8&ord=desc&id=F88275FED9B2AFD04ECF53BD4EEFB3F9&nu="+expressOrderNo+"&callback=?",function(json){
			var html = [];  
            if(json.status==1 && json.data){
            	for(var i=0;i<json.data.length;i++) {
					html.push('<p>'+ json.data[i].time +'&nbsp;'+ json.data[i].context +'</p>');
				}
	        }else{
				html.push('<p style="color:red;">很抱歉，未获取到快递公司的配送信息，可能是由于快递官网信息未及时录入，请耐心等待，如有问题可登录快递官网查询或致电快递官方客服。</p>');
			}
			$("#logicInfo_"+expressOrderNo).html(html.join(''));
			$("#btn_"+expressOrderNo).attr("disabled","");
	    });
	}
</script>
</html>
