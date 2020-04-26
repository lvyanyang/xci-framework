<#-- @ftlvariable name="entity" type="com.xci.platform.auth.entity.DocEntity" -->
<#include "/_inc/_layout.ftl">
<#include "/auth/history/_history.ftl">
<@header>
    <#if entity.docFormat==1>
        <link href="http://113.140.71.251:1000/cdn/jx/2.0.0/lib/kindeditor/themes/iframe.css" rel="stylesheet" type="text/css">
        <style>
            pre code, pre {
                background: none !important;
            }
        </style>
    <#else>
        <link href="<@html.url '/auth/css/article.css'/>" rel="stylesheet" type="text/css">
        <style>
            .article-detail .content li {
                list-style-type: decimal !important;
            }
            li.L1,li.L3,li.L5,li.L7,li.L9 {
                background: none !important;
            }
        </style>
    </#if>
    <link href="http://113.140.71.251:1000/cdn/jx/2.0.0/lib/kindeditor/plugins/code/prettify.css" rel="stylesheet" type="text/css">
    <script src="http://113.140.71.251:1000/cdn/jx/2.0.0/lib/kindeditor/plugins/code/prettify.js"></script>
</@header>
<div class="jxpanel jx-overflow-no winpanel" data-options="fit:true">
    <div class="jxtabs jxtabs-line" data-options="{fit:true}">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#tab-basic" data-toggle="tab">基本信息</a></li>
            <li><a href="#tab-history" data-toggle="tab">操作日志</a></li>
        </ul>
        <div class="tab-content">
            <div id="tab-basic" class="tab-pane jx-overflow-auto fade in active">
                <table id="basic" class="table table-bordered jxtable-details">
                    <tr>
                        <th class="w-100px">标题</th>
                        <td><a href="<@html.url '/auth/doc/details?id=${entity.id}'/>" target="_blank">${entity.title!}</a></td>
                        <th class="w-100px">分类</th>
                        <td class="w-200px">${entity.categoryName!}</td>
                    </tr>
                    <tr>
                        <th>标签</th>
                        <td colspan="3">${entity.tag!}</td>
                    </tr>
                    <tr>
                        <th>状态</th>
                        <td><@html.boolLabel value=entity.status trueLable = "发布" falseLable = "草稿"/></td>
                        <th>更新时间</th>
                        <td>${helper.formatDateTime(entity.operateDateTime)!}</td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <#if entity.docFormat==1>
                                ${entity.docContent!}
                            <#else>
                                <div class="article-detail">
                                    <div id="markdownContent" class="content">
                                        <textarea style="display:none;">${entity.docContent!}</textarea>
                                    </div>
                                </div>
                            </#if>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="tab-history" class="tab-pane jx-overflow-auto fade">
                <@histroy tableName="sys_doc" keyValue=entity.id/>
            </div>
        </div>
    </div>
    <div class="panel-footer text-right">
        <button class="btn btn-default" type="button" onclick="jx.closeDialog()">
            <i class="fa fa-sign-in"></i> 关闭
        </button>
    </div>
</div>
<span class="tabs-close">
    <button type="button" class="close" onclick="jx.closeDialog()"><span aria-hidden="true">&times;</span></button>
</span>
<@footer>
    <script src="http://113.140.71.251:1000/cdn/jx/2.0.0/lib/editormd/lib/marked.min.js"></script>
    <script>
        jx.auth.tabsAuthInit();
        prettyPrint();
        $(document.body).on('keydown', function (e) {
            if (e.keyCode === 27) { //Esc
                jx.closeDialog();
            }
        });
        <#if entity.docFormat==2>
            jx.ready('editormd', function () {
                editormd.markdownToHTML("markdownContent", {
                    htmlDecode: "style,script,iframe",
                    emoji: true,
                    taskList: true,
                    tex: false,
                    flowChart: false,
                    sequenceDiagram: false
                });
            });
        </#if>
    </script>
</@footer>