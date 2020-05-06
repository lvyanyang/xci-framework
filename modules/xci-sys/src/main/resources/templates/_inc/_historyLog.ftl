<#macro histroyLog tableName primaryKey>
    <table class="jxhistroy-grid-detail" data-options="cls:'jxhistroy-grid',border:true,fit:true,queryParams:{tableName:'${tableName}',primaryKey:'${primaryKey}'}">
        <thead>
        <tr>
            <th data-options="title: '操作类型', field: 'category', width: 80, align: 'center',formatter: jx.gf.formatHistoryLogCategory"></th>
            <th data-options="title: '操作人员', field: 'operateUserName', width: 120, align: 'left',formatter: jx.gf.formatHistoryLogName"></th>
            <th data-options="title: '操作时间', field: 'operateDateTime', width: 170, align: 'left',sortable: true"></th>
        </tr>
        </thead>
    </table>
</#macro>