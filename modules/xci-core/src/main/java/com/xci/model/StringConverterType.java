/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.model;

/**
 * 字符串转换策略
 */
public enum StringConverterType {
    /** 自定义实现对象 */
    custom(0),

    /** 去除左边空格 */
    trimLeft(1),

    /** 去除右边空格 */
    trimRight(2),

    /** 去除左边和右边空格 */
    trimLeftRight(3),

    /** 去除所有空格 */
    trimAll(4),

    /** 半角转全角 */
    toSBC(5),

    /** 全角转半角 */
    toDBC(6),

    /** 将驼峰式命名的字符串转换为下划线方式 */
    toUnderlineCase(7),

    /** 将下划线方式命名的字符串转换为驼峰式 */
    toCamelCase(8),

    /** 字符串替换 */
    replace(9);

    private final int value;

    StringConverterType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}