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
import com.github.lvyanyang.model.Dic;
import com.github.lvyanyang.model.IdValue;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.dao.DicDao;
import com.github.lvyanyang.sys.entity.SysDic;
import com.github.lvyanyang.sys.filter.DicFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统字典服务
 * @author 吕艳阳
 */
@Slf4j
@Service
public class DicService extends BaseService {
    /** 字典数据层对象 */
    @Resource private DicDao dicDao;
    /** 字典缓存对象 */
    @Resource private Cache dicCache;

    /**
     * 检查字典类型编码是否存在
     * @param categoryCode 字典编码
     * @return 如果存在返回true
     */
    public boolean existByCategoryCode(@NotBlank(message = "请指定字典编码") String categoryCode) {
        return dicDao.existxByCategoryCode(categoryCode);
    }

    /**
     * 是否存在指定名称的字典
     * @param categoryCode 字典类型编码
     * @param name         字典名称
     * @param excludeId    排除的主键，如果为null则不指定排除的主键
     * @return 如果存在返回true
     */
    public boolean existByName(@NotBlank(message = "请指定字典类型编码") String categoryCode, @NotBlank(message = "请指定字典名称") String name, Long excludeId) {
        return dicDao.existxByName(categoryCode, name, excludeId);
    }

    /**
     * 新建字典
     * @param entity 字典实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult insert(@Valid SysDic entity) {
        return SysService.me().dicService().save(true, entity);
    }

    /**
     * 修改字典
     * @param entity 字典实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult update(@Valid SysDic entity) {
        return SysService.me().dicService().save(false, entity);
    }

    /**
     * 批量保存字典
     * @param entities 字典实体集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void batchSave(@Valid List<SysDic> entities) {
        for (SysDic dic : entities) {
            if (dic.getId() == null || !dicDao.existxById(dic.getId())) {
                //无主键或者主键在数据库中不存在,则新增;
                SysService.me().dicService().save(true, dic).ifFailThrow();
            } else {
                //其他情况,则修改
                SysService.me().dicService().save(false, dic).ifFailThrow();
            }
        }
    }

    /**
     * 更新字典类型编码
     * @param oldCategoryCode 原字典类型编码
     * @param newCategoryCode 新字典类型编码
     */
    @OperateLog(tag = R.Module.Dic, msg = "更新字典类型编码")
    @Transactional(rollbackFor = Exception.class)
    public RestResult updateCategoryCode(@NotBlank(message = "请指定原字典类型编码") String oldCategoryCode, @NotBlank(message = "请指定新字典类型编码") String newCategoryCode) {
        dicDao.updateCategoryCode(oldCategoryCode, newCategoryCode);
        dicCache.evict(oldCategoryCode);
        return RestResult.ok();
    }

    /**
     * 修改字典状态
     * @param ids    主键字符串
     * @param status 字典状态
     */
    @OperateLog(tag = R.Module.Dic, msg = "修改字典状态", param = true, result = true)
    @Transactional(rollbackFor = Exception.class)
    public RestResult updateStatus(@NotBlank(message = "请指定字典主键") String ids,
                                   @NotNull(message = "请指定字典状态") Boolean status) {
        String[] idList = XCI.splitToArray(ids);
        for (String idStr : idList) {
            var id = Long.valueOf(idStr);
            SysDic old = dicDao.selectById(id);
            XCI.ifNullThrow(old, () -> RestResult.fail(XCI.format("无效的字典主键:[{}]", id)));

            dicDao.updateStatus(old.getId(), status);
            dicCache.evict(old.getCategoryCode());
        }
        return RestResult.ok();
    }

    /**
     * 根据主键修改字典父节点
     * @param IdValues 修改集合
     */
    @OperateLog(tag = R.Module.Dic, msg = "根据主键修改字典父节点")
    @Transactional(rollbackFor = Exception.class)
    public RestResult updateParentId(List<IdValue> IdValues) {
        for (IdValue idv : IdValues) {
            var parentId = XCI.toLong(idv.getValue(), -9999L);
            if (parentId == -9999L) continue;
            dicDao.updateParentId(idv.getId(), parentId);
        }
        refreshCache(IdValues);
        return RestResult.ok();
    }

    /**
     * 根据主键修改字典排序路径
     * @param IdValues 修改集合
     */
    @OperateLog(tag = R.Module.Dic, msg = "根据主键修改字典排序路径")
    @Transactional(rollbackFor = Exception.class)
    public RestResult updatePath(List<IdValue> IdValues) {
        for (IdValue idv : IdValues) {
            var path = XCI.toInt(idv.getValue(), -9999);
            if (path == -9999) continue;
            dicDao.updatePath(idv.getId(), path);
        }
        refreshCache(IdValues);
        return RestResult.ok();
    }

    /**
     * 删除字典
     * @param ids 字典主键字符串
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NotBlank(message = "请指定字典主键字符串") String ids) {
        for (var id : XCI.splitToArray(ids)) {
            SysService.me().dicService().deleteCore(Long.valueOf(id));
        }
    }

    /**
     * 根据字典类型编码删除
     * @param categoryCode 字典类型编码
     * @return 返回影响的行数
     */
    @OperateLog(tag = R.Module.Dic, msg = "根据字典类型编码删除")
    @Transactional(rollbackFor = Exception.class)
    public RestResult deleteByCategoryCode(@NotBlank(message = "请指定字典类型编码") String categoryCode) {
        dicDao.deleteByCategoryCode(categoryCode);
        dicCache.evict(categoryCode);
        return RestResult.ok();
    }

    /**
     * 根据主键查询单个字典
     * @param id 字典主键
     * @return 返回字典实体
     */
    public SysDic selectById(@NotNull(message = "请指定字典主键") Long id) {
        return dicDao.selectById(id);
    }

    /**
     * 查询字典列表
     * @param filter 过滤条件
     * @return 返回字典列表
     */
    public List<SysDic> selectList(DicFilter filter) {
        List<SysDic> list = dicDao.selectList(filter);
        if (XCI.isBlank(filter.getName())) {
            return list;
        }

        //如果指定了按名称过滤,那么先按状态和权限查询出所有记录,然后用stream进行过滤,过滤之后调用filterTree,把每个匹配项的所有父节点都查询出来
        String name = filter.getName();
        var result = list.stream().filter(p -> p.getName().toLowerCase().contains(name)
                || (p.getValue() != null && p.getValue().toLowerCase().contains(name))
                || (p.getSpell() != null && p.getSpell().toLowerCase().contains(name)))
                .sorted(Comparator.comparing(SysDic::getPath)).collect(Collectors.toList());
        return (List<SysDic>) XCI.filterTree(list, result);
    }

    /**
     * 根据类型编码查询字典列表(从缓存)
     * @param categoryCode 字典类型编码
     * @return 返回字典列表
     */
    public List<Dic> selectListByCategoryCode(@NotBlank(message = "请指定字典类型编码") String categoryCode) {
        return XCI.getCacheValue(dicCache, categoryCode, () ->
                toDicItemList(dicDao.selectList(DicFilter.builder().categoryCode(categoryCode).status(true).build())));
    }

    /**
     * 获取指定节点的直接子节点数
     * @param categoryCode 字典类型编码
     * @param id           主键
     * @return 返回直接子节点数量
     */
    public Integer selectChildCount(@NotBlank(message = "请指定字典类型编码") String categoryCode, @NotNull(message = "请指定字典主键") Long id) {
        return dicDao.selectChildCount(categoryCode, id);
    }

    private void refreshCache(List<IdValue> IdValues) {
        if (IdValues.size() == 0) return;

        var item = IdValues.get(0);
        var dic = dicDao.selectById(item.getId());
        if (dic != null) {
            dicCache.evict(dic.getCategoryCode());
        }
    }

    /**
     * 重新加载字典缓存
     */
    public void refresh() {
        dicCache.clear();
        DicFilter filter = new DicFilter();
        filter.setStatus(true);
        List<SysDic> dicList = dicDao.selectList(filter);
        Map<String, List<SysDic>> categoryMap = dicList.stream().collect(Collectors.groupingBy(SysDic::getCategoryCode));
        for (Map.Entry<String, List<SysDic>> category : categoryMap.entrySet()) {
            String code = category.getKey();
            List<SysDic> list = dicList.parallelStream().filter(p -> p.getCategoryCode().equals(code)).collect(Collectors.toList());
            // .sorted(Comparator.comparing(SysDic::getPath))
            dicCache.put(code, toDicItemList(list));
        }
        log.info("重新加载系统字典缓存");
    }

    /**
     * 保存数据,在insert和update之前和之后的校验和处理
     * @param created 是否新建
     * @param entity  字典实体
     */
    RestResult save(boolean created, SysDic entity) {
        //新建时如果主键未指定,设置主键
        XCI.ifTrueAction(XCI.invalidId(created, entity.getId()), () -> entity.setId(XCI.nextId()));

        //如果简拼为空,设置简拼
        XCI.ifBlankAction(entity.getSpell(), () -> entity.setSpell(XCI.getSpell(entity.getName())));

        //设置父节点默认值
        XCI.ifNullAction(entity.getParentId(), () -> entity.setParentId(R.ROOT_NODE_ID));

        //todo:检测:修改时,上级节点不能是自己及所有下级节点
        if (entity.getParentId().equals(entity.getId())) {
            return RestResult.fail("上级不能是自己");
        }

        //检查字典名称是否存在
        if (dicDao.existxByName(entity.getCategoryCode(), entity.getName(), XCI.excludeId(created, entity.getId()))) {
            return RestResult.fail(XCI.format("字典名称[{}]已经存在", entity.getName()));
        }

        //更新数据库
        XCI.ifAction(created,
                () -> SysService.me().dicService().insertCore(entity),
                () -> SysService.me().dicService().updateCore(entity));
        return RestResult.ok();
    }

    /**
     * 内部新建字典,由_save方法调用
     * @param entity 字典实体
     */
    @OperateLog(tag = R.Module.Dic, msg = "新增字典", param = true)
    void insertCore(SysDic entity) {
        entity.setPath(dicDao.selectChildCount(entity.getCategoryCode(), entity.getParentId()));//自动获取序号
        dicDao.insert(entity);
        dicCache.evict(entity.getCategoryCode());
        SysService.me().saveInsertHistory(entity.getId(), entity);
    }

    /**
     * 内部修改字典,由_save方法调用
     * @param entity 字典实体
     */
    @OperateLog(tag = R.Module.Dic, msg = "修改字典", param = true)
    void updateCore(SysDic entity) {
        var before = dicDao.selectById(entity.getId());
        dicDao.update(entity);
        dicCache.evict(before.getCategoryCode());
        SysService.me().saveUpdateHistory(entity.getId(), before, entity);
    }

    /**
     * 内部删除字典
     * @param id 字典主键
     */
    @OperateLog(tag = R.Module.Dic, msg = "删除字典", param = true)
    void deleteCore(Long id) {
        SysDic entity = dicDao.selectById(id);
        XCI.ifNullThrow(entity, () -> RestResult.fail(XCI.format("无效的字典主键:[{}]", id)));

        if (dicDao.selectChildCount(entity.getCategoryCode(), id) > 0)
            XCI.appThrow(XCI.format("字典[{}]存在下级字典,不允许删除", entity.getName()));

        dicDao.deleteById(id);                          //数据库删除
        dicCache.evict(entity.getCategoryCode());       //移除缓存
        SysService.me().saveDeleteHistory(id, entity);  //保存历史日志
    }

    List<Dic> toDicItemList(List<SysDic> list) {
        var dics = new ArrayList<Dic>(list.size());
        for (SysDic item : list) {
            dics.add(new Dic(item.getId(), item.getParentId(), item.getName(), item.getSpell(), item.getValue()));
        }
        return dics;
    }
}