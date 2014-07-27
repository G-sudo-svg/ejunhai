<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <title>骏海水产大闸蟹预定系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    
    <!-- Bootstrap -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">
  </head>
  
 <#-- 优惠券常量 -->
<#assign COUPON_STATE_NO_ACTIVATE = 0/><#-- 未激活/未生效 -->
<#assign COUPON_STATE_ACTIVATE = 1/><#-- 已生效 -->
<#assign COUPON_STATE_USED = 2/><#-- 已使用 -->
<#assign COUPON_STATE_EXPIRE = 3/><#-- 已过期 -->
<#assign COUPON_STATE_DISCARD = 4/><#-- 已作废 -->

  <body style="padding-top: 70px;">
	<div class="container">
		<div class="navbar navbar-default navbar-fixed-top">
			<a href="${BasePath}/logout.jhtml" class="navbar-text col-xs-2" title="返回"><span class="glyphicon glyphicon-arrow-left"></span></a>
			<span class="navbar-text col-xs-8" style="text-align:center;"><strong>欢迎登入骏海水产预定系统</strong></span>
			<a href="${BasePath}/logout.jhtml" class="navbar-text col-xs-2" title="退出"><span class="glyphicon glyphicon-log-out"></span></a>
      	</div>
      	
		<#if coupon??>
			<#-- 未激活/未生效 -->
			<#if coupon.state == COUPON_STATE_NO_ACTIVATE><#-- 未激活 -->
				<div class="alert alert-danger">抱歉：您的礼品券未激活</div>
			<#elseif coupon.state == COUPON_STATE_EXPIRE || curTime?date &gt; coupon.useEnddate?date><#-- 已过期 -->
				<div class="alert alert-danger">抱歉：您的礼品券已过期，结束时间：${coupon.useEnddate?string('yyyy-MM-dd HH:mm')}</div>
			<#elseif coupon.state == COUPON_STATE_DISCARD><#-- 已作废 -->
				<div class="alert alert-danger">抱歉：您的礼品券已作废</div>
			<#elseif curTime?date &lt; coupon.useStartdate?date><#-- 未到使用时间 -->
				<div class="alert alert-danger">谢谢您对骏海水产的支持！礼券预定开始时间为：${coupon.useStartdate?string('yyyy-MM-dd HH:mm')}，最新消息请关注官方微博。</div>
			<#else>
				<div class="alert alert-danger">抱歉：您的礼品券无效</div>
			</#if>
		</#if>
		
		<#include "/common/footer.ftl" >
	</div>
	
	

    <script src="http://ejunhai.qiniudn.com/jquery.min.js"></script>
    <script src="http://ejunhai.qiniudn.com/bootstrap.min.js"></script>
  </body>
</html>