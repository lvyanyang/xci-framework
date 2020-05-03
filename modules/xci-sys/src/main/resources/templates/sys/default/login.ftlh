<#-- @ftlvariable name="title" type="java.lang.String" -->
<#-- @ftlvariable name="titleColor" type="java.lang.String" -->
<#-- @ftlvariable name="copyright" type="java.lang.String" -->
<#include "/_inc/_layout.ftlh">
<@header title=systemTitle+" 系统登录">
    <link href="${web.url('/sys/css/login.css')}" rel="stylesheet"/>
</@header>
<div class="container">
    <form id="login_form" method="post" class="form-horizontal col-md-6 col-md-offset-3">
        <div class="form-group">
            <img src="${web.cdn('/img/logo.svg')}" width="106" height="128"/>
        </div>
        <div class="form-group title font-kai" style="color: ${titleColor};">
            ${title}
        </div>
        <div class="form-group">
            <div class="col-md-8 col-md-offset-2 has-feedback">
                <input id="account" name="account" class="form-control input-lg"
                       placeholder="账号" data-msg="请输入账号" autofocus autocomplete="off"/>
                <i class="form-control-feedback icon-user" style="font-size: 2rem"></i>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-8 col-md-offset-2 has-feedback">
                <input id="password" name="password" class="form-control input-lg" type="password"
                       placeholder="密码" data-msg="请输入密码" autocomplete="off"/>
                <i class="form-control-feedback box-icon icon-lock" style="font-size: 2rem"></i>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-8 col-md-offset-2 has-feedback">
                <div class="checkbox-container">
                    <label>
                        <input id="auto_login" name="auto_login" class="checkbox-check" type="checkbox" value="true">
                        <span></span>
                        <span>下次自动登录</span>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-8 col-md-offset-2">
                <input id="btn_login" name="btn_login" class="btn btn-default btn-block btn-lg"
                       type="button" value="登 录"/>
            </div>
        </div>
    </form>
</div>
<div id="captcha-container" style="display: none;">
    <div style="width: 310px;margin: 5px auto;">
        <div id="captcha"></div>
    </div>
</div>
<div class="footer">
    ${copyright}
</div>
<@footer>
    <script src="${web.cdn('/lib/jigsaw/jigsaw.js')}"></script>
    <script src="${web.url('/sys/js/login.js')}"></script>
</@footer>