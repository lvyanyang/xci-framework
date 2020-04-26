/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.model;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 分页结果
 * @author 吕艳阳
 */
public class PageList<E> {
    /**
     * 记录总数
     */
    @ApiModelProperty(value = "记录总数")
    private long total;

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private long totalPage;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private long pageIndex;

    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数")
    private long pageSize;

    /**
     * 数据集合
     */
    @ApiModelProperty(value = "数据集合")
    private List<E> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }

    public static <E> PageList<E> of(List<E> list) {
        Page<E> plist = (Page<E>)list;
        PageList<E> pageResult = new PageList<>();
        pageResult.setTotal(plist.getTotal());
        pageResult.setTotalPage(plist.getPages());
        pageResult.setPageIndex(plist.getPageNum());
        pageResult.setPageSize(plist.getPageSize());
        pageResult.setRows(plist.getResult());
        return pageResult;
    }
}
