/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.github.lvyanyang.core.BaseEntity;
import com.github.lvyanyang.core.R;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 实体(创建人/修改人)基类
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseUserEntity extends BaseEntity {
    /**
     * 创建人主键
     */
    @ExcelIgnore @Excel(name = "创建人主键")
    @ApiModelProperty(value = "创建人主键", position = 221)
    private Long createUserId;

    /**
     * 创建人姓名
     */
    @Excel(name = "创建人姓名", width = 20d)
    @ApiModelProperty(value = "创建人姓名", position = 222)
    private String createUserName;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 20d, exportFormat = R.DEFAULT_DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间", position = 223)
    private Date createDateTime;

    /**
     * 修改人主键
     */
    @ExcelIgnore @Excel(name = "修改人主键")
    @ApiModelProperty(value = "修改人主键", position = 224)
    private Long updateUserId;

    /**
     * 修改人姓名
     */
    @Excel(name = "修改人姓名", width = 20d)
    @ApiModelProperty(value = "修改人姓名", position = 225)
    private String updateUserName;

    /**
     * 修改时间
     */
    @Excel(name = "修改时间", width = 20d, exportFormat = R.DEFAULT_DATETIME_PATTERN)
    @ApiModelProperty(value = "修改时间", position = 226)
    private Date updateDateTime;
}