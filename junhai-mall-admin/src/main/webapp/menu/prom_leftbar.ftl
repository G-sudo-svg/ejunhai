<!DOCTYPE html PUbliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>俊海水产管理系统</title>
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-base.css"/>
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-index.css"/>
<SCRIPT src="${BasePath}/js/FusionCharts.js" type=text/javascript></SCRIPT>
</head>
<body>
<div class="menu" >
	<div class="menu_wrap"> 
		<h3>内容管理菜单</h3>
		<ul>
			<li class=title><b class="icos productCategoryManagement"></b>礼品券管理</li>
			<li><a target="rframe" href="${BasePath}/couponScheme/couponSchemeList.sc">礼品券方案管理</a></li>
			<li><a target="rframe" href="${BasePath}/coupon/couponList.sc">礼品券管理</a></li>
		</ul>
	</div>
</div>
<script type="text/javascript" src="${BasePath}/js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/common.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/calendar/lhgcalendar.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/ygdialog/lhgdialog.min.js?s=chrome"></script> 
<script type="text/javascript" src="${BasePath}/js/validator/formValidator-4.1.1.js"></script> 
<script type="text/javascript" src="${BasePath}/js/validator/formValidatorRegex.js"></script>
<script type="text/javascript">
//等待dom元素加载完毕.
$(document).ready(function(){
	$(".title").click(function(){
		$(this).siblings().show();
		$(this).parent().siblings().children(".title").siblings().hide();
	});
	$("#wap_title").siblings().hide();
});
</script>
</body>
</html>