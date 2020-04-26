<#-- @ftlvariable name="entity" type="com.xci.sys.model.LockUserModel" -->
<#include "/_inc/_layout.ftlh">
<@header/>
    <div class="jxpanel jx-overflow winpanel" data-options="fit:true">
        <table id="basic" class="table table-bordered jxtable-details">
            <tr>
                <th class="w-100px">登陆账户</th>
                <td>${entity.account}</td>
            </tr>
            <tr>
                <th>错误次数</th>
                <td>${entity.count}</td>
            </tr>
            <tr>
                <th>登录时间</th>
                <td>${web.formatDateTime(entity.loginTime)}</td>
            </tr>
            <tr>
                <th>解锁时间</th>
                <td>${web.formatDateTime(entity.disLockTime)}</td>
            </tr>
        </table>
    </div>
<@footer/>