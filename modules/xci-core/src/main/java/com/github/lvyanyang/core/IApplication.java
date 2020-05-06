/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.core;

import com.github.lvyanyang.model.Dic;
import com.github.lvyanyang.model.OperateLogInfo;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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

    /**
     * 根据参数编码获取参数值
     * @param code 参数编码
     * @param defaultValue 找不到时的默认值
     */
    Object getParam(String code, Object defaultValue);

    /**
     * 根据字典编码获取字典列表
     * @param code 字典类型编码
     */
    List<Dic> getDic(String code);
}