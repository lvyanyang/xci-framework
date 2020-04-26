/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.model;

import lombok.Data;

import java.util.Date;

/**
 * 锁定用户
 */
@Data
public class LockUserModel {
    /**
     * 账户
     */
    private String account;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 解锁时间
     */
    private Date disLockTime;

    /**
     * 错误次数
     */
    private int count;
}