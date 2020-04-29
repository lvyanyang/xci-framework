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
import java.io.Serializable;

/**
 * 系统模块
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统模块")
public class SysModule extends BaseEntity implements ITreeModel, Serializable {
    /**
     * 模块主键
     */
    @ExcelIgnore
    @Excel(name = "模块主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "模块主键", position = 1)
    private Long id;

    /**
     * 模块上级主键
     */
    @ExcelIgnore
    @Excel(name = "模块类型上级主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "模块上级主键", position = 2)
    private Long parentId;

    /**
     * 模块名称
     */
    @NotBlank(message = "模块名称不能为空")
    @Length(max = 100, message = "模块名称不能超过{max}个字符")
    @Excel(name = "模块名称", width = 30d)
    @ApiModelProperty(value = "模块名称", required = true, position = 3)
    private String name;

    /**
     * 模块编码
     */
    @NotBlank(message = "模块编码不能为空")
    @Length(max = 100, message = "模块编码不能超过{max}个字符")
    @Excel(name = "模块编码", width = 30d)
    @ApiModelProperty(value = "模块编码", required = true, position = 4)
    private String code;

    /**
     * 模块简拼
     */
    @Length(max = 100, message = "模块简拼不能超过{max}个字符")
    @ExcelIgnore@Excel(name = "简拼")
    @ApiModelProperty(value = "模块简拼", position = 5)
    private String spell;

    /**
     * 模块参数
     */
    @Length(max = 4000, message = "模块参数不能超过{max}个字符")
    @Excel(name = "模块参数", width = 30d)
    @ApiModelProperty(value = "模块参数", position = 6)
    private String param;

    /**
     * Web图标
     */
    @Length(max = 500, message = "Web图标不能超过{max}个字符")
    @Excel(name = "Web图标",width = 20d)
    @ApiModelProperty(value = "Web图标", position = 6)
    private String webCls;

    /**
     * Web路径
     */
    @Length(max = 500, message = "Web路径不能超过{max}个字符")
    @Excel(name = "Web路径", width = 30d)
    @ApiModelProperty(value = "Web路径", position = 6)
    private String webUrl;

    /**
     * Web配置
     */
    @Length(max = 2000, message = "Web配置不能超过{max}个字符")
    @Excel(name = "Web配置",width = 20d)
    @ApiModelProperty(value = "Web配置", position = 7)
    private String webSetting;

    /**
     * Win图标
     */
    @Length(max = 500, message = "Win图标不能超过{max}个字符")
    @Excel(name = "Win图标",width = 20d)
    @ApiModelProperty(value = "Win图标", position = 6)
    private String winCls;

    /**
     * Win路径
     */
    @Length(max = 500, message = "Win路径不能超过{max}个字符")
    @Excel(name = "Win路径", width = 30d)
    @ApiModelProperty(value = "Win路径", position = 6)
    private String winUrl;

    /**
     * Win配置
     */
    @Length(max = 2000, message = "Win配置不能超过{max}个字符")
    @Excel(name = "Win配置",width = 20d)
    @ApiModelProperty(value = "Win配置", position = 9)
    private String winSetting;

    /**
     * 是否Web菜单 [true-是, false-否]
     */
    @NotNull(message = "请输入是否Web菜单")
    @Excel(name = "Web菜单", replace = {R.YesStatusReplace, R.NoStatusReplace})
    @ApiModelProperty(value = "是否Web菜单 [true-是, false-否]", required = true, position = 12)
    private Boolean web;

    /**
     * 是否WinForm菜单 [true-是, false-否]
     */
    @NotNull(message = "请输入是否WinForm菜单")
    @Excel(name = "Win菜单", replace = {R.YesStatusReplace, R.NoStatusReplace})
    @ApiModelProperty(value = "是否WinForm菜单 [true-是, false-否]", required = true, position = 13)
    private Boolean win;

    /**
     * 是否展开 [true-是, false-否]
     */
    @NotNull(message = "请输入是否展开")
    @Excel(name = "展开", replace = {R.YesStatusReplace, R.NoStatusReplace})
    @ApiModelProperty(value = "是否展开 [是_1,否_0]", required = true, position = 14)
    private Boolean expand;

    /**
     * 是否公开 [true-是, false-否]
     */
    @NotNull(message = "请输入是否公开")
    @Excel(name = "公开", replace = {R.YesStatusReplace, R.NoStatusReplace})
    @ApiModelProperty(value = "是否公开 [true-是, false-否]", required = true, position = 15)
    private Boolean publiced;

    /**
     * 是否菜单 [true-是, false-否]
     */
    @NotNull(message = "请输入是否菜单")
    @Excel(name = "菜单", replace = {R.YesStatusReplace, R.NoStatusReplace})
    @ApiModelProperty(value = "是否菜单 [true-是, false-否]", required = true, position = 16)
    private Boolean menu;

    /**
     * 排序路径
     */
    @Excel(name = "排序")
    @ApiModelProperty(value = "排序路径", position = 8)
    private Integer path;

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
    @Excel(name = "备注")
    @ApiModelProperty(value = "备注", position = 100)
    private String remark;
}