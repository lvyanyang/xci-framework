/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.test.web;

import com.xci.base.ApiController;
import com.xci.core.RestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DefaultController extends ApiController {
    @GetMapping("/index")
    public RestResult index() {
        return RestResult.ok("Api启动成功");
    }
}