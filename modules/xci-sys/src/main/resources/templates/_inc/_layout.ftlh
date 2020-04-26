<#--<#import "/spring.ftl" as spring>-->
<#--<#ftl output_format="HTML" strip_whitespace=true>默认对html字符转义(安全性)-->
<#--<#ftl output_format=“undefined”> 去字符串中html标签,对html字符不转义-->
<#import "/_inc/_project.ftlh" as xci>
<#import "/_inc/_dic.ftlh" as dic>
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
    <#if sys.param("SystemLargeFontMode","0")=="1" && !request.getRequestUri()?contains("/login")>
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