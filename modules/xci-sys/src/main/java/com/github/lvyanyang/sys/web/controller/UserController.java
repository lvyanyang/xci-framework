/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.web.controller;

import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.exceptions.NotFoundException;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.entity.SysUser;
import com.github.lvyanyang.sys.entity.SysUserSave;
import com.github.lvyanyang.sys.filter.UserFilter;
import com.github.lvyanyang.sys.web.component.SysWebService;
import com.github.lvyanyang.sys.web.model.JsonGrid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 系统用户Web控制器
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-28 23:27:53
 */
@Authorize
@Controller
@RequestMapping(value = "/sys/user")
public class UserController extends SysWebController {
    //region 页面视图

    /** 首页 */
    @GetMapping
    public String index() {
        return "sys/user/index";
    }

    /** 新建页 */
    @GetMapping("/create")
    @Authorize(code = R.Permission.SysUserInsert)
    public String create(ModelMap map) {
        createMark(map);
        var entity = new SysUser();
        entity.setId(XCI.nextId());
        entity.setStatus(true);
        entity.setPwdAllowModify(true);
        entity.setVisible(true);
        map.put("entity", entity);
        setEditRoles(map);
        return "sys/user/edit";
    }

    /** 编辑页 */
    @GetMapping("/edit")
    @Authorize(code = R.Permission.SysUserUpdate)
    public String edit(String id, ModelMap map) {
        var entity = SysService.me().userService().selectById(Long.valueOf(id));
        if (entity == null) throw new NotFoundException(id);
        var userSave = SysUserSave.from(entity);
        userSave.setRoleIds(String.join(",", SysService.me().roleService().selectIdsByUserId(entity.getId())));
        map.put("entity", userSave);
        setEditRoles(map);
        return "sys/user/edit";
    }

    private void setEditRoles(ModelMap map) {
        map.put("roles", SysService.me().selectEnabledRoleList(SysService.me().getCurrentUser().getDeptId(), true));
    }

    /**
     * 修改密码页面
     */
    @GetMapping("/modifyPassword")
    public String modifyPassword() {
        return "sys/user/modify-password";
    }


    /** 详情页 */
    @GetMapping("/details")
    public String details(String id, ModelMap map) {
        var idLong = Long.valueOf(id);
        var entity = SysService.me().userService().selectById(idLong);
        if (entity == null) throw new NotFoundException(id);
        map.put("entity", entity);
        map.put("roles", SysService.me().roleService().selectListByUserId(idLong));
        var deptScope = SysService.me().userService().selectDeptScopeByUserId(getUser(idLong));
        map.put("deptScopeName",XCI.getDeptScopeNameByValue(deptScope));
        return "sys/user/details";
    }

    //endregion

    //region 数据处理

    /**
     * 用户拥有的模块权限
     * @param userId 用户主键
     */
    @ResponseBody
    @GetMapping("/userOwnModules")
    public RestResult userOwnModules(String userId) {
        var user = SysService.me().userService().selectById(Long.valueOf(userId));
        var modules = SysService.me().userService().selectUserModuleListByUser(user);
        var nodes = SysWebService.me().toModuleNodeList(modules);
        return RestResult.ok(nodes);
    }

    /**
     * 用户拥有的机构权限
     * @param userId 用户主键
     */
    @ResponseBody
    @GetMapping("/userOwnDepts")
    public RestResult userOwnDepts(String userId) {
        var user = SysService.me().userService().selectById(Long.valueOf(userId));
        var depts = SysService.me().userService().selectUserDeptDataListByUserId(user);
        var nodes = SysWebService.me().toDeptNodeList(depts);
        return RestResult.ok(nodes);
    }

    /** 表格查询 */
    @ResponseBody
    @PostMapping("/grid")
    public JsonGrid grid(UserFilter filter) {
        return new JsonGrid(SysService.me().userService().selectPageList(filter));
    }

    /** 新增保存 */
    @ResponseBody
    @PostMapping("/createSave")
    @Authorize(code = R.Permission.SysUserInsert)
    public RestResult createSave(@ModelAttribute SysUserSave entity) {
        return SysService.me().userService().insert(entity);
    }

    /** 修改保存 */
    @ResponseBody
    @PostMapping("/updateSave")
    @Authorize(code = R.Permission.SysUserUpdate)
    public RestResult updateSave(@ModelAttribute SysUserSave entity) {
        return SysService.me().userService().update(entity);
    }

    /** 删除 */
    @ResponseBody
    @PostMapping("/delete")
    @Authorize(code = R.Permission.SysUserDelete)
    public RestResult delete(String ids) {
        SysService.me().userService().delete(ids);
        return RestResult.ok();
    }

    /** 修改状态 */
    @ResponseBody
    @PostMapping("/status")
    @Authorize(code = R.Permission.SysUserUpdate)
    public RestResult status(String id, Integer status) {
        return SysService.me().userService().updateStatus(id, XCI.toBool(status));
    }

    /** 修改密码保存 */
    @ResponseBody
    @PostMapping("/modifyPasswordSave")
    public RestResult modifyPassword(String currentPassword, String newPassword) {
        return SysService.me().userService().modifyPassword(SysService.me().getCurrentUser().getId(), currentPassword, newPassword);
    }

    /** 重置密码保存 */
    @ResponseBody
    @PostMapping("/revisePasswordSave")
    @Authorize(code = R.Permission.SysUserRevisePassword)
    public RestResult revisePassword(String userIds, String password) {
        return SysService.me().userService().revisePassword(userIds, password);
    }

    /** 导出 */
    @GetMapping("/export")
    public void export(UserFilter filter) {
        XCI.exportExcel(SysService.me().userService().selectList(filter), SysUser.class, "系统用户列表");
    }

    //endregion
}
