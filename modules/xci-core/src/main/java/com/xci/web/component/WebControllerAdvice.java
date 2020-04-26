package com.xci.web.component;

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