/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.web.controller;

import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.exceptions.NotFoundException;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.sys.web.model.JsonGrid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 锁定用户控制器
 */
@Authorize
@Slf4j
@Controller
@RequestMapping("/sys/lockUser")
public class LockUserController extends SysWebController {
    //region 页面视图

    /** 首页 */
    @GetMapping
    @Authorize(code = "sys.lockUser")
    public String index() {
        return "sys/lockuser/index";
    }

    /** 详情页 */
    @GetMapping("/details")
    public String details(String id, ModelMap map) {
        var entity = SysService.me().lockUserService().get(id);
        if (entity == null) throw new NotFoundException(id);
        map.put("entity", entity);
        return "sys/lockuser/details";
    }

    //endregion

    //region 数据处理

    /** 表格查询 */
    @ResponseBody
    @PostMapping("/grid")
    @Authorize(code = "sys.lockUser.select")
    public JsonGrid grid() {
        return new JsonGrid(SysService.me().lockUserService().selectList());
    }

    /**
     * 移除
     */
    @ResponseBody
    @PostMapping("/remove")
    @Authorize(code = "sys.lockUser.remove")
    public RestResult remove(String accounts) {
        for (String account : XCI.splitToArray(accounts)) {
            SysService.me().lockUserService().remove(account);
        }
        return RestResult.ok();
    }

    //endregion
}