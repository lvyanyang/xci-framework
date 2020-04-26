<#include "/sys/_inc/_layout.ftlh">
<@header/>
<div class="jxpanel winpanel" data-fit="true">
    <form id="editform" class="jxform" method="post" action="<@xci.url "/auth/user/modify-password"/>" data-msgtip="false">
        <table class="table jxtable-form">
            <tr>
                <th class='w-100px'>当前密码</th>
                <td>
                    <input id="currentPassword" name="currentPassword" class="form-control"  maxlength="24" data-validate="required: [true,'请输入当前密码'],maxlength: 24" type="password" autocomplete="off">
                </td>
            </tr>
            <tr>
                <th>新密码</th>
                <td>
                    <input id="newPassword" name="newPassword" class="form-control" type="password" maxlength="24" data-validate="required: [true,'请输入新密码'],maxlength: 24" autocomplete="off">
                </td>
            </tr>
            <tr>
                <th>确认密码</th>
                <td>
                    <input id="confirmPassword" name="confirmPassword" class="form-control" type="password" maxlength="24" data-validate="required: [true,'请输入确认密码'],equalTo: ['#newPassword','确认密码必须与新密码输入一致'],maxlength: 24" autocomplete="off">
                </td> 
            </tr>
        </table>
    </form>
    <div class="panel-footer text-right">
        <button class="btn btn-primary" type="button" onclick="$('#editform').jxform().submit()">
            <i class="fa fa-save"></i> 保存
        </button>
        <button class="btn btn-default" type="button" onclick="jx.closeDialog()">
            <i class="fa fa-sign-in"></i> 取消
        </button>
    </div>
</div>
<@footer>
    <script>
        $('#editform').onFormSuccess(function (e, result) {
            top.jx.toastr.success('密码修改成功');
            jx.closeDialog();
        });
    </script>
</@footer>