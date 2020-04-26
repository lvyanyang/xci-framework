/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.web.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Web模块自动配置
 * @author 吕艳阳
 */
@Configuration
@EnableConfigurationProperties(WebProperties.class)
@ComponentScan(basePackages = "com.github.lvyanyang.web.component")
public class WebAutoConfiguration {

}