<#-- @ftlvariable name="roles" type="java.util.List<com.xci.sys.entity.SysRole>" -->
<#-- @ftlvariable name="modules" type="java.util.List<com.xci.sys.entity.SysModule>" -->
<#-- @ftlvariable name="entity" type="com.xci.sys.entity.SysUser" -->
<#-- @ftlvariable name="selfDataMessage" type="java.lang.String" -->
<#-- @ftlvariable name="dataMessage" type="java.lang.String" -->
<#include "/sys/_inc/_layout.ftlh">
<#include "/sys/_inc/_history.ftlh">
<#--<#include "/auth/urmap/user-details.ftl">-->
<#--<#include "/auth/objectmap/module-details.ftl">-->
<#--<#include "/auth/objectmap/data-details.ftl">-->
<@header>
    <link href="<@xci.url '/auth/css/tree-noselect.css'/>" rel="stylesheet"/>
</@header>
<div class="jxpanel jx-overflow-no winpanel" data-options="fit:true">
    <div class="jxtabs jxtabs-line" data-options="{fit:true}">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#tab-basic" data-toggle="tab">基本信息</a></li>
            <li><a href="#tab-role" data-toggle="tab">拥有角色</a></li>
            <li><a href="#tab-module" data-toggle="tab">功能权限</a></li>
            <li><a href="#tab-data" data-toggle="tab">部门权限</a></li>
            <li><a href="#tab-history" data-toggle="tab">操作日志</a></li>
        </ul>
        <div class="tab-content">
            <div id="tab-basic" class="tab-pane jx-overflow-auto fade in active">
                <table class="table table-bordered jxtable-details">
                    <tr>
                        <th class="w-120px">账号</th>
                        <td>${entity.account}</td>
                        <th class="w-120px">姓名</th>
                        <td>${entity.name}</td>
                    </tr>
                    <tr>
                        <th>部门</th>
                        <td>
                            <a href="#"
                               onclick="jx.auth.showDepartmentDetails('${entity.deptId}');">${entity.deptName}</a>
                        </td>
                        <th>登录次数</th>
                        <td>${entity.loginCount}</td>
                    </tr>
                    <tr>
                        <th>手机</th>
                        <td>${entity.mobile}</td>
                        <th>邮件</th>
                        <td>${entity.email}</td>
                    </tr>
                    <tr>
                        <th>首次登录时间</th>
                        <td>${helper.formatDateTime(entity.firstVisitTime)}</td>
                        <th>最后登录时间</th>
                        <td>${helper.formatDateTime(entity.lastVisitTime)}</td>
                    </tr>
                    <tr>
                        <th>管理员</th>
                        <td><@xci.yesnoLabel value=entity.isAdmin/></td>
                        <th>状态</th>
                        <td><@xci.boolLabel value=entity.status /></td>
                    </tr>
                    <#--<tr>-->
                    <#--    <th>操作人</th>-->
                    <#--    <td>${entity.operateUserName}</td>-->
                    <#--    <th>操作时间</th>-->
                    <#--    <td>${helper.formatDateTime(entity.operateDateTime)}</td>-->
                    <#--</tr>-->
                    <tr>
                        <th>自有部门权限</th>
                        <td colspan="3">
<#--                            ${selfDataSettingDesc!}-->
                        </td>
                    </tr>
                    <tr>
                        <th>备注</th>
                        <td colspan="3">${entity.remark}</td>
                    </tr>
                </table>
            </div>
            <div id="tab-role" class="tab-pane jx-overflow-auto fade">
                <ul class="card-listview">
                    <#list roles as role><li data-id="${role.id}">${role.name}</li></#list>
                </ul>
                <@xci.warningMessage test=roles?size==0 msg="未配置角色"/>
            </div>
            <div id="tab-module" class="tab-pane jx-overflow-auto fade">
                <ul class="list-group margin-5">
                    <li class="list-group-item padding-5">
                        <ul id="user-own-modules-tree"
                            data-url="<@xci.url '/auth/user/user-own-modules?id=${entity.id}'/>"
                            data-default-icon-cls="icon-flag"
                            data-load-empty-message="未配置功能权限"></ul>
                    </li>
                </ul>
            </div>
            <div id="tab-data" class="tab-pane jx-overflow-auto fade">
                <ul class="list-group margin-5">
                    <li class="list-group-item">
<#--                        ${auth.getObjectDataSettingDesc(ownDataSetting)!}-->
                    </li>
                    <li class="list-group-item padding-5">
                        <ul id="user-own-departments-tree"
                            data-url="<@xci.url '/auth/user/user-own-departments?id=${entity.id}'/>"
                            data-default-icon-cls="icon-flag"
                            data-load-empty-message=""></ul>
                    </li>
                </ul>
            </div>
            <div id="tab-history" class="tab-pane jx-overflow-auto fade">
                <@histroy tableName="SysUser" primaryKey=entity.id/>
            </div>
        </div>
    </div>
</div>
<@footer>
    <script>
        jx.auth.tabsAuthInit(function (id) {
            if (id == 'tab-module') {
                $('#user-own-modules-tree').jxtree();
            }else if (id == 'tab-data') {
                $('#user-own-departments-tree').jxtree();
            }
        });
        $(document.body).on('dblclick','.card-listview>li',function () {
            var id = $(this).data('id');
            jx.auth.showRoleDetails(id);
        });
    </script>
</@footer>