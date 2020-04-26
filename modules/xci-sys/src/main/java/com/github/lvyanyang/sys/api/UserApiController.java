/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.api;

import com.github.lvyanyang.sys.component.CaptchaService;
import com.github.lvyanyang.sys.core.SysApiController;
import com.github.lvyanyang.sys.entity.SysUserSave;
import com.github.lvyanyang.sys.service.SysService;
import com.github.lvyanyang.annotation.AllowAnonymous;
import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.annotation.SingleJson;
import com.github.lvyanyang.model.PageList;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.model.LoginBody;
import com.github.lvyanyang.sys.entity.SysModule;
import com.github.lvyanyang.sys.entity.SysUser;
import com.github.lvyanyang.sys.entity.SysUserLogin;
import com.github.lvyanyang.model.StatusBody;
import com.github.lvyanyang.sys.filter.UserFilter;
import io.swagger.annotations.*;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统用户接口
 * @author 吕艳阳
 */
@Api(tags = "系统用户接口")
@ApiSort(2)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/user", produces = R.PROJSON)
public class UserApiController extends SysApiController {
    //region 账户

    @ApiOperation(value = "用户登录")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @AllowAnonymous
    @PostMapping("/login")
    public RestResult<SysUserLogin> login(@RequestBody LoginBody loginBody) {
        return SysService.me().userService().login(loginBody.getAccount(), loginBody.getPassword(), loginBody.getCaptcha());
    }

    @ApiOperation(value = "用户注销")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "用户主键")
    @PostMapping("/logout")
    public RestResult logout(@SingleJson Long id) {
        return SysService.me().userService().logout(id);
    }

    @ApiOperation(value = "修改用户密码")
    @ApiOperationSupport(order = 3, author = R.LYY)
    @PostMapping("/modifyPassword")
    public RestResult modifyPassword(@RequestBody modifyPasswordBody body) {
        return SysService.me().userService().modifyPassword(body.getId(), body.getCurrentPassword(), body.getNewPassword());
    }

    @ApiOperation(value = "重置用户密码")
    @ApiOperationSupport(order = 4, author = R.LYY)
    @PostMapping("/revisePassword")
    public RestResult revisePassword(@RequestBody revisePasswordBody body) {
        return SysService.me().userService().revisePassword(body.getIds(), body.getNewPassword());
    }

    @ApiOperation(value = "检查账号登录是否需要验证码")
    @ApiOperationSupport(order = 5, author = R.LYY)
    @ApiImplicitParam(name = "account", value = "用户账号")
    @AllowAnonymous
    @PostMapping("/requireCaptcha")
    public RestResult requireCaptcha(@SingleJson String account) {
        if (SysService.me().lockUserService().requireCaptcha(account)) {
            return RestResult.ok();
        }
        return RestResult.fail();
    }

    @ApiOperation(value = "生成验证码")
    @ApiOperationSupport(order = 6, author = R.LYY)
    @AllowAnonymous
    @PostMapping(value = "/captcha", produces = {R.PRO_JPEG, R.PROJSON})
    public void captcha(@RequestBody captchaBody body) {
        var account = body.getAccount();
        var type = body.getType();
        if (XCI.isBlank(account)) {
            XCI.appThrow("请指定用户账号");
        }
        if (XCI.isBlank(type)) {
            type = "math";
        }
        CaptchaService.me().buildNew(account, type, getResponse());
    }

    @Data
    public static class modifyPasswordBody {
        @ApiModelProperty(value = "用户主键", required = true)
        private Long id;
        @ApiModelProperty(value = "当前密码", required = true)
        private String currentPassword;
        @ApiModelProperty(value = "新密码", required = true)
        private String newPassword;
    }

    @Data
    public static class revisePasswordBody {
        @ApiModelProperty(value = "用户主键字符串", required = true)
        private String ids;
        @ApiModelProperty(value = "新密码", required = true)
        private String newPassword;
    }

    @Data
    public static class captchaBody {
        @ApiModelProperty(value = "账号", required = true)
        private String account;
        @ApiModelProperty(value = "验证码类型", required = true, allowableValues = "math,char")
        private String type;
    }

    //endregion

    //region 用户角色关联

    @ApiOperation(value = "获取指定角色中已关联的用户列表")
    @ApiOperationSupport(order = 7, author = R.LYY)
    @ApiImplicitParam(name = "roleId", value = "角色主键")
    @PostMapping("/selectListByRoleId")
    public RestResult<List<SysUser>> selectListByRoleId(@SingleJson Long roleId) {
        return RestResult.ok(SysService.me().userService().selectListByRoleId(roleId));
    }

    @ApiOperation(value = "获取指定角色中未关联的用户列表")
    @ApiOperationSupport(order = 8, author = R.LYY)
    @ApiImplicitParam(name = "roleId", value = "角色主键")
    @PostMapping("/selectUnListByRoleId")
    public RestResult<List<SysUser>> selectUnListByRoleId(@SingleJson Long roleId) {
        return RestResult.ok(SysService.me().userService().selectUnListByRoleId(roleId));
    }

    //endregion

    //region 用户拥有的模块

    @ApiOperationSupport(order = 9, author = R.LYY)
    @ApiOperation(value = "根据用户主键查询用户拥有的模块列表")
    @ApiImplicitParam(name = "userId", value = "用户主键")
    @PostMapping("/selectModuleCacheListByUserId")
    public RestResult<List<SysModule>> selectModuleCacheListByUserId(@SingleJson Long userId) {
        return RestResult.ok(SysService.me().userService().selectUserModuleCacheListByUser(getUser(userId)));
    }

    @ApiOperation(value = "清除用户拥有模块缓存")
    @ApiOperationSupport(order = 10, author = R.LYY)
    @ApiImplicitParam(name = "userId", value = "用户主键")
    @PostMapping("/clearUserModuleListCache")
    public RestResult clearUserModuleCache(@SingleJson Long userId) {
        SysService.me().userService().clearUserModuleCache(userId);
        return RestResult.ok();
    }

    //endregion

    //region 增删改查
    @ApiOperation(value = "是否存在指定账号的用户")
    @ApiOperationSupport(order = 11, author = R.LYY)
    @ApiImplicitParam(name = "account", value = "用户账号")
    @PostMapping(value = "/existByAccount")
    public RestResult existByAccount(@SingleJson String account) {
        return RestResult.ok(SysService.me().userService().existByAccount(account, null));
    }

    @ApiOperation(value = "新建用户")
    @ApiOperationSupport(order = 12, author = R.LYY)
    @Authorize(code = R.Permission.SysUserInsert)
    @PostMapping(value = "/insert")
    public RestResult insert(@RequestBody SysUserSave entity) {
        return SysService.me().userService().insert(entity);
    }

    @ApiOperation(value = "修改用户")
    @ApiOperationSupport(order = 13, author = R.LYY)
    @Authorize(code = R.Permission.SysUserUpdate)
    @PostMapping(value = "/update")
    public RestResult update(@RequestBody SysUserSave entity) {
        return SysService.me().userService().update(entity);
    }

    @ApiOperation(value = "修改用户状态")
    @ApiOperationSupport(order = 14, author = R.LYY)

    @Authorize(code = R.Permission.SysUserUpdate)
    @PostMapping(value = "/updateStatus")
    public RestResult updateStatus(@RequestBody StatusBody statusBody) {
        return SysService.me().userService().updateStatus(statusBody.getIds(), statusBody.getStatus());
    }

    @ApiOperation(value = "删除用户")
    @ApiOperationSupport(order = 15, author = R.LYY)
    @ApiImplicitParam(name = "ids", value = "主键字符串")
    @Authorize(code = R.Permission.SysUserDelete)
    @PostMapping(value = "/delete")
    public RestResult delete(@SingleJson String ids) {
        SysService.me().userService().delete(ids);
        return RestResult.ok();
    }

    @ApiOperation(value = "根据主键查询单个用户")
    @ApiOperationSupport(order = 16, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "主键")
    @PostMapping(value = "/selectById")
    public RestResult<SysUser> selectById(@SingleJson Long id) {
        return RestResult.ok(SysService.me().userService().selectById(id));
    }

    @ApiOperation(value = "根据户账号查询单个用户")
    @ApiOperationSupport(order = 17, author = R.LYY)
    @ApiImplicitParam(name = "account", value = "用户账号")
    @PostMapping("/selectByAccount")
    public RestResult<SysUser> selectByAccount(@SingleJson String account) {
        return RestResult.ok(SysService.me().userService().selectByAccount(account));
    }

    @ApiOperation(value = "查询用户分页列表")
    @ApiOperationSupport(order = 18, author = R.LYY)
    @PostMapping(value = "/selectPageList")
    public RestResult<PageList<SysUser>> selectPageList(@RequestBody UserFilter filter) {
        return RestResult.ok(SysService.me().userService().selectPageList(filter));
    }

    @ApiOperation(value = "查询用户列表")
    @ApiOperationSupport(order = 19, author = R.LYY, ignoreParameters = {R.IG_PAGE_INDEX, R.IG_PAGE_SIZE, R.IG_SORT_NAME, R.IG_SORT_DIR})
    @PostMapping(value = "/selectList")
    public RestResult<List<SysUser>> selectList(@RequestBody UserFilter filter) {
        return RestResult.ok(SysService.me().userService().selectList(filter));
    }

    @ApiOperation(value = "导出用户列表")
    @ApiOperationSupport(order = 20, author = R.LYY, ignoreParameters = {R.IG_PAGE_INDEX, R.IG_PAGE_SIZE, R.IG_SORT_NAME, R.IG_SORT_DIR})
    @PostMapping(value = "/export", produces = {R.PROOCTET, R.PROJSON})
    public void export(@RequestBody UserFilter filter) {
        XCI.exportExcel(SysService.me().userService().selectList(filter), SysUser.class, "系统用户列表");
    }

    @ApiOperation(value = "刷新用户缓存")
    @ApiOperationSupport(order = 21, author = R.LYY)
    @PostMapping(value = "/refresh")
    public RestResult refresh() {
        SysService.me().userService().refresh();
        return RestResult.ok();
    }
    //endregion
}