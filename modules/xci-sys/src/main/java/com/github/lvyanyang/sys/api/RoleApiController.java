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
import com.github.lvyanyang.model.PermissionBody;
import com.github.lvyanyang.model.StatusBody;
import com.github.lvyanyang.sys.core.SysApiController;
import com.github.lvyanyang.sys.entity.SysDept;
import com.github.lvyanyang.sys.entity.SysModule;
import com.github.lvyanyang.sys.entity.SysParam;
import com.github.lvyanyang.sys.entity.SysRole;
import com.github.lvyanyang.sys.filter.RoleFilter;
import com.github.lvyanyang.sys.component.SysService;
import io.swagger.annotations.*;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 系统角色接口
 * @author 吕艳阳
 */
@Api(tags = "系统角色接口")
@ApiSort(4)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/role", produces = R.PROJSON)
public class RoleApiController extends SysApiController {
    @ApiOperation(value = "是否存在指定编码的角色")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @ApiImplicitParam(name = "code", value = "编码")
    @PostMapping("/existByCode")
    public RestResult existByCode(@SingleJson String code) {
        return RestResult.ok(SysService.me().roleService().existByCode(code, null));
    }

    @ApiOperation(value = "是否存在指定名称的角色")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @PostMapping(value = "/existByName")
    public RestResult existByName(@RequestBody existByNameBody body) {
        return RestResult.ok(SysService.me().roleService().existByName(body.getName(), body.getDeptId(), null));
    }

    @ApiOperation(value = "新建角色")
    @ApiOperationSupport(order = 3, author = R.LYY, ignoreParameters = "entities.deptName")
    @Authorize(code = R.Permission.SysRoleInsert)
    @PostMapping(value = "/insert")
    public RestResult insert(@RequestBody SysRole entity) {
        return SysService.me().roleService().insert(entity);
    }

    @ApiOperation(value = "修改角色")
    @ApiOperationSupport(order = 4, author = R.LYY, ignoreParameters = "entities.deptName")
    @Authorize(code = R.Permission.SysRoleUpdate)
    @PostMapping(value = "/update")
    public RestResult update(@RequestBody SysRole entity) {
        return SysService.me().roleService().update(entity);
    }

    @ApiOperation(value = "批量保存角色", notes = "如果主键存在则更新否则新建")
    @ApiOperationSupport(order = 5, author = R.LYY, ignoreParameters = "entities.deptName")
    @Authorize(code = R.Permission.SysRoleSave)
    @PostMapping(value = "/batchSave")
    public RestResult batchSave(@RequestBody List<SysRole> entities) {
        SysService.me().roleService().batchSave(entities);
        return RestResult.ok();
    }

    @ApiOperation(value = "修改角色状态")
    @ApiOperationSupport(order = 6, author = R.LYY)
    @Authorize(code = R.Permission.SysRoleUpdate)
    @PostMapping(value = "/updateStatus")
    public RestResult updateStatus(@RequestBody StatusBody statusBody) {
        return SysService.me().roleService().updateStatus(statusBody.getIds(), statusBody.getStatus());
    }

    @ApiOperation(value = "删除角色")
    @ApiOperationSupport(order = 7, author = R.LYY)
    @ApiImplicitParam(name = "ids", value = "主键字符串")
    @Authorize(code = R.Permission.SysRoleDelete)
    @PostMapping(value = "/delete")
    public RestResult delete(@SingleJson String ids) {
        SysService.me().roleService().delete(ids);
        return RestResult.ok();
    }

    @ApiOperation(value = "根据主键查询单个角色")
    @ApiOperationSupport(order = 8, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "主键")
    @PostMapping(value = "/selectById")
    public RestResult<SysRole> selectById(@SingleJson Long id) {
        return RestResult.ok(SysService.me().roleService().selectById(id));
    }

    @ApiOperation(value = "根据编码查询单个角色")
    @ApiOperationSupport(order = 9, author = R.LYY)
    @ApiImplicitParam(name = "code", value = "编码")
    @PostMapping(value = "/selectByCode")
    public RestResult<SysRole> selectByCode(@SingleJson String code) {
        return RestResult.ok(SysService.me().roleService().selectByCode(code));
    }

    @ApiOperation(value = "根据用户主键查询角色列表")
    @ApiOperationSupport(order = 10, author = R.LYY)
    @ApiImplicitParam(name = "userId", value = "用户主键")
    @PostMapping("/selectByUserId")
    public RestResult<List<SysRole>> selectByUserId(@SingleJson Long userId) {
        return RestResult.ok(SysService.me().roleService().selectListByUserId(userId));
    }

    @ApiOperation(value = "查询角色列表")
    @ApiOperationSupport(order = 11, author = R.LYY, ignoreParameters = {R.IG_PAGE_INDEX, R.IG_PAGE_SIZE, R.IG_SORT_NAME, R.IG_SORT_DIR})
    @PostMapping(value = "/selectList")
    public RestResult<List<SysRole>> selectList(@RequestBody RoleFilter filter) {
        return RestResult.ok(SysService.me().roleService().selectList(filter));
    }

    @ApiOperation(value = "查询角色分页列表")
    @ApiOperationSupport(order = 12, author = R.LYY)
    @PostMapping(value = "/selectPageList")
    public RestResult<PageList<SysRole>> selectPageList(@RequestBody RoleFilter filter) {
        return RestResult.ok(SysService.me().roleService().selectPageList(filter));
    }

    @ApiOperation(value = "导出角色列表")
    @ApiOperationSupport(order = 13, author = R.LYY, ignoreParameters = {R.IG_PAGE_INDEX, R.IG_PAGE_SIZE, R.IG_SORT_NAME, R.IG_SORT_DIR})
    @PostMapping(value = "/export", produces = {R.PROOCTET, R.PROJSON})
    public void export(@RequestBody RoleFilter filter) {
        XCI.exportExcel(SysService.me().roleService().selectList(filter), SysParam.class, "系统角色列表");
    }

    @ApiOperation(value = "刷新角色缓存")
    @ApiOperationSupport(order = 14, author = R.LYY)
    @PostMapping(value = "/refresh")
    public RestResult refresh() {
        SysService.me().roleService().refresh();
        return RestResult.ok();
    }

    @ApiOperation(value = "保存角色权限")
    @ApiOperationSupport(order = 15, author = R.LYY)
    @PostMapping("/savePermission")
    public RestResult savePermission(@RequestBody PermissionBody permissionModel) {
        return SysService.me().roleService().savePermission(permissionModel);
    }

    @ApiOperation(value = "根据角色主键查询关联模块主键集合")
    @ApiOperationSupport(order = 16, author = R.LYY)
    @ApiImplicitParam(name = "roleId", value = "角色主键")
    @PostMapping("/selectModuleMapArray")
    public RestResult<List<String>> selectModuleMapArray(@SingleJson Long roleId) {
        return RestResult.ok(SysService.me().roleService().selectModuleMapArray(roleId));
    }

    @ApiOperation(value = "根据角色主键查询关联模块对象列表")
    @ApiOperationSupport(order = 17, author = R.LYY)
    @ApiImplicitParam(name = "roleId", value = "角色主键")
    @PostMapping("/selectModuleMapList")
    public RestResult<List<SysModule>> selectModuleMapList(@SingleJson Long roleId) {
        return RestResult.ok(SysService.me().roleService().selectModuleMapList(roleId));
    }

    @ApiOperation(value = "根据角色主键查询关联机构主键集合")
    @ApiOperationSupport(order = 18, author = R.LYY)
    @ApiImplicitParam(name = "roleId", value = "角色主键")
    @PostMapping("/selectDeptDataMapArray")
    public RestResult<List<String>> selectDeptDataMapArray(@SingleJson Long roleId) {
        return RestResult.ok(SysService.me().roleService().selectDeptDataMapArray(roleId));
    }

    @ApiOperation(value = "根据角色主键查询关联机构对象列表")
    @ApiOperationSupport(order = 19, author = R.LYY)
    @ApiImplicitParam(name = "roleId", value = "角色主键")
    @PostMapping("/selectDeptDataMapList")
    public RestResult<List<SysDept>> selectDeptDataMapList(@SingleJson Long roleId) {
        return RestResult.ok(SysService.me().roleService().selectDeptDataMapList(roleId));
    }

    @Data
    public static class existByNameBody{
        @ApiModelProperty(value = "机构名称",required = true)
        private String name;
        @ApiModelProperty(value = "机构主键",required = true)
        private Long deptId;
    }
}