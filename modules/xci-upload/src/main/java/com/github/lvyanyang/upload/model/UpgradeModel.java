/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.upload.model;

import lombok.Data;

import java.util.Date;

/**
 * 更新包信息
 * @author 吕艳阳
 */
@Data
public class UpgradeModel {
    /**
     * 版本
     */
    private int version;

    /**
     * 发布日期
     */
    private Date createTime;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 下载路径
     */
    private String url;
}
