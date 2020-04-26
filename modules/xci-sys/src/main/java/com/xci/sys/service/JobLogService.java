package com.xci.sys.service;

import com.xci.sys.dao.JobLogDao;
import com.xci.sys.entity.SysJobLog;
import com.xci.sys.filter.JobLogFilter;
import com.xci.model.PageList;
import com.xci.component.AsyncService;
import com.xci.sys.core.Params;
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