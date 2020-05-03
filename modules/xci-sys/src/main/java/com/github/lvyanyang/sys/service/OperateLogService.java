/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.service;

import com.github.lvyanyang.component.AsyncService;
import com.github.lvyanyang.model.PageList;
import com.github.lvyanyang.sys.core.SysParams;
import com.github.lvyanyang.sys.dao.OperateLogDao;
import com.github.lvyanyang.sys.entity.SysOperateLog;
import com.github.lvyanyang.sys.filter.OperateLogFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.TimerTask;

/**
 * 操作日志服务
 * @author 吕艳阳
 */
@Service
public class OperateLogService {
    /** 操作日志数据层对象 */
    @Resource private OperateLogDao operateLogDao;

    /**
     * 新建操作日志
     * @param entity 操作日志实体
     */
    public void insert(SysOperateLog entity) {
        if (SysParams.SysLogEnableOperateLog.getBoolean()) {
            operateLogDao.insert(entity);
        }
    }

    /**
     * 新建操作日志(异步)
     * @param entity 操作日志实体
     */
    public void insertAsync(SysOperateLog entity) {
        if (SysParams.SysLogEnableOperateLog.getBoolean()) {
            AsyncService.me().execute(new TimerTask() {
                @Override
                public void run() {
                    operateLogDao.insert(entity);
                }
            });
        }
    }

    /**
     * 根据主键查询单个操作日志
     * @param id 操作日志主键
     * @return 返回操作日志实体
     */
    public SysOperateLog selectById(@NotNull(message = "请指定操作日志主键") Long id) {
        return operateLogDao.selectById(id);
    }

    /**
     * 查询操作日志分页列表
     * @param filter 过滤条件
     * @return 返回操作日志分页列表
     */
    public PageList<SysOperateLog> selectPageList(OperateLogFilter filter) {
        filter.PlusEndDateTime();
        return PageList.of(operateLogDao.selectPageList(filter));
    }
}