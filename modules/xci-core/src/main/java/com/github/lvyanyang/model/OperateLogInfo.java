/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

/**
 * 日志信息
 */
@Builder
@Getter
public class OperateLogInfo {
    /**
     * 模块
     */
    private String tag;

    /**
     * 耗时
     */
    private Long costTime;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 执行状态
     */
    private Boolean status;

    /**
     * http状态码
     */
    private Integer httpStatusCode;

    /**
     * 执行信息
     */
    private String msg;

    /**
     * 参数
     */
    private String param;

    /**
     * 执行结果
     */
    private String result;

    /**
     * 应用主键
     */
    private String appId;

    /**
     * 请求地址
     */
    private String reqUrl;

    /**
     * 请求方法
     */
    private String reqMethod;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 浏览器标识
     */
    private String userAgent;
}