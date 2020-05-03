/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.model;

import java.util.List;

/**
 * 字符串转换策略
 */
public enum DeptScopeEnum {
    /** 全部 */
    All(1, "全部"),

    /** 自定义 */
    Custom(2, "自定义"),

    /** 所在部门及所有下级 */
    DeptAndLower(3, "所在部门及所有下级"),

    /** 所在部门 */
    Dept(4, "所在部门"),

    /** 仅本人 */
    User(5, "仅本人");

    private final int value;
    private final String name;

    DeptScopeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static DeptScopeEnum valueOf(int v) {
        switch (v) {
            case 1:
                return All;
            case 2:
                return Custom;
            case 3:
                return DeptAndLower;
            case 4:
                return Dept;
            // case 5:
            //     return user;
            default:
                return User;
        }
    }

    /**
     * 合并机构数据权限
     * @param scopes   待合并的多个机构数据权限
     * @param strategy 合并策略: 1.有更大的权限时优先采用更大的权限 2.有更小的权限时优先采用更小的权限
     * @return 返回合并之后的机构数据权限
     */
    public static int merge(List<Integer> scopes, int strategy) {
        Integer current = null;
        for (int scope : scopes) {
            if (current == null) {
                current = scope;
            } else if (strategy == 1 && scope < current) {//策略1,取最小值即为更大的权限
                current = scope;
            } else if (strategy == 2 && scope > current) {//策略2,取最大值即为更小的权限
                current = scope;
            }
        }
        if (current == null) {
            current = 5;
        }
        return current;
    }
}