/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.api;

import com.github.lvyanyang.sys.core.Params;
import com.github.lvyanyang.sys.core.SysApiController;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.internal.ServletUtil;
import com.github.lvyanyang.core.XCI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiSort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 系统服务控制器
 * @author 吕艳阳
 */
@Api(tags = "系统服务接口")
@ApiSort(1)
@RestController
@RequestMapping(value = R.SysApiPrefix + "/sys", produces = R.PROJSON)
public class ServerApiController extends SysApiController {
    @ApiOperation(value = "网络测试")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @PostMapping("/ping")
    public RestResult ping() {
        return RestResult.ok(System.currentTimeMillis());
    }

    @ApiOperation(value = "获取服务器名称")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @PostMapping("/name")
    public RestResult serverName() {
        return RestResult.ok(Params.sysServerName());
    }

    @ApiOperation(value = "获取客户端IP地址")
    @ApiOperationSupport(order = 3, author = R.LYY)
    @PostMapping("/clientIP")
    public RestResult clientIP() {
        return RestResult.ok(ServletUtil.getClientIP(getRequest()));
    }

    @ApiOperation(value = "获取服务器时间")
    @ApiOperationSupport(order = 4, author = R.LYY)
    @PostMapping("/datetime")
    public RestResult datetime() {
        return RestResult.ok(XCI.formatDate(new Date(), R.DEFAULT_DATETIME_PATTERN));
    }

    @ApiOperation(value = "创建新ID")
    @ApiOperationSupport(order = 5, author = R.LYY)
    @PostMapping("/newId")
    public RestResult newId() {
        return RestResult.ok(XCI.nextIdStr());
    }

    @ApiOperation(value = "创建GUID")
    @ApiOperationSupport(order = 6, author = R.LYY)
    @PostMapping("/newGuid")
    public RestResult newGuid() {
        return RestResult.ok(XCI.guid());
    }

    // @ApiOperation(value = "获取服务器信息")
    // @ApiOperationSupport(order = 7)
    // @PostMapping("/serverInfo")
    // public RestResult<Server> ServerInfo() {
    //     Server server = new Server();
    //     server.init();
    //     return RestResult.ok(server);
    // }
}