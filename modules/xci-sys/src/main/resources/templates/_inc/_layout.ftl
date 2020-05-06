<#--去字符串中html标签,对html字符不转义-->
<#--<#import "/spring.ftl" as spring>-->
<#--<#ftl output_format="HTML" strip_whitespace=true>默认对html字符转义(安全性)-->
<#-- @ftlvariable name="_create_" type="java.lang.String" -->
<#macro header title="" bodyCls="">
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>${title!}</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit"/>
    <meta name="force-rendering" content="webkit"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link href="${web.cdn('/jx.css')}" rel="stylesheet"/>
    <link href="${web.url('/sys/css/sys.css')}" rel="stylesheet"/>
    <#if sys.param("sys.web.largeFont","false")=="true" && !request.getRequestUri()?contains("/login")>
    <link href="${web.url('/sys/css/large.css')}" rel="stylesheet"/>
    </#if>
    <#nested/>
</head>
<body class="${bodyCls}">
<div class="page-loading"></div>
</#macro>
<#macro footer>
    <script src="${web.cdn('/jx.js')}"></script>
    <script src="${web.url('/sys/js/plugin.js')}"></script>
    <script src="${web.url('/sys/js/sys.js')}" data-debug="false" data-page-size="${sys.param("SystemGridPageSize","20")}" data-root-path="${web.url("/")}" data-cdn="${web.cdnRoot()}"></script>
    <#nested/>
</body>
</html>
</#macro>


<#function saveUrl base create="" edit="">
    <#if _create_?? && _create_ == "1">
        <#return "${springMacroRequestContext.getContextUrl(base+create)}">
    <#else>
        <#return "${springMacroRequestContext.getContextUrl(base+edit)}">
    </#if>
</#function>

<#--格式化日期-->
<#macro formatDate date format="yyyy-MM-dd"><#if date??>${web.formatDate(date,format)}</#if></#macro>
<#--格式化日期-->
<#macro formatDateTime date format="yyyy-MM-dd HH:mm:ss"><#if date??>${web.formatDate(date,format)}</#if></#macro>

<#--是否新建记录-->
<#macro isCreate>
    <#if _create_?? && _create_ == "1">
        <#nested/>
    </#if>
</#macro>

<#--是否编辑记录-->
<#macro isEdit>
    <#if !_create_?? || _create_ == "0" || _create_ == "">
        <#nested/>
    </#if>
</#macro>

<#--编辑表单工具栏-->
<#macro editPanelFooterSave>
    <button class="btn btn-primary" type="button" onclick="jx.auth.editFormSave()">
        <i class="fa fa-save"></i> 保存
    </button>
    <button class="btn btn-default" type="button" onclick="jx.auth.editFormCancel()">
        <i class="fa fa-sign-in"></i> 取消
    </button>
</#macro>

<#macro editPanelFooter>
    <@isCreate>
        <button class="btn btn-success" type="button" onclick="jx.auth.editFormContinuousSave()">
            <i class="icon-plus"></i> 连续新增
        </button>
    </@isCreate>
    <@editPanelFooterSave/>
</#macro>
<#macro editPanelPonyFooter>
    <@isCreate>
        <button class="btn btn-success" type="button" onclick="jx.auth.editFormContinuousSave()">
            <i class="icon-plus"></i> 保存并新增
        </button>
    </@isCreate>
    <@editPanelFooterSave/>
</#macro>

<#--输入警告标签-->
<#macro warningMessage test msg>
    <#if test>
        <div class="alert alert-warning margin-5 padding-10">${msg}</div>
    </#if>
</#macro>

<#--引入UE脚本-->
<#macro ueScript>
<script src="${web.url('/lib/ueditor/ueditor.config.js')}"></script>
<script src="${web.url('/lib/ueditor/ueditor.all.min.js')}"></script>
</#macro>

<#--UE服务器路径-->
<#macro ueditorServerUrl>${web.siteFullUrl()}${request.getContextUrl("/ueditor/config")}</#macro>

<#--editormd图片上传服路径-->
<#macro mdEditorImageUploadURL>${web.siteFullUrl()}${request.getContextUrl("/editormd/upload")}</#macro>

<#--kindeditor文件上传服路径-->
<#macro kindeditorUploadUrl>${web.siteFullUrl()}${request.getContextUrl("/kindeditor/upload")}</#macro>

<#--kindeditor文件管理服路径-->
<#macro kindeditorFileManagerUrl>${web.siteFullUrl()}${request.getContextUrl("/kindeditor/manager")}</#macro>

<#--条件输出-->
<#macro echo test>
    <#if test>
        <#nested/>
    </#if>
</#macro>