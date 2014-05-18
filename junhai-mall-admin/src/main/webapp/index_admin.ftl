<!DOCTYPE html PUbliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<title>壹骏海水产管理系统</title>
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-base.css"/>
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-index.css"/>
</head>
<body>
<div class="continer" style="height:100%; clear:both;display:block;"> 
	<!--公共头部开始-->
	<div class="header">
		<div class="headerNav" >
			<div class="logo"> <img src="${BasePath}/images/plogo.gif" width="90" height="55px" style="margin:0px 0px 3px 10px;"/><span>&nbsp;骏海水产管理系统</span> </div>
			<div id="idContainer" class="container_nav">
			<div id="idContent">
			<ul class="nav"  >
				<li><a class="order m0_curr" title="订单" href="javascript:void(0);" onclick="iframeSrc('${BasePath}/config/redirect.sc?url=menu/order_leftbar','${BasePath}/config/redirect.sc?url=main');">订单</a></li>
				<li><a class="marketing" title="礼品券" href="javascript:void(0);" onclick="iframeSrc('${BasePath}/config/redirect.sc?url=menu/prom_leftbar','${BasePath}/config/redirect.sc?url=main');">礼品券</a> </li>
				<li><a class="customer" title="售后" href="javascript:void(0);" onclick="iframeSrc('${BasePath}/config/redirect.sc?url=menu/sale_leftbar','${BasePath}/config/redirect.sc?url=main');">售后</a></li>
				<li><a class="report" title="报表" href="javascript:void(0);" onclick="iframeSrc('${BasePath}/config/redirect.sc?url=menu/report_leftbar','${BasePath}/config/redirect.sc?url=main');">报表</a></li>
				<li><a class="system" title="系统配置" href="javascript:void(0);" onclick="iframeSrc('${BasePath}/config/redirect.sc?url=menu/sys_leftbar','${BasePath}/config/redirect.sc?url=main');">系统配置</a> </li>
			</ul>
			</div>
			<div style="clear:both;position:absolute;top:80px;left:8px;">
			<div class="slider_left" id="idSliderLeft"></div>
			<div class="slider" id="idSlider">
				<div class="bar" id="idBar">
					<div class="bar_left"></div>
					<div class="bar_right"></div>
				</div>
			</div>
			<div class="slider_right" id="idSliderRight"></div>
			</div>
			</div>
		</div>
		
		<div class="admin">
			<div class="info_wrap relative"> <!--<span class="b1"></span>--> <span class="link"> <a href="#">admin</a>|<a href="#">账号设置</a>|<a title="退出" onClick="gotolink('${BasePath}/logout.sc');" href="javascript:void(0);">退出</a> </span> <span class="info clearfix"> <a title="未处理的订单 " href="#"><b  class=s1></b><span >0</span></a> <a  title="未回复的商品咨询 " href="#"><b class=s2></b><span >0</span></a> <a  title="低库存货品" href="#"><b class=s3></b><span>0</span></a> </span> </div>
		</div>
	</div>
	<!--公共头部结束-->
	
	<div class="clearfix">
		<div class="lfbar">
			<iframe id="lframe" name="lframe" width="166" height="100%" border="0"  frameborder="0" scrolling="no" ></iframe>
		</div>
		<div class="barico">
			<div class="sidebar"></div>
		</div>
		<div class="rfbar">
			<iframe id="rframe" name="rframe" border="0" frameBorder="0" height="100%"  src="${BasePath}/config/redirect.sc?url=main"   scrolling="no" onload="this.height=rframe.document.body.scrollHeight" ></iframe>
		</div>
	</div>
</div>
<!--底部内容开始-->
<div class="footer"> ©2012 武汉壹骏海实业有限公司版权所有 </div>
<!--底部内容结束--> 
<script type="text/javascript" src="${BasePath}/js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/common.min.js"></script> 
<script src="${BasePath}/js/slider.js" type="text/javascript"></script>
<script src="${BasePath}/js/drag.js" type="text/javascript"></script>
<script type="text/javascript" src="${BasePath}/js/calendar/lhgcalendar.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/ygdialog/lhgdialog.min.js?s=chrome"></script>  
<script type="text/javascript" src="${BasePath}/js/validator/formValidator-4.1.1.js"></script> 
<script type="text/javascript" src="${BasePath}/js/validator/formValidatorRegex.js"></script> 

<script type="text/javascript">
//导航滑动设置
var w=document.documentElement.offsetWidth-390;
var w1=65*$(".nav li").length;
if(w>=w1-20)
	$(".slider").hide();
else
	$(".slider").show();
$("#idContent").attr('style','width:'+w+'px;overflow:hidden;margin-bottom:8px;');
$(".nav").width(w1)

var sld = new Slider("idSlider", "idBar", {
	MaxValue: $$("idContent").scrollWidth - $$("idContent").clientWidth,
	onMin: function(){ $$("idSliderLeft").style.backgroundPosition = "bottom left"; },
	onMax: function(){ $$("idSliderRight").style.backgroundPosition = "bottom right"; },
	onMid: function(){ $$("idSliderLeft").style.backgroundPosition = "top left"; $$("idSliderRight").style.backgroundPosition = "top right"; },
	onMove: function(){ $$("idContent").scrollLeft = this.GetValue(); }
});
sld.SetPercent(0);
sld.Ease = true;
//导航滑动设置结束

function resetH() //正常页面高度
{
var hr=$("#rframe").contents().find(".container").height()+60;
var hl=$("#lframe").contents().find(".menu").height();
var hc=document.documentElement.clientHeight;
if(hl<document.documentElement.clientHeight)
	hl=document.documentElement.clientHeight-60;
hc=hr;
//alert('hc:'+hc+'hl:'+hl);
if(hc<hl) 
	{
		hc=hl;
		hr=hl-100;
	}
//if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/7./i)=="7.")
//{
	if(hc>document.documentElement.clientHeight)
		hc=hc+160;
//}
if(hc<document.documentElement.clientHeight)
	$(".continer").height(document.documentElement.clientHeight);
else
	$(".continer").height(hc);
$("#rframe").height(hr);
if($("#lframe").contents().find(".menu").height()!=null)
	$("#lframe").height($("#lframe").contents().find(".menu").height()+60);
}

setInterval(resetH,100);//自适应高度
setInterval(resetW,100);//自适应宽度

//缩放窗体自适应宽度
window.onresize=function(){
resetW();
}


//设置开关样式
$(".sidebar").mouseover(function(){
	$(this).addClass("menu-on")
}).mouseout(function(){
	$(this).removeClass("menu-on")
});


//设置主菜单样式
$(".nav li a").click(function(){
	$(".nav li a").each(function(){
		$(".nav li a").removeClass("m"+$(".nav li a").index(this)+"_curr");
	});
	$(this).addClass("m"+$(".nav li a").index(this)+"_curr");
});


//左侧菜单开关
function fnToggleMenu(){
	if($("body").hasClass("close_left")){
		$("body").removeClass('close_left');
		opflag=0;
		$(".lfbar").show();
	}else{
		$("body").addClass('close_left');
		$(".lfbar").hide();
		opflag=1;
	}
}

//顶部导航普通菜单跳转
function iframeSrc(x,y){
$("body").removeClass('close_left');
opflag=0;
$(".lfbar").show();
$(".barico").show();
$(".sidebar").unbind('click').bind('click',fnToggleMenu);
document.getElementById("lframe").src=x;
document.getElementById("rframe").src=y;
}

//顶部导航《主面板》跳转
function iframeHomeSrc(url){
$("body").addClass('close_left');
$(".lfbar").hide();
$(".barico").hide();
opflag=1;
$(".sidebar").unbind('click');
document.getElementById("rframe").src=url;
}

//顶部次要菜单导航
function iframeSrcSec(x,y){
$("body").removeClass('close_left');
opflag=0;
$(".lfbar").show();
$(".barico").show();
$(".sidebar").unbind('click').bind('click',fnToggleMenu);
document.getElementById("lframe").src=x;
document.getElementById("rframe").src=y;
$(".nav li a").each(function(){
		$(".nav li a").removeClass("m"+$(".nav li a").index(this)+"_curr");
	});
}

//页面加载完成禁用菜单开关
$(function(){
$("body").addClass('close_left');
$(".lfbar").hide();
iframeHomeSrc('${BasePath}/config/redirect.sc?url=main')//F5刷新跳转到主面板
$("#rframe").load(function(){           
$('html, body').animate({scrollTop: '0px'});//框架加载滚动到顶部
});
});
</script>
</body>
</html>
