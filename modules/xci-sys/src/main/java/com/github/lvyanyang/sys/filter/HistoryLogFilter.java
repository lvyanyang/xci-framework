/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.filter;

import com.github.lvyanyang.core.BasePageFilter;
import com.github.lvyanyang.core.XCI;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 系统历史日志过滤条件
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "系统历史日志过滤条件")
public class HistoryLogFilter extends BasePageFilter {
    /**
     * 操作类型 [1-新增, 2-修改, 3-删除]
     */
    @ApiModelProperty(value = "操作类型 [1-新增, 2-修改, 3-删除]")
    private String category;

    /**
     * 操作类型数组
     */
    @ApiModelProperty(hidden = true)
    private String[] categorys;

    /**
     * 表名
     */
    @ApiModelProperty(value = "表名")
    private String tableName;

    /**
     * 记录主键
     */
    @ApiModelProperty(value = "记录主键")
    private String primaryKey;

    /**
     * 操作人姓名
     */
    @ApiModelProperty(value = "操作人姓名")
    private String operateUserName;

    /**
     * 操作开始时间
     */
    @ApiModelProperty(value = "操作开始时间")
    private Date operateStartDateTime;

    /**
     * 操作结束时间
     */
    @ApiModelProperty(value = "操作结束时间")
    private Date operateEndDateTime;

    /**
     * 分割操作类型为数组
     */
    public void splitCategory() {
        categorys = XCI.splitToArrayOrNull(category);
    }

    /// <summary>
    /// 处理结束日期,结束日期增加一天
    /// </summary>
    public void PlusEndDateTime() {
        setOperateEndDateTime(XCI.PlusEndDateTime(operateEndDateTime));
    }
}