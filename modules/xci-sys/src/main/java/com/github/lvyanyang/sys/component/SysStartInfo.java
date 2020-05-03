/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.component;

import com.github.lvyanyang.core.IAppStart;
import com.github.lvyanyang.sys.configuration.SysProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 系统权限模块启动服时输出日志
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-26 02:19
 */
@Slf4j
@Component
public class SysStartInfo implements IAppStart {
    @Resource private SysProperties sysProperties;

    /** 启动 */
    @Override
    public void start() {
        log.info("加载系统权限模块成功");

        // 系统启动时,是否自动加载系统应用缓存
        if (sysProperties.isAutoLoadSysAppCache()){
            SysService.me().appService().refresh();
        }
        // 系统启动时,是否自动加载系统机构缓存
        if (sysProperties.isAutoLoadSysDeptCache()){
            SysService.me().deptService().refresh();
        }
        // 系统启动时,是否自动加载系统字典缓存
        if (sysProperties.isAutoLoadSysDicCache()){
            SysService.me().dicService().refresh();
        }
        // 系统启动时,是否自动加载系统参数缓存
        if (sysProperties.isAutoLoadSysParamCache()){
            SysService.me().paramService().refresh();
        }
        // 系统启动时,是否自动加载系统报表缓存
        if (sysProperties.isAutoLoadSysReportCache()){
            SysService.me().reportService().refresh();
        }
        // 系统启动时,是否自动加载系统角色缓存
        if (sysProperties.isAutoLoadSysRoleCache()){
            SysService.me().roleService().refresh();
        }
        // 系统启动时,是否自动加载系统用户缓存
        if (sysProperties.isAutoLoadSysUserCache()){
            SysService.me().userService().refresh();
        }
    }
}
