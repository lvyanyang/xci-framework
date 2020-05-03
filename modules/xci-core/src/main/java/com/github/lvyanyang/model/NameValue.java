/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.model;

/**
 * 表示一个键值对
 * @author 吕艳阳
 */
public class NameValue {
    /**
     * 键名
     */
    private String name;

    /**
     * 键值
     */
    private String value;

    /**
     * 初始化键值对
     */
    public NameValue()
    {
    }

    /**
     * 指定键初始化键值对，键和值相同
     * @param name 指定的键，键和值相同
     */
    public NameValue(String name)
    {
        this(name, name);
    }

    /**
     * 指定键值初始化键值对
     * @param name 键
     * @param value 值
     */
    public NameValue(String name, String value)
    {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
