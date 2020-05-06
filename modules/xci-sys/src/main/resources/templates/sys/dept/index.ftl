<#include "/_inc/_layout.ftl">
<#assign allowCreate=sys.auth("sys.dept.insert")/>
<#assign allowEdit=sys.auth("sys.dept.update")/>
<#assign allowDelete=sys.auth("sys.dept.delete")/>
<#assign allowExport=sys.auth("sys.dept.export")/>
<@header/>
<div id="gridtoolbar">
    <form id="gridform" class="jxform jxlayout-form form-inline">
        <div class="form-group">
            <input class="form-control w-200px" name="name" placeholder="组织机构名称/简拼查询" autocomplete="off">
        </div>
        <div class="form-group">
            <select name="status" class="jxselect form-control"
                    data-select-submit="true" data-allow-clear="false"
                    data-width="110">
                ${web.statusSelectOptions()}
            </select>
        </div>
        <button class="btn btn-primary ml-5" type="submit">
            <i class="fa fa-search"></i> 查询
        </button>
        <div class="btn-group">
            <@echo test=allowCreate>
                <#--<a id="btn-createroot" class="btn btn-default">-->
                <#--    <i class="icon-vector"></i> 新增根节点-->
                <#--</a>-->
                <a id="btn-create" class="btn btn-default">
                    <i class="icon-plus"></i> 新增
                </a>
            </@echo>
            <@echo test=allowEdit>
                <a id="btn-edit" class="btn btn-default">
                    <i class="icon-pencil"></i> 编辑
                </a>
            </@echo>
            <@echo test=allowDelete>
                <a id="btn-delete" class="btn btn-default">
                    <i class="icon-trash"></i> 删除
                </a>
            </@echo>
            <@echo test=allowExport>
                <a id="btn-export"
                   class="btn btn-default jxexport"
                   data-url="${web.url('/sys/dept/export')}"
                   data-param="jx.serialize($('#gridform'))">
                    <i class="icon-share-alt"></i>
                    导出
                </a>
            </@echo>
        </div>
    </form>
</div>
<div id="gridcmenu">
    <@echo test=(allowCreate||allowEdit||allowDelete)>
        <ul class="dropdown-menu">
            <@echo test=allowCreate>
                <#--<li><a id="btn-cmcreateroot"><i class="icon-vector"></i> 新增根节点</a></li>-->
                <li><a id="btn-cmcreate"><i class="icon-plus"></i> 新增</a></li>
            </@echo>
            <@echo test=allowEdit><li><a id="btn-cmedit"><i class="icon-pencil"></i> 编辑</a></li></@echo>
            <@echo test=allowDelete><li><a id="btn-cmdelete"><i class="icon-trash"></i> 删除</a></li></@echo>
        </ul>
    </@echo>
</div>
<#--,queryParams:{status:1}-->
<table id="grid" class="jxtreegrid" data-options="dnd: true,checkbox:false,defaultIconCls:'icon-users',
            form:'#gridform',toolbar:'#gridtoolbar'">
    <thead>
    <tr>
        <th data-options="title: '机构名称', field: 'name', width: 300,align:'left'"></th>
        <th data-options="title: '机构编码', field: 'code', width: 100,align:'center'"></th>
        <th data-options="title: '负责人', field: 'leaderName', width: 150,align:'center'"></th>
        <th data-options="title: '类型', field: 'category', width: 80,align:'center'"></th>
        <th data-options="title: '性质', field: 'nature', width: 80,align:'center'"></th>
        <th data-options="title: '状态',field:'status',width:50,align:'center',formatter:jx.gf.status"></th>
        <th data-options="title: '备注',field:'remark',width:250"></th>
    </tr>
    </thead>
</table>
<@footer>
    <script src="${web.url('/sys/js/dept.js')}"></script>
</@footer>