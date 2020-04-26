package com.xci.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统用户角色关联
 * @author 吕艳阳
 */
@Data
@ApiModel(description = "系统用户角色关联")
public class SysUserRoleMap {
    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private Long userId;

    /**
     * 角色主键
     */
    @ApiModelProperty(value = "角色主键")
    private Long roleId;
}