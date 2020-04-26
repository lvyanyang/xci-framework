/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.core;

import cn.hutool.core.io.FileUtil;

import java.io.File;

/**
 * 表示Json文件
 * @author 吕艳阳
 */
public class JsonFile<T> {
    public T data;
    private Class<T> tClass;
    private File file;
    private T defaultValue;

    public JsonFile(String path, T defaultValue, Class<T> c) {
        this.file = XCI.buildRootFile(path);
        this.tClass = c;
        this.defaultValue = defaultValue;
        load();
    }

    /**
     * 从指定文件中重新加载对象数据
     */
    public void load() {
        if (file.exists()) {
            String content = FileUtil.readUtf8String(file);
            if (XCI.isNotBlank(content)) {
                data = XCI.toJsonObject(content, tClass);
            }
        }
        if (data == null) {
            data = defaultValue;
        }
    }

    /**
     * 保存数据到指定文件中
     */
    public void save() {
        String content = XCI.toJsonString(data, true);
        FileUtil.mkParentDirs(file);
        FileUtil.writeUtf8String(content, file);
    }
}