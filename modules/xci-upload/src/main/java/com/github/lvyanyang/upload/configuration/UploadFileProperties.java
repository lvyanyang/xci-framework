/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.upload.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 文件上传配置
 * @author 吕艳阳
 */
@Data
@ConfigurationProperties(prefix = "xci.upload")
public class UploadFileProperties {
    /**
     * 上传根目录
     */
    private String root = "/{webroot}/uploads";

    /**
     * 文件访问根路径
     */
    private String url = "/uploads";

    /**
     * 允许上传的扩展名,不含点
     * "gif", "jpg", "jpeg", "png", "bmp", "flv", "mp3", "mp4", "wav", "wma", "avi", "mpg", "rmvb", "zip", "rar", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "pdf"
     * "gif", "jpg", "jpeg", "png", "bmp", "zip", "rar", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "pdf", "repx", "json", "xml"
     */
    private String[] extensions = new String[]{"gif", "jpg", "jpeg", "png", "bmp", "zip", "rar", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "pdf", "repx", "json", "xml"};

    /**
     * 允许上传的最大文件大小,单位KB(单个文件大小)
     */
    private long maxSize = 10240;//10M

    /**
     * 使用启用软件更新包功能
     */
    private boolean enableUpgrade = false;

    /**
     * 上传更新包密码
     */
    private String upgradePassword = "www.xci96716.com";

    /**
     * 是否验证文件Token
     */
    private boolean validFileToken = false;

    /**
     * 是否允许删除
     */
    private boolean allowDelete = true;
}