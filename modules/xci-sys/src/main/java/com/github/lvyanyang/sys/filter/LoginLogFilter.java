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
 * 系统登录日志过滤条件
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统登录日志过滤条件")
public class LoginLogFilter extends BasePageFilter {
	/**
	 * 登录账号
	 */
	@ApiModelProperty(value = "登录账号", position = 1)
	private String account;

	/**
	 * IP地址
	 */
	@ApiModelProperty(value = "IP地址", position = 2)
	private String ip;

	/**
	 * 类型 [1-登录, 2-注销]
	 */
	@ApiModelProperty(value = "类型 [1-登录, 2-注销]", position = 3)
	private Integer category;

	/**
	 * 登录状态 [true-成功, false-失败]
	 */
	@ApiModelProperty(value = "登录状态 [true-成功, false-失败]", position = 4)
	private Boolean status;

	/**
	 * 应用主键
	 */
	@ApiModelProperty(value = "应用主键", position = 5)
	private String appId;

	/**
	 * 应用名称
	 */
	@ApiModelProperty(value = "应用名称", position = 6)
	private String appName;

	/**
	 * 操作开始时间
	 */
	@ApiModelProperty(value = "操作开始时间", position = 7)
	private Date operateStartDateTime;

	/**
	 * 操作结束时间
	 */
	@ApiModelProperty(value = "操作结束时间", position = 8)
	private Date operateEndDateTime;

	/// <summary>
	/// 处理结束日期,结束日期增加一天
	/// </summary>
	public void PlusEndDateTime() {
		setOperateEndDateTime(XCI.PlusEndDateTime(operateEndDateTime));
	}
}