package com.xci.sys.aspect;

import com.xci.sys.annotation.BindingCreateUser;
import com.xci.sys.service.SysService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 绑定当前创建用户字段信息拦截器
 * @author 吕艳阳
 */
@Aspect
@Slf4j
public class BindingCreateUserAspect {
    @Before("@annotation(com.xci.sys.annotation.BindingCreateUser)")
    public void doProcess(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) return;
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        BindingCreateUser cu = method.getAnnotation(BindingCreateUser.class);
        for (Object bean : args) {
            if (bean == null) continue;
            if (bean.getClass().isPrimitive()) continue;
            SysService.me().setCreateUserInfo(bean);
        }
    }
}