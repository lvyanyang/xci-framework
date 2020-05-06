/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.core;

import com.github.lvyanyang.model.Dic;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典对象
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-05-04 02:05
 */
public class DicObject {
    private final IApplication app;
    private final String code;

    /**
     * 初始化字典对象
     * @param code 字典类型编码
     */
    public DicObject(String code) {
        this.app = XCI.getBean(IApplication.class);
        this.code = code;
    }

    /**
     * 获取字典类型编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取字典列表
     */
    public List<Dic> get() {
        var result = app.getDic(code);
        if (result == null) {
            return new ArrayList<>();
        }
        return result;
    }

    /**
     * 根据值获取名称
     * @param value 字典值
     * @param def   如果找不到则返回此值
     */
    public String getName(Object value, String def) {
        if (XCI.isBlank(value)) return def;
        var dicList = get();
        for (Dic dic : dicList) {
            if (dic.getValue().equalsIgnoreCase(value.toString())) {
                return dic.getName();
            }
        }
        return def;
    }

    /**
     * 根据名称获取值
     * @param name 字典名称
     * @param def  如果找不到则返回此值
     */
    public String getValue(String name, String def) {
        var dicList = get();
        for (Dic dic : dicList) {
            if (dic.getName().equalsIgnoreCase(name)) {
                return dic.getValue();
            }
        }
        return def;
    }
}