<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
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
	function printPage(obj) {
			myDoc = {
		   		documents:document,
	       	copyrights:'杰创软件拥有版权  www.jatools.com',
	       	onState:function(job){
	       			if(job.status==128){
	       					<#if orderMain??>$.get("/order/printMainExpress.sc?orderMainIds=${orderMain.id!''}");</#if>
					 				<#if orderRepl??>$.get("/order/printReplExpress.sc?orderReplIds=${orderRepl.id!''}");</#if> 
	       			}
		      }
	    }; 
 
	    jatoolsPrinter.printPreview(myDoc);
	}
</script>
</head>

<body>
<div class="container">
	<!--工具栏start--> 
    <div class="toolbar">
		<div class="t-content"> 
			<div class="btn" 
				<#if orderMain??>onclick="javascript:gotolink('${BasePath}/order/orderMainList.sc?state=${orderMain.state!''}')"</#if>
				<#if orderRepl??>onclick="javascript:gotolink('${BasePath}/order/orderReplList.sc?state=${orderRepl.state!''}')"</#if>>
				<span class="btn-back"></span>
				<b class="ico_btn back"></b>
				<span class="btn_txt">返回</span>
				<span class="btn_r"></span> 
			</div>
		</div>
	</div>
    <!--工具栏end-->
    
	<div class="list_content">
		<div class="top clearfix" class="noprint">
			<ul class="tab">
				<li class="curr">
				  <span><a href="#" class="btn-onselc">打印快递单</a></span>
				</li>
			</ul>
		</div>
		
 		<div class="modify"> 
        <table class="com_modi_table">
				<tbody>
					<tr>
						<td>
							<#if orderMain??><#assign logisticsCompanyCode="${orderMain.logisticsCompanyCode!''}"></#if>
							<#if orderRepl??><#assign logisticsCompanyCode="${orderRepl.logisticsCompanyCode!''}"></#if>
							
							<#if logisticsCompanyCode == "zhongtong">
							<div id="page1" style="position:relative;width:231mm;height:152mm;">
								<SPAN style="POSITION: absolute; TOP: 78px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="1">${deliveryCustomerCode!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 105px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${deliveryCompany!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 105px; CURSOR: move; LEFT: 270px" class="drag" sizcache="7" sizset="1">${deliverySender!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 135px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${deliveryProCityArea!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 163px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${deliveryDetailAddress!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 190px; CURSOR: move; LEFT: 140px" class="drag" sizcache="7" sizset="1">${deliveryMobilePhone!''}</SPAN>
								
								<#if orderMain??>
								<SPAN style="POSITION: absolute; TOP: 235px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${orderMain.orderMainNo!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 263px; CURSOR: move; LEFT: 270px" class="drag" sizcache="7" sizset="0">${orderMain.consignee!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 290px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="1">${orderMain.provinceCityArea!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 315px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${orderMain.detailAddress!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 345px; CURSOR: move; LEFT: 135px" class="drag" sizcache="7" sizset="1">${orderMain.mobilePhone!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 415px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="1">${deliveryContent!''}</SPAN>
								</#if>
								
								<#if orderRepl??>
								<SPAN style="POSITION: absolute; TOP: 235px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${orderRepl.orderReplNo!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 263px; CURSOR: move; LEFT: 270px" class="drag" sizcache="7" sizset="0">${orderRepl.consignee!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 290px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="1">${orderRepl.provinceCityArea!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 315px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${orderRepl.detailAddress!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 345px; CURSOR: move; LEFT: 135px" class="drag" sizcache="7" sizset="1">${orderRepl.mobilePhone!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 415px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="1">${deliveryContent!''}</SPAN>
								</#if>
								
								<SPAN style="POSITION: absolute; TOP: 35px; CURSOR: move; LEFT: 555px" class="drag" sizcache="7" sizset="1">${deliveryOrignAddress!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 110px; CURSOR: move; LEFT: 555px" class="drag" sizcache="7" sizset="1">${monthlyPayment!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 265px; CURSOR: move; LEFT: 640px" class="drag" sizcache="7" sizset="1">${deliverySenderSign!''}</SPAN>
							</div>
							<#else>
							<div id="page1" style="position:relative;width:231mm;height:152mm;">
								<SPAN style="POSITION: absolute; TOP: 78px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="1">${deliveryCustomerCode!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 105px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${deliveryCompany!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 105px; CURSOR: move; LEFT: 270px" class="drag" sizcache="7" sizset="1">${deliverySender!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 135px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${deliveryProCityArea!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 163px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${deliveryDetailAddress!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 190px; CURSOR: move; LEFT: 140px" class="drag" sizcache="7" sizset="1">${deliveryMobilePhone!''}</SPAN>
								
								<#if orderMain??>
								<SPAN style="POSITION: absolute; TOP: 235px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${orderMain.orderMainNo!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 263px; CURSOR: move; LEFT: 270px" class="drag" sizcache="7" sizset="0">${orderMain.consignee!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 290px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="1">${orderMain.provinceCityArea!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 315px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${orderMain.detailAddress!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 345px; CURSOR: move; LEFT: 135px" class="drag" sizcache="7" sizset="1">${orderMain.mobilePhone!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 415px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="1">${deliveryContent!''}</SPAN>
								</#if>
								
								<#if orderRepl??>
								<SPAN style="POSITION: absolute; TOP: 235px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${orderRepl.orderReplNo!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 263px; CURSOR: move; LEFT: 270px" class="drag" sizcache="7" sizset="0">${orderRepl.consignee!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 290px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="1">${orderRepl.provinceCityArea!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 315px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="0">${orderRepl.detailAddress!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 345px; CURSOR: move; LEFT: 135px" class="drag" sizcache="7" sizset="1">${orderRepl.mobilePhone!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 415px; CURSOR: move; LEFT: 70px" class="drag" sizcache="7" sizset="1">${deliveryContent!''}</SPAN>
								</#if>
								
								<SPAN style="POSITION: absolute; TOP: 35px; CURSOR: move; LEFT: 555px" class="drag" sizcache="7" sizset="1">${deliveryOrignAddress!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 110px; CURSOR: move; LEFT: 555px" class="drag" sizcache="7" sizset="1">${monthlyPayment!''}</SPAN>
								<SPAN style="POSITION: absolute; TOP: 265px; CURSOR: move; LEFT: 640px" class="drag" sizcache="7" sizset="1">${deliverySenderSign!''}</SPAN>
							</div>
							</#if>
		        		</td>
					</tr>
					<tr>
						<td><input type="button" value="打印" onclick="printPage('Box')" class="btn-save"/><input type="button" class="btn-back"
							<#if orderMain??>onclick="javascript:gotolink('${BasePath}/order/orderMainList.sc?state=${orderMain.state!''}')"</#if>
							<#if orderRepl??>onclick="javascript:gotolink('${BasePath}/order/orderReplList.sc?state=${orderRepl.state!''}')"</#if>
							value="返回"/></td>
							
					</tr>
					<tr><td><span style="color:red;padding:10px 2px 2px 15px;">打印功能请移步至IE6+浏览器|360浏览器|腾讯浏览器操作</span></td></tr>
				</tbody>
			</table>
    	</div>
  	</div>
</div>
</body>
</html>
<script type="text/javascript" src="${BasePath}/js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/common.min.js"></script> 