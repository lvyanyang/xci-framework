/**
 * 主界面模块
 */
jx.ready(function () {
    //region 私有变量
    var $body = $(document.body);
    var $center = $('.center-panel');
    var $west = $('.west-panel');
    var $tree = $west.find('.jxtree');
    var $navHome = $('#nav_home');
    var $navLogout = $('#nav_logout');
    var $tabs = $('#tabs'), $closeMenu = $('#closeMenu');
    var $menuFilter = $tree.parent().find('.jxtree-filter');
    var idMap;
    //endregion

    //region 公共方法

    jx.app = {
        /**
         * 加载页面
         */
        addPage: function (id, title, url, iconCls, closable) {
            addPage(id, title, url, iconCls, closable);
        },
        /**
         * 刷新页面
         */
        refreshPage: function (id, title, url, iconCls, closable) {
            addPage(id, title, url, iconCls, closable);
        },
        /**
         * 关闭页面
         */
        closePage: function (id) {
            closePage(id);
        },
        /**
         * 关闭页面
         */
        closeCurrentPage: function () {
            var id = getCurrentPageId();
            closePage(id);
        },
        getCurrentPageId: function () {
            return getCurrentPageId();
        },
        /**
         * 查询页面对象
         */
        findCurrentPage: function () {
            var id = getCurrentPageId();
            return findPage(id);
        },
        /**
         * 查询页面对象
         * @param id 标识
         */
        findPage: function (id) {
            return findPage(id);
        },
        /**
         * 中间页面全屏
         */
        toggleFullPage: function () {
            toggleFullPage();
        },
        toggleFullScreen: function () {
            toggleFullScreen();
        },
        fullPage: function (isFull) {
            fullPage(isFull);
        },
        fullScreen: function (isFull) {
            fullScreen(isFull);
        }
    }

    //endregion

    //region 私有方法

    /**
     * 初始化用户消息刷新
     */
    var initMessageRefresh = function () {
        // if (window.systemEnableMessageRefresh === '1') {
        //     var load = function () {
        //         jx.ajax({
        //             url: jx.apiUrl('/sys/message/uncount'),
        //             type: 'post',
        //             silent: true, //不弹出错误框
        //             success: function (result) {
        //                 if (result.success) {
        //                     var count = result.data.count;
        //                     if (count > 0) {
        //                         $('#user_message .icon-bubbles').jxflash();
        //                         $('#unMessageCountBadge').show().text(count);
        //                     } else {
        //                         $('#user_message .icon-bubbles').jxunflash();
        //                         $('#unMessageCountBadge').hide().text('');
        //                     }
        //                 }
        //             }
        //         });
        //     };
        //     load();//先执行一次查询
        //     jx.auth.autoTask(load, 20000);//自动任务
        // }
    };

    /**
     * 初始化用户激活心跳
     */
    var initOnlineUserRefresh = function () {
        if (window.systemEnableOnlineUserRefresh === '1') {
            var load = function () {
                jx.ajax({
                    url: jx.url('/sys/active'),
                    type: 'post',
                    silent: true //不弹出错误框
                });
            };
            jx.auth.autoTask(load, 20000);//自动任务
        }
    };

    /**
     * 初始化刷新当前时间
     */
    var initCurrentDateTimeRefresh = function () {
        var load = function () {
            $('#current_time').text(jx.formatDateTime(new Date()));
        };
        jx.auth.autoTask(load, 1000);//自动任务
    };

    /**
     * 初始化主布局
     */
    var initLayout = function () {
        initTabs();
        initNavEvent();
        initTree();
        jx.complete(function () {
            $body.jxlayout().monitor('west', 'spring-boot-webapp');

            fullPage(!jx.toBoolean(localStorage.getItem('appPageFull')));
        });
    };

    /**
     * 初始化导航按钮
     */
    var initNavEvent = function () {
        $navHome.on('click', function () {
            window.location.hash = '';
            loadHome();
        });

        $navLogout.on('click', function () {
            var url = $(this).data('url');
            layer.confirm('注：您确定要安全退出本次登录吗？', {icon: 0, title: '系统提示', skin: 'jxnav-layer'}, function (index) {
                $.ajax({
                    url: url,
                    type: 'post',
                    dataType: 'json',
                    data: {}
                }).done(function (result) {
                    layer.close(index);
                    if (result.success) {
                        window.location.href = result.data.url;
                    } else {
                        layer.alert(result.msg);
                    }
                }).fail(function (request) {
                    if (request.responseJSON) {
                        layer.alert(request.responseJSON.message || '退出失败');
                    } else {
                        layer.alert(request.statusText || '退出失败');
                    }
                });
            });
        });

        $('#nav_fullscreen').click(function () {
            toggleFullScreen();
            var isFull = $body.data('appScreenFull');//全屏状态
            $(this).find('span').text(isFull ? '全屏' : '退出全屏');
        });
    };

    var getCharCode = function (id) {
        var sz = id.split('') || [];
        var result = '';
        var len = sz.length >= 32 ? 16 : sz.length;
        for (var i = 0; i < len; i++) {
            var c = sz[i];
            result = result + c.charCodeAt();
        }
        return result;
    };

    /**
     * 初始化导航菜单
     */
    var initTree = function () {
        var treeOps = {
            defaultIconCls: 'icon-flag',
            maskMsg: $tree.data('maskMsg') || '正在加载菜单...',
            onLoadSuccess: function (parentNode, result) {
                idMap = {};
                jx.treeRecursion(result, function (n) {
                    var id = n.id;
                    var code = getCharCode(id);
                    idMap[code] = id;
                });

                var hashId = window.location.hash.replace('#', '');
                if (!hashId) {
                    loadHome();
                    return;
                }

                var nodeId = idMap[hashId];
                if (!nodeId) {
                    loadHome();
                    window.location.hash = '';
                    return;
                }
                var node = $(this).tree('find', nodeId);
                if (!node) {
                    window.location.hash = '';
                    return;
                }
                $(this).tree('expandTo', node.target);
                $(this).tree('select', node.target);
                addPage(node.id, node.text, node.url, node.iconCls, true);
            },
            onClick: function (node) {
                $(this).tree('expand', node.target);
                addPage(node.id, node.text, node.url, node.iconCls, true);
            },
            onDblClick: function (node) {
                if (node.state === 'open') {
                    $(this).tree('collapse', node.target);
                }
            },
            onContextMenu: function (e, node) {
                $(this).tree('select', node.target);
                e.preventDefault();
            }
        };
        if ($menuFilter.length > 0) {
            treeOps.filterBox = $menuFilter;
        }
        //刷新用户菜单(不从缓存读取)
        $menuFilter.parent().find('button').click(function () {
            jx.ajax({
                url: jx.url('sys/clearUserModuleTree'),
                type: 'post',
                success: function () {
                    $tree.tree('reload');
                }
            });
        });
        $tree.options(treeOps);
    };

    /**
     * 初始化多标签页
     */
    var initTabs = function () {
        if (window.systemEnableTabPage === '1') {
            $closeMenu.menu({onClick: rightMenuClick});
            $tabs.tabs({
                fit: true,
                border: false,
                onContextMenu: function (e, title) {
                    $closeMenu.menu('show', {left: e.pageX, top: e.pageY});
                    $tabs.tabs('select', title);
                    e.preventDefault();
                },
                onClose: setLocationHash,
                onSelect: setLocationHash
            });
        } else {
            $center.attr('title', getHomeTitle());
            $center.empty();
        }
    };

    /**
     * 获取主页标题
     */
    var getHomeTitle = function () {
        return $center.data('title');
    };

    /**
     * 加载主页
     */
    var loadHome = function () {
        var url = $center.data('url');
        var title = getHomeTitle();
        addPage('home', title, url, 'icon-home', false);
    };

    var setLocationHash = function () {
        var hashId, mid;
        if (window.systemEnableTabPage === '1') {
            var currentTab = $tabs.tabs('getSelected');
            if (currentTab) {
                mid = currentTab.panel('options').mid;
                var node = $tree.tree('find', mid);
                if (node) {
                    $tree.tree('select', node.target);
                }
            }
        } else {
            var centerPanel = $body.layout('panel', 'center');
            if (centerPanel) {
                mid = centerPanel.panel('options').mid;
            }
        }
        if (!mid) {
            window.location.hash = '';
        } else if (mid !== 'home') {
            hashId = getCharCode(mid);
            window.location.hash = hashId;
        } else {
            window.location.hash = '';
        }
    };

    var tabRefresh = function (tabObject, url) {
        if (!url) {
            url = $(tabObject.panel('options').content).attr('src');
        }
        $tabs.tabs('update', {tab: tabObject, options: {content: jx.createIframe(url)}})
    };

    var getTabTitles = function () {
        var titles = [];
        var tabs = $tabs.tabs('tabs');
        $.each(tabs, function () {
            titles.push($(this).panel('options').title);
        });
        return titles;
    };

    var rightMenuClick = function (item) {
        var currentTab = $tabs.tabs('getSelected');
        var titles = getTabTitles();
        var currtabTitle = currentTab.panel('options').title;
        var tabIndex = $tabs.tabs('getTabIndex', currentTab);
        switch (item.id) {
            case "refresh":
                tabRefresh(currentTab);
                break;
            case "close":
                $tabs.tabs('close', currtabTitle);
                break;
            case "closeall":
                $.each(titles, function () {
                    if (this != getHomeTitle())
                        $tabs.tabs('close', this);
                });
                break;
            case "closeother":
                $.each(titles, function () {
                    if (this != currtabTitle && this != getHomeTitle())
                        $tabs.tabs('close', this);
                });
                break;
            case "closeright":
                if (tabIndex == titles.length - 1) {
                    jx.alert('亲，后边没有啦 ^@^!!');
                    return false;
                }
                $.each(titles, function (i) {
                    if (i > tabIndex && this != getHomeTitle())
                        $tabs.tabs('close', this);
                });
                break;
            case "closeleft":
                if (tabIndex == 1) {
                    jx.alert('亲，前边那个上头有人，咱惹不起哦。 ^@^!!');
                    return false;
                }
                $.each(titles, function (i) {
                    if (i < tabIndex && this != getHomeTitle())
                        $tabs.tabs('close', this);
                });
                break;
            case "fullPage":
                top.app.toggleFullPage();
                break;
        }
    };

    /**
     * 添加页面
     */
    var addPage = function (id, title, url, iconCls, closable) {
        if (!url || url === '#') return;
        if (!id) id = jx.uuid();
        if (!iconCls) iconCls = '';
        if (!closable) closable = true;

        url = jx.apiUrl(url);

        if (window.systemEnableTabPage === '1') {
            var tabCount = $tabs.tabs('tabs').length;
            var hasTab = $tabs.tabs('exists', title);
            if ((tabCount <= 15) || hasTab) {
                if (!hasTab) {
                    $tabs.tabs('add', {
                        mid: id,
                        title: title,
                        content: jx.createIframe(url),
                        bodyCls: 'tabs-panel-body',
                        closable: closable,
                        iconCls: iconCls
                    });
                } else {
                    $tabs.tabs('select', title);
                    tabRefresh($tabs.tabs('getSelected'), url);   //选择Tab时刷新页面
                }
                setLocationHash();
            } else {
                jx.alert('您当前打开了太多的页面，如果继续打开，会造成程序运行缓慢，无法流畅操作！');
            }
        } else {
            var centerPanel = $body.layout('panel', 'center');
            var _url = url + ((/\?/).test(url) ? '&' : '?') + '_ts=' + (Date.now());
            var _title = title || centerPanel.title || '';
            centerPanel.panel({
                mid: id,
                title: title,
                iconCls: iconCls,
                content: jx.createIframe(_url),
                onOpen: function () {
                    $(this).parent().find('.panel-header>.panel-icon')
                      .css('cursor', 'pointer').click(function () {
                        top.app.toggleFullPage();
                        // var $this = $(this);
                        // var isfs = localStorage.getItem('appPageFull');
                        // if (isfs === 'true') {
                        //     $this.addClass('jxfs-actual');
                        // }
                        // else {
                        //     $this.removeClass('jxfs-actual');
                        // }
                    });
                }
            });
            centerPanel.mask('正在加载 <span style="color: red;font-weight: bold;">' + _title + '</span> 请稍等...', 500);
            centerPanel.find('iframe').on('load', function () {
                centerPanel.unmask();
            });
            setLocationHash();
        }
    };

    var getCurrentPageId = function () {
        var currentTab = $tabs.tabs('getSelected');
        return currentTab.panel('options').mid;
    };

    var closePage = function (id) {
        if (!id) return null;
        if (window.systemEnableTabPage === '1') {
            var win = null;
            var tabs = $tabs.tabs('tabs');
            $.each(tabs, function () {
                var mid = $(this).panel('options').mid;
                if (mid === id) {
                    var title = $(this).panel('options').title;
                    $tabs.tabs('close', title);
                }
            });
            return win;
        }
    };

    var findPage = function (id) {
        if (!id) return null;
        if (window.systemEnableTabPage === '1') {
            var win = null;
            var tabs = $tabs.tabs('tabs');
            $.each(tabs, function () {
                var mid = $(this).panel('options').mid;
                if (mid === id) {
                    win = $(this).find('iframe')[0].contentWindow;
                }
            });
            return win;
        } else {
            var centerPanel = $body.layout('panel', 'center');
            return centerPanel.find('iframe')[0].contentWindow;
        }
    };

    /**
     * 切换中间页面全屏
     */
    var toggleFullPage = function () {
        var isScreenFull = $body.data('appPageFull');//全屏状态
        fullPage(isScreenFull);
        localStorage.setItem('appPageFull', !isScreenFull);
    };

    var fullPage = function (isFull) {
        if (jx.isUndefined(isFull)) isFull = false;
        var panels = $body.data().layout.panels;//全部面板
        var method = isFull === true ? 'open' : 'close';//调用方法

        if (panels.north.length > 0 && panels.north.panel('options').collapsed) panels.expandNorth.panel(method); else panels.north.panel(method);
        if (panels.south.length > 0 && panels.south.panel('options').collapsed) panels.expandSouth.panel(method); else panels.south.panel(method);
        if (panels.east.length > 0 && panels.east.panel('options').collapsed) panels.expandEast.panel(method); else panels.east.panel(method);
        if (panels.west.length > 0 && panels.west.panel('options').collapsed) panels.expandWest.panel(method); else panels.west.panel(method);

        $body.data('appPageFull', !isFull);
        $body.layout('resize');
    };

    /**
     * 切换浏览器全屏
     */
    var toggleFullScreen = function () {
        var isFull = $body.data('appScreenFull');//全屏状态
        fullScreen(isFull);
    };

    /**
     * 浏览器全屏
     */
    var fullScreen = function (isFull) {
        if (jx.isUndefined(isFull)) isFull = true;
        if (isFull === true) {
            var docElm = document.documentElement;
            if (docElm.requestFullscreen) {
                docElm.requestFullscreen();
            } else if (docElm.msRequestFullscreen) {
                docElm = document.body; //overwrite the element (for IE)
                docElm.msRequestFullscreen();
            } else if (docElm.mozRequestFullScreen) {
                docElm.mozRequestFullScreen();
            } else if (docElm.webkitRequestFullScreen) {
                docElm.webkitRequestFullScreen();
            }
        } else {
            if (document.exitFullscreen) {
                document.exitFullscreen();
            } else if (document.msExitFullscreen) {
                document.msExitFullscreen();
            } else if (document.mozCancelFullScreen) {
                document.mozCancelFullScreen();
            } else if (document.webkitCancelFullScreen) {
                document.webkitCancelFullScreen();
            }
        }
        $body.data('appScreenFull', !isFull);
    };

    /**
     * 模块初始化
     * @private
     */
    var init = function () {
        initCurrentDateTimeRefresh();
        initMessageRefresh();
        initOnlineUserRefresh();
        initLayout();
    }

    //endregion

    //region 模块初始化
    init();
    //endregion
})