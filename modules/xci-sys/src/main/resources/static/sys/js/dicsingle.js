/*-----------------------------------------------------
 * 系统字典模块----单个字典管理
 * ---------------------------------------------------*/
var categoryCode = document.currentScript.dataset.categoryCode || '';
jx.ready(function () {
    //region 私有变量

    var api = {
        grid: '/sys/dic/grid',
        create: '/sys/dic/create',
        edit: '/sys/dic/edit',
        delete: '/sys/dic/delete',
        details: '/sys/dic/details',
        export: '/sys/dic/export',
        status: '/sys/dic/status',
        dnd: '/sys/dic/dnd'
    };
    var gridInstance;
    var dialogWidth = '600px'
    var dialogHeight = '460px';

    //endregion

    //region 公共方法

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
            onBeforeLoad: function (row, param) {
                param.categoryCode = categoryCode;
                return true;
            },
            onDblClickRow: function (row) {
                jx.auth.detailsData(gridInstance, row, {
                    url: jx.url(api.details),
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
    }

    //初始化事件
    var bindEvent = function () {
        $('#btn-create,#btn-cmcreate').click(function () {
            var parentId = gridInstance.getSelectedRowId() || 0;
            jx.dialog({
                title: '新增字典',
                url: jx.url(api.create),
                params: {categoryCode: categoryCode, parentId: parentId},
                width: dialogWidth,
                height: dialogHeight
            });
        });
        $('#btn-edit,#btn-cmedit').click(function () {
            if (!gridInstance.hasSelectedRow()) return;
            var id = gridInstance.getSelectedRowId();
            jx.dialog({
                title: '修改字典',
                url: jx.url(api.edit),
                params: {id: id},
                width: dialogWidth,
                height: dialogHeight
            });
        });
        $('#btn-delete,#btn-cmdelete').click(function () {
            if (!gridInstance.hasSelectedRow()) return;
            var id = gridInstance.getSelectedRowId();
            jx.delete({
                confirm: '注：您确定要删除当前选中节点吗？',
                url: jx.url(api.delete),
                data: {id: id},
                success: function () {
                    gridInstance.reloadGridData();
                }
            });
        });

        $('#btn-export').click(function () {
            jx.export(jx.url(api.export), $.extend(jx.serialize($('#gridform')), {categoryCode: categoryCode}));
        });

        gridInstance.getPanel().on('click', '.gridstatus', function () {
            var id = $(this).data('id');
            var val = $(this).data('val');
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
        });
    }

    //endregion

    //region 模块初始化

    initGrid();
    bindEvent();

    //endregion
})