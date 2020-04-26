/*-----------------------------------------------------
 * 系统登录模块
 * ---------------------------------------------------*/
jx.ready(function () {
    //region 私有变量
    var $account = $('#account');
    var $password = $('#password');
    var $autoLogin = $('#auto_login');
    var $btnLogin = $('#btn_login');
    var $captchaContainer = $('#captcha-container');
    var loginUrl = '/sys/login';
    var lockUrl = '/sys/checkLock';
    var uuid = jx.uuid();
    var allowLogin = false;
    var setting = {
        /**
         * 是否启用随机背景图
         */
        enableRandomBackgroundImage: true,

        /**
         * 随机背景图最大序号
         */
        randomBackgroundImageMaxIndex: 24,

        /**
         * 默认背景图序号
         */
        defaultBackgroundImageIndex: 24,

        /**
         * 背景图片Url模板
         */
        backgroundImageUrl: jx.cdn + '/bg/bg{0}.jpg',

        /**
         * 登录页面url
         */
        loginUrl: loginUrl
    }
    //endregion

    //region 私有方法

    /**
     * 显示消息
     * @param msg 消息内容
     */
    var showMsg = function showMsg(msg) {
        toastr.options.timeOut = 60000;
        toastr.options.progressBar = true;
        toastr.options.positionClass = 'toast-top-center';
        toastr.remove();
        toastr.error(msg);
    }

    /**
     * 隐藏消息
     */
    var hideMsg = function () {
        toastr.remove();
    }

    /**
     * 设置背景图片
     */
    var setBackgroundImage = function () {
        var _core = function (num) {
            var path = jx.formatString(setting.backgroundImageUrl, num);
            $('body').css('background', 'url(' + path + ') no-repeat').css('background-size', '100% 100%');
        };
        if (setting.enableRandomBackgroundImage === true) {
            var randomNum = Math.floor(Math.random() * setting.randomBackgroundImageMaxIndex + 1);
            _core(randomNum);
        } else if (setting.defaultBackgroundImageIndex) {
            _core(setting.defaultBackgroundImageIndex);
        }
    }

    /**
     * 禁用文本框
     */
    var disableInput = function () {
        $account.attr('disabled', '');
        $password.attr('disabled', '');
        $autoLogin.attr('disabled', '');
        $btnLogin.val('登录中..').attr('disabled', '');
    }

    /**
     * 启用文本框
     */
    var enableInput = function () {
        $account.removeAttr('disabled');
        $password.removeAttr('disabled');
        $autoLogin.removeAttr('disabled');
        $btnLogin.val('登 录').removeAttr('disabled');
    }

    /**
     * 绑定控件事件
     */
    var bindEvent = function () {
        $btnLogin.on('click', function () {
            hideMsg();
            check();
        });

        $account.on('keydown', function (e) {
            hideMsg();
            if (e.keyCode === 13) { //回车
                if (!$(this).val().length) {
                    showMsg($(this).data('msg'));
                } else {
                    $password.focus();
                }
            }
        });

        $password.on('keydown', function (e) {
            hideMsg();
            if (e.keyCode === 27) { //Esc
                $account.focus();
                return;
            }
            if (e.keyCode === 13) { //回车
                if (!$(this).val()) {
                    showMsg($(this).data('msg'));
                } else {
                    check();
                }
            }
        });
    }

    /**
     * 校验
     */
    var valid = function () {
        if (!$account.val()) {
            showMsg($account.data('msg'));
            $account.focus();
            return false;
        }
        if (!$password.val()) {
            showMsg($password.data('msg'));
            $password.focus();
            return false;
        }
        if ($password.val().length > 30) {
            showMsg('密码长度不能超过30位');
            $password.focus();
            return false;
        }

        if (!loginUrl) {
            showMsg('请指定登录验证Url');
            return false;
        }
        return true;
    }

    /**
     * 检测账户状态
     */
    var check = function () {
        allowLogin = false;
        if (!valid()) {
            return;
        }
        hideMsg();

        //发送检测请求
        var _check = function () {
            $.ajax({
                url: lockUrl,
                type: 'post',
                data: {account: $account.val()}
            }).done(_callback)
                .fail(function () {
                    showMsg('检查锁定信息失败');
                });
        }

        //检测回调函数
        var _callback = function (r) {
            if (r.success) {
                //需要验证码
                jx.dialog({
                    title: '请完成登录验证',
                    content: $captchaContainer,
                    width: '320px',
                    height: '260px',
                    closeBtn: 0,
                    //btn: '登录',
                    success: function () {
                        $account[0].blur();
                        $password[0].blur();

                        if (!window.jigsawInstance) {
                            window.jigsawInstance = jigsaw.init({
                                el: document.getElementById('captcha'),
                                onSuccess: function () {
                                    jx.closeAllDialog();
                                    allowLogin = true;
                                    login();
                                },
                                onFail: function () {
                                    showMsg("无法验证通过");
                                },
                                onRefresh: function () {
                                }
                            });
                        } else {
                            window.jigsawInstance.reset();
                        }
                    }
                });
            } else {
                //不需要验证码,直接提交
                allowLogin = true;
                login();
            }
        }

        _check();
    }

    /**
     * 系统登录
     */
    var login = function () {
        if (!valid()) {
            return;
        }
        if (allowLogin == false) {
            showMsg('非法请求');
            return false;
        }
        hideMsg();
        disableInput();
        //发送登录请求
        var _send = function () {
            $.ajax({
                url: loginUrl,
                type: 'post',
                data: {
                    account: $account.val(),
                    password: $password.val(),
                    uuid: uuid,
                    autoLogin: $autoLogin.prop('checked')
                }
            }).done(function (r) {
                _callback(r);
            }).fail(function () {
                showMsg('登录失败');
                enableInput();
            });
        };

        //登录回调
        var _callback = function (r) {
            if (r.success) {
                $btnLogin.val('登陆成功,正在跳转...');
                var hash = window.location.hash;
                window.location.href = r.data.url + hash;
            } else {
                if (r.msg) {
                    showMsg(r.msg);
                }
                enableInput();
                $password.focus();
            }
        }
        _send();
    }

    /**
     * 模块初始化
     * @private
     */
    var init = function () {
        if (self != top) {
            top.location.href = jx.url(setting.loginUrl);
        }
        setBackgroundImage();
        bindEvent();
    }

    //endregion

    //region 模块初始化
    init();
    //endregion
})