<#-- @ftlvariable name="exception" type="java.lang.Exception" -->
<#-- @ftlvariable name="details" type="java.lang.String" -->
<#-- @ftlvariable name="params" type="java.lang.String" -->
<#-- @ftlvariable name="url" type="java.lang.String" -->
<#-- @ftlvariable name="msg" type="java.lang.String" -->
<!DOCTYPE html>
<html lang="zh">
<head>
    <title></title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit"/>
    <meta name="force-rendering" content="webkit"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link href="${web.cdn('/jx.css')}" rel="stylesheet"/>
</head>
<body>
<div class="jxpanel jx-overflow-no winpanel" data-options="fit:true">
    <table id="basic" class="table table-bordered jxtable-details">
        <tr>
            <td colspan="2">
                <h3 style="color: red;margin-top: 5px;">HTTP Status 500 - ${msg}</h3>
            </td>
        </tr>
        <tr>
            <th>消息</th>
            <td>${msg!}</td>
        </tr>
        <tr>
            <th class="w-100px">请求路径</th>
            <td>${url!}</td>
        </tr>
        <tr>
            <th>参数</th>
            <td>${params!}</td>
        </tr>
        <tr>
            <td colspan="2">
                <pre>${details!}</pre>
            </td>
        </tr>
    </table>
</div>
</body>
</html>