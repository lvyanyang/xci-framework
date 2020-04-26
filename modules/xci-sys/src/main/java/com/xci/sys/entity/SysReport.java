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
 * 系统报表
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统报表")
public class SysReport extends BaseEntity {
    /**
     * 报表主键
     */
    @ExcelIgnore
    @Excel(name = "报表主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "报表主键", position = 1)
    private Long id;

    /**
     * 报表名称
     */
    @NotBlank(message = "报表名称不能为空")
    @Length(max = 100, message = "报表名称不能超过{max}个字符")
    @Excel(name = "报表名称", width = 30d)
    @ApiModelProperty(value = "报表名称", required = true, position = 2)
    private String name;

    /**
     * 报表编码
     */
    @NotBlank(message = "报表编码不能为空")
    @Length(max = 100, message = "报表编码不能超过{max}个字符")
    @Excel(name = "报表编码", width = 30d)
    @ApiModelProperty(value = "报表编码", required = true, position = 3)
    private String code;

    /**
     * 接口地址
     */
    @Length(max = 200, message = "接口地址不能超过{max}个字符")
    @Excel(name = "接口地址", width = 30d)
    @ApiModelProperty(value = "接口地址", position = 4)
    private String url;

    /**
     * 文件路径
     */
    @Length(max = 500, message = "文件路径不能超过{max}个字符")
    @Excel(name = "文件路径", width = 30d)
    @ApiModelProperty(value = "文件路径", position = 5)
    private String path;

    /**
     * 文件md5
     */
    @Length(max = 100, message = "文件md5不能超过{max}个字符")
    @Excel(name = "文件md5", width = 30d)
    @ApiModelProperty(value = "文件md5", position = 6)
    private String md5;

    /**
     * 报表类型
     */
    @NotNull(message = "报表类型不能为空")
    @ExcelIgnore
    @Excel(name = "报表类型")
    @ApiModelProperty(value = "报表类型", required = true, position = 7)
    private Integer category;

    /**
     * 状态 [true-启用, false-禁用]
     */
    @NotNull(message = "请指定状态")
    @Excel(name = "状态", replace = {R.EnabledStatusReplace, R.DisabledStatusReplace})
    @ApiModelProperty(value = "状态 [true-启用, false-禁用]", required = true, position = 8)
    private Boolean status;

    /**
     * 备注
     */
    @Length(max = 500, message = "备注不能超过{max}个字符")
    @Excel(name = "备注", width = 40d)
    @ApiModelProperty(value = "备注", position = 100)
    private String remark;
}