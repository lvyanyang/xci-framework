<#-- @ftlvariable name="code" type="java.lang.String" -->
<#-- @ftlvariable name="url" type="java.lang.String" -->
<!DOCTYPE html>
<html lang="zh">
<head>
    <title></title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit"/>
    <meta name="force-rendering" content="webkit"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link href="${web.url('/sys/css/error.css')}" rel="stylesheet"/>
</head>
<body style="background-image: url(${web.cdn("/bg/bg404.png")}">
<div class="m-error-1">
    <div class="m-error_container">
		<span class="m-error_number">
			<h1>未授权</h1>
		</span>
        <p class="m-error_desc">
            抱歉，您没有查看该页面的权限，如需访问请联系管理员
            <br>
            权限编码: ${web.requstParameter("code")} <br>
            访问路径: ${web.requstParameter("url")} <br>
        </p>
    </div>
</div>
</body>
</html>