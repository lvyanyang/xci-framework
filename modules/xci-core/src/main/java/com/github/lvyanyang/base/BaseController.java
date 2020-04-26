/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.base;


import com.github.lvyanyang.core.XCI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 控制器基类
 * @author 吕艳阳
 */
public abstract class BaseController {
    /**
     * 获取request
     */
    protected HttpServletRequest getRequest() {
        return XCI.getRequest();
    }

    /**
     * 获取response
     */
    protected HttpServletResponse getResponse() {
        return XCI.getResponse();
    }

    /**
     * 获取session
     */
    protected HttpSession getSession() {
        return XCI.getSession();
    }
}