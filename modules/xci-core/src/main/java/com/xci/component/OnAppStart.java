/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.component;

import com.xci.core.IAppStart;
import com.xci.core.XCI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.TimerTask;

/**
 * 应用程序启动后初始化
 * @author 吕艳阳
 */
@Order(value = 1)
@Slf4j
@Component
public class OnAppStart implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        final Map<String, IAppStart> types = XCI.getBeansOfType(IAppStart.class);
        types.forEach((k, v) -> {
            AsyncService.me().execute(new TimerTask() {
                @Override
                public void run() {
                    v.start();
                }
            });
        });
    }
}