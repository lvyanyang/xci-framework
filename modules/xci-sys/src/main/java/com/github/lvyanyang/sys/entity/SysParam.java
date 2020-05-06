/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.lvyanyang.core.BaseEntity;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.sys.core.SysDics;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 系统参数
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统参数")
public class SysParam extends BaseEntity {
    /**
     * 参数主键
     */
    @ExcelIgnore
    @Excel(name = "参数主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "参数主键", position = 1)
    private Long id;

    /**
     * 参数名称
     */
    @NotBlank(message = "参数名称不能为空")
    @Length(max = 100, message = "参数名称长度不能超过{max}个字符")
    @Excel(name = "参数名称", width = 30d)
    @ApiModelProperty(value = "参数名称", required = true, position = 2)
    private String name;

    /**
     * 参数编码
     */
    @NotBlank(message = "参数编码不能为空")
    @Length(max = 100, message = "参数编码长度不能超过{max}个字符")
    @Excel(name = "参数编码", width = 30d)
    @ApiModelProperty(value = "参数编码", required = true, position = 3)
    private String code;

    /**
     * 参数简拼
     */
    @Length(max = 100, message = "参数简拼长度不能超过{max}个字符")
    @Excel(name = "参数简拼")
    @ApiModelProperty(value = "参数简拼", position = 4)
    private String spell;

    /**
     * 参数值
     */
    @Length(max = 2000, message = "参数值长度不能超过{max}个字符")
    @Excel(name = "参数值", width = 40d)
    @ApiModelProperty(value = "参数值", position = 5)
    private String value;

    /**
     * 参数类型
     */
    @NotNull(message = "参数类型不能为空")
    @ExcelIgnore
    @Excel(name = "参数类型值")
    @ApiModelProperty(value = "参数类型值", required = true, position = 6)
    private Integer category;

    /**
     * 参数类型名称
     */
    @Excel(name = "参数类型名称")
    @ApiModelProperty(value = "参数类型名称", position = 7)
    private String categoryName;

    public String getCategoryName() {
        return SysDics.SysParamCategory.getName(category, R.Empty);
    }

    /**
     * 备注
     */
    @Length(max = 500, message = "备注长度不能超过{max}个字符")
    @Excel(name = "备注", width = 40d)
    @ApiModelProperty(value = "备注", position = 100)
    private String remark;
}