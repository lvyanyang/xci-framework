<#-- @ftlvariable name="entity" type="com.github.lvyanyang.sys.entity.SysDept" -->
<#-- @ftlvariable name="users" type="java.util.List<com.github.lvyanyang.sys.entity.SysUser>" -->
<#-- @ftlvariable name="parentName" type="java.lang.String" -->
<#include "/_inc/_layout.ftlh">
<#include "/_inc/_historyLog.ftlh">
<#--<#include "/auth/department/user-member-details.ftl">-->
<@header/>
<div class="jxpanel jx-overflow-no winpanel" data-options="fit:true">
    <div class="jxtabs jxtabs-line" data-options="{fit:true}">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#tab-basic" data-toggle="tab">基本信息</a></li>
            <li><a href="#tab-member" data-toggle="tab">部门成员</a></li>
            <li><a href="#tab-history" data-toggle="tab">操作日志</a></li>
        </ul>
        <div class="tab-content">
            <div id="tab-basic" class="tab-pane jx-overflow-auto fade in active">
                <table id="basic" class="table table-bordered jxtable-details">
                    <tr>
                        <th>主键</th>
                        <td>${entity.id}</td>
                    </tr>
                    <tr>
                        <th class="w-100px">机构名称</th>
                        <td>${entity.name}</td>
                    </tr>
                    <tr>
                        <th>机构编码</th>
                        <td>${entity.code}</td>
                    </tr>
                    <tr>
                        <th>上级机构</th>
                        <td>${parentName}</td>
                    </tr>
                    <tr>
                        <th>负责人</th>
                        <td>${entity.leaderName}</td>
                    </tr>
                    <tr>
                        <th>机构类型</th>
                        <td>${entity.category}</td>
                    </tr>
                    <tr>
                        <th>机构性质</th>
                        <td>${entity.nature}</td>
                    </tr>
                    <tr>
                        <th>状态</th>
                        <td><@xci.boolLabel value=entity.status /></td>
                    </tr>
                    <tr>
                        <th>序号</th>
                        <td>${entity.path}</td>
                    </tr>
                    <tr>
                        <th>备注</th>
                        <td>${entity.remark}</td>
                    </tr>
                </table>
            </div>
            <div id="tab-member" class="tab-pane jx-overflow-auto fade">
                <ul class="card-listview">
                    <#list users as user>
                        <li data-id="${user.id}">${user.name}</li></#list>
                </ul>
                <@xci.warningMessage test=users?size==0 msg="未添加成员"/>
            </div>
            <div id="tab-history" class="tab-pane jx-overflow-auto fade">
                <@histroyLog tableName="SysDept" primaryKey=entity.id/>
            </div>
        </div>
    </div>
</div>
<@footer>
    <script>jx.auth.tabsAuthInit();</script>
</@footer>