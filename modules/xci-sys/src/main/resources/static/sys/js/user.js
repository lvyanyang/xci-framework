/*-----------------------------------------------------
 * 权限子系统-系统用户模块
 * ---------------------------------------------------*/
jx.ready(function () {
    window.api = {
        grid:'/sys/user/grid',
        create: '/sys/user/create',
        edit: '/sys/user/edit',
        delete: '/sys/user/delete',
        details: '/sys/user/details',
        export: '/sys/user/export',
        status:'/sys/user/status',
        authorize: '/sys/objectmap/setting',
        map:'/sys/urmap/user'
    };
    //定义变量
    var $grid = $('#grid'), $gridPanel, gridInstance;
    var $revisePasswordPopover = $('#revise-passwordpopover');
    var $revisePasswordForm = $('#revise-passwordform');

    var dialogWidth = $grid.data('dialogWidth');
    var dialogHeight = $grid.data('dialogHeight');
    var gridUrl = jx.apiUrl(api.grid);
    var createUrl = jx.apiUrl(api.create);
    var editUrl = jx.apiUrl(api.edit);
    var deleteUrl = jx.apiUrl(api.delete);
    var detailsUrl = jx.apiUrl(api.details);
    var exportUrl = jx.apiUrl(api.export);
    var statusUrl = jx.apiUrl(api.status);
    var authorizeUrl = jx.apiUrl(api.authorize);
    var mapUrl = jx.apiUrl(api.map);

    //初始化表格
    var initGrid = function () {
        gridInstance = $grid.jxgrid({
            url: gridUrl,
            onDblClickRow: jx.auth.onUserGridDblClickRow
        });
        $gridPanel = gridInstance.getPanel();
    };

    //初始化事件
    var initEvent = function () {
        $('#btn-create').click(function () {
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
        // $('#btn-map-role').click(function () {
        //     map();
        // });
        // $('#btn-authorize').click(function () {
        //     authorize();
        // });

        //密码重置提示
        $revisePasswordForm.onFormSuccess(function (e, result) {
            $revisePasswordPopover.popoverX('hide');
            $revisePasswordForm.jxform().reset();
            jx.toastr.success('用户密码重置成功');
        });
        $revisePasswordPopover.on('shown.bs.modal', function () {
            if (!gridInstance.hasCheckedRow()) {
                $revisePasswordPopover.popoverX('hide');
                return;
            }
            var ids = gridInstance.getCheckedRowIds();
            $('#revise-ids').val(ids.join());
        });

        // 表格事件
        $gridPanel.on('click', '.gridstatus', function () {
            var id = $(this).data('id');
            var val = $(this).data('val');
            setStatus(id, val);
        });
        //编辑事件
        $gridPanel.on('click', '.cmd-edit', function () {
            editData($(this).data('id'));
        });
        //删除事件
        $gridPanel.on('click', '.cmd-delete', function () {
            deleteDataById($(this).data('id'));
        });
        //授权事件
        $gridPanel.on('click', '.cmd-authorize', function () {
            authorize($(this).data('id'));
        });

    };

    //新建数据
    var createData = function () {
        jx.dialog({
            title: '新增用户',
            anim: -1,
            url: createUrl,
            width: dialogWidth,
            height: dialogHeight
        });
    };

    //编辑数据
    var editData = function (id) {
        jx.dialog({
            title: '修改用户',
            anim: -1,
            url: editUrl,
            params: {id: id},
            width: dialogWidth,
            height: dialogHeight
        });
    };

    //删除数据
    var deleteDataById = function (id) {
        deleteCore(id);
    };

    //删除数据
    var deleteData = function () {
        if (!gridInstance.hasCheckedRow(null,'请先选择系统用户后再操作！')) return;
        var ids = gridInstance.getCheckedRowIds();

        deleteCore(ids.join());
    };

    var deleteCore = function (ids) {
        jx.delete({
            url: deleteUrl,
            data: {ids: ids},
            success: function () {
                gridInstance.reloadGridData();
            }
        });
    };

    //导出数据
    var exportData = function () {
        jx.auth.exportData(exportUrl, jx.serialize($('#gridform')));
    };

    //设置数据状态
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

    //设置权限
    var authorize = function (id) {
        // if (!gridInstance.hasSelectedRow()) return;
        // var ids = gridInstance.getCheckedRowIds();
        // if (ids.length > 1) {
        //     jx.toastr.error('只能选择一项进行操作！');
        //     return;
        // }
        // var id = gridInstance.getSelectedRowId();
        var row = gridInstance.getSelected();
        jx.dialog({
            title: '用户授权 - ' + row.name,
            url: authorizeUrl,
            params: {objectId: id},
            width: '700px',
            height: '80%'
        });
    };

    // //关联角色
    // var map = function () {
    //     if (!gridInstance.hasSelectedRow()) return;
    //     var ids = gridInstance.getCheckedRowIds();
    //     if (ids.length > 1) {
    //         jx.toastr.error('只能选择一项进行操作！');
    //         return;
    //     }
    //     var id = gridInstance.getSelectedRowId();
    //     var user = gridInstance.getSelected();
    //     jx.dialog({
    //         title: '用户拥有角色列表 - 当前用户 : ' + user.name,
    //         url: mapUrl,
    //         params: {userId: id},
    //         width: '600px',
    //         height: '80%'
    //     });
    // };

    //定义表格列格式化函数
    //状态列格式化函数
    //对外接口
    jx.gf.command = function (v, row) {
        return jx.formatString($('#cmd_tpl').html(), v);
    };

    //对外接口
    //重新加载表格数据
    window.reloadGridData = function () {
        gridInstance.reloadGridData();
    };

    //初始化
    initGrid();
    initEvent();
});