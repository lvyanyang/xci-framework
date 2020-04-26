/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.web.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Web配置属性
 * @author 吕艳阳
 */
@Data
@ConfigurationProperties(prefix = "xci.web")
public class WebProperties {
    /**
     * 默认主页地址
     */
    private String defaultUrl = "/";

    /**
     * 登录地址
     */
    private String loginUrl = "/login";

    /**
     * 主页面地址
     */
    private String homeUrl = "/home";

    /**
     * 404错误页地址
     */
    private String error404Url = "/error404";

    /**
     * 500错误页地址
     */
    private String error500Url = "/error500";

    /**
     * 未授权地址
     */
    private String unAuthorizedUrl = "/unauthorized";

    /**
     * 错误404 Ajax消息
     */
    private String error404AjaxMessage = "抱歉，您要访问的页面没有找到。";

    /**
     * 错误500 Ajax消息
     */
    private String error500AjaxMessage = "抱歉，应用程序出现意外，请联系管理员。";

    /**
     * 未授权Ajax消息
     */
    private String unAuthorizedMessage = "您没有权限,请联系系统管理员";

    /**
     * 登录超时消息
     */
    private String loginTimeoutMessage = "登录超时,请重新登录";

    /**
     * 拦截地址
     */
    private String[] authUrlPatterns = new String[]{"/**"};

    /**
     * cdn服务器
     */
    private String cdn;

    /**
     * 忽略地址
     */
    private String[] authExcludes = new String[]{
            "/api/**", "/**/js/**", "/**/css/**","/**/font/**", "/**/img/**",
            "/**/*.js", "/**/*.css", "/**/*.jpg", "/**/*.png", "/favicon.ico"};
}