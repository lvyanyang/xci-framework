/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.component;

import cn.hutool.core.util.NumberUtil;
import com.github.lvyanyang.core.IApplication;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.configuration.ApiProperties;
import com.github.lvyanyang.annotation.Authorize;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Api接口拦截器
 * @author 吕艳阳
 */
@Component
public class ApiHandlerInterceptor implements HandlerInterceptor {
    @Resource private ApiProperties apiProperties;
    @Resource private IApplication apiApp;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //region appId检测
        if (apiProperties.isValidAppId()) {
            String appId = request.getHeader(R.HEADER_APPID_KEY);
            if (XCI.isBlank(appId) || !NumberUtil.isLong(appId)) {
                XCI.writeJson(RestResult.fail("请指定appId,请按照规范请求"));
                return false;
            }
            var appResult = apiApp.apiValidAppId(appId);
            if (appResult.isFail()) {
                XCI.writeJson(RestResult.fail(appResult.getMsg()));
                return false;
            }
        }
        //endregion

        //region 时间戳检测
        if (apiProperties.isValidTimestamp()) {
            String timestampStr = request.getHeader(R.HEADER_TIMESTAMP_KEY);
            if (XCI.isBlank(timestampStr) || !NumberUtil.isLong(timestampStr)) {
                XCI.writeJson(RestResult.fail("请指定时间戳,请按照规范请求"));
                return false;
            }

            long start = Long.parseLong(timestampStr);
            long ts = Math.abs((System.currentTimeMillis() - start));
            if (ts > (apiProperties.getTimestampOffset() * 1000)) {
                XCI.writeJson(RestResult.fail("无效的时间戳,请按照规范请求"));
                return false;
            }
        }
        //endregion

        //region 匿名访问验证
        Method method = ((HandlerMethod) handler).getMethod();
        //权限检测,确认使用者是否有权限访问此功能 类/方法上有@AllowAnonymous,允许匿名登录,直接放行
        if (XCI.IsAllowAnonymous(method)) {
            return true;
        }
        //endregion

        //region token验证
        String tokenStr = request.getHeader(R.HEADER_TOKEN_KEY);
        if (XCI.isBlank(tokenStr)) {
            XCI.writeJson(RestResult.fail("请指定token,请按照规范请求"));
            return false;
        }
        var tokenResult = XCI.getTokenId(tokenStr);
        if (tokenResult.isFail()) {
            XCI.writeJson(RestResult.fail(tokenResult.getMsg()));
            return false;
        }
        request.setAttribute(R.CURRENT_API_TOKEN_ID_KEY, tokenResult.getData());
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

        if (!this.apiApp.isAuthorize(code)) {
            XCI.writeJson(RestResult.fail(apiProperties.getUnAuthorizedMsg()));
            return false;
        }
        //endregion

        return true;
    }
}
