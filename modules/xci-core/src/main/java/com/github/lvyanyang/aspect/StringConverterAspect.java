/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.aspect;

import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.annotation.StringConverter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 方法参数(单个字符串、对象、Map)字符串字段转换拦截器
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-25
 */
@Order(0)
@Aspect
@Component
public class StringConverterAspect {
    @Pointcut("@annotation(com.github.lvyanyang.annotation.StringConverter)")
    public void stringConverterAspect() {
    }

    @Around("stringConverterAspect()")
    public Object doBefore(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        if (args.length == 0) return null;
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        var an = method.getAnnotation(StringConverter.class);

        for (int i = 0; i < args.length; i++) {
            Object bean = args[i];
            if (bean == null) continue;
            if (bean.getClass().isPrimitive()) continue;
            if (bean instanceof String) {
                args[i] = XCI.stringConvert((String) bean, an.converts(), an.customs(), an.oldStr(), an.newStr());
            } else {
                if (XCI.isEmpty(an.converts())) continue;
                XCI.objectStringConverter(bean, an.converts(), an.customs(), an.oldStr(), an.newStr());
            }
        }
        return point.proceed(args);
    }
}