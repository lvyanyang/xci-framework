package com.xci.sys.service;

import com.xci.component.AsyncService;
import com.xci.sys.dao.LoginLogDao;
import com.xci.sys.entity.SysLoginLog;
import com.xci.model.PageList;
import com.xci.sys.filter.LoginLogFilter;
import com.xci.sys.core.Params;
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
        if (Params.enableLoginLog()){
            loginLogDao.insert(entity);
        }
    }

    /**
     * 新建登陆日志(异步)
     * @param entity 登陆日志实体
     */
    public void insertAsync(SysLoginLog entity) {
        if (Params.enableLoginLog()) {
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