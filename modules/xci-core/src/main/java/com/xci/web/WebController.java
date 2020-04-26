/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.web;

import com.xci.base.BaseController;
import com.xci.core.XCI;
import org.springframework.ui.ModelMap;

/**
 * Web页面控制器基类
 * @author 吕艳阳
 */
public abstract class WebController extends BaseController {
    /**
     * 页面重定向
     * @param url 重定向的Url地址
     */
    protected String redirect(String url) {
        return XCI.redirectUrl(url);
    }

    /**
     * 页面跳转
     * @param url 跳转的Url地址
     */
    protected String forward(String url) {
        return XCI.forwardUrl(url);
    }

    /**
     * 标记新建记录
     * @param map 模型对象
     */
    protected void createMark(ModelMap map) {
        XCI.createMark(map);
    }

    /**
     * 是否新建记录
     */
    protected boolean isCreate() {
        return XCI.isCreate();
    }
}