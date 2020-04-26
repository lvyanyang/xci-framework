/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.lvyanyang.core.BaseEntity;
import com.github.lvyanyang.core.R;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 系统定时任务
 * @author 吕艳阳
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "系统定时任务")
public class SysJob extends BaseEntity {
	/**
	 * 任务主键
	 */
	@ExcelIgnore
	@Excel(name = "任务主键", width = 20d)
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "任务主键", required = true, position = 1)
	private Long id;

	/**
	 * 任务名称
	 */
	@NotBlank(message = "请输入任务名称")
	@Length(max = 100, message = "任务名称长度不能超过{max}")
	@Excel(name = "任务名称", width = 20d)
	@ApiModelProperty(value = "任务名称", required = true, position = 2)
	private String name;

	/**
	 * 名称简拼
	 */
	@Length(max = 100, message = "名称简拼不能超过{max}个字符")
	@ExcelIgnore@Excel(name = "简拼")
	@ApiModelProperty(value = "名称简拼", position = 4)
	private String spell;

	/**
	 * 任务组
	 */
	@NotBlank(message = "请输入任务组")
	@Length(max = 100, message = "任务组长度不能超过{max}")
	@Excel(name = "任务组", width = 20d)
	@ApiModelProperty(value = "任务组", required = true, position = 3)
	private String jobGroup;

	/**
	 * 任务表达式
	 */
	@NotBlank(message = "请输入任务表达式")
	@Length(max = 500, message = "任务表达式长度不能超过{max}")
	@Excel(name = "任务表达式", width = 20d)
	@ApiModelProperty(value = "任务表达式", required = true, position = 4)
	private String jobExpression;

	/**
	 * 触发表达式
	 */
	@NotBlank(message = "请输入触发表达式")
	@Length(max = 500, message = "触发表达式长度不能超过{max}")
	@Excel(name = "触发表达式", width = 20d)
	@ApiModelProperty(value = "触发表达式", required = true, position = 5)
	private String cronExpression;

	/**
	 * 计划执行错误策略 [1-立即执行, 2-执行一次, 3-放弃执行]
	 */
	@NotNull(message = "请输入计划执行错误策略")
	@Excel(name = "计划执行错误策略", width = 20d, replace = { "立即执行_1","执行一次_2","放弃执行_3" })
	@ApiModelProperty(value = "计划执行错误策略 [1-立即执行, 2-执行一次, 3-放弃执行]", required = true, position = 6)
	private Integer misfirePolicy;

	/**
	 * 是否允许并发 [true-允许, false-禁止]
	 */
	@NotNull(message = "请输入是否允许并发")
	@Excel(name = "是否允许并发", width = 20d, replace = { "允许_true", "禁止_false" })
	@ApiModelProperty(value = "是否允许并发 [true-允许, false-禁止]", required = true, position = 7)
	private Boolean concurrent;

	/**
	 * 状态 [true-启用, false-禁用]
	 */
	@NotNull(message = "请指定状态")
	@Excel(name = "状态", replace = {R.EnabledStatusReplace, R.DisabledStatusReplace})
	@ApiModelProperty(value = "状态 [true-启用, false-禁用]", required = true, position = 8)
	private Boolean status;

	/**
	 * 备注
	 */
	@Length(max = 500, message = "备注长度不能超过{max}")
	@Excel(name = "备注", width = 20d)
	@ApiModelProperty(value = "备注", position = 9)
	private String remark;
}
