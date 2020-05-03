/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.dao;

import com.github.lvyanyang.annotation.Paging;
import com.github.lvyanyang.sys.entity.SysUser;
import com.github.lvyanyang.sys.filter.UserFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户数据访问层
 * @author 吕艳阳
 */
public interface UserDao {
    /**
     * 是否存在指定主键的用户
     * @param id 用户主键
     * @return 如果存在返回true
     */
    boolean existxById(@Param("id") Long id);

    /**
     * 是否存在指定机构主键的用户
     * @param deptId 机构主键
     * @return 如果存在返回true, 否则返回false
     */
    boolean existxByDeptId(@Param("deptId") Long deptId);

    /**
     * 是否存在指定角色主键的用户
     * @param roleId 角色主键
     * @return 如果存在返回true, 否则返回false
     */
    boolean existxByRoleId(@Param("roleId") Long roleId);

    /**
     * 是否存在指定账号的用户
     * @param account   用户账号
     * @param excludeId 用户主键
     * @return 如果存在返回true, 否则返回false
     */
    boolean existxByAccount(@Param("account") String account, @Param("excludeId") Long excludeId);

    /**
     * 新建用户
     * @param entity 用户实体
     * @return 返回影响的行数
     */
    int insert(@Param("entity") SysUser entity);

    /**
     * 修改用户
     * @param entity 用户实体
     * @return 返回影响的行数
     */
    int update(@Param("entity") SysUser entity);

    /**
     * 根据主键修改用户状态
     * @param id     用户主键
     * @param status 用户状态
     * @return 返回影响的行数
     */
    int updateStatus(@Param("id") Long id, @Param("status") Boolean status);

    /**
     * 根据主键删除用户
     * @param id 用户主键
     * @return 返回影响的行数
     */
    int deleteById(@Param("id") Long id, @Param("newId") Long newId);

    /**
     * 根据主键查询单个用户
     * @param id 用户主键
     * @return 返回用户实体
     */
    SysUser selectById(@Param("id") Long id);

    /**
     * 根据账号查询单个用户
     * @param account 用户账号
     * @return 返回用户对象
     */
    SysUser selectByAccount(@Param("account") String account);

    /**
     * 查询用户列表
     * @param filter 过滤条件
     * @return 返回用户列表
     */
    List<SysUser> selectList(@Param("filter") UserFilter filter);

    /**
     * 查询用户分页列表
     * @param filter 过滤条件
     * @return 返回用户分页列表
     */
    @Paging
    List<SysUser> selectPageList(@Param("filter") UserFilter filter);
}