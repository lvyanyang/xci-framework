/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.component;

import com.github.lvyanyang.core.IApplication;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.web.configuration.WebProperties;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;

/**
 * 全局异常处理器
 * @author 吕艳阳
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Resource private IApplication application;

    /** 全局异常拦截处理 */
    @ExceptionHandler(value = Exception.class)
    public Object handler(Exception e) {
        if (!XCI.containsBean(WebProperties.class)) {
            //纯API站点
            return application.apiGlobalExceptionHandler(e);
        } else {
            //API和Web综合站点
            var request = XCI.getRequest();
            if (XCI.isApiRequest(request)) {
                return application.apiGlobalExceptionHandler(e);
            } else {
                return application.webGlobalExceptionHandler(e);
            }
        }
    }
}