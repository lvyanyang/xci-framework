<#include "/_inc/_layout.ftlh">
<#assign allowInsert=sys.auth("sys.module.insert")/>
<#assign allowUpdate=sys.auth("sys.module.update")/>
<#assign allowDelete=sys.auth("sys.module.delete")/>
<@header/>
<div id="gridtoolbar">
    <form id="gridform" class="jxform jxlayout-form form-inline">
        <div class="form-group">
            <input class="form-control w-250px" name="name" placeholder="模块编码/名称/简拼查询" autocomplete="off">
        </div>
        <div class="form-group">
            <select name="status" class="jxselect form-control" data-width="110" data-select-submit="true" data-allow-clear="false">
                <@xci.statusOptions/>
            </select>
        </div>

        <button class="btn btn-primary ml-5" type="submit">
            <i class="fa fa-search"></i> 查询
        </button>
        <div class="btn-group">
	        <@xci.echo test=allowInsert>
                <a id="btn-create" class="btn btn-default">
                    <i class="icon-plus"></i> 新增
                </a>
            </@xci.echo>
	        <@xci.echo test=allowUpdate>
	            <a id="btn-edit" class="btn btn-default">
                    <i class="icon-pencil"></i> 编辑
                </a>
            </@xci.echo>
	        <@xci.echo test=allowDelete>
	            <a id="btn-delete" class="btn btn-default">
                    <i class="icon-trash"></i> 删除
                </a>
            </@xci.echo>
	        <a id="btn-export"
               class="btn btn-default jxexport"
               data-url="${web.url('/sys/module/export')}"
               data-param="jx.serialize($('#gridform'))">
                <i class="icon-share-alt"></i> 导出
            </a>
        </div>
    </form>
</div>
<div id="gridcmenu">
    <@xci.echo test=(allowInsert||allowUpdate||allowDelete)>
        <ul class="dropdown-menu">
            <@xci.echo test=allowInsert>
                <#--<li><a id="btn-cmcreateroot"><i class="icon-vector"></i> 新增根节点</a></li>-->
                <li><a id="btn-cmcreate"><i class="icon-plus"></i> 新增</a></li>
                <li><a id="btn-cmcopy"><i class="fa fa-copy"></i> 复制</a></li>
            </@xci.echo>
            <@xci.echo test=allowUpdate><li><a id="btn-cmedit"><i class="icon-pencil"></i> 编辑</a></li></@xci.echo>
            <@xci.echo test=allowDelete><li><a id="btn-cmdelete"><i class="icon-trash"></i> 删除</a></li></@xci.echo>
        </ul>
    </@xci.echo>
</div>
<table id="grid" class="jxtreegrid" data-options="dnd: true,checkbox:false,defaultIconCls:'icon-flag',form:'#gridform',toolbar:'#gridtoolbar'">
    <thead>
    <tr>
        <th data-options="title: '名称', field: 'name', width: 250"></th>
        <th data-options="title: '编码', field: 'code', width: 250"></th>
        <th data-options="title: '状态', field: 'status', width: 50, align: 'center',formatter:jx.gf.status"></th>
        <th data-options="title: '展开', field: 'expand', width: 50, align: 'center',formatter:jx.gf.expandStatus"></th>
        <th data-options="title: '菜单', field: 'menu', width: 50, align: 'center',formatter:jx.gf.bool"></th>
        <th data-options="title: '公共', field: 'publiced', width: 50, align: 'center',formatter:jx.gf.bool"></th>
        <th data-options="title: 'Web', field: 'web', width: 50, align: 'center',formatter:jx.gf.bool"></th>
        <th data-options="title: 'Web路径', field: 'webUrl', width: 300"></th>
        <th data-options="title: 'Win', field: 'win', width: 50, align: 'center',formatter:jx.gf.bool"></th>
        <th data-options="title: 'Win路径', field: 'winUrl', width: 300"></th>
    </tr>
    </thead>
</table>
<@footer>
    <script src="${web.url('/sys/js/module.js')}"></script>
</@footer>