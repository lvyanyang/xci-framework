/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.model;

/**
 * 表示一个字典项
 * @author 吕艳阳
 */
public class Dic {
    /**
     * 字典主键
     */
    private Long id;

    /**
     * 上级主键
     */
    private Long parentId;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 名称简拼
     */
    private String spell;

    /**
     * 字典值
     */
    private String value;

    /**
     * 初始化字典项
     */
    public Dic() {
    }

    /**
     * 初始化字典项，名称和值相同
     * @param name 指定的名称，名称和值相同
     */
    public Dic(String name) {
        this(name, name);
    }

    /**
     * 指定键值初始化键值对
     * @param name  名称
     * @param value 值
     */
    public Dic(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Dic(Long id, Long parentId, String name, String spell, String value) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.spell = spell;
        this.value = value;
    }

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

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
