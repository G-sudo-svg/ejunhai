{
	"message":"${message!''}",
	"couponSchemeId":"${couponSchemeId!''}",
		<#if result?size &gt; 0>
			<#list result as data>
				"${data.useDate!""}" : {
					"count" : "${data.useCount?c}",
					"sum" : "${data.sumCount?c}"
				}
				<#if data_index != result?size - 1>
					,
				</#if>
			</#list>
		</#if>
}