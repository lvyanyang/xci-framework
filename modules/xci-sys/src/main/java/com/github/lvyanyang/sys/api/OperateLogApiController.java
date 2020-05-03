/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.api;

import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.annotation.SingleJson;
import com.github.lvyanyang.model.PageList;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.sys.entity.SysOperateLog;
import com.github.lvyanyang.sys.filter.OperateLogFilter;
import com.github.lvyanyang.sys.core.SysApiController;
import com.github.lvyanyang.sys.component.SysService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统操作日志控制器
 * @author 吕艳阳
 */
@Api(tags = "系统操作日志接口")
@ApiSort(35)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/operateLog", produces = R.PROJSON)
public class OperateLogApiController extends SysApiController {
    @ApiOperation(value = "根据主键查询单个操作日志")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "主键")
    @PostMapping(value = "/selectById")
    public RestResult<SysOperateLog> selectById(@SingleJson Long id) {
        return RestResult.ok(SysService.me().operateLogService().selectById(id));
    }

    @ApiOperation(value = "查询操作日志分页列表")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @PostMapping(value = "/selectPageList")
    public RestResult<PageList<SysOperateLog>> selectPageList(@RequestBody OperateLogFilter filter) {
        return RestResult.ok(SysService.me().operateLogService().selectPageList(filter));
    }
}