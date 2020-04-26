/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.web.component;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;

/**
 * 全局注入变量到Web页面中
 */
@ControllerAdvice
public class WebControllerAdvice {
    @Resource private WebHelper webHelper;

    @ModelAttribute("web")
    public WebHelper initWebHelper() {
        return this.webHelper;
    }
}