<#-- @ftlvariable name="entity" type="com.github.lvyanyang.sys.entity.SysDic" -->
<#-- @ftlvariable name="category" type="com.github.lvyanyang.sys.entity.SysDicCategory" -->
<#include "/_inc/_layout.ftlh">
<#assign url=xci.saveUrl("/sys/dic/","createSave","updateSave")/>
<@header/>
<div class="jxpanel winpanel" data-fit="true">
    <form id="editform" class="jxform" method="post" action="${url}">
        <input name="id" value="${entity.id}" type="hidden">
        <input name="categoryCode" value="${entity.categoryCode}" type="hidden">
        <table class="table jxtable-form">
            <tr>
                <th class="w-120px">字典类型</th>
                <td>
                    <p class="form-control-static">${category.name}-(${category.code})</p>
                </td>
            </tr>
            <tr>
                <th>上级</th>
                <td>
                    <input name="parentName" class='form-control jxcombotree' data-value="${entity.parentId}"
                           data-options="editable: false,showClear: true,hiddenField: 'parentId',panelHeight: '250px',
                                 treeOptions: {url: '${web.url("/sys/dic/parentList?id="+entity.id+"&categoryCode="+entity.categoryCode+"&created="+_create_)}',defaultIconCls: 'icon-badge',loadEmptyMessage: '无'}"/>
                </td>
            </tr>
            <tr>
                <th>字典名称</th>
                <td>
                    <input class="form-control" id="name" name="name" value="${entity.name}" maxlength="100"
                           data-validate="required: [true,'请输入字典名称'],maxlength: 100" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>字典值</th>
                <td>
                    <textarea class="form-control" name="value" maxlength="300"
                              data-validate="required: [true,'请输入字典值'],maxlength: 300"
                              rows="2" style="resize: vertical">${entity.value}</textarea>
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
                    <textarea class="form-control" name="remark" maxlength="500" data-validate="maxlength: 500" rows="3"
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
