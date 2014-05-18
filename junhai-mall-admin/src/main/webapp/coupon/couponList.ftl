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
		</div>
	</div>
		  
	<div class="list_content">
	 <!--当前位置start--> 
    <div class="top clearfix">
	    <ul class="tab">
	        <li class="curr"><span>优惠券管理</span></li>
	    </ul>
	  </div>
		<div class="modify" id="modify">
        	<!-- 搜索开始  -->
        	<form id="queryForm" name="queryForm" action="couponList.sc" method="post">
        	<div class="add_detail_box">
           			<label for="name">方案名称：</label>
           			<select id="couponSchemeId" name="couponSchemeId">
           				<option value="">请选择</option>
            			<#list couponSchemeList as item >
            				<option value="${item.id}" <#if coupon.couponSchemeId?? && coupon.couponSchemeId = item.id >selected</#if>>${item.couponName}</option>
            			</#list>
        			</select>
        			<label for="name">礼品券码：</label>
           			<input type="text" id="couponNumber" size="20" name="couponNumber" value="${coupon.couponNumber?default('')}" class="inputtxt"/>
           			<label>礼品券状态：</label>
			        <select name="state" style="width:80px;">
			        	<option value="">请选择</option>
			        	<option value="0" <#if coupon.state??&&(coupon.state==0)>selected</#if> >未激活</option>
			        	<option value="1" <#if coupon.state??&&(coupon.state==1)>selected</#if> >未使用</option>
			        	<option value="2" <#if coupon.state??&&(coupon.state==2)>selected</#if> >已使用</option>
			        	<option value="3" <#if coupon.state??&&(coupon.state==3)>selected</#if> >已作废</option>
			        </select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           			<input type="submit" class="btn-add-normal" value="搜索" />
           	</div>
           	</form>
           	
			<table class="list_table" cellspacing="0" cellpadding="0" border="0">
				<thead>
                    <tr>
                    <th width="15%" style="text-align:center">礼品券编码</th>
                    <th width="20%" style="text-align:center">方案名称</th>
                    <th width="5%" style="text-align:center">价格</th>
                    <th style="text-align:center">有效开始时间</th>
                    <th style="text-align:center">有效结束时间</th>
                    <th style="text-align:center">状态</th>
                    <th style="text-align:center">使用时间</th>
                    <th style="text-align:center">使用订单</th>
                    <th style="text-align:center">操作</th>
                    
                    </tr>              
                </thead>
                <tbody>
                <#if pageFinder??&&(pageFinder.data)?? >
                	<#list pageFinder.data as item >
                		<tr>
                			<td style="text-align:center">${item.couponNumber?default("")}</td>
                			<td>${item.couponName?default("")}</td>
                			<td style="text-align:center">${item.parValue?c}</td>
                			<td style="text-align:center">${item.useStartdate?string('yyyy-MM-dd HH:mm')}</td>
                			<td style="text-align:center">${item.useEnddate?string('yyyy-MM-dd HH:mm')}</td>
                			<td style="text-align:center">
                				<#if item.state==0><font color="black">未激活</font>
                				<#elseif item.state==1><font color="green">未使用</font>
                				<#elseif item.state==2><font color="blue">已使用</font>
                				<#else><font color="red">已作废</font></#if>
                			</td>
                			<td style="text-align:center"><#if item.useTime??>${item.useTime?string('yyyy-MM-dd HH:mm')}</#if></td>
                			<td style="text-align:center">${item.useOrderNumber?default("")}</td>
                			<td style="text-align:center">	
                				<#if item.state==0||item.state==1>
                					<#if item.state==0>
                						<a href="javascript:activateCoupon('${item.id}')">激活</a>
                					</#if>
                					<a href="javascript:discardCoupon('${item.id}')">作废</a>
                				<#else>
                					—
                				</#if>
                			</td>
                		</tr>
                	</#list>
                <#else>
                	<tr><td colspan="9" style="text-align:center"><div class="yt-tb-list-no">暂无内容</div></td></tr>
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

	function discardCoupon(couponId){
		ygdg.dialog.confirm('你确定要作废该优惠券？',function(){
			gotolink('${BasePath}/coupon/discardCoupon.sc?couponId='+couponId);
		});
	}
	
	function activateCoupon(couponId){
		ygdg.dialog.confirm('你确定要激活该优惠券？',function(){
			gotolink('${BasePath}/coupon/activateCoupon.sc?couponId='+couponId);
		});
	}
</script>
</html>
