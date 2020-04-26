/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.model;

import lombok.Data;

/**
 * Tree节点序号数据
 */
@Data
public class TreeNodeIndex {
    /**
     * 主键
     */
    private String id;

    /**
     * 父节点
     */
    private String parentId;

    /**
     * 序号
     */
    private Integer index;
}
