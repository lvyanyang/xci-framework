/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.dao;

import com.github.lvyanyang.annotation.Paging;
import com.github.lvyanyang.sys.entity.SysModule;
import com.github.lvyanyang.sys.entity.SysUser;
import com.github.lvyanyang.sys.entity.SysUserRoleMap;
import com.github.lvyanyang.sys.entity.SysUserSecurity;
import com.github.lvyanyang.sys.filter.UserFilter;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
    boolean existById(@Param("id") Long id);

    /**
     * 是否存在指定机构主键的用户
     * @param deptId 机构主键
     * @return 如果存在返回true, 否则返回false
     */
    boolean existByDeptId(@Param("deptId") Long deptId);

    /**
     * 是否存在指定角色主键的用户
     * @param roleId 角色主键
     * @return 如果存在返回true, 否则返回false
     */
    boolean existByRoleId(@Param("roleId") Long roleId);

    /**
     * 是否存在指定账号的用户
     * @param account   用户账号
     * @param excludeId 用户主键
     * @return 如果存在返回true, 否则返回false
     */
    boolean existByAccount(@Param("account") String account, @Param("excludeId") Long excludeId);

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
     * 根据主键查询单个用户安全对象,包含密码敏感信息
     * @param id 用户主键
     * @return 返回用户安全对象
     */
    SysUserSecurity selectSecurityById(@Param("id") Long id);

    /**
     * 根据账号查询单个用户安全对象,包含密码敏感信息
     * @param account 用户账号
     * @return 返回用户安全对象
     */
    SysUserSecurity selectSecurityByAccount(@Param("account") String account);

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

    //region 账户

    /**
     * 更新用户登录状态
     * @param id             用户主键
     * @param firstVisitTime 首次登录时间
     * @param lastVisitTime  最后登录时间
     */
    Integer updateLoginStatus(@Param("id") Long id, @Param("firstVisitTime") Date firstVisitTime, @Param("lastVisitTime") Date lastVisitTime);

    /**
     * 修改用户密码
     * @param id       用户主键
     * @param salt     新密码盐
     * @param pwd 新密码
     */
    Integer modifyPassword(@Param("id") Long id, @Param("salt") String salt, @Param("pwd") String pwd, @Param("pwdExpireTime") Date pwdExpireTime);

    /**
     * 重置用户密码
     * @param id       用户主键
     * @param salt     新密码盐
     * @param pwd 新密码
     */
    Integer revisePassword(@Param("id") Long id, @Param("salt") String salt, @Param("pwd") String pwd);

    //endregion

    //region 用户角色关联

    /**
     * 查询指定角色关联的用户主键列表
     * @param roleId 角色主键
     * @return 返回用户主键列表
     */
    List<String> selectIdsByRoleId(@Param("roleId") Long roleId);

    /**
     * 查询指定角色关联的用户列表
     * @param roleId 角色主键
     * @return 返回用户对象列表
     */
    List<SysUser> selectListByRoleId(@Param("roleId") Long roleId);

    /**
     * 查询指定角色未关联的用户主键列表
     * @param roleId 角色主键
     * @return 返回用户主键列表
     */
    List<String> selectUnIdsByRoleId(@Param("roleId") Long roleId);

    /**
     * 查询指定角色未关联的用户列表
     * @param roleId 角色主键
     * @return 返回用户对象列表
     */
    List<SysUser> selectUnListByRoleId(@Param("roleId") Long roleId);

    /**
     * 新建用户角色关联
     * @param entity 用户角色关联实体
     */
    int insertUserRoleMap(@Param("entity") SysUserRoleMap entity);

    /**
     * 根据用户主键删除用户角色关联
     * @param userId 用户主键
     */
    int deleteUserRoleMapByUserId(@Param("userId") Long userId);
    //endregion

    //region 用户拥有的模块

    /**
     * 根据用户主键查询用户拥有的模块列表
     * @param userId 用户主键
     * @return 返回用户拥有的模块列表
     */
    List<SysModule> selectUserModuleListByUserId(@Param("userId") Long userId);
    //endregion
}