package com.xci.sys.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xci.core.BaseEntity;
import com.xci.core.ITreeModel;
import com.xci.core.R;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 系统模块
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统模块")
public class SysModule extends BaseEntity implements ITreeModel {
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
    @Excel(name = "模块参数")
    @ApiModelProperty(value = "模块参数", position = 6)
    private String param;

    /**
     * Web路径
     */
    @Length(max = 500, message = "Web路径不能超过{max}个字符")
    @Excel(name = "Web路径")
    @ApiModelProperty(value = "Web路径", position = 6)
    private String webUrl;

    /**
     * Web图标
     */
    @Length(max = 500, message = "Web图标不能超过{max}个字符")
    @Excel(name = "Web图标")
    @ApiModelProperty(value = "Web图标", position = 6)
    private String webCls;

    /**
     * Win路径
     */
    @Length(max = 500, message = "Win路径不能超过{max}个字符")
    @Excel(name = "Win路径")
    @ApiModelProperty(value = "Win路径", position = 6)
    private String winUrl;

    /**
     * Win图标
     */
    @Length(max = 500, message = "Win图标不能超过{max}个字符")
    @Excel(name = "Win图标")
    @ApiModelProperty(value = "Win图标", position = 6)
    private String winCls;

    /**
     * Web配置选项
     */
    @Length(max = 2000, message = "Web配置选项不能超过{max}个字符")
    @Excel(name = "Web配置选项")
    @ApiModelProperty(value = "Web配置选项", position = 7)
    private String webSetting;

    /**
     * Win配置选项
     */
    @Length(max = 2000, message = "Win配置选项不能超过{max}个字符")
    @Excel(name = "WinFrom窗口类")
    @ApiModelProperty(value = "Win配置选项", position = 9)
    private String winSetting;

    /**
     * 是否Web菜单 [true-是, false-否]
     */
    @NotNull(message = "请输入是否Web菜单")
    @Excel(name = "是否Web菜单", width = 20d, replace = {R.YesStatusReplace, R.NoStatusReplace})
    @ApiModelProperty(value = "是否Web菜单 [true-是, false-否]", required = true, position = 12)
    private Boolean web;

    /**
     * 是否WinForm菜单 [true-是, false-否]
     */
    @NotNull(message = "请输入是否WinForm菜单")
    @Excel(name = "是否WinForm菜单", width = 20d, replace = {R.YesStatusReplace, R.NoStatusReplace})
    @ApiModelProperty(value = "是否WinForm菜单 [true-是, false-否]", required = true, position = 13)
    private Boolean win;

    /**
     * 是否展开 [true-是, false-否]
     */
    @NotNull(message = "请输入是否展开")
    @Excel(name = "是否展开", width = 20d, replace = {R.YesStatusReplace, R.NoStatusReplace})
    @ApiModelProperty(value = "是否展开 [是_1,否_0]", required = true, position = 14)
    private Boolean expand;

    /**
     * 是否公开 [true-是, false-否]
     */
    @NotNull(message = "请输入是否公开")
    @Excel(name = "是否公开", width = 20d, replace = {R.YesStatusReplace, R.NoStatusReplace})
    @ApiModelProperty(value = "是否公开 [true-是, false-否]", required = true, position = 15)
    private Boolean publiced;

    /**
     * 是否菜单 [true-是, false-否]
     */
    @NotNull(message = "请输入是否菜单")
    @Excel(name = "是否菜单", width = 20d, replace = {R.YesStatusReplace, R.NoStatusReplace})
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
    @Excel(name = "备注", width = 40d)
    @ApiModelProperty(value = "备注", position = 100)
    private String remark;
}