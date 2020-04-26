<#-- @ftlvariable name="entity" type="com.xci.sys.entity.SysRole" -->
<#include "/sys/_inc/_layout.ftlh">
<#assign url=xci.saveUrl("/sys/role/","createSave","updateSave")/>
<@header/>
<div class="jxpanel winpanel" data-fit="true">
    <form id="editform" class="jxform" method="post" action="${url}">
        <input name="id" value="${entity.id!}" type="hidden">
        <table class="table jxtable-form">
            <tr class="w-100px">
                <th>名称</th>
                <td>
                    <input class="form-control" name="name" value="${entity.name}" maxlength="50"
                           data-validate="required: [true,'请输入角色名称']" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>编码</th>
                <td>
                    <input class="form-control" name="code" value="${entity.code}" maxlength="50"
                           data-validate="required: [true,'请输入角色编码']" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>所属机构</th>
                <td>
                    <input name="deptName" class='form-control jxcombotree'
                           data-value="${entity.deptId}" data-validate="required: [true,'请选择所属机构']"
                           data-options="editable: false,showClear: false,hiddenField: 'deptId',panelHeight: '250px',
                                        treeOptions: {url: '<@xci.url "/sys/dept/user-own-departments"/>',defaultIconCls: 'icon-users'}"/>
                </td>
            </tr>
            <tr>
                <th>序号</th>
                <td>
                    <input name="path" class="jxnumber" type="number" value="${entity.path!0}"
                           data-min="-1000" data-max="9999999" data-step="1" data-decimals="0" data-verticalbuttons="true"
                           data-validate="required: [true,'请输入序号']" style="border-radius: 4px 0 0 4px" autocomplete="off">
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
                    <textarea class="form-control" name="remark" maxlength="1000" data-validate="maxlength: 1000"
                              rows="3" style="resize: none">${entity.remark}</textarea>
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