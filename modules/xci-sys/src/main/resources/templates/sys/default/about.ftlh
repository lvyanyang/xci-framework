<#-- @ftlvariable name="releaseHistoryList" type="java.util.List<com.xci.platform.auth.entity.ReleaseHistoryEntity>" -->
<#include "/_inc/_layout.ftlh">
<@header>
<link href="${web.url('/sys/css/timeline.css')}" rel="stylesheet"/>
<style>
    .tab-content {
        line-height: 2em;
    }

    .tab-pane-log {
        padding: 20px;
    }

    .tab-pane-content {
        padding: 20px;
    }

    .jxsection-content {
        padding-left: 10px;
    }

    .color-new {
        color: #00BF00;
    }

    .color-fix {
        color: #3DA4F2;
    }

    .color-optimize {
        color: #FFC200;
    }
</style>
</@header>
<div class="jxpanel jx-overflow-no winpanel" data-options="fit:true">
    <div class="jxtabs jxtabs-line" data-options="fit:true">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#tab-system" data-toggle="tab">关于系统</a></li>
            <li><a href="#tab-log" data-toggle="tab">升级日志</a></li>
        </ul>
        <div class="tab-content">
            <div id="tab-system" class="tab-pane jx-overflow-auto fade in active">
                <div class="tab-pane-content">
                ${auth.getContentValue("SystemAboutSoftwareIntroduce")}
                    <div>
                        <div class="jxsection-container">软件版本</div>
                        ${auth.getContentValue("SystemAboutSoftwareVersionInfo")}
                    </div>
                    <div>
                        <div class="jxsection-container">公司简介</div>
                        ${auth.getContentValue("SystemAboutCompanyInfo")}
                    </div>
                    <div>
                        <div class="jxsection-container">联系我们</div>
                        ${auth.getContentValue("SystemAboutAddressInfo")}
                    </div>
                </div>
            </div>
            <div id="tab-log" class="tab-pane jx-overflow-auto fade">
                <ul class="tab-pane-log timeline">
                    <#list releaseHistoryList as entity>
                        <li class="timeline-inverted">
                            <div class="timeline-badge ${entity.colorSchema}">${entity.version}</div>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <p>
                                        <small class="text-muted"><i
                                                class="fa fa-clock-o"></i> ${helper.formatDateTime(entity.operateDateTime)}</small>
                                    </p>
                                </div>
                                <div class="timeline-body">
                                    <#if entity.newContent?? && entity.newContent !="">
                                        <div class="jxsection-container color-new">新增</div>
                                        <div class="jxsection-content">
                                            ${entity.newContent}
                                        </div>
                                    </#if>
                                    <#if entity.newContent?? && entity.fixContent !="">
                                        <div class="jxsection-container color-fix">修复</div>
                                        <div class="jxsection-content">
                                            ${entity.fixContent}
                                        </div>
                                    </#if>
                                    <#if entity.optimizeContent?? && entity.optimizeContent !="">
                                        <div class="jxsection-container color-optimize">优化</div>
                                        <div class="jxsection-content">
                                            ${entity.optimizeContent}
                                        </div>
                                    </#if>
                                </div>
                            </div>
                        </li>
                    </#list>
                </ul>
            </div>
        </div>
    </div>
</div>
<span class="tabs-close">
    <button type="button" class="close" onclick="jx.closeDialog()"><span aria-hidden="true">&times;</span></button>
</span>
<@footer/>