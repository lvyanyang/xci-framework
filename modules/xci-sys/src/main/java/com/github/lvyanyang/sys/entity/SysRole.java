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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 系统角色
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统角色")
public class SysRole extends BaseEntity {
    /**
     * 角色主键
     */
    @ExcelIgnore
    @Excel(name = "角色主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "角色主键", position = 1)
    private Long id;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @Length(max = 100, message = "角色名称不能超过{max}个字符")
    @Excel(name = "角色名称", width = 30d)
    @ApiModelProperty(value = "角色名称", required = true, position = 2)
    private String name;

    /**
     * 角色编码
     */
    @NotBlank(message = "角色编码不能为空")
    @Length(max = 100, message = "角色编码不能超过{max}个字符")
    @Excel(name = "角色编码", width = 30d)
    @ApiModelProperty(value = "角色编码", required = true, position = 3)
    private String code;

    /**
     * 角色简拼
     */
    @Length(max = 100, message = "名称简拼不能超过{max}个字符")
    @ExcelIgnore
    @Excel(name = "角色简拼")
    @ApiModelProperty(value = "角色简拼", position = 4)
    private String spell;

    /**
     * 机构主键
     */
    @NotNull(message = "请指定机构主键")
    @ExcelIgnore
    @Excel(name = "机构主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "机构主键", required = true, position = 5)
    private Long deptId;

    /**
     * 机构名称
     */
    @Excel(name = "机构名称", width = 30d)
    @ApiModelProperty(value = "机构名称", required = true, position = 6)
    private String deptName;

    /**
     * 机构权限 [1-全部, 2-自定义, 3-所在部门, 4-所在部门及所有下级, 5-仅本人]
     */
    @Excel(name = "机构权限", replace = {"全部_1", "自定义_2", "所在部门_3", "所在部门及所有下级_4", "仅本人_5"})
    @ApiModelProperty(value = "机构权限 [1-全部, 2-自定义, 3-所在部门, 4-所在部门及所有下级, 5-仅本人]", required = true, position = 6)
    private Integer deptScope;

    /**
     * 机构数据权限名称
     */
    @ApiModelProperty(value = "机构数据权限名称")
    public String getDeptScopeName() {
        switch (deptScope) {
            case 1:
                return "全部";
            case 2:
                return "自定义";
            case 3:
                return "所在部门";
            case 4:
                return "所在部门及所有下级";
            case 5:
                return "仅本人";
        }
        return R.Empty;
    }

    /**
     * 排序路径
     */
    @Excel(name = "排序")
    @ApiModelProperty(value = "排序", position = 8)
    private Integer path;

    /**
     * 状态 [true-启用, false-禁用]
     */
    @NotNull(message = "请指定状态")
    @Excel(name = "状态", replace = {R.EnabledStatusReplace, R.DisabledStatusReplace})
    @ApiModelProperty(value = "状态 [true-启用, false-禁用]", required = true, position = 9)
    private Boolean status;

    /**
     * 备注
     */
    @Length(max = 500, message = "备注不能超过{max}个字符")
    @Excel(name = "备注")
    @ApiModelProperty(value = "备注", position = 100)
    private String remark;
}