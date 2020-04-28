<#-- @ftlvariable name="entity" type="com.github.lvyanyang.sys.entity.SysModule" -->
<#-- @ftlvariable name="_create_" type="java.lang.String" -->
<#include "/_inc/_layout.ftlh">
<#assign url=xci.saveUrl("/sys/module/","createSave","updateSave")/>
<@header/>
<div class="jxpanel winpanel" data-fit="true">
    <form id="editform" class="jxform" method="post" action="${url}">
        <input name="id" value="${entity.id}" type="hidden">
        <table class="table jxtable-form">
            <tr>
                <th class="w-100px">上级模块</th>
                <td>
                    <input name="parentName" class='form-control jxcombotree' data-value="${entity.parentId}"
                           data-options="editable: false,showClear: false,hiddenField: 'parentId',panelHeight: '250px',
                                        treeOptions: {url: '${web.url("/sys/module/parentList?id="+entity.id+"&created="+_create_)}',defaultIconCls: 'icon-flag',loadEmptyMessage: '无'}"/>
                </td>
                <td colspan="3"></td>
            </tr>
            <tr>
                <th>模块名称</th>
                <td>
                    <input class="form-control" name="name" value="${entity.name}" maxlength="50"
                           data-validate="required: [true,'请输入模块名称'],maxlength: 50" autocomplete="off"/>
                </td>
                <th>模块编码</th>
                <td>
                    <input class="form-control" name="code" value="${entity.code}" maxlength="50"
                           data-validate="required: [true,'请输入模块编码'],maxlength: 50" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>类型</th>
                <td>
                    <div class="input-group">
                        <div class="icheck-inline">
                            <label>
                                <input class="jxcheck" name="web" <@xci.isChecked value=entity.web /> type="checkbox" value="1"
                                       data-star="false" data-unchecked-value="0"> Web
                            </label>
                            <label>
                                <input class="jxcheck" name="win" <@xci.isChecked value=entity.win /> type="checkbox" value="1"
                                       data-unchecked-value="0"> WinForm
                            </label>
                        </div>
                    </div>
                </td>
                <th>选项</th>
                <td>
                    <div class="input-group">
                        <div class="icheck-inline">
                            <label>
                                <input class="jxcheck" name="status" <@xci.isChecked value=entity.status /> type="checkbox" value="1"
                                       data-unchecked-value="0"> 启用
                            </label>
                            <label>
                                <input class="jxcheck" name="menu" <@xci.isChecked value=entity.menu /> type="checkbox" value="1"
                                       data-unchecked-value="0"> 菜单
                            </label>
                            <label>
                                <input class="jxcheck" name="expand" <@xci.isChecked value=entity.expand /> type="checkbox" value="1"
                                       data-unchecked-value="0"> 展开
                            </label>
                            <label>
                                <input class="jxcheck" name="publiced" <@xci.isChecked value=entity.publiced /> type="checkbox" value="1"
                                       data-unchecked-value="0"> 公开
                            </label>
                        </div>
                    </div>
                </td>
            </tr>

            <tr>
                <th>Web图标</th>
                <td>
                    <div class="input-group">
                        <span class="input-group-btn">
                            <button id="btnIconCls" class="btn btn-default color-green" type="button">
                                <#if web.notBlank(entity.webCls)>
                                    <i class="fa-lg ${entity.webCls}"></i>
                                <#else>
                                    <i class="fa-lg icon-flag"></i>
                                </#if>
                            </button>
                        </span>
                        <input class="form-control" id="webCls" name="webCls" value="${entity.webCls}" style="height: auto;"
                               data-validate="maxlength: 90" maxlength="90" autocomplete="off" spellcheck="false"/>
                        <span class="input-group-btn">
                            <button id="selectIconCls" class="btn btn-default" type="button">
                                选择图标
                            </button>
                        </span>
                    </div>
                </td>
                <th>Win图标</th>
                <td>
                    <input class="form-control" id="winCls" name="winCls" value="${entity.winCls}" maxlength="90" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>Web地址</th>
                <td>
                    <textarea class="form-control" name="webUrl" maxlength="500" data-validate="maxlength: 500" rows="3"
                              style="resize: none">${entity.webUrl}</textarea>
                </td>
                <th>Win路径</th>
                <td>
                    <textarea class="form-control" name="winUrl" maxlength="500" data-validate="maxlength: 500" rows="3"
                              style="resize: none">${entity.winUrl}</textarea>
                </td>
            </tr>
            <tr>
                <th>Web配置</th>
                <td>
                    <textarea class="form-control" name="webSetting" maxlength="1000" data-validate="maxlength: 1000"
                              rows="3" style="resize: vertical">${entity.webSetting}</textarea>
                </td>
                <th>Win配置</th>
                <td>
                    <textarea class="form-control" name="winSetting" maxlength="1000" data-validate="maxlength: 1000"
                              rows="3" style="resize: vertical">${entity.winSetting}</textarea>
                </td>
            </tr>
            <tr>
                <th>模块参数</th>
                <td>
                    <textarea class="form-control" name="param" maxlength="3000" data-validate="maxlength: 3000"
                              rows="3" style="resize: vertical">${entity.param}</textarea>
                </td>
                <th>备注</th>
                <td>
                    <textarea class="form-control" name="remark" maxlength="1000" data-validate="maxlength: 1000"
                              rows="3"  style="resize: vertical">${entity.remark}</textarea>
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
        $('#selectIconCls').click(function () {
            jx.auth.iconDialog({
                inputId: 'webCls',
                callback: function (val) {
                    if (val) {
                        $('#btnIconCls').empty().append('<i class="fa-lg ' + val + '"></i>');
                    }
                }
            });
        });
        jx.auth.bindEditFormSuccess();
    </script>
</@footer>