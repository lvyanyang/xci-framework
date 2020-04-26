/*-----------------------------------------------------
 * 锁定用户模块
 * ---------------------------------------------------*/
jx.sys.LockUser = function (ops) {
    //region 私有变量

    var api = {
        grid: '/sys/lockUser/grid',
        remove: '/sys/lockUser/remove',
        details: '/sys/lockUser/details'
    };
    var gridInstance;

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
        var $grid = $('#grid');
        gridInstance = $grid.jxgrid({
            url: jx.url(api.grid),
            onDblClickRow: function (index, row) {
                detailsData(row);
            }
        });
        $grid.datagrid('getPager').pagination({
            layout: ['info'],
            displayMsg: '共{total}条记录'
        });
    }

    //初始化事件
    var bindEvent = function () {
        $('#btn-remove').click(function () {
            removeData();
        });
    }

    //移除
    var removeData = function () {
        if (!gridInstance.hasCheckedRow()) return;
        var ids = gridInstance.getCheckedRowIds();
        jx.ajax({
            url: jx.url(api.remove),
            data: {ids: ids.join()},
            confirm: '注：您确定要移除吗？',
            maskMsg: '正在移除,请稍等...',
            success: function (result) {
                gridInstance.reloadGridData();
                jx.toastr.success('移除成功');
            }
        });
    };

    //查看数据
    var detailsData = function (row) {
        jx.auth.detailsData(gridInstance, row, {
            url: jx.url(api.details),
            title: '查看锁定用户'
        });
    }

    /**
     * 模块初始化
     * @private
     */
    var init = function () {
        initGrid();
        bindEvent();
    }

    //endregion

    //region 模块初始化
    init();
    //endregion
}