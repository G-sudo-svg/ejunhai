<!DOCTYPE html PUbliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统出错</title>
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-base.css"/>
<link rel="stylesheet" type="text/css" href="${BasePath}/styles/sys-global.css"/>
</head> 
<body>
<div class="container"> 
    <!--工具栏start--> 
    <div class="toolbar"> </div>
    <div class="list_content"> 
    	<div class="detail_top_box" style="font-size:15px;color:red;font-weight:600">
    		系统异常：${errorDesc!'系统出错'}
    	</div>
    	<div class="detail_top_box">
    		<#list errorDesc.stackTrace as item>  
    			${item}<br/>
			 </#list>  
    	</div>
    </div>
    
</div>
</body>
</html>
