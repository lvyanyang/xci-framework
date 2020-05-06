/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Tree节点
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeNode {
    /**
     * 节点Id
     */
    private String id;

    /**
     * 父节点Id
     */
    private String pid;

    /**
     * 节点编码
     */
    private String code;

    /**
     * 节点文本
     */
    private String text;

    /**
     * 节点简拼
     */
    private String spell;

    /**
     * 节点值
     */
    private String value;

    /**
     * 节点css类
     */
    private String iconCls;

    /**
     * 该节点是否被选中
     */
    private Boolean checked;

    /**
     * 节点状态: open|closed
     */
    private String state;

    /**
     * 是否叶子节点
     */
    private int leaf;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 子节点
     */
    private List<TreeNode> children;

    /**
     * 绑定该节点的自定义属性
     */
    private Map<String, String> attributes;
}