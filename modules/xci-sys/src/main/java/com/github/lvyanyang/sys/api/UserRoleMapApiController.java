/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.api;

import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.annotation.SingleJson;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.core.SysApiController;
import com.github.lvyanyang.sys.entity.SysRole;
import com.github.lvyanyang.sys.entity.SysUser;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统用户角色关联接口
 * @author 吕艳阳
 */
@Api(tags = "系统用户角色关联接口")
@ApiSort(14)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/userRoleMap", produces = R.PROJSON)
public class UserRoleMapApiController extends SysApiController {
    //#region MapRole

    @ApiOperation(value = "添加用户角色")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户主键"),
            @ApiImplicitParam(name = "roleIds", value = "角色主键字符串")
    })
    @PostMapping("/insertMapRole")
    public RestResult insertMapRole(@SingleJson Long userId, @SingleJson String roleIds) {
        SysService.me().userRoleMapService().insertMapRole(userId, roleIds);
        return RestResult.ok();
    }

    @ApiOperation(value = "删除用户角色")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户主键"),
            @ApiImplicitParam(name = "roleIds", value = "角色主键字符串")
    })
    @PostMapping("/deleteMapRole")
    public RestResult deleteMapRole(@SingleJson Long userId, @SingleJson String roleIds) {
        SysService.me().userRoleMapService().deleteMapRole(userId, roleIds);
        return RestResult.ok();
    }

    @ApiOperation(value = "清除并添加用户角色")
    @ApiOperationSupport(order = 3, author = R.LYY)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户主键"),
            @ApiImplicitParam(name = "roleIds", value = "角色主键字符串")
    })
    @PostMapping("/saveMapRole")
    public RestResult saveMapRole(@SingleJson Long userId, @SingleJson String roleIds) {
        SysService.me().userRoleMapService().saveMapRole(userId, roleIds);
        return RestResult.ok();
    }

    @ApiOperation(value = "清除用户角色")
    @ApiOperationSupport(order = 4, author = R.LYY)
    @ApiImplicitParam(name = "userId", value = "用户主键")
    @PostMapping("/clearMapRole")
    public RestResult clearMapRole(@SingleJson Long userId) {
        SysService.me().userRoleMapService().clearMapRole(userId);
        return RestResult.ok();
    }

    @ApiOperation(value = "查询用户角色主键列表")
    @ApiOperationSupport(order = 5, author = R.LYY)
    @ApiImplicitParam(name = "userId", value = "用户主键")
    @PostMapping("/selectMapRoleIds")
    public RestResult<List<String>> selectMapRoleIds(@SingleJson Long userId) {
        return RestResult.ok(SysService.me().userRoleMapService().selectMapRoleIds(userId));
    }

    @ApiOperation(value = "查询用户角色列表")
    @ApiOperationSupport(order = 6, author = R.LYY)
    @ApiImplicitParam(name = "userId", value = "用户主键")
    @PostMapping("/selectMapRoleList")
    public RestResult<List<SysRole>> selectMapRoleList(@SingleJson Long userId) {
        return RestResult.ok(SysService.me().userRoleMapService().selectMapRoleList(userId));
    }

    @ApiOperation(value = "查询用户未关联角色主键列表")
    @ApiOperationSupport(order = 7, author = R.LYY)
    @ApiImplicitParam(name = "userId", value = "用户主键")
    @PostMapping("/selectUnMapRoleIds")
    public RestResult<List<String>> selectUnMapRoleIds(@SingleJson Long userId) {
        return RestResult.ok(SysService.me().userRoleMapService().selectUnMapRoleIds(userId));
    }

    @ApiOperation(value = "查询用户未关联角色列表")
    @ApiOperationSupport(order = 8, author = R.LYY)
    @ApiImplicitParam(name = "userId", value = "用户主键")
    @PostMapping("/selectUnMapRoleList")
    public RestResult<List<SysRole>> selectUnMapRoleList(@SingleJson Long userId) {
        return RestResult.ok(SysService.me().userRoleMapService().selectUnMapRoleList(userId));
    }

    //#endregion

    //#region MapUser

    @ApiOperation(value = "添加角色用户")
    @ApiOperationSupport(order = 9, author = R.LYY)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色主键"),
            @ApiImplicitParam(name = "userIds", value = "用户主键字符串")
    })
    @PostMapping("/insertMapUser")
    public RestResult insertMapUser(@SingleJson Long roleId, @SingleJson String userIds) {
        SysService.me().userRoleMapService().insertMapUser(roleId, userIds);
        return RestResult.ok();
    }

    @ApiOperation(value = "删除角色用户")
    @ApiOperationSupport(order = 10, author = R.LYY)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色主键"),
            @ApiImplicitParam(name = "userIds", value = "用户主键字符串")
    })
    @PostMapping("/deleteMapUser")
    public RestResult deleteMapUser(@SingleJson Long roleId, @SingleJson String userIds) {
        SysService.me().userRoleMapService().deleteMapUser(roleId, userIds);
        return RestResult.ok();
    }

    @ApiOperation(value = "清除并添加角色用户")
    @ApiOperationSupport(order = 11, author = R.LYY)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色主键"),
            @ApiImplicitParam(name = "userIds", value = "用户主键字符串")
    })
    @PostMapping("/saveMapUser")
    public RestResult saveMapUser(@SingleJson Long roleId, @SingleJson String userIds) {
        SysService.me().userRoleMapService().saveMapUser(roleId, userIds);
        return RestResult.ok();
    }

    @ApiOperation(value = "清除角色用户")
    @ApiOperationSupport(order = 12, author = R.LYY)
    @ApiImplicitParam(name = "roleId", value = "角色主键")
    @PostMapping("/clearMapUser")
    public RestResult clearMapUser(@SingleJson Long roleId) {
        SysService.me().userRoleMapService().clearMapUser(roleId);
        return RestResult.ok();
    }

    @ApiOperation(value = "查询角色用户主键列表")
    @ApiOperationSupport(order = 13, author = R.LYY)
    @ApiImplicitParam(name = "roleId", value = "角色主键")
    @PostMapping("/selectMapUserIds")
    public RestResult<List<String>> selectMapUserIds(@SingleJson Long roleId) {
        return RestResult.ok(SysService.me().userRoleMapService().selectMapUserIds(roleId));
    }

    @ApiOperation(value = "查询角色用户列表")
    @ApiOperationSupport(order = 14, author = R.LYY)
    @ApiImplicitParam(name = "roleId", value = "角色主键")
    @PostMapping("/selectMapUserList")
    public RestResult<List<SysUser>> selectMapUserList(@SingleJson Long roleId) {
        return RestResult.ok(SysService.me().userRoleMapService().selectMapUserList(roleId));
    }

    @ApiOperation(value = "查询未关联角色用户主键列表")
    @ApiOperationSupport(order = 15, author = R.LYY)
    @ApiImplicitParam(name = "roleId", value = "角色主键")
    @PostMapping("/selectUnMapUserIds")
    public RestResult<List<String>> selectUnMapUserIds(@SingleJson Long roleId) {
        return RestResult.ok(SysService.me().userRoleMapService().selectUnMapUserIds(roleId));
    }

    @ApiOperation(value = "查询未关联角色用户列表")
    @ApiOperationSupport(order = 16, author = R.LYY)
    @ApiImplicitParam(name = "roleId", value = "角色主键")
    @PostMapping("/selectUnMapUserList")
    public RestResult<List<SysUser>> selectUnMapUserList(@SingleJson Long roleId) {
        return RestResult.ok(SysService.me().userRoleMapService().selectUnMapUserList(roleId));
    }

    //#endregion
}