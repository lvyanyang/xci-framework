/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.annotation;

import java.lang.annotation.*;

/**
 * 服务方法参数校验注解
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-25 21:50
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Valid {
    Class<?>[] group() default {};
}