/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.filter;

import com.github.lvyanyang.core.BasePageFilter;
import com.github.lvyanyang.core.XCI;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 系统操作日志过滤条件
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "系统操作日志过滤条件")
public class OperateLogFilter extends BasePageFilter {
    /**
     * 模块
     */
    @ApiModelProperty(value = "模块", position = 1)
    private String tag;

    /**
     * 操作方法
     */
    @ApiModelProperty(value = "操作方法", position = 2)
    private String method;

    /**
     * IP地址
     */
    @ApiModelProperty(value = "IP地址", position = 3)
    private String ip;

    /**
     * 请求地址
     */
    @ApiModelProperty(value = "请求地址", position = 4)
    private String reqUrl;

    /**
     * 应用主键
     */
    @ApiModelProperty(value = "应用主键", position = 5)
    private Long appId;

    /**
     * 应用名称
     */
    @ApiModelProperty(value = "应用名称", position = 6)
    private String appName;

    /**
     * 操作人姓名
     */
    @ApiModelProperty(value = "操作人姓名", position = 7)
    private String operateUserName;

    /**
     * 操作开始时间
     */
    @ApiModelProperty(value = "操作开始时间", position = 8)
    private Date operateStartDateTime;

    /**
     * 操作结束时间
     */
    @ApiModelProperty(value = "操作结束时间", position = 9)
    private Date operateEndDateTime;

    /// <summary>
    /// 处理结束日期,结束日期增加一天
    /// </summary>
    public void PlusEndDateTime() {
        setOperateEndDateTime(XCI.PlusEndDateTime(operateEndDateTime));
    }
}