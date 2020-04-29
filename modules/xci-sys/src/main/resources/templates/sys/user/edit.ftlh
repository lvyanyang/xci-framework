<#-- @ftlvariable name="entity" type="com.github.lvyanyang.sys.entity.SysUser" -->
<#-- @ftlvariable name="roles" type="java.util.List<com.github.lvyanyang.sys.entity.SysRole>" -->
<#include "/_inc/_layout.ftlh">
<#assign url=xci.saveUrl("/sys/user/","createSave","updateSave")/>
<@header>
    <style>
        .chosen-container-multi .chosen-choices {
            height: auto !important;
        }
    </style>
</@header>
<div class="jxpanel winpanel" data-fit="true">
    <form id="editform" class="jxform" method="post" action="${url}">
        <input name="id" value="${entity.id}" type="hidden">
        <input name="category" value="0" type="hidden">
        <table class="table jxtable-form">
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
                <th class="w-120px">机构</th>
                <td>
                    <input id="deptName" name="deptName" class='form-control jxcombotree'
                           data-value="${entity.deptId}" data-validate="required: [true,'请选择机构']"
                           data-options="editable: false,showClear: false,hiddenField: 'deptId',panelHeight: '250px',
                                        treeOptions: {url: '${web.url('/sys/dept/user-own-departments')}',defaultIconCls: 'icon-users'}"/>
                </td>
            </tr>
            <tr>
                <th>角色</th>
                <td>
                    <#--<select name="roleIds" class="form-control jxchosen" multiple-->
                    <#--data-options="{display_selected_options:false}">-->
                    <#--<@xci.options simpleList=roleSimples selectedValue=roleIds isMultiple=true/>-->
                    <#--</select>-->
                    <select name="roleIds" class="form-control jxselect">
                        <option></option>
                        <@xci.options simpleList=roles selectedValue=entity.roleIds/>
                    </select>
                </td>
            </tr>
            <tr>
                <th>职位</th>
                <td>
                    <input class="form-control" id="post" name="post" value="${entity.post}" maxlength="100"
                           data-validate="required: [true,'请输入职位'],maxlength: 100" autocomplete="off"/>
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
                    <input class="form-control" type="text" name="email" value="${entity.email}" maxlength="50"
                           data-validate="email: [true,'请输入正确的电子邮件'],maxlength: 50" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>开始有效期</th>
                <td>
                    <input class="form-control" id="allowStartTime" name="allowStartTime" value="${entity.allowStartTime}"
                           data-validate="required: [true,'请输入开始有效期']" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>结束有效期</th>
                <td>
                    <input class="form-control" id="allowEndTime" name="allowEndTime" value="${entity.allowEndTime}"
                           data-validate="required: [true,'请输入结束有效期']" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>选项</th>
                <td>
                    <label>
                        <input class="jxcheck" name="status" <@xci.isChecked value=entity.status />
                               type="checkbox" value="1" data-unchecked-value="0"/> 启用
                    </label>
                    <br>
                    <label>
                        <input class="jxcheck" name="pwdMustModify" <@xci.isChecked value=entity.pwdMustModify />
                               type="checkbox" value="1" data-unchecked-value="0"/> 下次登录时须更改密码
                    </label>
                    <br>
                    <label>
                        <input class="jxcheck" name="pwdAllowModify" <@xci.isChecked value=entity.pwdAllowModify />
                               type="checkbox" value="1" data-unchecked-value="0"/> 允许用户更改密码
                    </label>
                    <br>
                    <label>
                        <input class="jxcheck" name="pwdNeverExpire" <@xci.isChecked value=entity.pwdNeverExpire />
                               type="checkbox" value="1" data-unchecked-value="0"/> 密码永不过期
                    </label>
                    <#if sys.currentUser().admin>
                        <br>
                        <label>
                            <input class="jxcheck" name="admin" <@xci.isChecked value=entity.admin />
                                   type="checkbox" value="1" data-unchecked-value="0"/> 管理员
                        </label>
                        <br>
                        <label>
                            <input class="jxcheck" name="visible" <@xci.isChecked value=entity.visible />
                                   type="checkbox" value="1" data-unchecked-value="0"/> 管理员可见
                        </label>
                    </#if>
                </td>
            </tr>
            <tr>
                <th>备注</th>
                <td>
                    <textarea class="form-control" name="remark" maxlength="500"
                              data-validate="maxlength: 500" rows="3"
                              style="resize: vertical">${entity.remark}</textarea>
                </td>
            </tr>
        </table>
    </form>
    <div class="panel-footer text-right">
        <@xci.editPanelFooter/>
    </div>
</div>
<@footer>
    <script>jx.auth.bindEditFormSuccess();</script>
</@footer>