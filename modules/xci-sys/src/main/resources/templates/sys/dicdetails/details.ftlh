<#-- @ftlvariable name="entity" type="com.github.lvyanyang.sys.entity.SysDic" -->
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
                        <th class="w-100px">字典类型</th>
                        <td>${entity.code}</td>
                    </tr>
                    <tr>
                        <th>字典名称</th>
                        <td>${entity.name}</td>
                    </tr>
                    <tr>
                        <th>字典值</th>
                        <td>${entity.value}</td>
                    </tr>
                    <tr>
                        <th>序号</th>
                        <td>${entity.path}</td>
                    </tr>
                    <tr>
                        <th>状态</th>
                        <td><@xci.boolLabel value=entity.status /></td>
                    </tr>
                    <#--<tr>-->
                    <#--    <th>操作人</th>-->
                    <#--    <td>${entity.operateUserName!?html}</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                    <#--    <th>操作时间</th>-->
                    <#--    <td>${helper.formatDateTime(entity.operateDateTime)!}</td>-->
                    <#--</tr>-->
                    <tr>
                        <th>备注</th>
                        <td>${entity.remark}</td>
                    </tr>
                </table>
            </div>
            <div id="tab-history" class="tab-pane jx-overflow-auto fade">
                <@histroy tableName="SysDic" primaryKey=entity.id/>
            </div>
        </div>
    </div>
</div>
<@footer>
    <script>
        jx.auth.tabsAuthInit();
    </script>
</@footer>
