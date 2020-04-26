/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.test.upload;

import com.github.lvyanyang.base.ApiController;
import com.github.lvyanyang.core.RestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DefaultController extends ApiController {
    @GetMapping("/")
    public RestResult index() {
        return RestResult.ok("文件服务器启动成功");
    }

    @GetMapping("/err404")
    public RestResult err404() {
        return RestResult.ok("无效的路径1");
    }
}