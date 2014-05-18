<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8,chrome=1" />
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-base.css"/>
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-global.css"/>
<style type="text/css" media=print>
.noprint{display:none}
.PageNext{PAGE-BREAK-AFTER:always}
</style>
<style>
.drag{border:0px solid #ff8c40;background:#fff5f6;padding:5px;color:blue;height:16px; line-height:16px;
	font-size:16px;display:inline-block;position:absolute;left:0px;top:0px;padding-right:15px;font-weight:600;}
</style>
<title></title>

<OBJECT ID="jatoolsPrinter" CLASSID="CLSID:B43D3361-D075-4BE2-87FE-057188254255" codebase="jatoolsPrinter.cab#version=5,7,0,0"></OBJECT>
<script language="javascript">
	function printPreview() {
		myDoc = {
	   		documents:document,
       		copyrights:'杰创软件拥有版权  www.jatools.com',
       		onState:function(job){
       			if(job.status==128){
       				<#if orderMainIds??>$.get("/order/printMainExpress.sc?orderMainIds=${orderMainIds}");</#if>
					<#if orderReplIds??>$.get("/order/printReplExpress.sc?orderReplIds=${orderReplIds}");</#if>
       			}
	      	}
    	}; 
	    jatoolsPrinter.printPreview(myDoc,false);
	}
	
	function directPrint(){
		myDoc = {
	   		documents:document,
       		copyrights:'杰创软件拥有版权  www.jatools.com',
       		onState:function(job){
       			if(job.status==128){
       				<#if orderMainIds??>$.get("/order/printMainExpress.sc?orderMainIds=${orderMainIds}");</#if>
					<#if orderReplIds??>$.get("/order/printReplExpress.sc?orderReplIds=${orderReplIds}");</#if>
       			}
	      	}
    	};
    	jatoolsPrinter.print(myDoc, false); 
	}
</script>
</head>

<body>
<div class="container">
	<!--工具栏start--> 
    <div class="toolbar">
		<div class="t-content"> 
			<div class="btn"
				<#if orderMainIds??>onclick="javascript:gotolink('${BasePath}/order/orderMainList.sc?state=${state}')"</#if>
				<#if orderReplIds??>onclick="javascript:gotolink('${BasePath}/order/orderReplList.sc?state=${state}')"</#if>>
				<span class="btn_l"></span><b class="ico_btn back"></b><span class="btn_txt">返回</span><span class="btn_r"></span> 
			</div>
			<div class="btn" onclick="printPreview()">
				<span class="btn_l"></span><b class="ico_btn btn-save"></b><span class="btn_txt">打印预览</span><span class="btn_r"></span> 
			</div>
			<div class="btn" onclick="directPrint()">
				<span class="btn_l"></span><b class="ico_btn btn-save"></b><span class="btn_txt">直接打印</span><span class="btn_r"></span> 
			</div>
		</div>
	</div>
    <!--工具栏end-->
    
	<div class="list_content">
		<div class="top clearfix" class="noprint">
			<ul class="tab">
				<li class="curr">
				  <span><a href="#" class="btn-onselc"><#if logisticsCompany == "zhongtong">中通<#else>顺丰</#if>快递单（${orderList?size}张）</a></span>
				</li>
			</ul>
			<p style="color:red;padding:8px 2px 2px 12px;">温馨提示：打印功能请移步至IE6+浏览器|360浏览器|腾讯浏览器操作</p>
		</div>
 		<div class="modify"> 
 			<#if orderList?? >
            <#list orderList as item >
        		<#if logisticsCompany == "zhongtong">
        		<div id="page${item_index+1}" name="printBox" style="position:relative;width:840px;height:550px;">
        			<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 48px; LEFT: 85px" class="drag" >${deliverySender!''}</SPAN>
        			<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 48px;  LEFT: 245px" class="drag" >${deliveryOrignAddress!''}</SPAN>
        			<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 80px;  LEFT: 85px" class="drag" >${deliveryProCityArea!''}</SPAN>
        			<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 105px;  LEFT: 85px" class="drag" >${deliveryDetailAddress!''}</SPAN>
        			<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 140px;  LEFT: 85px" class="drag" >${deliveryCompany!''}</SPAN>
        			<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 170px;  LEFT: 105px" class="drag" >${deliveryMobilePhone!''}</SPAN>
        			<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 300px;  LEFT: 65px" class="drag" >${deliveryContent!''}</SPAN>
        			<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 340px;  LEFT: 65px" class="drag" >${deliverySenderSign!''}</SPAN>
					
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 48px;  LEFT: 445px" class="drag" >${item.consignee!''}</SPAN>
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 80px;  LEFT: 445px" class="drag" >${item.provinceCityArea!''}</SPAN>
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 105px;  LEFT: 445px" class="drag" >${item.detailAddress!''}</SPAN>
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 170px;  LEFT: 455px" class="drag" >${item.mobilePhone!''}</SPAN>
				</div>
        		<#else>
        		<div id="page${item_index+1}" name="printBox" style="position:relative;width:840px;height:550px;">
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 78px; LEFT: 70px" class="drag" >${deliveryCustomerCode!''}</SPAN>
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 105px;  LEFT: 70px" class="drag" >${deliveryCompany!''}</SPAN>
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 105px;  LEFT: 270px" class="drag" >${deliverySender!''}</SPAN>
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 135px;  LEFT: 70px" class="drag" >${deliveryProCityArea!''}</SPAN>
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 163px;  LEFT: 70px" class="drag" >${deliveryDetailAddress!''}</SPAN>
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 190px;  LEFT: 140px" class="drag" >${deliveryMobilePhone!''}</SPAN>
					
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 235px;  LEFT: 70px" class="drag" >
						<#if orderMainIds??>${item.orderMainNo!''}</#if><#if orderReplIds??>${item.orderReplNo!''}</#if>
					</SPAN>
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 263px;  LEFT: 270px" class="drag" >${item.consignee!''}</SPAN>
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 290px;  LEFT: 70px" class="drag" >${item.provinceCityArea!''}</SPAN>
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 315px;  LEFT: 70px" class="drag" >${item.detailAddress!''}</SPAN>
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 345px;  LEFT: 135px" class="drag" >${item.mobilePhone!''}</SPAN>
					
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 415px;  LEFT: 70px" class="drag" >${deliveryContent!''}</SPAN>
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 35px;  LEFT: 555px" class="drag" >${deliveryOrignAddress!''}</SPAN>
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 110px; CURSOR: move; LEFT: 555px" class="drag" sizcache="7" sizset="1">${monthlyPayment!''}</SPAN>
					<SPAN style="Z-INDEX: 10000; POSITION: absolute; TOP: 265px;  LEFT: 640px" class="drag" >${deliverySenderSign!''}</SPAN>
				</div>
        		</#if>
        		<hr>
			</#list>
            </#if>
			<div style="clear:both;"></div>
    	</div>
  	</div>
</div>
</body>
</html>
<script type="text/javascript" src="${BasePath}/js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/common.min.js"></script> 
