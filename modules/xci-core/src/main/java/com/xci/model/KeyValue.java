/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.model;

/**
 * 表示一个键值对
 * @author 吕艳阳
 */
public class KeyValue {
    /**
     * 键名
     */
    private String key;

    /**
     * 键值
     */
    private String value;

    /**
     * 初始化键值对
     */
    public KeyValue()
    {
    }

    /**
     * 指定键初始化键值对，键和值相同
     * @param key 指定的键，键和值相同
     */
    public KeyValue(String key)
    {
        this(key, key);
    }

    /**
     * 指定键值初始化键值对
     * @param key 键
     * @param value 值
     */
    public KeyValue(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
