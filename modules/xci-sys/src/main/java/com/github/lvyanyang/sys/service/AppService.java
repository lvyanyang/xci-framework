/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.service;

import com.github.lvyanyang.annotation.OperateLog;
import com.github.lvyanyang.annotation.Valid;
import com.github.lvyanyang.component.AsyncService;
import com.github.lvyanyang.core.BaseService;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.model.PageList;
import com.github.lvyanyang.sys.dao.AppDao;
import com.github.lvyanyang.sys.entity.SysApp;
import com.github.lvyanyang.sys.filter.AppFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.TimerTask;

/**
 * 系统应用服务
 * @author 吕艳阳
 */
@Slf4j
@Service
public class AppService extends BaseService {
    /** 应用数据访问对象 */
    @Resource private AppDao appDao;
    @Resource private Cache appCache;

    @PostConstruct
    private void init() {
        refresh();
    }

    /**
     * 是否存在指定名称的应用
     * @param code      应用编码
     * @param excludeId 排除的主键,可为空
     * @return 如果存在返回true
     */
    public boolean existByName(@NotBlank(message = "请指定应用编码") String code, Long excludeId) {
        return appDao.existByName(code, excludeId);
    }

    /**
     * 新建应用
     * @param entity 应用实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult insert(@Valid SysApp entity) {
        return SysService.me().appService().save(true, entity);
    }

    /**
     * 修改应用
     * @param entity 应用实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult update(@Valid SysApp entity) {
        return SysService.me().appService().save(false, entity);
    }

    /**
     * 修改应用状态
     * @param ids    应用主键字符串
     * @param status 应用状态
     */
    @OperateLog(tag = R.Module.App, msg = "修改应用状态", param = true, result = true)
    @Transactional(rollbackFor = Exception.class)
    public RestResult updateStatus(@NotBlank(message = "请指定应用主键") String ids,
                                   @NotNull(message = "请指定应用状态") Boolean status) {
        String[] idList = XCI.splitToArray(ids);
        for (String idStr : idList) {
            var id = Long.valueOf(idStr);
            appDao.updateStatus(id, status);
            appCache.evict(id);
        }
        return RestResult.ok();
    }

    /**
     * 删除应用
     * @param ids 应用主键字符串
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NotBlank(message = "请指定应用主键字符串") String ids) {
        for (var id : XCI.splitToArray(ids)) {
            SysService.me().appService().deleteCore(Long.valueOf(id));
        }
    }

    /**
     * 根据主键查询单个应用
     * @param id 应用主键
     * @return 返回应用实体
     */
    public SysApp selectById(@NotNull(message = "请指定应用主键") Long id) {
        return XCI.getCacheValue(appCache, id, () -> appDao.selectById(id));
    }

    /**
     * 查询应用列表
     * @param filter 过滤条件
     * @return 返回应用列表
     */
    public List<SysApp> selectList(AppFilter filter) {
        return appDao.selectList(filter);
    }

    /**
     * 查询应用分页列表
     * @param filter 过滤条件
     * @return 返回应用分页列表
     */
    public PageList<SysApp> selectPageList(AppFilter filter) {
        return PageList.of(appDao.selectPageList(filter));
    }

    /**
     * 刷新应用缓存
     */
    public void refresh() {
        appCache.clear();
        appDao.selectList(new AppFilter()).forEach(p -> appCache.put(p.getId(), p));
        log.info("刷新系统应用缓存");
    }

    /**
     * 保存数据,在insert和update之前和之后的校验和处理
     * @param created 是否新建
     * @param entity  应用实体
     */
    RestResult save(boolean created, SysApp entity) {
        //新建时如果主键未指定,设置主键
        XCI.ifTrueAction(XCI.invalidId(created, entity.getId()), () -> entity.setId(XCI.nextId()));

        //检查应用名称是否存在
        if (appDao.existByName(entity.getName(), XCI.excludeId(created, entity.getId()))) {
            return RestResult.fail(XCI.format("应用名称[{}]已经存在", entity.getName()));
        }

        //更新数据库
        XCI.ifAction(created, () -> SysService.me().appService().insertCore(entity), () -> SysService.me().appService().updateCore(entity));
        return RestResult.ok();
    }

    /**
     * 内部新建应用,由_save方法调用
     * @param entity 应用实体
     */
    @OperateLog(tag = R.Module.App, msg = "新增应用", param = true)
    void insertCore(SysApp entity) {
        appDao.insert(entity);
        appCache.put(entity.getId(), entity);
        SysService.me().saveInsertHistory(entity.getId(), entity);
    }

    /**
     * 内部修改应用,由_save方法调用
     * @param entity 应用实体
     */
    @OperateLog(tag = R.Module.App, msg = "修改应用", param = true)
    void updateCore(SysApp entity) {
        var before = appDao.selectById(entity.getId());
        appDao.update(entity);
        appCache.put(entity.getId(), entity);
        SysService.me().saveUpdateHistory(entity.getId(), before, entity);
    }

    /**
     * 内部删除应用
     * @param id 应用主键
     */
    @OperateLog(tag = R.Module.App, msg = "删除应用", param = true)
    void deleteCore(Long id) {
        var entity = appDao.selectById(id);
        XCI.ifNullThrow(entity, () -> RestResult.fail(XCI.format("无效的应用主键:[{}]", id)));

        appDao.deleteById(entity.getId());
        //清除当前删除的应用缓存
        appCache.evict(entity.getId());
        //保存删除历史日志
        SysService.me().saveDeleteHistory(entity.getId(), entity);
    }
}