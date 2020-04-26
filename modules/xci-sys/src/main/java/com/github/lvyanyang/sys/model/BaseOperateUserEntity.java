/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.lvyanyang.core.BaseEntity;
import com.github.lvyanyang.core.R;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 实体(操作人)基类
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseOperateUserEntity extends BaseEntity {
    /**
     * 操作人主键
     */
    @ExcelIgnore @Excel(name = "操作人主键")
    @ApiModelProperty(value = "操作人主键", position = 901)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long operateUserId;

    /**
     * 操作人姓名
     */
    @Excel(name = "操作人姓名", width = 20d)
    @ApiModelProperty(value = "操作人姓名", position = 902)
    private String operateUserName;

    /**
     * 操作时间
     */
    @Excel(name = "操作时间", width = 20d, exportFormat = R.DEFAULT_DATETIME_PATTERN)
    @ApiModelProperty(value = "操作时间", position = 903)
    private Date operateDateTime;
}