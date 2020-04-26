/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.web.controller;

import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.exceptions.NotFoundException;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.sys.entity.SysSeq;
import com.github.lvyanyang.sys.filter.SeqFilter;
import com.github.lvyanyang.sys.web.model.JsonGrid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 系统序列控制器
 */
@Authorize
@Controller
@RequestMapping("/sys/seq")
public class SeqController extends SysWebController {
    //region 页面视图

    /** 首页 */
    @GetMapping
    public String index() {
        return "sys/seq/index";
    }

    /** 新建页 */
    @GetMapping("/create")
    @Authorize(code = R.Permission.SysSeqInsert)
    public String create(ModelMap map) {
        createMark(map);
        var entity = new SysSeq();
        entity.setId(XCI.nextId());
        entity.setStartWith(1L);
        entity.setIncrementBy(1);
        map.put("entity", entity);
        return "sys/seq/edit";
    }

    /** 编辑页 */
    @GetMapping("/edit")
    @Authorize(code = R.Permission.SysSeqUpdate)
    public String edit(String id, ModelMap map) {
        var entity = SysService.me().seqService().selectById(Long.valueOf(id));
        if (entity == null) throw new NotFoundException(id);
        map.put("entity", entity);
        return "sys/seq/edit";
    }

    /** 详情页 */
    @GetMapping("/details")
    public String details(String id, ModelMap map) {
        var entity = SysService.me().seqService().selectById(Long.valueOf(id));
        if (entity == null) throw new NotFoundException(id);
        map.put("entity", entity);
        return "sys/seq/details";
    }

    //endregion

    //region 数据处理

    /** 表格查询 */
    @ResponseBody
    @PostMapping("/grid")
    public JsonGrid grid(SeqFilter filter) {
        return new JsonGrid(SysService.me().seqService().selectPageList(filter));
    }

    /** 新增保存 */
    @ResponseBody
    @PostMapping("/createSave")
    @Authorize(code = R.Permission.SysSeqInsert)
    public RestResult createSave(@ModelAttribute SysSeq entity) {
        return SysService.me().seqService().insert(entity);
    }

    /** 修改保存 */
    @ResponseBody
    @PostMapping("/updateSave")
    @Authorize(code = R.Permission.SysSeqUpdate)
    public RestResult updateSave(@ModelAttribute SysSeq entity) {
        return SysService.me().seqService().update(entity);
    }


    /** 删除 */
    @ResponseBody
    @PostMapping("/delete")
    @Authorize(code = R.Permission.SysSeqDelete)
    public RestResult delete(String ids) {
        SysService.me().seqService().delete(ids);
        return RestResult.ok();
    }

    /** 导出 */
    @GetMapping("/export")
    public void export(SeqFilter filter) {
        XCI.exportExcel(SysService.me().seqService().selectList(filter), SysSeq.class, "系统序列列表");
    }

    //endregion
}