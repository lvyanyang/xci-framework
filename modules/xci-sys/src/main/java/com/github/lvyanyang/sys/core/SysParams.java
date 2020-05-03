/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.core;

import com.github.lvyanyang.core.ParamObject;

/**
 * 系统参数常量
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-05-04 02:36
 */
public class SysParams {
    /** 是否启用系统操作日志 */
    public static final ParamObject SysLogEnableOperateLog = new ParamObject("sys.log.enableOperateLog", true);

    /** 是否启用错误日志 */
    public static final ParamObject SysLogEnableErrorLog = new ParamObject("sys.log.enableErrorLog", true);

    /** 是否启用登录日志 */
    public static final ParamObject SysLogEnableLoginLog = new ParamObject("sys.log.enableLoginLog", true);

    /** 是否启用历史日志 */
    public static final ParamObject SysLogEnableHistoryLog = new ParamObject("sys.log.enableHistoryLog", true);

    /** 是否启用定时任务日志 */
    public static final ParamObject SysLogEnableJobLog = new ParamObject("sys.log.enableJobLog", true);

    /**
     * 系统用户密码强度检测正则表达式
     */
    public static final ParamObject SysUserPasswordStrongPattern = new ParamObject("sys.user.passwordStrongPattern", "(?=.*[0-9])(?=.*[a-zA-Z])(?=([\\x21-\\x7e]+)[^a-zA-Z0-9]).{6,}");

    /**
     * 系统用户密码强度检测错误消息
     */
    public static final ParamObject SysUserPasswordStrongErrorMsg = new ParamObject("sys.user.passwordStrongErrorMsg", "您输入的密码过于简单，不满足密码策略。");

    /**
     * 系统用户需要验证码重试次数
     */
    public static final ParamObject SysUserRequireCaptchaCount = new ParamObject("sys.user.requireCaptchaCount", 4);//系统用户需要验证码重试次数

    /**
     * 系统用户锁定允许的最大重试次数
     */
    public static final ParamObject SysUserLockMaxRetryCount = new ParamObject("sys.user.lockMaxRetryCount", 6);//系统用户锁定允许的最大重试次数

    /**
     * 是否允许用户重复登录
     */
    public static final ParamObject SysUserAllowRepeatLogin = new ParamObject("sys.user.allowRepeatLogin", true);//是否允许用户重复登录

    /**
     * 密码有效天数
     */
    public static final ParamObject SysUserPwdAvailableDays = new ParamObject("sys.user.pwdAvailableDays", 90);//密码有效天数

    /**
     * 密码过期提醒天数
     */
    public static final ParamObject SysUserPwdExpireRemindDays = new ParamObject("sys.user.pwdExpireRemindDays", 10);//密码过期提醒天数

    /**
     * 机构数据权限合并策略
     * 1.有更大的权限时优先采用更大的权限
     * 2.有更小的权限时优先采用更小的权限
     */
    public static final ParamObject SysDeptScopeMergeStrategy = new ParamObject("sys.dept.scopeMergeStrategy", 1);

    /** 系统接口服务器名称 */
    public static final ParamObject SysApiServerName = new ParamObject("sys.api.serverName", "西交投测试服务器");

    /** Web页面显示大字体样式 */
    public static final ParamObject SysWebLargeFont = new ParamObject("sys.web.largeFont", false);

    /** Web页面标题 */
    public static final ParamObject SysWebTitle = new ParamObject("sys.web.title", "西交投信息系统开发平台");

    /** Web页面标题颜色 */
    public static final ParamObject SysWebTitleColor = new ParamObject("sys.web.titleColor", "white");

    /** Web页面版权 */
    public static final ParamObject SysWebCopyright = new ParamObject("sys.web.copyright", "西安交通信息投资营运有限公司 版权所有");

    /** Web页面版本标题 */
    public static final ParamObject SysWebVersionTitle = new ParamObject("sys.web.versionTitle", "测试版");

    /** Web页面版本标题颜色 */
    public static final ParamObject SysWebVersionTitleColor = new ParamObject("sys.web.versionTitleColor", "yellow");


}