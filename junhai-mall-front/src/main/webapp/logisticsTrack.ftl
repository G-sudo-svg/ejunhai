<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>骏海水产预定系统 - 物流查询</title>
		<#include "/common/script.ftl" >
	</head>
<body>
	<#include "/common/header.ftl" >
	<div class="nav">
		<div class="title">
			<div class="nava">
				<a class="pngfix"><img src="${BasePath}/images/nav1bg.png" border="0" />请输入正确的券号和密码</a>
				<a class="pngfix"><img src="${BasePath}/images/nav2bg.png" border="0" />填写预约时间和收货人信息</a>
				<a id="nav_hover" class="pngfix"><img src="${BasePath}/images/nav3bg.png" border="0"/>等美味的大闸蟹上门了</a>
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
				<p>物流信息</p>
				<div class="dingdanbg"></div>
				<div style="clear:both;"></div>
			</div>
			
			
			<div style="margin-left:80px;">
				<p style="font-size:14px;font-weight:600;">订单：${orderMain.orderMainNo!""}</p>
				<div style="padding:0px 5px 5px 30px;">
					<#if orderLogList??>
		    			<#list orderLogList as item >
		    				<p>${item.createTime?string('yyyy-MM-dd HH:mm')}  ${item.behavioutDescribe!''}</p>
						</#list>
					</#if>
					<#if orderMain.state==1>
						<p>
							<b>物流公司：</b><font color="blue">${orderMain.logisticsCompany!""}</font>&nbsp;&nbsp; 
							<b>快递单号：</b><font color="blue">${orderMain.expressOrderNo!""}</font> &nbsp;&nbsp;
							<input type="button" id="btn_${orderMain.expressOrderNo!""}" class="btn-add-normal-4ft" value="获取物流信息" onclick="showOrderLogisticsTrack('${orderMain.logisticsCompanyCode?default('shunfeng')}','${orderMain.expressOrderNo}');"/>
						</p>
						<div id="logicInfo_${orderMain.expressOrderNo!""}"></div>
					</#if>
				</div>
				
				<#if orderReplList??>
	    		<#list orderReplList as orderRepl >
	    		<p style="font-size:14px;font-weight:600;">补货单：${orderRepl.orderReplNo!""}</p>
				<div style="padding:0px 5px 5px 30px;">
					<#if orderRepl.orderLogList??>
		    			<#list orderRepl.orderLogList as item >
		    				<p>${item.createTime?string('yyyy-MM-dd HH:mm')}  ${item.behavioutDescribe!''}</p>
						</#list>
					</#if>
					<#if orderRepl.state==1>
						<p>
							<b>物流公司：</b><font color="blue">${orderRepl.logisticsCompany!""}</font>&nbsp;&nbsp; 
							<b>快递单号：</b><font color="blue">${orderRepl.expressOrderNo!""}</font> &nbsp;&nbsp;
							<input type="button" id="btn_${orderRepl.expressOrderNo!""}" class="btn-add-normal-4ft" value="获取物流信息" onclick="showOrderLogisticsTrack('${orderMain.logisticsCompanyCode?default('shunfeng')}','${orderRepl.expressOrderNo}');"/>
						</p>
						<div id="logicInfo_${orderRepl.expressOrderNo!""}"></div>
					</#if>
				</div>
	    		</#list>
		    	</#if>
			</div>
			<div style=" clear:both;"></div>
		</div>
	</div>

	<div style=" clear:both;"></div>
</div>
<script type="text/javascript">

	function showOrderLogisticsTrack(logisticsCompanyCode,expressOrderNo){
		$("#btn_"+expressOrderNo).attr("disabled","false");
		$.getJSON("http://api.ickd.cn/?com="+logisticsCompanyCode+"&type=json&encode=utf8&ord=desc&id=F88275FED9B2AFD04ECF53BD4EEFB3F9&nu="+expressOrderNo+"&callback=?",function(json){
			var html = [];  
            if(json.data){
            	for(var i=0;i<json.data.length;i++) {
					html.push('<p>'+ json.data[i].time +'&nbsp;'+ json.data[i].context +'</p>');
				}
	        }else{
				html.push('<p style="color:red;">很抱歉，未获取到快递公司的配送信息，可能是由于快递官网信息未及时录入，请耐心等待，如有问题可登录快递官网查询或致电快递官方客服。</p>');
			}
			$("#logicInfo_"+expressOrderNo).html(html.join(''));
			$("#btn_"+expressOrderNo).attr("disabled","");
	    });
	}
</script>
<#include "/common/footer.ftl" >
</body>
</html>