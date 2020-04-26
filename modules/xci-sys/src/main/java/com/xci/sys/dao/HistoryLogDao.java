package com.xci.sys.dao;

import com.github.pagehelper.Page;
import com.xci.annotation.Paging;
import com.xci.sys.entity.SysHistoryLog;
import com.xci.sys.filter.HistoryLogFilter;
import org.apache.ibatis.annotations.Param;

/**
 * 系统历史日志数据层
 * @author 吕艳阳
 */
public interface HistoryLogDao {
    /**
     * 新建历史日志
     * @param entity 历史日志实体
     * @return 返回影响的行数
     */
    Integer insert(@Param("entity") SysHistoryLog entity);

    /**
     * 根据主键查询单个历史日志
     * @param id 历史日志主键
     * @return 返回历史记录实体
     */
    SysHistoryLog selectById(@Param("id") Long id);

    /**
     * 查询历史日志分页列表
     * @param filter 过滤条件
     * @return 返回历史记录分页列表
     */
    @Paging
    Page<SysHistoryLog> selectPageList(@Param("filter") HistoryLogFilter filter);
}