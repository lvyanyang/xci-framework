/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.dao;

import com.github.lvyanyang.sys.entity.SysRole;
import com.github.lvyanyang.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户角色关联数据层
 * @author 吕艳阳
 */
public interface UserRoleMapDao {
    //#region Insert

    /**
     * 新增用户角色关联
     * @param userId 用户主键
     * @param roleId 角色主键
     */
    int insert(@Param("userId") Long userId, @Param("roleId") Long roleId);

    //#endregion

    //#region Delete

    /**
     * 删除关联
     * @param userId 用户主键
     * @param roleId 角色主键
     */
    int delete(@Param("userId") Long userId, @Param("roleId") Long roleId);

    /**
     * 根据用户主键删除关联
     * @param userId 用户主键
     */
    int clearMapRole(@Param("userId") Long userId);

    /**
     * 根据角色主键删除关联
     * @param roleId 角色主键
     */
    int clearMapUser(@Param("roleId") Long roleId);

    //#endregion

    //#region MapUser

    /**
     * 根据角色主键查询已关联的用户主键列表
     * @param roleId 角色主键
     */
    List<String> selectMapUserIds(@Param("roleId") Long roleId);

    /**
     * 根据角色主键查询已关联的用户列表
     * @param roleId 角色主键
     */
    List<SysUser> selectMapUserList(@Param("roleId") Long roleId);

    /**
     * 根据角色主键查询未关联的用户主键列表
     * @param roleId 角色主键
     */
    List<String> selectUnMapUserIds(@Param("roleId") Long roleId);

    /**
     * 根据角色主键查询未关联的用户列表
     * @param roleId 角色主键
     */
    List<SysUser> selectUnMapUserList(@Param("roleId") Long roleId);

    //#endregion

    //#region MapRole

    /**
     * 根据用户主键查询已关联的角色主键列表
     * @param userId 用户主键
     */
    List<String> selectMapRoleIds(@Param("userId") Long userId);

    /**
     * 根据用户主键查询已关联的角色列表
     * @param userId 用户主键
     */
    List<SysRole> selectMapRoleList(@Param("userId") Long userId);

    /**
     * 根据用户主键查询未关联的角色主键列表
     * @param userId 用户主键
     */
    List<String> selectUnMapRoleIds(@Param("userId") Long userId);

    /**
     * 根据用户主键查询未关联的角色列表
     * @param userId 用户主键
     */
    List<SysRole> selectUnMapRoleList(@Param("userId") Long userId);

    //#endregion
}