/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 应用程序配置属性
 * @author 吕艳阳
 */
@Data
@ConfigurationProperties(prefix = "xci.api")
public class ApiProperties {
    /**
     * 是否验证AppId
     */
    private boolean validAppId = true;

    /**
     * 是否验证时间戳
     */
    private boolean validTimestamp = false;

    /**
     * 验证时间戳时验证偏差(单位秒)
     */
    private int timestampOffset = 120;

    /**
     * 拦截地址
     */
    private String[] urlPatterns = new String[]{"/api/**"};

    /**
     * 忽略拦截地址
     */
    private String[] excludeUrls = new String[]{};

    /**
     * 未授权消息
     */
    private String unAuthorizedMsg = "您没有操作此Api权限";

    /**
     * 是否启用404错误统一处理
     */
    private boolean enabledError404Handle = true;

    /**
     * 未找到请求路径
     */
    private String error404Msg = "请输入正确的Api请求路径";

    /**
     * 系统接口分页允许的最大页数
     */
    private int maxPageSize = 1000;
}