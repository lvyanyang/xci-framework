<#include "/_inc/_layout.ftlh">
<#assign allowCreate=sys.auth("sys.seq.insert")/>
<#assign allowEdit=sys.auth("sys.seq.edit")/>
<#assign allowDelete=sys.auth("sys.seq.delete")/>
<#assign allowExport=sys.auth("sys.seq.export")/>
<@header/>
<div id="gridtoolbar">
    <form id="gridform" class="jxlayout-form form-inline jxform">
        <div class="form-group">
            <input name="name" class="form-control w-200px" placeholder="请输入编码/名称" autocomplete="off">
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
                <a id="btn-export"
                   class="btn btn-default jxexport"
                   data-url="${web.url('/sys/seq/export')}"
                   data-param="jx.serialize($('#gridform'))">
                    <i class="icon-share-alt"></i>
                    导出
                </a>
            </@xci.echo>
        </div>
    </form>
</div>
<table id="grid" class="jxgrid" data-options="form:'#gridform',toolbar:'#gridtoolbar'">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="title:'名称',field:'name',width:250,align:'left',sortable: true,formatter: jx.gf.formatName"></th>
        <th data-options="title:'编码',field:'code',width:200,align:'left',sortable: true"></th>
        <th data-options="title:'开始值',field:'startWith',width:120,align:'center',sortable: true"></th>
        <th data-options="title:'当前值',field:'currentValue',width:120,align:'center',sortable: true"></th>
        <th data-options="title:'增量',field:'incrementBy',width:120,align:'center'"></th>
        <th data-options="title:'备注',field:'remark',width:200"></th>
    </tr>
    </thead>
</table>
<@footer>
    <script src="${web.url('/sys/js/seq.js')}"></script>
</@footer>