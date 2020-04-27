/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.web.component;

import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.core.IApplication;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.web.configuration.WebProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class WebHandlerInterceptor implements HandlerInterceptor {
    @Resource private WebProperties webProperties;
    @Resource private IApplication iapp;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //region 匿名访问验证
        Method method = ((HandlerMethod) handler).getMethod();
        // 类/方法上有@AllowAnonymous,允许匿名登录,直接放行
        if (XCI.IsAllowAnonymous(method)) {
            return true;
        }
        //endregion

        //region 超时检测
        String contextPath = request.getContextPath();
        boolean isAjax = XCI.isAjaxRequest();
        boolean isLogin = iapp.webAutoLogin();
        if (!isLogin) {
            //登录超时
            if (isAjax) {
                //ajax请求
                XCI.writeJson(RestResult.fail(webProperties.getLoginTimeoutMessage()));
            } else {
                //普通web请求
                response.sendRedirect(contextPath + webProperties.getLoginUrl());
            }
            return false;
        }
        //endregion

        //region 权限编码验证
        Authorize classAuth = method.getDeclaringClass().getAnnotation(Authorize.class);
        Authorize methodAuth = method.getAnnotation(Authorize.class);
        String code = R.Empty;
        if (methodAuth != null && XCI.isNotBlank(methodAuth.code())) {
            code = methodAuth.code();
        } else if (classAuth != null && XCI.isNotBlank(classAuth.code())) {
            code = classAuth.code();
        }
        if (XCI.isBlank(code)) {
            return true;
        }

        if (!this.iapp.isAuthorize(code)) {
            if (isAjax) {
                //ajax请求
                XCI.writeJson(RestResult.fail(webProperties.getUnAuthorizedMessage()));
            } else {
                //普通web请求
                var url = contextPath + webProperties.getUnAuthorizedUrl();
                var dash = url.contains("?") ? "&" : "?";
                response.sendRedirect(XCI.format("{}code={}&url={}", url + dash, code, request.getRequestURI()));
            }
            return false;
        }
        //endregion

        return true;
    }
}
