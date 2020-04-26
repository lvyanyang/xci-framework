package com.xci.sys.api;

import com.xci.annotation.Authorize;
import com.xci.annotation.SingleJson;
import com.xci.sys.model.LockUserModel;
import com.xci.core.R;
import com.xci.core.RestResult;
import com.xci.core.XCI;
import com.xci.sys.core.SysApiController;
import com.xci.sys.service.SysService;
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
@ApiSort(9)
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