<#if orderLogisticsTrack??>
{
	"status":"${orderLogisticsTrack.status!''}",
	"errCode":"${orderLogisticsTrack.errCode!''}",
	"message":"${orderLogisticsTrack.message!''}",
	"data":[
		<#if orderLogisticsTrack.data?size &gt; 0>
			<#list orderLogisticsTrack.data as data>{
					"time" : "${data.time!""}",
					"context" : "${data.context!""}"
				}
				<#if data_index != orderLogisticsTrack.data?size - 1>,</#if>
			</#list>
		</#if>
	]
}
</#if>