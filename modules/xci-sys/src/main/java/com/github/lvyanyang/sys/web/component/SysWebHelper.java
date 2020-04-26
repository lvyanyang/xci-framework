/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.web.component;

import com.github.lvyanyang.sys.entity.SysDic;
import com.github.lvyanyang.sys.entity.SysRole;
import com.github.lvyanyang.sys.service.SysService;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.sys.entity.SysDept;
import com.github.lvyanyang.sys.entity.SysUser;
import com.github.lvyanyang.sys.filter.DeptFilter;
import com.github.lvyanyang.sys.filter.RoleFilter;
import com.github.lvyanyang.sys.filter.UserFilter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * SysWeb帮助类
 * @author 吕艳阳
 */
@Component
public class SysWebHelper {
    /**
     * 是否有指定模块编码的权限
     * @param code 模块编码字符串
     */
    public boolean auth(String code) {
        var user = SysService.me().getCurrentUser();
        return SysService.me().isAuthorize(user, code);
    }

    /**
     * 根据参数编码获取参数值
     * @param code 参数编码
     * @return 返回参数编码对应的参数值, 如果找不到指定的参数则返回空字符串
     */
    public String param(String code) {
        return SysService.me().getParamStringValueByCode(code, R.Empty);
    }

    /**
     * 根据参数编码获取参数值
     * @param code         参数编码
     * @param defaultValue 找不到参数时返回的默认值
     * @return 返回参数编码对应的参数值
     */
    public String param(String code, String defaultValue) {
        return SysService.me().getParamStringValueByCode(code, defaultValue);
    }

    /**
     * 根据字典编码获取字典明细列表
     * @param dicCode 字典编码
     */
    public List<SysDic> dics(String dicCode) {
        return SysService.me().getDicList(dicCode);
    }

    /**
     * 查询启用的用户列表
     * 如果不是管理员,那么不显示隐藏的用户
     * @param dataScope 是否启用数据权限过滤 [true-启用, false-禁用]
     */
    public List<SysUser> enabledUserList(boolean dataScope) {
        var filter = new UserFilter();
        filter.setStatus(true);
        filter.setDataScope(dataScope);
        if (!SysService.me().getCurrentUser().getAdmin()) {
            //如果不是管理员,那么不显示隐藏的账户
            filter.setVisible(true);
        }
        return SysService.me().userService().selectList(filter);
    }

    /**
     * 查询启用的部门列表
     * @param dataScope 是否启用数据权限过滤 [true-启用, false-禁用]
     */
    public List<SysDept> enabledDeptList(boolean dataScope) {
        var filter = new DeptFilter();
        filter.setStatus(true);
        filter.setDataScope(dataScope);
        return SysService.me().deptService().selectList(filter);
    }

    /**
     * 查询启用的角色列表
     * @param deptId 机构主键
     * @param dataScope 是否启用数据权限过滤 [true-启用, false-禁用]
     */
    public List<SysRole> enabledRoleList(Long deptId, boolean dataScope) {
        var filter = new RoleFilter();
        filter.setStatus(true);
        filter.setDeptId(deptId);
        filter.setDataScope(dataScope);
        return SysService.me().roleService().selectList(filter);
    }
}