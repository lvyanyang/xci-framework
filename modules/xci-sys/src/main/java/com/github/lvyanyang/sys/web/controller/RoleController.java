/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.web.controller;

import com.github.lvyanyang.core.R;
import com.github.lvyanyang.model.PermissionBody;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.filter.RoleFilter;
import com.github.lvyanyang.sys.web.component.SysWebService;
import com.github.lvyanyang.sys.web.model.TreeNode;
import com.github.lvyanyang.sys.entity.SysRole;
import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.exceptions.NotFoundException;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.sys.web.model.JsonGrid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.function.Consumer;

/**
 * 系统角色Web控制器
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-28 23:27:53
 */
@Authorize
@Controller
@RequestMapping(value = "/sys/role")
public class RoleController extends SysWebController {
    //region 页面视图

    /** 首页 */
    @GetMapping
    public String index() {
        return "sys/role/index";
    }

    /** 新建页 */
    @GetMapping("/create")
    @Authorize(code = R.Permission.SysRoleInsert)
    public String create(ModelMap map) {
        createMark(map);
        var entity = new SysRole();
        entity.setId(XCI.nextId());
        entity.setStatus(true);
        map.put("entity", entity);
        return "sys/role/edit";
    }

    /** 编辑页 */
    @GetMapping("/edit")
    @Authorize(code = R.Permission.SysRoleUpdate)
    public String edit(String id, ModelMap map) {
        var entity = SysService.me().roleService().selectById(Long.valueOf(id));
        if (entity == null) throw new NotFoundException(id);
        map.put("entity", entity);
        return "sys/role/edit";
    }

    /** 详情页 */
    @GetMapping("/details")
    public String details(String id, ModelMap map) {
        var idLong = Long.valueOf(id);
        var entity = SysService.me().roleService().selectById(idLong);
        if (entity == null) throw new NotFoundException(id);
        map.put("entity", entity);

        map.put("users", SysService.me().userService().selectListByRoleId(idLong));
        //map.put("ownDataSetting", permissionService.queryObjectDataSetting(id));
        return "sys/role/details";
    }

    //endregion

    //region 数据处理

    /**
     * 角色拥有的模块权限
     */
    @ResponseBody
    @GetMapping("/roleOwnModules")
    public RestResult roleOwnModules(String roleId) {
        var modules = SysService.me().roleService().selectModuleMapList(Long.valueOf(roleId));
        var nodes = SysWebService.me().toModuleNodeList(modules);
        return RestResult.ok(nodes);
    }

    /**
     * 角色拥有的部门权限
     */
    @ResponseBody
    @GetMapping("/roleOwnDepts")
    public RestResult roleOwnDepts(String roleId) {
        var depts = SysService.me().roleService().selectDeptDataMapList(Long.valueOf(roleId));
        var nodes = SysWebService.me().toDeptNodeList(depts);
        return RestResult.ok(nodes);
    }

    /** 表格查询 */
    @ResponseBody
    @PostMapping("/grid")
    public JsonGrid grid(RoleFilter filter) {
        return new JsonGrid(SysService.me().roleService().selectPageList(filter));
    }

    /** 新增保存 */
    @ResponseBody
    @PostMapping("/createSave")
    @Authorize(code = R.Permission.SysRoleInsert)
    public RestResult createSave(@ModelAttribute SysRole entity) {
        return SysService.me().roleService().insert(entity);
    }

    /** 修改保存 */
    @ResponseBody
    @PostMapping("/updateSave")
    @Authorize(code = R.Permission.SysRoleUpdate)
    public RestResult updateSave(@ModelAttribute SysRole entity) {
        return SysService.me().roleService().update(entity);
    }

    /** 删除 */
    @ResponseBody
    @PostMapping("/delete")
    @Authorize(code = R.Permission.SysRoleDelete)
    public RestResult delete(String ids) {
        SysService.me().roleService().delete(ids);
        return RestResult.ok();
    }

    /** 修改状态 */
    @ResponseBody
    @PostMapping("/status")
    @Authorize(code = R.Permission.SysRoleUpdate)
    public RestResult status(String id, Integer status) {
        return SysService.me().roleService().updateStatus(id, XCI.toBool(status));
    }

    /** 导出 */
    @GetMapping("/export")
    public void export(RoleFilter filter) {
        XCI.exportExcel(SysService.me().roleService().selectList(filter), SysRole.class, "系统角色列表");
    }

    //endregion

    //region 角色授权

    /** 授权页 */
    @GetMapping("/authorize")
    public String authorize(String roleIds, ModelMap map) {
        SysRole role = new SysRole();
        role.setDeptScope(5);
        var ids = XCI.splitToArray(roleIds);
        if (ids.length == 1) {
            role = SysService.me().roleService().selectById(Long.valueOf(ids[0]));
            if (role == null) throw new NotFoundException(roleIds);
        }
        map.put("role", role);
        map.put("roleIds", roleIds);
        return "sys/role/authorize";
    }

    /** 保存角色授权 */
    @ResponseBody
    @PostMapping("/authorizeSave")
    @Authorize(code = R.Permission.SysRoleAuthorize)
    public RestResult authorizeSave(PermissionBody permissionModel) {
        return SysWebService.me().roleService().savePermission(permissionModel);
    }

    /**
     * 当前用户可分配模块权限
     */
    @ResponseBody
    @GetMapping("/authorizeModules")
    public RestResult authorizeModules(String roleId) {
        var modules = SysWebService.me().userService().selectUserModuleListByUser(getCurrentUser());
        Consumer<TreeNode> checkNodeCallback = null;
        if (XCI.isNotBlank(roleId)) {
            var moduleIds = SysService.me().roleService().selectModuleMapArray(Long.valueOf(roleId));
            checkNodeCallback = node -> {
                if (moduleIds.stream().anyMatch(p -> p.equals(node.getId()))) {
                    node.setChecked(true);
                }
            };
        }

        var nodes = SysWebService.me().toModuleNodeList(modules, checkNodeCallback);
        return RestResult.ok(nodes);
    }

    /**
     * 当前用户可分配部门权限
     */
    @ResponseBody
    @GetMapping("/authorizeDepts")
    public RestResult authorizeDepts(String roleId) {
        var depts = SysService.me().userService().selectUserDeptDataListByUserId(getCurrentUser());
        Consumer<TreeNode> checkNodeCallback = null;
        if (XCI.isNotBlank(roleId)) {
            var deptIds = SysService.me().roleService().selectDeptDataMapArray(Long.valueOf(roleId));
            checkNodeCallback = node -> {
                if (deptIds.stream().anyMatch(p -> p.equals(node.getId()))) {
                    node.setChecked(true);
                }
            };
        }

        var nodes = SysWebService.me().toDeptNodeList(depts, checkNodeCallback);
        return RestResult.ok(nodes);
    }

    //endregion
}
