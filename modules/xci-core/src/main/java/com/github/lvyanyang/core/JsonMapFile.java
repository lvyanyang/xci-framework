/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.core;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.HashMap;

/**
 * 表示Json列表文件
 * @author 吕艳阳
 */
public class JsonMapFile<T> {
    public HashMap<String, T> data;
    private Class<T> tClass;
    private File file;

    public JsonMapFile(String path, Class<T> c) {
        this.file = XCI.buildRootFile(path);
        this.tClass = c;
        load();
    }

    /**
     * 从指定文件中重新加载数据
     */
    public void load() {
        if(file.exists()) {
            String content = FileUtil.readUtf8String(file);
            if(XCI.isNotBlank(content)) {
                data = XCI.toJsonMapObject(content, HashMap.class, String.class, tClass);
            }
        }
        if(data == null) {
            data = new HashMap<>();
        }
    }

    /**
     * 保存配置数据到指定文件中
     */
    public void save() {
        String content = XCI.toJsonString(data, true);
        FileUtil.mkParentDirs(file);
        FileUtil.writeUtf8String(content, file);
    }
}
