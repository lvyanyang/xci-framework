<#-- @ftlvariable name="entity" type="com.xci.sys.entity.SysModule" -->
<#-- @ftlvariable name="parentName" type="java.lang.String" -->
<#include "/sys/_inc/_layout.ftlh">
<#assign url=xci.saveUrl("/sys/module/","createSave","updateSave")/>
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
                    <input class="form-control" name="name" value="${entity.name}" maxlength="50"
                           data-validate="required: [true,'请输入模块名称'],maxlength: 50" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>编码</th>
                <td>
                    <input class="form-control" name="code" value="${entity.code}" maxlength="50"
                           data-validate="required: [true,'请输入模块编码'],maxlength: 50" autocomplete="off"/>
                </td>
            </tr>

            <tr>
                <th>图标样式</th>
                <td>
                    <div class="input-group">
                        <span class="input-group-btn">
                            <button id="btnIconCls" class="btn btn-default color-green" type="button">
                                <#if helper.isNotBlank(entity.webCls)>
                                    <i class="fa-lg ${entity.webCls}"></i>
                                <#else>
                                    <i class="fa-lg icon-flag"></i>
                                </#if>
                            </button>
                        </span>
                        <input class="form-control" id="webCls" name="webCls" value="${entity.webCls!}" style="height: auto;"
                               data-validate="maxlength: 90" maxlength="90" autocomplete="off" spellcheck="false"/>
                        <span class="input-group-btn">
                            <button id="selectIconCls" class="btn btn-default" type="button">
                                选择图标
                            </button>
                        </span>
                    </div>
                </td>
            </tr>
            <tr>
                <th>地址</th>
                <td>
                    <textarea class="form-control" name="webUrl" maxlength="200" data-validate="maxlength: 200" rows="2"
                              style="resize: none">${entity.webUrl}</textarea>
                </td>
            </tr>
            <tr>
                <th>窗口类</th>
                <td>
                    <textarea class="form-control" name="winProvider" maxlength="300" data-validate="maxlength: 300" rows="2"
                              style="resize: none">${entity.winProvider}</textarea>
                </td>
            </tr>
            <tr>
                <th>选项</th>
                <td>
                    <div class="input-group">
                        <div class="icheck-inline">
                            <label>
                                <input class="jxcheck" name="status" <@xci.isChecked value=entity.status /> type="checkbox" value="1"
                                       data-unchecked-value="0"> 启用
                            </label>
                            <label>
                                <input class="jxcheck" name="isPublic" <@xci.isChecked value=entity.isPublic /> type="checkbox" value="1"
                                       data-unchecked-value="0"> 公开
                            </label>
                            <label>
                                <input class="jxcheck" name="isExpand" <@xci.isChecked value=entity.isExpand /> type="checkbox" value="1"
                                       data-unchecked-value="0"> 展开
                            </label>
                            <label>
                                <input class="jxcheck" name="isMenu" <@xci.isChecked value=entity.isMenu /> type="checkbox" value="1"
                                       data-unchecked-value="0"> 菜单
                            </label>


                            <label>
                                <input class="jxcheck" name="isWeb" <@xci.isChecked value=entity.isWeb /> type="checkbox" value="1"
                                       data-unchecked-value="0"> Web
                            </label>
                            <label>
                                <input class="jxcheck" name="isWin" <@xci.isChecked value=entity.isWin /> type="checkbox" value="1"
                                       data-unchecked-value="0"> WinForm
                            </label>
                            <label>
                                <input class="jxcheck" name="winIsDialog" <@xci.isChecked value=entity.winIsDialog /> type="checkbox" value="1"
                                       data-unchecked-value="0"> 对话框
                            </label>
                            <label>
                                <input class="jxcheck" name="WinIsPreloading" <@xci.isChecked value=entity.WinIsPreloading /> type="checkbox" value="1"
                                       data-unchecked-value="0"> 预加载
                            </label>
                        </div>
                    </div>
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