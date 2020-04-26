package com.xci.sys.service;


import com.xci.component.AsyncService;
import com.xci.model.HistoryInfo;
import com.xci.sys.dao.HistoryLogDao;
import com.xci.model.PageList;
import com.xci.core.R;
import com.xci.core.XCI;
import com.xci.sys.entity.SysHistoryLog;
import com.xci.sys.filter.HistoryLogFilter;
import com.xci.sys.core.Params;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.TimerTask;

/**
 * 系统历史日志服务
 * @author 吕艳阳
 */
@Service
public class HistoryLogService {
    /** 历史记录数据层对象 */
    @Resource private HistoryLogDao historyDao;

    /**
     * 保存历史日志
     * @param history 历史日志实体
     */
    public void insert(HistoryInfo history) {
        if (Params.enableHistoryLog()) {
            SysHistoryLog entity = buildHistory(history);
            historyDao.insert(entity);
        }
    }

    /**
     * 新建历史日志(异步)
     * @param history 历史日志实体
     */
    public void insertAsync(HistoryInfo history) {
        if (Params.enableHistoryLog()) {
            SysHistoryLog entity = buildHistory(history);
            AsyncService.me().execute(new TimerTask() {
                @Override
                public void run() {
                    historyDao.insert(entity);
                }
            });
        }
    }

    /**
     * 根据主键查询单个历史日志
     * @param id 历史日志主键
     * @return 返回历史日志实体
     */
    public SysHistoryLog selectById(@NotNull(message = "请指定历史日志主键") Long id) {
        return historyDao.selectById(id);
    }

    /**
     * 查询历史日志分页列表
     * @param filter 过滤条件
     * @return 返回历史日志分页列表
     */
    public PageList<SysHistoryLog> selectPageList(HistoryLogFilter filter) {
        filter.splitCategory();
        filter.PlusEndDateTime();
        return PageList.of(historyDao.selectPageList(filter));
    }

    /**
     * 生成历史日志对象
     */
    private SysHistoryLog buildHistory(HistoryInfo history) {
        SysHistoryLog entity = new SysHistoryLog();
        entity.setId(XCI.nextId());
        entity.setPrimaryKey(history.getPk().toString());
        entity.setBeforeData(XCI.toJsonString(history.getBefore(), true));
        entity.setAfterData(XCI.toJsonString(history.getAfter(), true));
        SysService.me().setOperateUserInfo(entity);

        //1新建 2修改 3删除
        switch (history.getType()) {
            case Insert:
                entity.setCategory(1);
                if (history.getAfter() != null) {
                    entity.setTableName(history.getAfter().getClass().getSimpleName());
                }
                break;
            case Update:
                entity.setCategory(2);
                if (history.getBefore() != null) {
                    entity.setTableName(history.getBefore().getClass().getSimpleName());
                }
                if (entity.getTableName() != null && history.getAfter() != null) {
                    entity.setTableName(history.getAfter().getClass().getSimpleName());
                }
                if (history.getBefore() != null && history.getAfter() != null) {
                    var diffList = XCI.contrast(history.getBefore(), history.getAfter());
                    if(XCI.isNotEmpty(diffList)){
                        entity.setDiff(XCI.toJsonString(diffList,true));
                    }
                }
                break;
            case Delete:
                entity.setCategory(3);
                if (history.getBefore() != null) {
                    entity.setTableName(history.getBefore().getClass().getSimpleName());
                }
                break;
        }
        if (XCI.isBlank(entity.getTableName())) {
            entity.setTableName(R.Empty);
        }
        if (XCI.isBlank(entity.getPrimaryKey())) {
            entity.setPrimaryKey(R.Empty);
        }

        return entity;
    }
}