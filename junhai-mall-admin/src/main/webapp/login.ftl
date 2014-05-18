<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>壹俊海水产管理系统</title>
<link href="${BasePath}/styles/sys-base.css" rel="stylesheet" type="text/css" />
<link href="${BasePath}/styles/sys-global.css" rel="stylesheet" type="text/css" />
<link href="${BasePath}/styles/sys-index.css" rel="stylesheet" type="text/css" />
<script>
if (top.location != self.location)top.location=self.location;
</script>
</head>
<body class="login_bg">
<table width="100%" align="center">
	<tbody>
		<tr>
			<td>
				<form action="${BasePath}/loginSystem.sc" class="loginForm" method="post" name="loginForm" id="loginForm">
					<div class="login">
						<div class="img"><img src="${BasePath}/images/plogo.gif" width="158" height="123" /></div>
						<div class="txt">
							<table class="com_modi_table" width="338">
								<tr>
									<th  style="width:80px;">用户名：</th>
									<td colspan="2">
										<input id="loginName" name="username" type="text" class="g-ipt"/>
										<span id="loginNameTip"></span>
										<div class="tip" id="loginNameFixTip" ></div>
									</td>
								</tr>
								<tr>
									<th style="width:80px;">密码：</th>
									<td colspan="2">
										<input id="loginPassword"  name="password" type="password" class="g-ipt" />
										<span id="loginPasswordTip"></span>
										<div class="tip" id="loginPasswordFixTip" ></div>
									</td>
								</tr>
								<tr>
									<th style="width:80px;">验证码：</th>
									<td colspan="2">
										<input type="text" class="g-ipt" id="loginCode"  name="validateCode" style="width:80px;">
										<img id="imgValidateCode" src="${BasePath}/servlet/imageValidate" width="55" height="20" align="top" border="0" style="cursor:hand"/>
										<div class="tip" id="loginCodeFixTip" ></div>
									</td>
								</tr>
								<tr>
									<th style="width:80px;">&nbsp;</th>
									<td>
										<input type="checkbox" name="rememberMe" id="rememberMe" tabindex="4" />
										<label for="rememberMe">记住登录</label>
									</td>
								</tr>
								<tr>
									<th style="width:80px;">&nbsp;</th>
									<td>
										<input name="" type="submit" class="button_login" value=""/>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="footer_login">©2012 ejunhai Technology Co., Ltd.</div>
				</form>
			</td>
		</tr>
	</tbody>
</table>
<script type="text/javascript" src="${BasePath}/js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/common.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/calendar/lhgcalendar.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/ygdialog/lhgdialog.min.js?s=chrome"></script>  
<script type="text/javascript" src="${BasePath}/js/validator/formValidator-4.1.1.js"></script> 
<script type="text/javascript" src="${BasePath}/js/validator/formValidatorRegex.js"></script> 
<script type="text/javascript">
$(function(){
	$.formValidator.initConfig({theme:"default",submitOnce:true,formID:"loginForm",
		onError:function(msg){},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	
	$("#loginName").formValidator({onShowFixText:" ",onCorrect:" "}).inputValidator({min:4,onError:"用户名不能为空！"});
	$("#loginPassword").formValidator({onShowFixText:" ",onCorrect:" "}).inputValidator({min:6,onError:"用户密码不能为空！"});
	$("#loginCode").formValidator({onShowFixText:" ",onCorrect:" "}).inputValidator({min:1,onError:"验证码不能为空！"});
});

function reloadCode(){
	var verify=document.getElementById('imgValidateCode');
	verify.setAttribute('src','${BasePath}/servlet/imageValidate?rand='+Math.random());
}
</script>
</body>
</html>
