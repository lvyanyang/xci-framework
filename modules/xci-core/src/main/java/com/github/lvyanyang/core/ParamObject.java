/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.core;

/**
 * 参数对象
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-05-04 02:05
 */
public class ParamObject {
    private final IApplication app;
    private final String code;
    private final Object defaultValue;

    /**
     * 初始化参数对象
     * @param code         参数编码
     * @param defaultValue 参数默认值
     */
    public ParamObject(String code, Object defaultValue) {
        this.app = XCI.getBean(IApplication.class);
        this.code = code;
        this.defaultValue = defaultValue;
    }

    /**
     * 获取参数编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取参数默认值
     */
    public Object getDefaultValue() {
        return defaultValue;
    }

    /**
     * 获取参数值
     */
    public Object get() {
        var result = app.getParam(code, defaultValue);
        if (XCI.isBlank(result)) {
            return R.Empty;
        }
        return result;
    }

    /**
     * 获取字符串型参数值
     */
    public String getString() {
        return XCI.toStr(get());
    }

    /**
     * 获取整型参数值
     */
    public Integer getInt() {
        return XCI.toInt(get());
    }

    /**
     * 获取长整型参数值
     */
    public Long getLong() {
        return XCI.toLong(get());
    }

    /**
     * 获取Short参数值
     */
    public short getShort() {
        return XCI.toShort(get());
    }

    /**
     * 获取Float参数值
     */
    public float getFloat() {
        return XCI.toFloat(get());
    }

    /**
     * 获取Double参数值
     */
    public double getDouble() {
        return XCI.toDouble(get());
    }

    /**
     * 获取Boolean参数值
     */
    public boolean getBoolean() {
        return XCI.toBool(get());
    }

    @Override
    public String toString() {
        return getString();
    }
}