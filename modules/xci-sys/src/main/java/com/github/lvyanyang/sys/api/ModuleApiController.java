/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.api;

import com.github.lvyanyang.model.IdValue;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.sys.entity.SysModule;
import com.github.lvyanyang.sys.filter.ModuleFilter;
import com.github.lvyanyang.sys.core.SysApiController;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.annotation.SingleJson;
import com.github.lvyanyang.model.StatusBody;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统模块控制器
 * @author 吕艳阳
 */
@Api(tags = "系统模块接口")
@ApiSort(26)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/module", produces = R.PROJSON)
public class ModuleApiController extends SysApiController {
    @ApiOperation(value = "检查模块编码是否存在")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiImplicitParam(name = "code", value = "编码")
    @PostMapping(value = "/existByCode")
    public RestResult existByCode(@SingleJson String code) {
        return RestResult.ok(SysService.me().moduleService().existByCode(code, null));
    }

    @ApiOperation(value = "新建模块")
    @ApiOperationSupport(order = 2, author = R.LYY, ignoreParameters = "entity.parentName")
    @Authorize(code = R.Permission.SysModuleInsert)
    @PostMapping(value = "/insert")
    public RestResult insert(@RequestBody SysModule entity) {
        return SysService.me().moduleService().insert(entity);
    }

    @ApiOperation(value = "修改模块")
    @ApiOperationSupport(order = 3, author = R.LYY, ignoreParameters = "entity.parentName")
    @Authorize(code = R.Permission.SysModuleUpdate)
    @PostMapping(value = "/update")
    public RestResult update(@RequestBody SysModule entity) {
        return SysService.me().moduleService().update(entity);
    }

    @ApiOperation(value = "批量保存模块", notes = "如果主键存在则更新否则新建")
    @ApiOperationSupport(order = 4, author = R.LYY, ignoreParameters = "entities.parentName")
    @Authorize(code = R.Permission.SysModuleSave)
    @PostMapping(value = "/batchSave")
    public RestResult batchSave(@RequestBody List<SysModule> entities) {
        SysService.me().moduleService().batchSave(entities);
        return RestResult.ok();
    }

    @ApiOperation(value = "修改模块状态")
    @ApiOperationSupport(order = 5, author = R.LYY)
    @Authorize(code = R.Permission.SysModuleUpdate)
    @PostMapping(value = "/updateStatus")
    public RestResult updateStatus(@RequestBody StatusBody statusBody) {
        return SysService.me().moduleService().updateStatus(statusBody.getIds(), statusBody.getStatus());
    }

    @ApiOperation(value = "根据主键修改模块父节点")
    @ApiOperationSupport(order = 6)
    @Authorize(code = R.Permission.SysModuleUpdate)
    @PostMapping("/updateParentId")
    public RestResult updateParentId(@RequestBody List<IdValue> values) {
        return SysService.me().moduleService().updateParentId(values);
    }

    @ApiOperation(value = "根据主键修改模块排序路径")
    @ApiOperationSupport(order = 7, author = R.LYY)
    @Authorize(code = R.Permission.SysModuleUpdate)
    @PostMapping("/updatePath")
    public RestResult updatePath(@RequestBody List<IdValue> values) {
        return SysService.me().moduleService().updatePath(values);
    }

    @ApiOperation(value = "根据主键修改模块公共状态")
    @ApiOperationSupport(order = 8, author = R.LYY)
    @Authorize(code = R.Permission.SysModuleUpdate)
    @PostMapping("/updatePublicStatus")
    public RestResult updatePublicStatus(@RequestBody StatusBody statusBody) {
        return SysService.me().moduleService().updatePublicStatus(statusBody.getIds(), statusBody.getStatus());
    }

    @ApiOperation(value = "根据主键修改模块展开状态")
    @ApiOperationSupport(order = 9, author = R.LYY)
    @Authorize(code = R.Permission.SysModuleUpdate)
    @PostMapping("/updateExpandStatus")
    public RestResult updateExpandStatus(@RequestBody StatusBody statusBody) {
        return SysService.me().moduleService().updateExpandStatus(statusBody.getIds(), statusBody.getStatus());
    }

    @ApiOperation(value = "删除模块")
    @ApiOperationSupport(order = 10, author = R.LYY)
    @ApiImplicitParam(name = "ids", value = "主键字符串")
    @Authorize(code = R.Permission.SysModuleDelete)
    @PostMapping(value = "/delete")
    public RestResult delete(@SingleJson String ids) {
        SysService.me().moduleService().delete(ids);
        return RestResult.ok();
    }

    @ApiOperation(value = "根据主键查询单个模块")
    @ApiOperationSupport(order = 11, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "主键")
    @PostMapping(value = "/selectById")
    public RestResult<SysModule> selectById(@SingleJson Long id) {
        return RestResult.ok(SysService.me().moduleService().selectById(id));
    }

    @ApiOperation(value = "根据编码查询单个模块")
    @ApiOperationSupport(order = 12, author = R.LYY)
    @ApiImplicitParam(name = "code", value = "编码")
    @PostMapping(value = "/selectByCode")
    public RestResult<SysModule> selectByCode(@SingleJson String code) {
        return RestResult.ok(SysService.me().moduleService().selectByCode(code));
    }

    @ApiOperation(value = "获取节点的直系子节点数")
    @ApiOperationSupport(order = 13, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "模块主键")
    @PostMapping("/selectChildCount")
    public RestResult selectChildCount(@SingleJson Long id) {
        return RestResult.ok(SysService.me().moduleService().selectChildCount(id));
    }

    @ApiOperation(value = "查询模块列表")
    @ApiOperationSupport(order = 14, author = R.LYY)
    @PostMapping(value = "/selectList")
    public RestResult<List<SysModule>> selectList(ModuleFilter filter) {
        return RestResult.ok(SysService.me().moduleService().selectList(filter));
    }

    @ApiOperation(value = "导出模块列表")
    @ApiOperationSupport(order = 15, author = R.LYY)
    @PostMapping(value = "/export", produces = {R.PROOCTET, R.PROJSON})
    public void export(ModuleFilter filter) {
        XCI.exportExcel(SysService.me().moduleService().selectList(filter), SysModule.class, "系统模块列表");
    }
}