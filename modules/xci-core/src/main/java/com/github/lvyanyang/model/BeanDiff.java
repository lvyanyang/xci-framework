/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.model;

/**
 * Bean对象差异
 * @author 吕艳阳
 */
public class BeanDiff {
    /** 字段描述 */
    private String fieldLabel;
    /** 字段名称 */
    private String fieldName;

    /** 修改前数据 */
    private Object before;

    /** 修改后数据 */
    private Object after;

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getBefore() {
        return before;
    }

    public void setBefore(Object before) {
        this.before = before;
    }

    public Object getAfter() {
        return after;
    }

    public void setAfter(Object after) {
        this.after = after;
    }
}
