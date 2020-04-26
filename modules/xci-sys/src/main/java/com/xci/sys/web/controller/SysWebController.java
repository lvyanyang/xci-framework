package com.xci.sys.web.controller;

import com.xci.web.WebController;
import com.xci.sys.entity.SysUser;
import com.xci.sys.service.SysService;

public class SysWebController extends WebController {
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
