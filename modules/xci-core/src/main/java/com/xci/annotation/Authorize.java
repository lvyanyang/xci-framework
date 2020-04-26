/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.annotation;

import java.lang.annotation.*;

/**
 * 在需要验证用户认证或者权限验证的Controller或者Action上使用此注解
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-25 21:39
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Authorize {
    /**
     * 权限编码,多个权限逗号隔开,多个权限编码需要同时满足才能通过验证
     */
    String code() default "";
}