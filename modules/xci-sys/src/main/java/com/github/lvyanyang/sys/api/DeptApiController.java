/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.api;

import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.annotation.SingleJson;
import com.github.lvyanyang.model.IdValue;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.model.ParentIdNameBody;
import com.github.lvyanyang.model.StatusBody;
import com.github.lvyanyang.sys.entity.SysDept;
import com.github.lvyanyang.sys.filter.DeptFilter;
import com.github.lvyanyang.sys.core.SysApiController;
import com.github.lvyanyang.sys.component.SysService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统机构控制器
 * @author 吕艳阳
 */
@Api(tags = "系统机构接口")
@ApiSort(3)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/dept", produces = R.PROJSON)
public class DeptApiController extends SysApiController {
    @ApiOperation(value = "检查机构编码是否存在")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiImplicitParam(name = "code", value = "编码")
    @PostMapping(value = "/existByCode")
    public RestResult existByCode(@SingleJson String code) {
        return RestResult.ok(SysService.me().deptService().existByCode(code, null));
    }

    @ApiOperation(value = "检查机构名称是否存在")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @PostMapping("/existByName")
    public RestResult existByName(@RequestBody ParentIdNameBody parentIdNameBody) {
        return RestResult.ok(SysService.me().deptService().existByName(parentIdNameBody.getName(), parentIdNameBody.getParentId(), null));
    }

    @ApiOperation(value = "新建机构")
    @ApiOperationSupport(order = 3, author = R.LYY, ignoreParameters = "entity.parentName")
    @Authorize(code = R.Permission.SysDeptInsert)
    @PostMapping(value = "/insert")
    public RestResult insert(@RequestBody SysDept entity) {
        return SysService.me().deptService().insert(entity);
    }

    @ApiOperation(value = "修改机构")
    @ApiOperationSupport(order = 4, author = R.LYY, ignoreParameters = "entity.parentName")
    @Authorize(code = R.Permission.SysDeptUpdate)
    @PostMapping(value = "/update")
    public RestResult update(@RequestBody SysDept entity) {
        return SysService.me().deptService().update(entity);
    }

    @ApiOperation(value = "根据主键更新机构状态")
    @ApiOperationSupport(order = 5)
    @Authorize(code = R.Permission.SysDeptUpdate)
    @PostMapping("/updateStatus")
    public RestResult updateStatus(@RequestBody StatusBody statusBody) {
        return SysService.me().deptService().updateStatus(statusBody.getIds(), statusBody.getStatus());
    }

    @ApiOperation(value = "根据主键修改机构父节点")
    @ApiOperationSupport(order = 6)
    @Authorize(code = R.Permission.SysDeptUpdate)
    @PostMapping("/updateParentId")
    public RestResult updateParentId(@RequestBody List<IdValue> values) {
        return SysService.me().deptService().updateParentId(values);
    }

    @ApiOperation(value = "根据主键修改机构排序路径")
    @ApiOperationSupport(order = 7, author = R.LYY)
    @Authorize(code = R.Permission.SysDeptUpdate)
    @PostMapping("/updatePath")
    public RestResult updatePath(@RequestBody List<IdValue> values) {
        return SysService.me().deptService().updatePath(values);
    }

    @ApiOperation(value = "删除机构")
    @ApiOperationSupport(order = 8, author = R.LYY)
    @ApiImplicitParam(name = "ids", value = "主键字符串")
    @Authorize(code = R.Permission.SysDeptDelete)
    @PostMapping(value = "/delete")
    public RestResult delete(@SingleJson String ids) {
        SysService.me().deptService().delete(ids);
        return RestResult.ok();
    }

    @ApiOperation(value = "根据主键查询单个机构")
    @ApiOperationSupport(order = 9, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "主键")
    @PostMapping(value = "/selectById")
    public RestResult<SysDept> selectById(@SingleJson Long id) {
        return RestResult.ok(SysService.me().deptService().selectById(id));
    }

    @ApiOperation(value = "根据编码查询单个机构")
    @ApiOperationSupport(order = 10, author = R.LYY)
    @ApiImplicitParam(name = "code", value = "编码")
    @PostMapping(value = "/selectByCode")
    public RestResult<SysDept> selectByCode(@SingleJson String code) {
        return RestResult.ok(SysService.me().deptService().selectByCode(code));
    }

    @ApiOperation(value = "获取节点的直系子节点数")
    @ApiOperationSupport(order = 11, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "机构主键")
    @PostMapping("/selectChildCount")
    public RestResult selectChildCount(@SingleJson Long id) {
        return RestResult.ok(SysService.me().deptService().selectChildCount(id));
    }

    @ApiOperation(value = "查询机构列表")
    @ApiOperationSupport(order = 12, author = R.LYY)
    @PostMapping(value = "/selectList")
    public RestResult<List<SysDept>> selectList(@RequestBody DeptFilter filter) {
        return RestResult.ok(SysService.me().deptService().selectList(filter));
    }

    @ApiOperation(value = "导出机构列表")
    @ApiOperationSupport(order = 13, author = R.LYY)
    @PostMapping(value = "/export", produces = {R.PROOCTET, R.PROJSON})
    public void export(@RequestBody DeptFilter filter) {
        XCI.exportExcel(SysService.me().deptService().selectList(filter), SysDept.class, "系统机构列表");
    }

    @ApiOperation(value = "刷新机构缓存")
    @ApiOperationSupport(order = 14, author = R.LYY)
    @PostMapping(value = "/refresh")
    public RestResult refresh() {
        SysService.me().deptService().refresh();
        return RestResult.ok();
    }
}