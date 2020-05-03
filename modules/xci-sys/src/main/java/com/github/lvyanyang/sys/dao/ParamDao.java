/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.dao;

import com.github.lvyanyang.annotation.Paging;
import com.github.lvyanyang.sys.entity.SysParam;
import com.github.lvyanyang.sys.filter.ParamFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统参数数据访问层
 * @author 吕艳阳
 */
public interface ParamDao {
    /**
     * 是否存在指定主键的参数
     * @param id 参数主键
     * @return 如果存在返回true
     */
    boolean existxById(@Param("id") Long id);

    /**
     * 是否存在指定编码的参数
     * @param code      参数编码
     * @param excludeId 排除的主键，如果为null则不指定排除的主键
     * @return 如果存在返回true
     */
    boolean existxByCode(@Param("code") String code, @Param("excludeId") Long excludeId);

    /**
     * 新建参数
     * @param entity 参数实体
     * @return 返回影响的行数
     */
    int insert(@Param("entity") SysParam entity);

    /**
     * 修改参数
     * @param entity 参数实体
     * @return 返回影响的行数
     */
    int update(@Param("entity") SysParam entity);

    /**
     * 根据主键删除参数
     * @param id 参数主键
     * @return 返回影响的行数
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据主键查询单个参数
     * @param id 参数主键
     * @return 返回参数实体
     */
    SysParam selectById(@Param("id") Long id);

    /**
     * 根据编码查询单个参数
     * @param code 参数编码
     * @return 返回参数实体
     */
    SysParam selectByCode(@Param("code") String code);

    /**
     * 查询参数列表
     * @param filter 过滤条件
     * @return 返回参数列表
     */
    List<SysParam> selectList(@Param("filter") ParamFilter filter);

    /**
     * 查询参数分页列表
     * @param filter 过滤条件
     * @return 返回参数分页列表
     */
    @Paging
    List<SysParam> selectPageList(@Param("filter") ParamFilter filter);
}