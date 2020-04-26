/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.entity;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.lvyanyang.sys.model.BaseOperateUserEntity;
import com.github.lvyanyang.core.R;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 系统操作日志
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统操作日志")
public class SysOperateLog extends BaseOperateUserEntity {
    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 日志模块
     */
    @Length(max = 500, message = "日志模块长度不能超过{max}")
    @ApiModelProperty(value = "日志模块")
    private String tag;

    /**
     * 操作信息
     */
    @Length(max = 4000, message = "操作信息长度不能超过{max}")
    @ApiModelProperty(value = "操作信息")
    private String msg;

    /**
     * 操作方法
     */
    @Length(max = 1000, message = "操作方法长度不能超过{max}")
    @ApiModelProperty(value = "操作方法")
    private String method;

    /**
     * 请求地址
     */
    @Length(max = 2000, message = "请求地址长度不能超过{max}")
    @ApiModelProperty(value = "请求地址")
    private String reqUrl;

    /**
     * 请求方法
     */
    @Length(max = 20, message = "请求方法不能超过{max}")
    @ApiModelProperty(value = "请求方法")
    private String reqMethod;

    /**
     * 耗时(毫秒数)
     */
    @NotNull(message = "请指定耗时")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "耗时(毫秒数)")
    private Long costTime;

    /**
     * 耗时描述
     */
    @ApiModelProperty(value = "耗时描述")
    public String getCostTimeName() {
        if(costTime != null) return DateUtil.formatBetween(costTime);
        return R.Empty;
    }

    /**
     * 浏览器标识
     */
    @Length(max = 100, message = "浏览器标识长度不能超过{max}")
    @ApiModelProperty(value = "浏览器标识")
    private String userAgent;

    /**
     * 状态 [true-启用, false-禁用]
     */
    @NotNull(message = "请输入状态")
    @ApiModelProperty(value = "状态 [true-启用, false-禁用]", required = true)
    private Boolean status;

    /**
     * 执行参数信息
     */
    @ApiModelProperty(value = "执行参数信息")
    private String executeParam;

    /**
     * 执行结果信息
     */
    @ApiModelProperty(value = "执行结果信息")
    private String executeResult;

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

    /**
     * 操作机构主键
     */
    @Length(max = 100, message = "操作机构主键长度不能超过{max}")
    @ApiModelProperty(value = "操作机构主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private String operateDeptId;

    /**
     * 操作机构名称
     */
    @Length(max = 100, message = "操作机构名称长度不能超过{max}")
    @ApiModelProperty(value = "操作机构名称")
    private String operateDeptName;
}