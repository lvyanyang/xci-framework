<#-- @ftlvariable name="entity" type="com.github.lvyanyang.sys.entity.SysUserSave" -->
<#-- @ftlvariable name="roles" type="java.util.List<com.github.lvyanyang.sys.entity.SysRole>" -->
<#include "/_inc/_layout.ftl">
<#assign url=saveUrl("/sys/user/","createSave","updateSave")/>
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
                <th class="w-100px">账号</th>
                <td>
                    <input class="form-control" name="account" value="${entity.account}"
                           autofocus maxlength="20"
                           ${web.isReadonly(_create_!="1")}
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
            <@isCreate>
                <tr>
                    <th>密码</th>
                    <td>
                        <input class="form-control" id="pwd" name="pwd" type="password" maxlength="20"
                               data-validate="required: [true,'请输入密码']" autocomplete="off"/>
                    </td>
                </tr>
                <tr>
                    <th>确认密码</th>
                    <td>
                        <input class="form-control" name="confirmPwd" type="password" maxlength="20"
                               data-validate="required: [true,'请输入确认密码'],equalTo: ['#pwd','确认密码必须与密码输入一致']"
                               autocomplete="off"/>
                    </td>
                </tr>
            </@isCreate>
            <tr>
                <th>机构</th>
                <td>
                    <input name="deptName" class='form-control jxcombotree'
                           data-value="${entity.deptId}" data-validate="required: [true,'请选择机构']"
                           data-options="editable: false,showClear: false,hiddenField: 'deptId',panelHeight: '250px',
                                        treeOptions: {url: '${web.url('/sys/dept/currentUserDeptTree')}',defaultIconCls: 'icon-users'}"/>
                </td>
            </tr>
            <tr>
                <th>角色</th>
                <td>
                    <#--<select name="roleIds" class="form-control jxchosen" multiple-->
                    <#--data-options="{display_selected_options:false}">-->
                    <#--<@options simpleList=roleSimples selectedValue=roleIds isMultiple=true/>-->
                    <#--</select>-->
                    <select name="roleIds" class="form-control jxchosen" multiple>
                        <option></option>
                        #{web.selectOptions(roles,entity.roleIds,true)}
                    </select>
                </td>
            </tr>
            <tr>
                <th>职位</th>
                <td>
                    <select class="form-control jxselect" name="post" data-allow-clear="true">
                        <option></option>
                        ${sys.dicSysUserPost(entity.post)}
                    </select>
                </td>
            </tr>
            <tr>
                <th>手机</th>
                <td>
                    <input class="form-control" name="mobile" value="${entity.mobile}" maxlength="11" data-validate="maxlength: 11"
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
                    <div class="input-group date jxdate" data-options="endDate: '0d',format: 'yyyy-mm-dd'">
                        <input id="allowStartTime" name="allowStartTime" class="form-control" value="${(entity.allowStartTime?string("yyyy-MM-dd"))!}"
                               autocomplete="off">
                        <span class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </span>
                    </div>
                </td>
            </tr>
            <tr>
                <th>结束有效期</th>
                <td>
                    <div class="input-group date jxdate" data-options="format: 'yyyy-mm-dd'">
                        <input id="allowEndTime" name="allowEndTime" class="form-control" value="${(entity.allowEndTime?string("yyyy-MM-dd"))!}"
                               data-validate="{compareEqualDate:'#allowStartTime'}" autocomplete="off">
                        <span class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </span>
                    </div>
                </td>
            </tr>
            <tr>
                <th>选项</th>
                <td>
                    <label>
                        <input class="jxcheck" name="status" ${web.isChecked(entity.status)}
                               type="checkbox" value="1" data-unchecked-value="0"/> 启用
                    </label>
                    <br>
                    <label>
                        <input class="jxcheck" name="pwdMustModify" ${web.isChecked(entity.pwdMustModify)}
                               type="checkbox" value="1" data-unchecked-value="0"/> 下次登录时须更改密码
                    </label>
                    <br>
                    <label>
                        <input class="jxcheck" name="pwdAllowModify" ${web.isChecked(entity.pwdAllowModify)}
                               type="checkbox" value="1" data-unchecked-value="0"/> 允许用户更改密码
                    </label>
                    <br>
                    <label>
                        <input class="jxcheck" name="pwdNeverExpire" ${web.isChecked(entity.pwdNeverExpire)}
                               type="checkbox" value="1" data-unchecked-value="0"/> 密码永不过期
                    </label>
                    <#if sys.currentUser().admin>
                        <br>
                        <label>
                            <input class="jxcheck" name="admin" ${web.isChecked(entity.admin)}
                                   type="checkbox" value="1" data-unchecked-value="0"/> 管理员
                        </label>
                        <br>
                        <label>
                            <input class="jxcheck" name="visible" ${web.isChecked(entity.visible)}
                                   type="checkbox" value="1" data-unchecked-value="0"/> 显示
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
        <@editPanelFooter/>
    </div>
</div>
<@footer>
    <script>jx.auth.bindEditFormSuccess();</script>
</@footer>