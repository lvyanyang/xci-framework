<#ftl strip_whitespace=true>
<#import '/_inc/_project.ftlh' as pro>

<#--数据字典树-->
<#macro dicTree categoryCode nameField="" idField="" selectedValue=""
    showClear=false defaultIconCls="icon-badge" panelHeight="250px" loadEmptyMessage="无">
    <input name="${nameField}" class='form-control jxcombotree'
           data-value="${selectedValue}"
           data-options="editable: false,showClear: ${showClear},hiddenField: '${idField}',panelHeight: '${panelHeight}',
           treeOptions: {url: '${web.url('/sys/dicTree?categoryCode='+categoryCode)}',defaultIconCls: '${defaultIconCls}',
           loadEmptyMessage: '${loadEmptyMessage}'}"/>
</#macro>


<#--角色分类编码-->
<#macro sysRoleCategory selectedValue="" isMultiple = false>
    <@pro.systemDic code="sys.role.category" selectedValue=selectedValue isMultiple=isMultiple />
</#macro>

<#--参数分类编码-->
<#macro sysParamCategory selectedValue>
    <@pro.systemDic code="sys.param.category" selectedValue=selectedValue isMultiple=false />
</#macro>

<#--用户职位-->
<#macro sysParamCategory selectedValue>
    <@pro.systemDic code="sys.user.post" selectedValue=selectedValue isMultiple=false />
</#macro>

<#--机构分类-->
<#macro systemDeptCategory selectedValue="">
    <@pro.systemDic code="sys.dept.category" selectedValue=selectedValue isMultiple=false />
</#macro>
<#macro systemDeptCategory selectedValue="" nameField="" idField="">
    <@dicTree categoryCode="sys.dept.category" nameField=nameField idField=idField selectedValue=selectedValue showClear=true/>
</#macro>
<#--机构性质-->
<#macro systemDeptNature selectedValue="">
    <@pro.systemDic code="sys.dept.nature" selectedValue=selectedValue isMultiple=false />
</#macro>
<#macro systemDeptNatureTree selectedValue="" nameField="" idField="">
    <@dicTree categoryCode="sys.dept.nature" nameField=nameField idField=idField selectedValue=selectedValue showClear=true/>
</#macro>