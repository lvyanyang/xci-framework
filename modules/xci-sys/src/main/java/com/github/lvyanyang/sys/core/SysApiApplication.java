/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.core;

import com.github.lvyanyang.core.BaseApplication;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;

import com.github.lvyanyang.model.OperateLogInfo;
import com.github.lvyanyang.sys.entity.SysApp;
import com.github.lvyanyang.sys.component.SysService;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统模块应用程序接口
 */
@Slf4j
public class SysApiApplication extends BaseApplication {
    @Override
    public Object apiGlobalExceptionHandler(Exception e) {
        var result = XCI.defaultApiExceptionHandler(e, x -> {
            try {
                SysService.me().errorService().insertAsync(SysService.me().buildExceptionLog(e));
            } catch (Exception ex) {
                log.error("API全局异常日志保存失败", ex);
            }
            return x.getMessage();
        });
        log.error("发生异常:{} {}", result.getMsg(), e.getClass().getName());
        return result;
    }

    @Override
    public RestResult apiValidAppId(String appId) {
        SysApp app = SysService.me().getApp(appId);
        if (app == null) {
            return RestResult.fail("无效的应用,无效应用标识");
        } else if (!app.getStatus()) {
            return RestResult.fail("无效的应用,尚未开通服务");
        }
        XCI.getRequest().setAttribute(R.CURRENT_APP_API_KEY, app);
        return RestResult.ok();
    }

    @Override
    public Boolean isAuthorize(String code) {
        var user = SysService.me().getCurrentUser();
        return SysService.me().isAuthorize(user, code);
    }

    @Override
    public void processOperateLog(OperateLogInfo operateLogInfo) {
        log.info(XCI.toJsonString(operateLogInfo, true));
        var operateLog = SysService.me().buildOperateLog(operateLogInfo);
        SysService.me().operateLogService().insertAsync(operateLog);
    }
}