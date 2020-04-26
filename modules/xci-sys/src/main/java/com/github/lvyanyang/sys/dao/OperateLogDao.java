/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.dao;

import com.github.pagehelper.Page;
import com.github.lvyanyang.annotation.Paging;
import com.github.lvyanyang.sys.entity.SysOperateLog;
import com.github.lvyanyang.sys.filter.OperateLogFilter;
import org.apache.ibatis.annotations.Param;

/**
 * 系统操作日志数据层
 * @author 吕艳阳
 */
public interface OperateLogDao {
    /**
     * 新建操作日志
     * @param entity 操作日志实体
     * @return 返回影响的行数
     */
    Integer insert(@Param("entity") SysOperateLog entity);

    /**
     * 根据主键查询单个操作日志
     * @param id 操作日志主键
     * @return 返回操作日志实体
     */
    SysOperateLog selectById(@Param("id") Long id);

    /**
     * 查询操作日志分页列表
     * @param filter 过滤条件
     * @return 返回操作日志分页列表
     */
    @Paging
    Page<SysOperateLog> selectPageList(@Param("filter") OperateLogFilter filter);
}