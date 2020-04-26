<#--<#ftl output_format="undefined">-->
<#-- @ftlvariable name="entity" type="com.github.lvyanyang.sys.entity.SysHistoryLog" -->
<#include "/_inc/_layout.ftlh">
<@header/>
<div class="jxpanel winpanel" data-fit="true">
<#--    <div class="jxsection-container">基本信息</div>-->
    <table class="table table-bordered jxtable-details">
        <#--<tr>-->
        <#--    <th>主键</th>-->
        <#--    <td>${entity.id}</td>-->
        <#--</tr>-->
        <tr>
            <th>操作类型</th>
            <td>${entity.categoryName}(${entity.category})</td>
        </tr>
        <tr>
            <th class="w-100px">数据主键</th>
            <td>${entity.primaryKey}</td>
        </tr>
        <tr>
            <th>数据表名</th>
            <td>${entity.tableName}</td>
        </tr>
        <#if web.notBlank(entity.beforeData)>
            <tr>
                <th>操作前数据</th>
                <td>
                    <pre>${entity.beforeData}</pre>
                </td>
            </tr>
        </#if>
        <#if web.notBlank(entity.afterData)>
            <tr>
                <th>操作后数据</th>
                <td>
                    <pre>${entity.afterData}</pre>
                </td>
            </tr>
        </#if>
        <#if web.notBlank(entity.diff)>
        <tr>
            <th>差异</th>
            <td>
                <pre>${entity.diff}</pre>
            </td>
        </tr>
        </#if>
        <tr>
            <th>操作用户</th>
            <td>${entity.operateUserName}</td>
        </tr>
        <tr>
            <th>操作时间</th>
            <td>
                ${web.formatDateTime(entity.operateDateTime)}
            </td>
        </tr>
    </table>
</div>
<@footer/>