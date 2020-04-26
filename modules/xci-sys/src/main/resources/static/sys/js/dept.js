/*-----------------------------------------------------
 * 权限子系统-系统组织机构模块
 * ---------------------------------------------------*/
jx.ready(function () {
    //region 私有变量
    var api = {
        grid: '/sys/dept/grid',
        create: '/sys/dept/create',
        edit: '/sys/dept/edit',
        delete: '/sys/dept/delete',
        details: '/sys/dept/details',
        status: '/sys/dept/status',
        dnd: '/sys/dept/dnd'
    };
    //定义变量
    var gridInstance;
    var dialogWidth = '40%';
    var dialogHeight = '580px';
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
            onDblClickRow: function (row) {
                jx.auth.showDepartmentDetails(gridInstance.getRowId(row));
            },
            onLoadSuccess: function () {
                gridInstance.getDataBodyPanel().contextmenu({target: '#gridcmenu'});
            },
            onContextMenu: function (e, row) {
                if (row) {
                    var id = gridInstance.getRowId(row);
                    gridInstance.select(id);
                }
            },
            loadFilter:function (data) {
                data && data.rows && data.rows.forEach(function (v) {
                    if (v._parentId) {
                        if(!jx.auth.hasParentNode(data.rows,v.parentId)){
                            v._parentId = null;
                        }
                    }
                });
                return data;
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
        $('#btn-export').click(function () {
            exportData();
        });

        // 表格事件
        gridInstance.getPanel().on('click', '.gridstatus', function () {
            var id = $(this).data('id');
            var val = $(this).data('val');
            setStatus(id, val);
        });
    };

    //新增数据
    var createData = function () {
        var parentId = gridInstance.getSelectedRowId() || 0;
        jx.dialog({
            title: '新增组织机构',
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
            title: '修改组织机构',
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

    //endregion

    //region 模块初始化

    initGrid();
    bindEvent();

    //endregion
});