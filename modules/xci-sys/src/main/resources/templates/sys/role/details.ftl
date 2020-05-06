<#-- @ftlvariable name="entity" type="com.github.lvyanyang.sys.entity.SysRole" -->
<#-- @ftlvariable name="users" type="java.util.List<com.github.lvyanyang.sys.entity.SysUser>" -->
<#include "/_inc/_layout.ftl">
<#include "/_inc/_historyLog.ftl">
<@header>
    <link href="${web.url('/sys/css/tree-noselect.css')}" rel="stylesheet"/>
</@header>
<div class="jxpanel jx-overflow-no winpanel" data-options="fit:true">
    <div class="jxtabs jxtabs-line" data-options="{fit:true}">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#tab-basic" data-toggle="tab">基本信息</a></li>
            <li><a href="#tab-member" data-toggle="tab">角色成员</a></li>
            <li><a href="#tab-module" data-toggle="tab">功能权限</a></li>
            <li><a href="#tab-dept" data-toggle="tab">机构权限</a></li>
            <li><a href="#tab-history" data-toggle="tab">操作日志</a></li>
        </ul>
        <div class="tab-content">
            <div id="tab-basic" class="tab-pane jx-overflow-auto fade in active">
                <table class="table table-bordered jxtable-details">
                    <tr>
                        <th class="w-100px">角色名称</th>
                        <td>${entity.name}</td>
                        </tr>
                    <tr>
                        <th class="w-100px">角色编码</th>
                        <td>${entity.code}</td>
                    </tr>
                    <tr>
                        <th>所属机构</th>
                        <td>
                            <a href="#" onclick="jx.auth.showDepartmentDetails('${entity.deptId}');">${entity.deptName}</a>
                        </td>
                        </tr>
                    <tr>
                        <th>机构权限</th>
                        <td>${entity.deptScopeName}</td>
                    </tr>
                    <tr>
                        <th>状态</th>
                        <td>${web.statusLabel(entity.status)}</td>
                        </tr>
                    <tr>
                        <th>序号</th>
                        <td>${entity.path}</td>
                    </tr>
                    <tr>
                        <th>备注</th>
                        <td colspan="3">${entity.remark}</td>
                    </tr>
                </table>
            </div>
            <div id="tab-member" class="tab-pane jx-overflow-auto fade">
                <ul class="card-listview">
                    <#list users as u>
                        <li data-id="${u.id}">${u.name}</li>
                    </#list>
                </ul>
                <@warningMessage test=users?size==0 msg="未包含任何用户"/>
            </div>
            <div id="tab-module" class="tab-pane jx-overflow-auto fade">
                <ul class="list-group margin-5">
                    <li class="list-group-item padding-5">
                        <ul id="roleOwnModulesTree" data-url="${web.url('/sys/role/roleOwnModules?roleId='+entity.id)}"
                            data-default-icon-cls="icon-flag"
                            data-load-empty-message="未配置"></ul>
                    </li>
                </ul>
            </div>
            <div id="tab-dept" class="tab-pane jx-overflow-auto fade">
                <ul class="list-group margin-5">
                    <li class="list-group-item">
                        ${entity.deptScopeName}
                    </li>
                    <li class="list-group-item padding-5">
                        <ul id="roleOwnDeptsTree" data-url="${web.url('/sys/role/roleOwnDepts?roleId='+entity.id)}"
                            data-default-icon-cls="icon-flag"
                            data-load-empty-message="">
                        </ul>
                    </li>
                </ul>
            </div>
            <div id="tab-history" class="tab-pane jx-overflow-auto fade">
                <@histroyLog tableName="SysRole" primaryKey=entity.id/>
            </div>
        </div>
    </div>
</div>
<@footer>
    <script>
        jx.auth.tabsAuthInit(function (id) {
            if (id == 'tab-module') {
                $('#roleOwnModulesTree').jxtree();
            } else if (id == 'tab-dept') {
                $('#roleOwnDeptsTree').jxtree();
            }
        });
        $(document.body).on('dblclick','.card-listview>li',function () {
            jx.auth.showUserDetails($(this).data('id'));
        });
    </script>
</@footer>