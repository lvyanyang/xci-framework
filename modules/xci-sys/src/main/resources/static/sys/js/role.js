/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

/*-----------------------------------------------------
 * 系统角色模块
 * ---------------------------------------------------*/
jx.ready(function () {
    //region 私有变量

    var api = {
        grid: '/sys/role/grid',
        create: '/sys/role/create',
        edit: '/sys/role/edit',
        delete: '/sys/role/delete',
        details: '/sys/role/details',
        status: '/sys/role/status',
        authorize: '/sys/role/authorize'
    };
    var gridInstance;
    var dialogWidth = '600px'
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

    jx.gf.command = function (v, row) {
        return jx.formatString($('#cmd_tpl').html(), v);
    };

    //endregion

    //region 私有方法

    //初始化表格
    var initGrid = function () {
        gridInstance = $('#grid').jxgrid({
            url: jx.url(api.grid),
            onDblClickRow: function (index, row) {
                detailsData(row);
            }
        });
    }

    //初始化事件
    var bindEvent = function () {
        $('#btn-create').click(function () {
            createData();
        });
        $('#btn-delete').click(function () {
            deleteData();
        });

        $('#btn-authorize').click(function () {
            authorize();
        });

        var $gridPanel = gridInstance.getPanel();
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
        // //成员事件
        // $gridPanel.on('click', '.cmd-map-user', function () {
        //     map($(this).data('id'));
        // });
        //授权事件
        // $gridPanel.on('click', '.cmd-auth', function () {
        //     auth($(this).data('id'));
        // });

        //详情事件
        $gridPanel.on('click', '.cmd-details', function () {
            detailsData(gridInstance.getSelected());
        });
    }

    //新建数据
    var createData = function () {
        //弹出对话框模式
        jx.dialog({
            title: '新增系统角色',
            url: jx.url(api.create),
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

        var editUrl = jx.url(api.edit);

        //弹出对话框模式
        jx.dialog({
            title: '修改系统角色',
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
    var deleteData = function () {
        if (!gridInstance.hasCheckedRow(null, '请先选择系统角色后再操作！')) return;
        var ids = gridInstance.getCheckedRowIds();

        deleteCore(ids.join());
    };

    //删除单条数据
    var deleteDataById = function (id) {
        deleteCore(id);
    };

    //删除多条数据
    var deleteCore = function (ids) {
        jx.delete({
            url: jx.url(api.delete),
            data: {ids: ids},
            success: function () {
                gridInstance.reloadGridData();
            }
        });
    };

    //查看详情
    var detailsData = function (row) {
        //弹出对话框模式
        jx.auth.detailsData(gridInstance, row, {
            url: jx.url(api.details),
            title: '查看系统角色'
        });

        //页面跳转模式
        // window.location.href = jx.setUrlData(detailsUrl, {id: id});

        //tab页模式
        // jx.auth.app.addPage(id, '查看角色详细信息', jx.setUrlData(detailsUrl, {id: id,_ppid:jx.auth.app.getCurrentPageId()}));
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

    //设置权限
    var authorize = function () {
        if (!gridInstance.hasSelectedRow()) return;
        var ids = gridInstance.getCheckedRowIds();
        jx.dialog({
            title: '角色授权',
            url: jx.url(api.authorize),
            params: {roleIds: ids.join()},
            width: '580px',
            height: '80%'
        });
    };

    //endregion

    //region 模块初始化

    //初始化
    // jx.layer = layer;//如果要使用页面内对话框,请启用此代码
    initGrid();
    bindEvent();

    //endregion
})