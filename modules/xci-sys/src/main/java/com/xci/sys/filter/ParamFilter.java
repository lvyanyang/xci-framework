package com.xci.sys.filter;

import com.xci.core.BasePageFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统参数过滤条件
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "系统参数过滤条件")
public class ParamFilter extends BasePageFilter {
    /**
     * 参数名称/编码关键字
     */
    @ApiModelProperty(value = "参数名称/编码关键字", position = 1)
    private String name;

    /**
     * 参数类型
     */
    @ApiModelProperty(value = "参数类型", position = 2)
    private Integer category;
}