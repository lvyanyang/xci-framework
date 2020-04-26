/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.core;


import com.github.lvyanyang.base.ApiController;
import com.github.lvyanyang.sys.entity.SysUser;
import com.github.lvyanyang.sys.service.SysService;

/**
 * 系统管理模块Api控制器基类
 * @author 吕艳阳
 */
public class SysApiController extends ApiController {
    /**
     * 获取当前操作用户
     */
    protected SysUser getCurrentUser() {
        return SysService.me().getCurrentUser();
    }

    /**
     * 获取用户对象
     * @param userId 用户主键
     */
    protected SysUser getUser(Long userId) {
        return SysService.me().userService().selectById(userId);
    }
}