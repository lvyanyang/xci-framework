/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.lvyanyang.sys.model.BaseOperateUserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * 系统错误日志
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统错误日志")
public class SysErrorLog extends BaseOperateUserEntity {
    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")
    private String reqParam;

    /**
     * 请求方法
     */
    @Length(max = 20, message = "请求方法不能超过{max}")
    @ApiModelProperty(value = "请求方法")
    private String reqMethod;

    /**
     * 请求地址
     */
    @Length(max = 500, message = "请求地址长度不能超过{max}")
    @ApiModelProperty(value = "请求地址")
    private String reqUrl;

    /**
     * 消息
     */
    @ApiModelProperty(value = "消息")
    private String msg;

    /**
     * 详细消息
     */
    @ApiModelProperty(value = "错误详细消息")
    private String details;

    /**
     * 应用主键
     */
    @Length(max = 100, message = "应用主键长度不能超过{max}")
    @ApiModelProperty(value = "应用主键")
    private String appId;

    /**
     * 应用名称
     */
    @Length(max = 100, message = "应用名称长度不能超过{max}")
    @ApiModelProperty(value = "应用名称")
    private String appName;

    /**
     * IP地址
     */
    @Length(max = 100, message = "IP地址长度不能超过{max}")
    @ApiModelProperty(value = "IP地址")
    private String ip;
}
