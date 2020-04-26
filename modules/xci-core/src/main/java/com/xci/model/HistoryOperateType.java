/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.model;

/**
 * 历史记录操作类型
 */
public enum HistoryOperateType {

    /**
     * 新建
     */
    Insert(1, "新建"),

    /**
     * 修改
     */
    Update(2, "修改"),

    /**
     * 删除
     */
    Delete(3, "删除");

    private Integer id;
    private String name;

    /**
     * 历史记录操作类型
     * @param name 描述信息
     */
    HistoryOperateType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 获取Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 获取描述信息
     */
    public String getName() {
        return name;
    }
}