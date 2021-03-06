package com.xci.sys.configuration;

import com.google.common.collect.Lists;
import com.xci.sys.api.ParamApiController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;

/**
 * 系统权限模块自动配置
 * @author 吕艳阳
 */
@Configuration
@ComponentScan(basePackages = {
        "com.xci.sys.aspect",
        "com.xci.sys.component",
        "com.xci.sys.api",
        "com.xci.sys.service",
        "com.xci.sys.web.component",
        "com.xci.sys.web.controller"
})
@MapperScan(basePackages = {"com.xci.sys.dao"})
public class SysAutoConfiguration {
    @Resource private ApiInfo apiInfo;
    @Resource private CacheManager cacheManager;

    @Bean
    public Docket createSysApiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .groupName("系统权限模块")
                .useDefaultResponseMessages(false)
                // .consumes(Set.of(MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                // .produces(Set.of(MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .select()
                .apis(RequestHandlerSelectors.basePackage(ParamApiController.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(Lists.newArrayList());
    }

    @Bean public Cache userCache() {
        return cacheManager.getCache("userCache");
    }

    @Bean public Cache roleCache() {
        return cacheManager.getCache("roleCache");
    }

    @Bean public Cache deptCache() {
        return cacheManager.getCache("deptCache");
    }

    @Bean public Cache dicCache() {
        return cacheManager.getCache("dicCache");
    }

    @Bean public Cache paramCache() {
        return cacheManager.getCache("paramCache");
    }

    @Bean public Cache appCache() {
        return cacheManager.getCache("appCache");
    }

    @Bean public Cache reportCache() {
        return cacheManager.getCache("reportCache");
    }

    @Bean public Cache onlineUserCache() {
        return cacheManager.getCache("onlineUserCache");
    }

    @Bean public Cache lockUserCache() {
        return cacheManager.getCache("lockUserCache");
    }

    @Bean public Cache captchaCache() {
        return cacheManager.getCache("captchaCache");
    }

    @Bean public Cache userModuleCache() {
        return cacheManager.getCache("userModuleCache");
    }
}
