/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.upload.component;

import com.xci.core.IAppStart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 文件上传模块启动服时输出日志
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-25 23:19
 */
@Slf4j
@Component
public class UploadFileStartInfo implements IAppStart {
    /** 启动 */
    @Override public void start() {
        log.info("文件上传模块加载成功");
    }
}
