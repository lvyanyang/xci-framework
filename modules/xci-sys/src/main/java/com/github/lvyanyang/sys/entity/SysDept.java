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
 * 系统机构
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统机构")
public class SysDept extends BaseEntity implements ITreeModel {
    /**
     * 机构主键
     */
    @ExcelIgnore
    @Excel(name = "机构主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "机构主键", position = 1)
    private Long id;

    /**
     * 机构上级主键
     */
    @ExcelIgnore
    @Excel(name = "机构上级主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "机构上级主键", position = 2)
    private Long parentId;

    /**
     * 机构名称
     */
    @NotBlank(message = "机构名称不能为空")
    @Length(max = 100, message = "机构名称不能超过{max}个字符")
    @Excel(name = "机构名称", width = 30d)
    @ApiModelProperty(value = "机构名称", required = true, position = 4)
    private String name;

    /**
     * 机构简拼
     */
    @Length(max = 100, message = "机构简拼不能超过{max}个字符")
    @Excel(name = "机构简拼")
    @ApiModelProperty(value = "机构简拼", position = 5)
    private String spell;

    /**
     * 机构编码
     */
    @NotBlank(message = "机构编码不能为空")
    @Length(max = 100, message = "机构编码不能超过{max}个字符")
    @Excel(name = "机构编码", width = 30d)
    @ApiModelProperty(value = "机构编码", required = true, position = 6)
    private String code;

    /**
     * 机构类型
     */
    @Length(max = 100, message = "机构类型长度不能超过{max}")
    @Excel(name = "机构类型", width = 20d)
    @ApiModelProperty(value = "机构类型", position = 7)
    private String category;

    /**
     * 机构性质
     */
    @Length(max = 100, message = "机构性质长度不能超过{max}")
    @Excel(name = "机构性质", width = 20d)
    @ApiModelProperty(value = "机构性质", position = 8)
    private String nature;

    /**
     * 机构负责人主键
     */
    @ExcelIgnore
    @Excel(name = "机构负责人主键", width = 20d)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "机构负责人主键", position = 9)
    private Long leaderId;

    /**
     * 机构负责人姓名
     */
    @Excel(name = "机构负责人姓名", width = 20d)
    @ApiModelProperty(value = "机构负责人姓名", position = 10)
    private String leaderName;

    /**
     * 排序路径
     */
    @Excel(name = "排序路径")
    @ApiModelProperty(value = "排序路径", position = 11)
    private Integer path;

    /**
     * 状态 [true-启用, false-禁用]
     */
    @NotNull(message = "请指定状态")
    @Excel(name = "状态", replace = {R.EnabledStatusReplace, R.DisabledStatusReplace})
    @ApiModelProperty(value = "状态 [true-启用, false-禁用]", required = true, position = 12)
    private Boolean status;

    /**
     * 备注
     */
    @Length(max = 500, message = "备注不能超过{max}个字符")
    @Excel(name = "备注", width = 40d)
    @ApiModelProperty(value = "备注", position = 100)
    private String remark;
}