/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.component;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReflectUtil;
import com.github.lvyanyang.core.GMap;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.model.HistoryInfo;
import com.github.lvyanyang.model.HistoryOperateType;
import com.github.lvyanyang.model.OperateLogInfo;
import com.github.lvyanyang.sys.core.SysParams;
import com.github.lvyanyang.sys.entity.*;
import com.github.lvyanyang.sys.filter.*;
import com.github.lvyanyang.sys.model.BaseOperateUserEntity;
import com.github.lvyanyang.sys.model.BaseUserEntity;
import com.github.lvyanyang.sys.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 系统权限公共服务
 * @author 吕艳阳
 */
@Slf4j
@Component
public class SysService {
    /** 密码强度监测正则表达式 */
    private static Pattern PASSWORD_STRONG_PATTERN;
    private static SysService me;

    // region 系统服务对象
    @Resource private AppService appService;
    @Resource private ReportService reportService;
    @Resource private UserService userService;
    @Resource private AccountService accountService;
    @Resource private UserRoleMapService userRoleMapService;
    @Resource private PermissionService permissionService;
    @Resource private DeptService deptService;
    @Resource private RoleService roleService;
    @Resource private ParamService paramService;
    @Resource private DicCategoryService dicCategoryService;
    @Resource private DicService dicService;
    @Resource private ModuleService moduleService;
    @Resource private SeqService seqService;
    @Resource private FileService fileService;
    @Resource private ObjectMapService objectMapService;
    @Resource private LockUserService lockUserService;
    @Resource private OnlineUserService onlineUserService;
    @Resource private OperateLogService operateLogService;
    @Resource private HistoryLogService historyLogService;
    @Resource private JobLogService jobLogService;
    @Resource private ErrorLogService errorService;
    @Resource private LoginLogService loginLogService;

    /** 系统应用服务 */
    public AppService appService() {
        return appService;
    }

    /** 系统报表服务 */
    public ReportService reportService() {
        return reportService;
    }

    /** 系统用户服务 */
    public UserService userService() {
        return userService;
    }

    /** 系统账户服务 */
    public AccountService accountService() {
        return accountService;
    }

    /** 系统用户角色关联服务 */
    public UserRoleMapService userRoleMapService() {
        return userRoleMapService;
    }

    /** 系统账户权限服务 */
    public PermissionService permissionService() {
        return permissionService;
    }

    /** 系统部门服务 */
    public DeptService deptService() {
        return deptService;
    }

    /** 系统角色服务 */
    public RoleService roleService() {
        return roleService;
    }

    /** 系统参数服务 */
    public ParamService paramService() {
        return paramService;
    }

    /** 系统字典类型服务 */
    public DicCategoryService dicCategoryService() {
        return dicCategoryService;
    }

    /** 系统字典服务 */
    public DicService dicService() {
        return dicService;
    }

    /** 系统模块服务 */
    public ModuleService moduleService() {
        return moduleService;
    }

    /** 系统序列服务 */
    public SeqService seqService() {
        return seqService;
    }

    /** 系统文件服务 */
    public FileService fileService() {
        return fileService;
    }

    /** 系统对象关联服务 */
    public ObjectMapService objectMapService() {
        return objectMapService;
    }

    /** 锁定用户服务 */
    public LockUserService lockUserService() {
        return lockUserService;
    }

    /** 在线用户服务 */
    public OnlineUserService onlineUserService() {
        return onlineUserService;
    }

    /** 系统操作日志服务 */
    public OperateLogService operateLogService() {
        return operateLogService;
    }

    /** 系统历史日志服务 */
    public HistoryLogService historyLogService() {
        return historyLogService;
    }

    /** 系统定时任务日志服务 */
    public JobLogService jobLogService() {
        return jobLogService;
    }

    /** 系统错误日志服务 */
    public ErrorLogService errorService() {
        return errorService;
    }

    /** 系统登陆日志服务 */
    public LoginLogService loginLogService() {
        return loginLogService;
    }

    // endregion

    @PostConstruct
    private void init() {
        SysService.me = this;
    }

    public static SysService me() {
        return me;
    }

    /**
     * 获取当前应用
     */
    public SysApp getCurrentApp() {
        HttpServletRequest request = XCI.getRequest();
        Object appObj = request.getAttribute(R.CURRENT_APP_API_KEY);
        if (appObj != null) {
            return (SysApp) appObj;
        }
        String appId = request.getHeader(R.HEADER_APPID_KEY);
        if (XCI.isBlank(appId)) return null;
        return appService.selectById(Long.valueOf(appId));
    }

    /**
     * 当前操作用户
     */
    public SysUser getCurrentUser() {
        HttpServletRequest request = XCI.getRequest();
        Object userObj;
        if (XCI.isApiRequest()) {
            userObj = request.getAttribute(R.CURRENT_USER_API_KEY);
            if (userObj == null) {
                String tokenStr = request.getHeader(R.HEADER_TOKEN_KEY);
                if (XCI.isBlank(tokenStr)) return null;
                var userResult = getUserByToken(tokenStr);
                if (userResult.isFail()) {
                    XCI.appThrow(userResult.getMsg());
                }
                userObj = userResult.getData();
                request.setAttribute(R.CURRENT_USER_API_KEY, userObj);
            }
        } else {
            userObj = request.getSession().getAttribute(R.CURRENT_USER_Session_KEY);
        }
        if (userObj != null) {
            return (SysUser) userObj;
        }
        return null;
    }

    /**
     * 当前操作用户主键
     */
    public Long getCurrentUserId() {
        var user = getCurrentUser();
        if (user != null) {
            return user.getId();
        }
        return null;
    }

    /**
     * 获取当前用户角色字符串
     */
    public String getRoleName(SysUser user) {
        if (user == null) return R.Empty;
        if (user.getAdmin()) {
            return "内置管理员";
        }
        // return roleService.selectByUserId(user.getId()).getName();
        var userPermission = permissionService.selectUserPermissionFromCache(user.getId());
        return userPermission.getRoles().stream().map(SysRole::getName).collect(Collectors.joining(","));
    }

    /**
     * 验证密码强度
     * @param pwd 密码
     * @return 密码符合强度要求返回true
     */
    public RestResult validPasswordStrong(String pwd) {
        if (pwd.length() > 20) {
            return RestResult.fail("密码长度不能超过20位");
        }
        if (PASSWORD_STRONG_PATTERN == null) {
            String regex = SysParams.SysUserPasswordStrongPattern.getString();
            PASSWORD_STRONG_PATTERN = Pattern.compile(regex);
        }
        if (PASSWORD_STRONG_PATTERN.matcher(pwd).matches()) {
            return RestResult.ok();
        }
        return RestResult.fail(SysParams.SysUserPasswordStrongErrorMsg.getString());
    }

    /**
     * 是否有指定模块编码的权限
     * @param userId 用户主键
     * @param code   模块编码字符串
     */
    public boolean isAuthModule(Long userId, String code) {
        return permissionService.isAuthModule(userId, code);
    }

    /**
     * 生成用户Token
     * @param userId 用户主键
     */
    public String buildUserToken(Long userId) {
        GMap map = GMap.newMap(R.USER_ID, XCI.encrypt(userId.toString()));
        Date expireDate = DateUtil.date().offset(DateField.DAY_OF_MONTH, 30);
        return XCI.createJwt(map, R.JWT_SECRET_KEY, expireDate);
    }

    /**
     * 设置用户登录cookie
     */
    public void setUserJwtCookie(SysUser entity) {
        var userId = entity.getId();
        GMap map = GMap.newMap(R.USER_ID, XCI.encrypt(userId.toString()));
        Date expireDate = DateUtil.date().offset(DateField.DAY_OF_MONTH, 7);
        String token = XCI.createJwt(map, R.JWT_SECRET_KEY, expireDate);
        Cookie cookie = new Cookie(R.CURRENT_USER_COOKIE_KEY, token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        //7天
        cookie.setMaxAge(60 * 60 * 24 * 7);
        XCI.setCookie(cookie);
    }

    /**
     * 根据token获取用户对象
     */
    public RestResult<SysUser> getUserByToken(String token) {
        Map<String, Object> map = XCI.parseJwt(token, R.JWT_SECRET_KEY);
        Object userIdObj = map.get(R.USER_ID);
        if (userIdObj == null || userIdObj.toString().length() == 0) {
            return RestResult.fail("非法用户,信息已被篡改");
        }
        String userId = XCI.decrypt(userIdObj.toString());
        if (!NumberUtil.isLong(userId)) {
            return RestResult.fail("非法用户,信息已被篡改");
        }
        UserService userService = XCI.getBean(UserService.class);
        SysUser userEntity = userService.selectById(Long.valueOf(userId));
        if (userEntity == null) {
            return RestResult.fail("非法用户,无效的用户标识");
        }
        return RestResult.ok(userEntity);
    }

    // region setUserInfo

    /**
     * 设置实体用户字段值
     * @param entity 实体对象
     */
    public void setOperateUserInfo(Object entity) {
        if (entity == null) return;
        SysUser user = getCurrentUser();
        Date date = new Date();
        if (entity instanceof BaseOperateUserEntity) {
            BaseOperateUserEntity baseEntity = (BaseOperateUserEntity) entity;
            baseEntity.setOperateDateTime(date);
            if (user != null) {
                baseEntity.setOperateUserId(user.getId());
                baseEntity.setOperateUserName(user.getName());
            }
        } else {
            setUserFieldValue(entity, user, "OperateUserId", "OperateUserName", "OperateDateTime");
        }
    }

    /**
     * 设置实体用户字段值
     * @param entity  实体对象
     * @param created 是否新建
     */
    public void setUserInfo(Object entity, boolean created) {
        if (entity == null) return;
        SysUser user = getCurrentUser();
        Date date = new Date();
        if (entity instanceof BaseUserEntity) {
            BaseUserEntity baseEntity = (BaseUserEntity) entity;
            if (created) {
                baseEntity.setCreateDateTime(date);
            } else {
                baseEntity.setUpdateDateTime(date);
            }
            if (user != null) {
                if (created) {
                    baseEntity.setCreateUserId(user.getId());
                    baseEntity.setCreateUserName(user.getName());
                } else {
                    baseEntity.setUpdateUserId(user.getId());
                    baseEntity.setUpdateUserName(user.getName());
                }
            }
        } else if (entity instanceof BaseOperateUserEntity) {
            BaseOperateUserEntity baseEntity = (BaseOperateUserEntity) entity;
            baseEntity.setOperateDateTime(date);
            if (user != null) {
                baseEntity.setOperateUserId(user.getId());
                baseEntity.setOperateUserName(user.getName());
            }
        } else {
            setUserFieldValue(entity, user, "OperateUserId", "OperateUserName", "OperateDateTime");
            if (created) {
                setUserFieldValue(entity, user, "CreateUserId", "CreateUserName", "CreateDateTime");
            } else {
                setUserFieldValue(entity, user, "UpdateUserId", "UpdateUserName", "UpdateDateTime");
            }
        }
    }

    /**
     * 设置实体用户字段值
     * @param entity    实体对象
     * @param user      用户对象
     * @param idField   用户主键字段
     * @param nameField 用户姓名字段
     * @param timeField 日期时间字段
     */
    public void setUserFieldValue(Object entity, SysUser user, String idField, String nameField, String timeField) {
        Method method = ReflectUtil.getMethodByName(entity.getClass(), "set" + timeField);
        if (method != null) {
            try {
                method.invoke(entity, new Date());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if (user != null) {
            method = ReflectUtil.getMethodByName(entity.getClass(), "set" + idField);
            if (method != null) {
                try {
                    method.invoke(entity, user.getId());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            method = ReflectUtil.getMethodByName(entity.getClass(), "set" + nameField);
            if (method != null) {
                try {
                    method.invoke(entity, user.getName());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 设置实体用户字段值
     * @param entity 实体对象
     */
    public void setCreateUserInfo(Object entity) {
        setUserInfo(entity, true);
    }

    /**
     * 设置实体用户字段值
     * @param entity 实体对象
     */
    public void setUpdateUserInfo(Object entity) {
        setUserInfo(entity, false);
    }
    // endregion

    //region selectEnabledList

    /**
     * 查询启用的用户列表
     * 如果不是管理员,那么不显示隐藏的用户
     * @param dataScope 是否启用数据权限过滤 [true-启用, false-禁用]
     */
    public List<SysUser> selectEnabledUserList(boolean dataScope) {
        var filter = new UserFilter();
        filter.setStatus(true);
        filter.setEnableDeptScope(dataScope);
        if (!SysService.me().getCurrentUser().getAdmin()) {
            //如果不是管理员,那么不显示隐藏的账户
            filter.setVisible(true);
        }
        return userService().selectList(filter);
    }

    /**
     * 查询启用的部门列表
     * @param deptScope 是否启用数据权限过滤 [true-启用, false-禁用]
     */
    public List<SysDept> selectEnabledDeptList(boolean deptScope) {
        var filter = new DeptFilter();
        filter.setStatus(true);
        filter.setEnableDeptScope(deptScope);
        return deptService().selectList(filter);
    }

    /**
     * 查询启用的角色列表
     * @param deptId    机构主键
     * @param dataScope 是否启用数据权限过滤 [true-启用, false-禁用]
     */
    public List<SysRole> selectEnabledRoleList(Long deptId, boolean dataScope) {
        var filter = new RoleFilter();
        filter.setStatus(true);
        filter.setDeptId(deptId);
        filter.setEnableDeptScope(dataScope);
        return roleService().selectList(filter);
    }

    /**
     * 查询启用的模块列表
     */
    public List<SysModule> selectEnabledModuleList() {
        var filter = new ModuleFilter();
        return moduleService().selectList(filter);
    }


    //endregion

    //region saveHistory

    /**
     * 保存新建历史记录
     */
    public void saveInsertHistory(Object id, Object after) {
        historyLogService.insertAsync(HistoryInfo.builder().type(HistoryOperateType.Insert).pk(id).after(after).build());
    }

    /**
     * 保存修改历史记录
     */
    public void saveUpdateHistory(Object id, Object before, Object after) {
        historyLogService.insertAsync(HistoryInfo.builder().type(HistoryOperateType.Update).pk(id).before(before).after(after).build());
    }

    /**
     * 保存删除历史记录
     */
    public void saveDeleteHistory(Object id, Object before) {
        historyLogService.insertAsync(HistoryInfo.builder().type(HistoryOperateType.Delete).pk(id).before(before).build());
    }

    //endregion

    //region buildLog

    /**
     * 构建操作日志对象
     * @param logInfo 日志信息
     */
    public SysOperateLog buildOperateLog(OperateLogInfo logInfo) {
        SysOperateLog operateLog = new SysOperateLog();
        operateLog.setId(XCI.nextId());
        operateLog.setTag(logInfo.getTag());
        operateLog.setMsg(logInfo.getMsg());
        operateLog.setMethod(logInfo.getMethod());
        operateLog.setReqUrl(logInfo.getReqUrl());
        operateLog.setReqMethod(logInfo.getReqMethod());
        operateLog.setCostTime(logInfo.getCostTime());
        operateLog.setStatus(logInfo.getStatus());
        operateLog.setExecuteParam(logInfo.getParam());
        operateLog.setExecuteResult(logInfo.getResult());
        SysApp app = getCurrentApp();
        if (app != null) {
            operateLog.setAppId(app.getId().toString());
            operateLog.setAppName(app.getName());
        }
        operateLog.setIp(logInfo.getIp());
        operateLog.setUserAgent(logInfo.getUserAgent());
        SysUser user = getCurrentUser();
        if (user != null) {
            operateLog.setOperateDeptId(user.getDeptId().toString());
            operateLog.setOperateDeptName(user.getDeptName());
        }
        setOperateUserInfo(operateLog);
        return operateLog;
    }

    /**
     * 构建登陆日志对象
     * @param category 类型 [1-登录, 2-注销]
     * @param account  账号
     * @param status   登录状态 [true-成功, false-失败]
     * @param msg      消息
     */
    public SysLoginLog buildLoginLog(Integer category, String account, Boolean status, String msg) {
        SysLoginLog loginLog = new SysLoginLog();
        loginLog.setId(XCI.nextId());
        loginLog.setAccount(account);
        loginLog.setCategory(category);
        loginLog.setStatus(status);
        loginLog.setMsg(msg);
        loginLog.setOperateDateTime(new Date());
        SysApp app = getCurrentApp();
        if (app != null) {
            loginLog.setAppId(app.getId().toString());
            loginLog.setAppName(app.getName());
        }

        var browserOsInfo = XCI.getRequestBrowserOsInfo(SysParams.SysUserResolvingIpLocation.getBoolean());
        loginLog.setIp(browserOsInfo.getIp());
        loginLog.setOs(browserOsInfo.getOs());
        loginLog.setBrowser(browserOsInfo.getBrowser());
        loginLog.setUserAgent(browserOsInfo.getUserAgent());
        loginLog.setIpLocation(browserOsInfo.getIpLocation());
        return loginLog;
    }

    /**
     * 根据异常对象构建错误消息
     * @param e 异常对象
     */
    public SysErrorLog buildExceptionLog(Exception e) {
        SysErrorLog errorLog = new SysErrorLog();
        errorLog.setId(XCI.nextId());
        errorLog.setReqParam(XCI.toJsonString(XCI.getRequestParamMap()));
        var request = XCI.getRequest();
        errorLog.setReqUrl(request.getRequestURI());
        errorLog.setReqMethod(request.getMethod());
        errorLog.setMsg(e.getMessage());
        SysApp app = getCurrentApp();
        if (app != null) {
            errorLog.setAppId(app.getId().toString());
            errorLog.setAppName(app.getName());
        }
        var browserOsInfo = XCI.getRequestBrowserOsInfo(false);
        errorLog.setIp(browserOsInfo.getIp());
        setOperateUserInfo(errorLog);
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        errorLog.setDetails(stringWriter.toString());
        return errorLog;
    }
    //endregion
}