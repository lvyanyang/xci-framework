<#include "/_inc/_layout.ftlh">
<#assign allowInsert=sys.auth("sys.user.insert")/>
<#assign allowUpdate=sys.auth("sys.user.update")/>
<#assign allowDelete=sys.auth("sys.user.delete")/>
<#assign allowRevisePassword=sys.auth("sys.user.revisePassword")/>
<@header/>
<div id="gridtoolbar">
    <form id="gridform" class="jxform jxlayout-form form-inline">
        <div class="form-group">
            <input class="form-control w-200px" name="name" placeholder="账号/姓名/简拼关键字" autocomplete="off">
        </div>
        <select name="status" class="jxselect form-control" data-select-submit="true" data-allow-clear="false" data-width="110">
                <@xci.statusOptions/>
        </select>
        <button class="btn btn-primary ml-5" type="submit">
            <i class="fa fa-search"></i> 查询
        </button>
        <div class="btn-group">
            <@xci.echo test=allowInsert>
                <a id="btn-create" class="btn btn-default">
                    <i class="icon-plus"></i> 新增
                </a>
            </@xci.echo>
	        <@xci.echo test=allowDelete>
	            <a id="btn-delete" class="btn btn-default">
                    <i class="icon-trash"></i> 删除
                </a>
            </@xci.echo>
            <@xci.echo test=allowRevisePassword>
                <a class="btn btn-default" data-target="#revise-passwordpopover" data-toggle="popover-x" data-placement="auto-bottom">
                    <i class="fa fa-key"></i> 重置密码
                </a>
            </@xci.echo>
            <a id="btn-export"
               class="btn btn-default jxexport"
               data-url="${web.url('/sys/user/export')}"
               data-param="jx.serialize($('#gridform'))">
                <i class="icon-share-alt"></i> 导出
            </a>
        </div>
    </form>
</div>
<div id="revise-passwordpopover" class="popover popover-x popover-default popover-lg">
    <div class="arrow"></div>
    <form id="revise-passwordform" class="jxform form-horizontal"
          action="${web.url('/sys/user/revisePasswordSave')}"
          data-layout="bootstrap" data-protip="false" method="post">
        <input id="revise-ids" name="userIds" type="hidden" />
        <div class="popover-title">
            <span class="close" data-dismiss="popover-x">&times;</span>重置密码
        </div>
        <div class="popover-content">
            <div class="form-group">
                <label class="col-md-3 control-label">新密码：</label>
                <div class="col-md-8">
                    <input id="revise_from_password" name="password" class="form-control" type="password" autocomplete="off"
                           data-validate="required: [true,'请输入新密码']">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">确认密码：</label>
                <div class="col-md-8">
                    <input name="confirm_password" class="form-control" type="password" autocomplete="off"
                           data-validate="required: [true,'请输入确认密码'],equalTo: ['#revise_from_password','确认密码必须与新密码输入一致']">
                </div>
            </div>
        </div>
        <div class="popover-footer">
            <button class="btn btn-primary" type="submit">
                <i class="fa fa-save"></i> 重置
            </button>
        </div>
    </form>
</div>
<table id="grid" class="jxgrid" data-options="form: '#gridform',toolbar: '#gridtoolbar'">
    <thead>
        <tr>
            <th data-options="field: 'ck',checkbox:true"></th>
            <th data-options="title: '操作',field:'id',align:'center',formatter:jx.gf.command"></th>
            <th data-options="title: '姓名', field: 'name', width: 100, align: 'center',sortable: true"></th>
            <th data-options="title: '账号', field: 'account', width: 150, sortable: true, align: 'center'"></th>
            <th data-options="title: '机构', field: 'deptName', width: 150, align: 'center'"></th>
            <th data-options="title: '状态', field: 'status', width: 50, align: 'center',formatter:jx.gf.status"></th>
            <th data-options="title: '职位',field:'post',width:200,align:'left'"></th>
            <th data-options="title: '手机号码',field:'mobile',width:120,align:'left'"></th>
            <th data-options="title: '电子邮件',field:'email',width:150,align:'left'"></th>
            <th data-options="title: '登录次数', field: 'loginTimes', width: 100, align: 'center'"></th>
            <th data-options="title: '首次登录时间', field: 'firstVisitTime', width: 170, align: 'center'"></th>
            <th data-options="title: '最后登录时间', field: 'lastVisitTime', width: 170, align: 'center'"></th>
            <th data-options="title: '密码过期时间',field:'pwdExpireTime',width:200,align:'center'"></th>
            <th data-options="title: '开始有效期',field:'allowStartTime',width:200,align:'center'"></th>
            <th data-options="title: '结束有效期',field:'allowEndTime',width:200,align:'center'"></th>
        </tr>
    </thead>
</table>
<script type="text/html" id="cmd_tpl">
    <div style="margin: 0 5px">
        <@xci.echo test=allowInsert><a class="cmd-copy" data-id="{0}">复制</a></@xci.echo>
        <@xci.echo test=allowUpdate><a class="cmd-edit" data-id="{0}">修改</a></@xci.echo>
        <@xci.echo test=allowDelete><a class="cmd-delete" data-id="{0}">删除</a></@xci.echo>
    </div>
</script>
<@footer>
    <script src="${web.url('/sys/js/user.js')}"></script>
</@footer>
