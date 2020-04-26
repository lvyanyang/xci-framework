/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-25 21:40
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OperateLog {
    /**
     * 日志消息
     */
    String msg() default "";

    /**
     * 日志模块
     */
    String tag() default "";

    /**
     * 是否保存请求参数，默认false
     */
    boolean param() default false;

    /**
     * 排除的参数名称
     */
    String[] excludeParams() default {};

    /**
     * 是否保存返回结果，默认false
     */
    boolean result() default false;
}
