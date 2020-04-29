/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

/*-----------------------------------------------------
 * 系统操作日志模块
 * ---------------------------------------------------*/
jx.sys.OperateLog = function (ops) {
    //region 私有变量

    var api = {
        grid: '/sys/operateLog/grid',
        details: '/sys/operateLog/details'
    };
    var gridInstance;
    var $timeButton = $('#time-horizon > a');

    //endregion

    //region 公共方法

    //重新加载表格
    jx.reloadGrid = function () {
        gridInstance.reloadGridData();
    }

    //格式化列操作类型
    jx.gf.formatCostTime = function (v, row, index) {
        return row.costTimeName;
    };

    jx.gf.formatStatus = function (v, row, index) {
        if (v == '1') {
            return '<span class="label label-success">成功</span>';
        } else {
            return '<span class="label label-danger">失败</span>';
        }
    };

    //格式化列表名
    jx.gf.formatOperateDateTime = function (v, row) {
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
            onBeforeLoad: function (param) {
                //查询数据之前必须先指定操作日期段,否则阻止提交
                if (param.operateEndDateTime && param.operateStartDateTime) {
                    return true;
                }
                return false;
            }
        });
    }

    //初始化事件
    var bindEvent = function () {
        $timeButton.click(function () {
            setTimeHorizon($(this));
        });

        $('#btn-date-search').click(function () {
            if ($('#dateform').valid()) {
                formSubmit();
                $('#date-popover').popoverX('hide');
            }
        });

        //数据行详情事件
        gridInstance.getPanel().on('click', '.cmd-details', function () {
            detailsData(gridInstance.getSelected());
        });
    }

    var setTimeHorizon = function ($this) {
        $timeButton.removeClass('active');
        $this.addClass('active');
        var val = $this.attr('data-value');
        if (val == '5') return;
        var $start = $('#operateStartDateTime');
        var $end = $('#operateEndDateTime');
        var cdate = jx.formatDate(new Date());
        switch (val) {
            case '1'://今天
                $start.val(cdate);
                $end.val(cdate);
                formSubmit();
                break;
            case '2'://近7天
                $start.val(jx.formatDate(jx.addDate(new Date(), -7)));
                $end.val(cdate);
                formSubmit();
                break;
            case '3'://近一个月
                $start.val(jx.formatDate(jx.addDate(new Date(), -30)));
                $end.val(cdate);
                formSubmit();
                break;
            case '4'://近三个月
                $start.val(jx.formatDate(jx.addDate(new Date(), -90)));
                $end.val(cdate);
                formSubmit();
                break;
            case '5'://自定义
                break;
        }
    };

    var formSubmit = function () {
        $('#gridform').find(':submit').click();
    }

    //查看数据
    var detailsData = function (row) {
        jx.auth.detailsData(gridInstance, row, {
            url: jx.url(api.details),
            maxmin: true,
            width: '70%'
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