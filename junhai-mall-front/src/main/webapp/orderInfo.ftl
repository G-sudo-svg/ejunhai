<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>骏海水产预定系统 - 订单信息</title>
		<#include "/common/script.ftl" >
	</head>
<body>
	<#include "/common/header.ftl" >
	<div class="nav">
		<div class="title">
			<div class="nava">
				<a class="pngfix"><img src="images/nav1bg.png" border="0" />请输入正确的券号和密码</a>
				<a class="pngfix"><img src="images/nav2bg.png" border="0" />填写预约时间和收货人信息</a>
				<a id="nav_hover" class="pngfix"><img src="images/nav3bg.png" border="0"/>等美味的大闸蟹上门了</a>
			</div>
			<div class="nav_l_e">
				<span><a href="${BasePath}/logout.sc">退出</a></span>
				<span><a href="javascript:void(0);">欢迎您！</a></span>
			</div>
			<div style=" clear:both;"></div>
		</div>
	</div>

<div class="title">
	<#if isJustNowOrder?? && isJustNowOrder>
		<div class="times">
			<span style=" font-size:30px;color:#d02a1f;">恭喜你，预约成功！</span>
			<br />
			我们将尽快为您配送，请您耐心等候！谢谢您的支持！
		</div>
	</#if>
	<#include "/common/serviceTitle.ftl" >
	
	<div class="dingdan">
		<div class="dingdan_man">
			<div class="dingdan_title">
			<p>订单信息</p>
			<div class="dingdanbg"></div>
			<div style="clear:both;"></div>
		</div>
		<div style=" width:375px;float:left;margin-left:80px;">
			<p>订单编号：${orderMain.orderMainNo}</p>
			<p>订单金额：${orderMain.payAmount}</p>
		</div>
		<div style="float:left;">
			<p>下单日期：${orderMain.createTime?datetime}</p>
			<p>预约发货日期：${orderMain.orderDate?date}</p>
		</div>
		<div style=" clear:both;"></div>
	</div>
	
	<div class="dingdan_man">
		<div class="dingdan_title">
			<p>礼品券信息</p>
			<div class="dingdanbg"></div>
			<div style="clear:both;"></div>
		</div>
		<div style="float:left;margin-left:80px; width:290px;">
			<p>礼品券名称：${couponScheme.couponName!""}</p>
			<p>礼品券编号：${coupon.couponNumber!""}</p>
		</div>
		
		<div style="float:left;margin-left:80px;">
			<p>礼品券面额：${couponScheme.parValue?c}</p>
			<div style="clear:both;"></div>
		</div>
		<div style="float:left;margin-left:80px;">
		  <p style="width:640px">礼品券内容：${couponScheme.remark!""}</p>
		</div>
		<div style=" clear:both;"></div>
	</div>
	
	<div class="dingdan_man">
		<div class="dingdan_title">
			<p>收货人信息</p>
			<div class="dingdanbg"></div>
			<div style="clear:both;"></div>
		</div>
		<div style=" width:375px;float:left;margin-left:80px;">
			<p>姓名：${orderMain.consignee!""}</p>
			<p>地区：${orderMain.provinceCityArea!""}</p>
			<p>详细地址：${orderMain.detailAddress!""}</p>
		</div>
		<div style="float:left;">
		  <p>手机：${orderMain.mobilePhone!""}</p>
		</div>
		<div style=" clear:both;"></div>
	</div>
</div>

<div style=" clear:both;"></div>
</div>
<#include "/common/footer.ftl" >
</body>
</html>