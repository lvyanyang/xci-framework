package com.xci.sys.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xci.core.BaseEntity;
import com.xci.core.R;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 系统应用
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统应用")
public class SysApp extends BaseEntity {
    /**
     * 应用主键
     */
    @ExcelIgnore
    @Excel(name = "应用主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "应用主键", position = 1)
    private Long id;

    /**
     * 应用名称
     */
    @NotBlank(message = "应用名称不能为空")
    @Length(max = 100, message = "应用名称不能超过{max}个字符")
    @Excel(name = "应用名称", width = 30d)
    @ApiModelProperty(value = "应用名称", required = true, position = 2)
    private String name;

    /**
     * 状态 [true-启用, false-禁用]
     */
    @NotNull(message = "请指定状态")
    @Excel(name = "状态", replace = {R.EnabledStatusReplace, R.DisabledStatusReplace})
    @ApiModelProperty(value = "状态 [true-启用, false-禁用]", required = true, position = 4)
    private Boolean status;

    /**
     * 备注
     */
    @Length(max = 500, message = "备注不能超过{max}个字符")
    @Excel(name = "备注", width = 40d)
    @ApiModelProperty(value = "备注", position = 100)
    private String remark;
}