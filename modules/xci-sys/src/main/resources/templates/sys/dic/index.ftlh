<#include "/sys/_inc/_layout.ftlh" >
<#assign allowCreate=helper.isAuthorize("sys.dic.insert")/>
<#assign allowEdit=helper.isAuthorize("sys.dic.update")/>
<#assign allowDelete=helper.isAuthorize("sys.dic.delete")/>
<#assign allowExport=helper.isAuthorize("sys.dic.export")/>

<#assign allowItemCreate=helper.isAuthorize("SystemDicDetails.Create")/>
<#assign allowItemEdit=helper.isAuthorize("SystemDicDetails.Edit")/>
<#assign allowItemDelete=helper.isAuthorize("SystemDicDetails.Delete")/>
<#assign allowItemExport=helper.isAuthorize("SystemDicDetails.Export")/>
<@header bodyCls="jxlayout"/>
<div id="treecmenu">
    <ul class="dropdown-menu">
        <li><a id="btn-tcmdetails"><i class="icon-eye"></i> 查看</a></li>
        <@xci.echo test=allowCreate>
            <li><a id="btn-tcmcreateroot"><i class="icon-plus"></i> 新增根节点</a></li>
            <li role="separator" class="divider"></li>
            <li><a id="btn-tcmcreate"><i class="icon-plus"></i> 新增</a></li>
        </@xci.echo>
        <@xci.echo test=allowEdit>
            <li><a id="btn-tcmedit"><i class="icon-pencil"></i> 编辑</a></li></@xci.echo>
        <@xci.echo test=allowDelete>
            <li><a id="btn-tcmdelete"><i class="icon-trash"></i> 删除</a></li></@xci.echo>
        <@xci.echo test=allowExport>
            <li>
            <li><a id="btn-tcmexport"><i class="icon-share-alt"></i> 导出</a></li>
        </@xci.echo>
    </ul>
</div>
<div title="系统字典" data-options="region:'west',width:200,split:true,border:false">
    <div class="m-5">
        <input class="form-control input-sm jxtree-filter" placeholder="请输入关键字模糊查询" autocomplete="off">
    </div>
    <ul id="tree" class="jxtree"
        data-dialog-width="40%" data-dialog-height="360px"
        data-filter-box="$('.jxtree-filter')"
        data-click-toggle="false"
        data-dnd="true"
        data-default-icon-cls="icon-notebook"></ul>
</div>
<div title="字典明细" data-options="region:'center',border:false">
    <#include "/sys/dicdetails/_grid.ftlh" >
</div>
<#include "/sys/dicdetails/_toolbar.ftlh" >
<#include "/sys/dicdetails/_menu.ftlh" >
<@footer>
    <script src="<@xci.url '/sys/js/dic.js'/>"></script>
</@footer>