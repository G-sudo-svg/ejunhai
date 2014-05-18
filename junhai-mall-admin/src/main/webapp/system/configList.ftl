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
<div class="container">
	 <div class="toolbar">
		<div class="t-content"> <!--操作按钮start-->
			<div class="btn" onclick="gotolink('${BasePath}/config/toConfig.sc')">
				<span class="btn_l"></span>
				<b class="ico_btn add"></b>
				<span class="btn_txt">添加</span>
				<span class="btn_r"></span> 
			</div>
		</div>
	</div>
		  
	<div class="list_content">
	 <!--当前位置start--> 
    <div class="top clearfix">
	    <ul class="tab">
	        <li class="curr"><span>配置项管理</span></li>
	    </ul>
	  </div>
		<div class="modify" id="modify">
        	<!-- 搜索开始  -->
        	<form id="queryForm" name="queryForm" action="configList.sc" method="post">
        	<div class="add_detail_box">
        			<label for="name">配置项名称：</label>
           			<input type="text" id="configName" size="20" name="configName" value="${config.configName?default('')}" class="inputtxt"/>
           			<label for="name">配置项Key：</label>
           			<input type="text" id="configKey" size="20" name="configKey" value="${config.configKey?default('')}" class="inputtxt"/>
           			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           			<input type="submit" class="btn-add-normal" value="搜索" />
           	</div>
           	</form>
           	
			<table class="list_table" cellspacing="0" cellpadding="0" border="0">
				<thead>
                     <tr>
                    	<th width="5%">序号</th>
	                    <th width="30%">名称</th>
	                    <th width="20%">配置项键</th>
	                    <th width="30%">配置项值</th>
	                    <th width="15%">操作</th>
                    </tr>                     
                </thead>
                <tbody>
                 <#if pageFinder?? && (pageFinder.data)?? >
		      		<#list pageFinder.data as item >
		      		<tr id="Tr${item.id}">
		      			<td >${item_index+1}</td>
		      			<td >${item.configName?default("")}</td>
	          			<td >${item.configKey?default("")}</td>
	          			<td >${item.configValue?default("")}</td>
	          			<td>
	          				<a href="javascript:gotolink('${BasePath}/config/toConfig.sc?configId=${item.id}')">编辑</a>
                			<a href="javascript:removeConfig('${item.id}')">删除</a>
	          			</td>
                    </tr>
		      	</#list>	
		      	<#else>
		      		<tr><td colspan="5" style="text-align:center"><div class="yt-tb-list-no">暂无内容</div></td></tr>
				</#if>
                </tbody>
			</table>
		</div>
		<div class="bottom clearfix">
			<#if pageFinder ??>
				<#import "../common.ftl" as page>
				<@page.queryForm formId="queryForm"/>
			</#if>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="${BasePath}/js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/common.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/ygdialog/lhgdialog.min.js?s=chrome"></script> 
<script type="text/javascript">

	function removeConfig(configId){
		ygdg.dialog.confirm('你确定要删除该配置项？',function(){
			gotolink('${BasePath}/config/removeConfig.sc?configId='+configId);
		});
	}
	
</script>
</html>
