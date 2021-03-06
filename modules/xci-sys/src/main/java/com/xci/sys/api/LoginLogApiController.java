package com.xci.sys.api;

import com.xci.annotation.Authorize;
import com.xci.annotation.SingleJson;
import com.xci.sys.entity.SysLoginLog;
import com.xci.model.PageList;
import com.xci.core.R;
import com.xci.core.RestResult;
import com.xci.sys.filter.LoginLogFilter;
import com.xci.sys.core.SysApiController;
import com.xci.sys.service.SysService;
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
@ApiSort(10)
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