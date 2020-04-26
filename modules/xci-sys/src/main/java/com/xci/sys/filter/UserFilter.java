package com.xci.sys.filter;

import com.xci.core.BasePageFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统用户过滤条件
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "系统用户过滤条件")
public class UserFilter extends BasePageFilter {
    /**
     * 账号/姓名/简拼模糊查询关键字
     */
    @ApiModelProperty(value = "账号/姓名/简拼模糊查询关键字")
    private String name;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    /**
     * 电子邮件
     */
    @ApiModelProperty(value = "电子邮件")
    private String email;

    /**
     * 机构主键,默认只查一级机构,如需查所有下级请指定deptAllLower参数为1
     */
    @ApiModelProperty(value = "机构主键,默认只查一级机构,如需查所有下级请指定deptAllLower参数为1")
    private Long deptId;

    /**
     * 查询机构所有下级 [true-启用, false-禁用]
     */
    @ApiModelProperty(value = "查询机构所有下级 [true-启用, false-禁用]")
    private Boolean deptAllLower;

    /**
     * 显示状态 [true-显示, false-不显示]
     */
    @ApiModelProperty(value = "显示状态 [true-显示, false-不显示]")
    private Boolean visible;

    /**
     * 状态 [true-启用, false-禁用]
     */
    @ApiModelProperty(value = "状态 [true-启用, false-禁用]")
    private Boolean status;

    /**
     * 账号类型 [1-企业账号, 0-系统账号]
     */
    @ApiModelProperty(value = "账号类型 [1-企业账号, 0-系统账号]")
    private Integer category;

    /**
     * 企业主键
     */
    @ApiModelProperty(value = "企业主键")
    private String entId;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String entName;

    /**
     * 是否启用数据权限过滤 [true-启用, false-禁用]
     */
    @ApiModelProperty(value = "是否启用数据权限过滤 [true-启用, false-禁用]")
    private Boolean dataScope;
}