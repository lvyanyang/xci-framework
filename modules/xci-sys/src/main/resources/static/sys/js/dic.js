/*-----------------------------------------------------
 * 权限子系统-系统字典模块
 * ---------------------------------------------------*/
jx.ready(function () {
    window.api = {
        dic: {
            tree: '/sys/dicCategory/tree',
            create: '/sys/dicCategory/create',
            edit: '/sys/dicCategory/edit',
            delete: '/sys/dicCategory/delete',
            details: '/sys/dicCategory/details',
            export: '/sys/dicCategory/export',
            dnd: '/sys/dicCategory/dnd'
        },
        dicDetails: {
            grid: '/sys/dic/grid',
            create: '/sys/dic/create',
            edit: '/sys/dic/edit',
            delete: '/sys/dic/delete',
            details: '/sys/dic/details',
            status: '/sys/dic/status',
            export: '/sys/dic/export',
            dnd: '/sys/dic/dnd'
        }
    };
    //定义变量
    var $tree = $('#tree'), $grid = $('#grid');
    var $gridPanel, treeInstance, gridInstance, lastSelectedId;

    var treeDialogWidth = $tree.data('dialogWidth');
    var treeDialogHeight = $tree.data('dialogHeight');
    var gridDialogWidth = $grid.data('dialogWidth');
    var gridDialogHeight = $grid.data('dialogHeight');

    var treeUrl = jx.apiUrl(api.dic.tree);
    var treeCreateUrl = jx.apiUrl(api.dic.create);
    var treeEditUrl = jx.apiUrl(api.dic.edit);
    var treeDeleteUrl = jx.apiUrl(api.dic.delete);
    var treeDetailsUrl = jx.apiUrl(api.dic.details);
    var treeExportUrl = jx.apiUrl(api.dic.export);
    var treeDndUrl = jx.apiUrl(api.dic.dnd);

    var gridUrl = jx.apiUrl(api.dicDetails.grid);
    var gridCreateUrl = jx.apiUrl(api.dicDetails.create);
    var gridEditUrl = jx.apiUrl(api.dicDetails.edit);
    var gridDeleteUrl = jx.apiUrl(api.dicDetails.delete);
    var gridStatusUrl = jx.apiUrl(api.dicDetails.status);
    var gridDetailsUrl = jx.apiUrl(api.dicDetails.details);
    var gridExportUrl = jx.apiUrl(api.dicDetails.export);
    var gridDndUrl = jx.apiUrl(api.dicDetails.dnd);


    //初始化树控件
    var initTree = function () {
        //树控件初始化参数
        treeInstance = $tree.jxtree({
            url: treeUrl,
            dndUrl: treeDndUrl,
            onContextMenu: function (e, node) {
                treeInstance.expand(node.target);
                treeInstance.select(node.target);
            },
            onSelect: function (node) {
                if (lastSelectedId === node.id) {
                    return;
                }
                lastSelectedId = node.id;
                gridInstance.reloadGridData();
                //设置右边区域标题
                $(document.body).layout('panel','center').panel('setTitle',node.text);
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
        $('#btn-tcmcreateroot').click(function () {
            jx.dialog({
                title: '新增根字典',
                url: treeCreateUrl,
                width: treeDialogWidth,
                height: treeDialogHeight
            });
        });

        $('#btn-tcmcreate').click(function () {
            var parentId = treeInstance.getSelected().id || 0;
            jx.dialog({
                title: '新增字典',
                url: treeCreateUrl,
                params: {parentId: parentId},
                width: treeDialogWidth,
                height: treeDialogHeight
            });
        });

        $('#btn-tcmedit').click(function () {
            if (!treeInstance.getSelected()) return;
            var id = treeInstance.getSelected().id;
            jx.dialog({
                title: '修改字典',
                url: treeEditUrl,
                params: {id: id},
                width: treeDialogWidth,
                height: treeDialogHeight
            });
        });

        $('#btn-tcmdelete').click(function () {
            if (!treeInstance.getSelected()) return;
            var id = treeInstance.getSelected().id;
            jx.delete({
                confirm: '注：如果有子节点则都会被删除，并且无法撤销，您确定要删除吗？',
                url: treeDeleteUrl,
                data: {id: id},
                success: function (result) {
                    treeInstance.reload();
                }
            });
        });

        $('#btn-tcmdetails').click(function () {
            var id = treeInstance.getSelected().id;
            jx.detailsDialog({
                title: '查看字典详细信息',
                url: treeDetailsUrl,
                params: {id: id}
            });
        });

        $('#btn-tcmexport').click(function () {
            window.location.href = treeExportUrl;
        });
    };

    //初始化表格
    var initGrid = function () {
        //表格控件初始化参数
        gridInstance = $grid.jxtreegrid({
            url: gridUrl,
            dndUrl: gridDndUrl,
            onBeforeLoad: function (row, param) {
                var item = treeInstance.getSelected();
                if (!item) return false;

                param.dicId = item.id;
                return true;
            },
            onDblClickRow: function (row) {
                var id = gridInstance.getRowId(row);
                jx.detailsDialog({
                    title: '查看字典明细详细信息',
                    url: gridDetailsUrl,
                    params: {id: id}
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
        $gridPanel = gridInstance.getPanel();
    };

    //初始化表格事件
    var initGridEvent = function () {
        $('#btn-createroot,#btn-cmcreateroot').click(function () {
            if (!treeInstance.getSelected()) {
                jx.toastr.error('请先选择字典');
                return;
            }
            var dicId = treeInstance.getSelected().id;
            jx.dialog({
                title: '新增根字典明细',
                url: gridCreateUrl,
                params: {dicId: dicId},
                width: gridDialogWidth,
                height: gridDialogHeight
            });
        });
        $('#btn-create,#btn-cmcreate').click(function () {
            if (!treeInstance.getSelected()) {
                jx.toastr.error('请先选择字典');
                return;
            }
            var dicId = treeInstance.getSelected().id;
            var parentId = gridInstance.getSelectedRowId() || 0;
            jx.dialog({
                title: '新增字典明细',
                url: gridCreateUrl,
                params: {dicId: dicId, parentId: parentId},
                width: gridDialogWidth,
                height: gridDialogHeight
            });
        });
        $('#btn-edit,#btn-cmedit').click(function () {
            if (!gridInstance.hasSelectedRow()) return;
            var id = gridInstance.getSelectedRowId();
            jx.dialog({
                title: '修改字典明细',
                url: gridEditUrl,
                params: {id: id},
                width: gridDialogWidth,
                height: gridDialogHeight
            });
        });
        $('#btn-delete,#btn-cmdelete').click(function () {
            if (!gridInstance.hasSelectedRow()) return;
            var id = gridInstance.getSelectedRowId();
            jx.delete({
                confirm: '注：如果有子节点则都会被删除，并且无法撤销，您确定要删除吗？',
                url: gridDeleteUrl,
                data: {id: id},
                success: function (result) {
                    gridInstance.reloadGridData();
                }
            });
        });
        $('#btn-export').click(function () {
            exportData();
        });

        $gridPanel.on('click', '.gridstatus', function () {
            var id = $(this).data('id');
            var val = $(this).data('val');
            setStatus(id, val);
        });
    };

    //导出数据
    var exportData = function () {
        if (!treeInstance.getSelected()) {
            jx.toastr.error('请先选择字典');
            return;
        }
        var dicId = treeInstance.getSelected().id;
        jx.auth.exportData(gridExportUrl, $.extend(jx.serialize($('#gridform')), {dicId: dicId}));
    };

    //设置数据状态
    var setStatus = function (id, val) {
        var status = val == '1' ? 0 : 1;
        jx.ajax({
            url: gridStatusUrl,
            data: {id: id, status: status},
            maskMsg: '正在更新状态,请稍等...',
            success: function (result) {
                gridInstance.reloadGridData();
                jx.toastr.success('状态更新成功');
            }
        });
    };

    //对外接口
    //刷新树控件数据
    window.reloadTreeData = function () {
        treeInstance.reload();
    };
    //刷新表格数据
    window.reloadGridData = function () {
        gridInstance.reloadGridData();
    };

    //初始化
    initTree();
    initTreeEvent();
    initGrid();
    initGridEvent();
});
jx.complete(function () {
    jx.monitorLayoutPanel($(document.body), 'west', 'spring-sysdic');
});