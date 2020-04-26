package com.xci.sys.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统序列
 * @author 吕艳阳
 */
@Data
@ApiModel(description = "系统序列")
public class SysSeq implements Serializable {
    /**
     * 序列主键
     */
    @ExcelIgnore
    @Excel(name = "序列主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "序列主键", position = 1)
    private Long id;

    /**
     * 序列名称
     */
    @NotBlank(message = "序列名称不能为空")
    @Length(max = 100, message = "序列名称不能超过{max}个字符")
    @Excel(name = "序列名称", width = 30d)
    @ApiModelProperty(value = "序列名称", required = true, position = 2)
    private String name;

    /**
     * 序列编码
     */
    @NotBlank(message = "序列编码不能为空")
    @Length(max = 100, message = "序列编码不能超过{max}个字符")
    @Excel(name = "序列编码", width = 30d)
    @ApiModelProperty(value = "序列编码", required = true, position = 3)
    private String code;

    /**
     * 开始值
     */
    @NotNull(message = "开始值不能为空")
    @Excel(name = "开始值")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "开始值", required = true, position = 4)
    private Long startWith;

    /**
     * 当前值
     */
    @Excel(name = "当前值")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "当前值", required = true, position = 5)
    private Long currentValue;

    /**
     * 步长
     */
    @NotNull(message = "步长不能为空")
    @Excel(name = "步长")
    @ApiModelProperty(value = "步长", required = true, position = 6)
    private Integer incrementBy;

    /**
     * 备注
     */
    @Length(max = 500, message = "备注不能超过{max}个字符")
    @Excel(name = "备注", width = 40d)
    @ApiModelProperty(value = "备注", position = 100)
    private String remark;
}