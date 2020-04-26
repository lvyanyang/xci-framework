<#-- @ftlvariable name="entity" type="com.github.lvyanyang.sys.entity.SysModule" -->
<#include "/sys/_inc/_layout.ftlh">
<#include "/sys/_inc/_history.ftlh">
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
                        <th class="w-100px">名称</th>
                        <td>${entity.name}</td>
                        <th class="w-100px">编码</th>
                        <td>${entity.code}</td>
                    </tr>
                    <tr>
                        <th>图标样式</th>
                        <td><i class="${entity.webCls} fa-lg"></i> ${entity.webCls}</td>
                        <th>排序路径</th>
                        <td>${entity.path}</td>
                    </tr>
                    <tr>
                        <th>展开</th>
                        <td>
                            <@xci.yesnoLabel value=entity.isExpand/>
                        </td>
                        <th>公开</th>
                        <td>
                            <@xci.yesnoLabel value=entity.isPublic/>
                        </td>
                    </tr>
                    <tr>
                        <th>菜单</th>
                        <td>
                            <@xci.yesnoLabel value=entity.isMenu/>
                        </td>
                        <th>状态</th>
                        <td><@xci.boolLabel value=entity.status /></td>
                    </tr>
                    <#--<tr>-->
                    <#--    <th>操作人</th>-->
                    <#--    <td>${entity.operateUserName}</td>-->
                    <#--    <th>操作时间</th>-->
                    <#--    <td>${helper.formatDateTime(entity.operateDateTime)!}</td>-->
                    </tr>
                    <#if helper.isNotBlank(entity.param)>
                        <tr>
                            <th>参数</th>
                            <td colspan="3">

                                <pre>${entity.param}</pre>

                            </td>
                        </tr>
                    </#if>
                    <tr>
                        <th>地址</th>
                        <td colspan="3">${entity.webUrl}</td>
                    </tr>
                    <tr>
                        <th>窗口类</th>
                        <td colspan="3">${entity.winProvider}</td>
                    </tr>
                    <tr>
                        <th>备注</th>
                        <td colspan="3">${entity.remark}</td>
                    </tr>
                </table>
            </div>
            <div id="tab-history" class="tab-pane jx-overflow-auto fade">
                <@histroy tableName="SysModule" primaryKey=entity.id/>
            </div>
        </div>
    </div>
</div>
<@footer>
<script>
    jx.auth.tabsAuthInit();
</script>
</@footer>
