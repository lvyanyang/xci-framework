/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.sys.component;

import com.xci.core.IAppStart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 系统权限模块启动服时输出日志
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-26 02:19
 */
@Slf4j
@Component
public class SysStartInfo implements IAppStart {
    /** 启动 */
    @Override public void start() {
        log.info("系统权限模块加载成功");
    }
}
