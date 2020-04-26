package com.xci.sys.filter;

import com.xci.core.BasePageFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统序列过滤条件
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "系统序列过滤条件")
public class SeqFilter extends BasePageFilter {
    /**
     * 编码/名称关键字
     */
    @ApiModelProperty(value = "编码/名称关键字")
    private String name;
}