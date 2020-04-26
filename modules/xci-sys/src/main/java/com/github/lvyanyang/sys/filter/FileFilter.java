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
 * 系统文件过滤条件
 * @author 吕艳阳
 */
@Data @EqualsAndHashCode(callSuper = false)
@ApiModel(description = "系统文件过滤条件")
public class FileFilter extends BasePageFilter {
	/**
	 * 记录主键
	 */
	@ApiModelProperty(value = "记录主键")
	private String recordId;

	/**
	 * 文件分组
	 */
	@ApiModelProperty(value = "文件分组")
	private String category;
}