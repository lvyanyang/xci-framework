/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.lvyanyang.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 系统定时任务日志
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统定时任务日志")
public class SysJobLog extends BaseEntity {
    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 任务主键
     */
    @NotNull(message = "请输入任务主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "任务主键", required = true)
    private Long jobId;

    /**
     * 任务名称
     */
    @NotBlank(message = "请输入任务名称")
    @Length(max = 500, message = "任务名称长度不能超过{max}")
    @ApiModelProperty(value = "任务名称", required = true)
    private String jobName;

    /**
     * 任务表达式
     */
    @NotBlank(message = "请输入任务表达式")
    @Length(max = 1000, message = "任务表达式长度不能超过{max}")
    @ApiModelProperty(value = "任务表达式", required = true)
    private String jobExpression;

    /**
     * 任务执行消息
     */
    @Length(max = 1000, message = "任务执行消息长度不能超过{max}")
    @ApiModelProperty(value = "任务执行消息")
    private String msg;

    /**
     * 任务执行错误信息
     */
    @Excel(name = "任务执行错误信息", width = 20d)
    @ApiModelProperty(value = "任务执行错误信息")
    private String errorMsg;

    /**
     * 执行类型 [1-手动触发, 0-系统触发]
     */
    @NotNull(message = "请输入执行类型")
    @ApiModelProperty(value = "执行类型 [1-手动触发, 0-系统触发]", required = true)
    private Integer triggerCategory;

    /**
     * 执行状态 [true-成功, false-失败]
     */
    @NotNull(message = "请输入执行状态")
    @ApiModelProperty(value = "执行状态 [true-成功, false-失败]", required = true)
    private Boolean status;

    /**
     * 创建时间
     */
    @NotNull(message = "请输入创建时间")
    @ApiModelProperty(value = "创建时间", required = true)
    private Date operateDateTime;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startDateTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endDateTime;
}