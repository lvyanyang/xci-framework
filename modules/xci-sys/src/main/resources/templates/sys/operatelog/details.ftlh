<#-- @ftlvariable name="entity" type="com.xci.sys.entity.SysOperateLog" -->
<#include "/_inc/_layout.ftlh">
<@header/>
<div class="jxpanel winpanel" data-fit="true">
    <table class="table table-bordered jxtable-details">
        <tr>
            <th class="w-100px">消息</th>
            <td>${entity.msg}</td>
        </tr>
        <tr>
            <th>操作用户</th>
            <td>${entity.operateUserName}</td>
        </tr>
        <tr>
            <th>操作机构</th>
            <td>${entity.operateDeptName}</td>
        </tr>
        <tr>
            <th>操作IP</th>
            <td>${entity.ip}</td>
        </tr>
        <tr>
            <th>操作时间</th>
            <td>${web.formatDateTime(entity.operateDateTime)}</td>
        </tr>
        <tr>
            <th>功能模块</th>
            <td>${entity.tag}</td>
        </tr>
        <tr>
            <th>后台方法</th>
            <td>${entity.method}</td>
        </tr>
        <tr>
            <th>执行参数</th>
            <td>
                <#if web.notBlank(entity.executeParam)>
                    <pre>${entity.executeParam}</pre>
                </#if>
            </td>
        </tr>
        <tr>
            <th>执行返回值</th>
            <td>
                <#if web.notBlank(entity.executeResult)>
                    <pre>${entity.executeResult}</pre>
                </#if>
            </td>
        </tr>
        <tr>
            <th>请求方法</th>
            <td>${entity.reqMethod}</td>
        </tr>
        <tr>
            <th>请求地址</th>
            <td>${entity.reqUrl}</td>
        </tr>
        <tr>
            <th>耗时</th>
            <td>${entity.costTimeName}</td>
        </tr>
        <tr>
            <th>UserAgent</th>
            <td>${entity.userAgent}</td>
        </tr>
        <tr>
            <th>执行结果</th>
            <td><@xci.boolLabel value=entity.status trueLable="成功" falseLable="失败"/></td>
        </tr>
        <tr>
            <th>应用ID</th>
            <td>${entity.appId}</td>
        </tr>
        <tr>
            <th>应用名称</th>
            <td>${entity.appName}</td>
        </tr>
    </table>
</div>
<@footer/>