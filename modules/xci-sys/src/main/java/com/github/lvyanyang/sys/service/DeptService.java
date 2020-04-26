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
import com.github.lvyanyang.exceptions.AppException;
import com.github.lvyanyang.model.IdValue;
import com.github.lvyanyang.sys.annotation.DataScope;
import com.github.lvyanyang.sys.dao.DeptDao;
import com.github.lvyanyang.sys.entity.SysDept;
import com.github.lvyanyang.sys.entity.SysUser;
import com.github.lvyanyang.sys.filter.DeptFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统机构服务
 * @author 吕艳阳
 */
@Slf4j
@Service
public class DeptService extends BaseService {
    /** 机构数据层对象 */
    @Resource private DeptDao deptDao;
    /** 机构缓存对象 */
    @Resource private Cache deptCache;

    @PostConstruct
    private void init() {
        refresh();
    }

    /**
     * 是否存在指定编码的机构
     * @param code      机构编码
     * @param excludeId 排除的主键,可为空
     * @return 如果存在返回true
     */
    public boolean existByCode(@NotBlank(message = "请指定机构编码") String code, Long excludeId) {
        return deptDao.existByCode(code, excludeId);
    }

    /**
     * 是否存在指定名称的机构
     * @param parentId  机构上级主键
     * @param name      机构名称
     * @param excludeId 排除的主键，如果为null则不指定排除的主键
     * @return 如果存在返回true
     */
    public boolean existByName(@NotBlank(message = "请指定机构名称") String name,
                               @NotNull(message = "请指定机构上级主键") Long parentId, Long excludeId) {
        return deptDao.existByName(name, parentId, excludeId);
    }

    /**
     * 新建机构
     * @param entity 机构实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult insert(@Valid SysDept entity) {
        return SysService.me().deptService().save(true, entity);
    }

    /**
     * 修改机构
     * @param entity 机构实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult update(@Valid SysDept entity) {
        return SysService.me().deptService().save(false, entity);
    }

    /**
     * 根据主键更新机构状态
     * @param ids    主键字符串
     * @param status 机构状态
     */
    @OperateLog(tag = R.Module.Dept, msg = "根据主键更新机构状态")
    @Transactional(rollbackFor = Exception.class)
    public RestResult updateStatus(@NotBlank(message = "请指定主键字符串") String ids,
                                   @NotNull(message = "请指定机构状态") Boolean status) {
        String[] idList = XCI.splitToArray(ids);
        for (String idStr : idList) {
            var id = Long.valueOf(idStr);
            deptDao.updateStatus(id, status);
            deptCache.evict(id);
        }
        return RestResult.ok();
    }

    /**
     * 根据主键修改机构父节点
     * @param IdValues 修改集合
     */
    @OperateLog(tag = R.Module.Dept, msg = "根据主键修改机构父节点")
    @Transactional(rollbackFor = Exception.class)
    public RestResult updateParentId(List<IdValue> IdValues) {
        for (IdValue idv : IdValues) {
            var id = idv.getId();
            var parentId = XCI.toLong(idv.getValue(), -9999L);
            if (parentId == -9999L) continue;
            deptDao.updateParentId(idv.getId(), parentId);
            deptCache.evict(id);
        }
        return RestResult.ok();
    }

    /**
     * 根据主键修改机构排序路径
     * @param IdValues 修改集合
     */
    @OperateLog(tag = R.Module.Dept, msg = "根据主键修改机构排序路径")
    @Transactional(rollbackFor = Exception.class)
    public RestResult updatePath(List<IdValue> IdValues) {
        for (IdValue idv : IdValues) {
            var id = idv.getId();
            var path = XCI.toInt(idv.getValue(), -9999);
            if (path == -9999) continue;
            deptDao.updatePath(idv.getId(), path);
            deptCache.evict(id);
        }
        return RestResult.ok();
    }

    /**
     * 删除机构
     * @param ids 机构主键字符串
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NotBlank(message = "请指定机构主键字符串") String ids) {
        for (var id : XCI.splitToArray(ids)) {
            SysService.me().deptService().deleteCore(Long.valueOf(id));
        }
    }

    /**
     * 根据主键查询单个机构
     * @param id 机构主键
     * @return 返回机构实体
     */
    public SysDept selectById(@NotNull(message = "请指定机构主键") Long id) {
        return XCI.getCacheValue(deptCache, id, () -> deptDao.selectById(id));
    }

    /**
     * 根据编码查询单个机构
     * @param code 机构编码
     * @return 返回机构实体
     */
    public SysDept selectByCode(@NotBlank(message = "请指定机构编码") String code) {
        return deptDao.selectByCode(code);
    }

    /**
     * 获取用户所属机构
     * @param user 用户对象
     */
    public SysDept selectByUserId(SysUser user) {
        if (user != null && XCI.validLong(user.getDeptId())) {
            return selectById(user.getDeptId());
        }
        return null;
    }

    /**
     * 查询机构列表
     * @param filter 过滤条件
     * @return 返回机构列表
     */
    @DataScope
    public List<SysDept> selectList(DeptFilter filter) {
        List<SysDept> list = deptDao.selectList(filter);
        if (XCI.isBlank(filter.getName())) {
            return list;
        }

        //如果指定了按名称过滤,那么先按状态和权限查询出所有记录,然后用stream进行过滤,过滤之后调用filterTree,把每个匹配项的所有父节点都查询出来
        String name = filter.getName();
        var result = list.stream().filter(p -> p.getName().toLowerCase().contains(name)
                || (p.getCode() != null && p.getCode().toLowerCase().contains(name))
                || (p.getSpell() != null && p.getSpell().toLowerCase().contains(name)))
                .sorted(Comparator.comparing(SysDept::getPath)).collect(Collectors.toList());
        return (List<SysDept>) XCI.filterTree(list, result);
    }

    /**
     * 获取指定节点的直接子节点数
     * @param id 主键
     * @return 返回直接子节点数量
     */
    public Integer selectChildCount(@NotNull(message = "请指定机构主键") Long id) {
        return deptDao.selectChildCount(id);
    }

    /**
     * 刷新机构缓存
     */
    public void refresh() {
        deptCache.clear();
        var list = deptDao.selectList(new DeptFilter());
        list.forEach(p -> deptCache.put(p.getId(), p));
        log.info("刷新系统机构缓存");
    }

    /**
     * 保存数据,在insert和update之前和之后的校验和处理
     * @param created 是否新建
     * @param entity  机构实体
     */
    RestResult save(boolean created, SysDept entity) {
        //新建时如果主键未指定,设置主键
        XCI.ifTrueAction(XCI.invalidId(created, entity.getId()), () -> entity.setId(XCI.nextId()));

        //如果简拼为空,设置简拼
        XCI.ifBlankAction(entity.getSpell(), () -> entity.setSpell(XCI.getSpell(entity.getName())));

        //设置父节点默认值
        XCI.ifNullAction(entity.getParentId(), () -> entity.setParentId(R.ROOT_NODE_ID));

        //todo:检测:修改时,上级节点不能是自己及所有下级节点
        if (entity.getParentId().equals(entity.getId())) {
            return RestResult.fail("上级机构不能是自己");
        }

        var currentUser = SysService.me().getCurrentUser();
        if (!created && !currentUser.getAdmin() && entity.getId().equals(
                currentUser.getDeptId())) {
            //修改数据时,当前用户不能修改自己所在的部门,只有上级账号才能修改(管理员除外)
            return RestResult.fail("不能修改自己的所属部门,只有上级机构才能修改");
        }

        //检查机构编码是否存在
        if (deptDao.existByCode(entity.getCode(), XCI.excludeId(created, entity.getId()))) {
            return RestResult.fail(XCI.format("机构编码[{}]已经存在", entity.getCode()));
        }

        //检查机构名称是否存在
        if (deptDao.existByName(entity.getName(), entity.getParentId(), XCI.excludeId(created, entity.getId()))) {
            return RestResult.fail(XCI.format("机构名称[{}]已经存在", entity.getName()));
        }

        //更新数据库
        XCI.ifAction(created, () -> SysService.me().deptService().insertCore(entity),
                () -> SysService.me().deptService().updateCore(entity));
        return RestResult.ok();
    }

    /**
     * 内部新建机构,由_save方法调用
     * @param entity 机构实体
     */
    @OperateLog(tag = R.Module.Dept, msg = "新增机构", param = true)
    void insertCore(SysDept entity) {
        entity.setPath(deptDao.selectChildCount(entity.getParentId()));//自动获取序号
        deptDao.insert(entity);
        deptCache.put(entity.getId(), entity);
        SysService.me().saveInsertHistory(entity.getId(), entity);
    }

    /**
     * 内部修改机构,由_save方法调用
     * @param entity 机构实体
     */
    @OperateLog(tag = R.Module.Dept, msg = "修改机构", param = true)
    void updateCore(SysDept entity) {
        var before = deptDao.selectById(entity.getId());
        deptDao.update(entity);
        deptCache.put(entity.getId(), entity);
        SysService.me().saveUpdateHistory(entity.getId(), before, entity);
    }

    /**
     * 内部删除机构
     * @param id 机构主键
     */
    @OperateLog(tag = R.Module.Dept, msg = "删除机构", param = true)
    void deleteCore(Long id) {
        SysDept entity = deptDao.selectById(id);
        XCI.ifNullThrow(entity, () -> RestResult.fail(XCI.format("无效的机构主键:[{}]", id)));

        SysUser currentUser = SysService.me().getCurrentUser();
        if (!currentUser.getAdmin() && entity.getId().equals(currentUser.getDeptId()))//管理员除外
            XCI.appThrow(XCI.format("机构[{}]删除失败,不允许删除当前用户所在机构,必须由上级机构账号来操作", entity.getName()));

        if (deptDao.selectChildCount(id) > 0) throw new AppException(XCI.format("机构[{}]存在下级机构,不允许删除", entity.getName()));

        if (SysService.me().roleService().existByDeptId(id)) {
            //检测是否被角色引用
            XCI.appThrow(XCI.format("角色中引用了机构[{}],不允许删除", entity.getName()));
        }

        if (SysService.me().userService().existByDeptId(id)) {
            //检测是否被用户引用
            XCI.appThrow(XCI.format("用户中引用了机构:[{}],不允许删除", entity.getName()));
        }

        deptDao.deleteById(id, XCI.nextId());
        deptCache.evict(entity.getId());
        SysService.me().saveDeleteHistory(id, entity);
    }
}