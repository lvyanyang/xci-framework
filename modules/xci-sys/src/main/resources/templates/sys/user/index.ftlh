<#include "/sys/_inc/_layout.ftlh">
<#assign allowCreate=helper.isAuthorize("sys.user.insert")/>
<#assign allowEdit=helper.isAuthorize("sys.user.update")/>
<#assign allowDelete=helper.isAuthorize("sys.user.delete")/>
<#assign allowExport=helper.isAuthorize("sys.user.export")/>
<#assign allowRevisePassword=helper.isAuthorize("sys.user.revisePassword")/>
<#--<#assign allowMapRole=helper.isAuthorize("SystemUser.MapRole")/>-->
<#assign allowAuthorize=helper.isAuthorize("sys.user.authorize")/>
<@header/>
<div id="gridtoolbar">
    <form id="gridform" class="jxform jxlayout-form form-inline">
        <div class="form-group">
            <input class="form-control w-200px" name="name" placeholder="账号/姓名/简拼关键字" autocomplete="off">
        </div>
        <select name="status" class="jxselect form-control" data-select-submit="true"
                data-allow-clear="false" data-width="110">
                <@xci.statusOptions/>
        </select>
        <button class="btn btn-primary ml-5" type="submit">
            <i class="fa fa-search"></i> 查询
        </button>
        <div class="btn-group">
            <@xci.echo test=allowCreate>
                <a id="btn-create" class="btn btn-default">
                    <i class="icon-plus"></i> 新增
                </a>
            </@xci.echo>
	        <#--<@xci.echo test=allowEdit>-->
	            <#--<a id="btn-edit" class="btn btn-default">-->
                    <#--<i class="icon-pencil"></i> 编辑-->
                <#--</a>-->
            <#--</@xci.echo>-->
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
            <@xci.echo test=allowExport>
                <a id="btn-export" class="btn btn-default">
                    <i class="icon-share-alt"></i> 导出
                </a>
            </@xci.echo>
        </div>
        <div class="btn-group tool-mt">
            <#--<@xci.echo test=allowAuthorize>-->
                <#--<a id="btn-authorize" class="btn btn-default">-->
                    <#--<i class="fa fa-gavel"></i> 用户授权-->
                <#--</a>-->
            <#--</@xci.echo>-->
        </div>
    </form>
</div>
<div id="revise-passwordpopover" class="popover popover-x popover-default popover-lg">
    <div class="arrow"></div>
    <form id="revise-passwordform" class="jxform form-horizontal"
          action="<@xci.url "/auth/user/revise-password"/>"
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
                <i class="fa fa-save"></i> 保存
            </button>
        </div>
    </form>
</div>
<table id="grid" class="jxgrid"
       data-options="form: '#gridform',toolbar: '#gridtoolbar'"
       data-dialog-width="40%" data-dialog-height="650px">
    <thead>
        <tr>
            <th data-options="field: 'ck',checkbox:true"></th>
            <th data-options="title: '操作',field:'id',align:'center',formatter:jx.gf.command"></th>
            <th data-options="title: '姓名', field: 'name', width: 100, align: 'center',sortable: true"></th>
            <th data-options="title: '账号', field: 'account', width: 150, sortable: true, align: 'center'"></th>
            <th data-options="title: '角色', field: 'roleName', width: 150, align: 'center'"></th>
            <th data-options="title: '机构', field: 'deptName', width: 150, align: 'center'"></th>
            <th data-options="title: '状态', field: 'status', width: 50, align: 'center',formatter:jx.gf.status"></th>
            <th data-options="title: '登录次数', field: 'loginCount', width: 100, align: 'center'"></th>
            <th data-options="title: '首次登录时间', field: 'firstVisitTime', width: 170, align: 'center'"></th>
            <th data-options="title: '最后登录时间', field: 'lastVisitTime', width: 170, align: 'center'"></th>
            <#--<th data-options="title: '操作人',field:'operateUserName',width:100,align:'center'"></th>-->
            <#--<th data-options="title: '操作时间',field:'operateDateTime',width:170,align:'center',sortable: true"></th>-->
            <th data-options="title: '备注',field:'remark',width:250"></th>
        </tr>
    </thead>
</table>
<script type="text/html" id="cmd_tpl">
    <div style="margin: 0 5px">
        <@xci.echo test=allowEdit><a class="cmd-edit" data-id="{0}">修改</a></@xci.echo>
        <@xci.echo test=allowDelete><a class="cmd-delete" data-id="{0}">删除</a></@xci.echo>
        <@xci.echo test=allowAuthorize><a class="cmd-authorize" data-id="{0}">授权</a></@xci.echo>
    </div>
</script>
<@footer>
    <script src="<@xci.url '/sys/js/user.js'/>"></script>
</@footer>
