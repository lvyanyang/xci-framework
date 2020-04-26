package com.xci.sys.aspect;

import com.xci.exceptions.AppException;
import com.xci.core.BaseEntity;
import com.xci.core.BaseFilter;
import com.xci.core.GMap;
import com.xci.core.XCI;
import com.xci.sys.annotation.DataScope;
import com.xci.sys.entity.SysUser;
import com.xci.sys.service.SysService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 数据过滤处理
 */
@Aspect
public class DataScopeAspect {
    // 配置织入点
    @Pointcut("@annotation(com.xci.sys.annotation.DataScope)")
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
        Object[] methodArgs = joinPoint.getArgs();
        Parameter[] methodParams = method.getParameters();
        if(methodArgs.length == 0) {
            throw new AppException("要启用数据权限必须要有参数,参数类型包括:BaseEntity,BaseFilter");
        }

        SysUser user = SysService.me().getCurrentUser();
        if(user == null || !user.getStatus()) {
            throw new AppException("您没有权限读取数据或者账号已停用");
        }
        if(user.getAdmin()) {// 如果是超级管理员，则不过滤数据
            return;
        }
        var dept = SysService.me().deptService().selectByUserId(user);
        if(dept == null || !dept.getStatus()) {
            throw new AppException(XCI.format("请给用户[{}][{}]指定机构", user.getAccount(), user.getId()));
        }
        // var role = roleService.selectByUserId(user);
        // if(role == null || dept.getStatus() == R.DisabledStatus) {
        //     throw new AppException(XCI.format("请给用户[{}][{}]指定角色", user.getAccount(), user.getId()));
        // }

        int deptScope = SysService.me().userService().selectDeptScopeCacheByUserId(user);
        if(1 == deptScope) {//全部数据权限
            return;
        }

        Object filterObject = null;
        for(int i = 0; i < methodArgs.length; i++) {
            var args = methodArgs[i];
            var param = methodParams[i];
            if(param.getName().equals("filter") && (args instanceof BaseEntity || args instanceof BaseFilter)) {
                filterObject = args;
                break;
            }
        }
        if(filterObject != null) {
            // 数据范围 dataScope
            // 用户主键 userId
            // 机构主键 deptId
            // 机构表别名 deptAlias
            // 用户表别名 userAlias
            // 机构主键列名 deptIdColumn
            // 用户主键列名 userIdColumn
            DataScope anScope = method.getAnnotation(DataScope.class);
            var map = GMap.newMap().append("dataScope", deptScope).append("userId", user.getId()).append("deptId", dept.getId())
                    .append("deptAlias",anScope.deptAlias()).append("userAlias", anScope.userAlias())
                    .append("deptIdColumn",anScope.deptIdColumn()).append("userIdColumn", anScope.userIdColumn());
            if(filterObject instanceof BaseFilter) {
                ((BaseFilter) filterObject).getParams().putAll(map);
            } else {
                ((BaseEntity) filterObject).getParams().putAll(map);
            }
        } else {
            throw new AppException("要启用数据权限必须至少要有一个参数,参数类型包括:BaseEntity,BaseFilter中的任何一个");
        }
    }
}