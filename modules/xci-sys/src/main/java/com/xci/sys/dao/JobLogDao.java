package com.xci.sys.dao;

import com.github.pagehelper.Page;
import com.xci.sys.entity.SysJobLog;
import com.xci.sys.filter.JobLogFilter;
import com.xci.annotation.Paging;
import org.apache.ibatis.annotations.Param;

/**
 * 系统定时任务日志数据层
 * @author 吕艳阳
 */
public interface JobLogDao {
    /**
     * 新建定时任务日志
     * @param entity 任务日志实体
     * @return 返回影响的行数
     */
    int insert(@Param("entity") SysJobLog entity);

    /**
     * 根据主键查询单个定时任务日志
     * @param id 任务日志主键
     * @return 返回单个任务日志实体
     */
    SysJobLog selectById(@Param("id") Long id);

    /**
     * 查询定时任务日志分页列表
     * @param filter 过滤条件
     * @return 返回符合条件的分页数据集合
     */
    @Paging
    Page<SysJobLog> selectPageList(@Param("filter") JobLogFilter filter);
}
