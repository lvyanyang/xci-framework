<#include "/_inc/_layout.ftl">
<#assign defOperateStartDateTime=web.formatDate(web.now(-7))/>
<#assign defOperateEndDateTime=web.formatDate(web.now())/>
<@header/>
<div id="gridtoolbar">
    <div class="search-container">
        <div class="row">
            <div class="col-sm-12 search-collapse">
                <form id="gridform" class="jxform jxlayout-form">
                    <div class="select-list">
                        <ul>
                            <li>
                                <label>操作人员： </label>
                                <input class="form-control" name="operateUserName">
                            </li>
                            <li>
                                <label>IP地址： </label>
                                <input class="form-control" name="ip">
                            </li>
                            <li>
                                <label>功能模块： </label>
                                <input class="form-control" name="tag">
                            </li>
                            <li>
                                <label>应用ID： </label>
                                <input class="form-control" name="appId">
                            </li>
                            <li>
                                <label>请求地址： </label>
                                <input class="form-control" name="reqUrl">
                            </li>
                            <li>
                                <div id="time-horizon" class="btn-group">
                                    <a class="btn btn-default" data-value="1">今天</a>
                                    <a class="btn btn-default active" data-value="2" >近7天</a>
                                    <a class="btn btn-default" data-value="3">近1个月</a>
                                    <a class="btn btn-default" data-value="4">近3个月</a>
                                    <a class="btn btn-default" data-value="5" data-toggle="popover-x"
                                       data-placement="auto-bottom" data-target="#date-popover">
                                        自定义
                                    </a>
                                </div>
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
</div>
<div id="date-popover" class="popover popover-x popover-default popover-lg">
    <div class="arrow"></div>
    <form id="dateform" class="jxform form-horizontal">
        <div class="popover-title">
            <span class="close" data-dismiss="popover-x">&times;</span>操作时间
        </div>
        <div class="popover-content">
            <div class="form-group">
                <label class="col-md-3 control-label">开始时间</label>
                <div class="col-md-8">
                    <div class="input-group date jxdate" data-options="endDate: '0d'">
                        <input id="operateStartDateTime" name="operateStartDateTime" class="form-control"
                               data-validate="{required: [true,'请选择开始时间']}" autocomplete="off">
                        <span class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">结束时间</label>
                <div class="col-md-8">
                    <div class="input-group date jxdate" data-options="endDate: '0d'">
                        <input id="operateEndDateTime" name="operateEndDateTime" class="form-control"
                               data-validate="{required: [true,'请选择结束时间'],compareEqualDate:'#operateStartDateTime'}" autocomplete="off">
                        <span class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="popover-footer">
            <button id="btn-date-search" type="button" class="btn btn-success">确定</button>
            <#--<button type="reset" class="btn btn-default">重置</button>-->
        </div>
    </form>
</div>

<table id="grid" class="jxgrid" data-options="form:'#gridform,#dateform',toolbar:'#gridtoolbar',
    queryParams:{operateStartDateTime:'${defOperateStartDateTime}',operateEndDateTime:'${defOperateEndDateTime}'}">
    <thead>
    <tr>
        <th data-options="title: '操作时间', field: 'operateDateTime', width: 170, align: 'left',sortable: true, formatter: jx.gf.formatOperateDateTime"></th>
        <th data-options="title: '操作机构', field: 'operateDeptName', width: 120, align: 'left'"></th>
        <th data-options="title: '操作人员', field: 'operateUserName', width: 120, align: 'left'"></th>
        <th data-options="title: '执行结果', field: 'status', width: 80, align: 'center', formatter: jx.gf.formatStatus"></th>
        <th data-options="title: '耗时', field: 'costTime', width: 100, align: 'center', sortable: true, formatter: jx.gf.formatCostTime"></th>
        <th data-options="title: '模块', field: 'tag', width: 140, align: 'center'"></th>
        <th data-options="title: '消息', field: 'msg', width: 400"></th>
        <th data-options="title: 'IP', field: 'ip', width: 150"></th>
        <th data-options="title: '请求方法', field: 'reqMethod', width: 200"></th>
        <th data-options="title: '请求地址', field: 'reqUrl', width: 200"></th>
        <th data-options="title: '应用', field: 'appName', width: 150"></th>
    </tr>
    </thead>
</table>
<@footer>
    <script src="${web.url('/sys/js/operatelog.js')}"></script>
</@footer>