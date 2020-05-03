/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.service;

import cn.hutool.core.date.DateUtil;
import com.github.lvyanyang.annotation.OperateLog;
import com.github.lvyanyang.core.BaseService;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.core.SysParams;
import com.github.lvyanyang.sys.dao.AccountDao;
import com.github.lvyanyang.sys.entity.SysUser;
import com.github.lvyanyang.sys.entity.SysUserLogin;
import com.github.lvyanyang.sys.entity.SysUserSecurity;
import com.github.lvyanyang.sys.model.LockUserModel;
import com.github.lvyanyang.sys.model.OnlineUserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 系统账户服务
 * @author 吕艳阳
 */
@Slf4j
@Service
public class AccountService extends BaseService {
    /** 用户验证码缓存 */
    @Resource private Cache captchaCache;
    /** 系统账户数据数据访问对象 */
    @Resource private AccountDao accountDao;

    /**
     * 根据主键查询单个用户安全对象,包含密码敏感信息
     * @param id 用户主键
     * @return 返回用户安全对象
     */
    public SysUserSecurity selectSecurityById(@NotNull(message = "请指定用户主键") Long id) {
        return accountDao.selectSecurityById(id);
    }

    /**
     * 根据账号查询单个用户安全对象,包含密码敏感信息
     * @param account 用户账号
     * @return 返回用户安全对象
     */
    public SysUserSecurity selectSecurityByAccount(@NotBlank(message = "请指定用户账号") String account) {
        return accountDao.selectSecurityByAccount(account);
    }

    /**
     * 用户登录
     * @param account  账号
     * @param password 密码
     * @param captcha  验证码
     * @return 登录成功返回true
     */
    public RestResult<SysUserLogin> login(@NotBlank(message = "请输入账号") String account, @NotBlank(message = "请输入密码") String password, String captcha) {
        var lockUserService = SysService.me().lockUserService();
        var onlineUserService = SysService.me().onlineUserService();
        if (lockUserService.isLock(account)) {
            LockUserModel lockUserModel = lockUserService.get(account);
            return RestResult.fail(XCI.format("账号密码输入错误次数达到上限,暂时被锁定,将于 {} 解锁", XCI.formatDateHasMinute(lockUserModel.getDisLockTime())));
        }

        if (lockUserService.requireCaptcha(account)) {
            String code = captchaCache.get(account, String.class);
            if ((XCI.isBlank(captcha)) || (XCI.isBlank(code)) || (!code.equalsIgnoreCase(captcha))) {
                captchaCache.evict(account);
                return RestResult.fail("输入的验证码无效");
            }
        }
        captchaCache.evict(account);

        RestResult<SysUser> result = valid(account, password);
        if (result.isOk()) {
            lockUserService.remove(account);
            SysUser user = result.getData();

            OnlineUserModel onlineUser = onlineUserService.get(user.getId());
            if (!SysParams.SysUserAllowRepeatLogin.getBoolean() && onlineUser != null) {
                var msg = XCI.format("用户在 {} 在IP:{} 已经登录,系统不允许重复登录!", XCI.formatDateTime(onlineUser.getLoginTime()), onlineUser.getIp());
                saveAccountLog(1, account, false, msg);
                return RestResult.fail(msg);
            }

            //region 更新账号登录状态
            Date firstVisitDateTime = null;
            Date lastVisitDateTime = new Date();
            if (user.getFirstVisitTime() == null) {
                firstVisitDateTime = new Date();
            }
            accountDao.updateLoginStatus(user.getId(), firstVisitDateTime, lastVisitDateTime);
            SysUser cacheUser = SysService.me().userService().selectById(user.getId());
            if (cacheUser != null) {
                cacheUser.setFirstVisitTime(firstVisitDateTime);
                cacheUser.setLastVisitTime(lastVisitDateTime);
                cacheUser.setLoginTimes(cacheUser.getLoginTimes() + 1);
            }
            //endregion

            onlineUserService.active(user.getId()); //激活在线用户
            String token = SysService.me().buildUserToken(user.getId());
            saveAccountLog(1, account, true, "登陆成功");

            //清除用户权限缓存
            SysService.me().permissionService().clearUserPermissionCache(user.getId());
            var loginUser = new SysUserLogin().from(user);
            loginUser.setToken(token);
            loginUser.setPermission(SysService.me().permissionService().selectUserPermissionFromCache(user.getId()));
            return RestResult.ok(loginUser);
        } else {
            lockUserService.add(account);
            int limitCount = lockUserService.limtCount(account);
            if (limitCount == 0) {
                var msg = XCI.format("账号{}多次登录失败被锁定", account);
                saveAccountLog(1, account, false, msg);
            }
            String msg = limitCount > 0 ? XCI.format("{},还可以重试{}次", result.getMsg(), limitCount) : "账号错误次数达到上限,暂时被锁定";
            return RestResult.fail(msg);
        }
    }

    /**
     * 用户注销
     * @param userId 用户主键
     */
    public RestResult logout(@NotNull(message = "请指定用户主键") Long userId) {
        var user = SysService.me().userService().selectById(userId);
        if (user != null) {
            SysService.me().onlineUserService().logoff(userId);//注销在线用户
            SysService.me().permissionService().clearUserPermissionCache(userId);//清除用户权限缓存
            saveAccountLog(2, user.getAccount(), true, "注销成功");
            return RestResult.ok();
        }
        return RestResult.ok();//错误的用户注解,不做任何处理
    }

    /**
     * 验证用户账号和密码
     * @param account  账号
     * @param password 密码
     * @return 验证成功返回true
     */
    public RestResult<SysUser> valid(@NotBlank(message = "请输入账号") String account, @NotBlank(message = "请输入密码") String password) {
        //region 检测密码强度
        RestResult result = SysService.me().validPasswordStrong(password);
        if (result.isFail()) {
            return RestResult.fail(result.getMsg());
        }
        //endregion

        account = account.trim();
        SysUserSecurity user = accountDao.selectSecurityByAccount(account);

        //region 检测条件

        if (user == null) {
            return RestResult.fail("账号密码错误");
        }

        if (XCI.isBlank(user.getPwdSalt())) {
            return RestResult.fail("账号配置无效");
        }
        String encryptedPwd = XCI.encryptPassword(account, password, user.getPwdSalt());
        if (!user.getPwd().equals(encryptedPwd)) {
            return RestResult.fail("账号密码错误");
        }

        if (user.getExpire()) {
            return RestResult.fail("账号已过期");
        }

        if (!user.getStatus()) {
            return RestResult.fail("账号已被禁用");
        }
        var msg = R.Empty;
        if (!user.getPwdNeverExpire() && user.getPwdExpireTime() != null) {
            if (new Date().getTime() >= user.getPwdExpireTime().getTime()) {
                //表示当前日期大于过期日期，提示已过期
                return RestResult.fail("密码已过期");
            }
            int days = Long.valueOf(DateUtil.betweenDay(user.getPwdExpireTime(), new Date(), true)).intValue();
            if (days <= SysParams.SysUserPwdExpireRemindDays.getInt()) {
                msg = XCI.format("密码将于 {} 天后过期,请及时修改密码.", days);
            }
        }
        //endregion
        return RestResult.ok(msg, user);
    }

    /**
     * 修改用户密码
     * @param id              用户主键
     * @param currentPassword 当前密码
     * @param newPassword     新密码
     */
    @OperateLog(tag = R.Module.User, msg = "修改用户密码")
    @Transactional(rollbackFor = Exception.class)
    public RestResult modifyPassword(@NotNull(message = "请指定用户主键") Long id, @NotBlank(message = "请指定当前密码") String currentPassword, @NotBlank(message = "请指定新密码") String newPassword) {
        //验证用户主键
        SysUserSecurity entity = accountDao.selectSecurityById(id);
        if (entity == null) {
            return RestResult.fail("无效的用户主键");
        }
        if (!entity.getPwdAllowModify()) {
            return RestResult.fail("不允许用户修改密码");
        }

        //验证密码强度
        RestResult result = SysService.me().validPasswordStrong(newPassword);
        if (result.isFail()) {
            return RestResult.fail(result.getMsg());
        }

        //验证当前密码
        var epwd = XCI.encryptPassword(entity.getAccount(), currentPassword, entity.getPwdSalt());
        if (!entity.getPwd().equals(epwd)) {
            return RestResult.fail("当前密码输入不正确,请重新输入");
        }

        //生成新的密钥和密码
        String account = entity.getAccount();
        String salt = XCI.createSalt();
        String newPwd = XCI.encryptPassword(account, newPassword, salt);

        //密码过期时间
        if (entity.getPwdNeverExpire()) {
            entity.setPwdExpireTime(null);
        } else {
            entity.setPwdExpireTime(DateUtil.offsetDay(new Date(), SysParams.SysUserPwdAvailableDays.getInt()));
        }

        accountDao.modifyPassword(id, salt, newPwd, entity.getPwdExpireTime());
        return RestResult.ok();
    }

    /**
     * 重置用户登录密码
     * @param ids         用户主键数组
     * @param newPassword 新密码
     */
    @OperateLog(tag = R.Module.User, msg = "重置用户密码")
    @Transactional(rollbackFor = Exception.class)
    public RestResult revisePassword(@NotBlank(message = "请指定用户主键字符串,多个用逗号隔开") String ids, @NotBlank(message = "请指定新密码") String newPassword) {
        String[] idList = XCI.splitToArray(ids);
        //验证密码强度
        RestResult result = SysService.me().validPasswordStrong(newPassword);
        if (result.isFail()) {
            return RestResult.fail(result.getMsg());
        }

        for (String idStr : idList) {
            var id = Long.valueOf(idStr);
            SysUserSecurity user = accountDao.selectSecurityById(id);
            if (user == null) {
                continue;
            }
            String account = user.getAccount();
            String salt = XCI.createSalt();
            String newPwd = XCI.encryptPassword(account, newPassword, salt);

            accountDao.revisePassword(id, salt, newPwd);
            // String msg = Helper.format("修改用户密码 主键: {} 账号: {}", id, account);
        }
        return RestResult.ok();
    }

    /**
     * 保存账户登录日志
     * @param category 类型 [1-登录, 2-注销]
     * @param account  账号
     * @param status   登录状态 [true-成功, false-失败]
     * @param msg      消息
     */
    private void saveAccountLog(Integer category, String account, Boolean status, String msg) {
        var loginLog = SysService.me().buildLoginLog(category, account, status, msg);
        SysService.me().loginLogService().insertAsync(loginLog);
    }
}