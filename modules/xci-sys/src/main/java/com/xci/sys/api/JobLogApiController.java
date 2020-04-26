package com.xci.sys.api;

import com.xci.annotation.Authorize;
import com.xci.annotation.SingleJson;
import com.xci.sys.entity.SysJobLog;
import com.xci.model.PageList;
import com.xci.core.R;
import com.xci.core.RestResult;
import com.xci.sys.filter.JobLogFilter;
import com.xci.sys.core.SysApiController;
import com.xci.sys.service.SysService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统定时任务日志控制器
 * @author 吕艳阳
 */
@Api(tags = "系统定时任务日志接口")
@ApiSort(10)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/jobLog", produces = R.PROJSON)
public class JobLogApiController extends SysApiController {
    @ApiOperation(value = "根据主键查询单个定时任务日志")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "主键")
    @PostMapping(value = "/selectById")
    public RestResult<SysJobLog> selectById(@SingleJson Long id) {
        return RestResult.ok(SysService.me().jobLogService().selectById(id));
    }

    @ApiOperation(value = "查询定时任务日志分页列表")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @PostMapping(value = "/selectPageList")
    public RestResult<PageList<SysJobLog>> selectPageList(@RequestBody JobLogFilter filter) {
        return RestResult.ok(SysService.me().jobLogService().selectPageList(filter));
    }
}