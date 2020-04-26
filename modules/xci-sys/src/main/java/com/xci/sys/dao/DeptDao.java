package com.xci.sys.dao;

import com.xci.sys.entity.SysDept;
import com.xci.sys.filter.DeptFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统机构数据层
 * @author 吕艳阳
 */
public interface DeptDao {
    /**
     * 是否存在指定主键的机构
     * @param id 机构主键
     * @return 如果存在返回true
     */
    boolean existById(@Param("id") Long id);

    /**
     * 是否存在指定编码的机构
     * @param code      机构编码
     * @param excludeId 排除的主键，如果为null则不指定排除的主键
     * @return 如果存在返回true
     */
    boolean existByCode(@Param("code") String code, @Param("excludeId") Long excludeId);

    /**
     * 是否存在指定名称的机构
     * @param name      机构名称
     * @param parentId  机构上级主键
     * @param excludeId 排除的主键，如果为null则不指定排除的主键
     * @return 如果存在返回true
     */
    boolean existByName(@Param("name") String name, @Param("parentId") Long parentId, @Param("excludeId") Long excludeId);

    /**
     * 新建机构
     * @param entity 机构实体
     * @return 返回影响的行数
     */
    int insert(@Param("entity") SysDept entity);

    /**
     * 修改机构
     * @param entity 机构实体
     * @return 返回影响的行数
     */
    int update(@Param("entity") SysDept entity);

    /**
     * 根据主键更新机构状态
     * @param id     机构主键
     * @param status 机构状态
     * @return 返回影响的行数
     */
    int updateStatus(@Param("id") Long id, @Param("status") Boolean status);

    /**
     * 根据主键更新排序路径
     * @param id   机构主键
     * @param path 排序路径
     * @return 返回影响的行数
     */
    int updatePath(@Param("id") Long id, @Param("path") Integer path);

    /**
     * 根据主键更新父节点
     * @param id       机构主键
     * @param parentId 父节点
     * @return 返回影响的行数
     */
    int updateParentId(@Param("id") Long id, @Param("parentId") Long parentId);

    /**
     * 根据主键删除机构
     * @param id 机构主键
     * @return 返回影响的行数
     */
    int deleteById(@Param("id") Long id, @Param("deleteId") Long deleteId);

    /**
     * 根据主键查询单个机构
     * @param id 机构主键
     * @return 返回机构实体
     */
    SysDept selectById(@Param("id") Long id);

    /**
     * 根据编码查询单个机构
     * @param code 机构编码
     * @return 返回机构实体
     */
    SysDept selectByCode(@Param("code") String code);

    /**
     * 查询机构列表
     * @param filter 过滤条件
     * @return 返回机构列表
     */
    List<SysDept> selectList(@Param("filter") DeptFilter filter);

    /**
     * 获取指定节点的直接子节点数
     * @param id 机构主键
     * @return 返回直接子节点数量
     */
    int selectChildCount(@Param("id") Long id);
}