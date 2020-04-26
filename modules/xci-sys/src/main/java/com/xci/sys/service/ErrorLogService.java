package com.xci.sys.service;

import com.xci.component.AsyncService;
import com.xci.sys.dao.ErrorLogDao;
import com.xci.sys.entity.SysErrorLog;
import com.xci.model.PageList;
import com.xci.sys.filter.ErrorLogFilter;
import com.xci.sys.core.Params;
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
        if (Params.enableErrorLog()){
            errorLogDao.insert(entity);
        }
    }

    /**
     * 新建错误日志(异步)
     * @param entity 错误日志实体
     */
    public void insertAsync(SysErrorLog entity) {
        if (Params.enableErrorLog()){
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