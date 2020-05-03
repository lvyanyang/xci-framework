/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.model;

/**
 * 字符串转换策略
 */
public enum StringConverterType {
    /** 自定义实现对象 */
    Custom(0),

    /** 去除左边空格 */
    TrimLeft(1),

    /** 去除右边空格 */
    TrimRight(2),

    /** 去除左边和右边空格 */
    TrimLeftRight(3),

    /** 去除所有空格 */
    TrimAll(4),

    /** 半角转全角 */
    ToSBC(5),

    /** 全角转半角 */
    ToDBC(6),

    /** 将驼峰式命名的字符串转换为下划线方式 */
    ToUnderlineCase(7),

    /** 将下划线方式命名的字符串转换为驼峰式 */
    ToCamelCase(8),

    /** 字符串替换 */
    Replace(9);

    private final int value;

    StringConverterType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}