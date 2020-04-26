/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 表示一个主键值对
 * @author 吕艳阳
 */
@ApiModel(description = "主键值对")
public class IdValue {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 键值
     */
    @ApiModelProperty(value = "值")
    private Object value;

    public IdValue()
    {
    }

    public IdValue(Long id)
    {
        this(id, id);
    }

    public IdValue(Long id, Object value)
    {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}