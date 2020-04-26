<#-- @ftlvariable name="dicName" type="java.lang.String" -->
<table id="grid" class="jxtreegrid"
       data-dialog-width="40%" data-dialog-height="460px"
       data-options="dnd: true,checkbox:false,border:false,defaultIconCls:'icon-badge',
        form:'#gridform',toolbar:'#gridtoolbar'">
    <thead>
    <tr>
        <th data-options="title:'名称',field:'name',width:200"></th>
        <th data-options="title:'值',field:'value',width:200"></th>
        <th data-options="title:'状态',field:'status',width:50,align:'center',formatter:jx.gf.status"></th>
        <#--<th data-options="title:'操作人',field:'operateUserName',width:100,align:'center'"></th>-->
        <#--<th data-options="title:'操作时间',field:'operateDateTime',width:170,align:'center',sortable: true"></th>-->
    </tr>
    </thead>
</table>