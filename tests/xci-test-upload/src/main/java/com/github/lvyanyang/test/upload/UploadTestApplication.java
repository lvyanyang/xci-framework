/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.test.upload;

import com.github.lvyanyang.core.BaseApplication;
import com.github.lvyanyang.upload.component.FileUploadService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class UploadTestApplication extends BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(UploadTestApplication.class, args);
    }

    /** 配置静态资源访问 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler(FileUploadService.me().buildFileResourceHandler(null))
                .addResourceLocations(FileUploadService.me().buildFileResourceLocation(null));
    }
}
