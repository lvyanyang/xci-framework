/*-----------------------------------------------------
 * 权限子系统-系统角色模块
 * ---------------------------------------------------*/
jx.ready(function () {
    window.api = {
        grid: '/sys/role/grid',
        create: '/sys/role/create',
        edit: '/sys/role/edit',
        delete: '/sys/role/delete',
        details: '/sys/role/details',
        export: '/sys/role/export',
        status: '/sys/role/status',
        authorize: '/sys/objectmap/setting',
        map: '/sys/urmap/role'
    };
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
    var authorizeUrl = jx.apiUrl(api.authorize);
    var mapUrl = jx.apiUrl(api.map);

    //初始化表格
    var initGrid = function () {
        gridInstance = $grid.jxgrid({
            url: gridUrl,
            onDblClickRow: function (index, row) {
                detailsData(row);
            }
        });
        $gridPanel = gridInstance.getPanel();
    };

    //初始化事件
    var initEvent = function () {
        $('#btn-create').click(function () {
            createData();
        });
        $('#btn-delete').click(function () {
            deleteData();
        });
        $('#btn-export').click(function () {
            exportData();
        });
        // $('#btn-map-user').click(function () {
        //     map();
        // });
        // $('#btn-authorize').click(function () {
        //     authorize();
        // });

        //状态事件
        $gridPanel.on('click', '.gridstatus', function () {
            setStatus($(this).data('id'), $(this).data('val'));
        });
        //编辑事件
        $gridPanel.on('click', '.cmd-edit', function () {
            editData($(this).data('id'));
        });
        //删除事件
        $gridPanel.on('click', '.cmd-delete', function () {
            deleteDataById($(this).data('id'));
        });
        //成员事件
        $gridPanel.on('click', '.cmd-map-user', function () {
            map($(this).data('id'));
        });
        //授权事件
        $gridPanel.on('click', '.cmd-authorize', function () {
            authorize($(this).data('id'));
        });

        //详情事件
        $gridPanel.on('click', '.cmd-details', function () {
            detailsData(gridInstance.getSelected());
        });
    };

    //新建数据
    var createData = function () {
        //弹出对话框模式
        jx.dialog({
            title: '新增角色',
            url: createUrl,
            width: dialogWidth,
            height: dialogHeight
        });

        //页面跳转模式
        // window.location.href = jx.setUrlData(createUrl, {_ppurl: encodeURI(window.location.href)});

        //tab页模式
        // jx.auth.app.addPage(jx.uuid(), '新增角色', jx.setUrlData(createUrl, {_ppid: jx.auth.app.getCurrentPageId()}));
    };

    //编辑数据
    var editData = function (id) {
        // if (!gridInstance.hasSelectedRow()) return;
        // var id = gridInstance.getSelectedRowId();

        //弹出对话框模式
        jx.dialog({
            title: '修改角色',
            url: editUrl,
            params: {id: id},
            width: dialogWidth,
            height: dialogHeight
        });

        //页面跳转模式
        // window.location.href = jx.setUrlData(editUrl, {id: id, _ppurl: encodeURI(window.location.href)});

        //tab页模式
        // jx.auth.app.addPage(id, '修改角色', jx.setUrlData(editUrl, {id: id, _ppid: jx.auth.app.getCurrentPageId()}));
    };

    //删除数据
    var deleteDataById = function (id) {
        deleteCore(id);
    };

    var deleteData = function () {
        if (!gridInstance.hasCheckedRow(null,'请先选择系统角色后再操作！')) return;
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

    var detailsData = function (row) {
        var id = gridInstance.getRowId(row);
        if (!id) return;

        //弹出对话框模式
        jx.detailsDialog({
            title: '查看角色详细信息',
            url: detailsUrl,
            params: {id: id},
            anim: -1,
            offset: 'auto',
            width: '700px',
            height: '70%'
        });

        //页面跳转模式
        // window.location.href = jx.setUrlData(detailsUrl, {id: id});

        //tab页模式
        // jx.auth.app.addPage(id, '查看角色详细信息', jx.setUrlData(detailsUrl, {id: id,_ppid:jx.auth.app.getCurrentPageId()}));
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
            title: '角色授权 - ' + row.name,
            url: authorizeUrl,
            params: {objectId: id},
            width: '700px',
            height: '80%'
        });
    };

    //角色用户
    var map = function (id) {
        // if (!gridInstance.hasSelectedRow()) return;
        // var ids = gridInstance.getCheckedRowIds();
        // if (ids.length > 1) {
        //     jx.toastr.error('只能选择一项进行操作！');
        //     return;
        // }
        // var id = gridInstance.getSelectedRowId();
        var row = gridInstance.getSelected();
        jx.dialog({
            title: '角色成员列表 - 当前角色 : ' + row.name,
            url: mapUrl,
            params: {roleId: id},
            shadeClose: true,
            width: '600px',
            height: '80%'
        });
    };

    //定义表格列格式化函数

    //对外接口
    jx.gf.command = function (v, row) {
        return jx.formatString($('#cmd_tpl').html(), v);
    };

    jx.gf.details = function (v, row) {
        return jx.formatString('<a class="cmd-details">{0}</a>', v);
    };

    //重新加载表格数据
    window.reloadGridData = function () {
        gridInstance.reloadGridData();
    };

    //初始化
    // jx.layer = layer;//如果要使用页面内对话框,请启用此代码
    initGrid();
    initEvent();
});