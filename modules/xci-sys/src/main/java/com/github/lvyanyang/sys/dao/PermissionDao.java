/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.dao;

import com.github.lvyanyang.sys.entity.SysDept;
import com.github.lvyanyang.sys.entity.SysModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统账户权限数据访问层
 * @author 吕艳阳
 */
public interface PermissionDao {
    /**
     * 根据用户主键查询用户拥有的模块权限列表
     * @param userId 用户主键
     * @return 返回用户拥有的模块权限列表
     */
    List<SysModule> selectUserModuleList(@Param("userId") Long userId);

    /**
     * 根据用户主键查询用户拥有的机构权限列表
     * @param userId 用户主键
     * @return 返回用户拥有的机构权限列表
     */
    List<SysDept> selectUserDeptDataList(@Param("userId") Long userId);

        /**
     * 根据角色主键查询模块对象列表
     * @param roleId 角色主键
     */
    List<SysModule> selectRoleModuleList(@Param("roleId") Long roleId);

    /**
     * 根据角色主键查询机构数据对象列表
     * @param roleId 角色主键
     */
    List<SysDept> selectRoleDeptDataList(@Param("roleId") Long roleId);
}