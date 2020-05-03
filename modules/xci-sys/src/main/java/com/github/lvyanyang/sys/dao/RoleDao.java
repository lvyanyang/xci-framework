/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.dao;

import com.github.lvyanyang.annotation.Paging;
import com.github.lvyanyang.sys.entity.SysRole;
import com.github.lvyanyang.sys.filter.RoleFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色数据层
 * @author 吕艳阳
 */
public interface RoleDao {
    /**
     * 是否存在指定主键的角色
     * @param id 角色主键
     * @return 如果存在返回true
     */
    boolean existxById(@Param("id") Long id);

    /**
     * 是否存在指定机构主键的角色
     * @param deptId 机构主键
     * @return 如果存在返回true
     */
    boolean existxByDeptId(@Param("deptId") Long deptId);

    /**
     * 是否存在指定编码的角色
     * @param code      角色编码
     * @param excludeId 排除的主键，如果为null则不指定排除的主键
     * @return 如果存在返回true
     */
    boolean existxByCode(@Param("code") String code, @Param("excludeId") Long excludeId);

    /**
     * 是否存在指定名称的角色
     * @param name      角色名称
     * @param deptId    机构主键
     * @param excludeId 排除的主键，如果为null则不指定排除的主键
     * @return 如果存在返回true
     */
    boolean existxByName(@Param("name") String name, @Param("deptId") Long deptId, @Param("excludeId") Long excludeId);

    /**
     * 新建角色
     * @param entity 角色实体
     * @return 返回影响的行数
     */
    int insert(@Param("entity") SysRole entity);

    /**
     * 修改角色
     * @param entity 角色实体
     * @return 返回影响的行数
     */
    int update(@Param("entity") SysRole entity);

    /**
     * 根据主键修改角色状态
     * @param id     角色主键
     * @param status 角色状态
     * @return 返回影响的行数
     */
    int updateStatus(@Param("id") Long id, @Param("status") Boolean status);

    /**
     * 根据主键修改角色机构数据权限
     * @param id        角色主键
     * @param deptScope 机构数据权限
     * @return 返回影响的行数
     */
    Integer updateDeptScope(@Param("id") Long id, @Param("deptScope") Integer deptScope);

    /**
     * 根据主键删除角色
     * @param id 角色主键
     * @return 返回影响的行数
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据主键查询单个角色
     * @param id 角色主键
     * @return 返回角色实体
     */
    SysRole selectById(@Param("id") Long id);

    /**
     * 根据编码查询单个角色
     * @param code 角色编码
     * @return 返回角色实体
     */
    SysRole selectByCode(@Param("code") String code);

    /**
     * 查询角色列表
     * @param filter 过滤条件
     * @return 返回角色列表
     */
    List<SysRole> selectList(@Param("filter") RoleFilter filter);

    /**
     * 查询角色分页列表
     * @param filter 过滤条件
     * @return 返回角色分页列表
     */
    @Paging
    List<SysRole> selectPageList(@Param("filter") RoleFilter filter);
}