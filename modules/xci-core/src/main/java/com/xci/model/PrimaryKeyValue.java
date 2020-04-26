/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 表示一个唯一键值对
 * @author 吕艳阳
 */
@ApiModel(description = "唯一键值对")
public class PrimaryKeyValue {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 字段名
     */
    @ApiModelProperty(value = "字段名")
    private String key;

    /**
     * 字段值
     */
    @ApiModelProperty(value = "字段值")
    private String value;

    /**
     * 初始化键值对
     */
    public PrimaryKeyValue() {
    }

    /**
     * 指定键值初始化键值对
     * @param id    唯一标识
     * @param key   键
     * @param value 值
     */
    public PrimaryKeyValue(String id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
