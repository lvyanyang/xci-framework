package com.xci.sys.dao;

import com.xci.sys.entity.SysModule;
import com.xci.sys.filter.ModuleFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统模块数据层
 * @author 吕艳阳
 */
public interface ModuleDao {
    /**
     * 是否存在指定主键的模块
     * @param id 模块主键
     * @return 如果存在返回true
     */
    boolean existById(@Param("id") Long id);

    /**
     * 是否存在指定编码的模块
     * @param code 模块编码
     * @param excludeId 排除的主键，如果为null则不指定排除的主键
     * @return 如果存在返回true
     */
    boolean existByCode(@Param("code") String code, @Param("excludeId") Long excludeId);

    /**
     * 新建模块
     * @param entity 模块实体
     * @return 返回影响的行数
     */
    int insert(@Param("entity") SysModule entity);

    /**
     * 修改模块
     * @param entity 模块实体
     * @return 返回影响的行数
     */
    int update(@Param("entity") SysModule entity);

    /**
     * 根据主键修改模块状态
     * @param id     应用主键
     * @param status 应用状态
     * @return 返回影响的行数
     */
    int updateStatus(@Param("id") Long id, @Param("status") Boolean status);

    /**
     * 根据主键修改模块公共状态
     * @param id 模块主键
     * @param publicStatus 模块公共状态
     * @return 返回影响的行数
     */
    Integer updatePublicStatus(@Param("id") Long id, @Param("publicStatus") Boolean publicStatus);

    /**
     * 根据主键修改模块展开状态
     * @param id 模块主键
     * @param expandStatus 模块展开状态
     * @return 返回影响的行数
     */
    Integer updateExpandStatus(@Param("id") Long id, @Param("expandStatus") Boolean expandStatus);

    /**
     * 根据主键更新排序路径
     * @param id 模块主键
     * @param path 排序路径
     * @return 返回影响的行数
     */
    int updatePath(@Param("id") Long id, @Param("path") Integer path);

    /**
     * 根据主键更新父节点
     * @param id 模块主键
     * @param parentId 父节点
     * @return 返回影响的行数
     */
    int updateParentId(@Param("id") Long id, @Param("parentId") Long parentId);

    /**
     * 根据主键删除模块
     * @param id 模块主键
     * @return 返回影响的行数
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据主键查询单个模块
     * @param id 模块主键
     * @return 返回模块实体
     */
    SysModule selectById(@Param("id") Long id);

    /**
     * 根据编码查询单个模块
     * @param code 模块编码
     * @return 返回模块实体
     */
    SysModule selectByCode(@Param("code") String code);

    /**
     * 查询模块列表
     * @param filter 过滤条件对象
     * @return 返回模块列表
     */
    List<SysModule> selectList(@Param("filter") ModuleFilter filter);

    /**
     * 获取指定节点的直接子节点数
     * @param id 模块主键
     * @return 返回直接子节点数量
     */
    int selectChildCount(@Param("id") Long id);
}