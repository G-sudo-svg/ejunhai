<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>骏海水产预定系统 - 死蟹申请</title>
		<#include "/common/script.ftl" >
		<script type="text/javascript" src="${BasePath}/js/jquery.fileEveryWhere.js?${JS_VERSION}"></script>
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
<p>死蟹申请</p>
<div class="dingdanbg"></div>
<div style="clear:both;"></div>
</div>
<div style="float:left;margin:0 0 20px 80px;">
<div class="shenq">

<#if afterSaleRequ??>
	<table>
		<tr>
			<td width="60px" align="right">说明：</td>
			<td>${afterSaleRequ.description!''}</td>
		</tr>
		<tr>
			<td width="60px" align="right">状态：</td>
			<td><#if afterSaleRequ.state==1>
					待处理中
				<#elseif afterSaleRequ.state==2>
					已下补货单
				<#elseif afterSaleRequ.state==3>
					已拒绝
				</#if>
			</td>
		</tr>
		<tr>
			<td width="60px" align="right">申请时间：</td>
			<td>${afterSaleRequ.createTime?string('yyyy-MM-dd HH:mm')}</td>
		</tr>
		<#if afterSaleRequ.state!=1>
			<tr>
				<td width="60px" align="right">处理时间：</td>
				<td>${afterSaleRequ.dealTime?string('yyyy-MM-dd HH:mm')}</td>
			</tr>
			<tr>
				<td width="60px" align="right">处理说明：</td>
				<td>${afterSaleRequ.dealInfo!''}</td>
			</tr>
		</#if>
		
		<tr>
			<td width="60px" align="right">照片：</td>
			<td>
				<#if afterSaleRequ.pic1Url??><img src="${afterSaleRequ.pic1Url!''}" style="width:160px;height:160px;padding:5px;"/></#if>
				<#if afterSaleRequ.pic2Url??><img src="${afterSaleRequ.pic2Url!''}" style="width:160px;height:160px;padding:5px;"/></#if>
				<#if afterSaleRequ.pic3Url??><img src="${afterSaleRequ.pic3Url!''}" style="width:160px;height:160px;padding:5px;"/></#if>
			</td>
		</tr>
	</table>
<#else>
<form name="afterSaleRequForm" id="afterSaleRequForm" action="afterSaleRequ.sc" method="POST" enctype="multipart/form-data"> 
	<input type="hidden" name="orderMainNo" value="${orderMain.orderMainNo}"/>
	<table>
		<tr>
			<td width="60px" align="right">说明：</td>
			<td>
				<textarea name="description" id="description" rows="6" cols="60" style="font-size:12px;"></textarea>
				<div style="color:#F00; margin:5px 0 10px 5px;">请至少输入50个说明文字</div>
			</td>
		</tr>
		<tr>
			<td width="60px" align="right">上传照片：</td>
			<td>
				<div style="height:24px;line-height:24px;">
					<input type="file" name="pic1File"  id="pic1File" size="48" value="选择照片1"  onchange="checkImg(this)"></input>
				</div>
				<div style="height:24px;line-height:24px;">
					<input type="file" name="pic2File" id="pic2File" size="48" value="选择照片2"  onchange="checkImg(this)"></input>
				</div>
				<div style="height:24px;line-height:24px;">
					<input type="file" name="pic3File" id="pic3File" size="48" value="选择照片3"  onchange="checkImg(this)"></input>
				</div>
				<div style="color:#F00; margin:5px 0 0 5px;">最多上传3张照片,每张照片最大不超过1M</div>
				<img id="imgSizeBox" style="display:none"/>
			</td>
		</tr>
		<tr>
			<td width="60px" align="right"></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="60px" align="right"></td>
			<td><input type="button" value="提交" onclick="submitAfterSale()"></input></td>
		</tr>
	</table>
</form>
<script language="javascript">
	$("#title a:eq(2)").attr("id","nav_hover");
		
	$("input:file").fileEveryWhere(); 
	
	function checkImg(obj){
		var fileName=obj.value;
		if(JunHai.Util.isEmpty(fileName)){
			return;
		}
		
		//判断文件类型
		var exd=fileName.substring(fileName.lastIndexOf(".")+1).toUpperCase();
		if(exd!="GIF" && exd!="JPG"){
			alert("只能上传JPG或GIF格式的图片");
			obj.value='';
			return;
		}
		
		//判断图片大小
		var imgsize=getFileSize(obj);
		if(imgsize>1024000){
			alert("每张照片最大不超过1M");
			obj.value='';
			return;
		}
	}
	
	function submitAfterSale(){
		if(JunHai.Util.isEmpty($('#description').val())||$('#description').val().length<50){
			alert("请至少输入50个说明文字");
			return;
		}
		
		if(JunHai.Util.isEmpty($('#pic1File').val())&&JunHai.Util.isEmpty($('#pic2File').val())&&
		JunHai.Util.isEmpty($('#pic3File').val())){
			alert("请至少上传一张死蟹图片");
			return;
		}
		
		$('#afterSaleRequForm').submit();
	}
	
	function getFileSize(obj){  
        var objValue = obj.value;  
        if (objValue=="") return ;  
        var fileLenth=-1;  
        try {  
            //对于IE判断要上传的文件的大小  
            var fso = new ActiveXObject("Scripting.FileSystemObject");  
            fileLenth=parseInt(fso.getFile(objValue).size);  
        } catch (e){  
            try{  
                //对于非IE获得要上传文件的大小  
                 fileLenth=parseInt(obj.files[0].size);  
            }catch (e) {  
                fileLenth=-1;  
            }  
        }  
        return fileLenth;  
    }  
</script>
</#if>
</div>
</div>
<div style=" clear:both;"></div>
<div style=" clear:both;"></div>
</div>
</div>
<div style=" clear:both;"></div>
</div>

<#include "/common/footer.ftl" >
</body>
</html>