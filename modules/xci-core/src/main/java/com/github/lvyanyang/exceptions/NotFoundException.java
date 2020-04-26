/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.exceptions;

/**
 * 实体未找到异常
 * @author 吕艳阳
 */
public class NotFoundException extends RuntimeException {
    private String id;

    public NotFoundException(String id) {
        super("传入的主键有误,无法找到相关数据,id="+id);
        this.id = id;
    }

    public NotFoundException(String id, String message) {
        super(message);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}