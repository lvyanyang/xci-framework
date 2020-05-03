/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.service;

import com.github.lvyanyang.component.AsyncService;
import com.github.lvyanyang.model.PageList;
import com.github.lvyanyang.sys.core.SysParams;
import com.github.lvyanyang.sys.dao.LoginLogDao;
import com.github.lvyanyang.sys.entity.SysLoginLog;
import com.github.lvyanyang.sys.filter.LoginLogFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.TimerTask;

/**
 * 系统登陆日志服务
 * @author 吕艳阳
 */
@Service
public class LoginLogService {
    /** 登陆日志数据层对象 */
    @Resource private LoginLogDao loginLogDao;

    /**
     * 新建登陆日志
     * @param entity 登陆日志实体
     */
    public void insert(SysLoginLog entity) {
        if (SysParams.SysLogEnableLoginLog.getBoolean()){
            loginLogDao.insert(entity);
        }
    }

    /**
     * 新建登陆日志(异步)
     * @param entity 登陆日志实体
     */
    public void insertAsync(SysLoginLog entity) {
        if (SysParams.SysLogEnableLoginLog.getBoolean()) {
            AsyncService.me().execute(new TimerTask() {
                @Override
                public void run() {
                    loginLogDao.insert(entity);
                }
            });
        }
    }

    /**
     * 根据主键查询单个登录日志
     * @param id 登陆日志主键
     * @return 返回登陆日志实体
     */
    public SysLoginLog selectById(@NotNull(message = "请指定登陆日志主键") Long id) {
        return loginLogDao.selectById(id);
    }

    /**
     * 查询登陆日志分页列表
     * @param filter 过滤条件
     * @return 返回登陆日志分页列表
     */
    public PageList<SysLoginLog> selectPageList(LoginLogFilter filter) {
        filter.PlusEndDateTime();
        return PageList.of(loginLogDao.selectPageList(filter));
    }
}