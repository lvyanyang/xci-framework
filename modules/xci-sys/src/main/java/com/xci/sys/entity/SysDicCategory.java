package com.xci.sys.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xci.core.BaseEntity;
import com.xci.core.ITreeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 系统字典类型
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统字典类型")
public class SysDicCategory extends BaseEntity implements ITreeModel {
    /**
     * 字典类型主键
     */
    @ExcelIgnore
    @Excel(name = "字典类型主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "字典类型主键", position = 1)
    private Long id;

    /**
     * 字典类型上级主键
     */
    @ExcelIgnore
    @Excel(name = "字典类型上级主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "字典类型上级主键", position = 2)
    private Long parentId;

    /**
     * 字典类型名称
     */
    @NotBlank(message = "字典类型名称不能为空")
    @Length(max = 100, message = "字典类型名称不能超过{max}个字符")
    @Excel(name = "字典类型名称", width = 30d)
    @ApiModelProperty(value = "字典类型名称", required = true, position = 4)
    private String name;

    /**
     * 字典类型简拼
     */
    @Length(max = 100, message = "字典类型简拼不能超过{max}个字符")
    @Excel(name = "字典类型简拼")
    @ApiModelProperty(value = "字典类型简拼", position = 5)
    private String spell;

    /**
     * 字典类型编码
     */
    @NotBlank(message = "字典类型编码不能为空")
    @Length(max = 100, message = "字典类型编码不能超过{max}个字符")
    @Excel(name = "字典类型编码", width = 30d)
    @ApiModelProperty(value = "字典类型编码", required = true, position = 6)
    private String code;

    /**
     * 排序路径
     */
    @Excel(name = "排序路径")
    @ApiModelProperty(value = "排序路径", position = 7)
    private Integer path;

    /**
     * 备注
     */
    @Length(max = 500, message = "备注不能超过{max}个字符")
    @Excel(name = "备注", width = 40d)
    @ApiModelProperty(value = "备注", position = 100)
    private String remark;
}