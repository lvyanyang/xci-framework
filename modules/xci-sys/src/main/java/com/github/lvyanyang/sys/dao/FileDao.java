/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.dao;

import com.github.lvyanyang.annotation.Paging;
import com.github.lvyanyang.annotation.Top;
import com.github.lvyanyang.sys.entity.SysFile;
import com.github.lvyanyang.sys.filter.FileFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统文件数据层
 * @author 吕艳阳
 */
public interface FileDao {
    /**
     * 检查记录主键是否存在
     * @param recordId 记录主键
     * @return 如果存在返回true, 否则返回false
     */
    boolean existxByRecordId(@Param("recordId") Long recordId);

    /**
     * 新建文件
     * @param entity 文件实体
     * @return 返回影响的行数
     */
    int insert(@Param("entity") SysFile entity);

    /**
     * 根据主键删除文件
     * @param id 文件主键
     * @return 返回影响的行数
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据记录主键删除文件
     * @param recordId 记录主键
     * @return 返回影响的行数
     */
    int deleteByRecordId(@Param("recordId") Long recordId);

    /**
     * 根据主键查询单个文件
     * @param id 文件主键
     * @return 返回文件实体
     */
    SysFile selectById(@Param("id") Long id);

    /**
     * 根据记录主键获取第一个文件实体
     * @param recordId 记录主键
     * @return 返回关联文件实体
     */
    @Top
    SysFile selectByRecordId(@Param("recordId") Long recordId);

    /**
     * 查询文件列表
     * @param filter 过滤条件
     * @return 返回文件列表
     */
    List<SysFile> selectList(@Param("filter") FileFilter filter);

    /**
     * 查询列表分页列表
     * @param filter 过滤条件
     * @return 返回列表分页列表
     */
    @Paging
    List<SysFile> selectPageList(@Param("filter") FileFilter filter);
}