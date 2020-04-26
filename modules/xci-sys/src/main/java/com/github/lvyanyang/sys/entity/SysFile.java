/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.lvyanyang.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 系统文件
 * @author 吕艳阳
 */
@Data @EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统文件")
public class SysFile extends BaseEntity {
    /**
     * 文件主键
     */
    @ExcelIgnore
    @Excel(name = "文件主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "应用主键", position = 1)
    private Long id;

    /**
     * 记录主键
     */
    @NotNull(message = "记录主键不能为空")
    @Excel(name = "记录主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "记录主键", required = true, position = 2)
    private Long recordId;

    /**
     * 文件分组
     */
    @Length(max = 50, message = "文件分组长度不能超过{max}")
    @Excel(name = "文件分组")
    @ApiModelProperty(value = "文件分组", position = 3)
    private String category;

    /**
     * 文件名称
     */
    @NotBlank(message = "文件名称不能为空")
    @Length(max = 100, message = "文件名称不能超过{max}个字符")
    @Excel(name = "文件名称", width = 30d)
    @ApiModelProperty(value = "文件名称", required = true, position = 4)
    private String fileName;

    /**
     * 文件路径
     */
    @Length(max = 100, message = "文件路径不能超过{max}个字符")
    @Excel(name = "文件路径")
    @ApiModelProperty(value = "文件路径", position = 5)
    private String filePath;

    /**
     * 文件大小
     */
    @NotNull(message = "文件大小不能为空")
    @Excel(name = "文件大小")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "文件大小", required = true, position = 6)
    private Long fileSize;

    /**
     * 文件类型
     */
    @Length(max = 100, message = "文件类型不能超过{max}个字符")
    @Excel(name = "文件类型")
    @ApiModelProperty(value = "文件类型", position = 7)
    private String contentType;
}