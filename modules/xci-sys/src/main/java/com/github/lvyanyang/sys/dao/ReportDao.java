/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.dao;

import com.github.lvyanyang.annotation.Paging;
import com.github.lvyanyang.sys.entity.SysReport;
import com.github.lvyanyang.sys.filter.ReportFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统报表数据层
 * @author 吕艳阳
 */
public interface ReportDao {
    /**
     * 是否存在指定主键的报表
     * @param id 报表主键
     * @return 如果存在返回true
     */
    boolean existxById(@Param("id") Long id);

    /**
     * 是否存在指定编码的报表
     * @param code      报表编码
     * @param excludeId 排除的主键，如果为null则不指定排除的主键
     * @return 如果存在返回true
     */
    boolean existxByCode(@Param("code") String code, @Param("excludeId") Long excludeId);

    /**
     * 新建报表
     * @param entity 报表实体
     * @return 返回影响的行数
     */
    int insert(@Param("entity") SysReport entity);

    /**
     * 修改报表
     * @param entity 报表实体
     * @return 返回影响的行数
     */
    int update(@Param("entity") SysReport entity);

    /**
     * 根据主键修改报表状态
     * @param id     报表主键
     * @param status 报表状态
     * @return 返回影响的行数
     */
    int updateStatus(@Param("id") Long id, @Param("status") Boolean status);

    /**
     * 根据主键删除报表
     * @param id 报表主键
     * @return 返回影响的行数
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据主键查询单个报表
     * @param id 报表主键
     * @return 返回报表实体
     */
    SysReport selectById(@Param("id") Long id);

    /**
     * 根据编码查询单个参数
     * @param code 参数编码
     * @return 返回参数实体
     */
    SysReport selectByCode(@Param("code") String code);

    /**
     * 查询报表列表
     * @param filter 报表过滤对象
     * @return 返回报表列表
     */
    List<SysReport> selectList(@Param("filter") ReportFilter filter);

    /**
     * 查询报表分页列表
     * @param filter 过滤条件
     * @return 返回参数分页列表
     */
    @Paging
    List<SysReport> selectPageList(@Param("filter") ReportFilter filter);
}