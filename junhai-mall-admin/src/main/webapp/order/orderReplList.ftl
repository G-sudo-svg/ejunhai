<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-base.css"/>
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-global.css"/>
<title>骏海水产管理后台</title>
</head>
<body>
<div class="container">
	 <div class="toolbar">
		<div class="t-content"> <!--操作按钮start-->
		</div>
	</div>
		  
	<div class="list_content">
	 <!--当前位置start--> 
    <div class="top clearfix">
	    <ul class="tab">
	        <li class="curr"><span>补货单管理</span></li>
	    </ul>
	  </div>
		<div class="modify" id="modify">
        	<!-- 搜索开始  -->
        	<div class="add_detail_box">
        		<form id="queryForm" name="queryForm" action="orderReplList.sc" method="post">
        			<div style="height:32px;line-height:32px;">
        				<input type="hidden" id="state" name="state" value="<#if orderRepl??>${orderRepl.state?c}</#if>"/>
        				<label for="name">补货单号：</label>
	           			<input size="18"  type="text" id="orderReplNo" name="orderReplNo" value="${orderRepl.orderReplNo?default('')}" class="inputtxt"/>
	           			<label for="name">订单编号：</label>
	           			<input size="18"  type="text" id="orderMainNo" name="orderMainNo" value="${orderRepl.orderMainNo?default('')}" class="inputtxt"/>
	           			<label for="name">下单时间：</label>
	           			<input size="15"  type="text" id="startOrderTime" name="startOrderTime" class="inputtxt"
	           				value="<#if orderRepl.startOrderTime??>${orderRepl.startOrderTime?string('yyyy-MM-dd HH:mm')}</#if>" /> 至
	           			<input size="15"  type="text" id="endOrderTime" name="endOrderTime" class="inputtxt"
	           				value="<#if orderRepl.endOrderTime??>${orderRepl.endOrderTime?string('yyyy-MM-dd HH:mm')}</#if>" />
        			</div>
        			<div style="height:32px;line-height:32px;">
        				<label for="name">预约日期：</label>
	           			<input size="18"  type="text" id="orderDate" name="orderDate" value="${orderRepl.orderDate!''}" class="inputtxt"/>
	           			<label>打印状态：</label>
	           			<select name="isPrintExpress" style="width:120px;">
				        	<option value="">请选择</option>
				        	<option value="0" <#if orderRepl.isPrintExpress??&&(orderRepl.isPrintExpress==0)>selected</#if> >未打印</option>
				        	<option value="1" <#if orderRepl.isPrintExpress??&&(orderRepl.isPrintExpress==1)>selected</#if> >已打印</option>
				        </select>
				        <label for="name">收货人：</label>
	           			<input size="15"  type="text" id="consignee" name="consignee" value="${orderRepl.consignee?default('')}" class="inputtxt"/>
	           			&nbsp;&nbsp;&nbsp;
           				<input type="submit" class="btn-add-normal" value="搜索" />
        			</div>
              	</form>
           	</div>
           	
			<table class="list_table" cellspacing="0" cellpadding="0" border="0">
				<thead>
                    <tr>
                    <th width="3%" style="text-align:center"><input type="checkbox" name="allBox" id="allBox"></th>
                    <th width="10%" style="text-align:center">订单号</th>
                    <th style="text-align:center">下单日期</th>
                    <th style="text-align:center">订单金额</th>
                    <th style="text-align:center">预约日期</th>
                    <th style="text-align:center">收货人</th>
                    <th style="text-align:center">电话</th>
                    <th style="text-align:center">物流公司</th>
                    <th style="text-align:center">发货状态</th>
                     <th style="text-align:center">打印状态</th>
                    <th style="text-align:center">操作</th>
                    </tr>              
                </thead>
                <tbody>
                <#if pageFinder??&&(pageFinder.data)?? >
                	<#list pageFinder.data as item >
                		<tr>
                			<td><input type="checkbox" name="idBox" id="${item.id!''}"></td>
                			<td style="text-align:center">${item.orderReplNo!''}</td>
                			<td style="text-align:center">${item.createTime?string('yyyy-MM-dd HH:mm')}</td>
                			<td style="text-align:center">${item.payAmount?c}</td>
                			<td style="text-align:center">${item.orderDate!''}</td>
                			<td style="text-align:center">${item.consignee!''}</td>
                			<td style="text-align:center">${item.mobilePhone!''}</td>
                			<td style="text-align:center">${item.logisticsCompany!''}</td>
                			<td style="text-align:center"><#if item.state==0>未发货<#else>已发货</#if></td>
                			<td style="text-align:center"><#if item.isPrintExpress==0>未打印<#else>已打印</#if></td>
                			<td style="text-align:center">
                				<#if item.state==0>
                					<a href="javascript:gotolink('${BasePath}/order/toDeliverOrderRepl.sc?orderReplId=${item.id}')">发货</a>
                					<a href="javascript:gotolink('${BasePath}/order/toPrintReplExpress.sc?orderReplId=${item.id}')">打印</a>
                					<a href="javascript:gotolink('${BasePath}/order/toOrderRepl.sc?orderReplId=${item.id}')">查看</a>
                				<#else>
                					<a href="javascript:gotolink('${BasePath}/order/toPrintReplExpress.sc?orderReplId=${item.id}')">打印</a>
                					<a href="javascript:gotolink('${BasePath}/order/toOrderRepl.sc?orderReplId=${item.id}')">查看</a>
                				</#if>
                			</td>
                		</tr>
                	</#list>
                <#else>
                	<tr><td colspan="10" style="text-align:center"><div class="yt-tb-list-no">暂无内容</div></td></tr>
                </#if>
                </tbody>
			</table>
			<div style="padding-top:10px;"><input class="btn-add-normal-4ft" value="批量打印" onclick="batchPrintExpress();"/></div>
		</div>
		<div class="bottom clearfix">
			<#if pageFinder ??>
				<#import "../common.ftl" as page>
				<@page.queryForm formId="queryForm"/>
			</#if>
		</div>
	</div>
	
</div>
</body>
<script type="text/javascript" src="${BasePath}/js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/common.min.js"></script> 
<script type="text/javascript" src="${BasePath}/js/calendar/lhgcalendar.min.js"></script> 

<script type="text/javascript">
	$(function(){
		$('#orderDate').calendar({format:'yyyy-MM-dd'});
		$('#startOrderTime').calendar({maxDate:'#endOrderTime',format:'yyyy-MM-dd HH:mm'});
		$('#endOrderTime').calendar({minDate:'#startOrderTime',format:'yyyy-MM-dd HH:mm'});
	});
	
	$("#allBox").click(function(){
        $("input[name='idBox']").attr("checked",$(this).attr("checked"));
    });
	
	function batchPrintExpress() {
    	var checkOrderArr=$("input:checked[name='idBox']");
    	if(checkOrderArr.size()==0){
    		alert("请选择需要打印的订单");
    	}
    	
    	var orderReplIds=',';
    	checkOrderArr.each(function (i){
    		orderReplIds=orderReplIds+','+this.id;
	    });
    	orderReplIds=orderReplIds.substring(1,orderReplIds.length);
    	gotolink('${BasePath}/order/toBatchPrintReplExpress.sc?state=<#if orderRepl??>${orderRepl.state?c}</#if>&orderReplIds='+orderReplIds);
    }
</script>
</html>
