<#-- @ftlvariable name="dicCode" type="java.lang.String" -->
<#assign allowItemCreate=helper.isAuthorize("SystemDicDetails.Create")/>
<#assign allowItemEdit=helper.isAuthorize("SystemDicDetails.Edit")/>
<#assign allowItemDelete=helper.isAuthorize("SystemDicDetails.Delete")/>
<#assign allowItemExport=helper.isAuthorize("SystemDicDetails.Export")/>
<#include "/sys/_inc/_layout.ftlh" >
<@header/>
<#include "/sys/dicdetails/_menu.ftlh" >
<#include "/sys/dicdetails/_toolbar.ftlh" >
<#include "/sys/dicdetails/_grid.ftlh" >
<@footer>
    <script>
        window.dicCode = '${dicCode!}';
    </script>
    <script src="<@xci.url '/sys/js/dicsingle.js'/>"></script>
</@footer>