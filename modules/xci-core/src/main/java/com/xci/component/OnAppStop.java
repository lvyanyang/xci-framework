/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.component;

import com.xci.core.IAppStop;
import com.xci.core.XCI;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 应用程序关闭前执行
 * @author 吕艳阳
 */
@Order(value = 1)
public class OnAppStop implements DisposableBean {
    @Override
    public void destroy() {
        //为所有带有资源关闭的组件进行关闭
        final Map<String, IAppStop> types = XCI.getBeansOfType(IAppStop.class);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> types.forEach((k, v) -> v.stop())));
    }
}