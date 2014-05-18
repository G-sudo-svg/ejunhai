function createOrder(){
	var consignee = $("#consignee").val();
	var areaCode = $("#areaCode").val();
	var detailAddress = $("#detailAddress").val();
	var mobilePhone = $("#mobilePhone").val();
	var orderDate = $("#orderDate").val();
	if(JunHai.Util.isEmpty(orderDate)){
		alert("请选择预约时间");
		$('html,body').animate({scrollTop: $("#orderleft").offset().top},1000);
		return;
	}
	
	if(JunHai.Util.isEmpty(consignee)){
		$("#consignee_tip").show();
		$('html,body').animate({scrollTop: $("#consignee").offset().top},1000);
		return;
	}else{
		$("#consignee_tip").hide();
	}
	
	if(JunHai.Util.isEmpty(areaCode)){
		$("#provinceCityArea_tip").show();
		$('html,body').animate({scrollTop: $("#district").offset().top},1000);
		return;
	}else{
		$("#provinceCityArea_tip").hide();
	}
	
	if(JunHai.Util.isEmpty(detailAddress)){
		$("#detailAddress_tip").show();
		$('html,body').animate({scrollTop: $("#detailAddress").offset().top},1000);
		return;
	}else{
		$("#detailAddress_tip").hide();
	}
	
	if(JunHai.Util.isEmpty(mobilePhone)){
		$("#mobilePhone_tip").show();
		$('html,body').animate({scrollTop: $("#mobilePhone").offset().top},1000);
		return;
	}else{
		$("#dmobilePhone_tip").hide();
	}
	
	var rePhone = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|2|3|5|6|7|8|9])\d{8}$/;
	if(!rePhone.test(mobilePhone)){
		$("#mobilePhone_tip").show();
		$('html,body').animate({scrollTop: $("#mobilePhone").offset().top},1000);
		return;
	}else{
		$("#dmobilePhone_tip").hide();
	}
	
	$("#orderDate").val(buildDate(orderDate));
	$("#orderForm").submit();
}

function buildDate(orderDate){
	var strs = [];
	strs = orderDate.split('-');
	var year = strs[0];
	var month = parseInt(strs[1])<10?"0"+parseInt(strs[1]):parseInt(strs[1]);
	var day = parseInt(strs[2])<10?"0"+parseInt(strs[2]):parseInt(strs[2]);
	return year+"-"+month+"-"+day;
}
