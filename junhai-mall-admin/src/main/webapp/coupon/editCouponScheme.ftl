<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>骏海水产管理后台</title>
	<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-base.css"/>
	<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-global.css"/>
</head>
<body>
<form id="couponSchemeForm"  name="couponSchemeForm" action="updateCouponScheme.sc"  method="post" >
<input type="hidden" id="id" name="id" value="<#if couponScheme??>${couponScheme.id?default("")}</#if>"/>
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
    <div class="list_content"> 
		<div class="top clearfix">
			<ul class="tab">
				<li class="curr"><span>编辑优惠券方案</span></li>
			</ul>
		</div>
		
        <!--主体start--> 
        <div id="modify" class="modify">
        	<table class="com_modi_table">
				<tbody>
					<tr>
					<th>方案名称：</th>
					<td>
						<input type="text" name="couponName" size="60" id="couponName" <#if readOnly??>disabled="true"</#if>
							value="<#if couponScheme??>${couponScheme.couponName?default("")}</#if>"
							class=validate[required] data-rel="请输入优惠券方案名称" />
						<span id="couponNameTip"></span>
	            	</td>
					</tr>
					<tr>
					<th>价格：</th>
					<td>
						<input type="text" name="parValue" size="10" id="parValue" <#if readOnly??>disabled="true"</#if>
							value="<#if couponScheme??>${couponScheme.parValue?c}</#if>"
							class=validate[required,number] data-rel="请输入优惠券价格,请输入数字"/>
	            		<span id="parValueTip"></span>
	            	</td>
					</tr>
					<tr>
					<th>有效期：</th>
					<td>
						<input type="text" name="useStartdate" size="16" id="useStartdate" <#if readOnly??>disabled="true"</#if>
							value="<#if couponScheme??>${couponScheme.useStartdate?string('yyyy-MM-dd HH:mm')}</#if>" /> 至  
						<input type="text" name="useEnddate" size="16" id="useEnddate" <#if readOnly??>disabled="true"</#if>
							value="<#if couponScheme??>${couponScheme.useEnddate?string('yyyy-MM-dd HH:mm')}</#if>" />
						<span id="useStartdateTip"></span><span id="useEnddateTip"></span>
	            	</td>
					</tr>
					<tr>
					<th> 发行总量：</th>
					<td>
						<input type="text" name="issueAmount" size="10" id="issueAmount" <#if readOnly??>disabled="true"</#if>
							value="<#if couponScheme??>${couponScheme.issueAmount?c}</#if>"
							class=validate[required,number] data-rel="请输入优惠券发行总量,请输入数字"/> 张
	            	<span id="issueAmountTip"></span></td>
					</tr>
					<tr>
					<th> 日限量：</th>
					<td><input type="text" name="dayLimitNum"  size="10" id="dayLimitNum" <#if readOnly??>disabled="true"</#if>
						value="<#if couponScheme??>${couponScheme.dayLimitNum?c}</#if>"
					class=validate[required,number] data-rel="请输入优惠券日限量,请输入数字"/> 张
	            	<span id="dayLimitNumTip"></span></td>
					</tr>
					<tr>
					<th>初始化状态：</th>
					<td>
						<input type="hidden" name="initActivate" id="initActivate"/>
						<input type="checkbox" name="initActivateBox" id="initActivateBox" <#if readOnly??>disabled="true"</#if>
						<#if couponScheme?? && couponScheme.initActivate==1>checked</#if>><label for="initActivateBox">激活</label>
	            	</td>
					</tr>
					<tr>
					<th> 礼品介绍：</th>
	            	<td>
						<textarea rows="6" cols="100" name="remark" id="remark" <#if readOnly??>disabled="true"</#if> 
							class=validate[required] data-rel="请输入礼品介绍"><#if couponScheme??>${couponScheme.remark?default("")}</#if></textarea>
						<span  id="remarkTip"></span>
					</td>
					</tr>
					<tr>
						<th></th>
						<td><input type="button" value="保存" onclick="save();" class="btn-save" <#if readOnly??>disabled="true"</#if>/>
	    				<input type="button" class="btn-back" onClick="goback()" value="返回"/></td>
					</tr>
				</tbody>
			</table>
        	<!-- 列表结束  -->
        </div>
        	
        <!--主体end--> 
    </div>
</div>
</form>

<script type="text/javascript" src="${BasePath}/js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/common.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/calendar/lhgcalendar.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/ygdialog/lhgdialog.min.js?s=chrome"></script> 
<script type="text/javascript" src="${BasePath}/js/validator/formValidatorRegex.js"></script> 
<script type="text/javascript" src="${BasePath}/js/validator/formValidator.js"></script>
<script type="text/javascript" src="${BasePath}/js/validator/formValidator-4.1.1.js"></script> 

<script type="text/javascript">
	$(function(){
		$("#couponSchemeForm").validation();
		$('#useStartdate').calendar({maxDate:'#useEnddate',format:'yyyy-MM-dd 00:00:00'});
		$('#useEnddate').calendar({minDate:'#useStartdate',format:'yyyy-MM-dd 23:59:59'});
	});
	
	function save(){
		if($("#initActivateBox").attr("checked")==true){
			$("#initActivate").val(1);
		}else{
			$("#initActivate").val(0);
		}
		$("#couponSchemeForm").validation();
		$("#couponSchemeForm").submit();
	}
</script>

</body>
</html>
