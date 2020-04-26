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
import com.github.lvyanyang.model.PermissionBody;
import com.github.lvyanyang.sys.annotation.DataScope;
import com.github.lvyanyang.sys.dao.RoleDao;
import com.github.lvyanyang.sys.entity.SysDept;
import com.github.lvyanyang.sys.entity.SysModule;
import com.github.lvyanyang.sys.entity.SysRole;
import com.github.lvyanyang.sys.entity.SysUser;
import com.github.lvyanyang.sys.filter.RoleFilter;
import com.google.common.collect.Lists;
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
 * 系统角色服务
 * @author 吕艳阳
 */
@Slf4j
@Service
public class RoleService extends BaseService {
    /** 角色数据访问对象 */
    @Resource private RoleDao roleDao;
    /** 角色缓存对象 */
    @Resource private Cache roleCache;

    @PostConstruct
    private void init() {
        refresh();
    }

    /**
     * 是否存在指定机构主键的角色
     * @param deptId 机构主键
     * @return 如果存在返回true, 否则返回false
     */
    public Boolean existByDeptId(@NotNull(message = "请指定机构主键") Long deptId) {
        return roleDao.existByDeptId(deptId);
    }

    /**
     * 是否存在指定编码的角色
     * @param code      角色编码
     * @param excludeId 排除的主键,可为空
     * @return 如果存在返回true
     */
    public Boolean existByCode(@NotBlank(message = "请指定角色编码") String code, Long excludeId) {
        return roleDao.existByCode(code, excludeId);
    }

    /**
     * 是否存在指定名称的角色
     * @param code      角色编码
     * @param deptId    机构主键
     * @param excludeId 排除的主键,可为空
     * @return 如果存在返回true
     */
    public boolean existByName(@NotBlank(message = "请指定角色编码") String code,
                               @NotNull(message = "请指定机构主键") Long deptId, Long excludeId) {
        return roleDao.existByName(code, deptId, excludeId);
    }

    /**
     * 新建角色
     * @param entity 角色实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult insert(@Valid SysRole entity) {
        return SysService.me().roleService().save(true, entity);
    }

    /**
     * 修改角色
     * @param entity 角色实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult update(@Valid SysRole entity) {
        return SysService.me().roleService().save(false, entity);
    }

    /**
     * 批量保存角色
     * @param entities 角色实体集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void batchSave(@Valid List<SysRole> entities) {
        for (SysRole entity : entities) {
            if (entity.getId() == null || !roleDao.existById(entity.getId())) {
                //无主键或者主键在数据库中不存在,则新增;
                SysService.me().roleService().save(true, entity).ifFailThrow();
            } else {
                //其他情况,则修改
                SysService.me().roleService().save(false, entity).ifFailThrow();
            }
        }
    }

    /**
     * 修改角色状态
     * @param ids    角色主键字符串
     * @param status 角色状态
     */
    @OperateLog(tag = R.Module.Role, msg = "修改角色状态", param = true, result = true)
    @Transactional(rollbackFor = Exception.class)
    public RestResult updateStatus(@NotBlank(message = "请指定角色主键") String ids,
                                   @NotNull(message = "请指定角色状态") Boolean status) {
        String[] idList = XCI.splitToArray(ids);
        for (String idStr : idList) {
            var id = Long.valueOf(idStr);
            roleDao.updateStatus(id, status);
            roleCache.evict(id);
        }
        return RestResult.ok();
    }

    /**
     * 删除角色
     * @param ids 角色主键字符串
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NotBlank(message = "请指定角色主键字符串") String ids) {
        for (var id : XCI.splitToArray(ids)) {
            SysService.me().roleService().deleteCore(Long.valueOf(id));
        }
    }

    /**
     * 根据主键查询单个角色
     * @param id 角色主键
     * @return 返回角色实体
     */
    public SysRole selectById(@NotNull(message = "请指定角色主键") Long id) {
        return XCI.getCacheValue(roleCache, id, () -> roleDao.selectById(id));
    }

    /**
     * 根据编码查询单个参数
     * @param code 参数编码
     * @return 返回参数实体
     */
    public SysRole selectByCode(@NotBlank(message = "请指定角色编码") String code) {
        return roleDao.selectByCode(code);
    }

    /**
     * 获取用户所属角色列表
     * @param userId 用户主键
     */
    public List<SysRole> selectByUserId(@NotNull(message = "请指定用户主键") Long userId) {
        SysUser user = SysService.me().userService().selectById(userId);
        return selectByUserId(user);
    }

    /**
     * 获取用户所属角色列表
     * @param user 用户对象
     */
    public List<SysRole> selectByUserId(SysUser user) {
        if (user == null) return Lists.newArrayList();
        return roleDao.selectListByUserId(user.getId());
    }

    /**
     * 查询角色列表
     * @param filter 过滤条件
     * @return 返回角色列表
     */
    @DataScope
    public List<SysRole> selectList(RoleFilter filter) {
        return roleDao.selectList(filter);
    }

    /**
     * 查询角色分页列表
     * @param filter 过滤条件
     * @return 返回角色分页列表
     */
    @DataScope
    public PageList<SysRole> selectPageList(RoleFilter filter) {
        return PageList.of(roleDao.selectPageList(filter));
    }

    /**
     * 刷新角色缓存
     */
    public void refresh() {
        roleCache.clear();
        roleDao.selectList(new RoleFilter()).forEach(p -> roleCache.put(p.getId(), p));
        log.info("刷新系统角色缓存");
    }

    /**
     * 保存角色权限
     * @param permissionModel 权限模型
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult savePermission(PermissionBody permissionModel) {
        String[] roleIdList = XCI.splitToArray(permissionModel.getRoleIds());
        for (String roleIdStr : roleIdList) {
            var roleId = Long.valueOf(roleIdStr);
            saveModuleMap(roleId, XCI.splitToArray(permissionModel.getModuleIds()));
            saveDeptDataMap(roleId, Integer.valueOf(permissionModel.getDeptIds()), XCI.splitToArray(permissionModel.getDeptIds()));
        }
        return RestResult.ok();
    }


    // region 模块

    /**
     * 保存角色关联模块
     * @param roleId    角色主键
     * @param moduleIds 模块主键数组
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult saveModuleMap(@NotNull(message = "请指定角色主键") Long roleId, String[] moduleIds) {
        return SysService.me().objectMapService().saveTargets(R.R_ROLE, roleId, R.R_MODULE, moduleIds);
    }

    /**
     * 根据角色主键删除关联模块
     * @param roleId 角色主键
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult deleteModuleMap(@NotNull(message = "请指定角色主键") Long roleId) {
        return SysService.me().objectMapService().deleteByObject(R.R_ROLE, roleId, R.R_MODULE);
    }

    /**
     * 根据角色主键查询关联模块主键集合
     * @param roleId 角色主键
     */
    public List<String> selectModuleMapArray(@NotNull(message = "请指定角色主键") Long roleId) {
        return SysService.me().objectMapService().selectObjectList(R.R_ROLE, roleId, R.R_MODULE);
    }

    /**
     * 根据角色主键查询关联模块对象列表
     * @param roleId 角色主键
     */
    public List<SysModule> selectModuleMapList(@NotNull(message = "请指定角色主键") Long roleId) {
        return roleDao.selectModuleListByRoleId(roleId);
    }
    // endregion

    // region 机构

    /**
     * 保存角色数据权限
     * @param roleId    角色主键
     * @param deptScope 机构数据权限 [1-全部, 2-自定义, 3-所在部门, 4-所在部门及所有下级, 5-仅本人]
     * @param deptIds   机构主键数组
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult saveDeptDataMap(@NotNull(message = "请指定角色主键") Long roleId, @NotNull(message = "请指定角色数据范围") Integer deptScope,
                                      String[] deptIds) {
        var role = selectById(roleId);
        if (role == null) {
            return RestResult.fail("无效的角色主键");
        }
        role.setDeptScope(deptScope);
        roleDao.updateScope(roleId, deptScope);
        roleCache.evict(roleId);
        if (deptScope == 2) {
            return SysService.me().objectMapService().saveTargets(R.R_ROLE, roleId, R.R_DEPT_DATA, deptIds);
        }
        return RestResult.ok();
    }

    /**
     * 根据角色主键删除关联机构
     * @param roleId 角色主键
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult deleteDeptDataMap(@NotNull(message = "请指定角色主键") Long roleId) {
        return SysService.me().objectMapService().deleteByObject(R.R_ROLE, roleId, R.R_DEPT_DATA);
    }

    /**
     * 根据角色主键查询关联机构主键集合
     * @param roleId 角色主键
     */
    public List<String> selectDeptDataMapArray(@NotNull(message = "请指定角色主键") Long roleId) {
        return SysService.me().objectMapService().selectObjectList(R.R_ROLE, roleId, R.R_DEPT_DATA);
    }

    /**
     * 根据角色主键查询关联机构列表
     * @param roleId 角色主键
     */
    public List<SysDept> selectDeptDataMapList(@NotNull(message = "请指定角色主键") Long roleId) {
        return roleDao.selectDeptDataListByRoleId(roleId);
    }

    // endregion

    /**
     * 保存数据,在insert和update之前和之后的校验和处理
     * @param created 是否新建
     * @param entity  角色实体
     */
    RestResult save(boolean created, SysRole entity) {
        //新建时如果主键未指定,设置主键
        XCI.ifTrueAction(XCI.invalidId(created, entity.getId()), () -> entity.setId(XCI.nextId()));

        //如果简拼为空,设置简拼
        XCI.ifBlankAction(entity.getSpell(), () -> entity.setSpell(XCI.getSpell(entity.getName())));

        XCI.ifNullAction(entity.getDeptScope(), () -> entity.setDeptScope(5));

        // var currentUser = SysService.me().getCurrentUser();
        // if(!created && currentUser.getAdministrator() == R.DisabledStatus && entity.getId().equals(currentUser.getRoleId())) {
        //     //修改数据时,当前用户不能修改自己所属角色,只有上级账号才能修改(管理员除外)
        //     return RestResult.fail("修改角色[" + entity.getName() + "]失败，当前用户不能修改自身所属角色,只有上级机构账号才能修改");
        // }

        //检查角色编码是否存在
        if (roleDao.existByCode(entity.getCode(), XCI.excludeId(created, entity.getId()))) {
            String msg = XCI.format("角色编码[{}]已经存在", entity.getCode());
            return RestResult.fail(msg);
        }

        //检查角色名称是否存在
        if (roleDao.existByName(entity.getName(), entity.getDeptId(), XCI.excludeId(created, entity.getId()))) {
            String msg = XCI.format("角色名称[{}]已经存在", entity.getName());
            return RestResult.fail(msg);
        }

        //更新数据库
        XCI.ifAction(created, () -> SysService.me().roleService().insertCore(entity), () -> SysService.me().roleService().updateCore(entity));
        return RestResult.ok();
    }

    /**
     * 内部新建角色,由_save方法调用
     * @param entity 角色实体
     */
    @OperateLog(tag = R.Module.Role, msg = "新增角色", param = true)
    void insertCore(SysRole entity) {
        roleDao.insert(entity);
        roleCache.put(entity.getId(), entity);
        SysService.me().saveInsertHistory(entity.getId(), entity);
    }

    /**
     * 内部修改角色,由_save方法调用
     * @param entity 角色实体
     */
    @OperateLog(tag = R.Module.Role, msg = "修改角色", param = true)
    void updateCore(SysRole entity) {
        var before = roleDao.selectById(entity.getId());
        roleDao.update(entity);
        roleCache.put(entity.getId(), entity);
        SysService.me().saveUpdateHistory(entity.getId(), before, entity);
    }

    /**
     * 内部删除角色
     * @param id 角色主键
     */
    @OperateLog(tag = R.Module.Role, msg = "删除角色", param = true)
    void deleteCore(Long id) {
        var entity = roleDao.selectById(id);
        XCI.ifNullThrow(entity, () -> RestResult.fail(XCI.format("无效的角色主键:[{}]", id)));

        // if(currentUser.getAdministrator() == R.DisabledStatus && entity.getId().equals(currentUser.getRoleId())) {
        //     //管理员除外
        //     throw new AppException(XCI.format("角色[{}]删除失败,不允许删除当前用户所属角色,必须由上级机构账号来操作", entity.getName()));
        // }

        if (SysService.me().userService().existByRoleId(id)) {
            //检测角色是否被账号引用
            XCI.appThrow(XCI.format("用户中引用了角色:[{}],不允许删除", entity.getName()));
        }

        roleDao.deleteById(entity.getId());
        roleCache.evict(entity.getId());//清除当前删除的角色缓存
        deleteModuleMap(id);            //删除角色对应的模块信息
        deleteDeptDataMap(id);          //删除角色对应的机构数据信息
        SysService.me().saveDeleteHistory(entity.getId(), entity);//保存删除历史日志
    }
}