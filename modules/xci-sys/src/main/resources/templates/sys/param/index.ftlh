<#include "/_inc/_layout.ftlh">
<#assign allowInsert=sys.auth("sys.param.insert")/>
<#assign allowUpdate=sys.auth("sys.param.update")/>
<#assign allowDelete=sys.auth("sys.param.delete")/>
<@header/>
<div id="gridtoolbar">
    <form id="gridform" class="jxlayout-form form-inline jxform">
        <div class="form-group">
            <input name="name" class="form-control w-200px" placeholder="请输入编码/名称" autocomplete="off">
        </div>
        <div class="form-group">
            <select id="category" name="category" class="form-control jxselect"
                    data-select-submit="true" data-allow-clear="false" data-width="110">
                <option value=" ">全部分类</option>
                <#--<@dic.sysParamCategory selectedValue=''/>-->
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
               data-url="${web.url('/sys/param/export')}"
               data-param="jx.serialize($('#gridform'))">
                <i class="icon-share-alt"></i>
                导出
            </a>
        </div>
    </form>
</div>
<div id="gridcmenu">
    <@xci.echo test=(allowUpdate||allowDelete)>
        <ul class="dropdown-menu">
            <@xci.echo test=allowUpdate>
                <li><a id="btn-cmedit"><i class="icon-pencil"></i> 编辑</a></li></@xci.echo>
            <@xci.echo test=allowDelete>
                <li><a id="btn-cmdelete"><i class="icon-trash"></i> 删除</a></li></@xci.echo>
        </ul>
    </@xci.echo>
</div>
<table id="grid" class="jxgrid" data-options="form:'#gridform',toolbar:'#gridtoolbar'">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="title:'名称',field:'name',width:250,align:'left',sortable: true,formatter: jx.gf.formatName"></th>
        <th data-options="title:'编码',field:'code',width:300,align:'left',sortable: true"></th>
        <th data-options="title:'参数值',field:'value',width:300,align:'left'"></th>
        <th data-options="title:'分类',field:'categoryName',width:120,align:'left',sortable: false"></th>
        <th data-options="title:'备注',field:'remark',width:500"></th>
    </tr>
    </thead>
</table>
<@footer>
    <script src="${web.url('/sys/js/param.js')}"></script>
</@footer>