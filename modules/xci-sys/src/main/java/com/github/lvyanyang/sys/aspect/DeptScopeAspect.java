/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.aspect;

import cn.hutool.core.util.ReflectUtil;
import com.github.lvyanyang.core.BaseFilter;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.exceptions.AppException;
import com.github.lvyanyang.sys.annotation.DeptScope;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.entity.SysUser;
import com.github.lvyanyang.sys.model.DeptScopeEnum;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据过滤处理
 */
@Aspect
@Component
public class DeptScopeAspect {
    // 配置织入点
    @Pointcut("@annotation(com.github.lvyanyang.sys.annotation.DeptScope)")
    public void dataScopePointCut() {
    }

    @Before("dataScopePointCut()")
    public void doBefore(JoinPoint point) {
        handleDataScope(point);
    }

    private void handleDataScope(final JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        DeptScope deptScopeAnnota = method.getAnnotation(DeptScope.class);
        Object[] methodArgs = joinPoint.getArgs();
        //Parameter[] methodParams = method.getParameters();
        if (methodArgs.length == 0) {
            throw new AppException("要启用数据权限至少需要有一个参数");
        }
        final String paramName = "params";
        Object filterObject = methodArgs[0];
        var clazz = filterObject.getClass();
        var enableField = ReflectUtil.getField(clazz, deptScopeAnnota.enableName());
        //不启用数据权限
        if (enableField != null && !XCI.toBool(ReflectUtil.getFieldValue(filterObject, enableField))) {
            return;
        }
        if (!(filterObject instanceof BaseFilter)) {
            throw new AppException("要启用数据权限至少需要有一个参数,且第一个参数必须是BaseFilter类型");
        }

        SysUser user = SysService.me().getCurrentUser();
        if (user == null) {
            throw new AppException("当前账号无效");
        }
        if (!user.getStatus()) {
            throw new AppException("您没有权限读取数据或者账号已停用");
        }
        if (user.getAdmin()) {
            // 如果是超级管理员，则不过滤数据
            return;
        }
        var dept = SysService.me().deptService().selectByUserId(user.getId());
        if (dept == null || !dept.getStatus()) {
            throw new AppException(XCI.format("请给用户[{}][{}]指定机构", user.getAccount(), user.getId()));
        }
        // var role = roleService.selectByUserId(user);
        // if(role == null || dept.getStatus() == R.DisabledStatus) {
        //     throw new AppException(XCI.format("请给用户[{}][{}]指定角色", user.getAccount(), user.getId()));
        // }

        //获取当前用户权限
        var userPermission = SysService.me().permissionService().selectUserPermissionFromCache(user.getId());
        var deptScope = userPermission.getDeptScopePermission().getDeptScope();
        if (deptScope == DeptScopeEnum.All) {
            //全部数据权限
            return;
        }

        var filterMap = ((BaseFilter) filterObject).getParams();
        var deptScopeFilter = new DeptScopeFilter();
        deptScopeFilter.setFilterName("filter." + paramName);
        deptScopeFilter.setDeptScope(deptScope);
        deptScopeFilter.setCurrentDeptId(dept.getId());
        deptScopeFilter.setCurrentUserId(user.getId());
        deptScopeFilter.setDeptIdName(deptScopeAnnota.deptIdName());
        deptScopeFilter.setUserIdName(deptScopeAnnota.userIdName());
        filterMap.put("deptScopeFilter", deptScopeFilter);
        filterMap.put("deptScopeSql", buildDeptScopeSql(deptScopeFilter));
    }

    /**
     * 生成机构数据权限过滤SQL
     * @param deptScopeFilter 机构权限对象
     */
    private String buildDeptScopeSql(DeptScopeFilter deptScopeFilter) {
        var filterUserId = XCI.format("{}.deptScopeFilter.currentUserId", deptScopeFilter.filterName);
        var filterDeptId = XCI.format("{}.deptScopeFilter.currentDeptId", deptScopeFilter.filterName);
        if (deptScopeFilter.deptScope == DeptScopeEnum.Custom) {//自定义
            return "and " + deptScopeFilter.deptIdName + " in (select distinct target_id from sys_object_map where object_name = 'role' " +
                    "                    and object_id in ( select r.id from sys_role r join sys_user_role_map urmap " +
                    "                           on r.status = 1 and urmap.user_id = #{" + filterUserId + "} and urmap.role_id = r.id) " +
                    "                    and target_name = 'dept.data')";
        } else if (deptScopeFilter.deptScope == DeptScopeEnum.DeptAndLower) {//所在部门及所有下级
            return "and " + deptScopeFilter.deptIdName + " in (" + XCI.getDeptAndLowerSqlStatement(XCI.getDefaultDatabaseId(), filterDeptId) + ")";
        } else if (deptScopeFilter.deptScope == DeptScopeEnum.Dept) {//所在部门
            return "and " + deptScopeFilter.deptIdName + "=#{" + filterDeptId + "}";
        } else if (deptScopeFilter.deptScope == DeptScopeEnum.User && XCI.isNotBlank(deptScopeFilter.userIdName)) {//仅本人
            return "and " + deptScopeFilter.userIdName + "=#{" + filterUserId + "}";
        } else if (deptScopeFilter.deptScope == DeptScopeEnum.User && XCI.isBlank(deptScopeFilter.userIdName)) { //不查询任何数据
            return "and 1=0";
        }
        return R.Empty;
    }

    @Data
    public static class DeptScopeFilter {
        /** 机构权限 */
        private DeptScopeEnum deptScope;

        /** 当前机构主键 */
        private Long currentDeptId;

        /** 当前用户主键 */
        private Long currentUserId;

        /** 机构列名称 */
        private String deptIdName;

        /** 用户列名称 */
        private String userIdName;

        /** 过滤参数名称 */
        private String filterName;
    }

}