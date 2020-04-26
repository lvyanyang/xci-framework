/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 状态提交参数
 * @author 吕艳阳
 */
@Data
@ApiModel(description = "状态提交参数")
public class StatusBody {
    /**
     * 主键字符串,多个用逗号分割
     */
    @ApiModelProperty(value = "主键字符串,多个用逗号分割", required = true)
    private String ids;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    private Boolean status;
}
