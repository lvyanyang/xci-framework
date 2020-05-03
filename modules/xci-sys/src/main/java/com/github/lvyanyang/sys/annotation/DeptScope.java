/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.annotation;

import com.github.lvyanyang.core.R;

import java.lang.annotation.*;

/**
 * 机构数据权限过滤注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DeptScope {
    /**
     * 是否启用机构权限字段名称,字段类型必须是Boolean
     */
    String enableName() default "enableDeptScope";

    /**
     * 机构列名称
     */
    String deptIdName() default "d.id";

    /**
     * 用户列名称,如果为空则忽略用户过滤
     */
    String userIdName() default R.Empty;
}