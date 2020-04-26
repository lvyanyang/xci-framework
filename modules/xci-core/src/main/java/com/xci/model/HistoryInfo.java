/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.model;

import lombok.Builder;
import lombok.Getter;

/**
 * 历史日志信息
 */
@Builder
@Getter
public class HistoryInfo {
    /**
     * 主键值
     */
    private Object pk;

    /**
     * 操作前数据
     */
    private Object before;

    /**
     * 操作后数据
     */
    private Object after;

    /**
     * 操作类型
     */
    private HistoryOperateType type;
}