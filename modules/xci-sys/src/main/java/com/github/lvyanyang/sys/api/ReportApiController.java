/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.api;

import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.annotation.SingleJson;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.model.PageList;
import com.github.lvyanyang.model.StatusBody;
import com.github.lvyanyang.sys.core.SysApiController;
import com.github.lvyanyang.sys.entity.SysReport;
import com.github.lvyanyang.sys.filter.ReportFilter;
import com.github.lvyanyang.sys.component.SysService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统报表控制器
 * @author 吕艳阳
 */
@Api(tags = "系统报表接口")
@ApiSort(15)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/report", produces = R.PROJSON)
public class ReportApiController extends SysApiController {
    @ApiOperation(value = "检查报表编码是否存在")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiImplicitParam(name = "code", value = "编码")
    @PostMapping(value = "/existByCode")
    public RestResult existByCode(@SingleJson String code) {
        return RestResult.ok(SysService.me().reportService().existByCode(code, null));
    }

    @ApiOperation(value = "新建报表")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @Authorize(code = R.Permission.SysReportInsert)
    @PostMapping(value = "/insert")
    public RestResult insert(@RequestBody SysReport entity) {
        return SysService.me().reportService().insert(entity);
    }

    @ApiOperation(value = "修改报表")
    @ApiOperationSupport(order = 3, author = R.LYY)
    @Authorize(code = R.Permission.SysReportUpdate)
    @PostMapping(value = "/update")
    public RestResult update(@RequestBody SysReport entity) {
        return SysService.me().reportService().update(entity);
    }

    @ApiOperation(value = "修改报表状态")
    @ApiOperationSupport(order = 4, author = R.LYY)
    @Authorize(code = R.Permission.SysReportUpdate)
    @PostMapping(value = "/updateStatus")
    public RestResult updateStatus(@RequestBody StatusBody statusBody) {
        return SysService.me().reportService().updateStatus(statusBody.getIds(), statusBody.getStatus());
    }

    @ApiOperation(value = "删除报表")
    @ApiOperationSupport(order = 5, author = R.LYY)
    @ApiImplicitParam(name = "ids", value = "主键字符串")
    @Authorize(code = R.Permission.SysReportDelete)
    @PostMapping(value = "/delete")
    public RestResult delete(@SingleJson String ids) {
        SysService.me().reportService().delete(ids);
        return RestResult.ok();
    }

    @ApiOperation(value = "根据主键查询单个报表")
    @ApiOperationSupport(order = 6, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "主键")
    @PostMapping(value = "/selectById")
    public RestResult<SysReport> selectById(@SingleJson Long id) {
        return RestResult.ok(SysService.me().reportService().selectById(id));
    }

    @ApiOperation(value = "根据编码查询单个参数")
    @ApiOperationSupport(order = 7, author = R.LYY)
    @ApiImplicitParam(name = "code", value = "编码")
    @PostMapping(value = "/selectByCode")
    public RestResult<SysReport> selectByCode(@SingleJson String code) {
        return RestResult.ok(SysService.me().reportService().selectByCode(code));
    }

    @ApiOperation(value = "查询参报表分页列表")
    @ApiOperationSupport(order = 8, author = R.LYY)
    @PostMapping(value = "/selectPageList")
    public RestResult<PageList<SysReport>> selectPageList(@RequestBody ReportFilter filter) {
        return RestResult.ok(SysService.me().reportService().selectPageList(filter));
    }

    @ApiOperation(value = "查询报表列表")
    @ApiOperationSupport(order = 9, author = R.LYY, ignoreParameters = {R.IG_PAGE_INDEX, R.IG_PAGE_SIZE, R.IG_SORT_NAME, R.IG_SORT_DIR})
    @PostMapping(value = "/selectList")
    public RestResult<List<SysReport>> selectList(@RequestBody ReportFilter filter) {
        return RestResult.ok(SysService.me().reportService().selectList(filter));
    }

    @ApiOperation(value = "导出报表列表")
    @ApiOperationSupport(order = 10, author = R.LYY, ignoreParameters = {R.IG_PAGE_INDEX, R.IG_PAGE_SIZE, R.IG_SORT_NAME, R.IG_SORT_DIR})
    @PostMapping(value = "/export", produces = {R.PROOCTET, R.PROJSON})
    public void export(@RequestBody ReportFilter filter) {
        XCI.exportExcel(SysService.me().reportService().selectList(filter), SysReport.class, "系统报表列表");
    }

    @ApiOperation(value = "刷新报表缓存")
    @ApiOperationSupport(order = 11, author = R.LYY)
    @PostMapping(value = "/refresh")
    public RestResult refresh() {
        SysService.me().reportService().refresh();
        return RestResult.ok();
    }
}