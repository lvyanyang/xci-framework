package com.xci.sys.api;

import com.xci.annotation.Authorize;
import com.xci.annotation.SingleJson;
import com.xci.model.PageList;
import com.xci.core.R;
import com.xci.core.RestResult;
import com.xci.sys.entity.SysHistoryLog;
import com.xci.sys.filter.HistoryLogFilter;
import com.xci.sys.core.SysApiController;
import com.xci.sys.service.SysService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统历史日志控制器
 * @author 吕艳阳
 */
@Api(tags = "系统历史日志接口")
@ApiSort(11)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/historyLog", produces = R.PROJSON)
public class HistoryLogApiController extends SysApiController {
    @ApiOperation(value = "根据主键查询单个历史日志")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "主键")
    @PostMapping(value = "/selectById")
    public RestResult<SysHistoryLog> selectById(@SingleJson Long id) {
        return RestResult.ok(SysService.me().historyLogService().selectById(id));
    }

    @ApiOperation(value = "查询历史日志分页列表")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @PostMapping(value = "/selectPageList")
    public RestResult<PageList<SysHistoryLog>> selectPageList(@RequestBody HistoryLogFilter filter) {
        return RestResult.ok(SysService.me().historyLogService().selectPageList(filter));
    }
}