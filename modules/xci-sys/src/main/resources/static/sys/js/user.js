/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

/*-----------------------------------------------------
 * 系统用户模块
 * ---------------------------------------------------*/
jx.ready(function () {
    //region 私有变量

    var api = {
        grid: '/sys/user/grid',
        create: '/sys/user/create',
        edit: '/sys/user/edit',
        delete: '/sys/user/delete',
        details: '/sys/user/details',
        status: '/sys/user/status'
    };

    var gridInstance;
    var $revisePasswordPopover = $('#revise-passwordpopover');
    var $revisePasswordForm = $('#revise-passwordform');
    var dialogWidth = '500px';
    var dialogHeight = '800px';

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

    //格式化命令列
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
        $('#btn-edit,#btn-cmedit').click(function () {
            editData();
        });
        $('#btn-delete,#btn-cmdelete').click(function () {
            deleteData();
        });

        var $gridPanel = gridInstance.getPanel();
        // 表格事件
        $gridPanel.on('click', '.gridstatus', function () {
            var id = $(this).data('id');
            var val = $(this).data('val');
            setStatus(id, val);
        });
        //数据行详情事件
        $gridPanel.on('click', '.cmd-details', function () {
            detailsData(gridInstance.getSelected());
        });
        //编辑事件
        $gridPanel.on('click', '.cmd-edit', function () {
            editData($(this).data('id'));
        });
        //删除事件
        $gridPanel.on('click', '.cmd-delete', function () {
            deleteDataById($(this).data('id'));
        });

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

    }

    //新建数据
    var createData = function () {
        jx.dialog({
            title: '新增系统用户',
            url: jx.url(api.create),
            anim: -1,
            width: dialogWidth,
            height: dialogHeight
        });
    };

    //编辑数据
    var editData = function (id) {
        jx.dialog({
            title: '修改系统用户',
            anim: -1,
            url: jx.url(api.edit),
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
        if (!gridInstance.hasCheckedRow(null, '请先选择系统用户后再操作！')) return;
        var ids = gridInstance.getCheckedRowIds();

        deleteCore(ids.join());
    };

    var deleteCore = function (ids) {
        jx.delete({
            url: jx.url(api.delete),
            data: {ids: ids},
            success: function () {
                gridInstance.reloadGridData();
            }
        });
    };

    //查看数据
    var detailsData = function (row) {
        jx.auth.detailsData(gridInstance, row, {
            url: jx.url(api.details),
            title: '查看系统用户'
        });
    }

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
})