<#-- @ftlvariable name="entity" type="com.xci.sys.entity.SysDic" -->
<#-- @ftlvariable name="parentName" type="java.lang.String" -->
<#include "/sys/_inc/_layout.ftlh">
<#assign url=xci.saveUrl("/sys/dic/","createSave","updateSave")/>
<@header/>
<div class="jxpanel winpanel" data-fit="true">
    <form id="editform" class="jxform" method="post" action="${url}">
        <input name="id" value="${entity.id!}" type="hidden">
        <input name="parentId" value="${entity.parentId!}" type="hidden">
        <input name="dicId" value="${entity.dicId!}" type="hidden">
        <input name="leaf" value="1" type="hidden">
        <table class="table jxtable-form">
            <tr>
                <th class="w-100px">字典类型</th>
                <td>
                    <p class="form-control-static">${entity.code}</p>
                </td>
            </tr>
            <tr>
                <th>上级</th>
                <td>
                    <p class="form-control-static">${parentName}</p>
                </td>
            </tr>
            <tr>
                <th>字典名称</th>
                <td>
                    <input class="form-control" name="name" value="${entity.name}" maxlength="50"
                           data-validate="required: [true,'请输入选项名称'],maxlength: 50" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>字典值</th>
                <td>
                    <input class="form-control" name="value" value="${entity.value}" maxlength="1000"
                           data-validate="maxlength: 1000" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>状态</th>
                <td>
                    <label>
                        <input class="jxcheck" name="status" <@xci.isChecked value=entity.status /> type="checkbox" value="1"
                               data-unchecked-value="0"/> 启用
                    </label>
                </td>
            </tr>
            <tr>
                <th>备注</th>
                <td>
                <textarea class="form-control" name="remark" maxlength="1000" data-validate="maxlength: 1000" rows="3"
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