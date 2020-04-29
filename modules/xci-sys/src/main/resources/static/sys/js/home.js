/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

jx.ready(function () {

    var index = function () {
        return {
            handlePortletTools: function () {
                var self = this;

                $('body').on('click', '.portlet > .portlet-title > .actions > a.bclose', function (e) {
                    e.preventDefault();
                    var portlet = $(this).closest('.portlet');

                    if ($('body').hasClass('page-portlet-fullscreen')) {
                        $('body').removeClass('page-portlet-fullscreen');
                    }

                    portlet.find('.portlet-title > .actions > .bclose').tooltip('destroy');
                    portlet.find('.portlet-title > .actions > .fullscreen').tooltip('destroy');
                    portlet.find('.portlet-title > .actions > .breload').tooltip('destroy');
                    portlet.find('.portlet-title > .actions > .bcollapse, .portlet > .portlet-title > .actions > .bexpand').tooltip('destroy');
                    portlet.remove();
                });

                $('body').on('click', '.portlet > .portlet-title > .actions > .fullscreen', function (e) {
                    e.preventDefault();
                    var portlet = $(this).closest('.portlet');
                    if (portlet.hasClass('portlet-fullscreen')) {
                        $(this).removeClass('on');
                        portlet.removeClass('portlet-fullscreen');
                        $('body').removeClass('page-portlet-fullscreen');
                        portlet.children('.portlet-body').css('height', 'auto');
                    } else {
                        var height = self.getViewPort().height -
                            portlet.children('.portlet-title').outerHeight() -
                            parseInt(portlet.children('.portlet-body').css('padding-top')) -
                            parseInt(portlet.children('.portlet-body').css('padding-bottom'));
                        //console.log(height);
                        $(this).addClass('on');
                        portlet.addClass('portlet-fullscreen');
                        $('body').addClass('page-portlet-fullscreen');
                        portlet.children('.portlet-body').css('height', height);
                    }
                    //var $chart = portlet.find('.jxchart');
                    //$chart.height('100%');
                    //$chart.jxchart().getChart().resize();
                });

                $('body').on('click', '.portlet > .portlet-title > .actions > .breload', function (e) {
                    e.preventDefault();
                    var el = $(this).closest('.portlet').children('.portlet-body');
                    var url = $(this).attr('data-url');
                    if (url) {
                        el.mask('加载中...');
                        $.ajax({
                            type: 'GET',
                            cache: false,
                            url: url,
                            dataType: 'html',
                            success: function (res) {
                                el.unmask();
                                el.html(res);
                            },
                            error: function (xhr, ajaxOptions, thrownError) {
                                el.unmask();
                                //var msg = 'Error on reloading the content. Please check your connection and try again.';
                                //alert(msg);
                                console.log(xhr);
                            }
                        });
                    }
                });

                $('body').on('click', '.portlet > .portlet-title > .actions > .bcollapse, .portlet .portlet-title > .actions > .bexpand', function (e) {
                    e.preventDefault();
                    var portlet = $(this).closest('.portlet');
                    var el = portlet.children('.portlet-body');
                    var title = el.prev();
                    if ($(this).hasClass('bcollapse')) {
                        $(this).removeClass('bcollapse').addClass('bexpand');
                        portlet.data('orghei', portlet.height());
                        el.slideUp(200, function () {
                            //portlet.height(title.height() + parseInt(portlet.css('margin-bottom')));
                            portlet.height(title.height() + 10);
                        });
                    } else {
                        $(this).removeClass('bexpand').addClass('bcollapse');

                        portlet.height(portlet.data('orghei'));
                        el.slideDown(200);
                    }
                });
            },
            initPortlet: function () {
                var self = this;

                self.handlePortletTools();
                $('.column').sortable({
                    connectWith: '.column',
                    items: '.portlet',
                    opacity: 0.8,
                    handle: '.portlet-title',
                    coneHelperSize: true,
                    placeholder: 'portlet-sortable-placeholder',
                    forcePlaceholderSize: true,
                    tolerance: 'pointer',
                    helper: 'clone',
                    cancel: '.portlet-sortable-empty, .portlet-fullscreen', // cancel dragging if portlet is in fullscreen mode
                    revert: 250, // animation in milliseconds
                    create: function (event, ui) {
                        self.readItems();
                    },
                    update: function (event, ui) {
                        if (ui.item.prev().hasClass('portlet-sortable-empty')) {
                            ui.item.prev().before(ui.item);
                        }
                    },
                    stop: function (event, ui) {
                        //console.log($('#sortable_portlets').sortable('toArray'));
                        var charts = ui.item.find('.jxchart');
                        if (charts.length > 0) {
                            for (var i = 0; i < charts.length; i++) {
                                var chart = charts[i];
                                //console.log();
                                $(chart).jxchart().getChart().resize();
                            }
                        }
                        //console.log(ui);
                        self.saveItems();
                    }
                });

            },
            getViewPort: function () {
                var e = window,
                    a = 'inner';
                if (!('innerWidth' in window)) {
                    a = 'client';
                    e = document.documentElement || document.body;
                }

                var result = {
                    width: e[a + 'Width'],
                    height: e[a + 'Height']
                };
                return result;
            },
            initSlimScroll: function (el) {
                $(el).each(function () {
                    if ($(this).attr('data-initialized')) {
                        return; // exit
                    }

                    var height;

                    if ($(this).attr('data-height')) {
                        height = $(this).attr('data-height');
                    } else {
                        height = $(this).css('height');
                    }

                    $(this).slimScroll({
                        allowPageScroll: true, // allow page scroll when the element scroll is ended
                        size: '7px',
                        color: ($(this).attr('data-handle-color') ? $(this).attr('data-handle-color') : '#bbb'),
                        wrapperClass: ($(this).attr('data-wrapper-class') ? $(this).attr('data-wrapper-class') : 'slimScrollDiv'),
                        railColor: ($(this).attr('data-rail-color') ? $(this).attr('data-rail-color') : '#eaeaea'),
                        position: 'right',
                        height: height,
                        alwaysVisible: ($(this).attr('data-always-visible') == '1' ? true : false),
                        railVisible: ($(this).attr('data-rail-visible') == '1' ? true : false),
                        disableFadeOut: true
                    });

                    $(this).attr('data-initialized', '1');
                });
            },
            saveItems: function () {
                var portlets = {};
                //获得列
                var colums = $(".column");
                for (var i = 0; i < colums.length; i++) {
                    var colum = colums.eq(i);
                    //获得列中可拖动的成员
                    var cItem = colum.sortable("toArray");
                    var columId = colum.attr("id");
                    portlets[columId] = {};
                    for (var t = 0; t < cItem.length; t++) {
                        //获得可拖动模块的id
                        var portletId = cItem[t];
                        if (portletId) {
                            portlets[columId]["portlet" + t] = portletId;
                        }
                    }
                }
                window.localStorage.setItem('portlets_indexs', JSON.stringify(portlets));
            },
            readItems: function () {
                var portlets = localStorage.getItem('portlets_indexs');
                if (portlets) {
                    portlets = JSON.parse(portlets);
                }
                if (portlets) {
                    for (column in portlets) {
                        var col = portlets[column];
                        for (portlet in col) {
                            var portletId = col[portlet];
                            if (portletId) {
                                $("#" + column).find('.portlet-sortable-empty').before($("#" + portletId).remove());
                            }
                        }
                    }
                }
            },
            init: function () {
                this.initPortlet();
            }
        }
    }();

    index.init();
    index.initSlimScroll('.scroller');
    setInterval(function () {
        $('.current-datetime-time').html(jx.formatTime(new Date()));
    }, 1000);

    var $chart = $('#mainChart');
    var $chart2 = $('#mainChart2');

    $('#chartPie').jxchart({
        title: {
            text: '某站点用户访问来源',
            subtext: '纯属虚构',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    {value: 335, name: '直接访问'},
                    {value: 310, name: '邮件营销'},
                    {value: 234, name: '联盟广告'},
                    {value: 135, name: '视频广告'},
                    {value: 1548, name: '搜索引擎'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    });

    var instance = $chart.jxchart({
        title: {
            text: 'ECharts示例'
        },
        tooltip: {},
        // legend: {
        //     data: ['销量']
        // },
        xAxis: {
            data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            itemStyle: {
                color: 'green'
            },
            data: [5, 20, 36, 10, 10, 20]
        }]
    });
    var instance1 = $chart2.jxchart({
        title: {
            text: '折线图堆叠'
        },
        tooltip: {
            trigger: 'axis'
        },
        // legend: {
        //     data: ['邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎']
        // },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            show: false
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name: '邮件营销',
                type: 'line',
                stack: '总量',
                data: [120, 132, 101, 134, 90, 230, 210]
            },
            {
                name: '联盟广告',
                type: 'line',
                stack: '总量',
                data: [220, 182, 191, 234, 290, 330, 310]
            },
            {
                name: '视频广告',
                type: 'line',
                stack: '总量',
                data: [150, 232, 201, 154, 190, 330, 410]
            },
            {
                name: '直接访问',
                type: 'line',
                stack: '总量',
                data: [320, 332, 301, 334, 390, 330, 320]
            },
            {
                name: '搜索引擎',
                type: 'line',
                stack: '总量',
                data: [820, 932, 901, 934, 1290, 1330, 1320]
            }
        ]
    });

    instance.on('click', function (p) {
        jx.msg(p.name);
    });
});