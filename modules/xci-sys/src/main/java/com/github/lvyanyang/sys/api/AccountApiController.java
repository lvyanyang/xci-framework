/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.api;

import com.github.lvyanyang.annotation.AllowAnonymous;
import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.annotation.SingleJson;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.model.LoginBody;
import com.github.lvyanyang.sys.component.CaptchaService;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.core.SysApiController;
import com.github.lvyanyang.sys.entity.SysUserLogin;
import io.swagger.annotations.*;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统账户接口
 * @author 吕艳阳
 */
@Api(tags = "系统账户接口")
@ApiSort(12)
@Authorize
@RestController
@RequestMapping(value = R.SysApiPrefix + "/account", produces = R.PROJSON)
public class AccountApiController extends SysApiController {
    @ApiOperation(value = "用户登录")
    @ApiOperationSupport(order = 1, author = R.LYY)
    @AllowAnonymous
    @PostMapping("/login")
    public RestResult<SysUserLogin> login(@RequestBody LoginBody loginBody) {
        return SysService.me().accountService().login(loginBody.getAccount(), loginBody.getPassword(), loginBody.getCaptcha());
    }

    @ApiOperation(value = "用户注销")
    @ApiOperationSupport(order = 2, author = R.LYY)
    @ApiImplicitParam(name = "id", value = "用户主键")
    @PostMapping("/logout")
    public RestResult logout(@SingleJson Long id) {
        return SysService.me().accountService().logout(id);
    }

    @ApiOperation(value = "修改用户密码")
    @ApiOperationSupport(order = 3, author = R.LYY)
    @PostMapping("/modifyPassword")
    public RestResult modifyPassword(@RequestBody modifyPasswordBody body) {
        return SysService.me().accountService().modifyPassword(body.getId(), body.getCurrentPassword(), body.getNewPassword());
    }

    @ApiOperation(value = "重置用户密码")
    @ApiOperationSupport(order = 4, author = R.LYY)
    @PostMapping("/revisePassword")
    public RestResult revisePassword(@RequestBody revisePasswordBody body) {
        return SysService.me().accountService().revisePassword(body.getIds(), body.getNewPassword());
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
}