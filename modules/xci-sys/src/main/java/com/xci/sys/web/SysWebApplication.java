/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.sys.web;

import com.xci.core.XCI;
import com.xci.sys.core.SysApiApplication;
import com.xci.sys.service.SysService;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统模块应用程序接口
 */
@Slf4j
public class SysWebApplication extends SysApiApplication {
    /**
     * 检测当前用户是否已登录,如果未登录尝试自动登录
     * @return 已登录或者自动登录成功返回 true
     */
    @Override public Boolean webAutoLogin() {
        return SysService.me().checkAndAutoLogin();
    }

    /**
     * Web全局异常处理
     * @param e 异常对象
     * @return 返回处理结果
     */
    @Override
    public Object webGlobalExceptionHandler(Exception e) {
        XCI.defaultApiExceptionHandler(e, x -> {
            try {
                SysService.me().errorService().insertAsync(SysService.me().buildExceptionLog(e));
            } catch (Exception ex) {
                log.error("Web全局异常日志保存失败", ex);
            }
            return x.getMessage();
        });
        return super.webGlobalExceptionHandler(e);
    }
}