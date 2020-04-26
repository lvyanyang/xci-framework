/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.core;

/**
 * 树对象接口
 * @author 吕艳阳
 */
public interface ITreeModel {
    /**
     * 获取主键
     */
    Long getId();

    /**
     * 获取上级主键
     */
    Long getParentId();
}
