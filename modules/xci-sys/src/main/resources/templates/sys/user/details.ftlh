<#-- @ftlvariable name="entity" type="com.github.lvyanyang.sys.entity.SysUser" -->
<#-- @ftlvariable name="roles" type="java.util.List<com.github.lvyanyang.sys.entity.SysRole>" -->
<#include "/_inc/_layout.ftlh">
<#include "/_inc/_historyLog.ftlh">
<@header>
    <link href="${xci.url('/sys/css/tree-noselect.css')}" rel="stylesheet"/>
</@header>
<div class="jxpanel jx-overflow-no winpanel" data-options="fit:true">
    <div class="jxtabs jxtabs-line" data-options="{fit:true}">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#tab-basic" data-toggle="tab">基本信息</a></li>
            <li><a href="#tab-role" data-toggle="tab">拥有角色</a></li>
            <li><a href="#tab-module" data-toggle="tab">功能权限</a></li>
            <li><a href="#tab-data" data-toggle="tab">机构权限</a></li>
            <li><a href="#tab-history" data-toggle="tab">操作日志</a></li>
        </ul>
        <div class="tab-content">
            <div id="tab-basic" class="tab-pane jx-overflow-auto fade in active">
                <table class="table table-bordered jxtable-details">
                    <tr>
                        <th>用户主键</th>
                        <td colspan="3">${entity.id}</td>
                    </tr>
                    <tr>
                        <th class="w-120px">账号</th>
                        <td>${entity.account}</td>
                        <th class="w-120px">姓名</th>
                        <td>${entity.name}</td>
                    </tr>
                    <tr>
                        <th>机构主键</th>
                        <td>${entity.deptId}</td>
                        <th>机构名称</th>
                        <td>
                            <a href="#"
                               onclick="jx.auth.showDepartmentDetails('${entity.deptId}');">${entity.deptName}</a>
                        </td>
                    </tr>
                    <tr>
                        <th>开始有效期</th>
                        <td>${web.formatDateTime(entity.allowStartTime)}</td>
                        <th>结束有效期</th>
                        <td>${web.formatDateTime(entity.allowEndTime)}</td>
                    </tr>
                    <tr>
                        <th>密码过期时间</th>
                        <td colspan="3">${web.formatDateTime(entity.pwdExpireTime)}</td>
                    </tr>
                    <tr>
                        <th>登录次数</th>
                        <td>${entity.loginCount}</td>
                        <th>职位</th>
                        <td>${entity.post}</td>
                    </tr>
                    <tr>
                        <th>手机</th>
                        <td>${entity.mobile}</td>
                        <th>邮件</th>
                        <td>${entity.email}</td>
                    </tr>
                    <tr>
                        <th>首次登录时间</th>
                        <td>${web.formatDateTime(entity.firstVisitTime)}</td>
                        <th>最后登录时间</th>
                        <td>${web.formatDateTime(entity.lastVisitTime)}</td>
                    </tr>
                    <tr>
                        <th>选项</th>
                        <td colspan="3">
                            <#--账号类型-->
                            <@xci.boolAllLabel value=entity.category==0 allLable=entity.categoryName/>
                            <#--管理员-->
                            <@xci.boolLabel value=!entity.admin trueLable="普通账号" falseLable="管理员"/>
                            <#--状态-->
                            <@xci.statusBoolLabel value=entity.status/>
                            <#--允许修改密码-->
                            <@xci.boolLabel value=entity.pwdAllowModify trueLable="允许" falseLable="不允许"/>
                            <#--密码永不过期-->
                            <@xci.boolLabel value=entity.pwdNeverExpire trueLable="密码永不过期" falseLable=""/>
                        </td>
                    </tr>
                    <tr>
                        <th>企业主键</th>
                        <td>${entity.entId}</td>
                        <th>企业名称</th>
                        <td>${entity.entName}</td>
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
                            data-url="${web.url('/sys/user/userOwnModules?userId=${entity.id}')}"
                            data-default-icon-cls="icon-flag"
                            data-load-empty-message="未配置功能权限"></ul>
                    </li>
                </ul>
            </div>
            <div id="tab-data" class="tab-pane jx-overflow-auto fade">
                <ul class="list-group margin-5">
                    <li class="list-group-item">
                        <#--${auth.getObjectDataSettingDesc(ownDataSetting)!}-->
                    </li>
                    <#--<li class="list-group-item padding-5">-->
                    <#--    <ul id="user-own-departments-tree"-->
                    <#--        data-url="<@xci.url '/auth/user/user-own-departments?id=${entity.id}'/>"-->
                    <#--        data-default-icon-cls="icon-flag"-->
                    <#--        data-load-empty-message=""></ul>-->
                    <#--</li>-->
                </ul>
            </div>
            <div id="tab-history" class="tab-pane jx-overflow-auto fade">
                <@histroyLog tableName="SysUser" primaryKey=entity.id/>
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