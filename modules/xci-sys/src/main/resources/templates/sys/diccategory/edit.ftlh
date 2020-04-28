<#-- @ftlvariable name="entity" type="com.github.lvyanyang.sys.entity.SysDicCategory" -->
<#include "/_inc/_layout.ftlh">
<#assign url=xci.saveUrl("/sys/dicCategory/","createSave","updateSave")/>
<@header/>
<div class="jxpanel winpanel" data-fit="true">
    <form id="editform" class="jxform" method="post" action="${url}">
        <input name="id" value="${entity.id}" type="hidden">
        <table class="table jxtable-form">
            <tr>
                <th class="w-120px">上级字典类型</th>
                <td>
                    <input name="parentName" class='form-control jxcombotree' data-value="${entity.parentId}"
                           data-options="editable: false,showClear: false,hiddenField: 'parentId',panelHeight: '250px',
                                                    treeOptions: {url: '${web.url("/sys/dicCategory/parentList?id="+entity.id+"&created="+_create_)}',defaultIconCls: 'icon-notebook',loadEmptyMessage: '无'}"/>
                </td>
            </tr>
            <tr>
                <th>字典类型名称</th>
                <td>
                    <input class="form-control" id="name" name="name" value="${entity.name}" maxlength="100"
                           data-validate="required: [true,'请输入字典类型名称'],maxlength: 100" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>字典类型编码</th>
                <td>
                    <input class="form-control" id="code" name="code" value="${entity.code}" maxlength="100"
                           data-validate="required: [true,'请输入字典类型编码'],maxlength: 100" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>备注</th>
                <td>
                    <textarea class="form-control" name="remark" maxlength="1000"
                              data-validate="maxlength: 1000" rows="3" style="resize: vertical">${entity.remark}</textarea>
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
