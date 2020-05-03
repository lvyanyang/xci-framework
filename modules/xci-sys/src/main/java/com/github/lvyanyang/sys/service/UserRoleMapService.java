/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.service;

import com.github.lvyanyang.core.BaseService;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.sys.dao.UserRoleMapDao;
import com.github.lvyanyang.sys.entity.SysRole;
import com.github.lvyanyang.sys.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统用户角色关联服务
 * @author 吕艳阳
 */
@Slf4j
@Service
public class UserRoleMapService extends BaseService {
    @Resource private UserRoleMapDao userRoleMapDao;

    //#region MapRole

    /**
     * 添加用户角色
     * @param userId 用户主键
     * @param roleIds 角色主键字符串
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertMapRole(@NotNull(message = "请指定用户主键") Long userId, String roleIds) {
        if (XCI.isEmpty(roleIds)) return;
        var roleIdList = XCI.splitToArray(roleIds);
        for (String roleId : roleIdList) {
            userRoleMapDao.insert(userId, Long.valueOf(roleId.trim()));
        }
    }

    /**
     * 删除用户角色
     * @param userId 用户主键
     * @param roleIds 角色主键字符串
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteMapRole(@NotNull(message = "请指定用户主键") Long userId, String roleIds) {
        if (XCI.isEmpty(roleIds)) return;
        var roleIdList = XCI.splitToArray(roleIds);
        for (String roleId : roleIdList) {
            userRoleMapDao.delete(userId, Long.valueOf(roleId.trim()));
        }
    }

    /**
     * 清除并添加用户角色
     * @param userId  用户主键
     * @param roleIds 角色主键字符串
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveMapRole(@NotNull(message = "请指定用户主键") Long userId, String roleIds) {
        clearMapRole(userId);
        insertMapRole(userId, roleIds);
    }

    /**
     * 清除用户角色
     * @param userId 用户主键
     */
    public void clearMapRole(@NotNull(message = "请指定用户主键") Long userId) {
        userRoleMapDao.clearMapRole(userId);
    }

    /**
     * 查询用户角色主键列表
     * @param userId 用户主键
     */
    public List<String> selectMapRoleIds(@NotNull(message = "请指定用户主键") Long userId) {
        return userRoleMapDao.selectMapRoleIds(userId);
    }

    /**
     * 查询用户角色列表
     * @param userId 用户主键
     */
    public List<SysRole> selectMapRoleList(@NotNull(message = "请指定用户主键") Long userId) {
        return userRoleMapDao.selectMapRoleList(userId);
    }

    /**
     * 查询用户未关联角色主键列表
     * @param userId 用户主键
     */
    public List<String> selectUnMapRoleIds(@NotNull(message = "请指定用户主键") Long userId) {
        return userRoleMapDao.selectUnMapRoleIds(userId);
    }

    /**
     * 查询用户未关联角色列表
     * @param userId 用户主键
     */
    public List<SysRole> selectUnMapRoleList(@NotNull(message = "请指定用户主键") Long userId) {
        return userRoleMapDao.selectUnMapRoleList(userId);
    }

    //#endregion

    //#region MapUser

    /**
     * 添加角色用户
     * @param roleId 角色主键
     * @param userIds 用户主键字符串
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertMapUser(@NotNull(message = "请指定角色主键") Long roleId, String userIds) {
        if (XCI.isEmpty(userIds)) return;
        var userIdList = XCI.splitToArray(userIds);
        for (String userId : userIdList) {
            userRoleMapDao.insert(Long.valueOf(userId.trim()), roleId);
        }
    }

    /**
     * 删除角色用户
     * @param roleId 角色主键
     * @param userIds 用户主键字符串
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteMapUser(@NotNull(message = "请指定角色主键") Long roleId, String userIds) {
        if (XCI.isEmpty(userIds)) return;
        var userIdList = XCI.splitToArray(userIds);
        for (String userId : userIdList) {
            userRoleMapDao.delete(Long.valueOf(userId.trim()), roleId);
        }
    }

    /**
     * 清除并添加角色用户
     * @param roleId 角色主键
     * @param userIds 用户主键字符串
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveMapUser(@NotNull(message = "请指定角色主键") Long roleId, String userIds) {
        clearMapUser(roleId);
        insertMapUser(roleId, userIds);
    }

    /**
     * 清除角色用户
     * @param roleId 角色主键
     */
    public void clearMapUser(@NotNull(message = "请指定角色主键") Long roleId) {
        userRoleMapDao.clearMapUser(roleId);
    }

    /**
     * 查询角色用户主键列表
     * @param roleId 角色主键
     */
    public List<String> selectMapUserIds(@NotNull(message = "请指定角色主键") Long roleId) {
        return userRoleMapDao.selectMapUserIds(roleId);
    }

    /**
     * 查询角色用户列表
     * @param roleId 角色主键
     */
    public List<SysUser> selectMapUserList(@NotNull(message = "请指定角色主键") Long roleId) {
        return userRoleMapDao.selectMapUserList(roleId);
    }

    /**
     * 查询未关联角色用户主键列表
     * @param roleId 角色主键
     */
    public List<String> selectUnMapUserIds(@NotNull(message = "请指定角色主键") Long roleId) {
        return userRoleMapDao.selectUnMapUserIds(roleId);
    }

    /**
     * 查询未关联角色用户列表
     * @param roleId 角色主键
     */
    public List<SysUser> selectUnMapUserList(@NotNull(message = "请指定角色主键") Long roleId) {
        return userRoleMapDao.selectUnMapUserList(roleId);
    }

    //#endregion
}