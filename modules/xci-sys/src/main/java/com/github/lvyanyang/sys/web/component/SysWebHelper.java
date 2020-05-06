/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.web.component;

import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.core.SysDics;
import com.github.lvyanyang.sys.entity.SysUser;
import com.github.lvyanyang.web.component.WebHelper;
import org.springframework.stereotype.Component;

/**
 * SysWeb帮助类
 * @author 吕艳阳
 */
@Component
public class SysWebHelper extends WebHelper {

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
    public SysUser currentUser() {
        return SysService.me().getCurrentUser();
    }

    //参数分类编码 sys.param.category
    public String dicSysParamCategory(Object selectedValue) {
        return dicSelectOptions(SysDics.SysParamCategory.get(), selectedValue, false);
    }

    //用户职位 sys.user.post
    public String dicSysUserPost(Object selectedValue) {
        return dicSelectOptions(SysDics.SysUserPost.get(), selectedValue, false);
    }

    //机构分类 sys.dept.category
    public String dicSysDeptCategory(Object selectedValue) {
        return dicSelectOptions(SysDics.SysDeptCategory.get(), selectedValue, false);
    }

    //机构性质 sys.dept.nature
    public String dicSysDeptNature(Object selectedValue) {
        return dicSelectOptions(SysDics.SysDeptNature.get(), selectedValue, false);
    }
}