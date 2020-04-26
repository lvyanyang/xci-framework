/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.annotation;

import java.lang.annotation.*;

/**
 * 在Controller或者Action上使用此注解后系统将不做任何权限及登录验证
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-25 21:35
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AllowAnonymous {
}