/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.dao;

import com.github.lvyanyang.annotation.Paging;
import com.github.lvyanyang.sys.entity.SysApp;
import com.github.lvyanyang.sys.filter.AppFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统应用数据访问层
 * @author 吕艳阳
 */
public interface AppDao {
    /**
     * 是否存在指定主键的应用
     * @param id 应用主键
     * @return 如果存在返回true
     */
    boolean existxById(@Param("id") Long id);

    /**
     * 是否存在指定名称的应用
     * @param name      应用名称
     * @param excludeId 排除的主键，如果为null则不指定排除的主键
     * @return 如果存在返回true
     */
    boolean existxByName(@Param("name") String name, @Param("excludeId") Long excludeId);

    /**
     * 新建应用
     * @param entity 应用实体
     * @return 返回影响的行数
     */
    int insert(@Param("entity") SysApp entity);

    /**
     * 修改应用
     * @param entity 应用实体
     * @return 返回影响的行数
     */
    int update(@Param("entity") SysApp entity);

    /**
     * 根据主键修改应用状态
     * @param id     应用主键
     * @param status 应用状态
     * @return 返回影响的行数
     */
    int updateStatus(@Param("id") Long id, @Param("status") Boolean status);

    /**
     * 根据主键删除应用
     * @param id 应用主键
     * @return 返回影响的行数
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据主键查询单个应用
     * @param id 应用主键
     * @return 返回应用实体
     */
    SysApp selectById(@Param("id") Long id);

    /**
     * 查询应用列表
     * @param filter 过滤条件
     * @return 返回应用列表
     */
    List<SysApp> selectList(@Param("filter") AppFilter filter);

    /**
     * 查询应用分页列表
     * @param filter 过滤条件
     * @return 返回应用分页列表
     */
    @Paging
    List<SysApp> selectPageList(@Param("filter") AppFilter filter);
}