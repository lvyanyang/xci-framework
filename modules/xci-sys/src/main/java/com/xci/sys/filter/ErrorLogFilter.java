package com.xci.sys.filter;

import com.xci.core.BasePageFilter;
import com.xci.core.XCI;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 系统错误日志过滤条件
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统错误日志过滤条件")
public class ErrorLogFilter extends BasePageFilter {
    /**
     * 请求地址
     */
    @ApiModelProperty(value = "请求地址", position = 1)
    private String reqUrl;

    /**
     * IP地址
     */
    @ApiModelProperty(value = "IP地址", position = 2)
    private String ip;

    /**
     * 应用主键
     */
    @ApiModelProperty(value = "应用主键", position = 3)
    private String appId;

    /**
     * 应用名称
     */
    @ApiModelProperty(value = "应用名称", position = 4)
    private String appName;

    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人", position = 5)
    private String operateUserName;

    /**
     * 操作开始时间
     */
    @ApiModelProperty(value = "操作开始时间", position = 6)
    private Date operateStartDateTime;

    /**
     * 操作结束时间
     */
    @ApiModelProperty(value = "操作结束时间", position = 7)
    private Date operateEndDateTime;

    /// <summary>
    /// 处理结束日期,结束日期增加一天
    /// </summary>
    public void PlusEndDateTime() {
        setOperateEndDateTime(XCI.PlusEndDateTime(operateEndDateTime));
    }
}