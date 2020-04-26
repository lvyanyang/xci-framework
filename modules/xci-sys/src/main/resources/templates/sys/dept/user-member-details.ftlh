<#import '/spring.ftl' as sps>
<#macro userMemberDetails departmentId>
<div id="userMemberDetailsToolbar">
    <form id="userMemberDetailsForm" class="jxform jxlayout-form">
        <table>
            <tr>
                <td>
                    <input class="form-control w-300px" name="name" placeholder="请输入姓名/账号关键字">
                </td>
                <td>
                    <button class="btn btn-primary ml-5" type="submit">
                        <i class="fa fa-search"></i> 查询
                    </button>
                </td>
            </tr>
        </table>
    </form>
</div>
<table id="userMemberDetailsGrid" data-options="
                url: '<@sps.url '/auth/user/user-member-details?departmentId=${departmentId}'/>',
                cls:'jxgrid-sub',border:true,fit:true,onDblClickRow: jx.auth.onUserGridDblClickRow,
                form: '#userMemberDetailsForm',toolbar: '#userMemberDetailsToolbar'">
    <thead>
        <tr>
            <th data-options="title: '账号', field: 'account', width: 200, sortable: true, align: 'center'"></th>
            <th data-options="title: '姓名', field: 'name', width: 100, align: 'center'"></th>
            <th data-options="title: '部门', field: 'departmentName', width: 200, align: 'center'"></th>
        </tr>
    </thead>
</table>
</#macro>