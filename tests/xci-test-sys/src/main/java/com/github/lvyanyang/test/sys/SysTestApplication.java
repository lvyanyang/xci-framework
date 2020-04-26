/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.test.sys;

import com.github.lvyanyang.sys.web.SysWebApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动器
 **/
@EnableCaching
@EnableTransactionManagement
@SpringBootApplication
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SysTestApplication extends SysWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysTestApplication.class, args);
    }
}