/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.web.controller;

import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.exceptions.NotFoundException;
import com.github.lvyanyang.model.TreeNodeIndex;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.entity.SysDept;
import com.github.lvyanyang.sys.filter.DeptFilter;
import com.github.lvyanyang.sys.web.component.SysWebService;
import com.github.lvyanyang.sys.web.model.JsonGrid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统组织机构控制器
 * @author 吕艳阳
 */
@Authorize
@Slf4j
@Controller
@RequestMapping("/sys/dept")
public class DeptController extends SysWebController {
    // region 页面视图

    /** 首页 */
    @GetMapping
    public String index() {
        return "sys/dept/index";
    }

    /** 新建页 */
    @GetMapping("/create")
    @Authorize(code = R.Permission.SysDeptInsert)
    public String create(String parentId, ModelMap map) {
        createMark(map);
        SysDept entity = new SysDept();
        entity.setId(XCI.nextId());
        entity.setParentId(Long.valueOf(parentId));
        entity.setStatus(true);
        map.put("entity", entity);
        //查询负责人列表
        map.put("managerUserList", SysWebService.me().selectEnabledUserList(true));
        return "sys/dept/edit";
    }

    /** 编辑页 */
    @GetMapping("/edit")
    @Authorize(code = R.Permission.SysDeptUpdate)
    public String edit(String id, ModelMap map) {
        SysDept entity = SysService.me().deptService().selectById(Long.valueOf(id));
        if (entity == null) throw new NotFoundException(id);

        map.put("entity", entity);
        //查询负责人列表
        map.put("managerUserList", SysWebService.me().selectEnabledUserList(true));
        return "sys/dept/edit";
    }

    /** 详情页 */
    @GetMapping("/details")
    public String details(String id, ModelMap map) {
        var longId = Long.valueOf(id);
        SysDept entity = SysService.me().deptService().selectById(longId);
        if (entity == null) throw new NotFoundException(id);

        map.put("entity", entity);
        //查询当前机构成员列表
        map.put("users", SysService.me().userService().selectListByDeptId(longId));
        return "sys/dept/details";
    }

    //endregion

    //region 数据处理

    /** 编辑时获取父节点列表 */
    @ResponseBody
    @GetMapping("/parentList")
    public RestResult parentList(@RequestParam String id, @RequestParam String created) {
        List<SysDept> depts = SysWebService.me().selectEnabledDeptList(true);
        //如果是修改时移除当前记录以及所有下级
        XCI.ifTrueAction(XCI.isBlank(created),()->XCI.removeTreeChildren(depts,Long.valueOf(id),true));
        return RestResult.ok(SysWebService.me().toDeptNodeList(depts));
    }

    /** 获取当前用户拥有的机构树 */
    @ResponseBody
    @GetMapping("/currentUserDeptTree")
    public RestResult currentUserDeptTree() {
        var depts = SysWebService.me().selectEnabledDeptList(true);
        var nodes = SysWebService.me().toDeptNodeList(depts);
        return RestResult.ok(nodes);
    }

    /**
     * 表格数据
     */
    @ResponseBody
    @GetMapping("/grid")
    public JsonGrid grid(DeptFilter filter) {
        return new JsonGrid(SysService.me().deptService().selectList(filter));
    }

    /** 新增保存 */
    @ResponseBody
    @PostMapping("/createSave")
    @Authorize(code = R.Permission.SysDeptInsert)
    public RestResult createSave(@ModelAttribute SysDept entity) {
        return SysService.me().deptService().insert(entity);
    }

    /** 修改保存 */
    @ResponseBody
    @PostMapping("/updateSave")
    @Authorize(code = R.Permission.SysDeptUpdate)
    public RestResult editSave(@ModelAttribute SysDept entity) {
        return SysService.me().deptService().update(entity);
    }

    /** 保存拖拽数据(父节点和路径) */
    @ResponseBody
    @PostMapping("/dnd")
    @Authorize(code = R.Permission.SysDeptUpdate)
    public RestResult dnd(@RequestBody List<TreeNodeIndex> nodeIndexs) {
        SysService.me().deptService().updateParentId(XCI.toParentIdValues(nodeIndexs));
        SysService.me().deptService().updatePath(XCI.toPathIdValues(nodeIndexs));
        return RestResult.ok();
    }

    /** 修改状态 */
    @ResponseBody
    @PostMapping("/status")
    @Authorize(code = R.Permission.SysDeptUpdate)
    public RestResult status(String id, Integer status) {
        return SysService.me().deptService().updateStatus(id, XCI.toBool(status));
    }

    /** 删除 */
    @ResponseBody
    @PostMapping("/delete")
    @Authorize(code = R.Permission.SysDeptDelete)
    public RestResult delete(String id) {
        SysService.me().deptService().delete(id);
        return RestResult.ok();
    }

    /** 导出 */
    @GetMapping("/export")
    public void export(DeptFilter filter) {
        XCI.exportExcel(SysService.me().deptService().selectList(filter), SysDept.class, "系统机构列表");
    }

    //endregion
}