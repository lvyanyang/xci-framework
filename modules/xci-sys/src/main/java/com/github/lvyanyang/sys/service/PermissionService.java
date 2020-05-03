/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.service;

import com.github.lvyanyang.core.BaseService;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.model.PermissionBody;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.core.SysParams;
import com.github.lvyanyang.sys.dao.PermissionDao;
import com.github.lvyanyang.sys.entity.SysModule;
import com.github.lvyanyang.sys.entity.SysRole;
import com.github.lvyanyang.sys.filter.ModuleFilter;
import com.github.lvyanyang.sys.model.DeptScopeEnum;
import com.github.lvyanyang.sys.model.DeptScopePermission;
import com.github.lvyanyang.sys.model.RolePermission;
import com.github.lvyanyang.sys.model.UserPermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统账户权限服务
 * @author 吕艳阳
 */
@Slf4j
@Service
public class PermissionService extends BaseService {
    /** 用户权限模块缓存 */
    @Resource private Cache permissionCache;
    /** 系统用户数据访问对象 */
    @Resource private PermissionDao permissionDao;

    //region 当前用户操作

    /**
     * 判断用户是否拥有指定编码的功能模块
     * @param userId 用户主键
     * @param code   编码字符串,多个编码逗号隔开,多个编码必须全部拥有
     * @return 如果拥有返回true
     */
    public boolean isAuthModule(@NotNull(message = "请指定用户主键") Long userId, String code) {
        XCI.ifNullThrow(userId, () -> RestResult.fail("无效的用户主键:" + userId));
        var user = SysService.me().userService().selectById(userId);
        XCI.ifNullThrow(user, () -> RestResult.fail("无效的用户主键:" + userId));

        if (user.getAdmin() || XCI.isBlank(code)) return true;

        var codes = XCI.splitToArray(code);
        Map<String, Object> moduleMap = XCI.getCacheValue(permissionCache, "user-modules-" + user.getId(), () -> {
            List<SysModule> list = selectUserPermission(userId).getModules();
            Map<String, Object> map = new HashMap<>(list.size());
            for (SysModule entity : list) {
                map.put(entity.getCode(), 1);
            }
            return map;
        });
        for (String c : codes) {
            boolean has = moduleMap.containsKey(c);
            if (!has) {
                //任何一个为false,则直接返回false;
                return false;
            }
        }
        return true;
    }

    /**
     * 从缓存中查询用户权限
     * @param userId 用户主键
     */
    public UserPermission selectUserPermissionFromCache(@NotNull(message = "请指定用户主键") Long userId) {
        var user = SysService.me().userService().selectById(userId);
        XCI.ifNullThrow(user, () -> RestResult.fail("无效的用户主键:" + userId));

        return XCI.getCacheValue(permissionCache, user.getId(), () -> selectUserPermission(userId));
    }

    /**
     * 清除缓存中的用户权限
     * @param userId 用户主键
     */
    public void clearUserPermissionCache(@NotNull(message = "请指定用户主键") Long userId) {
        permissionCache.evict(userId);
    }

    //endregion

    //region 用户权限

    /**
     * 查询用户权限
     * @param userId 用户主键
     */
    public UserPermission selectUserPermission(@NotNull(message = "请指定用户主键") Long userId) {
        UserPermission userPermission = new UserPermission();
        //1.设置用户对象
        var user = SysService.me().userService().selectById(userId);
        XCI.ifNullThrow(user, () -> RestResult.fail("无效的用户主键:" + userId));
        // userPermission.setUser(user);
        //2.设置机构对象
        var dept = SysService.me().deptService().selectByUserId(userId);
        XCI.ifNullThrow(dept, () -> RestResult.fail("没有给用户:" + userId + "设置有效的机构"));
        userPermission.setDept(dept);
        //3.设置拥有的角色列表
        var roles = SysService.me().userRoleMapService().selectMapRoleList(userId);
        userPermission.setRoles(roles);
        //4.设置拥有的模块列表
        List<SysModule> modules;
        if (user.getAdmin()) {
            modules = SysService.me().moduleService().selectList(ModuleFilter.builder().status(true).build());
        } else {
            modules = permissionDao.selectUserModuleList(user.getId());
        }
        userPermission.setModules(modules);
        //5.设置机构权限
        DeptScopePermission deptScopePermission = new DeptScopePermission();
        if (user.getAdmin()) {
            deptScopePermission.setDeptScope(DeptScopeEnum.All);
        } else {
            var deptScopes = roles.stream().map(SysRole::getDeptScope).collect(Collectors.toList());
            var strategy = SysParams.SysDeptScopeMergeStrategy.getInt();
            deptScopePermission.setDeptScope(DeptScopeEnum.valueOf(DeptScopeEnum.merge(deptScopes, strategy)));

            //5.1如果是自定义机构权限,还需要查询自定义机构明细
            if (deptScopePermission.getDeptScope() == DeptScopeEnum.Custom) {
                var customDeptList = permissionDao.selectUserDeptDataList(userId);
                deptScopePermission.setCustoms(customDeptList);
            }
        }
        userPermission.setDeptScopePermission(deptScopePermission);
        return userPermission;
    }

    //endregion

    //region 角色权限

    /**
     * 保存角色权限
     * @param permissionModel 权限对象
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveRolePermission(PermissionBody permissionModel) {
        String[] roleIdList = XCI.splitToArray(permissionModel.getRoleIds());
        for (String roleIdStr : roleIdList) {
            var roleId = Long.valueOf(roleIdStr);
            var role = SysService.me().roleService().selectById(roleId);
            XCI.ifNullThrow(role, () -> RestResult.fail("无效的角色主键:" + roleId));

            String[] moduleIds = XCI.splitToArray(permissionModel.getModuleIds());
            //1.保存模块权限
            SysService.me().objectMapService().save(R.R_ROLE, roleId, R.R_MODULE, moduleIds);
            //2.保存机构权限
            var deptScope = permissionModel.getDeptScope();
            //2.1 保存机构数据权限字段
            SysService.me().roleService().updateDeptScope(roleId.toString(), deptScope);
            //2.3 如果是自定义机构,则需要添加自定义的机构表
            if (deptScope == 2) {//自定义
                var deptIds = XCI.splitToArray(permissionModel.getCustomDeptIds());
                SysService.me().objectMapService().save(R.R_ROLE, roleId, R.R_DEPT_DATA, deptIds);
            } else {
                //如果不是自定义,需要清除之前的自定义记录
                SysService.me().objectMapService().deleteByObject(R.R_ROLE, roleId, R.R_DEPT_DATA);
            }
        }
    }

    /**
     * 清除角色权限
     * @param roleId 角色主键
     */
    @Transactional(rollbackFor = Exception.class)
    public void clearRolePermission(@NotNull(message = "请指定角色主键") Long roleId) {
        //1.清除模块权限
        SysService.me().objectMapService().deleteByObject(R.R_ROLE, roleId, R.R_MODULE);
        //2.清除自定义机构权限
        SysService.me().objectMapService().deleteByObject(R.R_ROLE, roleId, R.R_DEPT_DATA);
    }

    /**
     * 查询角色权限
     * @param roleId 角色主键
     */
    public RolePermission selectRolePermission(@NotNull(message = "请指定角色主键") Long roleId) {
        var role = SysService.me().roleService().selectById(roleId);
        XCI.ifNullThrow(role, () -> RestResult.fail("无效的角色主键"));

        RolePermission rolePermission = new RolePermission();
        //1.查询模块权限
        var modulesList = permissionDao.selectRoleModuleList(roleId);
        rolePermission.setModules(modulesList);
        //2.查询机构权限权限
        DeptScopePermission deptScopePermission = new DeptScopePermission();
        deptScopePermission.setDeptScope(DeptScopeEnum.valueOf(role.getDeptScope()));
        //2.1如果是自定义机构权限,还需要查询自定义机构明细
        if (deptScopePermission.getDeptScope() == DeptScopeEnum.Custom) {
            var customDeptList = permissionDao.selectRoleDeptDataList(roleId);
            deptScopePermission.setCustoms(customDeptList);
        }
        rolePermission.setDeptScopePermission(deptScopePermission);
        return rolePermission;
    }

    //endregion
}