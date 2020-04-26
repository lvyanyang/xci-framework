/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 权限保存参数
 * @author 吕艳阳
 */
@Data
@ApiModel(description = "权限保存参数")
public class PermissionBody {
    /** 角色主键字符串 */
    @ApiModelProperty(value = "角色主键字符串")
    private String roleIds;

    /** 机构数据权限 [1-全部, 2-自定义, 3-所在部门, 4-所在部门及所有下级, 5-仅本人] */
    @ApiModelProperty(value = "机构数据权限 [1-全部, 2-自定义, 3-所在部门, 4-所在部门及所有下级, 5-仅本人]")
    private Integer deptScope;

    /** 自定义机构数据主键数组字符串 */
    @ApiModelProperty(value = "机构数据主键数组字符串")
    private String deptIds;

    /** 模块主键数组字符串 */
    @ApiModelProperty(value = "模块主键数组字符串")
    private String moduleIds;
}
