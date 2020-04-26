<#-- @ftlvariable name="entity" type="com.xci.sys.entity.SysParam" -->
<#include "/_inc/_layout.ftlh">
<#include "/_inc/_historyLog.ftlh">
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
                        <th>主键</th>
                        <td>${entity.id}</td>
                    </tr>
                    <tr>
                        <th class="w-100px">参数名称</th>
                        <td>${entity.name}</td>
                    </tr>
                    <tr>
                        <th>参数编码</th>
                        <td>${entity.code}</td>
                    </tr>
                    <tr>
                        <th>参数分类</th>
                        <td>${entity.categoryName}</td>
                    </tr>
                    <tr>
                        <th>参数值</th>
                        <td>${entity.value}</td>
                    </tr>
                    <tr>
                        <th>备注</th>
                        <td>${entity.remark}</td>
                    </tr>
                </table>
            </div>
            <div id="tab-history" class="tab-pane jx-overflow-auto fade">
                <@histroyLog tableName="SysParam" primaryKey=entity.id/>
            </div>
        </div>
    </div>
</div>
<@footer>
    <script>jx.auth.tabsAuthInit();</script>
</@footer>