/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.web.controller;

import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.exceptions.NotFoundException;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.web.SysWebController;
import com.github.lvyanyang.sys.web.model.JsonGrid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统在线用户控制器
 */
@Authorize
@Controller
@RequestMapping("/sys/onlineUser")
public class OnlineUserController extends SysWebController {
    //region 页面视图

    /** 首页 */
    @GetMapping
    @Authorize(code = "sys.onlineUser")
    public String index() {
        return "sys/onlineuser/index";
    }

    /** 详情页 */
    @GetMapping("/details")
    public String details(String id, ModelMap map) {
        var entity = SysService.me().onlineUserService().get(Long.valueOf(id));
        if (entity == null) throw new NotFoundException(id);
        map.put("entity", entity);
        return "sys/onlineuser/details";
    }

    //endregion

    //region 数据处理

    /** 表格查询 */
    @ResponseBody
    @PostMapping("/grid")
    @Authorize(code = "sys.onlineUser.select")
    public JsonGrid grid(String name) {
        return new JsonGrid(SysService.me().onlineUserService().selectList(name));
    }

    /** 注销 */
    @ResponseBody
    @PostMapping("/logoff")
    @Authorize(code = "sys.onlineUser.logoff")
    public RestResult logoff(String ids) {
        for (String id : XCI.splitToArray(ids)) {
            SysService.me().onlineUserService().logoff(Long.valueOf(id));
        }
        return RestResult.ok();
    }

    //endregion
}