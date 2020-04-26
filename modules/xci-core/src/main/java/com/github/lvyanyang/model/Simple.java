/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.model;

/**
 * 表示一个键值简单对象
 * @author 吕艳阳
 */
public class Simple {
    /**
     * 主键
     */
    private String id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 简拼
     */
    private String spell;

    /**
     * 构建键值简单对象
     */
    public Simple() {
    }

    /**
     * 构建键值简单对象
     *
     * @param id   主键
     * @param name 名称
     */
    public Simple(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 构建键值简单对象
     * @param id 主键
     * @param code 编码
     * @param name 名称
     * @param spell 简拼
     */
    public Simple(String id, String code, String name, String spell) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.spell = spell;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
