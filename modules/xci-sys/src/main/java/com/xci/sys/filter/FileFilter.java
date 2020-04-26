package com.xci.sys.filter;

import com.xci.core.BasePageFilter;
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