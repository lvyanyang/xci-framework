/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.model;

import lombok.Data;

/**
 * IP地点信息
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-05-06 10:35
 */
@Data
public class IpInfo {
     /**
     * 国家
     */
    private String country;
    /**
     * 区域
     */
    private String area;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 运营商
     */
    private String isp;
}
