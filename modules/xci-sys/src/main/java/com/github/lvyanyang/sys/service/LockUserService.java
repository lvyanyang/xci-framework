/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.service;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.sys.core.Params;
import com.github.lvyanyang.sys.model.LockUserModel;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * 系统锁定用户服务
 * @author 吕艳阳
 */
@Service
public class LockUserService {
    /**
     * 锁定用户缓存
     */
    @Resource
    private Cache lockUserCache;

    /**
     * 添加锁定用户
     */
    public void add(@NotBlank(message = "请指定用户账号") String account) {
        LockUserModel current = lockUserCache.get(account, LockUserModel.class);
        if (current == null) {
            current = new LockUserModel();
            current.setAccount(account);
            current.setLoginTime(new Date());
            current.setCount(0);
            lockUserCache.put(account, current);
        }

        current.setCount(current.getCount() + 1);
        if (current.getCount() >= Params.sysUserLockMaxRetryCount()) {
            current.setDisLockTime(DateTime.now().offset(DateField.HOUR, 1).toJdkDate());
        }
    }

    /**
     * 用户是否锁定
     * @param account 用户账号
     * @return 用户锁定返回true
     */
    public boolean isLock(@NotBlank(message = "请指定用户账号") String account) {
        LockUserModel current = get(account);
        return current != null && (current.getCount() >= Params.sysUserLockMaxRetryCount());
    }

    /**
     * 检测用户是否需要验证码
     * @param account 用户账号
     * @return 需要验证码返回true
     */
    public boolean requireCaptcha(@NotBlank(message = "请指定用户账号") String account) {
        LockUserModel current = get(account);
        return current != null && (current.getCount() >= Params.sysUserRequireCaptchaCount());
    }

    /**
     * 查询剩余重试次数
     * @param account 用户账号
     */
    public int limtCount(@NotBlank(message = "请指定用户账号") String account) {
        LockUserModel current = get(account);
        if (current != null) {
            return Params.sysUserLockMaxRetryCount() - current.getCount();
        }
        return Params.sysUserLockMaxRetryCount();
    }

    /**
     * 获取锁定用户对象
     * @param account 用户账号
     */
    public LockUserModel get(@NotBlank(message = "请指定用户账号") String account) {
        return lockUserCache.get(account, LockUserModel.class);
    }

    /**
     * 移除锁定用户
     * @param account 用户账号
     */
    public void remove(@NotBlank(message = "请指定用户账号") String account) {
        lockUserCache.evict(account);
    }

    /**
     * 查询锁定用户列表
     */
    public List<LockUserModel> selectList() {
        return XCI.getCacheValueList(lockUserCache);
    }
}
