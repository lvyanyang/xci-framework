/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.api;

import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.annotation.SingleJson;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.model.PageList;
import com.github.lvyanyang.model.StatusBody;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.core.SysApiController;
import com.github.lvyanyang.sys.entity.SysUser;
import com.github.lvyanyang.sys.entity.SysUserSave;
import com.github.lvyanyang.sys.filter.UserFilter;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统用户接口
 * @author 吕艳阳
 */
@Api(tags = "系统用户接口")
@ApiSort(11)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/user", produces = R.PROJSON)
public class UserApiController extends SysApiController {
    @ApiOperation(value = "是否存在指定账号的用户")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiImplicitParam(name = "account", value = "用户账号")
    @PostMapping(value = "/existByAccount")
    public RestResult existByAccount(@SingleJson String account) {
        return RestResult.ok(SysService.me().userService().existByAccount(account, null));
    }

    @ApiOperation(value = "新建用户")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @Authorize(code = R.Permission.SysUserInsert)
    @PostMapping(value = "/insert")
    public RestResult insert(@RequestBody SysUserSave entity) {
        return SysService.me().userService().insert(entity);
    }

    @ApiOperation(value = "修改用户")
    @ApiOperationSupport(order = 3, author = R.LYY)
    @Authorize(code = R.Permission.SysUserUpdate)
    @PostMapping(value = "/update")
    public RestResult update(@RequestBody SysUserSave entity) {
        return SysService.me().userService().update(entity);
    }

    @ApiOperation(value = "修改用户状态")
    @ApiOperationSupport(order = 4, author = R.LYY)
    @Authorize(code = R.Permission.SysUserUpdate)
    @PostMapping(value = "/updateStatus")
    public RestResult updateStatus(@RequestBody StatusBody statusBody) {
        SysService.me().userService().updateStatus(statusBody.getIds(), statusBody.getStatus());
        return RestResult.ok();
    }

    @ApiOperation(value = "删除用户")
    @ApiOperationSupport(order = 5, author = R.LYY)
    @ApiImplicitParam(name = "ids", value = "主键字符串")
    @Authorize(code = R.Permission.SysUserDelete)
    @PostMapping(value = "/delete")
    public RestResult delete(@SingleJson String ids) {
        SysService.me().userService().delete(ids);
        return RestResult.ok();
    }

    @ApiOperation(value = "根据主键查询单个用户")
    @ApiOperationSupport(order = 6, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "主键")
    @PostMapping(value = "/selectById")
    public RestResult<SysUser> selectById(@SingleJson Long id) {
        return RestResult.ok(SysService.me().userService().selectById(id));
    }

    @ApiOperation(value = "根据户账号查询单个用户")
    @ApiOperationSupport(order = 7, author = R.LYY)
    @ApiImplicitParam(name = "account", value = "用户账号")
    @PostMapping("/selectByAccount")
    public RestResult<SysUser> selectByAccount(@SingleJson String account) {
        return RestResult.ok(SysService.me().userService().selectByAccount(account));
    }

    @ApiOperation(value = "查询用户分页列表")
    @ApiOperationSupport(order = 8, author = R.LYY)
    @PostMapping(value = "/selectPageList")
    public RestResult<PageList<SysUser>> selectPageList(@RequestBody UserFilter filter) {
        return RestResult.ok(SysService.me().userService().selectPageList(filter));
    }

    @ApiOperation(value = "查询用户列表")
    @ApiOperationSupport(order = 9, author = R.LYY, ignoreParameters = {R.IPI, R.IPS, R.IPSN, R.IPSD})
    @PostMapping(value = "/selectList")
    public RestResult<List<SysUser>> selectList(@RequestBody UserFilter filter) {
        return RestResult.ok(SysService.me().userService().selectList(filter));
    }

    @ApiOperation(value = "导出用户列表")
    @ApiOperationSupport(order = 10, author = R.LYY, ignoreParameters = {R.IPI, R.IPS, R.IPSN, R.IPSD})
    @PostMapping(value = "/export", produces = {R.PROOCTET, R.PROJSON})
    public void export(@RequestBody UserFilter filter) {
        XCI.exportExcel(SysService.me().userService().selectList(filter), SysUser.class, "系统用户列表");
    }

    @ApiOperation(value = "刷新用户缓存")
    @ApiOperationSupport(order = 11, author = R.LYY)
    @PostMapping(value = "/refresh")
    public RestResult refresh() {
        SysService.me().userService().refresh();
        return RestResult.ok();
    }
    //endregion
}