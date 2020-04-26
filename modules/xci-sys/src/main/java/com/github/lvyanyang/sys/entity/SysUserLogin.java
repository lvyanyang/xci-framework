/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 系统用户(登录)
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统用户(登录)")
public class SysUserLogin extends SysUser {
    /**
     * 用户登录凭据
     */
    @ApiModelProperty(value = "用户登录凭据", position = 200)
    private String token;

    /**
     * 用户拥有的模块
     */
    @ApiModelProperty(value = "用户拥有的模块", position = 200)
    private List<SysModule> modules;

    /**
     * 从系统用户中创建
     * @param sysUser 用户对象
     */
    public SysUserLogin from(SysUser sysUser) {
        BeanUtils.copyProperties(sysUser, this);
        return this;
    }
}