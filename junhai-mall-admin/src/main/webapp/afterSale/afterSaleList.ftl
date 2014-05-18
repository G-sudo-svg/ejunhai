<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-base.css"/>
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-global.css"/>
<script type="text/javascript" src="${BasePath}/js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/common.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/ygdialog/lhgdialog.min.js?s=chrome"></script> 
<title>骏海水产管理后台</title>

</head>
<body>
<div class="container">
	 <div class="toolbar">
		<div class="t-content"> <!--操作按钮start-->
		</div>
	</div>
		  
	<div class="list_content">
	 <!--当前位置start--> 
    <div class="top clearfix">
	    <ul class="tab">
	        <li class="curr"><span>留言管理</span></li>
	    </ul>
	  </div>
		<div class="modify" id="modify">
        	<!-- 搜索开始  -->
        	<form id="queryForm" name="queryForm" action="afterSaleConsList.sc" method="post">
        	<div class="add_detail_box">
        			<label for="name">订单号：</label><input size="24"  type="text" id="orderMainNo" name="orderMainNo" value="${afterSaleCons.orderMainNo!""}"/>
           			<label for="name">留言内容：</label><input size="24"  type="text" id="consultation" name="consultation" value="${afterSaleCons.consultation!""}"/>
           			<label>留言状态：</label>
			        <select name="state" style="width:80px;">
			        	<option value="0">请选择</option>
			        	<option value="1" <#if afterSaleCons.state??&&(afterSaleCons.state==1)>selected</#if> >未回复</option>
			        	<option value="2" <#if afterSaleCons.state??&&(afterSaleCons.state==2)>selected</#if> >已回复</option>
			        </select> 
           			<input type="submit" class="btn-add-normal" value="搜索" />
           	</div>
           	</form>
           	
			<table class="list_table" cellspacing="0" cellpadding="0" border="0">
				<thead>
                    <tr>
                    <th width="15%" style="text-align:center">订单号</th>
                    <th width="15%" style="text-align:center">礼品券名称</th>
                    <th width="40%" style="text-align:left">留言内容</th>
                    <th style="text-align:center">留言时间</th>
                    <th style="text-align:center">状态</th>
                    <th style="text-align:center">操作</th>
                    </tr>              
                </thead>
                <tbody>
                <#if pageFinder??&&(pageFinder.data)?? && pageFinder.data?size &gt; 0>
                	<#list pageFinder.data as item >
                		<tr>
                			<td style="text-align:center">${item.orderMainNo?default("")}</td>
                			<td style="text-align:center">${item.orderMainNo?default("")}</td>
                			<td style="text-align:left">${item.consultation!""}</td>
                			<td style="text-align:center"><#if item.createTime??>${item.createTime?string('yyyy-MM-dd HH:mm')}</#if></td>
                			<td style="text-align:center"><#if item.state==1><font color="red">未回复</font><#else><font color="green">已回复</font></#if></td>
                			<td style="text-align:center"><#if item.state==1><a href="toAfterSaleConsReply.sc?id=${item.id?default("")}">回复</a><#else><a href="toAfterSaleConsReply.sc?id=${item.id?default("")}&type=1">查看</a></#if></td>
                		</tr>
                	</#list>
                <#else>
                	<tr><td colspan="6" style="text-align:center"><div class="yt-tb-list-no">暂无内容</div></td></tr>
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
</html>
