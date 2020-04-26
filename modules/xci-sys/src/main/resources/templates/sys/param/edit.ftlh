<#-- @ftlvariable name="entity" type="com.xci.sys.entity.SysParam" -->
<#include "/_inc/_layout.ftlh">
<#assign url=xci.saveUrl("/sys/param/","createSave","updateSave")/>
<@header/>
<div class="jxpanel winpanel" data-fit="true">
    <form id="editform" class="jxform" method="post" action="${url}">
        <input name="id" value="${entity.id}" type="hidden">
        <table class="table jxtable-form">
            <tr>
                <th class="w-100px">参数名称</th>
                <td>
                    <input class="form-control" name="name" value="${entity.name}" maxlength="50"
                           data-validate="required: [true,'请输入参数名称'],maxlength: 50" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>参数编码</th>
                <td>
                    <input class="form-control" name="code" value="${entity.code}" maxlength="50"
                           data-validate="required: [true,'请输入参数编码'],maxlength: 50" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>参数分类</th>
                <td>
                    <select class="form-control jxselect" name="category"
                            data-validate="required: [true,'请选择参数分类']" data-allow-clear="true">
                        <option></option>
                        <@dic.sysParamCategory selectedValue=entity.category/>
                    </select>
                </td>
            </tr>
            <tr>
                <th>参数值</th>
                <td>
                    <textarea class="form-control" name="value" maxlength="800" data-validate="maxlength: 800" rows="3"
                              style="resize: none">${entity.value}</textarea>
                </td>
            </tr>
            <tr>
                <th>备注</th>
                <td>
                    <textarea class="form-control" name="remark" maxlength="1000" rows="4"
                              data-validate="maxlength: 1000" style="resize: none">${entity.remark}</textarea>
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