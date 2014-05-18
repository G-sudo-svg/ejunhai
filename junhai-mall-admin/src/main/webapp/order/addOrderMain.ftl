<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>骏海水产管理后台</title>
	<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-base.css"/>
	<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-global.css"/>
</head>
<body>
<form id="orderMainForm"  name="orderMainForm" action="createOrderMain.sc"  method="post" >
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
		<h3 style="margin-top:0px">后台下单： </h3>
		<table class="com_modi_table">
			<tbody>
				<tr>
					<th>礼品券号：</th>
					<td>
						<input type="text" name="couponNumber" size="20" id="couponNumber"
							class=validate[required] data-rel="请输入礼品券" />
						<span id="couponNumberTip"></span>
	            	</td>
				</tr>
				<tr>
					<th>密码：</th>
					<td>
						<input type="password" name="couponPassword" size="20" id="couponPassword"
							class=validate[required] data-rel="请输入礼品券密码"/>
	            		<span id="mobilePhoneTip"></span>
	            	</td>
				</tr>
				<tr>
					<th>预约时间：</th>
					<td>
						<input type="text" name="orderDate" size="12" id="orderDate"/>
	            		<span id="orderDateTip"></span>
	            	</td>
				</tr>
			</tbody>
		</table>
        
		
		<h3>收货人信息</h3>
		<table class="com_modi_table">
			<tbody>
				<tr>
					<th>收货人：</th>
					<td>
						<input type="text" name="consignee" size="20" id="consignee""
							class=validate[required] data-rel="请输入收货人姓名" />
						<span id="consigneeTip"></span>
	            	</td>
				</tr>
				<tr>
					<th>手机：</th>
					<td>
						<input type="text" name="mobilePhone" size="20" id="mobilePhone"
							class=validate[required,telephone] data-rel="请输入手机号码,请输入合法手机号码"/>
	            		<span id="mobilePhoneTip"></span>
	            	</td>
				</tr>
				<tr>
					<th>省市区：</th>
					<td>
						<span id="district"></span>
						<input type="hidden" name="provinceCode" id="provinceCode" />
						<input type="hidden" name="cityCode" id="cityCode" />
						<input type="hidden" name="areaCode" id="areaCode" class=validate[required] data-rel="请选择省市区"/>
						<span id="provinceCityAreaTip"></span>
	            	</td>
				</tr>
				<tr>
	            	<th>详细地址：</th>
					<td>
						<input type="text" name="detailAddress" size="60" id="detailAddress"
							class=validate[required] data-rel="请输入详细地址"/>
	            		<span id="detailAddressTip"></span>
	            	</td>
				</tr>
				
				<tr>
					<th></th>
					<td><input type="button" value="保存" onclick="createOrderMain();" class="btn-save"/>
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
<script type="text/javascript" src="${BasePath}/js/MD5.js"></script> 
<script type="text/javascript" src="${BasePath}/js/calendar/lhgcalendar.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/ygdialog/lhgdialog.min.js?s=chrome"></script> 
<script type="text/javascript" src="${BasePath}/js/validator/formValidator.js"></script>
<script type="text/javascript" src="${BasePath}/js/validator/formValidator-4.1.1.js"></script> 
<script type="text/javascript" src="${BasePath}/js/areaselecor.js?d"></script> 

<script type="text/javascript">
	$(function(){
		$("#orderMainForm").validation();
		$('#orderDate').calendar({minDate:'%y-%M-%d',format:'yyyy-MM-dd'});
	});
	
	$("#district").areaSelecor({valueType:'id',province:'',city:'',area:'',change:function(province,city,area){
		if(area==''){
			$("#areaCode").val('');
			$("#provinceCityAreaTip").show();
		}else{
			$("#provinceCode").val(province);
			$("#cityCode").val(city);
			$("#areaCode").val(area);
			$("#provinceCityAreaTip").hide();
		}
	}});
	
	
	function createOrderMain(){
		$("#orderMainForm").validation();
		if($("#areaCode").val()==''){
			alert('请选择省市区');
			return;
		}
		
		if($("#orderDate").val()==''){
			alert('请选择预约时间');
			return;
		}
		
		var couponNumber=$('#couponNumber').val();
		var couponPassword=$('#couponPassword').val();
		if(couponNumber!=''&&couponPassword!=''){
			$.get("${BasePath}/coupon/checkCoupon.sc",{
				couponNumber: $.trim(couponNumber) , 
				couponPassword: $.trim(hex_md5(couponPassword)) 
			},function(data){
				if(data=='0'){
					$("#orderMainForm").submit();
				}else{
					ygdg.dialog.alert(data);
				}
			});
		}
	}
</script>
</html>
