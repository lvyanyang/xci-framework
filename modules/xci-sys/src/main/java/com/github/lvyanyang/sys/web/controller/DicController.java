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
import com.github.lvyanyang.sys.entity.SysDic;
import com.github.lvyanyang.sys.filter.DicFilter;
import com.github.lvyanyang.sys.web.SysWebController;
import com.github.lvyanyang.sys.web.component.SysWebService;
import com.github.lvyanyang.sys.web.model.JsonGrid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统字典控制器
 * @author 吕艳阳
 */
@Authorize
@Slf4j
@Controller
@RequestMapping("/sys/dic")
public class DicController extends SysWebController {
    // region 页面视图

    /** 首页 */
    @GetMapping
    public String index() {
        return "sys/dic/index";
    }

    @GetMapping("/single")
    public String single(String code, ModelMap map) {
        XCI.ifBlankThrow(code, () -> RestResult.fail("请指定字典编码"));
        map.put("categoryCode", code);
        return "sys/dic/single";
    }

    /** 新建页 */
    @GetMapping("/create")
    @Authorize(code = R.Permission.SysDicInsert)
    public String create(String parentId, String categoryCode, ModelMap map) {
        createMark(map);
        SysDic entity = new SysDic();
        entity.setId(XCI.nextId());
        entity.setParentId(Long.valueOf(parentId));
        entity.setCategoryCode(categoryCode);
        entity.setStatus(true);
        map.put("entity", entity);
        map.put("category", SysService.me().dicCategoryService().selectByCode(categoryCode));
        return "sys/dic/edit";
    }

    /** 编辑页 */
    @GetMapping("/edit")
    @Authorize(code = R.Permission.SysDicUpdate)
    public String edit(String id, ModelMap map) {
        SysDic entity = SysService.me().dicService().selectById(Long.valueOf(id));
        if (entity == null) throw new NotFoundException(id);
        map.put("entity", entity);
        map.put("category", SysService.me().dicCategoryService().selectByCode(entity.getCategoryCode()));
        return "sys/dic/edit";
    }

    /** 详情页 */
    @GetMapping("/details")
    public String details(String id, ModelMap map) {
        var longId = Long.valueOf(id);
        SysDic entity = SysService.me().dicService().selectById(longId);
        if (entity == null) throw new NotFoundException(id);
        map.put("entity", entity);
        return "sys/dic/details";
    }

    //endregion

    //region 数据处理

    /** 编辑时获取父节点列表 */
    @ResponseBody
    @GetMapping("/parentList")
    public RestResult parentList(@RequestParam String id, String categoryCode, @RequestParam String created) {
        List<SysDic> list = SysWebService.me().selectEnabledDicList(categoryCode);
        //如果是修改时移除当前记录以及所有下级
        XCI.ifTrueAction(XCI.isBlank(created), () -> XCI.removeTreeChildren(list, Long.valueOf(id), true));
        return RestResult.ok(SysWebService.me().toDicNodeList(list));
    }

    /**
     * 表格数据
     */
    @ResponseBody
    @GetMapping("/grid")
    public JsonGrid grid(DicFilter filter) {
        return new JsonGrid(SysService.me().dicService().selectList(filter));
    }

    /** 新增保存 */
    @ResponseBody
    @PostMapping("/createSave")
    @Authorize(code = R.Permission.SysDicInsert)
    public RestResult createSave(@ModelAttribute SysDic entity) {
        return SysService.me().dicService().insert(entity);
    }

    /** 修改保存 */
    @ResponseBody
    @PostMapping("/updateSave")
    @Authorize(code = R.Permission.SysDicUpdate)
    public RestResult editSave(@ModelAttribute SysDic entity) {
        return SysService.me().dicService().update(entity);
    }

    /** 保存拖拽数据(父节点和路径) */
    @ResponseBody
    @PostMapping("/dnd")
    @Authorize(code = R.Permission.SysDicUpdate)
    public RestResult dnd(@RequestBody List<TreeNodeIndex> nodeIndexs) {
        SysService.me().dicService().updateParentId(XCI.toParentIdValues(nodeIndexs));
        SysService.me().dicService().updatePath(XCI.toPathIdValues(nodeIndexs));
        return RestResult.ok();
    }

    /** 删除 */
    @ResponseBody
    @PostMapping("/delete")
    @Authorize(code = R.Permission.SysDicDelete)
    public RestResult delete(String id) {
        SysService.me().dicService().delete(id);
        return RestResult.ok();
    }

    /** 修改状态 */
    @ResponseBody
    @PostMapping("/status")
    @Authorize(code = R.Permission.SysDicUpdate)
    public RestResult status(String id, Integer status) {
        return SysService.me().dicService().updateStatus(id, XCI.toBool(status));
    }

    /** 导出 */
    @GetMapping("/export")
    public void export(DicFilter filter) {
        XCI.exportExcel(SysService.me().dicService().selectList(filter), SysDic.class, "系统字典列表");
    }

    //endregion
}