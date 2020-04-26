package com.xci.sys.filter;

import com.xci.core.BaseFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 系统模块过滤条件
 * @author 吕艳阳
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "系统模块过滤条件")
public class ModuleFilter extends BaseFilter {
	/**
	 * 状态 [true-启用, false-禁用]
	 */
	@ApiModelProperty(value = "状态 [true-启用, false-禁用]")
	private Boolean status;
}