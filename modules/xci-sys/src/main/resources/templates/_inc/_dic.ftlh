<#ftl strip_whitespace=true>
<#import '/_inc/_project.ftlh' as pro>
<#--数据字典-->

<#--角色分类编码-->
<#macro sysRoleCategory selectedValue="" isMultiple = false>
    <@pro.systemDic code="sys.role.category" selectedValue=selectedValue isMultiple=isMultiple />
</#macro>

<#--参数分类编码-->
<#macro sysParamCategory selectedValue>
    <@pro.systemDic code="sys.param.category" selectedValue=selectedValue isMultiple=false />
</#macro>

<#--机构分类-->
<#macro systemDeptCategory selectedValue="">
    <@pro.systemDic code="sys.dept.category" selectedValue=selectedValue isMultiple=false />
</#macro>
<#--机构性质-->
<#macro systemDeptNature selectedValue="">
    <@pro.systemDic code="sys.dept.nature" selectedValue=selectedValue isMultiple=false />
</#macro>

<#--内容字典分类编码-->
<#macro systemContentCategory selectedValue="" isMultiple = false>
    <@pro.systemDic code="DicSystemContentCategory" selectedValue=selectedValue isMultiple=isMultiple />
</#macro>