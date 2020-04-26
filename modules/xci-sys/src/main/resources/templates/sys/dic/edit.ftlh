<#-- @ftlvariable name="entity" type="com.github.lvyanyang.sys.entity.SysDicCategory" -->
<#-- @ftlvariable name="parentName" type="java.lang.String" -->
<#include "/sys/_inc/_layout.ftlh">
<#assign url=xci.saveUrl("/sys/dicCategory/","createSave","updateSave")/>
<@header/>
<div class="jxpanel winpanel" data-fit="true">
    <form id="editform" class="jxform" method="post" action="${url}">
        <input name="id" value="${entity.id}" type="hidden">
        <input name="parentId" value="${entity.parentId}" type="hidden">
        <input name="path" value="${entity.path}" type="hidden">
        <table class="table jxtable-form">
            <tr>
                <th class="w-100px">上级</th>
                <td>
                    <p class="form-control-static">${parentName}</p>
                </td>
            </tr>
            <tr>
                <th>名称</th>
                <td>
                    <input class="form-control" name="name" value="${entity.name}" maxlength="30"
                           data-validate="required: [true,'请输入字典名称'],maxlength: 30" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>编码</th>
                <td>
                    <input class="form-control" name="code" value="${entity.code}" maxlength="50"
                           data-validate="required: [true,'请输入字典编码'],maxlength: 50" autocomplete="off"/>
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
        jx.auth.bindEditFormTreeSuccess();
    </script>
</@footer>