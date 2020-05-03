/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.model;

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

    /** 模块主键数组字符串 */
    @ApiModelProperty(value = "模块主键数组字符串")
    private String moduleIds;

    /** 机构权限*/
    @ApiModelProperty(value = "机构权限")
    private Integer deptScope;

    /** 自定义机构数据主键数组字符串 */
    @ApiModelProperty(value = "机构数据主键数组字符串")
    private String customDeptIds;
}
