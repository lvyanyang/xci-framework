<#ftl strip_whitespace=true>
<#-- @ftlvariable name="_create_" type="java.lang.String" -->

<#function url relativeUrl extra...>
    <#if extra?? && extra?size!=0>
        <#return "${springMacroRequestContext.getContextUrl(relativeUrl,extra)}">
    <#else>
        <#return "${springMacroRequestContext.getContextUrl(relativeUrl)}">
    </#if>
</#function>

<#function saveUrl base create="" edit="">
    <#if _create_?? && _create_ == "1">
        <#return "${springMacroRequestContext.getContextUrl(base+create)}">
    <#else>
        <#return "${springMacroRequestContext.getContextUrl(base+edit)}">
    </#if>
</#function>

<#--状态标签-->
<#macro boolAllLabel value allLable>
    <#if (value?is_number && value == 1) || (value?is_boolean && value == true)>
        <span class="label label-success">${allLable}</span>
    <#else>
        <span class="label label-danger">${allLable}</span>
    </#if>
</#macro>

<#macro boolLabel value trueLable = "启用" falseLable = "禁用">
    <#if (value?is_number && value == 1) || (value?is_boolean && value == true)>
        <span class="label label-success">${trueLable!}</span>
    <#else>
        <span class="label label-danger">${falseLable!}</span>
    </#if>
</#macro>

<#--状态标签-->
<#macro statusBoolLabel value trueLable = "启用" falseLable = "禁用">
    <#if (value?is_number && value == 1) || (value?is_boolean && value == true)>
        <span class="label label-success">${trueLable!}</span>
    <#else>
        <span class="label label-danger">${falseLable!}</span>
    </#if>
</#macro>

<#--是否标签-->
<#macro yesnoLabel value trueLable = "是" falseLable = "否">
    <@boolLabel value,trueLable,falseLable/>
</#macro>

<#--性别标签-->
<#macro sexBoolLabel value  male = "男" female = "女">
    <#if (value?is_number&& value ==0)>
        <span class="label label-primary">${male!}</span>
    <#else>
        <span class="label label-danger">${female!}</span>
    </#if>
</#macro>

<#--获取数据字典选项-->
<#macro systemDic code selectedValue="" isMultiple = false>
    <#assign simpleList = sys.dics(code)>
    <#list simpleList as item>
        <#if selectedValue??>
            <#if isMultiple == true>
                <#assign selected = selectedValue?contains(item.value)>
            <#else>
                <#assign selected = selectedValue == item.value>
            </#if>
        </#if>
        <option value="${item.value!}" data-keys="${item.spell!}" <@isSelected selected/>>${item.name!}</option>
    </#list>
</#macro>

<#--获取数据字典选项Text-->
<#macro systemDicText code selectedValue="" isMultiple = false>
    <#assign simpleList = sys.dics(code)>
    <#list simpleList as item>
        <#if selectedValue??>
            <#if isMultiple == true>
                <#assign selected = selectedValue?contains(item.name)>
            <#else>
                <#assign selected = selectedValue == item.name>
            </#if>
        </#if>
        <option value="${item.name!}" data-keys="${item.spell!}" <@isSelected selected/>>${item.name!}</option>
    </#list>
</#macro>


<#--获取下拉选项-->
<#macro options simpleList selectedValue="" isMultiple = false>
    <#list simpleList as item>
        <#if selectedValue??>
            <#if isMultiple == true>
                <#assign selected = selectedValue?contains(item.id)>
            <#else>
                <#assign selected = selectedValue == item.id>
            </#if>
        </#if>
        <option value="${item.id!}" data-keys="${item.spell!}" <@isSelected selected/>>${item.name!}</option>
    </#list>
</#macro>

<#--状态下拉选项-->
<#macro statusOptions emptyLable = "全部状态" enableLable = "启用" disableLable = "禁用" selectedValue="">
    <option value=" " <@isSelected value=selectedValue == " "/>>${emptyLable!}</option>
    <option value="1" <@isSelected value=selectedValue == "1"/>>${enableLable!}</option>
    <option value="0" <@isSelected value=selectedValue == "0"/>>${disableLable!}</option>
</#macro>

<#--状态下拉选项-->
<#macro statusEnableOptions emptyLable = "全部状态" enableLable = "启用" disableLable = "禁用">
    <option value=" ">${emptyLable!}</option>
    <option value="1" selected>${enableLable!}</option>
    <option value="0">${disableLable!}</option>
</#macro>

<#--是否新建记录-->
<#macro isCreate>
    <#if _create_?? && _create_ == "1">
        <#nested/>
    </#if>
</#macro>

<#--是否编辑记录-->
<#macro isEdit>
    <#if !_create_?? || _create_ == "0" || _create_ == "">
        <#nested/>
    </#if>
</#macro>

<#--输入警告标签-->
<#macro warningMessage test msg>
    <#if test>
        <div class="alert alert-warning margin-5 padding-10">${msg}</div>
    </#if>
</#macro>

<#--新建记录标记-->
<#macro createMark>
    <input type="hidden" name="_create_" value="${_create_}"/>
</#macro>

<#--条件输出-->
<#macro echo test>
    <#if test>
        <#nested/>
    </#if>
</#macro>

<#--格式化日期-->
<#macro formatDate date format="yyyy-MM-dd"><#if date??>${web.formatDate(date,format)}</#if></#macro>
<#--格式化日期-->
<#macro formatDateTime date format="yyyy-MM-dd HH:mm:ss"><#if date??>${web.formatDate(date,format)}</#if></#macro>

<#--引入UE脚本-->
<#macro ueScript>
<script src="<@url '/lib/ueditor/ueditor.config.js'/>"></script>
<script src="<@url '/lib/ueditor/ueditor.all.min.js'/>"></script>
</#macro>

<#--UE服务器路径-->
<#macro ueditorServerUrl>${web.siteFullUrl()}${request.getContextUrl("/ueditor/config")}</#macro>

<#--editormd图片上传服路径-->
<#macro mdEditorImageUploadURL>${web.siteFullUrl()}${request.getContextUrl("/editormd/upload")}</#macro>

<#--kindeditor文件上传服路径-->
<#macro kindeditorUploadUrl>${web.siteFullUrl()}${request.getContextUrl("/kindeditor/upload")}</#macro>

<#--kindeditor文件管理服路径-->
<#macro kindeditorFileManagerUrl>${web.siteFullUrl()}${request.getContextUrl("/kindeditor/manager")}</#macro>

<#--编辑表单工具栏，取消的单独处理-->
<#macro editPanelFooterSaveSave>
    <button class="btn btn-primary" type="button" onclick="jx.auth.editFormSave()">
        <i class="fa fa-save"></i> 保存
    </button>

</#macro>

<#--编辑表单工具栏-->
<#macro editPanelFooterSave>
    <button class="btn btn-primary" type="button" onclick="jx.auth.editFormSave()">
        <i class="fa fa-save"></i> 保存
    </button>
    <button class="btn btn-default" type="button" onclick="jx.auth.editFormCancel()">
        <i class="fa fa-sign-in"></i> 取消
    </button>
</#macro>
<#macro editPanelFooter>
    <@isCreate>
        <button class="btn btn-success" type="button" onclick="jx.auth.editFormContinuousSave()">
            <i class="icon-plus"></i> 连续新增
        </button>
    </@isCreate>
    <@editPanelFooterSave/>
</#macro>

<#macro editPanelPonyFooter>
    <@isCreate>
        <button class="btn btn-success" type="button" onclick="jx.auth.editFormContinuousSave()">
            <i class="icon-plus"></i> 保存并新增
        </button>
    </@isCreate>
    <@editPanelFooterSave/>
</#macro>

<#--url 转换-->
<#macro url relativeUrl extra...><#if extra?? && extra?size!=0>${springMacroRequestContext.getContextUrl(relativeUrl,extra)}<#else>${springMacroRequestContext.getContextUrl(relativeUrl)}</#if></#macro>

<#--条件输出标签-->
<#macro iif value trueMsg = "" falseMsg = ""><#if (value??) &&( (value?is_number && value == 1) || (value?is_boolean && value == true))>${trueMsg!}<#else>${falseMsg!}</#if></#macro>

<#--输出 readonly 字符串-->
<#macro isReadonly value><@iif value,'readonly',""/></#macro>

<#--输出 selected 字符串-->
<#macro isSelected value><@iif value,'selected',""/></#macro>

<#--输出 checked 字符串-->
<#macro isChecked value><@iif value,'checked',""/></#macro>

<#--输出 disabled 字符串-->
<#macro isDisabled value><@iif value,'disabled',""/></#macro>

<#--输出 disabled 字符串-->
<#macro isClsActive value><@iif value,'class="active"',""/></#macro>