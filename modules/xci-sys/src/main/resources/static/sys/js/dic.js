/*-----------------------------------------------------
 * 系统字典模块
 * ---------------------------------------------------*/
jx.ready(function () {
    //region 私有变量

    var api = {
        dic: {
            grid: '/sys/dic/grid',
            create: '/sys/dic/create',
            edit: '/sys/dic/edit',
            delete: '/sys/dic/delete',
            details: '/sys/dic/details',
            status: '/sys/dic/status',
            export: '/sys/dic/export',
            dnd: '/sys/dic/dnd'
        },
        category: {
            tree: '/sys/dicCategory/tree',
            create: '/sys/dicCategory/create',
            edit: '/sys/dicCategory/edit',
            delete: '/sys/dicCategory/delete',
            details: '/sys/dicCategory/details',
            export: '/sys/dicCategory/export',
            dnd: '/sys/dicCategory/dnd'
        }
    };
    var $tree = $('#tree'), $grid = $('#grid');
    var treeInstance, gridInstance, lastSelectedId;

    var treeDialogWidth = '600px';
    var treeDialogHeight = '360px';
    var gridDialogWidth = '600px';
    var gridDialogHeight = '480px';

    //endregion

    //region 公共方法

    //重新加载树
    jx.reloadTree = function () {
        treeInstance.reload();
    }

    //重新加载表格
    jx.reloadGrid = function () {
        gridInstance.reloadGridData();
    }

    //endregion

    //region 私有方法

    //初始化树控件
    var initTree = function () {
        //树控件初始化参数
        treeInstance = $tree.jxtree({
            url: jx.url(api.category.tree),
            dndUrl: jx.url(api.category.dnd),
            onContextMenu: function (e, node) {
                treeInstance.expand(node.target);
                treeInstance.select(node.target);
            },
            onDblClick: function (node) {
                treeDetails(node.id);
            },
            onSelect: function (node) {
                if (lastSelectedId === node.id) {
                    return;
                }
                lastSelectedId = node.id;
                gridInstance.reloadGridData();
                //设置右边区域标题
                $(document.body).layout('panel', 'center').panel('setTitle', node.text);
            },
            onLoadSuccess: function () {
                $tree.contextmenu({target: '#treecmenu'});
                $tree.parent().contextmenu({target: '#treecmenu'});

                var node = treeInstance.find(lastSelectedId);
                if (!node) {
                    var roots = treeInstance.getRoots();
                    if (roots.length > 0) {
                        node = roots[0];
                    }
                }
                if (node) {
                    treeInstance.expandTo(node.target);
                    treeInstance.expand(node.target);
                    treeInstance.select(node.target);
                    treeInstance.scrollTo(node.target);
                }
            }
        });
    };

    //初始化树控件事件
    var initTreeEvent = function () {
        $('#btn-tcmcreate').click(function () {
            var parentId = treeInstance.getSelected().id || 0;
            jx.dialog({
                title: '新增字典类型',
                url: jx.url(api.category.create),
                params: {parentId: parentId},
                width: treeDialogWidth,
                height: treeDialogHeight
            });
        });

        $('#btn-tcmedit').click(function () {
            if (!treeInstance.getSelected()) return;
            var id = treeInstance.getSelected().id;
            jx.dialog({
                title: '修改字典类型',
                url: jx.url(api.category.edit),
                params: {id: id},
                width: treeDialogWidth,
                height: treeDialogHeight
            });
        });

        $('#btn-tcmdelete').click(function () {
            if (!treeInstance.getSelected()) return;
            var id = treeInstance.getSelected().id;
            jx.delete({
                confirm: '注：您确定要删除当前选中节点吗？',
                url: jx.url(api.category.delete),
                data: {id: id},
                success: function () {
                    treeInstance.reload();
                }
            });
        });

        $('#btn-tcmdetails').click(function () {
            var id = treeInstance.getSelected().id;
            treeDetails(id);
        });

        $('#btn-tcmexport').click(function () {
            jx.export(jx.url(api.category.export));
        });
    };

    //初始化表格
    var initGrid = function () {
        //表格控件初始化参数
        gridInstance = $grid.jxtreegrid({
            url: jx.url(api.dic.grid),
            dndUrl: jx.url(api.dic.dnd),
            onBeforeLoad: function (row, param) {
                var item = treeInstance.getSelected();
                if (!item) return false;

                param.categoryCode = item.code;
                return true;
            },
            onDblClickRow: function (row) {
                jx.auth.detailsData(gridInstance, row, {
                    url: jx.url(api.dic.details),
                    title: '查看系统字典'
                });
            },
            onLoadSuccess: function () {
                gridInstance.getDataBodyPanel().contextmenu({target: '#gridcmenu'});
            },
            onContextMenu: function (e, row) {
                if (row) {
                    var id = gridInstance.getRowId(row);
                    gridInstance.select(id);
                }
            }
        });
    };

    //初始化表格事件
    var initGridEvent = function () {
        $('#btn-create,#btn-cmcreate').click(function () {
            if (!treeInstance.getSelected()) {
                jx.toastr.error('请先选择字典类型');
                return;
            }
            var categoryCode = treeInstance.getSelected().code;
            var parentId = gridInstance.getSelectedRowId() || 0;
            jx.dialog({
                title: '新增字典',
                url: jx.url(api.dic.create),
                params: {categoryCode: categoryCode, parentId: parentId},
                width: gridDialogWidth,
                height: gridDialogHeight
            });
        });
        $('#btn-edit,#btn-cmedit').click(function () {
            if (!gridInstance.hasSelectedRow()) return;
            var id = gridInstance.getSelectedRowId();
            jx.dialog({
                title: '修改字典',
                url: jx.url(api.dic.edit),
                params: {id: id},
                width: gridDialogWidth,
                height: gridDialogHeight
            });
        });
        $('#btn-delete,#btn-cmdelete').click(function () {
            if (!gridInstance.hasSelectedRow()) return;
            var id = gridInstance.getSelectedRowId();
            jx.delete({
                confirm: '注：您确定要删除当前选中节点吗？',
                url: jx.url(api.dic.delete),
                data: {id: id},
                success: function () {
                    gridInstance.reloadGridData();
                }
            });
        });
        $('#btn-export').click(function () {
            if (!treeInstance.getSelected()) {
                jx.toastr.error('请先选择字典类型');
                return;
            }
            var code = treeInstance.getSelected().code;
            jx.export(jx.url(api.dic.export), $.extend(jx.serialize($('#gridform')), {categoryCode: code}));
        });

        gridInstance.getPanel().on('click', '.gridstatus', function () {
            var id = $(this).data('id');
            var val = $(this).data('val');
            var status = val == '1' ? 0 : 1;
            jx.ajax({
                url: jx.url(api.dic.status),
                data: {id: id, status: status},
                maskMsg: '正在更新状态,请稍等...',
                success: function () {
                    gridInstance.reloadGridData();
                    jx.toastr.success('状态更新成功');
                }
            });
        });
    };

    function treeDetails(id) {
        jx.detailsDialog({
            title: '查看字典类型',
            url: jx.url(api.category.details),
            params: {id: id}
        });
    }

    //endregion

    //region 模块初始化

    initTree();
    initTreeEvent();
    initGrid();
    initGridEvent();

    //endregion
});

jx.complete(function () {
    jx.monitorLayoutPanel($(document.body), 'west', 'xci-sysdic');
});