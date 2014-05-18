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
		<div class="t-content">
			<div class="btn" onclick="goback()">
				<span class="btn-back"></span>
				<b class="ico_btn back"></b>
				<span class="btn_txt">返回</span>
				<span class="btn_r"></span> 
		</div>
	</div>
		  
	<div class="list_content">
	 <!--当前位置start--> 
    <div class="top clearfix">
	    <ul class="tab">
	        <li class="curr"><span><#-- 查看or回复 -->
			<#if type?? && type == '1'>
				查看回复
				<#else>
				留言回复
			</#if></span></li>
	    </ul>
	  </div>
		<div class="modify" id="modify">
			<#if type?? && type == '1'>
				<#else>
	        	<form id="afterSaleConsForm" name="afterSaleConsForm" action="doAfterSaleConsReply.sc" method="post">
	        	<input type="hidden" name="id" id="id" value="${afterSaleCons.id!""}"/>
	        	<div class="add_detail_box">
	        		<label for="name">订单号：</label>
	    			${afterSaleCons.orderMainNo!""}<br/>
	    			<label for="name">客户留言：</label>
	    			${afterSaleCons.consultation!""}<br/>
	    			<label for="name">回复内容：</label>
	    			<textarea rows="6" cols="100" name="reply" id="reply" class=validate[required] data-rel="请输入回复内容"></textarea>
					<span id="replyTip"></span>
	           	</div>
	           	<div class="add_detail_box">
	           		<label for="name"></label>
	           		<input class="btn-save" type="submit" value="添加"/>
	           		<input class="btn-back" type="button" onclick="goback();" value="返回"/>
	           	</div>
	           	</form>
			</#if>
			<table class="list_table" cellspacing="0" cellpadding="0" border="0">
				<thead>
                    <tr>
                    <th width="40%" style="text-align:left">留言内容</th>
                    <th style="text-align:center">回复内容</th>
                    <th style="text-align:center">回复时间</th>
                    </tr>              
                </thead>
                <tbody>
                <#if lstAfterSaleCons??&&lstAfterSaleCons?size &gt; 0>
                	<#list lstAfterSaleCons as item >
                		<tr>
                			<td style="text-align:left">${item.consultation!""}</td>
                			<td style="text-align:left">${item.reply!""}</td>
                			<td style="text-align:center"><#if item.replyTime??>${item.replyTime?string('yyyy-MM-dd HH:mm')}</#if></td>
                		</tr>
                	</#list>
                <#else>
                	<tr><td colspan="3"><div class="yt-tb-list-no">暂无内容</div></td></tr>
                </#if>
                </tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>
