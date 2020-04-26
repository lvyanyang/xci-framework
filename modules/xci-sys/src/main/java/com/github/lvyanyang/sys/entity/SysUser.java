/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.lvyanyang.core.BaseEntity;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.core.R;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 系统用户
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统用户")
public class SysUser extends BaseEntity {
    /**
     * 用户主键
     */
    @ExcelIgnore
    @Excel(name = "用户主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "用户主键", position = 1)
    private Long id;

    /**
     * 用户姓名
     */
    @NotBlank(message = "用户姓名不能为空")
    @Length(max = 100, message = "用户姓名不能超过{max}个字符")
    @Excel(name = "用户姓名", width = 30d)
    @ApiModelProperty(value = "用户姓名", position = 2)
    private String name;

    /**
     * 用户简拼
     */
    @Length(max = 100, message = "简拼不能超过{max}个字符")
    @ExcelIgnore
    @Excel(name = "用户简拼")
    @ApiModelProperty(value = "用户简拼", position = 3)
    private String spell;

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    @Length(max = 100, message = "账号不能超过{max}个字符")
    @Excel(name = "账号")
    @ApiModelProperty(value = "账号", position = 4)
    private String account;

    /**
     * 账号类型 [1-企业账号, 0-系统账号]
     */
    @NotNull(message = "请输入账号类型")
    @ExcelIgnore @Excel(name = "账号类型", replace = {"企业_1", "系统_0"})
    @ApiModelProperty(value = "账号类型 [1-企业账号, 0-系统账号]", position = 21)
    private Integer category;

    /**
     * 机构主键
     */
    @NotNull(message = "请选择机构")
    @ExcelIgnore
    @Excel(name = "机构主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "机构主键", position = 5)
    private Long deptId;

    /**
     * 机构名称
     */
    @Excel(name = "机构名称")
    @ApiModelProperty(value = "机构名称", position = 6)
    private String deptName;

    /**
     * 必须修改密码 [true-是, false-否]
     */
    @NotNull(message = "请指定必须修改密码字段")
    @ExcelIgnore
    @ApiModelProperty(value = "必须修改密码 [true-是, false-否]", position = 9)
    private Boolean pwdMustModify;

    /**
     * 允许修改密码 [true-是, false-否]
     */
    @NotNull(message = "请指定允许修改密码字段")
    @ExcelIgnore
    @ApiModelProperty(value = "允许修改密码 [true-是, false-否]", required = true, position = 10)
    private Boolean pwdAllowModify;

    /**
     * 密码永不过期 [true-是, false-否]
     */
    @NotNull(message = "请指定密码永不过期字段")
    @ExcelIgnore
    @ApiModelProperty(value = "密码永不过期 [true-是, false-否]", required = true, position = 10)
    private Boolean pwdNeverExpire;

    /**
     * 密码过期时间
     */
    @Excel(name = "密码过期时间", width = 20d, exportFormat = R.DEFAULT_DATETIME_PATTERN)
    @ApiModelProperty(value = "密码过期时间", position = 11)
    private Date pwdExpireTime;

    /**
     * 有效期开始时间
     */
    @Excel(name = "有效期开始时间", exportFormat = R.DEFAULT_DATETIME_PATTERN)
    @ApiModelProperty(value = "有效期开始时间", position = 12)
    private Date allowStartTime;

    /**
     * 有效期结束时间
     */
    @Excel(name = "有效期结束时间", exportFormat = R.DEFAULT_DATETIME_PATTERN)
    @ApiModelProperty(value = "有效期结束时间", position = 13)
    private Date allowEndTime;

    /**
     * 是否超管 [true-是, false-否]
     */
    @NotNull(message = "请输入是否超管")
    @ExcelIgnore @Excel(name = "是否超管", width = 20d, replace = {R.YesStatusReplace, R.NoStatusReplace})
    @ApiModelProperty(value = "是否超管 [true-是, false-否]", position = 14)
    private Boolean admin;

    /**
     * 手机号码
     */
    @Length(max = 50, message = "手机号码不能超过{max}个字符")
    @Excel(name = "手机号码")
    @ApiModelProperty(value = "手机号码", position = 15)
    private String mobile;

    /**
     * 电子邮件
     */
    @Length(max = 100, message = "电子邮件不能超过{max}个字符")
    @Excel(name = "电子邮件")
    @ApiModelProperty(value = "电子邮件", position = 16)
    private String email;

    /**
     * 职位
     */
    @Length(max = 100, message = "职位长度不能超过{max}")
    @Excel(name = "职位", width = 20d)
    @ApiModelProperty(value = "职位", position = 17)
    private String post;

    /**
     * 登录次数
     */
    @Excel(name = "登录次数")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "登录次数", position = 18)
    private Long loginTimes;

    /**
     * 首次登录时间
     */
    @Excel(name = "首次登录时间", exportFormat = R.DEFAULT_DATETIME_PATTERN)
    @ApiModelProperty(value = "首次登录时间", position = 19)
    private Date firstVisitTime;

    /**
     * 最后登录时间
     */
    @Excel(name = "最后登录时间", exportFormat = R.DEFAULT_DATETIME_PATTERN)
    @ApiModelProperty(value = "最后登录时间", position = 20)
    private Date lastVisitTime;

    /**
     * 企业主键
     */
    @Length(max = 100, message = "企业主键长度不能超过{max}")
    @ExcelIgnore @Excel(name = "企业主键")
    @ApiModelProperty(value = "企业主键", position = 22)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String entId;

    /**
     * 企业名称
     */
    @Length(max = 100, message = "企业名称长度不能超过{max}")
    @Excel(name = "企业名称")
    @ApiModelProperty(value = "企业名称", position = 23)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String entName;

    /**
     * 是否过期
     */
    @ApiModelProperty(value = "是否过期", position = 24)
    public Boolean getExpire() {
        return XCI.isExpired(new Date(), allowStartTime, allowEndTime);
    }

    /**
     * 显示状态 [true-显示, false-不显示]
     */
    @NotNull(message = "请输入显示状态")
    @Excel(name = "显示状态", width = 20d, replace = {"显示_true", "不显示_false"})
    @ApiModelProperty(value = "显示状态 [true-显示, false-不显示]", position = 25)
    private Boolean visible;

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
    @Excel(name = "备注", width = 40d)
    @ApiModelProperty(value = "备注", position = 100)
    private String remark;
}