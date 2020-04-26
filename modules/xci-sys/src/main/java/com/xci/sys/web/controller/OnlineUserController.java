package com.xci.sys.web.controller;

import com.xci.annotation.Authorize;
import com.xci.exceptions.NotFoundException;
import com.xci.core.RestResult;
import com.xci.core.XCI;
import com.xci.sys.service.SysService;
import com.xci.sys.web.model.JsonGrid;
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