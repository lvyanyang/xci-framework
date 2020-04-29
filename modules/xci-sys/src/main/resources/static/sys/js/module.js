/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

/*-----------------------------------------------------
 * 权限子系统-系统模块模块
 * ---------------------------------------------------*/
jx.ready(function () {
    //region 私有变量
    var api = {
        grid: '/sys/module/grid',
        create: '/sys/module/create',
        edit: '/sys/module/edit',
        delete: '/sys/module/delete',
        details: '/sys/module/details',
        copy: '/sys/module/copy',
        status: '/sys/module/status',
        expand: '/sys/module/expand',
        dnd: '/sys/module/dnd'
    };
    //定义变量
    var gridInstance;
    var dialogWidth = '1100px';
    var dialogHeight = '600px';
    //endregion

    //region 公共方法

    /**
     * 展开状态格式化函数
     */
    jx.gf.expandStatus = function (v, row, index) {
        var id = row.id;
        var cls = v == '1' ? 'fa-toggle-on' : 'fa-toggle-off';
        return jx.formatString('<i class="fa {0} grid-expand-status" data-id="{1}" data-val="{2}"></i>', cls, id, v);
    };

    //重新加载表格
    jx.reloadGrid = function () {
        gridInstance.reloadGridData();
    }

    //endregion

    //region 私有方法

    //初始化表格
    var initGrid = function () {
        gridInstance = $('#grid').jxtreegrid({
            url: jx.url(api.grid),
            dndUrl: jx.url(api.dnd),
            rowStyler: function (row) {
                if (row && !row.status) {
                    return 'color:#d9534f;';
                }
            },
            onDblClickRow: function (row) {
                detailsData(row);
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

    //初始化事件
    var bindEvent = function () {
        $('#btn-create,#btn-cmcreate').click(function () {
            createData();
        });
        $('#btn-edit,#btn-cmedit').click(function () {
            editData();
        });
        $('#btn-delete,#btn-cmdelete').click(function () {
            deleteData();
        });
        $('#btn-cmcopy').click(function () {
            var id = gridInstance.getSelectedRowId();
            copy(id);
        });

        // 表格事件
        var panel = gridInstance.getPanel();
        panel.on('click', '.gridstatus', function () {
            var id = $(this).data('id');
            var val = $(this).data('val');
            setStatus(id, val);
        });
        panel.on('click', '.grid-expand-status', function () {
            var id = $(this).data('id');
            var val = $(this).data('val');
            setExpandStatus(id, val);
        });
    };

    //新增数据
    var createData = function () {
        var parentId = gridInstance.getSelectedRowId() || 0;
        jx.dialog({
            title: '新增模块',
            url: jx.url(api.create),
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
            url: jx.url(api.edit),
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
            confirm: '注：您确定要删除当前选中节点吗？',
            url: jx.url(api.delete),
            data: {id: id},
            success: function (result) {
                gridInstance.reloadGridData();
            }
        });
    };

    //查看数据
    var detailsData = function (row) {
        jx.auth.detailsData(gridInstance, row, {
            url: jx.url(api.details),
            title: '查看系统模块'
        });
    }

    //复制数据
    var copy = function (id) {
        jx.ajax({
            url: jx.url(api.copy),
            data: {id: id},
            maskMsg: '正在复制,请稍等...',
            success: function () {
                gridInstance.reloadGridData();
                jx.toastr.success('复制成功');
            }
        });
    };

    //设置数据状态
    var setStatus = function (id, val) {
        var status = val == '1' ? 0 : 1;
        jx.ajax({
            url: jx.url(api.status),
            data: {id: id, status: status},
            maskMsg: '正在更新状态,请稍等...',
            success: function (result) {
                gridInstance.reloadGridData();
                jx.toastr.success('状态更新成功');
            }
        });
    };

    //设置数据展开状态
    var setExpandStatus = function (id, val) {
        var status = val == '1' ? 0 : 1;
        jx.ajax({
            url: jx.url(api.expand),
            data: {id: id, expandStatus: status},
            maskMsg: '正在更新展开状态,请稍等...',
            success: function () {
                gridInstance.reloadGridData();
                jx.toastr.success('展开状态更新成功');
            }
        });
    };

    //endregion

    //region 模块初始化

    initGrid();
    bindEvent();

    //endregion
});