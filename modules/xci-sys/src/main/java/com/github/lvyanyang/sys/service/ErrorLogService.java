/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.service;

import com.github.lvyanyang.component.AsyncService;
import com.github.lvyanyang.model.PageList;
import com.github.lvyanyang.sys.core.SysParams;
import com.github.lvyanyang.sys.dao.ErrorLogDao;
import com.github.lvyanyang.sys.entity.SysErrorLog;
import com.github.lvyanyang.sys.filter.ErrorLogFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.TimerTask;

/**
 * 系统错误日志服务
 * @author 吕艳阳
 */
@Service
public class ErrorLogService {
    /** 错误日志数据层对象 */
    @Resource private ErrorLogDao errorLogDao;

    /**
     * 新建错误日志
     * @param entity 错误日志对象
     */
    public void insert(SysErrorLog entity) {
        if (SysParams.SysLogEnableErrorLog.getBoolean()){
            errorLogDao.insert(entity);
        }
    }

    /**
     * 新建错误日志(异步)
     * @param entity 错误日志实体
     */
    public void insertAsync(SysErrorLog entity) {
        if (SysParams.SysLogEnableErrorLog.getBoolean()){
            AsyncService.me().execute(new TimerTask() {
                @Override
                public void run() {
                    errorLogDao.insert(entity);
                }
            });
        }
    }

    /**
     * 根据主键查询单个错误日志
     * @param id 错误日志主键
     * @return 返回错误日志实体
     */
    public SysErrorLog selectById(@NotNull(message = "请指定日志主键") Long id) {
        return errorLogDao.selectById(id);
    }

    /**
     * 查询错误日志分页列表
     * @param filter 过滤条件
     * @return 返回错误日志分页列表
     */
    public PageList<SysErrorLog> selectPageList(ErrorLogFilter filter) {
        filter.PlusEndDateTime();
        return PageList.of(errorLogDao.selectPageList(filter));
    }
}