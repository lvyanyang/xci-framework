<#include "/_inc/_layout.ftl">
<#assign allowRemove=sys.auth("sys.lockUser.remove")/>
<@header/>
<div id="gridtoolbar">
    <form id="gridform" class="jxlayout-form form-inline jxform">
        <#--<div class="form-group">-->
        <#--    <input name="name" class="form-control w-200px" placeholder="请输入账号" autocomplete="off">-->
        <#--</div>-->
        <button class="btn btn-primary ml-5" type="submit">
            <i class="fa fa-search"></i> 刷新
        </button>
        <@echo test=allowRemove>
            <div class="btn-group">
                <a id="btn-remove" class="btn btn-default">
                    <i class="icon-trash"></i> 删除
                </a>
            </div>
        </@echo>
    </form>
</div>
<table id="grid" class="jxgrid" data-options="form:'#gridform',toolbar:'#gridtoolbar',idField:'account'">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="title:'账号',field:'account',width:150,align:'center'"></th>
        <th data-options="title:'错误次数',field:'count',width:150,align:'center'"></th>
        <th data-options="title:'登录时间',field:'loginTime',width:160,align:'center'"></th>
        <th data-options="title:'解锁时间',field:'disLockTime',width:160,align:'center'"></th>
    </tr>
    </thead>
</table>
<@footer>
    <script src="${web.url('/sys/js/lockuser.js')}"></script>
    <script>
      jx.ready(function () {
        jx.sys.LockUser();
      })
    </script>
</@footer>