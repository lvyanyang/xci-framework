package com.github.lvyanyang.sys.web.controller;

import com.github.lvyanyang.core.R;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.entity.SysDept;
import com.github.lvyanyang.sys.entity.SysModule;
import com.github.lvyanyang.sys.filter.RoleFilter;
import com.github.lvyanyang.sys.web.component.SysWebService;
import com.github.lvyanyang.web.WebController;
import com.github.lvyanyang.sys.entity.SysRole;
import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.exceptions.NotFoundException;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.sys.web.model.JsonGrid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统角色Web控制器
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-28 23:27:53
 */
@Authorize
@Controller
@RequestMapping(value = "/sys/role")
public class RoleController extends WebController {
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

    /** 编辑时获取机构列表 */
    @ResponseBody
    @GetMapping("/ownDeptList")
    public RestResult ownDeptList() {
        List<SysDept> depts = SysWebService.me().selectEnabledDeptList(true);
        return RestResult.ok(SysWebService.me().toDeptNodeList(depts));
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

    // /**
    //  * 角色拥有的模块 tree 节点
    //  * @param id 角色主键
    //  */
    // @ResponseBody
    // @GetMapping("/role-own-modules")
    // public RestMessage roleOwnModules(String id) {
    //     List<SysModule> list = roleService.selectModuleMapList(id);
    //     List<TreeNode> models = moduleService.convertToNodeList(list);
    //     return RestMessage.tree(models);
    // }
    //
    // /**
    //  * 角色拥有的部门 tree 节点
    //  * @param id 角色主键
    //  */
    // @ResponseBody
    // @GetMapping("/role-own-departments")
    // public RestMessage roleOwnDepartments(String id) {
    //     List<SysDept> departments = roleService.selectDeptMapList(id);
    //     return RestMessage.tree(deptService.convertToNodeList(departments));
    // }

    //endregion
}
