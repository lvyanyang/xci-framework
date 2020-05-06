<#-- @ftlvariable name="url" type="java.lang.String" -->
<#import "/spring.ftl" as spring>
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
			<h1>404</h1>
		</span>
        <p class="m-error_desc">
            抱歉，您要访问的页面没有找到
            <br>
            ${url}
        </p>
    </div>
</div>
</body>
</html>