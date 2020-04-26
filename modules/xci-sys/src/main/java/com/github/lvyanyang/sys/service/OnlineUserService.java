/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.service;

import com.github.lvyanyang.core.BaseService;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.sys.model.OnlineUserModel;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统在线用户服务
 * @author 吕艳阳
 */
@Service
public class OnlineUserService extends BaseService {
    /**
     * 在线用户缓存
     */
    @Resource
    private Cache onlineUserCache;
    @Resource
    private UserService userService;

    /**
     * 激活在线用户
     * @param userId 用户主键
     */
    public void active(Long userId) {
        OnlineUserModel current = XCI.getCacheValue(onlineUserCache, userId);
        if(current == null) {
            var user = SysService.me().userService().selectById(userId);
            XCI.ifNullThrow(user, () -> RestResult.fail("无效的用户主键：" + userId));
            current = new OnlineUserModel(user);
        }
        current.setActiveTime(new Date());
        onlineUserCache.put(userId, current);
    }

    /**
     * 用户是否在线
     * @param userId 用户主键
     * @return 用户在线返回true
     */
    public boolean isOnline(@NotNull(message = "请指定用户主键") Long userId) {
        return XCI.cacheContains(onlineUserCache, userId);
    }

    /**
     * 获取在线用户对象
     * @param userId 用户主键
     */
    public OnlineUserModel get(@NotNull(message = "请指定用户主键") Long userId) {
        return XCI.getCacheValue(onlineUserCache, userId);
    }

    /**
     * 注销在线用户
     * @param userId 用户主键
     */
    public void logoff(@NotNull(message = "请指定用户主键") Long userId) {
        onlineUserCache.evict(userId);
    }

    /**
     * 根据账号注销在线用户
     * @param account 用户账号
     */
    public void logoffByAccount(@NotBlank(message = "请指定用户账号") String account) {
        var user = userService.selectByAccount(account);
        if(user != null) {
            onlineUserCache.evict(user.getId());
        }
    }

    /**
     * 查询在线用户列表
     * @param name 账号或者姓名过滤条件，可以为null
     */
    public List<OnlineUserModel> selectList(String name) {
        List<OnlineUserModel> list = XCI.getCacheValueList(onlineUserCache);
        if(XCI.isNotBlank(name)) {
            return list.stream().filter(p -> (XCI.isNotBlank(p.getAccount()) && p.getAccount().toLowerCase().contains(
                    name)) || (XCI.isNotBlank(p.getName()) && p.getName().toLowerCase().contains(name))).collect(
                    Collectors.toList());
        }
        return list;
    }
}
