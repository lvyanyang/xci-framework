<#-- @ftlvariable name="entity" type="com.github.lvyanyang.sys.entity.SysUser" -->
<#include "/sys/_inc/_layout.ftlh">
<#assign url=xci.saveUrl("/sys/user/","createSave","updateSave")/>
<@header>
    <style>
        .chosen-container-multi .chosen-choices{
            height: auto !important;
        }
    </style>
</@header>
<div class="jxpanel winpanel" data-fit="true">
    <form id="editform" class="jxform" method="post" action="${url}">
        <input name="id" value="${entity.id}" type="hidden">
        <table class="table jxtable-form">
            <tr>
                <th class="w-120px">部门</th>
                <td>
                    <input id="department" name="deptName" class='form-control jxcombotree'
                           data-value="${entity.deptId}" data-validate="required: [true,'请选择部门']"
                           data-options="editable: false,showClear: false,hiddenField: 'deptId',panelHeight: '250px',
                                        treeOptions: {url: '<@xci.url "/sys/dept/user-own-departments"/>',defaultIconCls: 'icon-users'}"/>
                </td>
            </tr>
            <tr>
                <th>账号</th>
                <td>
                    <input class="form-control" name="account" value="${entity.account}" autofocus maxlength="20"
                           data-validate="required:[true,'请输入账号'],maxlength: 20" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>姓名</th>
                <td>
                    <input class="form-control" name="name" value="${entity.name}" maxlength="20"
                           data-validate="required: [true,'请输入姓名'],maxlength: 20" autocomplete="off"/>
                </td>
            </tr>
            <@xci.isCreate>
                <tr>
                    <th>密码</th>
                    <td>
                        <input class="form-control" id="password" name="password" type="password"
                               data-validate="required: [true,'请输入密码']" autocomplete="off"/>
                    </td>
                </tr>
                <tr>
                    <th>确认密码</th>
                    <td>
                        <input class="form-control" name="confirmPassword" type="password"
                               data-validate="required: [true,'请输入确认密码'],equalTo: ['#password','确认密码必须与密码输入一致']"
                               autocomplete="off"/>
                    </td>
                </tr>
            </@xci.isCreate>
            <tr>
                <th>角色</th>
                <td>
                    <#--<select name="roleIds" class="form-control jxchosen" multiple-->
                            <#--data-options="{display_selected_options:false}">-->
                        <#--<@xci.options simpleList=roleSimples selectedValue=roleIds isMultiple=true/>-->
                    <#--</select>-->
                    <select name="roleId" class="form-control jxselect">
                        <option></option>
                        <@xci.options simpleList=roleSimples selectedValue=entity.roleId/>
                    </select>
                </td>
            </tr>
            <tr>
                <th>手机</th>
                <td>
                    <input class="form-control" name="mobile" value="${entity.mobile}" data-validate="phone:true"
                           autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>邮件</th>
                <td>
                    <input class="form-control" name="email" value="${entity.email}" maxlength="50"
                           data-validate="email: [true,'请输入正确的电子邮件'],maxlength: 50" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>状态</th>
                <td>
                    <label>
                        <input class="jxcheck" name="status" <@xci.isChecked value=entity.status />
                               type="checkbox" value="1" data-unchecked-value="0"/> 启用
                    </label>
                    <#if helper.getCurrentUser().getIsAdmin()>
                        <label class="ml-5">
                            <input class="jxcheck" name="isAdmin" <@xci.isChecked value=entity.isAdmin />
                                   type="checkbox" value="1" data-unchecked-value="0"/> 管理员
                        </label>
                    </#if>
                </td>
            </tr>
            <tr>
                <th>备注</th>
                <td>
                    <textarea class="form-control" name="remark" maxlength="1000"
                              data-validate="maxlength: 1000" rows="3"
                              style="resize: none">${entity.remark}</textarea>
                </td>
            </tr>
        </table>
    </form>
    <div class="panel-footer text-right">
        <@xci.editPanelFooter/>
    </div>
</div>
<@footer>
    <script>
        jx.auth.bindEditFormSuccess();
    </script>
</@footer>