/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.core;

import com.github.lvyanyang.sys.service.SysService;

/**
 * 系统参数
 */
public class Params {
    /** 是否启用操作日志 */
    public static boolean enableOperateLog() {
        return SysService.me().getParamBooleanValueByCode("server.sys.enableOperateLog", true);
    }

    /** 是否启用错误日志 */
    public static boolean enableErrorLog() {
        return SysService.me().getParamBooleanValueByCode("server.sys.enableErrorLog", true);
    }

    /** 是否启用登录日志 */
    public static boolean enableLoginLog() {
        return SysService.me().getParamBooleanValueByCode("server.sys.enableLoginLog", true);
    }

    /** 是否启用历史日志 */
    public static boolean enableHistoryLog() {
        return SysService.me().getParamBooleanValueByCode("server.sys.enableHistoryLog", true);
    }

    /** 是否启用定时任务日志 */
    public static boolean enableJobLog() {
        return SysService.me().getParamBooleanValueByCode("server.sys.enableJobLog", true);
    }

    /** 系统服务器名称 */
    public static String sysServerName() {
        return SysService.me().getParamStringValueByCode("server.api.serverName", "西交投测试服务器");
    }

    /**
     * 系统用户密码强度检测正则表达式
     */
    public static String sysUserPasswordStrongPattern() {
        return SysService.me().getParamStringValueByCode("sys.user.passwordStrongPattern", "(?=.*[0-9])(?=.*[a-zA-Z])(?=([\\x21-\\x7e]+)[^a-zA-Z0-9]).{6,}");
    }

    /**
     * 系统用户密码强度检测错误消息
     */
    public static String sysUserPasswordStrongErrorMsg() {
        return SysService.me().getParamStringValueByCode("sys.user.passwordStrongErrorMsg", "您输入的密码过于简单，不满足密码策略。");
    }

    /**
     * 系统用户需要验证码重试次数
     */
    public static int sysUserRequireCaptchaCount() {
        return SysService.me().getParamIntValueByCode("sys.user.requireCaptchaCount", 3);//系统用户需要验证码重试次数
    }

    /**
     * 系统用户锁定允许的最大重试次数
     */
    public static int sysUserLockMaxRetryCount() {
        return SysService.me().getParamIntValueByCode("sys.user.lockMaxRetryCount", 5);//系统用户锁定允许的最大重试次数
    }

    /**
     * 是否允许用户重复登录
     */
    public static boolean sysUserAllowRepeatLogin() {
        return SysService.me().getParamBooleanValueByCode("sys.user.allowRepeatLogin", false);//是否允许用户重复登录
    }

    /**
     * 密码有效天数
     */
    public static int sysUserPwdAvailableDays() {
        return SysService.me().getParamIntValueByCode("sys.user.pwdAvailableDays", 90);//密码有效天数
    }

    /**
     * 密码过期提醒天数
     */
    public static int sysUserPwdExpireRemindDays() {
        return SysService.me().getParamIntValueByCode("sys.user.pwdExpireRemindDays", 30);//密码过期提醒天数
    }

    /**
     * 机构数据权限合并策略
     */
    public static int sysDeptScopeMergeStrategy() {
        //合并策略:1.有更大的权限时优先采用更大的权限 2.有更小的权限时优先采用更小的权限
        return SysService.me().getParamIntValueByCode("sys.user.deptScopeMergeStrategy", 1);
    }

    // /**
    //  * 是否解析IP地址地理位置
    //  */
    // public static boolean sysApiResolvingIpLocation() {
    //     return SysService.me().getParamBooleanValueByCode("sys.api.resolvingIpLocation", false);
    // }
}