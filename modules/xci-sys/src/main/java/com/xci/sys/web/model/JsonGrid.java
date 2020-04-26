package com.xci.sys.web.model;

import com.xci.model.PageList;
import lombok.Data;

import java.util.List;

/**
 * Json表格
 * @author lvyanyang
 */
@Data
public class JsonGrid{
    /**
     * 总记录数
     */
    private long total;

    /**
     * 数据集合
     */
    private List rows;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 消息
     */
    private String msg;

    public JsonGrid(PageList list) {
        this.success = true;
        this.total = list.getTotal();
        this.rows = list.getRows();
    }

    public JsonGrid(List list) {
        this.success = true;
        this.total = list.size();
        this.rows = list;
    }

    public JsonGrid(List list,long total) {
        this.success = true;
        this.total = total;
        this.rows = list;
    }
}
