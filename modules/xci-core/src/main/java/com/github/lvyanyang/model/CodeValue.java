/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.model;

/**
 * 表示一个键值对
 * @author lvyanyang
 */
public class CodeValue {
    /**
     * 键名
     */
    private String code;

    /**
     * 键值
     */
    private String value;

    /**
     * 初始化键值对
     */
    public CodeValue()
    {
    }

    /**
     * 指定键初始化键值对，键和值相同
     * @param code 指定的键，键和值相同
     */
    public CodeValue(String code)
    {
        this(code, code);
    }

    /**
     * 指定键值初始化键值对
     * @param code 键
     * @param value 值
     */
    public CodeValue(String code, String value)
    {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String key) {
        this.code = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
