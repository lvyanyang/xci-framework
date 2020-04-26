/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.model;

import lombok.Data;

/**
 * 浏览器操作系统IP地址信息
 * @author 吕艳阳
 */
@Data
public class BrowserOsIpInfo {
    /**
     * 浏览器标识
     */
    private String userAgent;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * IP地址
     */
    private String ip;

    /**
     * IP地理位置
     */
    private String ipLocation;
}
