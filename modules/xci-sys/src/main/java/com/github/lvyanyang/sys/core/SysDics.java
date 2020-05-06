/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.core;

import com.github.lvyanyang.core.DicObject;

/**
 * 系统字典常量
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-05-04 02:36
 */
public class SysDics {
    /** 系统参数分类字典 */
    public final static DicObject SysParamCategory = new DicObject("sys.param.category");

    /** 系统用户职位字典 */
    public final static DicObject SysUserPost = new DicObject("sys.user.post");

    /** 系统机构分类字典 */
    public final static DicObject SysDeptCategory = new DicObject("sys.dept.category");

    /** 系统机构性质字典 */
    public final static DicObject SysDeptNature = new DicObject("sys.dept.nature");
}