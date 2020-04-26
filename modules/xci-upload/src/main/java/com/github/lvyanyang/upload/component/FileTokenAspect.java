/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.upload.component;

import com.github.lvyanyang.annotation.AllowAnonymous;
import com.github.lvyanyang.exceptions.AppException;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.upload.configuration.UploadFileProperties;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 文件上传Token拦截器
 * @author 吕艳阳
 */
@Aspect
@Component
public class FileTokenAspect {
    @Resource private UploadFileProperties fileUploadProperties;

    @Pointcut("execution(* com.xci.upload.api.*.*(..))")
    public void fileTokenAspect() {
    }

    @Before("fileTokenAspect()")
    public void doBefore(JoinPoint point) {
        if (!fileUploadProperties.isValidFileToken()) {
            return;
        }
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        //允许匿名访问
        if (method.getAnnotation(AllowAnonymous.class) != null
                || method.getDeclaringClass().getAnnotation(AllowAnonymous.class) != null) {
            return;
        }
        var request = XCI.getRequest();
        String fileTokenStr = request.getHeader(R.HEADER_FILE_TOKEN_KEY);
        String ftv = XCI.decrypt(fileTokenStr);
        if (!XCI.formatDate(new Date(), "*yyyy*MM*dd*").equalsIgnoreCase(ftv)) {
            throw new AppException("请指定有效的文件token");
        }
    }
}