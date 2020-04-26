/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.service;

import com.github.lvyanyang.component.AsyncService;
import com.github.lvyanyang.model.PageList;
import com.github.lvyanyang.sys.core.Params;
import com.github.lvyanyang.sys.dao.JobLogDao;
import com.github.lvyanyang.sys.entity.SysJobLog;
import com.github.lvyanyang.sys.filter.JobLogFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.TimerTask;

/**
 * 系统定时任务日志服务
 * @author 吕艳阳
 */
@Service
public class JobLogService {
    /** 定时任务日志数据层对象 */
    @Resource private JobLogDao jobLogDao;

    /**
     * 新建定时任务日志
     * @param entity 定时任务日志实体
     */
    public void insert(SysJobLog entity) {
        if (Params.enableJobLog()) {
            jobLogDao.insert(entity);
        }
    }

    /**
     * 新建定时任务日志(异步)
     * @param entity 定时任务日志实体
     */
    public void insertAsync(SysJobLog entity) {
        if (Params.enableJobLog()) {
            AsyncService.me().execute(new TimerTask() {
                @Override
                public void run() {
                    jobLogDao.insert(entity);
                }
            });
        }
    }

    /**
     * 根据主键查询单个定时任务日志
     * @param id 任务日志主键
     * @return 返回任务日志实体
     */
    public SysJobLog selectById(@NotNull(message = "请指定任务日志主键") Long id) {
        return jobLogDao.selectById(id);
    }

    /**
     * 查询定时任务日志分页列表
     * @param filter 过滤条件
     * @return 返回定时任务日志分页列表
     */
    public PageList<SysJobLog> selectPageList(JobLogFilter filter) {
        filter.PlusEndDateTime();
        return PageList.of(jobLogDao.selectPageList(filter));
    }
}