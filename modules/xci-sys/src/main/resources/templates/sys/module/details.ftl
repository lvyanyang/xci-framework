<#-- @ftlvariable name="entity" type="com.github.lvyanyang.sys.entity.SysModule" -->
<#include "/_inc/_layout.ftl">
<#include "/_inc/_historyLog.ftl">
<@header/>
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
                        <th class="w-100px">模块主键</th>
                        <td>${entity.id}</td>
                    </tr>
                    <tr>
                        <th>上级主键</th>
                        <td>${entity.parentId}</td>
                    </tr>
                    <tr>
                        <th>模块名称</th>
                        <td>${entity.name}</td>
                        </tr>
                    <tr>
                        <th>模块编码</th>
                        <td>${entity.code}</td>
                    </tr>
                    <tr>
                        <th>模块参数</th>
                        <td>${entity.param}</td>
                    </tr>
                    <tr>
                        <th>序号</th>
                        <td>${entity.path}</td>
                    </tr>
                    <#if entity.web>
                        <tr>
                            <th>Web图标</th>
                            <td><i class="${entity.webCls} fa-lg"></i> ${entity.webCls}</td>
                        </tr>
                        <tr>
                            <th>Web路径</th>
                            <td>${entity.webUrl}</td>
                        </tr>
                        <tr>
                            <th>Web配置</th>
                            <td>${entity.webSetting}</td>
                        </tr>
                    </#if>
                    <#if entity.win>
                        <tr>
                            <th>Win图标</th>
                            <td>${entity.winCls}</td>
                        </tr>
                        <tr>
                            <th>Win路径</th>
                            <td>${entity.winUrl}</td>
                        </tr>
                        <tr>
                            <th>Win配置</th>
                            <td>${entity.winSetting}</td>
                        </tr>
                    </#if>
                    <tr>
                        <th>选项</th>
                        <td>
                            <#--是否菜单-->
                            ${web.boolLabel(entity.menu,"菜单","权限")}
                            <#--是否展开-->
                            ${web.boolLabel(entity.expand,"展开" ,"合上")}
                            <#--是否公开-->
                            ${web.boolLabel(entity.publiced,"公开" ,"不公开")}
                            <#--是否状态-->
                            ${web.boolLabel(entity.status,"启用" ,"禁用")}
                        </td>
                    </tr>
                    <tr>
                        <th>备注</th>
                        <td>${entity.remark}</td>
                    </tr>
                </table>
            </div>
            <div id="tab-history" class="tab-pane jx-overflow-auto fade">
                <@histroyLog tableName="SysModule" primaryKey=entity.id/>
            </div>
        </div>
    </div>
</div>
<@footer>
    <script>
        jx.auth.tabsAuthInit();
    </script>
</@footer>
