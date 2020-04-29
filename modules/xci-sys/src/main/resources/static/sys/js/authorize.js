/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

/*-----------------------------------------------------
 * 权限子系统-设置权限模块
 * ---------------------------------------------------*/
jx.ready(function () {

    var $moduleTree = $('#module-tree');
    var $departmentTree = $('#department-tree');

    var setDepartmentTreeBackground = function () {
        var $checks = $('.jxcheck:checked');
        if ($checks.length > 0) {
            var value = $checks[0].value;
            if (value == 'customDeptData') {
                $("#department-tree-background").hide();
            } else {
                $("#department-tree-background").show();
            }
        }
    };
    var initTree = function () {
        $moduleTree.jxtree({
            defaultIconCls: 'icon-flag',
            cascadeCheck: false,
            checkbox: true,
            onCheck: function (node, checked) {
                $(this).jxtree('reHighlightCheckedNode');
            },
            onLoadSuccess: function () {
                $(this).jxtree('options').cascadeCheck = true;
                $(this).jxtree('highlightCheckedNode');
            }
        });

        $departmentTree.jxtree({
            defaultIconCls: 'icon-users',
            cascadeCheck: false,
            checkbox: true,
            onCheck: function (node, checked) {
                var $tree = $(this);
                var method = checked ? 'check' : 'uncheck';
                var childNodes = $tree.tree('getChildren', node.target);
                if (childNodes && childNodes.length > 0) {
                    for (var i = 0; i < childNodes.length; i++) {
                        $tree.tree(method, childNodes[i].target);
                    }
                }
                $(this).jxtree('reHighlightCheckedNode');
            },
            onLoadSuccess: function () {
                // $(this).jxtree('options').cascadeCheck = true;
                $(this).jxtree('highlightCheckedNode');
            }
        });
    };
    var initForm = function () {
        $("#editform").on("beforesubmit", function () {
            var moduleNodes = $moduleTree.jxtree().getChecked(['checked', 'indeterminate']);
            var moduleIds = [];
            $.each(moduleNodes, function (i, v) {
                moduleIds.push(v.id);
            });
            $('#moduleIds').val(moduleIds.join());

            var departmentNodes = $departmentTree.jxtree().getChecked(['checked', 'indeterminate']);
            var departmentIds = [];
            $.each(departmentNodes, function (i, v) {
                departmentIds.push(v.id);
            });
            $('#departmentIds').val(departmentIds.join());

        }).on("aftersubmit", function (e, result) {
            if (result.success) {
                top.jx.toastr.success('授权成功');
                jx.closeDialog();
            } else if (result.msg) {
                jx.alert(result.msg);
            }
        });
    };
    var afterPage = function () {
        $('#wizard').wizard().on('change', function (e, data) {
            var $finish = $("#btn_finish");
            var $next = $("#btn_next");
            if (data.direction == "next") {
                if (data.step == 1) {
                    $finish.removeAttr('disabled');
                    $next.attr('disabled', 'disabled');
                }
            } else {
                $finish.attr('disabled', 'disabled');
                $next.removeAttr('disabled');
            }
        });

        $('.jxcheck').on('ifChecked', function (e) {
            setDepartmentTreeBackground();
        });

        //向导控件自适应高度
        jx.wizardFit();
        departmentTreeFit();
    };
    var departmentTreeFit = function () {
        var height = $('#step-2').height() - 44 - 25;// $('.list-group-item:first').outerHeight();
        $('#department-tree').height(height - 20);
        $('#department-tree-background').height(height);
    };

    initTree();
    initForm();
    setDepartmentTreeBackground();

    jx.onAfterInit(function () {
        afterPage();
    });
});
