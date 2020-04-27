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
import com.github.lvyanyang.sys.dao.ParamDao;
import com.github.lvyanyang.sys.entity.SysParam;
import com.github.lvyanyang.sys.filter.ParamFilter;
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
 * 系统参数服务
 * @author 吕艳阳
 */
@Slf4j
@Service
public class ParamService extends BaseService {
    /** 参数数据访问对象 */
    @Resource private ParamDao paramDao;
    @Resource private Cache paramCache;

    @PostConstruct
    private void init() {
        refresh();
    }

    /**
     * 是否存在指定编码的参数
     * @param code      参数编码
     * @param excludeId 排除的主键,可为空
     * @return 如果存在返回true
     */
    public boolean existByCode(@NotBlank(message = "请指定参数编码") String code, Long excludeId) {
        return paramDao.existByCode(code, excludeId);
    }

    /**
     * 新建参数
     * @param entity 参数实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult insert(@Valid SysParam entity) {
        return SysService.me().paramService().save(true, entity);
    }

    /**
     * 修改参数
     * @param entity 参数实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult update(@Valid SysParam entity) {
        return SysService.me().paramService().save(false, entity);
    }

    /**
     * 批量保存参数
     * @param entities 参数实体集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void batchSave(@Valid List<SysParam> entities) {
        for (SysParam param : entities) {
            if (param.getId() == null || !paramDao.existById(param.getId())) {
                //无主键或者主键在数据库中不存在,则新增;
                SysService.me().paramService().save(true, param).ifFailThrow();
            } else {
                //其他情况,则修改
                SysService.me().paramService().save(false, param).ifFailThrow();
            }
        }
    }

    /**
     * 删除参数
     * @param ids 参数主键字符串
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NotBlank(message = "请指定参数主键字符串") String ids) {
        for (var id : XCI.splitToArray(ids)) {
            SysService.me().paramService().deleteCore(Long.valueOf(id));
        }
    }

    /**
     * 根据主键查询单个参数
     * @param id 参数主键
     * @return 返回参数实体
     */
    public SysParam selectById(@NotNull(message = "请指定参数主键") Long id) {
        return paramDao.selectById(id);
    }

    /**
     * 根据编码查询单个参数
     * @param code 参数编码
     * @return 返回参数实体
     */
    public SysParam selectByCode(@NotBlank(message = "请指定参数编码") String code) {
        return XCI.getCacheValue(paramCache, code, () -> paramDao.selectByCode(code));
    }

    /**
     * 根据编码查询单个参数值
     * @param code         参数编码
     * @param defaultValue 如果没有找到指定编码的参数,则返回此默认值
     * @return 返回参数对象
     */
    public String selectValueByCode(@NotBlank(message = "请指定参数编码") String code, String defaultValue) {
        var entity = selectByCode(code);
        if (entity == null || XCI.isBlank(entity.getValue())) {
            return defaultValue;
        }
        return entity.getValue();
    }

    /**
     * 查询参数列表
     * @param filter 过滤条件
     * @return 返回参数列表
     */
    public List<SysParam> selectList(ParamFilter filter) {
        return paramDao.selectList(filter);
    }

    /**
     * 查询参数分页列表
     * @param filter 过滤条件
     * @return 返回参数分页列表
     */
    public PageList<SysParam> selectPageList(ParamFilter filter) {
        return PageList.of(paramDao.selectPageList(filter));
    }

    /**
     * 刷新参数缓存
     */
    public void refresh() {
        paramCache.clear();
        paramDao.selectList(new ParamFilter()).forEach(p -> paramCache.put(p.getCode(), p));
        log.info("刷新系统参数缓存");
    }

    /**
     * 保存数据,在insert和update之前和之后的校验和处理
     * @param created 是否新建
     * @param entity  参数实体
     */
    RestResult save(boolean created, SysParam entity) {
        //新建时如果主键未指定,设置主键
        XCI.ifTrueAction(XCI.invalidId(created, entity.getId()), () -> entity.setId(XCI.nextId()));

        //如果简拼为空,设置简拼
        XCI.ifBlankAction(entity.getSpell(), () -> entity.setSpell(XCI.getSpell(entity.getName())));

        //检查参数编码是否存在
        if (paramDao.existByCode(entity.getCode(), XCI.excludeId(created, entity.getId()))) {
            return RestResult.fail(XCI.format("参数编码[{}]已经存在", entity.getCode()));
        }

        //更新数据库
        XCI.ifAction(created,
                () -> SysService.me().paramService().insertCore(entity),
                () -> SysService.me().paramService().updateCore(entity));
        return RestResult.ok();
    }

    /**
     * 内部新建参数,由save方法调用
     * @param entity 参数实体
     */
    @OperateLog(tag = R.Module.Param, msg = "新增参数", param = true)
    void insertCore(SysParam entity) {
        paramDao.insert(entity);
        paramCache.put(entity.getCode(), entity);
        SysService.me().saveInsertHistory(entity.getId(), entity);
    }

    /**
     * 内部修改参数,由_save方法调用
     * @param entity 参数实体
     */
    @OperateLog(tag = R.Module.Param, msg = "修改参数", param = true)
    void updateCore(SysParam entity) {
        var before = paramDao.selectById(entity.getId());
        paramDao.update(entity);
        paramCache.put(entity.getCode(), entity);
        SysService.me().saveUpdateHistory(entity.getId(), before, entity);
    }

    /**
     * 内部删除参数
     * @param id 参数主键
     */
    @OperateLog(tag = R.Module.Param, msg = "删除参数", param = true)
    void deleteCore(Long id) {
        var entity = paramDao.selectById(id);
        XCI.ifNullThrow(entity, () -> RestResult.fail(XCI.format("无效的参数主键:[{}]", id)));

        paramDao.deleteById(entity.getId());
        //清除当前删除的参数缓存
        paramCache.evict(entity.getCode());
        //保存删除历史日志
        SysService.me().saveDeleteHistory(entity.getId(), entity);
    }
}