/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.api;

import com.github.lvyanyang.sys.core.SysApiController;
import com.github.lvyanyang.sys.entity.SysLoginLog;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.annotation.SingleJson;
import com.github.lvyanyang.model.PageList;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.sys.filter.LoginLogFilter;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统登陆日志控制器
 * @author 吕艳阳
 */
@Api(tags = "系统登陆日志接口")
@ApiSort(34)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/loginLog", produces = R.PROJSON)
public class LoginLogApiController extends SysApiController {
    @ApiOperation(value = "根据主键查询单个登陆日志")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "主键")
    @PostMapping(value = "/selectById")
    public RestResult<SysLoginLog> selectById(@SingleJson Long id) {
        return RestResult.ok(SysService.me().loginLogService().selectById(id));
    }

    @ApiOperation(value = "查询登陆日志分页列表")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @PostMapping(value = "/selectPageList")
    public RestResult<PageList<SysLoginLog>> selectPageList(@RequestBody LoginLogFilter filter) {
        return RestResult.ok(SysService.me().loginLogService().selectPageList(filter));
    }
}