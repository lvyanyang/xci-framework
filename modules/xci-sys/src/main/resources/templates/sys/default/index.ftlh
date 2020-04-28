<#-- @ftlvariable name="currentUser" type="com.github.lvyanyang.sys.entity.SysUser" -->
<#-- @ftlvariable name="systemTitle" type="java.lang.String" -->
<#-- @ftlvariable name="systemTitleColor" type="java.lang.String" -->
<#-- @ftlvariable name="systemCopyright" type="java.lang.String" -->
<#-- @ftlvariable name="systemTitleVersion" type="java.lang.String" -->
<#-- @ftlvariable name="systemTitleVersionColor" type="java.lang.String" -->
<#-- @ftlvariable name="systemEnableOnlineUserRefresh" type="java.lang.String" -->
<#-- @ftlvariable name="systemEnableMessageRefresh" type="java.lang.String" -->
<#-- @ftlvariable name="systemEnableTabPage" type="java.lang.String" -->
<#include "/_inc/_layout.ftlh">
<@header title=systemTitle bodyCls="jxlayout">
    <link href="${web.url('/sys/css/main.css')}" rel="stylesheet"/>
</@header>
<div class="north-panel" data-options="region:'north'">
    <div class="logo">
        <img src="${web.cdn('/img/logo.svg')}">
    </div>
    <div class="title font-kai" style="color: ${systemTitleColor};">
        ${systemTitle}
        <div class="subtitle" style="color: ${systemTitleVersionColor};">
            ${systemTitleVersion}
        </div>
    </div>
    <div class="nav">
        <ul>
            <li>
                <a id="nav_home" title="我的主页">
                    <i class="icon-home"></i><br>我的主页
                </a>
            </li>
<#--            <li>-->
<#--                <span id="unMessageCountBadge" class="badge" style="background-color: red;margin-left: -15px;"></span>-->
<#--                <a id="user_message" title="我的消息" class="jxpage" href="/sys/message?receiveUserId=${currentUser.id}">-->
<#--                    <i class="icon-bubbles"></i><br>我的消息-->
<#--                </a>-->
<#--            </li>-->
<#--            <li>-->
<#--                <a id="sys_feedback" title="我要反馈" class="jxdialog"-->
<#--                   data-options="width: '75%',height: '80%',shadeClose: true,maxmin: true,title: '我要反馈',url: '/sys/feedback/feedback'">-->
<#--                    <i class="icon-note"></i><br>我要反馈-->
<#--                </a>-->
<#--            </li>-->
            <li>
                <a title="修改密码" class="jxdialog"
                   data-options="width: '30%',height: '280px',shadeClose: true,title: '修改密码',url: '${web.url('/sys/user/modifyPassword')}'">
                    <i class="icon-key"></i><br>修改密码
                </a>
            </li>
<#--            <li>-->
<#--                <a title="关于" class="jxdialog"-->
<#--                   data-options="width: '60%',height: '100%',closeBtn:0,shadeClose:true,title: false,url: '/sys/about'">-->
<#--                    <i class="icon-bulb"></i><br>关于-->
<#--                </a>-->
<#--            </li>-->
            <li>
                <a id="nav_fullscreen" title="浏览器全屏">
                    <i class="icon-size-fullscreen"></i><br><span>全屏</span>
                </a>
            </li>
            <li>
                <a id="nav_logout" title="安全退出" data-url="/sys/logout">
                    <i class="icon-login"></i><br>安全退出
                </a>
            </li>
        </ul>
    </div>
</div>
<div class="west-panel" data-options="region:'west',split:true" title="导航菜单">
    <div class="m-5">
        <input class="form-control input-sm jxtree-filter" placeholder="请输入关键字模糊查询">
    </div>
    <ul class="jxtree" data-url="${web.url('/sys/userModules')}"></ul>
</div>
<div class="center-panel" data-options="region:'center'" data-url="/sys/home" data-title="我的主页">
    <div id="tabs"></div>
</div>
<div class="south-panel" data-options="region:'south'">
    <div class="left">
        欢迎您：
        <span id="user_name">${currentUser.name}(${currentUser.account}) - ${currentUser.deptName}</span>
        <span id="current_time"></span>
    </div>
    <div class="right">
        ${systemCopyright}
    </div>
</div>
<div id="closeMenu" style="width:150px;">
    <div id="refresh">刷新</div>
    <div class="menu-sep"></div>
    <div id="close">关闭</div>
    <div id="closeall">全部关闭</div>
    <div id="closeother">除此之外全部关闭</div>
    <div class="menu-sep"></div>
    <div id="closeright">关闭右侧标签</div>
    <div id="closeleft">关闭左侧标签</div>
    <div class="menu-sep"></div>
    <div id="fullPage">全屏</div>
</div>
<@footer>
<script>
    <#--window.systemEnableTabPage = '${systemEnableTabPage}';-->
    <#--window.systemEnableOnlineUserRefresh = '${systemEnableOnlineUserRefresh}';-->
    <#--window.systemEnableMessageRefresh = '${systemEnableMessageRefresh}';-->
    window.systemEnableTabPage = '1';
    window.systemEnableOnlineUserRefresh = '1';
    window.systemEnableMessageRefresh = '0';
</script>
<script src="${web.url('/sys/js/main.js')}"></script>
</@footer>