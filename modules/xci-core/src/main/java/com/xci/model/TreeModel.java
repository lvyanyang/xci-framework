/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.model;

/**
 * 树对象模型
 * @author 吕艳阳
 */
public class TreeModel {
    /**
     * 主键
     */
    private Long id;

    /**
     * 上级主键
     */
    private Long parentId;

    /**
     * 名称
     */
    private String name;

    // /**
    //  * 序号
    //  */
    // private String path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public String getPath() {
    //     return path;
    // }
    //
    // public void setPath(String path) {
    //     this.path = path;
    // }
}