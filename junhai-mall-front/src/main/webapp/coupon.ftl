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
				<a class="pngfix"><img src="${BasePath}/images/nav1bg.png" border="0" />请输入正确的券号和密码</a>
				<a class="pngfix"><img src="${BasePath}/images/nav2bg.png" border="0" />填写预约时间和收货人信息</a>
				<a class="pngfix"><img src="${BasePath}/images/nav3bg.png" border="0" />等美味的大闸蟹上门了</a>
			</div>
			<div style=" clear:both;"></div>
		</div>
	</div>
<div class="title">
	<div style="height:240px;line-height:32px;">
		<div style="height:32px;line-height:32px;padding:20px 1px 20px 50px;">
			<label for="name">优惠券编码：</label>
			<input type="text" name="couponNumber" id="couponNumber" value="" style="font-size:16px;font-weight:bold;padding-left:5px;width:320px;height:28px;"></input>
			<input type="button" style="width:64px;height:32px;" id="couponNumberBtn" value="查 询" onclick="showCouponState()"/>		
		</div> 
		<div id="couponNumber_state_div" style="font-size:24px;padding-left:60px;"></div>
	</div>
	
</div>

<#include "/common/footer.ftl" >
</body>

<script type="text/javascript">

	function showCouponState(){
		$("#couponNumber_state_div").html('');
		var couponNumber=$('#couponNumber').val();
		if(JunHai.Util.isEmpty(couponNumber)){
			alert("请输入优惠券号码");
			return;
		}

		$("#couponNumberBtn").attr("disabled","false");

		$.getJSON("${BasePath}/getCouponState.sc?couponNumber="+couponNumber,function(json){
			var html = [];  
            if(json.state==0){
				html.push('<p style="color:green;">'+ json.msg +'</p>');
	        }else{
				html.push('<p style="color:red;">'+json.msg+'</p>');
			}
			$("#couponNumber_state_div").html(html.join(''));
			$("#couponNumberBtn").attr("disabled","");
	    });
	}
</script>
</html>