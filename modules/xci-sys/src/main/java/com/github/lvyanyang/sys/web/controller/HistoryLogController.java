/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.web.controller;

import com.github.lvyanyang.sys.service.SysService;
import com.github.lvyanyang.web.WebController;
import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.exceptions.NotFoundException;
import com.github.lvyanyang.sys.filter.HistoryLogFilter;
import com.github.lvyanyang.sys.web.model.JsonGrid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统历史日志控制器
 */
@Authorize
@Slf4j
@Controller
@RequestMapping("/sys/historyLog")
public class HistoryLogController extends WebController {
    //region 页面视图

    /** 首页 */
    @GetMapping
    public String index() {
        return "sys/historylog/index";
    }

    /** 详情页 */
    @GetMapping("/details")
    public String details(String id, ModelMap map) {
        var entity = SysService.me().historyLogService().selectById(Long.valueOf(id));
        if (entity == null) throw new NotFoundException(id);
        map.put("entity", entity);
        return "sys/historylog/details";
    }

    //endregion

    //region 数据处理

    /** 表格查询 */
    @ResponseBody
    @PostMapping("/grid")
    public JsonGrid grid(HistoryLogFilter filter) {
        return new JsonGrid(SysService.me().historyLogService().selectPageList(filter));
    }

    //endregion
}