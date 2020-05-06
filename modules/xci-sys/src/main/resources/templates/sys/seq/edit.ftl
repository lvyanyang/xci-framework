<#-- @ftlvariable name="entity" type="com.github.lvyanyang.sys.entity.SysSeq" -->
<#include "/_inc/_layout.ftl">
<#assign url=saveUrl("/sys/seq/","createSave","updateSave")/>
<@header/>
<div class="jxpanel winpanel" data-fit="true">
    <form id="editform" class="jxform" method="post" action="${url}">
        <input name="id" value="${entity.id}" type="hidden">
        <table class="table jxtable-form">
            <tr>
                <th class="w-100px">名称</th>
                <td>
                    <input class="form-control" name="name" value="${entity.name}" maxlength="100" data-options="{alwaysShow:true}" data-validate="required: [true,'请输入序列名称'],maxlength: 100" autocomplete="off" />
                </td>
            </tr>
            <tr>
                <th>编码</th>
                <td>
                    <input class="form-control" name="code" value="${entity.code}" maxlength="100" data-options="{alwaysShow:true}" data-validate="required: [true,'请输入序列编码'],maxlength: 100" autocomplete="off" />
                </td>
            </tr>
            <tr>
                <th>开始于</th>
                <td>
                    <input class="form-control jxnumber" name="startWith" type="number" data-validate="required: [true,'请输入序列开始值']"
                           max="999999999" data-max="999999999" data-min="0" data-decimals="0" data-step="1" value="${entity.startWith}" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>增量</th>
                <td>
                    <input class="form-control jxnumber" name="incrementBy" type="number" data-validate="required: [true,'请输入序列增量']"
                           max="999999" data-max="999999" data-min="1" data-decimals="0" data-step="1" value="${entity.incrementBy}" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>备注</th>
                <td>
                    <textarea class="form-control" name="remark" maxlength="400" rows="2" data-validate="maxlength: 400" style="resize: none">${entity.remark}</textarea>
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