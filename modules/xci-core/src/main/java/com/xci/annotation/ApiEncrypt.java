/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.annotation;

import java.lang.annotation.*;

/**
 * 在Controller或者Action上使用此注解后将对接收参数进行解密，对输出内容进行加密
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-25 21:38
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ApiEncrypt {
}
