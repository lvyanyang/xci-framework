/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.dao;

import com.github.lvyanyang.sys.entity.SysDic;
import com.github.lvyanyang.sys.filter.DicFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统字典数据层
 * @author 吕艳阳
 */
public interface DicDao {
    /**
     * 是否存在指定主键的字典
     * @param id 字典主键
     * @return 如果存在返回true
     */
    boolean existById(@Param("id") Long id);

    /**
     * 检查字典类型编码是否存在
     * @param categoryCode 字典类型编码
     * @return 如果存在返回true
     */
    boolean existByCategoryCode(@Param("categoryCode") String categoryCode);

    /**
     * 是否存在指定名称的字典
     * @param categoryCode 字典类型编码
     * @param name 字典名称
     * @param excludeId 排除的主键，如果为null则不指定排除的主键
     * @return 如果存在返回true
     */
    boolean existByName(@Param("categoryCode") String categoryCode, @Param("name") String name, @Param("excludeId") Long excludeId);

    /**
     * 新建字典
     * @param entity 字典实体
     * @return 返回影响的行数
     */
    int insert(@Param("entity") SysDic entity);

    /**
     * 修改字典
     * @param entity 字典实体
     * @return 返回影响的行数
     */
    int update(@Param("entity") SysDic entity);

    /**
     * 根据主键修改字典状态
     * @param id     字典主键
     * @param status 字典状态
     * @return 返回影响的行数
     */
    int updateStatus(@Param("id") Long id, @Param("status") Boolean status);

    /**
     * 更新字典类型编码
     * @param oldCategoryCode 原字典类型编码
     * @param newCategoryCode 新字典类型编码
     */
    int updateCategoryCode(@Param("oldCategoryCode") String oldCategoryCode, @Param("newCategoryCode") String newCategoryCode);

    /**
     * 根据主键更新排序路径
     * @param id 字典主键
     * @param path 排序路径
     * @return 返回影响的行数
     */
    int updatePath(@Param("id") Long id, @Param("path") Integer path);

    /**
     * 根据主键更新父节点
     * @param id 字典主键
     * @param parentId 父节点
     * @return 返回影响的行数
     */
    int updateParentId(@Param("id") Long id, @Param("parentId") Long parentId);

    /**
     * 根据主键删除字典
     * @param id 字典主键
     * @return 返回影响的行数
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据字典类型编码删除
     * @param categoryCode 字典类型编码
     * @return 返回影响的行数
     */
    int deleteByCategoryCode(@Param("categoryCode") String categoryCode);

    /**
     * 根据主键查询单个字典
     * @param id 字典主键
     * @return 返回字典实体
     */
    SysDic selectById(@Param("id") Long id);

    /**
     * 查询字典列表
     * @param filter 过滤条件
     * @return 返回字典列表
     */
    List<SysDic> selectList(@Param("filter") DicFilter filter);

    /**
     * 获取指定节点的直接子节点数
     * @param categoryCode 字典类型编码
     * @param id 字典主键
     * @return 返回直接子节点数量
     */
    int selectChildCount(@Param("categoryCode") String categoryCode, @Param("id") Long id);
}