<#-- @ftlvariable name="categoryCode" type="java.lang.String" -->
<#include "/_inc/_layout.ftl">
<@header/>
<#include "/sys/dic/_inc/_menu.ftl" >
<#include "/sys/dic/_inc/_toolbar.ftl" >
<#include "/sys/dic/_inc/_grid.ftl" >
<@footer>
    <script src="${web.url('/sys/js/dicsingle.js')}" data-category-code="${categoryCode}"></script>
</@footer>
