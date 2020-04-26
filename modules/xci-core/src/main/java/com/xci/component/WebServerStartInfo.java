/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.component;

import com.xci.configuration.SwaggerProperties;
import com.xci.core.IAppStart;
import com.xci.core.R;
import com.xci.core.XCI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 启动服务器时输出服务器信息
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-25 23:17
 */
@Slf4j
@Order(value = 1)
@Component
public class WebServerStartInfo implements IAppStart {
    @Value("${server.port:7001}")
    private int port;

    @Value("${server.servlet.context-path:/}")
    private String contextPath;

    @Value("${" + R.SWAGGER_ENABLED_ON_PROPERTY + ":true}")
    private boolean swaggerEnabled;

    /** 启动服务器时输出服务器信息 */
    @Override
    public void start() {
        if (swaggerEnabled) {
            log.info("服务启动成功 | " + build("http://localhost:{}") + " | " + build("http://localhost:{}{}doc.html"));
        } else {
            log.info("服务启动成功 | " + build("http://localhost:{}"));
        }
    }

    private String build(String tpl) {
        return XCI.format(tpl, port, contextPath);
    }
}
