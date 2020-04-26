package com.xci.sys.web.model;

import lombok.Data;

/**
 * 登录页面模型
 */
@Data
public class LoginModel {

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String captcha;

    /**
     * 自动登录
     */
    private boolean autoLogin;

    /**
     * 客户端唯一ID
     */
    private String uuid;
}
