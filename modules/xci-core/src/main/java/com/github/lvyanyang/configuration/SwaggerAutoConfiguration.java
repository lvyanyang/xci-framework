/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.configuration;

import com.github.lvyanyang.core.R;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.github.xiaoymin.swaggerbootstrapui.filter.SecurityBasicAuthFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

/**
 * Api 接口文档自动配置
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(name = R.SWAGGER_ENABLED_ON_PROPERTY, havingValue = R.TRUE)
public class SwaggerAutoConfiguration {
    @Resource private SwaggerProperties swaggerProperties;
    @Resource private SecurityBasicAuthFilter securityBasicAuthFilter;

    /**
     * 接口文档组件
     */
    @Bean
    public ApiInfo buildApiInfo() {
        var info = swaggerProperties;
        securityBasicAuthFilter.setEnableBasicAuth(info.isAuth());
        securityBasicAuthFilter.setUserName(info.getUserName());
        securityBasicAuthFilter.setPassword(info.getPassword());
        return new ApiInfoBuilder()
                .title(info.getTitle())
                .description(info.getDescription())
                .termsOfServiceUrl(info.getTermsOfServiceUrl())
                .license(info.getLicense())
                .licenseUrl(info.getLicenseUrl())
                .contact(new Contact(info.getContactName(), info.getContactUrl(), info.getContactEmail()))
                .version(info.getVersion())
                .build();
    }
}