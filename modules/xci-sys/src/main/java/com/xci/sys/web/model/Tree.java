package com.xci.sys.web.model;

import lombok.Data;

/**
 * Tree模型
 */
@Data
public class Tree {
    /**
     * 主键
     */
    private String id;

    /**
     * 上级主键
     */
    private String parentId;

    /**
     * 排序路径
     */
    private String path;

    /**
     * 是否叶子节点
     */
    private Integer leaf;

    /**
     * 名称
     */
    private String name;

    /**
     * 简拼
     */
    private String spell;
}