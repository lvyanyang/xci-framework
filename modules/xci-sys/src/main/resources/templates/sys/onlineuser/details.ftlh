<#-- @ftlvariable name="entity" type="com.xci.sys.model.OnlineUserModel" -->
<#include "/_inc/_layout.ftlh">
<@header/>
<div class="jxpanel jx-overflow winpanel" data-options="fit:true">
    <table id="basic" class="table table-bordered jxtable-details">
        <tr>
            <th>主键</th>
            <td>${entity.id}</td>
        </tr>
        <tr>
            <th class="w-100px">姓名</th>
            <td>
                <a href="#" onclick="jx.auth.showUserDetails('${entity.id}')" data-id="${entity.id}">${entity.name}</a>
            </td>
        </tr>
        <tr>
            <th>账号</th>
            <td>${entity.account}</td>
        </tr>
        <tr>
            <th>登录时间</th>
            <td>${web.formatDateTime(entity.loginTime)}</td>
        </tr>
        <tr>
            <th>激活时间</th>
            <td>${web.formatDateTime(entity.activeTime)}</td>
        </tr>
        <tr>
            <th>部门</th>
            <td>${entity.deptName}(${entity.deptId})</td>
        </tr>
        <tr>
            <th>登录IP</th>
            <td>${entity.ip}</td>
        </tr>
        <tr>
            <th>超管</th>
            <td><@xci.yesnoLabel value=entity.admin /></td>
        </tr>
    </table>
</div>
<@footer/>