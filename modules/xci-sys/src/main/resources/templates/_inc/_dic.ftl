<#ftl strip_whitespace=true>

<#--数据字典树-->
<#macro dicTree categoryCode nameField="" idField="" selectedValue=""
    showClear=false defaultIconCls="icon-badge" panelHeight="250px" loadEmptyMessage="无">
    <input name="${nameField}" class='form-control jxcombotree'
           data-value="${selectedValue}"
           data-options="editable: false,showClear: ${showClear},hiddenField: '${idField}',panelHeight: '${panelHeight}',
           treeOptions: {url: '${web.url('/sys/dicTree?categoryCode='+categoryCode)}',defaultIconCls: '${defaultIconCls}',
           loadEmptyMessage: '${loadEmptyMessage}'}"/>
</#macro>
<#macro systemDeptCategory selectedValue="" nameField="" idField="">
    <@dicTree categoryCode="sys.dept.category" nameField=nameField idField=idField selectedValue=selectedValue showClear=true/>
</#macro>
<#macro systemDeptNatureTree selectedValue="" nameField="" idField="">
    <@dicTree categoryCode="sys.dept.nature" nameField=nameField idField=idField selectedValue=selectedValue showClear=true/>
</#macro>

