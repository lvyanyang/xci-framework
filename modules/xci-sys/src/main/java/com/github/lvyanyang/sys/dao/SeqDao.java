/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.dao;

import com.github.lvyanyang.annotation.Paging;
import com.github.lvyanyang.sys.entity.SysSeq;
import com.github.lvyanyang.sys.filter.SeqFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统序列数据层
 * @author 吕艳阳
 */
public interface SeqDao {
    /**
     * 是否存在指定主键的序列
     * @param id 序列主键
     * @return 如果存在返回true
     */
    boolean existById(@Param("id") Long id);

    /**
     * 是否存在指定编码的序列
     * @param code      序列编码
     * @param excludeId 排除的主键，如果为null则不指定排除的主键
     * @return 如果存在返回true
     */
    boolean existByCode(@Param("code") String code, @Param("excludeId") Long excludeId);

    /**
     * 新建序列
     * @param entity 序列实体
     * @return 返回影响的行数
     */
    int insert(@Param("entity") SysSeq entity);

    /**
     * 修改序列
     * @param entity 序列实体
     * @return 返回影响的行数
     */
    int update(@Param("entity") SysSeq entity);

    /**
     * 根据主键删除序列
     * @param id 序列主键
     * @return 返回影响的行数
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据主键查询单个序列
     * @param id 序列主键
     * @return 返回序列实体
     */
    SysSeq selectById(@Param("id") Long id);

    /**
     * 根据编码查询单个序列
     * @param code 序列编码
     * @return 返回序列实体
     */
    SysSeq selectByCode(@Param("code") String code);

    /**
     * 获取当前序列值
     * @param code 序列编码
     */
    Long currentValue(@Param("code") String code);

    /**
     * 增长当前序列值
     * @param code 序列编码
     */
    Long incrementValue(@Param("code") String code);

    /**
     * 查询序列列表
     * @param filter 过滤条件
     * @return 返回序列列表
     */
    List<SysSeq> selectList(@Param("filter") SeqFilter filter);

    /**
     * 查询序列分页列表
     * @param filter 过滤条件
     * @return 返回序列分页列表
     */
    @Paging
    List<SysSeq> selectPageList(@Param("filter") SeqFilter filter);
}