package com.xci.sys.web.controller;

import com.xci.web.WebController;
import com.xci.annotation.Authorize;
import com.xci.exceptions.NotFoundException;
import com.xci.sys.filter.OperateLogFilter;
import com.xci.sys.service.SysService;
import com.xci.sys.web.model.JsonGrid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统操作日志控制器
 */
@Authorize
@Slf4j
@Controller
@RequestMapping("/sys/operateLog")
public class OperateLogController extends WebController {
    //region 页面视图

    /** 首页 */
    @GetMapping
    public String index() {
        return "sys/operatelog/index";
    }

    /** 详情页 */
    @GetMapping("/details")
    public String details(String id, ModelMap map) {
        var entity = SysService.me().operateLogService().selectById(Long.valueOf(id));
        if (entity == null) throw new NotFoundException(id);
        map.put("entity", entity);
        return "sys/operatelog/details";
    }

    //endregion

    //region 数据处理

    /** 表格查询 */
    @ResponseBody
    @PostMapping("/grid")
    public JsonGrid grid(OperateLogFilter filter) {
        return new JsonGrid(SysService.me().operateLogService().selectPageList(filter));
    }

    //endregion
}