/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.api;

import com.github.lvyanyang.annotation.AllowAnonymous;
import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.annotation.SingleJson;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.sys.model.OnlineUserModel;
import com.github.lvyanyang.sys.core.SysApiController;
import com.github.lvyanyang.sys.component.SysService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统在线用户控制器
 * @author 吕艳阳
 */
@Api(tags = "系统在线用户接口")
@ApiSort(15)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/onlineUser", produces = R.PROJSON)
public class OnlineUserApiController extends SysApiController {
    @ApiOperation(value = "激活在线用户")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiImplicitParam(name = "userId", value = "用户主键")
    @PostMapping("/active")
    public RestResult active(@SingleJson Long userId) {
        SysService.me().onlineUserService().active(userId);
        return RestResult.ok();
    }

    @ApiOperation(value = "注销在线用户")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @ApiImplicitParam(name = "userId", value = "用户主键")
    @AllowAnonymous
    @PostMapping("/logoff")
    public RestResult logoff(@SingleJson Long userId) {
        SysService.me().onlineUserService().logoff(userId);
        return RestResult.ok();
    }

    @ApiOperation(value = "根据账号注销在线用户")
    @ApiOperationSupport(order = 3, author = R.LYY)
    @ApiImplicitParam(name = "account", value = "用户账号")
    @AllowAnonymous
    @PostMapping("/logoffByAccount")
    public RestResult logoffByAccount(@SingleJson String account) {
        SysService.me().onlineUserService().logoffByAccount(account);
        return RestResult.ok();
    }

    @ApiOperation(value = "查询单个在线用户")
    @ApiOperationSupport(order = 3, author = R.LYY)
    @ApiImplicitParam(name = "userId", value = "用户主键")
    @PostMapping("/get")
    public RestResult<OnlineUserModel> get(@SingleJson Long userId) {
        return RestResult.ok(SysService.me().onlineUserService().get(userId));
    }

    @ApiOperation(value = "查询在线用户列表")
    @ApiOperationSupport(order = 4, author = R.LYY)
    @ApiImplicitParam(name = "name", value = "查询关键字")
    @PostMapping("/selectList")
    public RestResult<List<OnlineUserModel>> selectList(@SingleJson String name) {
        return RestResult.ok(SysService.me().onlineUserService().selectList(name));
    }

    @ApiOperation(value = "导出在线用户列表")
    @ApiOperationSupport(order = 5, author = R.LYY)
    @PostMapping(value = "/export", produces = {R.PROOCTET, R.PROJSON})
    public void export() {
        XCI.exportExcel(SysService.me().onlineUserService().selectList(null), OnlineUserModel.class, "系统在线用户列表");
    }
}