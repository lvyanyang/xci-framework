package com.xci.sys.aspect;

import com.xci.sys.annotation.BindingUpdateUser;
import com.xci.sys.service.SysService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 绑定当前操作用户字段信息拦截器
 * @author 吕艳阳
 */
@Aspect
@Slf4j
public class BindingUpdateUserAspect {
    @Before("@annotation(com.xci.sys.annotation.BindingUpdateUser)")
    public void doProcess(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) return;
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        BindingUpdateUser cu = method.getAnnotation(BindingUpdateUser.class);
        for (Object bean : args) {
            if (bean == null) continue;
            if (bean.getClass().isPrimitive()) continue;
            SysService.me().setUpdateUserInfo(bean);
        }
    }
}