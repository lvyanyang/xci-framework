/*-----------------------------------------------------
 * 系统序列模块
 * ---------------------------------------------------*/
jx.ready(function () {
    //region 私有变量

    var api = {
        grid: '/sys/seq/grid',
        create: '/sys/seq/create',
        edit: '/sys/seq/edit',
        delete: '/sys/seq/delete',
        details: '/sys/seq/details'
    };
    var gridInstance;
    var dialogWidth = '50%'
    var dialogHeight = '400px';

    //endregion

    //region 公共方法

    //重新加载表格
    jx.reloadGrid = function () {
        gridInstance.reloadGridData();
    }

    //格式化列名称
    jx.gf.formatName = function (v, row) {
        return jx.formatString('<a class="cmd-details">{0}</a>', v);
    }

    //endregion

    //region 私有方法

    //初始化表格
    var initGrid = function () {
        gridInstance = $('#grid').jxgrid({
            url: jx.url(api.grid),
            onDblClickRow: function (index, row) {
                detailsData(row);
            },
            onRowContextMenu: function (e, index, row) {
                jx.auth.gridContextMenuHandle(gridInstance, e, index, row)
            },
            onLoadSuccess: function () {
            }
        });
    }

    //初始化事件
    var bindEvent = function () {
        $('#btn-create').click(function () {
            createData();
        });
        $('#btn-edit').click(function () {
            editData();
        });
        $('#btn-delete').click(function () {
            deleteData();
        });

        //数据行详情事件
        gridInstance.getPanel().on('click', '.cmd-details', function () {
            detailsData(gridInstance.getSelected());
        });
    }

    //新增数据
    var createData = function () {
        jx.dialog({
            url: jx.url(api.create),
            title: '新增系统序列',
            width: dialogWidth,
            height: dialogHeight
        });
    }

    //编辑数据
    var editData = function () {
        jx.auth.editData(gridInstance, {
            url: jx.url(api.edit),
            title: '修改系统序列',
            width: dialogWidth,
            height: dialogHeight
        });
    }

    //删除数据
    var deleteData = function () {
        jx.auth.deleteData(gridInstance, {
            url: jx.url(api.delete)
        });
    }

    //查看数据
    var detailsData = function (row) {
        jx.auth.detailsData(gridInstance, row, {
            url: jx.url(api.details),
            title: '查看系统序列'
        });
    }

    //endregion

    //region 模块初始化

    initGrid();
    bindEvent();

    //endregion
})