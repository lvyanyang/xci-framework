package com.xci.sys.annotation;

import java.lang.annotation.*;

/**
 * 数据权限过滤注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope
{
    /**
     * 机构表别名,机构主键列名设置后,此字段无效
     */
    String deptAlias() default "d";

    /**
     * 用户表别名,用户主键列名设置后,此字段无效
     */
    String userAlias() default "u";

    /**
     * 机构主键列名
     */
    String deptIdColumn() default "";

    /**
     * 用户主键列名
     */
    String userIdColumn() default "";
}
