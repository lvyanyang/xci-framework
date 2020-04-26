/*-----------------------------------------------------
 * 在线用户模块
 * ---------------------------------------------------*/
jx.sys.OnlineUser = function (ops) {
  //region 私有变量

  var api = {
    grid: '/sys/onlineUser/grid',
    logoff: '/sys/onlineUser/logoff',
    details: '/sys/onlineUser/details'
  };
  var gridInstance;

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
    $('#btn-logoff').click(function () {
      logoffData();
    });

    //数据行详情事件
    gridInstance.getPanel().on('click', '.cmd-details', function () {
      detailsData(gridInstance.getSelected());
    });
  }

  //注销
  var logoffData = function () {
    if (!gridInstance.hasCheckedRow()) return;
    var ids = gridInstance.getCheckedRowIds();
    jx.ajax({
      url: jx.url(api.logoff),
      data: {ids: ids.join()},
      confirm: '注：您确定要注销吗？',
      maskMsg: '正在注销,请稍等...',
      success: function (result) {
        gridInstance.reloadGridData();
        jx.toastr.success('注销成功');
      }
    });
  };

  //查看数据
  var detailsData = function (row) {
    jx.auth.detailsData(gridInstance, row, {
      url: jx.url(api.details),
      title: '查看在线用户'
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
