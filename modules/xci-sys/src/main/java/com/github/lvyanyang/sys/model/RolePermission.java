/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.model;

import com.github.lvyanyang.sys.entity.SysModule;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 角色权限
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-05-03 21:51
 */
@Data
@ApiModel(description = "角色权限")
public class RolePermission {
    /**
     * 模块权限
     */
    @ApiModelProperty(value = "模块权限")
    private List<SysModule> modules;

    /**
     * 机构权限
     */
    @ApiModelProperty(value = "机构权限")
    private DeptScopePermission deptScopePermission;
}
