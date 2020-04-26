/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录对象
 */
@Data
@ApiModel(description = "用户登录对象")
public class LoginBody {
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", required = true, position = 1)
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true, position = 2)
    private String password;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码", position = 3)
    private String captcha;
}
