/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.component;

import com.github.lvyanyang.core.IApplication;
import com.github.lvyanyang.core.XCI;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 * @author 吕艳阳
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Resource private IApplication application;

    /** 全局异常拦截处理 */
    @ExceptionHandler(value = Exception.class)
    public Object handler(Exception e, HttpServletResponse response, HttpServletRequest request) {
        if (XCI.isApiRequest()) {
            return application.apiGlobalExceptionHandler(e);
        } else {
            return application.webGlobalExceptionHandler(e);
        }
    }
}