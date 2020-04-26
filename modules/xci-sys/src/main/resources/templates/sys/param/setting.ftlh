<#include "/_inc/_layout.ftlh">
<@header/>
<div class="jxpanel">
    <div class="jxform-wrap" style="width: 60%;">
        <div class="jxform-container">
            <form id="editform" class="jxform" method="post" action="<@xci.url "/auth/param/setting-save"/>">
                <div class="jxtabs jxtabs-line">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab-system" data-toggle="tab">系统设置</a></li>
                        <li><a href="#tab-upload" data-toggle="tab">上传设置</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-system" class="tab-pane jx-overflow-auto fade in active">
                            <table class="table jxtable-form" style="width: 97%;margin-top: 0;">
                                <tr>
                                    <th class="w-100px">系统标题</th>
                                    <td>
                                        <input class="form-control" name="SystemTitle" value="${SystemTitle!}" maxlength="100"
                                               data-validate="required: [true,'请输入系统标题'],maxlength: 100" autocomplete="off"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>标题颜色</th>
                                    <td>
                                        <input class="form-control" name="SystemTitleColor" value="${SystemTitleColor!}" maxlength="100"
                                               data-validate="required: [true,'请输入标题颜色'],maxlength: 100" autocomplete="off"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>系统版本</th>
                                    <td>
                                        <input class="form-control" name="SystemTitleVersion" value="${SystemTitleVersion!}" maxlength="100"
                                               data-validate="required: [true,'请输入系统版本'],maxlength: 100" autocomplete="off"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>版本颜色</th>
                                    <td><input class="form-control" name="SystemTitleVersionColor" value="${SystemTitleVersionColor!}"
                                               maxlength="100"
                                               data-validate="required: [true,'请输入版本颜色'],maxlength: 100" autocomplete="off"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>每页记录数</th>
                                    <td>
                                        <select class="form-control jxselect" name="SystemGridPageSize" data-allow-clear="false">
                                            <option value="10" <@xci.isSelected value=SystemGridPageSize=='10' />>10</option>
                                            <option value="15" <@xci.isSelected value=SystemGridPageSize=='15' />>15</option>
                                            <option value="20" <@xci.isSelected value=SystemGridPageSize=='20' />>20</option>
                                            <option value="25" <@xci.isSelected value=SystemGridPageSize=='25' />>25</option>
                                            <option value="30" <@xci.isSelected value=SystemGridPageSize=='30' />>30</option>
                                            <option value="50" <@xci.isSelected value=SystemGridPageSize=='50' />>50</option>
                                            <option value="80" <@xci.isSelected value=SystemGridPageSize=='80' />>80</option>
                                            <option value="100" <@xci.isSelected value=SystemGridPageSize=='100' />>100</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th>页面主题</th>
                                    <td><input class="form-control" name="SystemTheme" value="${SystemTheme!}" maxlength="100"
                                               autocomplete="off"/></td>
                                </tr>
                                <tr>
                                    <th>版权信息</th>
                                    <td>
                                        <input class="form-control" name="SystemCopyright" value="${SystemCopyright!}" maxlength="100"
                                               autocomplete="off"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="padding-left: 40px;">
                                        <label class="ml-5px">
                                            <input class="jxcheck"
                                                   name="SystemEnableTabPage" <@xci.isChecked value=SystemEnableTabPage=='1' />
                                                   type="checkbox" value="1"
                                                   data-unchecked-value="0"/> 多标签页导航
                                        </label>
                                        <label class="ml-5px">
                                            <input class="jxcheck"
                                                   name="SystemLargeFontMode" <@xci.isChecked value=SystemLargeFontMode=='1' />
                                                   type="checkbox" value="1"
                                                   data-unchecked-value="0"/> 大字体模式
                                        </label>

                                        <label class="ml-5px">
                                            <input class="jxcheck"
                                                   name="SystemEnableMessageRefresh" <@xci.isChecked value=SystemEnableMessageRefresh=='1' />
                                                   type="checkbox" value="1"
                                                   data-unchecked-value="0"/> 启用消息刷新
                                        </label>

                                        <label class="ml-5px">
                                            <input class="jxcheck"
                                                   name="SystemEnableOnlineUserRefresh" <@xci.isChecked value=SystemEnableOnlineUserRefresh=='1' />
                                                   type="checkbox" value="1"
                                                   data-unchecked-value="0"/> 启用在线用户
                                        </label>
                                        <label>
                                            <input class="jxcheck"
                                                   name="SystemAllowRepeatLogin" <@xci.isChecked value=SystemAllowRepeatLogin=='1' />
                                                   type="checkbox" value="1"
                                                   data-unchecked-value="0"/> 允许重复登录
                                        </label>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div id="tab-upload" class="tab-pane jx-overflow-auto fade">

                        </div>
                    </div>
                </div>

                <div class="jxform-footer" style="padding-left: 30px;">
                    <button id="btnSave" class="btn btn-primary" type="button">
                        <i class="fa fa-save"></i> 保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<@footer>
    <script>
        $('#btnSave').click(function () {
            $('#editform').jxform().submit();
        });
        $('#editform').onFormSuccess(function (e, result) {
            jx.toastr.success('保存成功');
        });
    </script>
</@footer>