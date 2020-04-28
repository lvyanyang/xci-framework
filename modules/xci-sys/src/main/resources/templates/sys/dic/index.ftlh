<#include "/_inc/_layout.ftlh">
<@header bodyCls="jxlayout"/>
<div id="treecmenu">
    <ul class="dropdown-menu">
        <li><a id="btn-tcmcreate"><i class="icon-plus"></i> 新增</a></li>
        <li><a id="btn-tcmedit"><i class="icon-pencil"></i> 编辑</a></li>
        <li><a id="btn-tcmdelete"><i class="icon-trash"></i> 删除</a></li>
        <li><a id="btn-tcmdetails"><i class="icon-eye"></i> 查看</a></li>
        <li><a id="btn-tcmexport"><i class="icon-share-alt"></i> 导出</a></li>
    </ul>
</div>
<div title="系统字典" data-options="region:'west',width:200,split:true,border:false">
    <div class="m-5">
        <input class="form-control input-sm jxtree-filter" placeholder="请输入关键字模糊查询" autocomplete="off">
    </div>
    <ul id="tree" class="jxtree"
        data-filter-box="$('.jxtree-filter')"
        data-click-toggle="false"
        data-dnd="true"
        data-default-icon-cls="icon-notebook"></ul>
</div>
<div title="字典明细" data-options="region:'center',border:false">
    <#include "/sys/dic/_inc/_grid.ftlh" >
</div>
<#include "/sys/dic/_inc/_toolbar.ftlh" >
<#include "/sys/dic/_inc/_menu.ftlh" >
<@footer>
    <script src="${web.url('/sys/js/dic.js')}"></script>
</@footer>