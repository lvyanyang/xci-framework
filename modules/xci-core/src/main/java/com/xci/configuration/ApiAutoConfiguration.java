/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.xci.core.XCI;
import com.xci.internal.DateConverter;
import com.xci.internal.SingleJsonHandlerMethodArgumentResolver;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.StringHttpMessageConverter;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Api模块自动配置
 * @author 吕艳阳
 */
@Configuration
@EnableConfigurationProperties(ApiProperties.class)
@Import(SwaggerAutoConfiguration.class)
@ComponentScan(basePackages = {"com.xci.component", "com.xci.aspect"})
public class ApiAutoConfiguration {
    // 核心线程池大小
    private static final int corePoolSize = 50;
    // // 最大可创建的线程数
    // private static final int maxPoolSize = 200;
    // // 队列最大长度
    // private static final int queueCapacity = 1000;
    // // 线程池维护线程所允许的空闲时间
    // private static final int keepAliveSeconds = 300;

    /**
     * 自动识别使用的数据库类型
     * 在mapper.xml中databaseId的值就是跟这里对应，
     * 如果没有databaseId选择则说明该sql适用所有数据库
     */
    @Bean
    public DatabaseIdProvider createDatabaseIdProvider() {
        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties properties = new Properties();
        properties.setProperty("Oracle", "oracle");
        properties.setProperty("MySQL", "mysql");
        properties.setProperty("SQL Server", "sqlserver");
        properties.setProperty("SQLite", "sqlite");
        properties.setProperty("PostgreSQL", "postgresql");
        properties.setProperty("Derby", "derby");
        properties.setProperty("H2", "h2");
        properties.setProperty("HSQL", "hsql");
        // properties.setProperty("DB2","db2");
        // properties.setProperty("Informix","informix");
        // properties.setProperty("Sybase","sybase");
        // properties.setProperty("Hana","hana");
        databaseIdProvider.setProperties(properties);
        return databaseIdProvider;
    }

    /**
     * 随机文本验证码
     */
    @Bean(name = "captchaProducer")
    public DefaultKaptcha getKaptchaBean() {
        Properties properties = getDefaultProperties();
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    /**
     * 数学公式验证码
     */
    @Bean(name = "captchaProducerMath")
    public DefaultKaptcha getKaptchaBeanMath() {
        Properties properties = getDefaultProperties();
        // 验证码文本字符大小 默认为40
        properties.setProperty("kaptcha.textproducer.font.size", "35");
        // 验证码文本生成器
        properties.setProperty("kaptcha.textproducer.impl", "com.xci.core.internal.KaptchaTextCreator");
        // 干扰实现类
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        // 图片样式 水纹com.google.code.kaptcha.impl.WaterRipple 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy 阴影com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    /**
     * 获取验证码默认配置
     */
    private Properties getDefaultProperties() {
        Properties properties = new Properties();

        // 是否有边框 默认为true 我们可以自己设置yes，no
        properties.setProperty("kaptcha.border", "yes");
        // 边框颜色 默认为Color.BLACK
        properties.setProperty("kaptcha.border.color", "105,179,90");
        // 验证码文本字符颜色 默认为Color.BLACK
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        // 验证码图片宽度 默认为200
        properties.setProperty("kaptcha.image.width", "160");
        // 验证码图片高度 默认为50
        properties.setProperty("kaptcha.image.height", "60");
        // 验证码文本字符大小 默认为30
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        // 验证码文本字符间距 默认为2
        properties.setProperty("kaptcha.textproducer.char.space", "3");
        // 验证码文本字符长度 默认为5
        properties.setProperty("kaptcha.textproducer.char.length", "6");

        // 验证码文本字体样式 默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
        properties.setProperty("kaptcha.textproducer.font.names", "Arial,Courier");
        // 验证码噪点颜色 默认为Color.BLACK
        properties.setProperty("kaptcha.noise.color", "white");

        return properties;
    }

    /**
     * 解析单个Json参数组件
     */
    @Bean
    public SingleJsonHandlerMethodArgumentResolver createRequestJsonHandlerMethodArgumentResolver() {
        return new SingleJsonHandlerMethodArgumentResolver();
    }

    /**
     * 字符串解析编码为UTF-8
     */
    @Bean
    public StringHttpMessageConverter createResponseBodyConverter() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }


    /**
     * 对方法中的简单参数进行校验
     */
    @Bean
    public ExecutableValidator methodValidator() {
        return beanValidator().forExecutables();
    }

    /**
     * 对方法中的对象参数进行校验
     */
    @Bean
    public Validator beanValidator() {
        return Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();
    }

    /**
     * 对键值对参数字段日期转换进行转换
     */
    @Bean
    public DateConverter dateConverter() {
        return new DateConverter();
    }

    // @Bean(name = "threadPoolTaskExecutor")
    // public ThreadPoolTaskExecutor threadPoolTaskExecutor()
    // {
    //     ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    //     executor.setMaxPoolSize(maxPoolSize);
    //     executor.setCorePoolSize(corePoolSize);
    //     executor.setQueueCapacity(queueCapacity);
    //     executor.setKeepAliveSeconds(keepAliveSeconds);
    //     // 线程池对拒绝任务(无线程可用)的处理策略
    //     executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    //     return executor;
    // }

    /**
     * 调度定时任务
     */
    @Bean(name = "scheduledExecutorService")
    protected ScheduledExecutorService scheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(corePoolSize,
                new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                XCI.printThreadException(r, t);
            }
        };
    }
}