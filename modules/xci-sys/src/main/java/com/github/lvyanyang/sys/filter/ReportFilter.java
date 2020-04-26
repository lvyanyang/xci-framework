/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.filter;

import com.github.lvyanyang.core.BasePageFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统报表过滤条件
 * @author 吕艳阳
 */
@Data @EqualsAndHashCode(callSuper = false)
@ApiModel(description = "系统报表过滤条件")
public class ReportFilter extends BasePageFilter {
	/**
	 * 主键/名称关键字
	 */
	@ApiModelProperty(value = "主键/名称关键字")
	private String name;

	/**
	 * 报表类型
	 */
	@ApiModelProperty(value = "报表类型")
	private Integer category;

	/**
	 * 状态 [true-启用, false-禁用]
	 */
	@ApiModelProperty(value = "状态 [true-启用, false-禁用]")
	private Boolean status;
}