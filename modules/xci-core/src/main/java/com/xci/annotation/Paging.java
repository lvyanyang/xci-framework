/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.annotation;

import java.lang.annotation.*;

/**
 * 数据库分页注解
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-25 21:42
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Paging {
    /**
     * 是否启用排序
     */
    boolean allowSort() default true;

    /**
     * 是否允许多字段排序,默认单字段排序
     */
    boolean allowMultiSort() default false;

    /**
     * 默认页码
     */
    int defaultPageIndex() default 1;

    /**
     * 默认每页大小
     */
    int defaultPageSize() default 10;

    /**
     * 默认排序字段
     */
    String defaultSort() default "";

    /**
     * 默认排序方式
     */
    String defaultSortDir() default "";

    /**
     * 排序字段名称转换方式 0无变化 1驼峰转下划线 2下划线转驼峰
     */
    int sortNameConvertType() default 1;

    /**
     * 排序字段名映射转换,多个映射用分号分割,每个映射之间用冒号分割,例如:"接收字段名1:转换后字段名1;接收字段名1:转换后字段名1;...."
     */
    String sortNameMap() default "";

    /**
     * 允许的排序字段名称,多个逗号隔开
     */
    String allowSortNames() default "";
}