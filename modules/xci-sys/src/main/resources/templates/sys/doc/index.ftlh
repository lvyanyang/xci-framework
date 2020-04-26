<#include "/_inc/_layout.ftl" >
<#assign allowCreate=auth.isAuthorize("SystemDoc.Create")/>
<#assign allowEdit=auth.isAuthorize("SystemDoc.Edit")/>
<#assign allowDelete=auth.isAuthorize("SystemDoc.Delete")/>
<@header bodyCls="jxlayout"/>
<div id="gridtoolbar">
    <form id="gridform" class="jxform jxlayout-form form-inline">
        <div class="form-group">
            <input name="title" class="form-control w-300px" placeholder="请输入查询关键字">
        </div>
        <div class="form-group">
            <select name="status" class="jxselect form-control"
                    data-select-submit="true" data-allow-clear="false"
                    data-width="70">
                <@html.statusOptions emptyLable = "全部" enableLable = "发布" disableLable = "草稿"/>
            </select>
        </div>

        <input class="jxswitch" name="containContent" data-on-text="含内容" data-off-text="不含内容"
               value="1" data-unchecked-value="0" type="checkbox">

        <button class="btn btn-primary ml-5" type="submit">
            <i class="fa fa-search"></i> 查询
        </button>
        <div class="btn-group">
	        <@html.echo test=allowCreate>
                <a id="btn-create" class="btn btn-default">
                    <i class="icon-plus"></i> 新增
                </a>
            </@html.echo>
	        <@html.echo test=allowEdit>
	            <a id="btn-edit" class="btn btn-default">
                    <i class="icon-pencil"></i> 编辑
                </a>
            </@html.echo>
	        <@html.echo test=allowDelete>
	            <a id="btn-delete" class="btn btn-default">
                    <i class="icon-trash"></i> 删除
                </a>
            </@html.echo>
        </div>
    </form>
</div>
<div id="gridcmenu">
    <@html.echo test=(allowEdit||allowDelete)>
        <ul class="dropdown-menu">
            <@html.echo test=allowEdit><li><a id="btn-cmedit"><i class="icon-pencil"></i> 编辑</a></li></@html.echo>
            <@html.echo test=allowDelete><li><a id="btn-cmdelete"><i class="icon-trash"></i> 删除</a></li></@html.echo>
        </ul>
    </@html.echo>
</div>
<div title="文档分类" data-options="region:'west',width:200,split:true,border:false">
    <div class="m-5">
        <input class="form-control input-sm jxtree-filter" placeholder="请输入关键字模糊查询" autocomplete="off">
    </div>
    <ul id="tree" class="jxtree"
        data-filter-box="$('.jxtree-filter')"
        data-click-toggle="false"
        data-dnd="false"
        data-default-icon-cls="icon-badge"></ul>
</div>
<div title="文档列表" data-options="region:'center',border:false">
    <table id="grid" class="jxgrid"
           data-options="form:'#gridform',toolbar:'#gridtoolbar'"
           data-dialog-width="100%" data-dialog-height="100%">
        <thead>
        <tr>
            <th data-options="field:'ck',checkbox:true"></th>
            <th data-options="title:'标题',field:'title',width:500,align:'left'"></th>
            <th data-options="title:'格式',field:'docFormat',width:50,align:'center',formatter:jx.gf.docFormat"></th>
            <th data-options="title:'分类',field:'categoryName',width:150,align:'left'"></th>
            <th data-options="title:'状态',field:'status',width:50,align:'center',formatter:jx.gf.docStatus"></th>
            <th data-options="title:'操作人',field:'operateUserName',width:100,align:'center'"></th>
            <th data-options="title:'更新时间',field:'operateDateTime',width:150,align:'center',sortable: true"></th>
        </tr>
        </thead>
    </table>
</div>
<@footer>
    <script src="<@html.url '/auth/js/doc.js'/>"></script>
</@footer>