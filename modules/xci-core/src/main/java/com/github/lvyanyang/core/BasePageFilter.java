/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.core;

import io.swagger.annotations.ApiModelProperty;

/**
 * 数据过滤基类
 * @author 吕艳阳
 */
public class BasePageFilter extends BaseFilter {
    /**
     * 页码,从1开始
     */
    @ApiModelProperty(value = "页码,从1开始", example = "1", position = 9001)
    private Integer pageIndex = 1;

    /**
     * 每页数量
     */
    @ApiModelProperty(value = "每页数量", example = "10", position = 9002)
    private Integer pageSize = 10;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段", position = 9003)
    private String sortName;

    /**
     * 排序方式
     */
    @ApiModelProperty(value = "排序方式", position = 9004)
    private String sortDir;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortDir() {
        return sortDir;
    }

    public void setSortDir(String sortDir) {
        this.sortDir = sortDir;
    }
}