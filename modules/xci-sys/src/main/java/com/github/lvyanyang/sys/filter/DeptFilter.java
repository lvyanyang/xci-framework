/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.filter;

import com.github.lvyanyang.core.BaseFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统机构过滤条件
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "系统机构过滤条件")
public class DeptFilter extends BaseFilter {
	/**
	 * 名称/编码关键字
	 */
	@ApiModelProperty(value = "名称/编码关键字")
	private String name;

	/**
	 * 状态 [true-启用, false-禁用]
	 */
	@ApiModelProperty(value = "状态 [true-启用, false-禁用]")
	private Boolean status;

	/**
	 * 是否启用数据权限过滤 [true-启用, false-禁用]
	 */
	@ApiModelProperty(value = "是否启用数据权限过滤 [true-启用, false-禁用]")
	private Boolean dataScope;
}