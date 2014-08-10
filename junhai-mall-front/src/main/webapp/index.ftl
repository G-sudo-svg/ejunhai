<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>骏海水产预定系统</title>
		<link rel="stylesheet" href="${BasePath}/styles/style.css?${JS_VERSION}" type="text/css" />
	</head>
<body>
	<#include "/common/header.ftl" >
	<div class="nav">
		<div class="title">
			<div class="nava">
				<a id="nav_hover" class="pngfix"><img src="${BasePath}/images/nav1bg.png" border="0" />请输入正确的券号和密码</a>
				<a class="pngfix"><img src="${BasePath}/images/nav2bg.png" border="0" />填写预约时间和收货人信息</a>
				<a class="pngfix"><img src="${BasePath}/images/nav3bg.png" border="0"/>等美味的大闸蟹上门了</a>
			</div>
			<div style=" clear:both;"></div>
		</div>
	</div>
	<div style="color:red;width:990px; margin:auto;padding-left:200px;">
		温馨提示：预约选择的时间为发货时间，而非到货时间！根据多年养蟹经验，建议在10月中旬到11月这段时间内预约为佳。一般发货起48小时内可达。
	</div>
	<div class="title">
		<div class="ticket_l">
			<p><strong>预约不了怎么办?</strong></p>
			<p>别着急，您可以通过如下方式</p>
			<p>和我们的工程师联系。</p>
			<div class="ticket_l_img">联系旺旺：
			<a target="_blank" href="http://www.taobao.com/webww/ww.php?ver=3&touid=%E9%AA%8F%E6%B5%B7%E6%B0%B4%E4%BA%A7&siteid=cntaobao&status=1&charset=utf-8"><img border="0" src="http://amos.alicdn.com/realonline.aw?v=2&uid=%E9%AA%8F%E6%B5%B7%E6%B0%B4%E4%BA%A7&site=cntaobao&s=1&charset=utf-8" width="67px" height="18px"  alt="券的问题可以咨询我哦！" /></a>
			<div style="clear:both;"></div>
		</div>
		<p>联系邮箱：<a href="mailto:ejunhai@ejunhai.com" style="color: #000000;">ejunhai@ejunhai.com</a></p>
		<p>咨询电话：0711-5113338</p>
		<p><a href="http://weibo.com/junhaisc" target="blank" style="color:black;height:36px;line-height:36px;">
			<img src="images/weibo.gif" border="0">武汉壹骏海实业有限公司</a></p>
		<p style="padding:10px 0 0 0"><img src="images/wxtm.jpg" border="0" ><p>
	</div>
	
	<div class="ticket_r">
	  <table width="520" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <td colspan="3"><div><img src="${BasePath}/images/landed_top.gif"></div></td>
	      </tr>
	    <tr>
	      <td width="10"><div style="margin:0 0 0 2px;"><img src="${BasePath}/images/landed_left.gif"></div></td>
	      <td width="485" align="left" valign="top" >
	      <div class="landeda">
	      <div class="landed_title">欢迎登入骏海水产预定系统</div>
	      <form name="couponForm" id="couponForm" action="${BasePath}/login.sc" method="POST">
	      <div class="landed_user">
	      <p style="height:42px;line-height:42px; width:86px;align:right;">券  号：</p>
	      <p><input type="text" name="couponNumber" id="couponNumber" value="" style="font-size:16px;font-weight:bold;padding-left:5px;"></input></p>
	      <div style="clear:both;"></div>
	      </div>
	      <div class="landed_pass">
	      <p style="height:42px;line-height:42px; width:86px;align:right;" >密  码：</p>
	      <p><input type="password" name="couponPassword" id="couponPassword" value="" style="font-size:16px;font-weight:bold;padding-left:5px;"></input></p>
	       <div style="clear:both;"></div>
	      </div>
	      <div class="landed_pass">
	      <p style="height:42px;line-height:42px; width:86px; align:right;">验证码：</p>
	      <p><input type="input" name="validateCode" id="validateCode" value="" style="width:120px;font-size:16px;font-weight:bold;padding-left:5px;"/></input> </p>
	      <p style="padding:4px 0px 0px 20px;"><img id="imgValidateCode" src="${BasePath}/servlet/imageValidate" width="60" height="32" align="top" border="0" style="cursor:hand"/></p>
	       <div style="clear:both;"></div>
	      </div>
	      
	      <div class="landed_button">
	      <p style="margin-top:36px;padding-left:20px;"><img src="${BasePath}/images/buttonbg1.gif" style="width:230px; height:60px;cursor: pointer;" value="进入预约" onclick="JunHai.Biz.login();"/></p>
	      <input type="button" style="display:none" onclick="JunHai.Biz.login();" />
	      <p style="float:right; margin-top:3px;"><img src="images/plogoad.gif" width="96px" height="104px"></p>
	      </div>
	      </form>
	      </div>
	      </td>
	      <td width="25"><div style="margin-left:0px; height:361px;" ><img src="${BasePath}/images/landed_right.gif"></div></td>
	    </tr>
	    <tr>
	      <td colspan="3"><img src="${BasePath}/images/landed_bottom.gif"></td>
	      </tr>
	  </table>
  
	</div>
		<div style=" clear:both;"></div>
	</div>
	<#include "/common/script.ftl" >
	<script>
		var nreg_input=$("#couponNumber,#couponPassword,#validateCode");
	    nreg_input.focusin(function(){ 
	    	$(this).css("border","1px solid #F3B599");
	    }).focusout(function(){
	    	$(this).css("border","1px solid #D5D5D5");
	    });
	</script>
	<#include "/common/footer.ftl" >
	<script type="text/javascript" src="${BasePath}/js/MD5.js?${JS_VERSION}"></script>
</body>
</html>
