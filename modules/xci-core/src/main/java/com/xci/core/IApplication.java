/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.core;

import com.xci.model.OperateLogInfo;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 应用程序业务处理接口
 */
public interface IApplication extends WebMvcConfigurer {
    /**
     * 验证 AppId
     * @param appId AppId
     * @return 验证成功返回true
     */
    RestResult apiValidAppId(String appId);

    /**
     * Api接口全局异常处理
     * @param e 异常对象
     * @return 返回处理结果
     */
    Object apiGlobalExceptionHandler(Exception e);

    /**
     * 检测当前用户是否已登录,如果未登录尝试自动登录
     * @return 已登录或者自动登录成功返回 true
     */
    Boolean webAutoLogin();

    /**
     * Web全局异常处理
     * @param e 异常对象
     * @return 返回处理结果
     */
    Object webGlobalExceptionHandler(Exception e);

    /**
     * 是否有指定模块编码的权限
     * @param code 权限编码
     * @return 如果指定的tokenId拥有code的权限则返回true
     */
    Boolean isAuthorize(String code);

    /**
     * 处理操作日志
     * @param operateLogInfo 操作日志对象
     */
    void processOperateLog(OperateLogInfo operateLogInfo);
}