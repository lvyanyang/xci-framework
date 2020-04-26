/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.service;

import com.github.lvyanyang.annotation.OperateLog;
import com.github.lvyanyang.annotation.Valid;
import com.github.lvyanyang.core.BaseService;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.model.PageList;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.dao.ReportDao;
import com.github.lvyanyang.sys.entity.SysReport;
import com.github.lvyanyang.sys.filter.ReportFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统报表服务
 * @author 吕艳阳
 */
@Slf4j
@Service
public class ReportService extends BaseService {
    /** 报表数据访问对象 */
    @Resource private ReportDao reportDao;
    @Resource private Cache reportCache;

    @PostConstruct
    private void init() {
        refresh();
    }

    /**
     * 是否存在指定编码的报表
     * @param code      报表编码
     * @param excludeId 排除的主键,可为空
     * @return 如果存在返回true
     */
    public boolean existByCode(@NotBlank(message = "请指定报表编码") String code, Long excludeId) {
        return reportDao.existByCode(code, excludeId);
    }

    /**
     * 新建报表
     * @param entity 报表实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult insert(@Valid SysReport entity) {
        return SysService.me().reportService().save(true, entity);
    }

    /**
     * 修改报表
     * @param entity 报表实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult update(@Valid SysReport entity) {
        return SysService.me().reportService().save(false, entity);
    }

    /**
     * 修改报表状态
     * @param ids    报表主键字符串
     * @param status 报表状态
     */
    @OperateLog(tag = R.Module.Report, msg = "修改报表状态", param = true, result = true)
    @Transactional(rollbackFor = Exception.class)
    public RestResult updateStatus(@NotBlank(message = "请指定报表主键") String ids,
                                   @NotNull(message = "请指定报表状态") Boolean status) {
        String[] idList = XCI.splitToArray(ids);
        for (String idStr : idList) {
            var id = Long.valueOf(idStr);
            var entity = reportDao.selectById(id);
            XCI.ifNullThrow(entity, () -> RestResult.fail(XCI.format("无效的报表主键:[{}]", id)));

            reportDao.updateStatus(id, status);
            reportCache.evict(entity.getCode());
        }
        return RestResult.ok();
    }

    /**
     * 删除报表
     * @param ids 报表主键字符串
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NotBlank(message = "请指定报表主键字符串") String ids) {
        for (var id : XCI.splitToArray(ids)) {
            SysService.me().reportService().deleteCore(Long.valueOf(id));
        }
    }

    /**
     * 根据主键查询单个报表
     * @param id 报表主键
     * @return 返回报表实体
     */
    public SysReport selectById(@NotNull(message = "请指定报表主键") Long id) {
        return reportDao.selectById(id);
    }

    /**
     * 根据编码查询单个报表
     * @param code 报表编码
     * @return 返回报表实体
     */
    public SysReport selectByCode(@NotBlank(message = "请指定报表编码") String code) {
        return XCI.getCacheValue(reportCache, code, () -> reportDao.selectByCode(code));
    }

    /**
     * 查询报表列表
     * @param filter 报表过滤实体
     * @return 返回报表列表
     */
    public List<SysReport> selectList(ReportFilter filter) {
        return reportDao.selectList(filter);
    }

    /**
     * 查询报表分页列表
     * @param filter 过滤条件
     * @return 返回报表分页列表
     */
    public PageList<SysReport> selectPageList(ReportFilter filter) {
        return PageList.of(reportDao.selectPageList(filter));
    }

    /**
     * 刷新报表缓存
     */
    public void refresh() {
        reportCache.clear();
        reportDao.selectList(new ReportFilter()).forEach(p -> reportCache.put(p.getCode(), p));
        log.info("刷新系统报表缓存");
    }

    /**
     * 保存数据,在insert和update之前和之后的校验和处理
     * @param created 是否新建
     * @param entity  报表实体
     */
    RestResult save(boolean created, SysReport entity) {
        //新建时如果主键未指定,设置主键
        XCI.ifTrueAction(XCI.invalidId(created, entity.getId()), () -> entity.setId(XCI.nextId()));

        //检查报表编码是否存在
        if (reportDao.existByCode(entity.getCode(), XCI.excludeId(created, entity.getId()))) {
            return RestResult.fail(XCI.format("报表编码[{}]已经存在", entity.getCode()));
        }

        //更新数据库
        XCI.ifAction(created,
                () -> SysService.me().reportService().insertCore(entity),
                () -> SysService.me().reportService().updateCore(entity));
        return RestResult.ok();
    }

    /**
     * 内部新建报表,由_save方法调用
     * @param entity 报表实体
     */
    @OperateLog(tag = R.Module.Report, msg = "新增报表", param = true)
    void insertCore(SysReport entity) {
        reportDao.insert(entity);
        reportCache.put(entity.getCode(), entity);
        SysService.me().saveInsertHistory(entity.getId(), entity);
    }

    /**
     * 内部修改报表,由_save方法调用
     * @param entity 报表实体
     */
    @OperateLog(tag = R.Module.Report, msg = "修改报表", param = true)
    void updateCore(SysReport entity) {
        var before = reportDao.selectById(entity.getId());
        reportDao.update(entity);
        reportCache.put(entity.getCode(), entity);
        SysService.me().saveUpdateHistory(entity.getId(), before, entity);
    }

    /**
     * 内部删除报表
     * @param id 报表主键
     */
    @OperateLog(tag = R.Module.Report, msg = "删除报表", param = true)
    void deleteCore(Long id) {
        var entity = reportDao.selectById(id);
        XCI.ifNullThrow(entity, () -> RestResult.fail(XCI.format("无效的报表主键:[{}]", id)));

        reportDao.deleteById(entity.getId());
        //清除当前删除的报表缓存
        reportCache.evict(entity.getCode());
        //保存删除历史日志
        SysService.me().saveDeleteHistory(entity.getId(), entity);
    }
}