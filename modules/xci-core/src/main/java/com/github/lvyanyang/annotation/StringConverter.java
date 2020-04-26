/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.annotation;

import com.github.lvyanyang.model.StringConverterType;

import java.lang.annotation.*;

/**
 * 对方法参数(单个字符串、对象、Map)字符串字段进行转换
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-25 21:42
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StringConverter {
    /**
     * 转换类型数组，用来指定转换类型
     */
    StringConverterType[] converts() default {};

    /**
     * 自定义实现类数组
     */
    Class<?>[] customs() default {};

    /**
     * 待替换的字符串,需要替换多个字符用逗号隔开
     */
    String oldStr() default "";

    /**
     * 将要替换的字符串,多个字符替换用逗号隔开
     */
    String newStr() default "";
}