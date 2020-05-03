/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.web.component;

import com.github.lvyanyang.core.R;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.entity.SysDept;
import com.github.lvyanyang.sys.entity.SysDic;
import com.github.lvyanyang.sys.entity.SysRole;
import com.github.lvyanyang.sys.entity.SysUser;
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
        return SysWebService.me().isAuthModule(SysService.me().getCurrentUserId(), code);
    }

    /**
     * 获取当前登录用户
     */
    public SysUser currentUser(){
        return SysService.me().getCurrentUser();
    }

    /**
     * 根据参数编码获取参数值
     * @param code 参数编码
     * @return 返回参数编码对应的参数值, 如果找不到指定的参数则返回空字符串
     */
    public String param(String code) {
        return SysWebService.me().paramService().selectValueByCode(code, R.Empty);
    }

    /**
     * 根据参数编码获取参数值
     * @param code         参数编码
     * @param defaultValue 找不到参数时返回的默认值
     * @return 返回参数编码对应的参数值
     */
    public String param(String code, String defaultValue) {
        return SysWebService.me().paramService().selectValueByCode(code, defaultValue);
    }

    /**
     * 根据字典编码获取字典明细列表
     * @param dicCode 字典编码
     */
    public List<SysDic> dics(String dicCode) {
        return SysWebService.me().getDicList(dicCode);
    }

    /**
     * 查询数据字典键值对
     * @param dicCode      字典类型编码
     * @param name         字典名称
     * @param defaultValue 找不到指定的项值时返回的默认值
     * @return 返回数据字典键值对
     */
    public String dicValueByName(String dicCode, Object name, String defaultValue) {
        return SysWebService.me().getDicValueByName(dicCode, name.toString(), defaultValue);
    }

    /**
     * 查询数据字典键值对
     * @param dicCode     字典类型编码
     * @param value       字典项值
     * @param defaultName 找不到指定的项值时返回的默认名称
     * @return 返回数据字典键值对
     */
    public String dicNameByValue(String dicCode, Object value, String defaultName) {
        return SysWebService.me().getDicNameByValue(dicCode, value.toString(), defaultName);
    }

    /**
     * 查询启用的用户列表
     * 如果不是管理员,那么不显示隐藏的用户
     * @param dataScope 是否启用数据权限过滤 [true-启用, false-禁用]
     */
    public List<SysUser> enabledUserList(boolean dataScope) {
        return SysWebService.me().selectEnabledUserList(dataScope);
    }

    /**
     * 查询启用的部门列表
     * @param dataScope 是否启用数据权限过滤 [true-启用, false-禁用]
     */
    public List<SysDept> enabledDeptList(boolean dataScope) {
        return SysWebService.me().selectEnabledDeptList(dataScope);
    }

    /**
     * 查询启用的角色列表
     * @param deptId    机构主键
     * @param dataScope 是否启用数据权限过滤 [true-启用, false-禁用]
     */
    public List<SysRole> enabledRoleList(Long deptId, boolean dataScope) {
        return SysWebService.me().selectEnabledRoleList(deptId, dataScope);
    }
}