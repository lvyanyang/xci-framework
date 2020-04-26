package com.xci.sys.filter;

import com.xci.core.BaseFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


/**
 * 系统字典过滤条件
 * @author 吕艳阳
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "系统字典过滤条件")
public class DicFilter extends BaseFilter {
    /**
     * 字典名称/简拼关键字
     */
    @ApiModelProperty(value = "字典名称/简拼/编码关键字")
    private String name;

    /**
     * 字典类型编码
     */
    @ApiModelProperty(value = "字典类型编码")
    private String categoryCode;

    /**
     * 状态 [true-启用, false-禁用]
     */
    @ApiModelProperty(value = "状态 [true-启用, false-禁用]")
    private Boolean status;
}