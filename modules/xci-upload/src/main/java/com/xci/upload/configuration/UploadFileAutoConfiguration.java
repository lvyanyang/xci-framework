package com.xci.upload.configuration;

import com.google.common.collect.Lists;
import com.xci.upload.component.UploadFileApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 文件上传模块自动配置
 * @author 吕艳阳
 */
@Configuration
@EnableConfigurationProperties(UploadFileProperties.class)
@ComponentScan(basePackages = "com.xci.upload.component")
public class UploadFileAutoConfiguration {
    @Autowired(required = false)
    private ApiInfo apiInfo;

    @Bean
    @ConditionalOnBean(ApiInfo.class)
    public Docket uploadFileApiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .groupName("文件上传模块")
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage(UploadFileApiController.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(Lists.newArrayList());
    }
}