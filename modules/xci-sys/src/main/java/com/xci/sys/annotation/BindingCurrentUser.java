package com.xci.sys.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 绑定当前用户信息
 * @author 吕艳阳
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindingCurrentUser {
    /**
     * 是否新建用户
     */
    boolean created() default false;
}
