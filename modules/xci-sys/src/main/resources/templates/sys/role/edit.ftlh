<#-- @ftlvariable name="entity" type="com.github.lvyanyang.sys.entity.SysRole" -->
<#include "/_inc/_layout.ftlh">
<#assign url=xci.saveUrl("/sys/role/","createSave","updateSave")/>
<@header/>
<div class="jxpanel winpanel" data-fit="true">
    <form id="editform" class="jxform" method="post" action="${url}">
        <input name="id" value="${entity.id}" type="hidden">
        <input name="path" value="${entity.path}" type="hidden">
        <table class="table jxtable-form">
            <tr>
                <th class="w-100px">所属机构</th>
                <td>
                    <input name="deptName" class='form-control jxcombotree'
                           data-value="${entity.deptId}" data-validate="required: [true,'请选择所属机构']"
                           data-options="editable: false,showClear: false,hiddenField: 'deptId',panelHeight: '250px',
                                        treeOptions: {url: '${web.url('/sys/dept/currentUserDeptTree')}',defaultIconCls: 'icon-users'}"/>
                </td>
            </tr>
            <tr class="w-100px">
                <th>角色名称</th>
                <td>
                    <input class="form-control" name="name" value="${entity.name}" maxlength="50"
                           data-validate="required: [true,'请输入角色名称']" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>角色编码</th>
                <td>
                    <input class="form-control" name="code" value="${entity.code}" maxlength="50"
                           data-validate="required: [true,'请输入角色编码']" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>状态</th>
                <td>
                    <label>
                        <input class="jxcheck" name="status" <@xci.isChecked value=entity.status />
                               type="checkbox" value="1" data-unchecked-value="0"/> 启用
                    </label>
                </td>
            </tr>
            <tr>
                <th>备注</th>
                <td>
                    <textarea class="form-control" name="remark" maxlength="500" data-validate="maxlength: 500"
                              rows="3" style="resize: vertical">${entity.remark}</textarea>
                </td>
            </tr>
        </table>
    </form>
    <div class="panel-footer text-right">
        <@xci.isCreate>
            <button id="btnContinuousSave" class="btn btn-success" type="button">
                <i class="icon-plus"></i> 保存并新增
            </button>
        </@xci.isCreate>
        <button id="btnSave" class="btn btn-primary" type="button">
            <i class="fa fa-save"></i> 保存
        </button>
        <button id="btnCancel" class="btn btn-default" type="button">
            <i class="fa fa-sign-in"></i> 取消
        </button>
    </div>
</div>
<@footer>
    <script>
        //参数:_$form 表单对象 _mode 操作模式:1.对话框模式 2.页面跳转模式 3.标签页模式
        jx.auth.jxEditFormInit($('#editform'), 1);
    </script>
</@footer>