/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.api;

import com.github.lvyanyang.sys.core.SysApiController;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.annotation.SingleJson;
import com.github.lvyanyang.model.IdValue;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.sys.entity.SysDicCategory;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统字典类型控制器
 * @author 吕艳阳
 */
@Api(tags = "系统字典类型接口")
@ApiSort(3)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/dicCategory", produces = R.PROJSON)
public class DicCategoryApiController extends SysApiController {
    @ApiOperation(value = "检查字典类型编码是否存在")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiImplicitParam(name = "code", value = "编码")
    @PostMapping(value = "/existByCode")
    public RestResult existByCode(@SingleJson String code) {
        return RestResult.ok(SysService.me().dicCategoryService().existByCode(code, null));
    }

    @ApiOperation(value = "检查字典类型名称是否存在")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @ApiImplicitParam(name = "name", value = "字典类型名称")
    @PostMapping("/existByName")
    public RestResult existByName(@SingleJson String name) {
        return RestResult.ok(SysService.me().dicCategoryService().existByName(name, null));
    }

    @ApiOperation(value = "新建字典类型")
    @ApiOperationSupport(order = 3, author = R.LYY, ignoreParameters = "entity.parentName")
    @Authorize(code = R.Permission.SysDicCategoryInsert)
    @PostMapping(value = "/insert")
    public RestResult insert(@RequestBody SysDicCategory entity) {
        return SysService.me().dicCategoryService().insert(entity);
    }

    @ApiOperation(value = "修改字典类型")
    @ApiOperationSupport(order = 4, author = R.LYY, ignoreParameters = "entity.parentName")
    @Authorize(code = R.Permission.SysDicCategoryUpdate)
    @PostMapping(value = "/update")
    public RestResult update(@RequestBody SysDicCategory entity) {
        return SysService.me().dicCategoryService().update(entity);
    }

    @ApiOperation(value = "根据主键修改字典类型父节点")
    @ApiOperationSupport(order = 5)
    @Authorize(code = R.Permission.SysDicCategoryUpdate)
    @PostMapping("/updateParentId")
    public RestResult updateParentId(@RequestBody List<IdValue> values) {
        return SysService.me().dicCategoryService().updateParentId(values);
    }

    @ApiOperation(value = "根据主键修改字典类型排序路径")
    @ApiOperationSupport(order = 6, author = R.LYY)
    @Authorize(code = R.Permission.SysDicCategoryUpdate)
    @PostMapping("/updatePath")
    public RestResult updatePath(@RequestBody List<IdValue> values) {
        return SysService.me().dicCategoryService().updatePath(values);
    }

    @ApiOperation(value = "删除字典类型")
    @ApiOperationSupport(order = 7, author = R.LYY)
    @ApiImplicitParam(name = "ids", value = "主键字符串")
    @Authorize(code = R.Permission.SysDicCategoryDelete)
    @PostMapping(value = "/delete")
    public RestResult delete(@SingleJson String ids) {
        SysService.me().dicCategoryService().delete(ids);
        return RestResult.ok();
    }

    @ApiOperation(value = "根据主键查询单个字典类型")
    @ApiOperationSupport(order = 8, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "主键")
    @PostMapping(value = "/selectById")
    public RestResult<SysDicCategory> selectById(@SingleJson Long id) {
        return RestResult.ok(SysService.me().dicCategoryService().selectById(id));
    }

    @ApiOperation(value = "根据编码查询单个字典类型")
    @ApiOperationSupport(order = 9, author = R.LYY)
    @ApiImplicitParam(name = "code", value = "编码")
    @PostMapping(value = "/selectByCode")
    public RestResult<SysDicCategory> selectByCode(@SingleJson String code) {
        return RestResult.ok(SysService.me().dicCategoryService().selectByCode(code));
    }

    @ApiOperation(value = "获取节点的直系子节点数")
    @ApiOperationSupport(order = 10, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "字典类型主键")
    @PostMapping("/selectChildCount")
    public RestResult selectChildCount(@SingleJson Long id) {
        return RestResult.ok(SysService.me().dicCategoryService().selectChildCount(id));
    }

    @ApiOperation(value = "查询字典类型列表")
    @ApiOperationSupport(order = 11, author = R.LYY)
    @PostMapping(value = "/selectList")
    public RestResult<List<SysDicCategory>> selectList() {
        return RestResult.ok(SysService.me().dicCategoryService().selectList());
    }

    @ApiOperation(value = "导出字典类型列表")
    @ApiOperationSupport(order = 12, author = R.LYY)
    @PostMapping(value = "/export", produces = {R.PROOCTET, R.PROJSON})
    public void export() {
        XCI.exportExcel(SysService.me().dicCategoryService().selectList(), SysDicCategory.class, "系统字典类型列表");
    }
}