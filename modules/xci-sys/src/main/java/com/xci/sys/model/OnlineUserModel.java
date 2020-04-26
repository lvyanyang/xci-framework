/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.sys.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.xci.sys.entity.SysUser;
import com.xci.core.R;
import com.xci.core.XCI;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 在线用户
 */
@Data
public class OnlineUserModel {
    public OnlineUserModel(SysUser user) {
        this.setId(user.getId());
        this.setName(user.getName());
        this.setAccount(user.getAccount());
        this.setLoginTime(new Date());
        this.setActiveTime(new Date());
        this.setDeptId(user.getDeptId());
        this.setDeptName(user.getDeptName());
        this.setDeptId(user.getDeptId());
        this.setDeptName(user.getDeptName());
        this.setIp(XCI.getClientIP());
        this.setAdmin(user.getAdmin());
    }

    /**
     * 用户主键
     */
    @ExcelIgnore
    @ApiModelProperty(value = "用户主键")
    private Long id;

    /**
     * 用户姓名
     */
    @Excel(name = "用户姓名")
    @ApiModelProperty(value = "用户姓名")
    private String name;

    /**
     * 登陆账户
     */
    @Excel(name = "登陆账户")
    @ApiModelProperty(value = "登陆账户")
    private String account;

    /**
     * 登录时间
     */
    @Excel(name = "登录时间", width = 20d, exportFormat = R.DEFAULT_DATETIME_PATTERN)
    @ApiModelProperty(value = "登录时间")
    private Date loginTime;

    /**
     * 激活时间
     */
    @Excel(name = "激活时间", width = 20d, exportFormat = R.DEFAULT_DATETIME_PATTERN)
    @ApiModelProperty(value = "激活时间")
    private Date activeTime;

    /**
     * 机构主键
     */
    @ExcelIgnore
    @ApiModelProperty(value = "机构主键")
    private Long deptId;

    /**
     * 机构名称
     */
    @Excel(name = "机构名称")
    @ApiModelProperty(value = "机构名称")
    private String deptName;

    /**
     * 登录IP
     */
    @Excel(name = "登录IP")
    @ApiModelProperty(value = "登录IP")
    private String ip;

    /**
     * 是否超级管理员
     */
    @ExcelIgnore
    @ApiModelProperty(hidden = true)
    private Boolean admin;
}