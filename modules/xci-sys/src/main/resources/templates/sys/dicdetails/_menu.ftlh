<div id="gridcmenu">
    <@xci.echo test=(allowItemCreate||allowItemEdit||allowItemDelete)>
        <ul class="dropdown-menu">
            <@xci.echo test=allowItemCreate>
                <#--<li><a id="btn-cmcreateroot"><i class="icon-vector"></i> 新增根节点</a></li>-->
                <li><a id="btn-cmcreate"><i class="icon-plus"></i> 新增</a></li>
            </@xci.echo>
            <@xci.echo test=allowItemEdit><li><a id="btn-cmedit"><i class="icon-pencil"></i> 编辑</a></li></@xci.echo>
            <@xci.echo test=allowItemDelete><li><a id="btn-cmdelete"><i class="icon-trash"></i> 删除</a></li></@xci.echo>
        </ul>
    </@xci.echo>
</div>