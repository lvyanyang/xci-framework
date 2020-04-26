/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 上级名称提交参数
 * @author 吕艳阳
 */
@Data
@ApiModel(description = "上级名称提交参数")
public class ParentIdNameBody {
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    /**
     * 上级主键
     */
    @ApiModelProperty(value = "上级主键", required = true)
    private Long parentId;
}
