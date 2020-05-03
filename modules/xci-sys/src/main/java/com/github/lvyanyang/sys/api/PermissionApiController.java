/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.api;

import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.annotation.SingleJson;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.model.PermissionBody;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.core.SysApiController;
import com.github.lvyanyang.sys.model.RolePermission;
import com.github.lvyanyang.sys.model.UserPermission;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统账户权限接口
 * @author 吕艳阳
 */
@Api(tags = "系统账户权限接口")
@ApiSort(13)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/permission", produces = R.PROJSON)
public class PermissionApiController extends SysApiController {
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiOperation(value = "判断指定用户是否拥有指定编码的功能模块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户主键"),
            @ApiImplicitParam(name = "code", value = "编码字符串,多个编码逗号隔开,多个编码必须全部拥有"),
    })
    @PostMapping("/isAuthModule")
    public RestResult<Boolean> isAuthModule(@SingleJson Long userId, @SingleJson String code) {
        return RestResult.ok(SysService.me().permissionService().isAuthModule(userId, code));
    }

    @ApiOperationSupport(order = 2, author = R.LYY)
    @ApiOperation(value = "从缓存中查询用户权限")
    @ApiImplicitParam(name = "userId", value = "用户主键")
    @PostMapping("/selectUserPermissionFromCache")
    public RestResult<UserPermission> selectUserPermissionFromCache(@SingleJson Long userId) {
        return RestResult.ok(SysService.me().permissionService().selectUserPermissionFromCache(userId));
    }

    @ApiOperationSupport(order = 3, author = R.LYY)
    @ApiOperation(value = "清除缓存中的用户权限")
    @ApiImplicitParam(name = "userId", value = "用户主键")
    @PostMapping("/clearUserPermissionCache")
    public RestResult clearUserPermissionCache(@SingleJson Long userId) {
        SysService.me().permissionService().clearUserPermissionCache(userId);
        return RestResult.ok();
    }

    @ApiOperationSupport(order = 4, author = R.LYY)
    @ApiOperation(value = "查询用户权限")
    @ApiImplicitParam(name = "userId", value = "用户主键")
    @PostMapping("/selectUserPermission")
    public RestResult<UserPermission> selectUserPermission(@SingleJson Long userId) {
        return RestResult.ok(SysService.me().permissionService().selectUserPermission(userId));
    }

    @ApiOperationSupport(order = 5, author = R.LYY)
    @ApiOperation(value = "保存角色权限")
    @PostMapping("/saveRolePermission")
    public RestResult saveRolePermission(@RequestBody PermissionBody permissionModel) {
        SysService.me().permissionService().saveRolePermission(permissionModel);
        return RestResult.ok();
    }

    @ApiOperationSupport(order = 6, author = R.LYY)
    @ApiOperation(value = "清除角色权限")
    @ApiImplicitParam(name = "roleId", value = "角色主键")
    @PostMapping("/clearRolePermission")
    public RestResult clearRolePermission(@SingleJson Long roleId) {
        SysService.me().permissionService().clearRolePermission(roleId);
        return RestResult.ok();
    }

    @ApiOperationSupport(order = 7, author = R.LYY)
    @ApiOperation(value = "查询角色权限")
    @ApiImplicitParam(name = "roleId", value = "角色主键")
    @PostMapping("/selectRolePermission")
    public RestResult<RolePermission> selectRolePermission(@SingleJson Long roleId) {
        return RestResult.ok(SysService.me().permissionService().selectRolePermission(roleId));
    }
}