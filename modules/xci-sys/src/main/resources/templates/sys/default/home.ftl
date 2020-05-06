<#include "/_inc/_layout.ftl">
<@header>
    <link href="${web.cdn('/portlet.css')}" rel="stylesheet"/>
    <link href="${web.url('/sys/css/home.css')}" rel="stylesheet"/>
</@header>
<div class="jxpanel">
    <div style="padding-top: 15px; ">
        <div id="col1" class="row column sortable">
            <div id="p1" class="portlet portlet-sortable light bordered" style="height:150px;">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="fa fa-calendar"></i> 当前时间
                    </div>
                    <div class="actions">
                        <a class="btn btn-circle btn-icon-only btn-default bcollapse" title="展开/合上">
                            <i class="fa fa-angle-down"></i>
                        </a>
                        <a class="btn btn-circle btn-icon-only btn-default breload" title="刷新">
                            <i class="icon-reload"></i>
                        </a>
                        <a class="btn btn-circle btn-icon-only btn-default fullscreen" title="全屏"></a>
                        <a class="btn btn-circle btn-icon-only btn-default bclose" title="关闭">
                            <i class="la la-close"></i>
                        </a>
                    </div>
                </div>
                <div class="portlet-body">
                    <div class="text-center">
                        <div class="current-datetime-time" style="font-size: 50px; font-weight: bold;">
                            21:04:23
                        </div>
                    </div>
                </div>
            </div>

            <div class="portlet portlet-sortable-empty"></div>
        </div>
        <div class="row">
            <div id="col21" class="col-md-6 column sortable">

                <div id="p322" class="portlet portlet-sortable light bordered" style="height:500px">
                    <div class="portlet-title" style="padding-top:0;padding-bottom:0;">
                        <div class="caption">
                            <i class="fa fa-bar-chart"></i>统计信息
                        </div>
                        <div class="actions">
                            <a class="btn btn-circle btn-icon-only btn-default bcollapse" title="展开/合上">
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <a class="btn btn-circle btn-icon-only btn-default breload" title="刷新">
                                <i class="icon-reload"></i>
                            </a>
                            <a class="btn btn-circle btn-icon-only btn-default fullscreen" title="全屏"></a>
                            <a class="btn btn-circle btn-icon-only btn-default bclose" title="关闭">
                                <i class="la la-close"></i>
                            </a>
                        </div>
                    </div>
                    <div class="portlet-body" style="padding:0;">
                        <div class="col-md-6">
                            <div id="mainChart" class="jxchart" style="padding: 10px;height: 458px;"></div>
                        </div>
                        <div class="col-md-6" style="border-left:1px solid #ddd;">
                            <div id="mainChart2" class="jxchart" style="padding: 10px;height: 458px;"></div>
                        </div>
                    </div>
                </div>

                <div class="portlet portlet-sortable-empty"></div>
            </div>
            <div id="col22" class="col-md-6 column sortable">

                <div id="p312" class="portlet portlet-sortable light bordered" style="height:600px">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-tasks"></i>待办任务
                        </div>
                        <div class="actions">
                            <a class="btn btn-circle btn-icon-only btn-default bcollapse" title="展开/合上">
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <a class="btn btn-circle btn-icon-only btn-default breload" title="刷新">
                                <i class="icon-reload"></i>
                            </a>
                            <a class="btn btn-circle btn-icon-only btn-default fullscreen" title="全屏"></a>
                            <a class="btn btn-circle btn-icon-only btn-default bclose" title="关闭">
                                <i class="la la-close"></i>
                            </a>
                        </div>
                    </div>
                    <div class="portlet-body" style="text-align:center;">
                        <div id="chartPie" class="jxchart" style="height: 558px;"></div>
                    </div>
                </div>

                <div class="portlet portlet-sortable-empty"></div>
            </div>
        </div>
        <div class="row">
            <div id="col31" class="col-md-4 column sortable">
                <div id="p22" class="portlet portlet-sortable light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-calendar"></i>农历日期
                        </div>
                        <div class="actions">
                            <a class="btn btn-circle btn-icon-only btn-default bcollapse" title="展开/合上">
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <a class="btn btn-circle btn-icon-only btn-default breload" title="刷新">
                                <i class="icon-reload"></i>
                            </a>
                            <a class="btn btn-circle btn-icon-only btn-default fullscreen" title="全屏"></a>
                            <a class="btn btn-circle btn-icon-only btn-default bclose" title="关闭">
                                <i class="la la-close"></i>
                            </a>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="text-center">
                            <div style="font-size:25px">
                            ${.now?string("yyyy")}-${.now?string("MM")}-
                                <span style="font-size: 50px;color:green">${.now?string("dd")}</span>
                                <br>
                            </div>

                            <div class="current-datetime-time" style="font-size: 50px; font-weight: bold;">
                            ${.now?string("HH:mm:ss")}
                            </div>
                        </div>
                    </div>
                </div>

                <div class="portlet portlet-sortable-empty"></div>
            </div>
            <div id="col32" class="col-md-4 column sortable">

                <div id="p321" class="portlet portlet-sortable light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-bubbles"></i>通知公告
                        </div>
                        <div class="actions">
                            <a class="btn btn-circle btn-icon-only btn-default bcollapse" title="展开/合上">
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <a class="btn btn-circle btn-icon-only btn-default breload" title="刷新">
                                <i class="icon-reload"></i>
                            </a>
                            <a class="btn btn-circle btn-icon-only btn-default fullscreen" title="全屏"></a>
                            <a class="btn btn-circle btn-icon-only btn-default bclose" title="关闭">
                                <i class="la la-close"></i>
                            </a>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div id="myNotice" class="scroller" style="height: 200px;" data-always-visible="1"
                             data-rail-visible1="1">
                            <ul class="portlet-data-list">
                                <li>
                                    <div>
                                        <a href="javascript:;" class="name"> 张晓娜 </a>
                                        <span class="datetime"> 08月25日 16:26 </span>
                                        <span class="body"> <a class="read-message cursor-pointer"
                                                               data-url="/HR/HrNotice/Details/129">2016年9月周内值班表</a> </span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        <a href="javascript:;" class="name"> 张晓娜 </a>
                                        <span class="datetime"> 08月25日 16:23 </span>
                                        <span class="body"> <a class="read-message cursor-pointer"
                                                               data-url="/HR/HrNotice/Details/128">2016年9月周末值班表</a> </span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        <a href="javascript:;" class="name"> 张晓娜 </a>
                                        <span class="datetime"> 08月22日 15:01 </span>
                                        <span class="body"> <a class="read-message cursor-pointer"
                                                               data-url="/HR/HrNotice/Details/124">关于提交8月部门工作数据的通知</a> </span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        <a href="javascript:;" class="name"> 张强 </a>
                                        <span class="datetime"> 08月08日 16:44 </span>
                                        <span class="body"> <a class="read-message cursor-pointer"
                                                               data-url="/HR/HrNotice/Details/123">关于召开高企认证协作推进会的通知</a> </span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        <a href="javascript:;" class="name"> 张晓娜 </a>
                                        <span class="datetime"> 08月05日 15:56 </span>
                                        <span class="body"> <a class="read-message cursor-pointer"
                                                               data-url="/HR/HrNotice/Details/122">关于给予徐尧尧通报批评的决定</a> </span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        <a href="javascript:;" class="name"> 张晓娜 </a>
                                        <span class="datetime"> 08月05日 15:56 </span>
                                        <span class="body"> <a class="read-message cursor-pointer"
                                                               data-url="/HR/HrNotice/Details/121">关于给予李海江等人通报批评的决定</a> </span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        <a href="javascript:;" class="name"> 张强 </a>
                                        <span class="datetime"> 08月05日 09:48 </span>
                                        <span class="body"> <a class="read-message cursor-pointer"
                                                               data-url="/HR/HrNotice/Details/120">关于召开2016年上半年工作总结会议的通知</a> </span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        <a href="javascript:;" class="name"> 张强 </a>
                                        <span class="datetime"> 08月01日 15:15 </span>
                                        <span class="body"> <a class="read-message cursor-pointer"
                                                               data-url="/HR/HrNotice/Details/119">关于公司组织架构及部分人员调整的决定</a> </span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        <a href="javascript:;" class="name"> 张强 </a>
                                        <span class="datetime"> 08月01日 15:08 </span>
                                        <span class="body"> <a class="read-message cursor-pointer"
                                                               data-url="/HR/HrNotice/Details/118">关于给予马刚强通报批评的决定</a> </span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        <a href="javascript:;" class="name"> 张强 </a>
                                        <span class="datetime"> 07月27日 14:46 </span>
                                        <span class="body"> <a class="read-message cursor-pointer"
                                                               data-url="/HR/HrNotice/Details/117">关于提交7月部门工作数据的通知</a> </span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        <a href="javascript:;" class="name"> 张强 </a>
                                        <span class="datetime"> 07月26日 17:09 </span>
                                        <span class="body"> <a class="read-message cursor-pointer"
                                                               data-url="/HR/HrNotice/Details/116">关于下发《固定资产管理制度》的通知</a> </span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        <a href="javascript:;" class="name"> 张强 </a>
                                        <span class="datetime"> 07月25日 09:15 </span>
                                        <span class="body"> <a class="read-message cursor-pointer"
                                                               data-url="/HR/HrNotice/Details/115">2016年8月周末值班表</a> </span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        <a href="javascript:;" class="name"> 张强 </a>
                                        <span class="datetime"> 07月25日 09:14 </span>
                                        <span class="body"> <a class="read-message cursor-pointer"
                                                               data-url="/HR/HrNotice/Details/114">2016年8月周内值班表</a> </span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        <a href="javascript:;" class="name"> 张强 </a>
                                        <span class="datetime"> 07月22日 09:58 </span>
                                        <span class="body"> <a class="read-message cursor-pointer"
                                                               data-url="/HR/HrNotice/Details/113">28楼消防疏散图</a> </span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        <a href="javascript:;" class="name"> 张强 </a>
                                        <span class="datetime"> 07月22日 09:58 </span>
                                        <span class="body"> <a class="read-message cursor-pointer"
                                                               data-url="/HR/HrNotice/Details/112">27楼消防疏散图</a> </span>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>


                <div class="portlet portlet-sortable-empty"></div>
            </div>
            <div id="col33" class="col-md-4 column sortable">


                <div id="p332" class="portlet portlet-sortable light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-user"></i>用户信息
                        </div>
                        <div class="actions">
                            <a class="btn btn-circle btn-icon-only btn-default bcollapse" title="展开/合上">
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <a class="btn btn-circle btn-icon-only btn-default breload" title="刷新">
                                <i class="icon-reload"></i>
                            </a>
                            <a class="btn btn-circle btn-icon-only btn-default fullscreen" title="全屏"></a>
                            <a class="btn btn-circle btn-icon-only btn-default bclose" title="关闭">
                                <i class="la la-close"></i>
                            </a>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div style="font-size:18px">

                            <div class="row">
                                <div class="col-xs-12">
                                    欢迎您：<span style="color: red; font-weight: bold">系统管理员<text>(信息中心)</text></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-xs-12">
                                    登陆次数:<span>2830</span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    上次登陆时间:<span>2018-03-08 21:04</span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    角色:<span>${userOwneRoleString}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="portlet portlet-sortable-empty"></div>
            </div>
        </div>
    </div>
</div>
<@footer>
    <script src="${web.cdn('/lib/jquery/jquery.ui.js')}"></script>
    <script src="${web.url('/sys/js/home.js')}"></script>
</@footer>