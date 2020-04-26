/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统用户(安全)
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统用户(安全)")
public class SysUserSecurity extends SysUser {
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", position = 7)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pwd;

    /**
     * 密码盐
     */
    @ApiModelProperty(value = "密码盐", position = 8)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pwdSalt;
}