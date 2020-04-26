/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.test.sys;

import com.xci.annotation.Authorize;
import com.xci.core.RestResult;
import com.xci.web.WebController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 默认控制器
 */
@Controller
public class DefaultController extends WebController {
    @GetMapping("/")
    public String index() {
        // return "index";
        return redirect("/sys");
    }

    @ResponseBody
    @GetMapping("/abc")
    public RestResult indexApi() {
        return RestResult.ok("服务启动成功");
    }

    @ResponseBody
    @GetMapping("/err")
    public RestResult indexErr() {
        var a = 1;
        var b=0;
        var c = a/b;
        return RestResult.ok();
    }

    @Authorize(code = "abc")
    @ResponseBody
    @GetMapping("/auth")
    public RestResult indexAuth() {
        return RestResult.ok();
    }
}