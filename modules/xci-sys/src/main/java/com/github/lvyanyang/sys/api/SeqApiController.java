/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.api;

import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.annotation.SingleJson;
import com.github.lvyanyang.model.PageList;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.sys.entity.SysSeq;
import com.github.lvyanyang.sys.filter.SeqFilter;
import com.github.lvyanyang.sys.core.SysApiController;
import com.github.lvyanyang.sys.component.SysService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统序列控制器
 * @author 吕艳阳
 */
@Api(tags = "系统序列接口")
@ApiSort(7)
// @ApiEncrypt
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/seq", produces = R.PROJSON)
public class SeqApiController extends SysApiController {
    @ApiOperation(value = "检查序列编码是否存在")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiImplicitParam(name = "code", value = "编码")
    @PostMapping(value = "/existByCode")
    public RestResult existByCode(@SingleJson String code) {
        return RestResult.ok(SysService.me().seqService().existByCode(code, null));
    }

    @ApiOperation(value = "新建序列")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @Authorize(code = R.Permission.SysSeqInsert)
    @PostMapping(value = "/insert")
    public RestResult insert(@RequestBody SysSeq entity) {
        return SysService.me().seqService().insert(entity);
    }

    @ApiOperation(value = "修改序列")
    @ApiOperationSupport(order = 3, author = R.LYY)
    @Authorize(code = R.Permission.SysSeqUpdate)
    @PostMapping(value = "/update")
    public RestResult update(@RequestBody SysSeq entity) {
        return SysService.me().seqService().update(entity);
    }

    @ApiOperation(value = "删除序列")
    @ApiOperationSupport(order = 4, author = R.LYY)
    @ApiImplicitParam(name = "ids", value = "主键字符串")
    @Authorize(code = R.Permission.SysSeqDelete)
    @PostMapping(value = "/delete")
    public RestResult delete(@SingleJson String ids) {
        SysService.me().seqService().delete(ids);
        return RestResult.ok();
    }

    @ApiOperation(value = "根据主键查询单个序列")
    @ApiOperationSupport(order = 5, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "主键")
    @PostMapping(value = "/selectById")
    public RestResult<SysSeq> selectById(@SingleJson Long id) {
        return RestResult.ok(SysService.me().seqService().selectById(id));
    }

    @ApiOperation(value = "根据编码查询单个序列")
    @ApiOperationSupport(order = 6, author = R.LYY)
    @ApiImplicitParam(name = "code", value = "编码")
    @PostMapping(value = "/selectByCode")
    public RestResult<SysSeq> selectByCode(@SingleJson String code) {
        return RestResult.ok(SysService.me().seqService().selectByCode(code));
    }

    @ApiOperation(value = "查询序列分页列表")
    @ApiOperationSupport(order = 7, author = R.LYY)
    @PostMapping(value = "/selectPageList")
    public RestResult<PageList<SysSeq>> selectPageList(@RequestBody SeqFilter filter) {
        return RestResult.ok(SysService.me().seqService().selectPageList(filter));
    }

    @ApiOperation(value = "查询序列列表")
    @ApiOperationSupport(order = 8, author = R.LYY, ignoreParameters = {R.IG_PAGE_INDEX, R.IG_PAGE_SIZE, R.IG_SORT_NAME, R.IG_SORT_DIR})
    @PostMapping(value = "/selectList")
    public RestResult<List<SysSeq>> selectList(@RequestBody SeqFilter filter) {
        return RestResult.ok(SysService.me().seqService().selectList(filter));
    }

    @ApiOperation(value = "导出序列列表")
    @ApiOperationSupport(order = 9, author = R.LYY, ignoreParameters = {R.IG_PAGE_INDEX, R.IG_PAGE_SIZE, R.IG_SORT_NAME, R.IG_SORT_DIR})
    @PostMapping(value = "/export", produces = {R.PROOCTET, R.PROJSON})
    public void export(@RequestBody SeqFilter filter) {
        XCI.exportExcel(SysService.me().seqService().selectList(filter), SysSeq.class, "系统序列列表");
    }

    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "根据编码查询当前序列值")
    @ApiImplicitParam(name = "code", value = "编码")
    @PostMapping("/selectCurrentValue")
    public RestResult<Long> selectCurrentValue(@SingleJson String code) {
        return RestResult.ok(SysService.me().seqService().selectCurrentValue(code));
    }

    @ApiOperationSupport(order = 11)
    @ApiOperation(value = "根据编码查询下一个序列值")
    @ApiImplicitParam(name = "code", value = "编码")
    @PostMapping("/selectNextValue")
    public RestResult<Long> selectNextValue(@SingleJson String code) {
        return RestResult.ok(SysService.me().seqService().selectNextValue(code));
    }
}