/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.core;

import com.github.lvyanyang.component.ApiHandlerInterceptor;
import com.github.lvyanyang.configuration.ApiProperties;
import com.github.lvyanyang.internal.SingleJsonHandlerMethodArgumentResolver;
import com.github.lvyanyang.model.Dic;
import com.github.lvyanyang.model.OperateLogInfo;
import com.github.lvyanyang.web.component.WebHandlerInterceptor;
import com.github.lvyanyang.web.configuration.WebProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 应用程序基类
 * @author 吕艳阳
 */
@Slf4j
public class BaseApplication implements IApplication {
    @Resource private ApiHandlerInterceptor apiHandlerInterceptor;
    @Resource private SingleJsonHandlerMethodArgumentResolver requestJsonHandlerMethodArgumentResolver;
    @Resource private StringHttpMessageConverter stringHttpMessageConverter;
    @Value("${" + R.SWAGGER_ENABLED_ON_PROPERTY + ":true}")
    private boolean swaggerEnabled;

    /**
     * 配置请求拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截api权限判断
        var apiProperties = XCI.getBean(ApiProperties.class);
        registry.addInterceptor(apiHandlerInterceptor)
                .addPathPatterns(apiProperties.getUrlPatterns())
                .excludePathPatterns(apiProperties.getExcludeUrls());

        if (XCI.containsBean(WebProperties.class)) {
            var webProperties = XCI.getBean(WebProperties.class);

            // 拦截所有请求判断Web权限
            WebHandlerInterceptor webHandlerInterceptor = XCI.getBean(WebHandlerInterceptor.class);
            registry.addInterceptor(webHandlerInterceptor)
                    .addPathPatterns(webProperties.getAuthUrlPatterns())
                    .excludePathPatterns(webProperties.getAuthExcludes());//不拦截
        }
    }

    // /**
    //  * 配置Controller
    //  */
    // @Override
    // public void addViewControllers(ViewControllerRegistry registry) {
    //     //registry.addViewController("/toLogin").setViewName("login");
    // }

    /**
     * 配置静态资源访问
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (swaggerEnabled) {
            registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
        else{
            registry.addResourceHandler("doc.html").addResourceLocations("/404");
        }
    }

    /**
     * 配置参数解析组件
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(requestJsonHandlerMethodArgumentResolver);
    }

    /**
     * 配置消息转换组件
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(stringHttpMessageConverter);
    }

    /**
     * 验证 AppId
     * @param appId AppId
     * @return 验证成功返回true
     */
    @Override
    public RestResult apiValidAppId(String appId) {
        return RestResult.ok();
    }

    /**
     * Api接口全局异常处理
     * @param e 异常对象
     * @return 返回处理结果
     */
    @Override
    public Object apiGlobalExceptionHandler(Exception e) {
        var result = XCI.defaultApiExceptionHandler(e, Throwable::getMessage);
        log.error("发生异常:{} {}", result.getMsg(), e.getClass().getName());
        return result;
    }

    /**
     * Web全局异常处理
     * @param e 异常对象
     * @return 返回处理结果
     */
    @Override
    public Object webGlobalExceptionHandler(Exception e) {
        var result = XCI.defaultApiExceptionHandler(e, Throwable::getMessage);
        log.error("发生异常:{} {}", result.getMsg(), e.getClass().getName());
        var msg = result.getMsg();
        var webProperties = XCI.getBean(WebProperties.class);
        if (XCI.isBlank(msg)) {
            msg = webProperties.getError500AjaxMessage();
        }
        if (XCI.isAjaxRequest()) {
            return RestResult.fail(msg);
        }
        String errorUrl = webProperties.getError500Url();
        return XCI.getExceptionView(e, errorUrl, msg);
    }

    /**
     * 检测当前用户是否已登录,如果未登录尝试自动登录
     * @return 已登录或者自动登录成功返回 true
     */
    @Override
    public Boolean webAutoLogin() {
        return false;
    }

    /**
     * 是否有指定模块编码的权限
     * @param code 权限编码
     * @return 如果指定的tokenId拥有code的权限则返回true
     */
    @Override
    public Boolean isAuthorize(String code) {
        return true;
    }

    /**
     * 处理操作日志
     * @param operateLogInfo 操作日志对象
     */
    @Override
    public void processOperateLog(OperateLogInfo operateLogInfo) {
        log.info(XCI.toJsonString(operateLogInfo, true));
    }

    @Override
    public Object getParam(String code, Object defaultValue) {
        return R.Empty;
    }

    @Override
    public List<Dic> getDic(String code) {
        return new ArrayList<>();
    }
}