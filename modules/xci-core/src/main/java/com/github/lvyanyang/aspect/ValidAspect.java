/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.aspect;

import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.annotation.Valid;
import com.github.lvyanyang.exceptions.ValidException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Set;

/**
 * 服务方法参数校验拦截器
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-25
 */
@Order(1)
@Aspect
@Component
public class ValidAspect {
    @Resource
    private Validator beanValidator;

    @Resource
    private ExecutableValidator methodValidator;

    @Pointcut("execution(* com.github.lvyanyang.core.BaseService+.*(..)) || @annotation(org.springframework.validation.annotation.Validated)")
    public void validatorPointcut() {
    }

    @Before("validatorPointcut()")
    public void doBefore(JoinPoint point) {
        Object target = point.getThis();// 获得切入目标对象
        Object[] args = point.getArgs();// 获得切入方法参数

        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();// 获得切入的方法
        // if (validated == null) { //方法上没有验证注解,则直接退出
        //     return;
        // }
        //        HashMap<String, String> validatorMap = new HashMap<>();

        StringBuilder msgBuilder = new StringBuilder();
        // 校验以基本数据类型 为方法参数的
        Set<ConstraintViolation<Object>> validResult = methodValidator.validateParameters(target, method,
                point.getArgs());
        for (ConstraintViolation<Object> r : validResult) {
            // msgBuilder.append(XCI.getValidMsg(r.getPropertyPath().toString(), r.getMessage()));
            msgBuilder.append(r.getMessage());
        }

        // 校验以java bean对象 为方法参数的
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Object val = args[i];
            if (null == val) {
                continue;
            }
            Valid validParam = parameters[i].getAnnotation(Valid.class);
            if (null == validParam) {
                continue;
            }
            if (val instanceof List) {
                var list = (List) val;
                for (int index = 0; index < list.size(); index++) {
                    Object item = list.get(index);
                    var result = beanValidator.validate(item, validParam.group());
                    if (result.size() > 0) {
                        msgBuilder.append(XCI.format("第{}行验证失败:", (index + 1)));
                    }
                    validBean(msgBuilder, result);
                }
            } else {
                var result = beanValidator.validate(val, validParam.group());
                validBean(msgBuilder, result);
            }
        }

        if (msgBuilder.length() > 0) {
            throw new ValidException(msgBuilder.toString());
        }
    }

    private void validBean(StringBuilder stringBuilder, Set<ConstraintViolation<Object>> result) {
        for (ConstraintViolation<Object> aValidResult : result) {
            String field = aValidResult.getPropertyPath().toString();
            String msg = aValidResult.getMessage();
            stringBuilder.append(XCI.getValidMsg(field, msg));
        }
    }
}