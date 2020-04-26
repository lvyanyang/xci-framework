package com.xci.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统对象关联
 * @author 吕艳阳
 */
@Data
@ApiModel(description = "系统对象关联")
public class SysObjectMap {
    /**
     * 对象名称
     */
    @ApiModelProperty(value = "对象名称")
    private String objectName;

    /**
     * 对象主键
     */
    @ApiModelProperty(value = "对象主键")
    private Long objectId;

    /**
     * 目标名称
     */
    @ApiModelProperty(value = "目标名称")
    private String targetName;

    /**
     * 目标主键
     */
    @ApiModelProperty(value = "目标主键")
    private Long targetId;
}