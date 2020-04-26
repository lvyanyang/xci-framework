/*-----------------------------------------------------
 * 权限子系统-系统模块
 * ---------------------------------------------------*/
jx.ready(function () {
    window.api = {
        grid: '/sys/module/grid',
        create: '/sys/module/create',
        edit: '/sys/module/edit',
        delete: '/sys/module/delete',
        details: '/sys/module/details',
        export: '/sys/module/export',
        status: '/sys/module/status',
        dnd: '/sys/module/dnd'
    }
    //定义变量
    var $grid = $('#grid'), $gridPanel, gridInstance;
    var dialogWidth = $grid.data('dialogWidth');
    var dialogHeight = $grid.data('dialogHeight');
    var gridUrl = jx.apiUrl(api.grid);
    var createUrl = jx.apiUrl(api.create);
    var editUrl = jx.apiUrl(api.edit);
    var deleteUrl = jx.apiUrl(api.delete);
    var detailsUrl = jx.apiUrl(api.details);
    var exportUrl = jx.apiUrl(api.export);
    var statusUrl = jx.apiUrl(api.status);
    var dndUrl = jx.apiUrl(api.dnd);

    //初始化表格
    var initGrid = function () {
        gridInstance = $grid.jxtreegrid({
            url: gridUrl,
            dndUrl: dndUrl,
            onDblClickRow: function (row) {
                var id = gridInstance.getRowId(row);
                jx.detailsDialog({
                    title: '查看模块详细信息',
                    url: detailsUrl,
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

    //初始化事件
    var initEvent = function () {
        $('#btn-createroot,#btn-cmcreateroot').click(function () {
            createRootData();
        });
        $('#btn-create,#btn-cmcreate').click(function () {
            createData();
        });
        $('#btn-edit,#btn-cmedit').click(function () {
            editData();
        });
        $('#btn-delete,#btn-cmdelete').click(function () {
            deleteData();
        });
        $('#btn-export').click(function () {
            exportData();
        });

        // $('#foldingStatus').click(function () {
        //     var checked = $(this).prop('checked');
        //     if (checked) {
        //         gridInstance.expandAll();
        //     }
        //     else {
        //         gridInstance.collapseAll();
        //     }
        // });

        // 表格事件
        $gridPanel.on('click', '.gridstatus', function () {
            var id = $(this).data('id');
            var val = $(this).data('val');
            setStatus(id, val);
        });
    };

    //新增根节点数据
    var createRootData = function () {
        jx.dialog({
            title: '新增根模块',
            url: createUrl,
            width: dialogWidth,
            height: dialogHeight
        });
    };

    //新增数据
    var createData = function () {
        var parentId = gridInstance.getSelectedRowId() || 0;
        jx.dialog({
            title: '新增模块',
            url: createUrl,
            params: {parentId: parentId},
            width: dialogWidth,
            height: dialogHeight
        });
    };

    //编辑数据
    var editData = function () {
        if (!gridInstance.hasSelectedRow()) return;
        var id = gridInstance.getSelectedRowId();
        jx.dialog({
            title: '修改模块',
            url: editUrl,
            params: {id: id},
            width: dialogWidth,
            height: dialogHeight
        });
    };

    //删除数据
    var deleteData = function () {
        if (!gridInstance.hasSelectedRow()) return;
        var id = gridInstance.getSelectedRowId();
        jx.delete({
            confirm: '注：如果有子节点则都会被删除，并且无法撤销，您确定要删除吗？',
            url: deleteUrl,
            data: {id: id},
            success: function (result) {
                gridInstance.reloadGridData();
            }
        });
    };

    //导出数据
    var exportData = function () {
        jx.auth.exportData(exportUrl, jx.serialize($('#gridform')));
    };

    //设置数据启用状态
    var setStatus = function (id, val) {
        var status = val == '1' ? 0 : 1;
        jx.ajax({
            url: statusUrl,
            data: {id: id, status: status},
            maskMsg: '正在更新状态,请稍等...',
            success: function (result) {
                gridInstance.reloadGridData();
                jx.toastr.success('状态更新成功');
            }
        });
    };

    //定义表格列格式化函数

    //对外接口
    //重新加载表格数据
    window.reloadGridData = function () {
        gridInstance.reloadGridData();
    };

    //初始化
    initGrid();
    initEvent();
});