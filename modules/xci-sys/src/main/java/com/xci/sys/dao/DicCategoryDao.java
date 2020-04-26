package com.xci.sys.dao;

import com.xci.sys.entity.SysDicCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统字典类型数据层
 * @author 吕艳阳
 */
public interface DicCategoryDao {
    /**
     * 是否存在指定主键的字典类型
     * @param id 字典类型主键
     * @return 如果存在返回true
     */
    boolean existById(@Param("id") Long id);

    /**
     * 是否存在指定编码的字典类型
     * @param code 字典类型编码
     * @param excludeId 排除的主键，如果为null则不指定排除的主键
     * @return 如果存在返回true
     */
    boolean existByCode(@Param("code") String code, @Param("excludeId") Long excludeId);

    /**
     * 是否存在指定名称的字典类型
     * @param name 字典类型名称
     * @param excludeId 排除的主键，如果为null则不指定排除的主键
     * @return 如果存在返回true
     */
    boolean existByName(@Param("name") String name, @Param("excludeId") Long excludeId);

    /**
     * 新建字典类型
     * @param entity 字典类型实体
     * @return 返回影响的行数
     */
    int insert(@Param("entity") SysDicCategory entity);

    /**
     * 修改字典类型
     * @param entity 字典类型实体
     * @return 返回影响的行数
     */
    int update(@Param("entity") SysDicCategory entity);

    /**
     * 根据主键更新排序路径
     * @param id 字典类型主键
     * @param path 排序路径
     * @return 返回影响的行数
     */
    int updatePath(@Param("id") Long id, @Param("path") Integer path);

    /**
     * 根据主键更新父节点
     * @param id 字典类型主键
     * @param parentId 父节点
     * @return 返回影响的行数
     */
    int updateParentId(@Param("id") Long id, @Param("parentId") Long parentId);

    /**
     * 根据主键删除字典类型
     * @param id 字典类型主键
     * @return 返回影响的行数
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据主键查询单个字典类型
     * @param id 字典类型主键
     * @return 返回字典类型实体
     */
    SysDicCategory selectById(@Param("id") Long id);

    /**
     * 根据编码查询单个字典类型
     * @param code 字典类型编码
     * @return 返回字典类型实体
     */
    SysDicCategory selectByCode(@Param("code") String code);

    /**
     * 查询字典类型列表
     * @return 返回字典类型列表
     */
    List<SysDicCategory> selectList();

    /**
     * 获取指定节点的直接子节点数
     * @param id 字典类型主键
     * @return 返回直接子节点数量
     */
    int selectChildCount(@Param("id") Long id);
}