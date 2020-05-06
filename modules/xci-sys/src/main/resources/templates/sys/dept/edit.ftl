<#-- @ftlvariable name="_create_" type="java.lang.String" -->
<#-- @ftlvariable name="entity" type="com.github.lvyanyang.sys.entity.SysDept" -->
<#-- @ftlvariable name="managerUserList" type="java.util.List<com.github.lvyanyang.sys.entity.SysUser>" -->
<#-- @ftlvariable name="parentName" type="java.lang.String" -->
<#include "/_inc/_layout.ftl">
<#assign url=saveUrl("/sys/dept/","createSave","updateSave")/>
<@header/>
<div class="jxpanel winpanel" data-fit="true">
    <form id="editform" class="jxform" method="post" action="${url}">
        <input name="id" value="${entity.id}" type="hidden">
        <table class="table jxtable-form">
            <tr>
                <th>上级机构</th>
                <td>
                    <input name="deptName" class='form-control jxcombotree'
                           data-value="${entity.parentId}"
                            <#if (_create_?? && _create_ == "1") || entity.parentId!="0">
                                data-validate="required: [true,'请选择上级机构']"
                            </#if>
                           data-options="editable: false,showClear: false,hiddenField: 'parentId',panelHeight: '250px',
                                        treeOptions: {url: '${web.url("/sys/dept/parentList?id="+entity.id+"&created="+_create_)}',defaultIconCls: 'icon-users',loadEmptyMessage: '无'}"/>
                </td>
            </tr>
            <tr>
                <th>部门名称</th>
                <td>
                    <input class="form-control" name="name" value="${entity.name}" maxlength="50"
                           data-validate="required: [true,'请输入部门名称']" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>部门编码</th>
                <td>
                    <input class="form-control" name="code" value="${entity.code}" maxlength="50"
                           data-validate="required: [true,'请输入部门编码']" autocomplete="off"/>
                </td>
            </tr>

            <tr>
                <th>负责人</th>
                <td>
                    <select class="form-control jxselect" name="leaderId"
                            data-validate="required: [true,'请选择负责人']"
                            <#--data-text-field="leaderName"-->
                            data-allow-clear="true">
                        <option></option>
                        ${sys.selectOptions(managerUserList,entity.leaderId)}
                    </select>
                </td>
            </tr>
            <tr>
                <th>机构类型</th>
                <td>
                    <#--<@dic.systemDeptCategory selectedValue=entity.category nameField="category"/>-->
                    <select class="form-control jxselect" name="category"
                            <#--data-validate="required: [true,'请选择机构类型']"-->
                            data-allow-clear="true">
                        <option></option>
                        ${sys.dicSysDeptCategory(entity.category)}
                    </select>
                </td>
            </tr>
            <tr>
                <th>机构性质</th>
                <td>
                    <#--<@dic.systemDeptNatureTree selectedValue=entity.nature nameField="nature"/>-->
                    <select class="form-control jxselect" name="nature"
                            <#--data-validate="required: [true,'请选择机构性质']"-->
                            data-allow-clear="true">
                        <option></option>
                        ${sys.dicSysDeptNature(entity.nature)}
                    </select>
                </td>
            </tr>
            <tr>
                <th>状态</th>
                <td>
                    <label>
                        <input class="jxcheck" name="status" ${web.isChecked(entity.status)}  type="checkbox" value="1"
                               data-unchecked-value="0"/> 启用
                    </label>
                </td>
            </tr>
            <tr>
                <th>备注</th>
                <td>
                    <textarea class="form-control" name="remark" maxlength="400" data-validate="maxlength: 400" rows="3"
                              style="resize: none">${entity.remark}</textarea>
                </td>
            </tr>
        </table>
    </form>
    <div class="panel-footer text-right">
        <@editPanelFooter/>
    </div>
</div>
<@footer>
    <script>jx.auth.bindEditFormSuccess()</script>
</@footer>