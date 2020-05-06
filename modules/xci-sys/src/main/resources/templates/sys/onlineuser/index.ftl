<#include "/_inc/_layout.ftl">
<#assign allowLogoff=sys.auth("sys.onlineUser.logoff")/>
<#--<#assign allowExport=helper.isAuthorize("sys.onlineUser.export")/>-->
<@header/>
<div id="gridtoolbar">
    <form id="gridform" class="jxlayout-form form-inline jxform">
        <div class="form-group">
            <input name="name" class="form-control w-200px" placeholder="请输入账号/姓名" autocomplete="off">
        </div>
        <button class="btn btn-primary ml-5" type="submit">
            <i class="fa fa-search"></i> 查询
        </button>
        <@echo test=allowLogoff>
            <div class="btn-group">
                <a id="btn-logoff" class="btn btn-default">
                    <i class="icon-trash"></i> 注销
                </a>
            </div>
        </@echo>
        <#--<@echo test=allowExport>-->
        <#--    <a id="btn-export" class="btn btn-default">-->
        <#--        <i class="icon-share-alt"></i> 导出-->
        <#--    </a>-->
        <#--</@echo>-->
    </form>
</div>
<table id="grid" class="jxgrid" data-options="form:'#gridform',toolbar:'#gridtoolbar',idField:'id'">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="title:'姓名',field:'name',width:150,align:'center',formatter:jx.gf.formatName"></th>
        <th data-options="title:'账号',field:'account',width:150,align:'center'"></th>
        <th data-options="title:'登录时间',field:'loginTime',width:160,align:'center'"></th>
        <th data-options="title:'激活时间',field:'activeTime',width:160,align:'center'"></th>
        <th data-options="title:'部门',field:'deptName',width:200,align:'center'"></th>
        <#--<th data-options="title:'角色',field:'roleName',width:200,align:'center'"></th>-->
        <th data-options="title:'登录IP',field:'ipAddress',width:200,align:'center'"></th>
        <th data-options="title:'超管',field:'admin',width:100,align:'center',formatter:jx.gf.status"></th>
    </tr>
    </thead>
</table>
<@footer>
    <script src="${web.url('/sys/js/onlineuser.js')}"></script>
</@footer>