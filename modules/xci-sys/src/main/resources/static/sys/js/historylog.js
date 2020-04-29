/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

/*-----------------------------------------------------
 * 系统历史日志模块
 * ---------------------------------------------------*/
jx.sys.HistoryLog = function (ops) {
    //region 私有变量

    var gridInstance;
    var $gridform = $('#gridform');

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
        gridInstance = $('#grid').jxgrid({
            url: jx.url(jx.historyLogGridUrl),
            onDblClickRow: function (index, row) {
                jx.showHistoryLogDetails(gridInstance.getRowId(row));
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
        $('.nav-tabs > li').on('click', function () {
            $('#category').val($(this).data('value'));
            $gridform.find(':submit').click();
        });

        //数据行详情事件
        gridInstance.getPanel().on('click', '.cmd-details', function () {
            jx.showHistoryLogDetails(gridInstance.getRowId(gridInstance.getSelected()));
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