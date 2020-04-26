<#-- @ftlvariable name="entity" type="com.xci.platform.auth.entity.DocEntity" -->
<#include "/_inc/_layout.ftl">
<#assign url=html.saveUrls("/auth/doc/create-save","/auth/doc/edit-save")/>
<@header/>
<div class="jxpanel winpanel" data-fit="true">
    <form id="editform" class="jxform" method="post" action="${url}">
        <input name="id" value="${entity.id!}" type="hidden">
        <input id="docFormat" name="docFormat" value="${entity.docFormat!}" type="hidden">
        <textarea id="docContent" name="docContent" style="display: none;">${entity.docContent!}</textarea>
        <table class="table jxtable-form" style="width:96%;">
            <tr>
                <th class="w-80px">标题</th>
                <td colspan="3">
                    <input id="title" name="title" class="form-control"
                           value="${entity.title!}" maxlength="300"
                           data-validate="required: [true,'请输入标题'],maxlength: 300"
                           autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>分类</th>
                <td>
                    <input id="categoryName"
                           name="categoryName"
                           class='form-control jxcombotree'
                           data-value="${entity.categoryId!}"
                           data-options="
                                editable: false, showClear: false, hiddenField: 'categoryId',panelHeight: '250px',
                                treeOptions: {
                                url: '<@html.url "/auth/dicdetails/queryTreeByDicCode/DicSystemDocCategory"/>',
                                defaultIconCls: 'icon-badge'
                                }">
                </td>
                <th>标签</th>
                <td>
                    <input id="tag" name="tag" class="form-control" value="${entity.tag!}" maxlength="200" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <th>状态</th>
                <td>
                    <label>
                        <input class="jxcheck" name="status" data-color="green" <@html.isChecked value=entity.status /> type="checkbox"
                               value="1"
                               data-unchecked-value="0"/> 发布
                    </label>
                </td>
            </tr>
            <tr>
                <th></th>
                <td colspan="3" style="padding-top: 0">
                    <div class="jxtabs jxtabs-line">
                        <ul class="nav nav-tabs">
                            <li>
                                <a href="#tab-content" data-toggle="tab" data-format="1">HTML</a>
                            </li>
                            <li>
                                <a href="#tab-content" data-toggle="tab" data-format="2">Markdown</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-content" class="tab-pane jx-overflow-auto fade in active">

                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </form>
    <div class="panel-footer text-right">
        <@html.editPanelFooter/>
    </div>
</div>
<@footer>
    <script src="<@html.url '/lib/showdown.js'/>"></script>
    <script src="<@html.url '/lib/turndown.js'/>"></script>
    <script src="<@html.url '/auth/js/util/switch-html-md-editor.js'/>"></script>
    <script>
        $('form').on('beforesubmit', function () {
            $('#docFormat').val(jx.switchHtmlMarkdownEditor.getFormat());
            $('#docContent').val(jx.switchHtmlMarkdownEditor.getContent());
        });
        jx.ready('kindeditor,editormd', function () {
            var htmlOps = {
                height: 440,
                uploadJson: '<@html.kindeditorUploadUrl/>',
                fileManagerJson: '<@html.kindeditorFileManagerUrl/>'
            };
            var mdOps = {
                height: 440,
                imageUploadURL: '<@html.mdEditorImageUploadURL/>'
            };
            jx.auth.bindEditFormSuccess();
            jx.switchHtmlMarkdownEditor.init($('.jxtabs'), $('#docContent'), '${entity.docFormat}', htmlOps, mdOps);
        });
    </script>
</@footer>