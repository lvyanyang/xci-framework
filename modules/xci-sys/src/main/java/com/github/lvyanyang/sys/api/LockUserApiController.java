/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.api;

import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.annotation.SingleJson;
import com.github.lvyanyang.sys.model.LockUserModel;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.sys.core.SysApiController;
import com.github.lvyanyang.sys.component.SysService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统锁定用户控制器
 * @author 吕艳阳
 */
@Api(tags = "系统锁定用户接口")
@ApiSort(16)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/lockUser", produces = R.PROJSON)
public class LockUserApiController extends SysApiController {
    @ApiOperation(value = "添加锁定用户")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiImplicitParam(name = "account", value = "账号")
    @PostMapping("/add")
    public RestResult add(@SingleJson String account) {
        SysService.me().lockUserService().add(account);
        return RestResult.ok();
    }

    @ApiOperation(value = "移除锁定用户")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @ApiImplicitParam(name = "account", value = "账号")
    @PostMapping("/remove")
    public RestResult remove(@SingleJson String account) {
        SysService.me().lockUserService().remove(account);
        return RestResult.ok();
    }

    @ApiOperation(value = "查询单个锁定用户")
    @ApiOperationSupport(order = 4, author = R.LYY)
    @ApiImplicitParam(name = "account", value = "账号")
    @PostMapping("/get")
    public RestResult<LockUserModel> get(@SingleJson String account) {
        return RestResult.ok(SysService.me().lockUserService().get(account));
    }

    @ApiOperation(value = "查询锁定用户列表")
    @ApiOperationSupport(order = 5, author = R.LYY)
    @PostMapping(value = "/selectList")
    public RestResult<List<LockUserModel>> selectList() {
        return RestResult.ok(SysService.me().lockUserService().selectList());
    }

    @ApiOperation(value = "导出锁定用户列表")
    @ApiOperationSupport(order = 6, author = R.LYY)
    @PostMapping(value = "/export", produces = {R.PROOCTET, R.PROJSON})
    public void export() {
        XCI.exportExcel(SysService.me().lockUserService().selectList(), LockUserModel.class, "系统锁定用户列表");
    }
}