<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>骏海水产预定系统 - 填写预约时间和收货人信息</title>
		<#include "/common/script.ftl" >
		<script src="${BasePath}/js/areaselecor.js?${JS_VERSION}" charset="UTF-8" type="text/javascript"></script>
		<script src="${BasePath}/js/order.js?${JS_VERSION}" charset="UTF-8" type="text/javascript"></script>
		<link rel="stylesheet" href="${BasePath}/js/couponCalendar/calendar.css?ss${JS_VERSION}" type="text/css" />
		<script src="${BasePath}/js/couponCalendar/calendar.js?1fsdddddfaf${JS_VERSION}" type="text/javascript"></script>
		<script type="text/javascript">
			var expiryDate = ${expiryDate?c};
			var get = {
				byId : function(id){
					return document.getElementById(id);
				}
			};
			var EventUtil = {
				addHandler: function (oElement, sEvent, fnHandler) {
					if (oElement) {
							oElement.addEventListener ? oElement.addEventListener(sEvent, fnHandler, false) : (oElement["_" + sEvent + fnHandler] = fnHandler, oElement[sEvent + fnHandler] = function () { oElement["_" + sEvent + fnHandler]() }, oElement.attachEvent("on" + sEvent, oElement[sEvent + fnHandler]));
						}
					},
					removeHandler: function (oElement, sEvent, fnHandler) {
						if (oElement) {
							oElement.removeEventListener ? oElement.removeEventListener(sEvent, fnHandler, false) : oElement.detachEvent("on" + sEvent, oElement[sEvent + fnHandler]);
						}
					},
					addLoadHandler: function (fnHandler) {
						this.addHandler(window, "load", fnHandler);
					}
				};
		    $(function() {
		        loadcalendar('${today!""}','${startDate!""}','${endDate!""}',${deferDate?c});
		    });
		</script>
	</head>
<body>
	<#include "/common/header.ftl" >
	<div class="nav">
		<div class="title">
			<div class="nava">
				<a class="pngfix"><img src="${BasePath}/images/nav1bg.png" border="0" />请输入正确的券号和密码</a>
				<a id="nav_hover" class="pngfix"><img src="${BasePath}/images/nav2bg.png" border="0" />填写预约时间和收货人信息</a>
				<a class="pngfix"><img src="${BasePath}/images/nav3bg.png" border="0"/>等美味的大闸蟹上门了</a>
			</div>
			<div class="nav_l_e">
				<span><a href="${BasePath}/logout.sc">退出</a></span>
				<span><a href="javascript:void(0);">欢迎您！</a></span>
			</div>
			<div style=" clear:both;"></div>
		</div>
	</div>
	<#include "/common/Constant.ftl" >
	<#assign state = false/>
	<#if coupon??>
		<#-- 未激活/未生效 -->
		<#if coupon.state == COUPON_STATE_NO_ACTIVATE>
				<div class="title">
					<div class="times">谢谢您对骏海水产的支持！礼券预定开始时间为：${coupon.useStartdate?string('yyyy-MM-dd HH:mm')}，最新消息请关注官方微博。</div>
				</div>
			<#elseif coupon.state == COUPON_STATE_EXPIRE><#-- 已过期 -->
				<div class="title">
					<div class="times">抱歉：您的礼品券已过期，结束时间：${coupon.useEnddate?string('yyyy-MM-dd HH:mm')}</div>
				</div>
			<#elseif coupon.state == COUPON_STATE_DISCARD><#-- 已作废 -->
				<div class="title">
					<div class="times">抱歉：您的礼品券已作废</div>
				</div>
			<#else>
				<#assign state = true/>
		</#if>
	</#if>
	
	<#if state>
	<div class="title">
		<div class="times">有效期倒计时：您的礼品券有效期还有<span style="font-size:48px; color:#ffaa01; ">${expiryDate?c}</span>天</div>
		<div class="times_title">您的礼品券信息</div>
		<div class="times_man">
			<p><strong style="margin-left:52px;">券号：</strong>${coupon.couponNumber!""}</p>
			<p><strong>礼品券面值：</strong>${couponScheme.parValue?c}元</p>
			<p><strong>礼品券内容：</strong>${couponScheme.remark!""}</p>
		</div>
		<div class="times_title">请您选择<font color="red">发货时间</font><span style="font-size:12px; font-weight:normal;"><#if deferDate != 0 >（由于预定需提前${deferDate*24}小时，故您需选择合适的时间）</#if></span></div>
		<div class="times_time">
			<div id="section" class="clearfix">
			    <div id="orderleft" class="clearfix">
			        <div class="calendartitle">
			            <div>
							<a style="display: block;color:#0000FC;" href="javascript:void(0);" id="upmonth">上月</a>
						</div>
						<span id="dayTitle"></span>
					<div>
						<a style="display: block;color:#0000FC;" href="javascript:void(0);" id="nextmonth">下月</a>
					</div>
					</div>
			        <ul class="weekday">
			            <li>星期日</li>
			            <li>星期一</li>
			            <li>星期二</li>
			            <li>星期三</li>
			            <li>星期四</li>
			            <li>星期五</li>
			            <li>星期六</li>
			        </ul>
			        <ul id="dayTable" class="day"></ul>
			    </div>
			</div>
			</br>
			<div style="margin-left:100px;"><div style="float:left;width:150px;"><img src="${BasePath}/images/tip_1.png"/>&nbsp;&nbsp;为您的发货时间</div><div style="float:left;"><img src="${BasePath}/images/tip_2.png"/>&nbsp;&nbsp;为预约已满或已过期无法预约</div></div>
			<div style=" clear:both;"></div>
		</div>
		<div class="times_title">请您填写正确的收货人信息<span style="font-size:12px;font-weight:normal;">（该项非常重要请您务必认真填写，填写之后不可更改。）</span></div>
		<form name="orderForm" id="orderForm" action="${BasePath}/createOrder.sc" method="POST">
		<div class="consignee_man">
			<div class="consignee_name">
				<p style="margin-right:25px;">收货人姓名<span style="color:#F00">*</span> </p>
				<p><input type="text" name="consignee" id="consignee" style="width:175px; height:30px;"></input></p>
				<p id="consignee_tip" style="display:none;">
					<img style="" src="images/x.gif">
					<span style="color:#f84705; font-weight:bold;">请输入收货人姓名</span>
				</p>
			</div>
			
			<div class="consignee_name">
				<p style="margin-right:25px;">收货人地址<span style="color:#F00">*</span> </p>
				<p><span id="district"></span></p>
				<input type="hidden" name="provinceCode" id="provinceCode" />
				<input type="hidden" name="cityCode" id="cityCode" />
				<input type="hidden" name="areaCode" id="areaCode" />
				<input type="hidden" name="orderDate" id="orderDate" />
				<p id="provinceCityArea_tip" style="display:none;">
					<img style=""src="${BasePath}/images/x.gif">
					<span style="color:#f84705; font-weight:bold;">请选择收货地址</span>
				</p>
			</div>
			
			<div class="consignee_name">
				<p style="padding-left:90px;"><input type="text" name="detailAddress" id="detailAddress" style="width:500px;  height:30px;line-height:30px;"></input></p>
				<p id="detailAddress_tip" style="display:none;">
					<img style=""src="images/x.gif">
					<span style="color:#f84705; font-weight:bold;">请输入详细地址</span>
				</p>
			</div>
			<div class="consignee_name">
				<p  style="margin-right:25px;">收货人手机<span style="color:#F00">*</span> </p>
				<p><input type="text"  name="mobilePhone" id="mobilePhone" maxlength="11"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" style="width:175px;  height:30px;line-height:30px;"></input></p>
				<p id="mobilePhone_tip" style="display:none;">
					<img style=""src="images/x.gif">
					<span style="color:#f84705; font-weight:bold;">请输入正确的手机号码！</span>
				</p>
			</div>
			</form>
	</div>
	
	
	<div class="times_title" >收货协议</div>
	<div style="padding:10px;20px;0px;30px;line-height:24px;">
		<p>亲，您所提取的大闸蟹为鲜活产品，为确保大闸蟹安全到达您手中，需要您在预约前确认以下协议：</p>
		<p>	一、	请签收快递单后，立即开箱验货！请按以下步骤检查螃蟹并辨别蟹的死活。</p>
		<p>	1.	首先看蟹的眼睛，弹在外面的，用手碰一下有反应的就是活的。</p>
		<p>	2.	若无反应，可以把蟹的绳子解开，用水冲一下，看蟹是否有反应。</p>
		<p>	3.	如仍无反应可立即和骏海水产客服联系报告死蟹时间，并有快递员在场拍照为证，拍照时请把蟹肚子朝上，放在大闸蟹礼盒箱和运单上，掰开蟹脐拍照，随后再次登录本网站，输入券号密码然后上传照片（不要传到客服的旺旺号），同时务必输入正确的淘宝昵称和订单号，可以多张以方便骏海水产客服确认。如果出现死的情况，一经查证，我们会进行赔付的。由于非死蟹、图片模糊、不清晰及没有按照要求拍照等原因无法辨别死蟹情况，而又不配合提供更多图片资料的，我方不予以确认赔付。</p>
		<p>	二、	签收前请务必要确认数目，如果数目不对，请立即联系我们（可在快递单上写少几只）！顺丰明确规定：可以先验货后签收的，如果您当地的顺丰快递不讲理野蛮的，属于不让先验货的那种，亲需要控制情绪与该快递员友好协商，国家规定是应该的！</p>
		<p>	三、	签收时发现如果有部分死蟹，请务必也要签收。因为如果一旦拒签，由于鲜活产品存活时间等特殊性原因，蟹在来回运输途中将全部牺牲，所造成剩下几只活蟹的死亡我们不予赔付，死蟹是有毒的，不可食用哦！</p>
		<p>	四、	预约务必填写正确的收件人信息，因地址、手机号填错、送货上门手机打不通等您单方面个人原因发生收货延误导致大闸蟹死亡的，商家不承担任何责任，建议亲在预约的发货日期后1-5个工作日内保持通讯信息畅通，具体以顺丰快递订单物流跟踪状态判别责任归属。</p>
	</div>
	
	<a href="javascript:void(0);" onclick="createOrder();"><div class="times_button"><img src="images/buttonbg2.gif"></div></a>
	<div style="text-align:center;color:red;padding:6px;">本系统一经提交，无法修改，请仔细核对信息。</div>

	</#if>
<script>
	var nreg_input=$("#consignee,#mobilePhone,#detailAddress");
    nreg_input.focusin(function(){ 
    	$(this).css("border","1px solid #F3B599");
    }).focusout(function(){
    	$(this).css("border","1px solid #D5D5D5");
    });
	    
	$("#title a:eq(1)").attr("id","nav_hover");
	
	$("#district").areaSelecor({valueType:'id',province:'',city:'',area:'',change:function(province,city,area){
		if(JunHai.Util.isEmpty(area)){
			$("#areaCode").val('');
			$("#provinceCityArea_tip").show();
		}else{
			$("#provinceCode").val(province);
			$("#cityCode").val(city);
			$("#areaCode").val(area);
			$("#provinceCityArea_tip").hide();
		}
	}});
</script>

<#include "/common/footer.ftl" >
</body>
</html>