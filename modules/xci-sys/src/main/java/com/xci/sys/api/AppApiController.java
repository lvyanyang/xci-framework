package com.xci.sys.api;


import com.xci.annotation.Authorize;
import com.xci.annotation.SingleJson;
import com.xci.model.StatusBody;
import com.xci.sys.entity.SysApp;
import com.xci.model.PageList;
import com.xci.core.R;
import com.xci.core.RestResult;
import com.xci.sys.filter.AppFilter;
import com.xci.sys.core.SysApiController;
import com.xci.sys.service.SysService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统应用控制器
 * @author 吕艳阳
 */
@Api(tags = "系统应用接口")
@ApiSort(4)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/app", produces = R.PROJSON)
public class AppApiController extends SysApiController {
    @ApiOperation(value = "检查应用名称是否存在")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiImplicitParam(name = "name", value = "名称")
    @PostMapping(value = "/existByName")
    public RestResult existByName(@SingleJson String name) {
        return RestResult.ok(SysService.me().appService().existByName(name, null));
    }

    @ApiOperation(value = "新建应用")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @Authorize(code = R.Permission.SysAppInsert)
    @PostMapping(value = "/insert")
    public RestResult insert(@RequestBody SysApp entity) {
        return SysService.me().appService().insert(entity);
    }

    @ApiOperation(value = "修改应用")
    @ApiOperationSupport(order = 3, author = R.LYY)
    @Authorize(code = R.Permission.SysAppUpdate)
    @PostMapping(value = "/update")
    public RestResult update(@RequestBody SysApp entity) {
        return SysService.me().appService().update(entity);
    }

    @ApiOperation(value = "修改应用状态")
    @ApiOperationSupport(order = 4, author = R.LYY)
    @Authorize(code = R.Permission.SysAppUpdate)
    @PostMapping(value = "/updateStatus")
    public RestResult updateStatus(@RequestBody StatusBody statusBody) {
        return SysService.me().appService().updateStatus(statusBody.getIds(), statusBody.getStatus());
    }

    @ApiOperation(value = "删除应用")
    @ApiOperationSupport(order = 5, author = R.LYY)
    @ApiImplicitParam(name = "ids", value = "主键字符串")
    @Authorize(code = R.Permission.SysAppDelete)
    @PostMapping(value = "/delete")
    public RestResult delete(@SingleJson String ids) {
        SysService.me().appService().delete(ids);
        return RestResult.ok();
    }

    @ApiOperation(value = "根据主键查询单个应用")
    @ApiOperationSupport(order = 6, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "主键")
    @PostMapping(value = "/selectById")
    public RestResult<SysApp> selectById(@SingleJson Long id) {
        return RestResult.ok(SysService.me().appService().selectById(id));
    }

    @ApiOperation(value = "查询应用列表")
    @ApiOperationSupport(order = 7, author = R.LYY, ignoreParameters = {R.IG_PAGE_INDEX, R.IG_PAGE_SIZE, R.IG_SORT_NAME, R.IG_SORT_DIR})
    @PostMapping(value = "/selectList")
    public RestResult<List<SysApp>> selectList(@RequestBody AppFilter filter) {
        return RestResult.ok(SysService.me().appService().selectList(filter));
    }

    @ApiOperation(value = "查询应用分页列表")
    @ApiOperationSupport(order = 8, author = R.LYY)
    @PostMapping(value = "/selectPageList")
    public RestResult<PageList<SysApp>> selectPageList(@RequestBody AppFilter filter) {
        return RestResult.ok(SysService.me().appService().selectPageList(filter));
    }

    @ApiOperation(value = "刷新应用缓存")
    @ApiOperationSupport(order = 9, author = R.LYY)
    @PostMapping(value = "/refresh")
    public RestResult refresh() {
        SysService.me().appService().refresh();
        return RestResult.ok();
    }
}