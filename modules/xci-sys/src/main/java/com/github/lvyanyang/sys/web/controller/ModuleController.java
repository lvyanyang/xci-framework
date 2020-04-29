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
import com.github.lvyanyang.sys.entity.SysModule;
import com.github.lvyanyang.sys.filter.ModuleFilter;
import com.github.lvyanyang.sys.web.component.SysWebService;
import com.github.lvyanyang.sys.web.model.JsonGrid;
import com.github.lvyanyang.sys.web.model.SysModuleTreeModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统模块控制器
 * @author 吕艳阳
 */
@Authorize
@Slf4j
@Controller
@RequestMapping("/sys/module")
public class ModuleController extends SysWebController {
    // region 页面视图

    /** 首页 */
    @GetMapping
    public String index() {
        return "sys/module/index";
    }

    /** 新建页 */
    @GetMapping("/create")
    @Authorize(code = R.Permission.SysModuleInsert)
    public String create(String parentId, ModelMap map) {
        createMark(map);
        SysModule entity = new SysModule();
        entity.setId(XCI.nextId());
        entity.setParentId(Long.valueOf(parentId));
        entity.setStatus(true);
        entity.setExpand(true);
        entity.setMenu(true);
        entity.setPubliced(false);
        map.put("entity", entity);
        return "sys/module/edit";
    }

    /** 编辑页 */
    @GetMapping("/edit")
    @Authorize(code = R.Permission.SysModuleUpdate)
    public String edit(String id, ModelMap map) {
        SysModule entity = SysService.me().moduleService().selectById(Long.valueOf(id));
        if (entity == null) throw new NotFoundException(id);
        map.put("entity", entity);
        return "sys/module/edit";
    }

    /** 详情页 */
    @GetMapping("/details")
    public String details(String id, ModelMap map) {
        var longId = Long.valueOf(id);
        SysModule entity = SysService.me().moduleService().selectById(longId);
        if (entity == null) throw new NotFoundException(id);
        map.put("entity", entity);
        return "sys/module/details";
    }

    //endregion

    //region 数据处理

    /** 编辑时获取父节点列表 */
    @ResponseBody
    @GetMapping("/parentList")
    public RestResult parentList(@RequestParam String id, @RequestParam String created) {
        List<SysModule> modules = SysWebService.me().selectEnabledModuleList();
        //如果是修改时移除当前记录以及所有下级
        XCI.ifTrueAction(XCI.isBlank(created), () -> XCI.removeTreeChildren(modules, Long.valueOf(id), true));
        return RestResult.ok(SysWebService.me().toModuleNodeList(modules));
    }

    /**
     * 表格数据
     */
    @ResponseBody
    @GetMapping("/grid")
    public JsonGrid grid(ModuleFilter filter) {
        return new JsonGrid(SysModuleTreeModel.toTreeList(SysService.me().moduleService().selectList(filter)));
    }

    /** 新增保存 */
    @ResponseBody
    @PostMapping("/createSave")
    @Authorize(code = R.Permission.SysModuleInsert)
    public RestResult createSave(@ModelAttribute SysModule entity) {
        return SysService.me().moduleService().insert(entity);
    }

    /** 修改保存 */
    @ResponseBody
    @PostMapping("/updateSave")
    @Authorize(code = R.Permission.SysModuleUpdate)
    public RestResult editSave(@ModelAttribute SysModule entity) {
        return SysService.me().moduleService().update(entity);
    }

    /** 复制对象 */
    @ResponseBody
    @PostMapping("/copy")
    @Authorize(code = R.Permission.SysModuleInsert)
    public RestResult copy(String id) {
        SysModule old = SysService.me().moduleService().selectById(Long.valueOf(id));
        if (old == null) throw new NotFoundException(id);
        SysModule entity = SerializationUtils.clone(old);
        entity.setId(XCI.nextId());
        entity.setName(entity.getName() + "-复制");
        entity.setCode(entity.getCode() + "-复制");
        return SysService.me().moduleService().insert(entity);
    }

    /** 保存拖拽数据(父节点和路径) */
    @ResponseBody
    @PostMapping("/dnd")
    @Authorize(code = R.Permission.SysModuleUpdate)
    public RestResult dnd(@RequestBody List<TreeNodeIndex> nodeIndexs) {
        SysService.me().moduleService().updateParentId(XCI.toParentIdValues(nodeIndexs));
        SysService.me().moduleService().updatePath(XCI.toPathIdValues(nodeIndexs));
        return RestResult.ok();
    }

    /** 修改状态 */
    @ResponseBody
    @PostMapping("/status")
    @Authorize(code = R.Permission.SysModuleUpdate)
    public RestResult status(String id, Integer status) {
        return SysService.me().moduleService().updateStatus(id, XCI.toBool(status));
    }

    /** 修改展开状态 */
    @ResponseBody
    @PostMapping("/expand")
    @Authorize(code = R.Permission.SysModuleUpdate)
    public RestResult expand(String id, Integer expandStatus) {
        return SysService.me().moduleService().updateExpandStatus(id, XCI.toBool(expandStatus));
    }

    /** 删除 */
    @ResponseBody
    @PostMapping("/delete")
    @Authorize(code = R.Permission.SysModuleDelete)
    public RestResult delete(String id) {
        SysService.me().moduleService().delete(id);
        return RestResult.ok();
    }

    /** 导出 */
    @GetMapping("/export")
    public void export(ModuleFilter filter) {
        XCI.exportExcel(SysService.me().moduleService().selectList(filter), SysModule.class, "系统机构列表");
    }

    //endregion
}