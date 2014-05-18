var calendarObj = null;
var calendar = function (date,startDate,endDate,deferDate) {
    this.staticdate = new Date(date);
    this.statictime = this.staticdate.getTime();
    this.date = new Date(date);
	this.today = date;
    this.lastid = null;
	this.deferDate = deferDate;
	this.startDate = startDate;
	this.endDate = endDate;
	this.firstid = null;
    this.lastselectid = null;
    this.tempclass = null;

    this.createcalendar = function (seek) {
		this.lastselectid = null;
		$("#orderDate").val("");
        var tempdate = seek ? new Date(Date.parse(this.date.getFullYear() + "/" + (this.date.getMonth() + 1 + seek) + "/1")) : this.date;
        var year = tempdate.getFullYear();
        var month = tempdate.getMonth() + 1;
        var firstdayWeek = new Date(Date.parse(year + "/" + month + "/" + "1")).getDay();
        var endday= new Date(Date.parse(year + "/" + (month + 1) + "/" + "1") - (24 * 60 * 60 * 1000)).getDate();
        var monthsday = endday; //月天数
        var html = "";
        //计算前后
        var dayseek = 42 - monthsday;
        var frontsday = firstdayWeek;
        var calenarr = [];
        //前补丁
        var upmonthfirstday = new Date(Date.parse(year + "/" + month + "/" + "1") - frontsday * (24 * 60 * 60 * 1000));
        var nextmonthfirstday= new Date(Date.parse(year + "/" + (month + 1) + "/" + "1"));
        
        for (var i = 0; i < frontsday; i++) {
            calenarr.push(upmonthfirstday.getFullYear() + "-" + (upmonthfirstday.getMonth() + 1) + "-" + (upmonthfirstday.getDate() + i));
        }
        for (var i = 1; i <= endday; i++) {
            calenarr.push(tempdate.getFullYear() + "-" + (tempdate.getMonth() + 1) + "-" + i);
        }
        for (var i = 1; i <= dayseek - frontsday; i++) {
            calenarr.push(nextmonthfirstday.getFullYear() + "-" + (nextmonthfirstday.getMonth() + 1) + "-" + i);
        }
        //本月数据
        var id = null, idstr = null, price = null, state = null, classstr = null, sellstr = null; stylestr = null, titlestr = null;

        //get.byId("upmonth").style.display = 'none';

		var _deferDate = 0;
		if(parseInt(this.deferDate) !=0){
			_deferDate = parseInt(this.deferDate) + 1;
		}
		this.firstid = calenarr[0];
		this.lastid = calenarr[41];
//		alert(this.firstid +"=="+this.lastid);
		
		var isUse = function(date){
			var strs = [];
			strs = date.split('-');
			
			var year = strs[0];
			var month = parseInt(strs[1])<10?"0"+parseInt(strs[1]):parseInt(strs[1]);
			var day = parseInt(strs[2])<10?"0"+parseInt(strs[2]):parseInt(strs[2]);
			date = year+"-"+month+"-"+day;
			var _date =  new Date(date.replace(/-/g, "/")).getTime();
			var _startDate = new Date(calendarObj.startDate.replace(/-/g, "/")).getTime();
			var _endDate = new Date(calendarObj.endDate.replace(/-/g, "/")).getTime();
			if(_date >= _startDate && _date <= _endDate){
				//alert("date="+date+"\nstartDate="+calendarObj.startDate+"\nendDate="+calendarObj.endDate+"\nreulst=true");
				return true;
			}else{
				//alert("date="+date+"\nstartDate="+calendarObj.startDate+"\nendDate="+calendarObj.endDate+"\nreulst=false");
				return false;
			}
		};
		
		//isUse("2012-12-1");
		//alert(calenarr);
		//return;
		var isValid3 = false;
        var today = new Date(calendarObj.staticdate.getFullYear() + "/" + (calendarObj.staticdate.getMonth() + 1) + "/" + calendarObj.staticdate.getDate()).getTime();
        var endDay = new Date(calendarObj.endDate.replace(/-/g, "/")).getTime();
		if(today + (_deferDate -1) * 24*60*60*1000 >= endDay){
			isValid3 = true;
        }
		
		var initData = function(jsonData){
			jsonData = eval('(' + jsonData + ')');
			
			for (var i = 0; i < 42; i++) {
	            id = calenarr[i];
	            var data = jsonData[id];
	            idstr = " id='" + id + "'", price = "", state = "", classstr = "", sellstr = " sell='off'", stylestr = "", titlestr = "";
	            // 是否在有效期内
	            var isValid = isUse(id);
	            // 今天+推迟天数后有效
//	            var isValid1 = false;
//	            var curDay = new Date(id.replace(/-/g, "/")).getTime();
//	            var today = new Date(calendarObj.staticdate.getFullYear() + "/" + (calendarObj.staticdate.getMonth() + 1) + "/" + calendarObj.staticdate.getDate()).getTime();
//	            var endDay = new Date(calendarObj.endDate.replace(/-/g, "/")).getTime();
	            var isValid1 = new Date(id.replace(/-/g, "/")).getTime() >= (new Date(calendarObj.staticdate.getFullYear() + "/" + (calendarObj.staticdate.getMonth() + 1) + "/" + calendarObj.staticdate.getDate()).getTime() + _deferDate *24*60*60*1000);
	            //alert(new Date(calendarObj.staticdate.getFullYear() + "/" + (calendarObj.staticdate.getMonth() + 1) + "/" + (parseInt(calendarObj.staticdate.getDate()))));
	            // 券数量
	            var isValid2 = parseInt(data.count) < parseInt(data.sum);
	            //alert("id="+id+"\nisValid="+isValid+"\nisValid1="+isValid1+"\nisValid2="+isValid);
	            if (isValid && (isValid1 || (isValid3 && new Date(id.replace(/-/g, "/")).getTime() >= new Date(calendarObj.staticdate.getFullYear() + "/" + (calendarObj.staticdate.getMonth() + 1) + "/" + calendarObj.staticdate.getDate()).getTime())) && isValid2) {
	            	classstr = " class='coming'";
	            	stylestr = "style='cursor:pointer;'";
	            	sellstr = " sell='on'";
	            	state = '<span style="background:none; color:orange;" class="sellout">可预订</span>';
	            }else {
	            	sellstr = " sell='off'";
	            	stylestr = "style='background-color:#E4E4E4;'";
	            }
	            if (id.replace(/-/g, "/") == (new Date().getFullYear() + "/" + (new Date().getMonth() + 1) + "/" + new Date().getDate())) {
	                html += "<li onclick='calendarObj.clickwork(this);' " + idstr + classstr + sellstr + stylestr + titlestr + ">" + state + "<em>" + new Date(Date.parse(id.replace(/-/g, "/"))).getDate() + "</em><font color='#000000'>"+data.count+"/"+data.sum+"</font>" + price + "<span class='arrowbg'></span><span class='arrowtext'>今</span>";
	            } else {
	                html += "<li onclick='calendarObj.clickwork(this);' " + idstr + classstr + sellstr + stylestr + titlestr + ">" + state + "<em>" + new Date(Date.parse(id.replace(/-/g, "/"))).getDate() + "</em><font color='#000000'>"+data.count+"/"+data.sum+"</font>" + price;
	            }
	            html +=state!=""? "<span class='tip'>单击选择发货时间</span></li>":"</li>";
	        }
	        seek == 1 && (get.byId("upmonth").style.display == 'none') && (get.byId("upmonth").style.display = 'block');
	        calendarObj.date = tempdate;
	        get.byId("dayTable").innerHTML = html;
	        get.byId("dayTitle").innerHTML = year + "年" + month + "月";
	        $("#dayTable li").hover(
	        	function(){$(this).find(".tip").attr({"style": 'display:inline'}).animate({opacity: 1, top: "-30"}, {queue:false, duration:400});}, 
	        	function(){$(this).find(".tip").animate({opacity: 0, top: "-20"}, {queue:false, duration:400, complete: function(){$(this).attr({"style": 'display:none'});}});
	        });
		};
		
		$.ajax({
			type:'post',
			url:"/front/getCouponData.sc",
			data:{'startDate':this.firstid,'endDate':this.lastid},
			success:function(result){
				if(JunHai.Util.isEmpty(result)){
				}else{
					initData(result);
				}
			}
		});
    };
    this.clickwork = function (e) {
        //alert("clickwork");
		var _e = $("#"+e.id);
		if(_e.attr("sell") == "off"){
			return;
		}
		if(this.lastselectid){
			get.byId(this.lastselectid).style.backgroundColor = "#FFFFFF";
		}
		e.style.backgroundColor = "#F6680C";
		this.lastselectid = e.id;
		$("#orderDate").val(e.id);
    };
};

//新建日历对象
function loadcalendar(date,startDate,endDate,deferDate) {
    calendarObj = new calendar(date.replace(/-/g, "/"),startDate,endDate,deferDate);
    calendarObj.createcalendar();
    EventUtil.addHandler(get.byId("upmonth"), "click", function () { calendarObj.createcalendar(-1); });
    EventUtil.addHandler(get.byId("nextmonth"), "click", function () { calendarObj.createcalendar(1); });
}