<#include "/_inc/_layout.ftlh">
<#assign allowCreate=helper.isAuthorize("sys.role.insert")/>
<#assign allowEdit=helper.isAuthorize("sys.role.update")/>
<#assign allowDelete=helper.isAuthorize("sys.role.delete")/>
<#assign allowExport=helper.isAuthorize("sys.role.export")/>
<#assign allowAuthorize=helper.isAuthorize("sys.role.authorize")/>
<@header/>
<div id="gridtoolbar">
    <form id="gridform" class="jxform jxlayout-form form-inline">
        <div class="form-group">
            <input name="name" class="form-control w-200px" placeholder="请输入名称/简拼关键字" autocomplete="off">
        </div>
        <div class="form-group">
            <select name="status" class="jxselect form-control"
                    data-select-submit="true" data-allow-clear="false" data-width="110">
                <@xci.statusOptions/>
            </select>
        </div>
        <button class="btn btn-primary ml-5" type="submit">
            <i class="fa fa-search"></i> 查询
        </button>
        <div class="btn-group">
	        <@xci.echo test=allowCreate>
                <a id="btn-create" class="btn btn-default">
                    <i class="icon-plus"></i> 新增
                </a>
            </@xci.echo>
	        <@xci.echo test=allowDelete>
	            <a id="btn-delete" class="btn btn-default">
                    <i class="icon-trash"></i> 删除
                </a>
            </@xci.echo>
	        <@xci.echo test=allowExport>
	            <a id="btn-export" class="btn btn-default">
                    <i class="icon-share-alt"></i> 导出
                </a>
            </@xci.echo>
        </div>
    </form>
</div>
<table id="grid" class="jxgrid" data-options="form:'#gridform',toolbar:'#gridtoolbar'"
       data-dialog-width="40%" data-dialog-height="500px">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="title:'操作',field:'id',align:'center',formatter: jx.gf.command"></th>
        <th data-options="title:'角色名称',field:'name',width:200,sortable: true,formatter: jx.gf.details"></th>
        <th data-options="title:'角色编码',field:'code',width:150,sortable: true"></th>
        <th data-options="title:'所属机构',field:'deptName',width:150,sortable: true"></th>
        <th data-options="title:'状态',field:'status',width:80,align:'center',sortable: true,formatter:jx.gf.status"></th>
        <th data-options="title:'序号',field:'path',width:80,align:'center',sortable: true"></th>
        <th data-options="title:'备注',field:'remark',width:200"></th>
    </tr>
    </thead>
</table>
<script type="text/html" id="cmd_tpl">
    <div style="margin: 0 5px">
    <@xci.echo test=allowEdit><a class="cmd-edit" data-id="{0}">修改</a></@xci.echo>
    <@xci.echo test=allowDelete><a class="cmd-delete" data-id="{0}">删除</a></@xci.echo>
    <#--<@xci.echo test=allowMapUser><a class="cmd-map-user" data-id="{0}">成员</a></@xci.echo>-->
    <#--<@xci.echo test=allowAuthorize><a class="cmd-authorize" data-id="{0}">授权</a></@xci.echo>-->
    </div>
</script>
<@footer>
    <script src="<@xci.url '/sys/js/role.js'/>"></script>
</@footer>