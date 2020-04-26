package com.xci.sys.web.controller;

import cn.hutool.core.map.MapUtil;
import com.xci.web.configuration.WebProperties;
import com.xci.annotation.AllowAnonymous;
import com.xci.annotation.Authorize;
import com.xci.core.GMap;
import com.xci.core.R;
import com.xci.core.RestResult;
import com.xci.core.XCI;
import com.xci.sys.entity.SysModule;
import com.xci.sys.filter.HistoryLogFilter;
import com.xci.sys.service.*;
import com.xci.sys.web.model.JsonGrid;
import com.xci.sys.web.model.LoginModel;
import com.xci.sys.web.model.TreeNode;
import com.xci.sys.web.component.SysWebService;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 权限子系统默认控制器
 */
@Authorize
@Controller("sysDefaultController")
@RequestMapping("/sys")
public class DefaultController extends SysWebController {
    @Resource private WebProperties webProperties;
    @Resource private UserService userService;//用户服务
    @Resource private LockUserService lockUserService;//锁定用户服务
    @Resource private Cache captchaCache;//用户验证码缓存

    //region 页面视图

    @AllowAnonymous
    @GetMapping("/login")
    public String login(ModelMap map) {
        if (!XCI.checkBrowserCompatibility()) {
            return redirect("/compatibility");
        }
        boolean loginResult = SysService.me().checkAndAutoLogin();
        if (loginResult) {
            return redirect(webProperties.getDefaultUrl());
        }
        map.put("systemTitle", SysService.me().getParamValueByCode("SystemTitle", "西交投信息系统开发平台"));
        map.put("systemTitleColor", SysService.me().getParamValueByCode("SystemTitleColor", "white"));
        map.put("systemCopyright", SysService.me().getParamValueByCode("SystemCopyright", "西安交通信息投资营运有限公司 版权所有"));
        return "sys/default/login";
    }

    // @GetMapping("/")
    @GetMapping()
    public String index(ModelMap map) {
        map.put("currentUser", getCurrentUser());
        map.put("systemTitle", SysService.me().getParamValueByCode("SystemTitle", "西交投信息系统开发平台"));
        map.put("systemTitleColor", SysService.me().getParamValueByCode("SystemTitleColor", "white"));
        map.put("systemCopyright", SysService.me().getParamValueByCode("SystemCopyright", "西安交通信息投资营运有限公司 版权所有"));
        map.put("systemTitleVersion", SysService.me().getParamValueByCode("SystemTitleVersion", "测试版"));
        map.put("systemTitleVersionColor", SysService.me().getParamValueByCode("SystemTitleVersionColor", "yellow"));

        map.put("systemEnableOnlineUserRefresh", SysService.me().getParamValueByCode("SystemEnableOnlineUserRefresh", "0"));
        map.put("systemEnableMessageRefresh", SysService.me().getParamValueByCode("SystemEnableMessageRefresh", "0"));
        map.put("systemEnableTabPage", SysService.me().getParamValueByCode("SystemEnableTabPage", "0"));

        return "sys/default/index";
    }

    @GetMapping("/home")
    public String home(ModelMap map) {
        map.put("userOwneRoleString", SysService.me().getRoleName(getCurrentUser()));
        return "sys/default/home";
    }

    // @AllowAnonymous
    // @GetMapping("/about")
    // public String about(ModelMap map) {
    //     // List<ReleaseHistoryEntity> list = releaseHistoryService.query(GMap.newMap("hasContent", "1"));
    //     // map.put("releaseHistoryList", list);
    //     return "sys/default/about";
    // }

    /**
     * 图标页面
     */
    @AllowAnonymous
    @GetMapping("/icon")
    public String icon() {
        return "sys/default/icon";
    }

    /**
     * 浏览器兼容性页面
     */
    @AllowAnonymous
    @GetMapping("/compatibility")
    public String compatibility() {
        return redirect(webProperties.getCdn()+"/compatibility/index.html");
    }

    @AllowAnonymous
    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "sys/default/unauthorized";
    }

    @AllowAnonymous
    @GetMapping("/error404")
    public String error404() {
        return "sys/default/error404";
    }

    @AllowAnonymous
    @GetMapping("/error500")
    public String error500() {
        return "sys/default/error500";
    }

    @AllowAnonymous
    @GetMapping("/error500debug")
    public String error500debug() {
        return "sys/default/error500debug";
    }

    //endregion

    //region 数据处理

    @AllowAnonymous
    @ResponseBody
    @PostMapping("/login")
    public RestResult login(@ModelAttribute LoginModel model) {
        String account = model.getAccount();
        if (XCI.isBlank(model.getUuid())) {
            return RestResult.fail("账号密码错误");
        }
        if (lockUserService.isLock(account)) {
            return RestResult.fail("账号错误次数达到上限,暂时被锁定");
        }
        model.setCaptcha(captchaCache.get(account, String.class));
        var result = userService.login(account, model.getPassword(), model.getCaptcha());
        if (result.isFail()) {
            return result;
        }

        var entity = result.getData();
        RestResult result1 = SysService.me().onLoginSuccess(entity);
        if (result1.isFail()) {
            return result1;
        }
        if (model.isAutoLogin()) {
            SysService.me().setUserJwtCookie(entity);
        }
        return RestResult.ok(GMap.newMap("url", webProperties.getDefaultUrl()));
    }

    /**
     * 检查账号锁定状态
     */
    @AllowAnonymous
    @ResponseBody
    @PostMapping("/checkLock")
    public RestResult checkLock(String account) {
        if (lockUserService.requireCaptcha(account)) {
            return RestResult.ok();
        }
        return RestResult.fail();
    }

    @ResponseBody
    @PostMapping("/logout")
    public RestResult logout() {
        userService.logout(getCurrentUser().getId());
        getSession().removeAttribute(R.CURRENT_USER_Session_KEY);
        XCI.removeCookie(R.CURRENT_USER_COOKIE_KEY);
        return RestResult.ok(MapUtil.of("url", webProperties.getLoginUrl()));
    }

    /**
     * 模块Tree
     */
    @ResponseBody
    @GetMapping("/module")
    public RestResult module(@RequestParam Map<String, Object> params) {
        List<SysModule> list = SysService.me().userService().selectUserModuleCacheListByUser(getCurrentUser())
                .stream().filter(p -> p.getMenu() && p.getWeb()).collect(Collectors.toList());
        List<TreeNode> nodes = SysWebService.me().toModuleNodeList(list);
        return RestResult.ok(nodes);
    }


    /**
     * 激活当前用户
     */
    @ResponseBody
    @PostMapping("/active")
    public RestResult active() {
        SysService.me().onlineUserService().active(getCurrentUser().getId());
        return RestResult.ok();
    }

    /**
     * 根据表名和主键查询历史日志
     */
    @ResponseBody
    @PostMapping("/history")
    public JsonGrid history(String tableName, String primaryKey) {
        var filter = new HistoryLogFilter();
        filter.setTableName(tableName);
        filter.setPrimaryKey(primaryKey);
        return new JsonGrid(SysService.me().historyLogService().selectPageList(filter));
    }

    //endregion
}