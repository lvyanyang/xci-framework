/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.upload.component;

import cn.hutool.core.io.FileUtil;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.upload.configuration.UploadFileProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * 文件上传服务
 * @author 吕艳阳
 */
@Slf4j
@Component
public class FileUploadService {
    @Resource private UploadFileProperties fileUploadProperties;
    private static FileUploadService me;

    public static FileUploadService me() {
        return me;
    }

    @PostConstruct
    private void init() {
        FileUploadService.me = this;
    }

    /**
     * 上传文件
     * @param file 上传文件对象
     * @return 返回上传文件信息
     */
    public RestResult<UploadFileInfo> upload(MultipartFile file) {
        return upload(file, null, null);
    }

    /**
     * 上传文件
     * @param file       上传文件对象
     * @param subCatalog 子目录,如果为null,则自动生成
     * @return 返回上传文件信息
     */
    public RestResult<UploadFileInfo> upload(MultipartFile file, String subCatalog) {
        return upload(file, subCatalog, null);
    }

    /**
     * 上传文件
     * @param file              上传文件对象
     * @param subCatalog        子目录,如果为null,则自动生成
     * @param fileUploadSetting 上传配置,如果为null,则使用默认值
     * @return 返回上传文件信息
     */
    public RestResult<UploadFileInfo> upload(MultipartFile file, String subCatalog, UploadFileProperties fileUploadSetting) {
        if (file == null || file.getSize() == 0) {
            return RestResult.fail("请指定要上传的文件");
        }
        if (fileUploadSetting == null) {
            fileUploadSetting = fileUploadProperties;
        }
        if (XCI.isBlank(fileUploadSetting.getRoot())) {
            return RestResult.fail("请指定文件上传根目录");
        }
        if (fileUploadSetting.getMaxSize() <= 0) {
            return RestResult.fail("请指定允许文件上传的最大文件大小");
        }
        if (fileUploadSetting.getExtensions() == null || fileUploadSetting.getExtensions().length == 0) {
            return RestResult.fail("请指定允许文件上传的扩展名");
        }

        String originFileName = file.getOriginalFilename();
        if (XCI.isNotBlank(originFileName)) {
            originFileName = originFileName.replace("&", "").replace("?", "");
        }

        if (XCI.isBlank(FileUtil.mainName(originFileName))) {
            return RestResult.fail("请指定上传文件的文件名");
        }
        String extName = FileUtil.extName(originFileName);
        if (XCI.isBlank(extName)) {
            return RestResult.fail("请指定上传文件的扩展名");
        }

        extName = extName.toLowerCase();
        long allowFileMaxSize = fileUploadSetting.getMaxSize() * 1024;
        if (file.getSize() > allowFileMaxSize) {
            String msg = XCI.format("上传文件大小超过限制,系统限制最大为{}", FileUtil.readableFileSize(allowFileMaxSize));
            return RestResult.fail(msg);
        }
        String finalExtName = extName;
        if (Arrays.stream(fileUploadSetting.getExtensions()).noneMatch(p -> p.toLowerCase().equals(finalExtName))) {
            return RestResult.fail("当前文件类型禁止上传");
        }

        if (XCI.isNotBlank(subCatalog)) {
            subCatalog = subCatalog.replace("\\", "/");
        }
        var fileName = XCI.format("{}.{}", XCI.nextId(), extName);
        String filePath = XCI.pathOf(subCatalog, fileName);

        try {
            // if(saveFileObject.exists()){
            //     return new BoolMessage<>(false, "指定的文件名称已存在");
            // }else {
            //     FileUtil.mkParentDirs(saveFileObject);
            //     FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(saveFileObject));
            // }
            File saveFile = new File(buildDistFilePath(filePath, fileUploadSetting));
            FileUtil.mkParentDirs(saveFile);
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(saveFile));
        } catch (IOException e) {
            return RestResult.fail(e.getMessage());
        }

        UploadFileInfo info = new UploadFileInfo();
        info.setName(originFileName);
        try {
            info.setContentType(Files.probeContentType(Path.of(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        info.setUrl(filePath);
        info.setSize(file.getSize());
        return RestResult.ok(info);
    }

    /**
     * 生成上传文件路径
     * @param path 路径
     */
    public String buildDistFilePath(String path) {
        return buildDistFilePath(path, null);
    }

    /**
     * 生成上传文件路径
     * @param fileUploadSetting 上传配置,如果为null,则使用默认值
     * @param path              路径
     */
    public String buildDistFilePath(String path, UploadFileProperties fileUploadSetting) {
        if (fileUploadSetting == null) fileUploadSetting = fileUploadProperties;

        var root = fileUploadSetting.getRoot();
        if (XCI.isBlank(root)) {
            XCI.appThrow("请指定文件上传根目录");
        }
        if (root.startsWith("/{webroot}")) {
            root = root.replace("/{webroot}", new File(R.Empty).getAbsolutePath());
        }
        return XCI.pathOf(root, path);
    }

    public String[] buildFileResourceHandler(UploadFileProperties fileUploadSetting) {
        if (fileUploadSetting == null) fileUploadSetting = fileUploadProperties;
        var sz = XCI.splitToArray(fileUploadSetting.getUrl());
        var arrays = new String[sz.length];
        for (int i = 0; i < sz.length; i++) {
            String item = sz[i];
            arrays[i] = XCI.pathOfClean(item) + "/**";
        }
        return arrays;
    }

    public String buildFileResourceLocation(UploadFileProperties fileUploadSetting) {
        if (fileUploadSetting == null) fileUploadSetting = fileUploadProperties;
        var local = "file:" + buildDistFilePath(null, fileUploadSetting);
        if (!local.endsWith("/")) {
            local = local + "/";
        }
        return local;
    }

    public String buildFileWebUrl(String url, UploadFileProperties fileUploadSetting) {
        if (fileUploadSetting == null) fileUploadSetting = fileUploadProperties;

        var rurl = XCI.pathOfClean(fileUploadSetting.getUrl());
        url = XCI.pathOfClean(url);

        return XCI.getSiteFullUrl(XCI.pathOf(rurl, url));
    }


    /**
     * 上传文件信息
     * @author 吕艳阳
     */
    public static class UploadFileInfo {
        /**
         * 文件名称
         */
        private String name;

        /**
         * 文件类型
         */
        private String contentType;

        /**
         * 虚拟路径
         */
        private String url;

        /**
         * 文件大小
         */
        private long size;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }
    }
}