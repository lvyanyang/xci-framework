package com.xci.sys.api;

import com.xci.annotation.Authorize;
import com.xci.annotation.SingleJson;
import com.xci.model.PageList;
import com.xci.core.R;
import com.xci.core.RestResult;
import com.xci.core.XCI;
import com.xci.sys.entity.SysFile;
import com.xci.sys.filter.FileFilter;
import com.xci.sys.core.SysApiController;
import com.xci.sys.service.SysService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统文件控制器
 * @author 吕艳阳
 */
@Api(tags = "系统文件接口")
@ApiSort(12)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/file", produces = R.PROJSON)
public class FileApiController extends SysApiController {
    @ApiOperation(value = "根据记录主键检查文件是否存在")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiImplicitParam(name = "recordId", value = "记录主键")
    @PostMapping(value = "/existByRecordId")
    public RestResult existByRecordId(@SingleJson Long recordId) {
        return RestResult.ok(SysService.me().fileService().existByRecordId(recordId));
    }

    @ApiOperation(value = "新建文件")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @PostMapping(value = "/insert")
    public RestResult insert(@RequestBody SysFile entity) {
        return SysService.me().fileService().insert(entity);
    }

    @ApiOperation(value = "根据主键删除文件")
    @ApiOperationSupport(order = 3, author = R.LYY)
    @ApiImplicitParam(name = "ids", value = "主键字符串")
    @PostMapping(value = "/delete")
    public RestResult delete(@SingleJson String ids) {
        SysService.me().fileService().delete(ids);
        return RestResult.ok();
    }

    @ApiOperation(value = "根据记录主键删除文件")
    @ApiOperationSupport(order = 4, author = R.LYY)
    @ApiImplicitParam(name = "recordId", value = "记录主键")
    @PostMapping("/deleteByRecordId")
    public RestResult deleteByRecordId(@SingleJson Long recordId) {
        SysService.me().fileService().deleteByRecordId(recordId);
        return RestResult.ok();
    }

    @ApiOperation(value = "根据主键查询单个文件")
    @ApiOperationSupport(order = 5, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "文件主键")
    @PostMapping(value = "/selectById")
    public RestResult<SysFile> selectById(@SingleJson Long id) {
        return RestResult.ok(SysService.me().fileService().selectById(id));
    }

    @ApiOperation(value = "根据记录主键获取第一个文件对象")
    @ApiOperationSupport(order = 6, author = R.LYY)
    @ApiImplicitParam(name = "recordId", value = "记录主键")
    @PostMapping("/selectByRecordId")
    public RestResult<SysFile> selectByRecordId(@SingleJson Long recordId) {
        return RestResult.ok(SysService.me().fileService().selectByRecordId(recordId));
    }

    @ApiOperation(value = "查询文件分页列表")
    @ApiOperationSupport(order = 7, author = R.LYY)
    @PostMapping(value = "/selectPageList")
    public RestResult<PageList<SysFile>> selectPageList(@RequestBody FileFilter filter) {
        return RestResult.ok(SysService.me().fileService().selectPageList(filter));
    }

    @ApiOperation(value = "查询文件列表")
    @ApiOperationSupport(order = 8, author = R.LYY, ignoreParameters = {R.IG_PAGE_INDEX, R.IG_PAGE_SIZE, R.IG_SORT_NAME, R.IG_SORT_DIR})
    @PostMapping(value = "/selectList")
    public RestResult<List<SysFile>> selectList(@RequestBody FileFilter filter) {
        return RestResult.ok(SysService.me().fileService().selectList(filter));
    }

    @ApiOperation(value = "导出文件列表")
    @ApiOperationSupport(order = 9, author = R.LYY, ignoreParameters = {R.IG_PAGE_INDEX, R.IG_PAGE_SIZE, R.IG_SORT_NAME, R.IG_SORT_DIR})
    @PostMapping(value = "/export", produces = {R.PROOCTET, R.PROJSON})
    public void export(@RequestBody FileFilter filter) {
        XCI.exportExcel(SysService.me().fileService().selectList(filter), SysFile.class, "系统文件列表");
    }
}