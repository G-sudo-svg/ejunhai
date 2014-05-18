<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>骏海水产预定系统 - 咨询留言</title>
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
	<#include "/common/serviceTitle.ftl" >
	<div class="dingdan">
		<div class="dingdan_man">
			<div class="dingdan_title">
				<p>咨询留言</p>
				<div class="dingdanbg"></div>
				<div style="clear:both;"></div>
			</div>
			<div style="float:left;margin:0 0 20px 80px;">
				<input type="hidden" name="orderMainNo" id="orderMainNo" value="${orderMain.orderMainNo!""}">
				<textarea name="consultation" id="consultation" rows="6" cols="70"></textarea>
				<p style=" margin:20px 0 0 30px;"><input type="button" onclick="saveRemark();" value="提交留言"></input></p>
			</div>
			<#if lstAfterSaleCons?? && lstAfterSaleCons?size &gt; 0>
			<#list lstAfterSaleCons as afterSaleCons>
			<div style=" clear:both;"></div>
			<div class="liuyan">
				<img src="images/liuyanbgtop.gif">
				<div class="liuyanbox">
					<div style="margin:0 0 0 35px;">
						<p style=" width:460px; margin-right:20px;"><strong>客户留言：</strong>${afterSaleCons.consultation!""}</p>
						<p>${afterSaleCons.createTime?datetime}</p>
						<div style=" clear:both;"></div>
					</div>
					<br/>
					<#if afterSaleCons?? && afterSaleCons.reply??>
					<div style="margin:0 0 0 35px;">
						<p style=" width:460px; margin-right:20px;"><strong>客服回复：</strong>${afterSaleCons.reply!""}</p>
						<p>${afterSaleCons.replyTime?datetime}</p>
						<div style=" clear:both;"></div>
					</div>
					<div style=" clear:both;"></div>
					</#if>
				</div>
				<img src="images/liuyanbgbottom.gif">
			</div>
			</#list>
			</#if>
	<div style=" clear:both;"></div>
	</div>
</div>
<script>
	$("#title a:eq(2)").attr("id","nav_hover");
    
	function saveRemark(){
		var consultation = $("#consultation").val();
		var orderMainNo = $("#orderMainNo").val();
		if(JunHai.Util.isEmpty(consultation.trim())){
			alert("请输入留言内容后在提交");
			$("#consultation").val("");
			return;
		}
		
		$.ajax({
			type:'post',
			url:"${BasePath}/saveRemark.sc",
			data:{
				'orderMainNo':orderMainNo,
				'consultation' : consultation
			},
			async : false,
			success:function(result){
				if(!JunHai.Util.isEmpty(result) && result=="true"){
					alert("您已成功提交的留言，我们会尽快处理！");
					window.location.reload();
				}else{
					alert("抱歉，提交留言失败，请稍候再试!");
				}
			}
		});
	}
</script>
<#include "/common/footer.ftl" >
</body>
</html>