/**
 * 应用程序页面组件
 */
jx.plugin({
    name: 'jxpage',
    create: function (ele, ops) {
        var $ele = $(ele);
        $ele.click(function (e) {
            jx.stope(e);
            var $this = $(this);
            var url = $this.attr('href') || $this.data('url');
            if (url) {
                top.app.addPage(
                    $this.attr('id')||'',
                    $this.attr('title') || $this.html(),
                     url,$this.attr('iconCls')||'',true);
            }
        });
    }
});

/**
 * Change提交表单组件
 */
jx.plugin({
    name: 'jxswitchsubmit',
    create: function (ele, ops) {
        return {};
    },
    onInstance: function (e) {
        $(e.target).find('.jxswitchsubmit').each(function (i, v) {
            $(v).data('bootstrap-switch').options.onSwitchChange = function () {
                $(this).closest('form').find(':submit').click();
            };
        });
    }
});

