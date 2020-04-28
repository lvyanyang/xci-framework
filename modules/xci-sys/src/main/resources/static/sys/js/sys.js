/*-----------------------------------------------------
 * 权限子系统
 * ---------------------------------------------------*/

jx.rootPath = document.currentScript.dataset.rootPath || '';
jx.debug = document.currentScript.dataset.debug == 'true' || false;
$.fn.jxgrid.defaults.pageSize = document.currentScript.dataset.pageSize || 20;
$.fn.jxgrid.defaults.pageList = [10, 15, 20, 25, 30, 50, 80, 100];
$.fn.jxgrid.defaults.idField = 'id';
$.fn.jxgrid.defaults.nameField = 'name';
$.fn.jxgrid.defaults.striped = true;
$.fn.jxtreegrid.defaults.idField = 'id';
$.fn.jxtreegrid.defaults.treeField = 'name';
$.fn.jxtreegrid.defaults.striped = true;

jx.cdn = document.currentScript.dataset.cdn || '';
jx.sys = {};

jx.auth = {
    app: top.app,

    /**
     * 初始化编辑表单
     * @param _$form 表单对象
     * @param _mode 操作模式:1.对话框模式 2.页面跳转模式 3.标签页模式
     * @return {JXEditForm}
     */
    jxEditFormInit: function (_$form, _mode) {
        var jxEditForm = new JXEditForm(_$form, _mode);
        $('#btnContinuousSave').click(jxEditForm.continuousSave);
        $('#btnSave').click(jxEditForm.save);
        $('#btnCancel').click(jxEditForm.cancel);
        return jxEditForm;
    },
    findParentPage: function () {
        return top.app.findPage(jx.getUrlParam('_ppid'));
    },
    /**
     * 定时任务
     * @param fn
     * @param interval
     */
    autoTask: function (fn, interval) {
        var callback = function () {
            fn();
            setTimeout(callback, interval);
        };

        setTimeout(callback, interval);
    },
    /**
     * 提交当前表单
     */
    submitCurrentForm: function (element) {
        $(element).closest('form').find(':submit').click();
    },
    /**
     * 详情对话框
     */
    iconDialog: function (ops) {
        var url = jx.url('/sys/icon');
        if (ops.inputId) {
            var inputId = ops.inputId;
            url = jx.setUrlParam(url, 'inputId', inputId)
        }
        var def = {
            title: '选择图标',
            shadeClose: true,
            width: '90%',
            url: jx.apiUrl(url),
            height: '80%'
        };
        if (ops.callback) {
            def.end = function () {
                ops.callback($('#' + ops.inputId).val());
            };
        }
        jx.dialog($.extend({}, def, ops));
    },
    /**
     * 初始化标签页
     */
    tabsInit: function (callback) {
        $('a[data-toggle="tab"]').on('shown.bs.tab', function () {
            var $this = $(this);
            if ($this.data('_init')) return;
            $this.data('_init', 1);
            var id = this.hash.substr(1);
            if (callback) {
                callback(id);
            }
        });
    },
    /**
     * 初始化文件表格
     */
    initFileGrid: function () {
        var gridInstance = $('#jx-file-grid').jxgrid({
            onDblClickRow: function (index, row) {
                jx.auth.detailsData(gridInstance, row, {
                    title: '查看文件详细信息',
                    url: jx.apiUrl('/sys/file/details')
                });
            }
        });
        var $gridPanel = gridInstance.getPanel();
        $gridPanel.on('click', '.griddelete', function () {
            var id = $(this).data('id');
            var deleteUrl = jx.apiUrl('/sys/file/delete');
            jx.ajax({
                url: deleteUrl,
                data: {id: id},
                confirm: '注：您确定要删除吗？该操作将无法恢复',
                maskMsg: '正在删除...',
                success: function (result) {
                    gridInstance.reloadGridData();
                    jx.toastr.success('操作成功');
                }
            });
        });
    },
    /**
     * 初始化授权标签页
     */
    tabsAuthInit: function (callback) {
        jx.auth.tabsInit(function (id) {
            if (id == 'tab-history') {
                var gridInstance = $('.jxhistroy-grid-detail').jxgrid({
                    url: jx.url(jx.historyLogGridUrl),
                    onDblClickRow: function (index, row) {
                        var gridInstance = $(this).data('jxgrid');
                        jx.showHistoryLogDetails(gridInstance.getRowId(row));
                    }
                });
                //数据行详情事件
                gridInstance.getPanel().on('click', '.cmd-details', function () {
                    jx.showHistoryLogDetails(gridInstance.getRowId(gridInstance.getSelected()));
                });
            } else if (id == 'tab-file') {
                jx.auth.initFileGrid();
            } /*else if (id == 'tab-member') {
                $('#jx-member-user-grid').jxgrid();
            } else if (id == 'tab-user-member') {
                $('#userMemberDetailsGrid').jxgrid();
            } else if (id == 'tab-module') {
                $('#jxAuthModuleDetails').jxtree();
            }else if (id == 'tab-data') {
                $('#jxAuthDeptDetails').jxtree();
            } else if (id == 'tab-role') {
                $('#jx-owner-role-grid').jxgrid();
            }*/
                // else if (id == 'tab-data') {
                //     $('#jxAuthDataDetails').jxtree();
            // }
            else if (callback) {
                callback(id);
            }
        });
    },
    /**
     * 用户表格行双击事件
     * @param index
     * @param row
     */
    onUserGridDblClickRow: function (index, row) {
        var gridInstance = $(this).jxgrid();
        var id = gridInstance.getRowId(row);
        if (!id) return;
        jx.auth.showUserDetails(id);
    },
    /**
     * 角色表格行双击事件
     * @param index
     * @param row
     */
    onRoleGridDblClickRow: function (index, row) {
        var gridInstance = $(this).jxgrid();
        var id = gridInstance.getRowId(row);
        if (!id) return;
        jx.auth.showRoleDetails(id);
    },
    /**
     * 显示角色详情对话框
     * @param id 主键
     */
    showRoleDetails: function (id) {
        if (!id) return;
        jx.detailsDialog({
            title: '查看角色详细信息',
            url: jx.apiUrl('/sys/role/details'),
            params: {id: id},
            anim: -1,
            offset: 'auto',
            width: '700px',
            height: '70%'
        });
    },
    /**
     * 显示用户详情对话框
     * @param id 主键
     */
    showUserDetails: function (id) {
        if (!id) return;
        jx.detailsDialog({
            title: '查看用户详细信息',
            url: jx.apiUrl('/sys/user/details'),
            params: {id: id},
            anim: -1,
            offset: 'auto',
            width: '700px',
            height: '70%'
        });
    },
    /**
     * 显示机构详情对话框
     * @param id 主键
     */
    showDepartmentDetails: function (id) {
        if (!id) return;
        jx.detailsDialog({
            url: jx.url('/sys/dept/details'),
            title: '查看组织机构',
            params: {id: id},
            shadeClose: true,
            offset: 'rt',
            anim: 2,
            width: '50%',
            height: '100%'
        });
    },

    /**
     * 获取简洁版UE编辑器配置
     */
    simpleUEOptions: function () {
        var ops = {
            initialFrameWidth: '100%',
            initialFrameHeight: 200,
            autoHeightEnabled: false,//是否自动长高,默认true
            scaleEnabled: true,//是否可以拉伸长高,默认true(当开启时，自动长高失效)
            enableAutoSave: true,   //启用自动保存
            saveInterval: 2000,     //自动保存间隔时间， 单位ms
            toolbars: [[
                'paragraph', 'fontfamily', 'fontsize', '|',
                'forecolor', 'backcolor', 'bold', 'italic', 'underline', 'fontborder', 'strikethrough', '|',
                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', 'insertorderedlist', 'insertunorderedlist', '|',
                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                'simpleupload', 'link', '|',// 'pagebreak', 'insertcode', 'insertimage', 'scrawl', 'unlink', '|',
                'removeformat', 'formatmatch', 'autotypeset', 'fullscreen', 'source'//, 'help', 'undo', 'redo'
            ]]
        };
        return ops;
    },
    editData: function (gridInstance, ops) {
        if (!gridInstance.hasSelectedRow()) return;
        var id = gridInstance.getSelectedRowId();
        ops.params = $.extend({}, ops.params, {id: id});
        jx.dialog(ops);
    },
    deleteData: function (gridInstance, ops) {
        if (!gridInstance.hasCheckedRow()) return;
        var ids = gridInstance.getCheckedRowIds();
        ops.data = $.extend({}, ops.data, {ids: ids.join()});
        if (!ops.success) {
            ops.success = function () {
                gridInstance.reloadGridData();
            }
        }
        jx.delete(ops);
    },
    detailsData: function (gridInstance, row, ops) {
        var id = gridInstance.getRowId(row);
        if (!id) {
            jx.toastr.error('请设置主键');
            return;
        }
        ops.params = $.extend({}, ops.params, {id: id});
        var def = {
            title: '详细信息',
            shadeClose: true,
            offset: 'rt',
            anim: 2,
            width: '50%',
            height: '100%'
        };
        jx.dialog($.extend({}, def, ops));
    },
    exportData: function (exportUrl, data) {
        $.fileDownload(exportUrl, {
            data: data,
            prepareCallback: function (url) {
                $(document.body).mask('正在导出,请稍等...', 500);
            },
            successCallback: function (url) {
                $(document.body).unmask();
            },
            failCallback: function (responseHtml, url, error) {
                jx.dialog({
                    title: '系统提示',
                    type: 1,
                    content: responseHtml,
                    width: '80%',
                    height: '80%',
                    btn: '确定',
                    callback: function (dwin, layero, index) {
                        $(document.body).unmask();
                        jx.closeDialog(index);
                    }
                });
            }
        });
    },

    gridContextMenuHandle: function (gridInstance, e, index, row) {
        gridInstance.unselectAll();
        gridInstance.selectRow(index);
    },
    showGridContextMenu: function (gridInstance, contextmenuSelector) {
        gridInstance.getDataBodyPanel().contextmenu({target: contextmenuSelector});
    },

    editFormContinuousSave: function ($form) {
        jx.auth.isContinuousSave = true;
        jx.auth.editFormSave($form);
    },
    editFormSave: function ($form) {
        if (!$form) {
            $form = $('#editform');
        }
        $form.jxform().submit();
    },
    editFormCancel: function () {
        jx.closeDialog();
    },
    bindEditFormSuccess: function ($form) {
        if (!$form) {
            $form = $('#editform');
        }
        $form.onFormSuccess(function (e, result) {
            if (jx.auth.isContinuousSave === true) {
                top.jx.toastr.success('保存成功');
                window.location.reload();
            } else {
                top.jx.toastr.success('保存成功');
                jx.closeDialog();
                if (pwin.jx.reloadGrid) {
                    pwin.jx.reloadGrid();
                } else {
                    pwin.reloadGridData();
                }
            }
        });
    },
    bindEditFormTreeSuccess: function ($form) {
        if (!$form) {
            $form = $('#editform');
        }
        $form.onFormSuccess(function (e, result) {
            if (jx.auth.isContinuousSave === true) {
                top.jx.toastr.success('保存成功');
                window.location.reload();
            } else {
                top.jx.toastr.success('保存成功');
                jx.closeDialog();
                pwin.reloadTreeData();
            }
        });
    },
    /**
     * 格式化文本框的导入消息
     */
    formatBoxImportMessage: function (result) {
        var message = jx.formatString("总数: {0} 成功: {1} 失败: {2}\n", result.total, result.success, result.fail);
        if (result.fail > 0 && result.details && result.details.length > 0) {
            message += "\n详细错误信息如下:";
            $.each(result.details, function (i, v) {
                message += jx.formatString("\n行:{0}\t 列:{1}\t {2}", v.rowIndex + 1, v.columnName, v.msg);
            });
        }
        return message;
    },
    /**
     * 是否存在父节点
     * @param rows
     * @param parentId 父节点 Id
     * @returns {boolean}
     */
    hasParentNode : function (rows, parentId) {
        for (var i = 0; i < rows.length; i++) {
            if (rows[i].id == parentId) return true;
        }
        return false;
    }
};

/**
 * 获取请求上下文绝对路径
 */
jx.url = function (url) {
    if (!this.rootUrl) {
        this.rootUrl = jx.rootPath.substring(0, jx.rootPath.lastIndexOf('/'));
    }
    return this.rootUrl + url;
};

/**
 * bool/数字格式化函数
 */
jx.gf.bool = function (v) {
    if ((jx.isNumber(v) && v === 1) || (jx.isBool(v) && v === true)) {
        return '<i class="fa fa-check"></i>';
    }
    return '<i class="fa fa-close"></i>';
};

/**
 * 状态格式化函数
 */
jx.gf.status = function (v, row, index) {
    var id = row.id;
    var cls = v == '1' ? 'fa-toggle-on' : 'fa-toggle-off';
    return jx.formatString('<i class="fa {0} gridstatus" data-id="{1}" data-val="{2}"></i>', cls, id, v);
};

/**
 * 历史记录操作类型列格式化函数
 */
jx.gf.histroyCategoryText = function (v, row) {
    if (!v) return v;
    var url = jx.apiUrl('/sys/history/details?id=' + row.id);
    var options = jx.formatString("{title: '历史日志详细信息',url: '{0}',width: '50%',height: '100%',shadeClose:true,offset: 'rt',anim: 2}", url);
    return jx.formatString('<a class="jxdialog" data-options="{0}">{1}</a>', options, row.categoryText);
};

/**
 * 文件查看操作类型列格式化函数
 */
jx.gf.fileViewOperateType = function (v, row) {
    if (!v) return v;
    var viewUrl = jx.apiUrl(row.fileUrl);
    var downloadUrl = jx.apiUrl('/sys/file/download/' + row.id);
    return jx.formatString('<a href="{0}" target="_blank">查看</a> &nbsp;<a href="{1}">下载</a>', viewUrl, downloadUrl);
};

/**
 * 文件编辑操作类型列格式化函数
 */
jx.gf.fileEditOperateType = function (v, row) {
    if (!v) return v;
    var viewUrl = jx.apiUrl(row.fileUrl);
    return jx.formatString('<a href="{0}" target="_blank">查看</a> &nbsp;<a href="#" class="griddelete" data-id="{1}">删除</a>', viewUrl, row.id);
};

// //子页面标题全屏组件
// jx.complete(function () {
//     $(document.body).on('click','.jxfs',function () {
//
//     });
// });

jx.__extends = (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({__proto__: []} instanceof Array && function (d, b) {
                d.__proto__ = b;
            }) ||
            function (d, b) {
                for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
            };
        return extendStatics(d, b);
    }
    return function (d, b) {
        extendStatics(d, b);

        function __() {
            this.constructor = d;
        }

        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

/**
 * 编辑界面基本操作类
 * @type {JXEditForm}
 */
var JXEditForm = (function () {
    /**
     * @type {JXEditForm}
     */
    var self;

    /**
     * 构造函数
     * @param _$form 表单对象
     * @param _mode 操作模式:1.对话框模式 2.页面跳转模式 3.标签页模式
     * @constructor
     */
    function JXEditForm(_$form, _mode) {
        this.$form = _$form;
        this.mode = _mode;
        this.isContinuousSave = false;
        this.parentUrl = jx.getUrlParam('_ppurl') || '';
        self = this;
        self.init();
    }

    /**
     * 初始化
     */
    JXEditForm.prototype.init = function () {
        self.$form.onFormSuccess(function (e, result) {
            if (self.isContinuousSave === true) {
                top.jx.toastr.success('保存成功');
                window.location.reload();
            } else {
                top.jx.toastr.success('保存成功');

                switch (self.mode) {
                    case 1://对话框模式
                        jx.closeDialog();
                        if (pwin && pwin.jx.reloadGrid) {
                            pwin.jx.reloadGrid();
                        } else if (pwin && pwin.reloadGridData){
                            pwin.reloadGridData();
                        }
                        break;
                    case 2://页面跳转模式
                        window.location.href = self.parentUrl;
                        break;
                    case 3://标签页模式
                        var page = jx.auth.findParentPage();
                        if (page && page.reloadGridData) {
                            page.reloadGridData(); //刷新表格数据
                        }
                        jx.auth.app.closeCurrentPage(); //关闭标签页
                        break;
                }
            }
        });
    };

    /**
     * 设置父页面地址
     */
    JXEditForm.prototype.setParentUrl = function (_parentUrl) {
        self.parentUrl = _parentUrl;
    };

    /**
     * 设置IFrame内部对话框
     */
    JXEditForm.prototype.setInnerDialog = function () {
        jx.layer = parent.layer;//如果要使用页面内对话框,请启用此代码
    };

    /**
     * 保存
     */
    JXEditForm.prototype.save = function () {
        self.$form.jxform().submit();
    };

    /**
     * 连续保存
     */
    JXEditForm.prototype.continuousSave = function () {
        self.isContinuousSave = true;
        self.save();
    };

    /**
     * 取消
     */
    JXEditForm.prototype.cancel = function () {
        switch (self.mode) {
            case 1://对话框模式
                jx.closeDialog();
                break;
            case 2://页面跳转模式
                window.location.href = self.parentUrl;
                break;
            case 3://标签页模式
                jx.auth.app.closeCurrentPage(); //关闭标签页
                break;
        }
    };

    /**
     * 关闭当前标签页
     */
    JXEditForm.prototype.closeCurrentPage = function () {
        jx.auth.app.closeCurrentPage(); //关闭标签页
    };

    /**
     * 关闭当前对话框
     */
    JXEditForm.prototype.closeCurrentDialog = function () {
        jx.closeDialog();
    };

    return JXEditForm;
}());


/**
 * 数据选择组件
 * @type {JXDataItemSelect}
 */
var JXDataItemSelect = (function () {
    /**
     * @type {JXDataItemSelect}
     */
    var self;

    var $element, $view, $toolbar, $section, $sectionTitle, $hr, $box, $summary,
        $page, $btnSelectAll, $btnInverseAll, $btnCleanAll;

    var clsOps = {maxSection: 500, pageSize: 100};

    var pageIndex = 1, totalCount = 0, pageCount = 0, selecionData = {}, loadOps = {};

    var searchDelay = jx.delay();

    /**
     * 构造函数
     * @constructor
     */
    function JXDataItemSelect(_$element, _ops) {
        $element = _$element;
        $.extend(clsOps, _ops);
        self = this;
        self.init();
        self.initEvent();
    }

    /**
     * 初始化
     */
    JXDataItemSelect.prototype.init = function () {
        $view = $element.find('.map-view');
        $section = $element.find('.map-section');
        $sectionTitle = $element.find('.map-section-title');
        $toolbar = $element.find('.map-toolbar');
        $hr = $element.find('hr');
        $box = $element.find('.map-search-box');
        $summary = $element.find('.map-summary');
        $page = $element.find('.map-page');
        $btnSelectAll = $element.find('.btnSelectAll');
        // $btnInverseAll= $element.find('.btnInverseAll');
        $btnCleanAll = $element.find('.btnCleanAll');
    };

    /**
     * 初始化事件
     */
    JXDataItemSelect.prototype.initEvent = function () {
        $view.on('click', 'li', function () {
            var $this = $(this);
            var id = $this.attr('id');

            if (self.hasSelecion(id)) {
                self.removeSelecion(id);
            } else {
                self.addSelecion(id);
            }
        });

        $btnSelectAll.click(function () {
            self.addAllSelecion();
        });

        $btnCleanAll.click(function () {
            self.cleanSelecion();
        });

        $box.keyup(function () {
            self.filter($(this).val());
        });

        $element.on('click', 'span', function () {
            var id = $(this).attr('id').replace('s-', '');
            self.removeSelecion(id);
            // jx.confirm('你确定要移除吗?', function (ok, index) {
            //     if (ok) {
            //     }
            // });
        });
        $element.on('click', '.first-page', function () {
            self.gotoPage(1);
        });
        $element.on('click', '.previous-page', function () {
            self.gotoPage(pageIndex - 1);
        });
        $element.on('click', '.next-page', function () {
            self.gotoPage(pageIndex + 1);
        });
        $element.on('click', '.last-page', function () {
            self.gotoPage(pageCount);
        });
    };

    /**
     * 数据过滤
     * @param key 关键字
     */
    JXDataItemSelect.prototype.filter = function (key) {
        var _core = function () {
            // var $lis = $view.find('li');
            // if (key == '') {
            //     $lis.show();
            //     if ($lis.length > 0) {
            //         $view.find('.alert').remove();
            //     }
            // } else {
            //     var count = 0;
            //     $lis.each(function () {
            //         var $this = $(this);
            //         if ($this.text().indexOf(key) == -1) {
            //             $this.hide();
            //         } else {
            //             $this.show();
            //             count++;
            //         }
            //     });
            //     if (count == 0) {
            //         $view.find('.alert').remove();
            //         $view.append('<div class="alert alert-warning margin-5 padding-10">没有找到符合条件的数据</div>');
            //     } else {
            //         $view.find('.alert').remove();
            //     }
            // }
            // self.refreshSummary();
            loadOps.params.page = 1;
            loadOps.params.key = key;
            self.load(loadOps);
        };
        searchDelay.run(_core, 250);
    };

    JXDataItemSelect.prototype.refreshSummary = function () {
        var html = '';
        if (pageCount > 0) {
            html += '共 <b class="color-blue mr-5">' + pageCount + '</b>页';
            html += '&nbsp;当前第 <b class="color-blue mr-5">' + pageIndex + '</b>页';
        }

        if (totalCount > 0) {
            html += '&nbsp;&nbsp;&nbsp;&nbsp;车辆总数：<b class="color-red mr-5">' + totalCount + '</b>';
        }
        // var filters = $view.find('li:visible');
        // if (filters && filters.length > 0) {
        //     html += '&nbsp;可见车辆：<b class="color-blue mr-5">' + filters.length + '</b>';
        // }

        var count = $section.find('span').length;
        if (count > 0) {
            html += '&nbsp;已选车辆：<b class="color-green mr-5">' + count + '</b>';
        }
        $summary.html(html);
        if (html == '') {
            $summary.hide();
        } else {
            $summary.show();
        }
    };

    JXDataItemSelect.prototype.addAllSelecion = function () {
        $view.find('li:visible').each(function () {
            if ($section.children().length > clsOps.maxSection) {
                return;
            }
            var id = $(this).attr('id');
            if (!self.hasSelecion(id)) {
                self.addSelecion(id);
            }
        });
        self.statusSelecion();
    };

    JXDataItemSelect.prototype.hasSelecion = function (item) {
        return !!(selecionData[item] && selecionData[item] === 1);
    };

    JXDataItemSelect.prototype.addSelecion = function (item) {
        if ($section.children().length > clsOps.maxSection) {
            jx.alert('选择项不能超过' + clsOps.maxSection + '个');
            return;
        }
        selecionData[item] = 1;
        $section.append('<span id="s-' + item + '" class="label label-success lab-car">' + item + '</span>');
        $('#' + item).addClass('selected');
        self.statusSelecion();
    };

    JXDataItemSelect.prototype.removeSelecion = function (item) {
        selecionData[item] = 0;
        $('#s-' + item).remove();
        $('#' + item).removeClass('selected');
        self.statusSelecion();
    };

    JXDataItemSelect.prototype.cleanSelecion = function () {
        selecionData = {};
        $view.find('li').removeClass('selected');
        $section.empty().hide();
        self.statusSelecion();
    };

    JXDataItemSelect.prototype.getSelecionData = function () {
        var names = Object.getOwnPropertyNames(selecionData);
        var ids = [];
        for (var i = 0; i < names.length; i++) {
            var id = selecionData[names[i]];
            if (id && id === 1) {
                ids.push(names[i]);
            }
        }
        return ids;
    };

    JXDataItemSelect.prototype.statusSelecion = function () {
        if ($section.children().length > 0) {
            $section.show();
            $sectionTitle.show();
        } else {
            $section.hide();
            $sectionTitle.hide();
        }
        self.refreshSummary();
    };

    /**
     * 加载数据
     */
    JXDataItemSelect.prototype.load = function (ops) {
        ops.params = $.extend({rows: clsOps.pageSize}, ops.params);
        if (!ops.params.page) {
            ops.params.page = 1;
        }
        jx.ajax({
            url: ops.url,
            data: ops.params,
            type: ops.method || 'get',
            maskMsg: ops.maskMsg || '正在加载,请稍等...',
            success: function (result) {
                // $box.val('');
                totalCount = result.data.total;
                pageCount = result.data.pageCount;
                pageIndex = result.data.pageIndex;
                loadOps = ops;
                if (totalCount > 0) {
                    $view.empty().append(result.data.html);
                    $section.show();
                    $hr.show();
                } else {
                    $view.empty().append('<div class="alert alert-warning margin-5 padding-10">' + ops.emptyMsg || '无数据' + '</div>');
                    $section.hide();
                    $hr.hide();
                }
                // self.cleanSelecion();
                self.refreshSummary();
                self.buildPagination();
                if (ops.callback) {
                    ops.callback.call(self, result.data);
                }
            }
        });
    };

    JXDataItemSelect.prototype.gotoPage = function (page) {
        loadOps.params.page = page;
        self.load(loadOps);
    };

    JXDataItemSelect.prototype.buildPagination = function () {
        if (pageCount <= 1) {
            $page.hide();
            $page.empty();
            return;
        }

        var html = '<ul class="pagination" style="margin: 0">';
        if (pageIndex == 1) {
            html += '<li class="disabled"><a>首页</a></li><li class="disabled"><a>上一页</a></li>';
        } else {
            html += '<li><a class="first-page">首页</a></li><li><a class="previous-page">上一页</a></li>';
        }
        if (pageIndex == pageCount) {
            html += '<li class="disabled"><a>下一页</a></li><li class="disabled"><a>末页</a></li>';
        } else {
            html += '<li><a class="next-page">下一页</a></li><li><a class="last-page">末页</a></li>';
        }
        html += '</ul>';
        $page.empty().append(html);
        $page.show();
    };

    return JXDataItemSelect;
}());


/**
 * 表单验证规则扩展
 */
(function ($) {

    function isLicensePlateNumber(str) {
        return /(^(([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z](([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z][A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳使领]))$)/.test(str);
    }

    /**
     * 车牌号码验证
     */
    $.validator.methods.carNumber = function (value, element, param) {
        return this.optional(element) || isLicensePlateNumber(value);
    };

    //设置验证规则错误消息默认值
    $.extend($.validator.messages, {
        carNumber: '请输入正确的车牌号码'
    });


})(jQuery);


//region 导出组件

/**
 * 导出组件
 */

$(document).on('click', '.jxexport', function (e) {
    jx.stope(e);
    var ops = jx.parseOptions($(this), ['url', {param: 'jquery'}]);
    jx.export(ops.url, ops.param);
});
//endregion


//region 历史日志组件

jx.historyLogGridUrl = '/sys/historyLog/grid';
jx.historyLogDetailsUrl = '/sys/historyLog/details';

//格式化列操作类型
jx.gf.formatHistoryLogCategory = function (v, row) {
    //[1-新增, 2-修改, 3-删除]
    var cls = 'default';
    if (v == '1') {
        cls = 'success';
    } else if (v == '2') {
        cls = 'primary';
    } else if (v == '3') {
        cls = 'danger';
    }
    return '<span class="label label-' + cls + '">' + row.categoryName + '</span>';
}

//格式化列表名
jx.gf.formatHistoryLogName = function (v, row) {
    return jx.formatString('<a class="cmd-details">{0}</a>', v);
}

//查看历史日志详情
jx.showHistoryLogDetails = function (id) {
    jx.dialog({
        title: '历史日志详细信息',
        url: jx.url(jx.historyLogDetailsUrl),
        maxmin: true,
        shadeClose: true,
        offset: 'rt',
        anim: 2,
        width: '70%',
        height: '100%',
        params: {id: id}
    });
}

//endregion