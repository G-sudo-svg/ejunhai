<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-base.css"/>
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-global.css"/>
<title>骏海水产管理后台</title>
</head>
<body>
<div class="container">
	 <div class="toolbar">
		<div class="t-content"> <!--操作按钮start-->
			<div class="btn" onclick="gotolink('${BasePath}/couponScheme/toCouponScheme.sc')">
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
	        <li class="curr"><span>优惠券方案</span></li>
	    </ul>
	  </div>
		<div class="modify" id="modify">
        	<!-- 搜索开始  -->
        	<div class="add_detail_box">
        		<form id="queryForm" name="queryForm" action="couponSchemeList.sc" method="post">
           			<label for="name">方案名称：</label>
           			<input size="24"  type="text" id="couponName" name="couponName" value="${couponScheme.couponName?default('')}" class="inputtxt"/>
           			<label>方案状态：</label>
			        <select name="state" style="width:80px;">
			        	<option value="">请选择</option>
			        	<option value="0" <#if couponScheme.state??&&(couponScheme.state==0)>selected</#if> >未开始</option>
			        	<option value="1" <#if couponScheme.state??&&(couponScheme.state==1)>selected</#if> >已开始</option>
			        	<option value="2" <#if couponScheme.state??&&(couponScheme.state==2)>selected</#if> >已结束</option>
			        </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           			<input type="submit" class="btn-add-normal" value="搜索" />
              	</form>
           	</div>
           	
			<table class="list_table" cellspacing="0" cellpadding="0" border="0">
				<thead>
                    <tr>
                    <th width="20%" style="text-align:center">方案名称</th>
                    <th width="5%" style="text-align:center">价格</th>
                    <th style="text-align:center">有效期</th>
                    <th style="text-align:center">总发行量</th>
                    <th style="text-align:center">已发行量</th>
                    <th style="text-align:center">已使用量</th>
                    <th style="text-align:center">日限发行量</th>
                    <th style="text-align:center">创建时间</th>
                    <th style="text-align:center">方案状态</th>
                    <th style="text-align:center">操作</th>
                    </tr>              
                </thead>
                <tbody>
                <#if pageFinder??&&(pageFinder.data)?? >
                	<#list pageFinder.data as item >
                		<tr>
                			<td title="${item.couponName}"><#if item.couponName?length lt 18>${item.couponName}<#else>${item.couponName[0..17]}…</#if></td>
                			<td style="text-align:center">${item.parValue?c}</td>
                			<td style="text-align:center">${item.useStartdate?string('yyyy-MM-dd HH:mm')} 至 ${item.useEnddate?string('yyyy-MM-dd HH:mm')}</td>
                			<td style="text-align:center">${item.issueAmount?c}</td>
                			<td style="text-align:center">${item.hasIssueNum?c}</td>
                			<td style="text-align:center">${item.hasUseNum?c}</td>
                			<td style="text-align:center">${item.dayLimitNum?c}</td>
                			<td style="text-align:center">${item.createTime?string('yyyy-MM-dd HH:mm')}</td>
                			<td style="text-align:center">
                				<#if item.state==0>未开始<#elseif item.state==1><font color='green'>已开始</font><#elseif item.state==2>已结束<#else>-</#if>
                			</td>
                			<td style="text-align:center">
                				<#if item.hasIssueNum ==item.issueAmount>
                					<a href="javascript:exportCoupon('${item.id}')">下载</a>
                					<#if item.hasDisturb ==0>
                						<a href="javascript:disturbCoupon('${item.id}')">扰乱</a>
                					</#if>
                				<#else>
                					<a href="javascript:generateCoupon('${item.id}')">生成</a>
                				</#if>
                             	<#if item.hasIssueNum==0>
                					<a href="javascript:gotolink('${BasePath}/couponScheme/toCouponScheme.sc?couponSchemeId=${item.id}')">编辑</a>
                					<a href="javascript:removeCouponScheme('${item.id}')">删除</a>
                				<#else>
                					<a href="javascript:gotolink('${BasePath}/couponScheme/toCouponScheme.sc?readOnly=true&couponSchemeId=${item.id}')">查看</a>
                				</#if>
                			</td>
                		</tr>
                	</#list>
                <#else>
                	<tr><td colspan="10" style="text-align:center"><div class="yt-tb-list-no">暂无内容</div></td></tr>
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

	function removeCouponScheme(couponSchemeId){
		ygdg.dialog.confirm('你确定要删除该优惠券方案？',function(){
			gotolink('${BasePath}/couponScheme/removeCouponScheme.sc?couponSchemeId='+couponSchemeId);
		});
	}
	
	function generateCoupon(couponSchemeId){
		var msg='生成优惠券后将无法编辑该优惠券方案<br/><br/>';
		msg=msg+'每次最多能生成1000张优惠券<br/><br/>';
		msg=msg+'你确定要生成优惠券吗？';
		ygdg.dialog.confirm(msg,function(){
			gotolink('${BasePath}/coupon/generateCoupon.sc?couponSchemeId='+couponSchemeId);
		});
	}
	
	function exportCoupon(couponSchemeId){
		gotolink('${BasePath}/coupon/exportCoupon.sc?couponSchemeId='+couponSchemeId);
	}
	
	function disturbCoupon(couponSchemeId){
		var msg='请确认是否已经下载过优惠券列表？<br/><br/>';
		msg=msg+'优惠券扰乱后将无法分辨优惠券密码<br/><br/>';
		msg=msg+'你确定要扰乱优惠券吗？';
		ygdg.dialog.confirm(msg,function(){
			gotolink('${BasePath}/coupon/disturbCoupon.sc?couponSchemeId='+couponSchemeId);
		});
	}
</script>
</html>
