/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.annotation;


import java.lang.annotation.*;

/**
 * 单个Json参数(非对象类型Json参数)解析注解
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-25 21:44
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SingleJson {
    /**
     * 解析时用到的JSON的键名
     */
    String name() default "";

    /**
     * 是否必填
     */
    boolean required() default true;

    /**
     * 当value的值或者参数名不匹配时，是否允许解析最外层属性到该对象
     */
    boolean parseAllFields() default true;
}