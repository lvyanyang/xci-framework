/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 应用程序配置属性
 * @author 吕艳阳
 */
@Data
@ConfigurationProperties(prefix = "xci.swagger")
public class SwaggerProperties {
    /**
     * 是否启用
     */
    private boolean enabled = false;

    /**
     * 是否启用权限控制
     */
    private boolean auth = true;

    /**
     * 访问账号
     */
    private String userName;

    /**
     * 访问密码
     */
    private String password;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 服务条款Url
     */
    private String termsOfServiceUrl;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人Url
     */
    private String contactUrl;

    /**
     * 联系人电子邮件
     */
    private String contactEmail;

    /**
     * 许可类型
     */
    private String license;

    /**
     * 许可Url
     */
    private String licenseUrl;

    /**
     * 版本
     */
    private String version;
}