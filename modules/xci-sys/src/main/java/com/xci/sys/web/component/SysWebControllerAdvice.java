/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.sys.web.component;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;

@ControllerAdvice
public class SysWebControllerAdvice {
    @Resource private SysWebHelper sysWebHelper;

    @ModelAttribute("sys")
    public SysWebHelper sysWebHelper() {
        return this.sysWebHelper;
    }
}