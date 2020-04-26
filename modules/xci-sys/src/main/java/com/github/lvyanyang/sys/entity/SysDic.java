/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.lvyanyang.core.BaseEntity;
import com.github.lvyanyang.core.ITreeModel;
import com.github.lvyanyang.core.R;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 系统字典
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统字典")
public class SysDic extends BaseEntity implements ITreeModel {
    /**
     * 字典主键
     */
    @ExcelIgnore
    @Excel(name = "字典主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "字典主键", position = 1)
    private Long id;

    /**
     * 字典上级主键
     */
    @ExcelIgnore
    @Excel(name = "字典上级主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "字典上级主键", position = 2)
    private Long parentId;

    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空")
    @Length(max = 100, message = "字典名称不能超过{max}个字符")
    @Excel(name = "字典名称", width = 30d)
    @ApiModelProperty(value = "字典名称", required = true, position = 4)
    private String name;

    /**
     * 字典简拼
     */
    @Length(max = 100, message = "字典简拼不能超过{max}个字符")
    @ExcelIgnore@Excel(name = "字典简拼")
    @ApiModelProperty(value = "字典简拼", position = 6)
    private String spell;

    /**
     * 字典类型编码
     */
    @NotBlank(message = "字典类型编码不能为空")
    @Length(max = 100, message = "字典类型编码不能超过{max}个字符")
    @Excel(name = "字典类型编码", width = 30d)
    @ApiModelProperty(value = "字典类型编码", required = true, position = 3)
    private String categoryCode;

    /**
     * 字典值
     */
    @NotNull(message = "字典值不能为空")
    @Length(max = 500, message = "字典值不能超过{max}个字符")
    @Excel(name = "字典值", width = 30d)
    @ApiModelProperty(value = "字典值", required = true, position = 5)
    private String value;

    /**
     * 排序路径
     */
    @Excel(name = "排序路径")
    @ApiModelProperty(value = "排序路径", position = 7)
    private Integer path;

    /**
     * 状态 [true-启用, false-禁用]
     */
    @Excel(name = "状态", replace = {R.EnabledStatusReplace, R.DisabledStatusReplace})
    @ApiModelProperty(value = "状态 [true-启用, false-禁用]", position = 8)
    private Boolean status;

    /**
     * 备注
     */
    @Length(max = 500, message = "备注不能超过{max}个字符")
    @Excel(name = "备注", width = 40d)
    @ApiModelProperty(value = "备注", position = 100)
    private String remark;
}