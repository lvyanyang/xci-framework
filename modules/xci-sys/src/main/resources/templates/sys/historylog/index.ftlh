<#include "/_inc/_layout.ftlh">
<#assign defOperateStartDateTime=web.formatDate(web.now(-7))/>
<#assign defOperateEndDateTime=web.formatDate(web.now())/>
<@header/>
<div id="gridtoolbar">
    <div class="search-container">
        <div class="row">
            <div class="col-sm-12 search-collapse">
                <form id="gridform" class="jxform jxlayout-form">
                    <input type="hidden" id="category" name="category" value=""/>
                    <div class="select-list">
                        <ul>
                            <li>
                                <label>操作人员： </label>
                                <input class="form-control" name="operateUserName">
                            </li>
                            <li>
                                <label>数据表名： </label>
                                <input class="form-control" name="tableName">
                            </li>
                            <li>
                                <label>数据主键： </label>
                                <input class="form-control" name="primaryKey">
                            </li>

                            <li class="select-time">
                                <label>操作时间： </label>
                                <input id="operateStartDateTime" name="operateStartDateTime" class="form-control jxdate"
                                       value="${defOperateStartDateTime}" placeholder="开始时间" data-validate="{required: [true,'请选择开始时间']}"
                                       data-options="endDate: '0d'" autocomplete="off"/>
                                <span>-</span>
                                <input id="operateEndDateTime" name="operateEndDateTime" class="form-control jxdate"
                                       value="${defOperateEndDateTime}" placeholder="结束时间" data-validate="{required: [true,'请选择结束时间'],compareEqualDate:'#operateStartDateTime'}"
                                       data-options="endDate: '0d'" autocomplete="off"/>
                            </li>
                            <li>
                                <button class="btn btn-primary" type="submit">
                                    <i class="fa fa-search"></i> 查询
                                </button>
                                <button type="reset" class="btn btn-default">
                                    <i class="fa fa-refresh"></i> 重置
                                </button>
                            </li>
                        </ul>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="jxtabs-line line-primary border-top-gray" style="margin-bottom: 2px;">
        <ul class="nav nav-tabs">
            <li class="active" data-value="">
                <a data-toggle="tab">全部</a>
            </li>
            <li data-value="1">
                <a data-toggle="tab">新增</a>
            </li>
            <li data-value="2">
                <a data-toggle="tab">修改</a>
            </li>
            <li data-value="3">
                <a data-toggle="tab">删除</a>
            </li>
        </ul>
    </div>
</div>
<table id="grid" class="jxgrid" data-options="form:'#gridform',toolbar:'#gridtoolbar',
    queryParams:{operateStartDateTime:'${defOperateStartDateTime}',operateEndDateTime:'${defOperateEndDateTime}'}">
    <thead>
    <tr>
        <th data-options="title: '操作类型', field: 'category', width: 80, align: 'center',formatter: jx.gf.formatHistoryLogCategory"></th>
        <th data-options="title: '数据表名', field: 'tableName', width: 150, align: 'left',formatter: jx.gf.formatHistoryLogName"></th>
        <th data-options="title: '数据主键', field: 'primaryKey', width: 310, align: 'left'"></th>
        <th data-options="title: '操作人员', field: 'operateUserName', width: 120, align: 'left'"></th>
        <th data-options="title: '操作时间', field: 'operateDateTime', width: 170, align: 'left',sortable: true"></th>
    </tr>
    </thead>
</table>
<@footer>
    <script src="${web.url('/sys/js/historylog.js')}"></script>
    <script>
        jx.ready(function () {
            jx.sys.HistoryLog();
        })
    </script>
</@footer>