/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.api;

import com.github.lvyanyang.sys.core.SysApiController;
import com.github.lvyanyang.sys.entity.SysParam;
import com.github.lvyanyang.sys.service.SysService;
import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.annotation.SingleJson;
import com.github.lvyanyang.model.PageList;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.sys.filter.ParamFilter;
import io.swagger.annotations.*;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统参数控制器
 * @author 吕艳阳
 */
@Api(tags = "系统参数接口")
@ApiSort(7)
// @ApiEncrypt
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/param", produces = R.PROJSON)
public class ParamApiController extends SysApiController {
    @ApiOperation(value = "检查参数编码是否存在")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiImplicitParam(name = "code", value = "编码")
    @PostMapping(value = "/existByCode")
    public RestResult existByCode(@SingleJson String code) {
        return RestResult.ok(SysService.me().paramService().existByCode(code, null));
    }

    @ApiOperation(value = "新建参数")
    @ApiOperationSupport(order = 2, author = R.LYY, ignoreParameters = "entity.categoryName")
    @Authorize(code = R.Permission.SysParamInsert)
    @PostMapping(value = "/insert")
    public RestResult insert(@RequestBody SysParam entity) {
        return SysService.me().paramService().insert(entity);
    }

    @ApiOperation(value = "修改参数")
    @ApiOperationSupport(order = 3, author = R.LYY, ignoreParameters = "entity.categoryName")
    @Authorize(code = R.Permission.SysParamUpdate)
    @PostMapping(value = "/update")
    public RestResult update(@RequestBody SysParam entity) {
        return SysService.me().paramService().update(entity);
    }

    @ApiOperation(value = "批量保存参数", notes = "如果主键存在则更新否则新建")
    @ApiOperationSupport(order = 4, author = R.LYY, ignoreParameters = "entities.categoryName")
    @Authorize(code = R.Permission.SysParamSave)
    @PostMapping(value = "/batchSave")
    public RestResult batchSave(@RequestBody List<SysParam> entities) {
        SysService.me().paramService().batchSave(entities);
        return RestResult.ok();
    }

    @ApiOperation(value = "删除参数")
    @ApiOperationSupport(order = 5, author = R.LYY)
    @ApiImplicitParam(name = "ids", value = "主键字符串")
    @Authorize(code = R.Permission.SysParamDelete)
    @PostMapping(value = "/delete")
    public RestResult delete(@SingleJson String ids) {
        SysService.me().paramService().delete(ids);
        return RestResult.ok();
    }

    @ApiOperation(value = "根据主键查询单个参数")
    @ApiOperationSupport(order = 6, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "主键")
    @PostMapping(value = "/selectById")
    public RestResult<SysParam> selectById(@SingleJson Long id) {
        return RestResult.ok(SysService.me().paramService().selectById(id));
    }

    @ApiOperation(value = "根据编码查询单个参数")
    @ApiOperationSupport(order = 7, author = R.LYY)
    @ApiImplicitParam(name = "code", value = "编码")
    @PostMapping(value = "/selectByCode")
    public RestResult<SysParam> selectByCode(@SingleJson String code) {
        return RestResult.ok(SysService.me().paramService().selectByCode(code));
    }

    @ApiOperation(value = "根据编码查询单个参数值")
    @ApiOperationSupport(order = 7, author = R.LYY)
    @PostMapping("/selectValueByCode")
    public RestResult<String> selectValueByCode(@RequestBody ValueByCodeBody valueByCodeBody) {
        return RestResult.ok(SysService.me().paramService().selectValueByCode(valueByCodeBody.getCode(), valueByCodeBody.getDefaultValue()));
    }

    @ApiOperation(value = "查询参数分页列表")
    @ApiOperationSupport(order = 8, author = R.LYY)
    @PostMapping(value = "/selectPageList")
    public RestResult<PageList<SysParam>> selectPageList(@RequestBody ParamFilter filter) {
        return RestResult.ok(SysService.me().paramService().selectPageList(filter));
    }

    @ApiOperation(value = "查询参数列表")
    @ApiOperationSupport(order = 9, author = R.LYY, ignoreParameters = {R.IG_PAGE_INDEX, R.IG_PAGE_SIZE, R.IG_SORT_NAME, R.IG_SORT_DIR})
    @PostMapping(value = "/selectList")
    public RestResult<List<SysParam>> selectList(@RequestBody ParamFilter filter) {
        return RestResult.ok(SysService.me().paramService().selectList(filter));
    }

    @ApiOperation(value = "导出参数列表")
    @ApiOperationSupport(order = 10, author = R.LYY, ignoreParameters = {R.IG_PAGE_INDEX, R.IG_PAGE_SIZE, R.IG_SORT_NAME, R.IG_SORT_DIR})
    @PostMapping(value = "/export", produces = {R.PROOCTET, R.PROJSON})
    public void export(@RequestBody ParamFilter filter) {
        XCI.exportExcel(SysService.me().paramService().selectList(filter), SysParam.class, "系统参数列表");
    }

    @ApiOperation(value = "刷新参数缓存")
    @ApiOperationSupport(order = 11, author = R.LYY)
    @PostMapping(value = "/refresh")
    public RestResult refresh() {
        SysService.me().paramService().refresh();
        return RestResult.ok();
    }


    @Data
    @ApiModel(description = "根据编码查询参数值参数")
    public static class ValueByCodeBody{
        @ApiModelProperty(value = "参数编码",required = true)
        private String code;
        @ApiModelProperty(value = "如果没有找到指定编码的参数,则返回此默认值")
        private String defaultValue;
    }
}