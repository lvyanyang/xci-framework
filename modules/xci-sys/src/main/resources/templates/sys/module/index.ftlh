<#include "/sys/_inc/_layout.ftlh">
<#assign allowCreate=helper.isAuthorize("sys.module.insert")/>
<#assign allowEdit=helper.isAuthorize("sys.module.update")/>
<#assign allowDelete=helper.isAuthorize("sys.module.delete")/>
<#assign allowExport=helper.isAuthorize("sys.module.export")/>
<@header/>
<div id="gridtoolbar">
    <form id="gridform" class="jxform jxlayout-form form-inline">
        <div class="form-group">
            <input class="form-control w-250px" name="name" placeholder="模块编码/名称/简拼查询" autocomplete="off">
        </div>
        <div class="form-group">
            <select name="status" class="jxselect form-control" data-width="110"
                    data-select-submit="true" data-allow-clear="false">
                <@xci.statusOptions/>
            </select>
        </div>

        <button class="btn btn-primary ml-5" type="submit">
            <i class="fa fa-search"></i> 查询
        </button>
        <div class="btn-group">
	        <@xci.echo test=allowCreate>
                <a id="btn-createroot" class="btn btn-default">
                    <i class="icon-anchor"></i> 新增根节点
                </a>
                <a id="btn-create" class="btn btn-default">
                    <i class="icon-plus"></i> 新增
                </a>
            </@xci.echo>
	        <@xci.echo test=allowEdit>
	            <a id="btn-edit" class="btn btn-default">
                    <i class="icon-pencil"></i> 编辑
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
<div id="gridcmenu">
    <@xci.echo test=(allowCreate||allowEdit||allowDelete)>
        <ul class="dropdown-menu">
            <@xci.echo test=allowCreate>
                <li><a id="btn-cmcreateroot"><i class="icon-vector"></i> 新增根节点</a></li>
                <li><a id="btn-cmcreate"><i class="icon-plus"></i> 新增</a></li>
            </@xci.echo>
            <@xci.echo test=allowEdit><li><a id="btn-cmedit"><i class="icon-pencil"></i> 编辑</a></li></@xci.echo>
            <@xci.echo test=allowDelete><li><a id="btn-cmdelete"><i class="icon-trash"></i> 删除</a></li></@xci.echo>
        </ul>
    </@xci.echo>
</div>
<table id="grid" class="jxtreegrid"
       data-options="dnd: true,checkbox:false,defaultIconCls:'icon-flag',
            form:'#gridform',toolbar:'#gridtoolbar'"
       data-dialog-width="70%" data-dialog-height="90%">
    <thead>
    <tr>
        <th data-options="title: '名称', field: 'name', width: 250"></th>
        <th data-options="title: '编码', field: 'code', width: 250"></th>
        <th data-options="title: '状态', field: 'status', width: 50, align: 'center',formatter:jx.gf.status"></th>
        <#--<th data-options="title: '菜单', field: 'isMenu', width: 50, align: 'center',formatter:jx.gf.bool"></th>-->
        <#--<th data-options="title: '公共', field: 'isPublic', width: 50, align: 'center',formatter:jx.gf.bool"></th>-->
        <th data-options="title: '展开', field: 'isExpand', width: 50, align: 'center',formatter:jx.gf.bool"></th>
        <th data-options="title: '地址', field: 'webUrl', width: 300"></th>
        <#--<th data-options="title: '操作人',field:'operateUserName',width:100,align:'center'"></th>-->
        <#--<th data-options="title: '操作时间',field:'operateDateTime',width:170,align:'center',sortable: true"></th>-->
        <th data-options="title: '备注',field:'remark',width:200"></th>
    </tr>
    </thead>
</table>
<@footer>
    <script src="<@xci.url '/sys/js/module.js'/>"></script>
</@footer>