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
import com.github.lvyanyang.sys.entity.SysDicCategory;
import com.github.lvyanyang.sys.web.component.SysWebService;
import com.github.lvyanyang.sys.web.model.JsonGrid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统字典类型控制器
 * @author 吕艳阳
 */
@Authorize
@Slf4j
@Controller
@RequestMapping("/sys/dicCategory")
public class DicCategoryController extends SysWebController {
    // region 页面视图

    /** 首页 */
    @GetMapping
    public String index() {
        return "sys/diccategory/index";
    }

    /** 新建页 */
    @GetMapping("/create")
    @Authorize(code = R.Permission.SysDicCategoryInsert)
    public String create(String parentId, ModelMap map) {
        createMark(map);
        SysDicCategory entity = new SysDicCategory();
        entity.setId(XCI.nextId());
        entity.setParentId(Long.valueOf(parentId));
        map.put("entity", entity);
        return "sys/diccategory/edit";
    }

    /** 编辑页 */
    @GetMapping("/edit")
    @Authorize(code = R.Permission.SysDicCategoryUpdate)
    public String edit(String id, ModelMap map) {
        SysDicCategory entity = SysService.me().dicCategoryService().selectById(Long.valueOf(id));
        if (entity == null) throw new NotFoundException(id);
        map.put("entity", entity);
        return "sys/diccategory/edit";
    }

    /** 详情页 */
    @GetMapping("/details")
    public String details(String id, ModelMap map) {
        var longId = Long.valueOf(id);
        SysDicCategory entity = SysService.me().dicCategoryService().selectById(longId);
        if (entity == null) throw new NotFoundException(id);
        map.put("entity", entity);
        return "sys/diccategory/details";
    }

    //endregion

    //region 数据处理

    /** 编辑时获取父节点列表 */
    @ResponseBody
    @GetMapping("/parentList")
    public RestResult parentList(@RequestParam String id, @RequestParam String created) {
        List<SysDicCategory> list = SysWebService.me().dicCategoryService().selectList();
        //如果是修改时移除当前记录以及所有下级
        XCI.ifTrueAction(XCI.isBlank(created), () -> XCI.removeTreeChildren(list, Long.valueOf(id), true));
        return RestResult.ok(SysWebService.me().toDicCategoryNodeList(list));
    }

    /**
     * 字典类型Tree
     */
    @ResponseBody
    @GetMapping("/tree")
    public Object tree() {
        var list = SysService.me().dicCategoryService().selectList();
        return RestResult.ok(SysWebService.me().toDicCategoryNodeList(list));
    }

    /**
     * 表格数据
     */
    @ResponseBody
    @GetMapping("/grid")
    public JsonGrid grid() {
        return new JsonGrid(SysService.me().dicCategoryService().selectList());
    }

    /** 新增保存 */
    @ResponseBody
    @PostMapping("/createSave")
    @Authorize(code = R.Permission.SysDicCategoryInsert)
    public RestResult createSave(@ModelAttribute SysDicCategory entity) {
        return SysService.me().dicCategoryService().insert(entity);
    }

    /** 修改保存 */
    @ResponseBody
    @PostMapping("/updateSave")
    @Authorize(code = R.Permission.SysDicCategoryUpdate)
    public RestResult editSave(@ModelAttribute SysDicCategory entity) {
        return SysService.me().dicCategoryService().update(entity);
    }

    /** 保存拖拽数据(父节点和路径) */
    @ResponseBody
    @PostMapping("/dnd")
    @Authorize(code = R.Permission.SysDicCategoryUpdate)
    public RestResult dnd(@RequestBody List<TreeNodeIndex> nodeIndexs) {
        SysService.me().dicCategoryService().updateParentId(XCI.toParentIdValues(nodeIndexs));
        SysService.me().dicCategoryService().updatePath(XCI.toPathIdValues(nodeIndexs));
        return RestResult.ok();
    }

    /** 删除 */
    @ResponseBody
    @PostMapping("/delete")
    @Authorize(code = R.Permission.SysDicCategoryDelete)
    public RestResult delete(String id) {
        SysService.me().dicCategoryService().delete(id);
        return RestResult.ok();
    }

    /** 导出 */
    @GetMapping("/export")
    public void export() {
        XCI.exportExcel(SysService.me().dicCategoryService().selectList(), SysDicCategory.class, "系统字典类型列表");
    }

    //endregion
}