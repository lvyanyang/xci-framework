/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 系统权限组件配置参数
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-05-03 00:45
 */
@Data
@ConfigurationProperties(prefix = "xci.sys")
public class SysProperties {
    /** 系统启动时,是否自动加载系统应用缓存 */
    private boolean autoLoadSysAppCache = false;

    /** 系统启动时,是否自动加载系统机构缓存 */
    private boolean autoLoadSysDeptCache = false;

    /** 系统启动时,是否自动加载系统字典缓存 */
    private boolean autoLoadSysDicCache = false;

    /** 系统启动时,是否自动加载系统参数缓存 */
    private boolean autoLoadSysParamCache = false;

    /** 系统启动时,是否自动加载系统报表缓存 */
    private boolean autoLoadSysReportCache = false;

    /** 系统启动时,是否自动加载系统角色缓存 */
    private boolean autoLoadSysRoleCache = false;

    /** 系统启动时,是否自动加载系统用户缓存 */
    private boolean autoLoadSysUserCache = false;
}
