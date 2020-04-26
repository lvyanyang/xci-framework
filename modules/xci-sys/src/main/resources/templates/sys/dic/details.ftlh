<#-- @ftlvariable name="entity" type="com.xci.sys.entity.SysDicCategory" -->
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
                    </tr>
                    <tr>
                        <th>编码</th>
                        <td>${entity.code}</td>
                    </tr>
                    <tr>
                        <th>上级</th>
                        <td></td>
                    </tr>
                    <tr>
                        <th>排序路径</th>
                        <td>${entity.path}</td>
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
                <@histroy tableName="SysDicCategory" primaryKey=entity.id/>
            </div>
        </div>
    </div>
</div>
<@footer>
<script>
    jx.auth.tabsAuthInit();
</script>
</@footer>
