/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.aspect;

import com.github.lvyanyang.core.IApplication;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.model.OperateLogInfo;
import com.github.lvyanyang.annotation.OperateLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 操作日志拦截器
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-25
 */
@Aspect
@Component
public class OperateLogAspect {
    private ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();
    @Resource private IApplication apiApp;

    @Pointcut("@annotation(com.github.lvyanyang.annotation.OperateLog)")
    public void executeLogPointcut() {
    }

    @Before("executeLogPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        startTimeThreadLocal.set(System.currentTimeMillis());
    }

    @AfterReturning(pointcut = "executeLogPointcut()", returning = "rvt")
    public void doAfterReturning(JoinPoint joinPoint, Object rvt) {
        //当方法出现异常后将跳入全局异常处理,此处不会执行
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        long usedTime = System.currentTimeMillis() - startTimeThreadLocal.get();
        OperateLog log = method.getAnnotation(OperateLog.class);
        OperateLogInfo.OperateLogInfoBuilder builder = OperateLogInfo.builder();

        //方法名称
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        builder.method(XCI.format("{}.{}", className, methodName));

        if (log.param()) {
            //参数
            builder.param(XCI.getExecuteParamString(method, joinPoint.getArgs(), log.excludeParams()));
        }
        if (log.result()) {
            //返回值
            builder.result(XCI.getExecuteResultString(rvt));
        }
        var request = XCI.getRequest();
        // var appId = request.getHeader(R.HEADER_APPID_KEY);
        var browserOsInfo = XCI.getRequestBrowserOsInfo(false);
        builder.tag(log.tag()).status(true).costTime(usedTime).msg(log.msg())
                .reqUrl(request.getRequestURI()).reqMethod(request.getMethod())
                .ip(browserOsInfo.getIp()).userAgent(browserOsInfo.getUserAgent());

        apiApp.processOperateLog(builder.build());
    }
}