<table id="grid" class="jxtreegrid"
       data-options="dnd: true,checkbox:false,border:false,defaultIconCls:'icon-badge',
        form:'#gridform',toolbar:'#gridtoolbar'">
    <thead>
    <tr>
        <th data-options="title:'字典名称',field:'name',width:300"></th>
        <th data-options="title:'字典值',field:'value',width:300"></th>
        <th data-options="title:'状态',field:'status',width:50,align:'center',formatter:jx.gf.status"></th>
        <th data-options="title:'序号',field:'path',width:80,align:'center',sortable: true"></th>
    </tr>
    </thead>
</table>