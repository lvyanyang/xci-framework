<#-- @ftlvariable name="role" type="com.github.lvyanyang.sys.entity.SysRole" -->
<#-- @ftlvariable name="roleIds" type="java.lang.String" -->
<#include "/_inc/_layout.ftl">
<@header>
    <link href="${web.url('/sys/css/tree-noselect.css')}" rel="stylesheet"/>
</@header>
<#assign save_url=web.url("/sys/role/authorizeSave")>
<#assign module_url=web.url("/sys/role/authorizeModules?roleId=${role.id}")>
<#assign dept_url=web.url("/sys/role/authorizeDepts?roleId=${role.id}")>

<div class="jxpanel jx-overflow-no winpanel" data-fit="true">
    <form id="editform" class="jxform" method="post" action="${save_url}">
        <input id="roleIds" name="roleIds" type="hidden" value="${roleIds}"/>
        <input id="moduleIds" name="moduleIds" type="hidden"/>
        <input id="customDeptIds" name="customDeptIds" type="hidden"/>

        <div id="wizard" class="wizard" data-target="#wizard-steps" style="border-left: none; border-top: none; border-right: none;">
            <ul class="steps">
                <li data-target="#step-1" class="active"><span class="step">1</span>功能权限<span class="chevron"></span></li>
                <li data-target="#step-2"><span class="step">2</span>机构权限<span class="chevron"></span></li>
            </ul>
        </div>

        <div id="wizard-steps" class="step-content" style="border-left: none; border-bottom: none; border-right: none;">
            <div id="step-1" class="step-pane jx-overflow-auto active">
                <ul id="module-tree" class="jxtree margin-10" data-url="${module_url}"
                    data-load-empty-message="无功能模块数据"></ul>
            </div>
            <div id="step-2" class="step-pane jx-overflow-no">
                <div class="margin-10">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <div class="input-group">
                                <div class="icheck-inline">
                                    <label><input name="deptScope" class="jxcheck" type="radio" data-color="green" value="1" ${web.isChecked(role.deptScope==1)}/>全部</label>
                                    <label><input name="deptScope" class="jxcheck" type="radio" data-color="green" value="2" ${web.isChecked(role.deptScope==2)}/>自定义</label>
                                    <label><input name="deptScope" class="jxcheck" type="radio" data-color="green" value="3" ${web.isChecked(role.deptScope==3)}/>所在机构及所有下级</label>
                                    <label><input name="deptScope" class="jxcheck" type="radio" data-color="green" value="4" ${web.isChecked(role.deptScope==4)}/>所在机构</label>
                                    <label><input name="deptScope" class="jxcheck" type="radio" data-color="green" value="5" ${web.isChecked(role.deptScope==5)}/>仅本人</label>
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item" style="padding-left: 0;">
                            <ul id="dept-tree" class="jxtree" data-url="${dept_url}"
                                data-load-empty-message="无部门数据"
                                style="overflow:auto;margin: 0;"></ul>
                            <div id="dept-tree-background"
                                 style="position:absolute;top:0;left:0;z-index: 2; width: 100%; background: #000; filter: alpha(opacity=10); opacity: 0.1;"></div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </form>
    <div class="panel-footer text-right">
        <div id="wizard-actions">
            <a id="btn_last" disabled class="btn btn-default btn-prev">上一步</a>
            <a id="btn_next" class="btn btn-default btn-next">下一步</a>
            <button id="btn_finish" type="button" disabled class="btn btn-primary" onclick="$('#editform').jxform().submit()">完成</button>
        </div>
    </div>
</div>
<@footer>
    <script src="${web.url('/sys/js/authorize.js')}"></script>
</@footer>
