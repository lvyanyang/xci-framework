/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.annotation;

import java.lang.annotation.*;

/**
 * 数据库取前几条数据注解
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-25 21:45
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Top {
    /**
     * 取得数量,默认为1
     */
    int count() default 1;
}