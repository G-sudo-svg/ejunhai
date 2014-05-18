<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>骏海水产管理后台</title>
	<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-base.css"/>
	<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-global.css"/>
</head>
<body>
<form id="configForm"  name="configForm" action="updateConfig.sc"  method="post" >
<input type="hidden" id="id" name="id" value="<#if config??>${config.id?default("")}</#if>"/>
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
				<li class="curr"><span>编辑系统配置项</span></li>
			</ul>
		</div>
		
        <!--主体start--> 
        <div id="modify" class="modify">
        	<table class="com_modi_table">
				<tbody>
					<tr>
					<th>配置项名称：</th>
					<td>
						<input type="text" name="configName" size="60" id="configName"
							value="<#if config??>${config.configName?default("")}</#if>"
							class=validate[required] data-rel="请输入配置项名称" />
						<span id="configNameTip"></span>
	            	</td>
					</tr>
					<tr>
					<th>配置项KEY：</th>
					<td>
						<input type="text" name="configKey" size="60" id="configKey" <#if config??>disabled="true"</#if>
							value="<#if config??>${config.configKey?default("")}</#if>"
							class=validate[required] data-rel="请输入配置项KEY" />
						<span id="configKeyTip"></span>
	            	</td>
					</tr>
					<tr>
					<th>配置项值：</th>
					<td>
						<input type="text" name="configValue" size="60" id="configValue" 
							value="<#if config??>${config.configValue?default("")}</#if>"
							class=validate[required] data-rel="请输入配置项值" />
						<span id="configValueTip"></span>
	            	</td>
					</tr>
					<tr>
					<th> 备注：</th>
	            	<td>
						<textarea rows="6" cols="100" name="remark" id="remark"><#if config??>${config.remark?default("")}</#if></textarea>
						<span  id="remarkTip"></span>
					</td>
					</tr>
					<tr>
						<th></th>
						<td><input type="button" value="保存" onclick="save();" class="btn-save"/>
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
<script type="text/javascript" src="${BasePath}/js/validator/formValidator.js"></script>
<script type="text/javascript" src="${BasePath}/js/validator/formValidator-4.1.1.js"></script> 

<script type="text/javascript">
	$(function(){
		$("#configForm").validation();
	});
	
	function save(){
		$("#configForm").validation();
		$("#configForm").submit();
	}
</script>

</body>
</html>
