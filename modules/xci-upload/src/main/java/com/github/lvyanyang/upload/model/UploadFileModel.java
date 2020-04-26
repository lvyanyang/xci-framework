/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.upload.model;

import lombok.Data;

/**
 * 上传的文件信息
 * @author 吕艳阳
 */
@Data
public class UploadFileModel {
    /**
     * 文件主键
     */
    private String id;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件类型
     */
    private String contentType;

    /**
     * 文件路径
     */
    private String url;

    /**
     * 完整 web 路径
     */
    private String webUrl;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件指纹
     */
    private String md5;
}