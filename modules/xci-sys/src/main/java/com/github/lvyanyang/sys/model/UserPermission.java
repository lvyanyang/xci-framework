/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.model;

import com.github.lvyanyang.sys.entity.SysDept;
import com.github.lvyanyang.sys.entity.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户权限
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-05-03 21:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户权限")
public class UserPermission extends RolePermission {
    /**
     * 用户所属机构
     */
    @ApiModelProperty(value = "用户所属机构")
    private SysDept dept;

    /**
     * 用户拥有的角色
     */
    @ApiModelProperty(value = "用户拥有的角色")
    private List<SysRole> roles;
}