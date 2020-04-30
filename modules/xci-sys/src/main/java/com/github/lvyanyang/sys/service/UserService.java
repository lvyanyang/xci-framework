/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.service;

import cn.hutool.core.date.DateUtil;
import com.github.lvyanyang.annotation.OperateLog;
import com.github.lvyanyang.annotation.Valid;
import com.github.lvyanyang.core.BaseService;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.model.PageList;
import com.github.lvyanyang.sys.annotation.DataScope;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.core.Params;
import com.github.lvyanyang.sys.dao.UserDao;
import com.github.lvyanyang.sys.entity.*;
import com.github.lvyanyang.sys.filter.DeptFilter;
import com.github.lvyanyang.sys.filter.ModuleFilter;
import com.github.lvyanyang.sys.filter.UserFilter;
import com.github.lvyanyang.sys.model.LockUserModel;
import com.github.lvyanyang.sys.model.OnlineUserModel;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 系统用户服务
 * @author 吕艳阳
 */
@Slf4j
@Service
public class UserService extends BaseService {
    /** 密码强度监测正则表达式 */
    private static Pattern PASSWORD_STRONG_PATTERN;
    @Resource private UserDao userDao;//用户数据层对象
    @Resource private Cache userCache;//用户缓存对象
    @Resource private Cache captchaCache;//用户验证码缓存
    @Resource private Cache userModuleCache;//用户权限模块缓存

    @PostConstruct
    private void init() {
        refresh();
    }

    /**
     * 是否存在指定机构主键的用户
     * @param deptId 机构主键
     * @return 如果存在返回true
     */
    public boolean existByDeptId(@NotNull(message = "请指定机构主键") Long deptId) {
        return userDao.existByDeptId(deptId);
    }

    /**
     * 是否存在指定角色主键的用户
     * @param roleId 角色主键
     * @return 如果存在返回true
     */
    public boolean existByRoleId(@NotNull(message = "请指定角色主键") Long roleId) {
        return userDao.existByRoleId(roleId);
    }

    /**
     * 是否存在指定账号的用户
     * @param account   用户账号
     * @param excludeId 排除的主键,可为空
     * @return 如果存在返回true
     */
    public boolean existByAccount(@NotBlank(message = "请指定用户账号") String account, Long excludeId) {
        return userDao.existByAccount(account, excludeId);
    }

    /**
     * 新建用户
     * @param entity 用户实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult insert(@Valid SysUserSave entity) {
        return SysService.me().userService().save(true, entity);
    }

    /**
     * 修改用户
     * @param entity 用户实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult update(@Valid SysUserSave entity) {
        return SysService.me().userService().save(false, entity);
    }

    /**
     * 批量保存用户
     * @param entities 用户实体集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void batchSave(@Valid List<SysUserSave> entities) {
        for (var entity : entities) {
            if (entity.getId() == null || !userDao.existById(entity.getId())) {
                //无主键或者主键在数据库中不存在,则新增;
                SysService.me().userService().save(true, entity).ifFailThrow();
            } else {
                //其他情况,则修改
                SysService.me().userService().save(false, entity).ifFailThrow();
            }
        }
    }

    /**
     * 修改用户状态
     * @param ids    用户主键字符串
     * @param status 用户状态
     */
    @OperateLog(tag = R.Module.User, msg = "修改用户状态", param = true, result = true)
    @Transactional(rollbackFor = Exception.class)
    public RestResult updateStatus(@NotBlank(message = "请指定用户主键") String ids,
                                   @NotNull(message = "请指定用户状态") Boolean status) {
        String[] idList = XCI.splitToArray(ids);
        for (String idStr : idList) {
            var id = Long.valueOf(idStr);
            userDao.updateStatus(id, status);
            userCache.evict(id);
        }
        return RestResult.ok();
    }

    /**
     * 删除用户
     * @param ids 用户主键字符串
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NotBlank(message = "请指定用户主键字符串") String ids) {
        for (var id : XCI.splitToArray(ids)) {
            SysService.me().userService().deleteCore(Long.valueOf(id));
        }
    }

    /**
     * 根据主键查询单个用户
     * @param id 用户主键
     * @return 返回用户实体
     */
    public SysUser selectById(@NotNull(message = "请指定用户主键") Long id) {
        return XCI.getCacheValue(userCache, id, () -> userDao.selectById(id));
    }

    /**
     * 根据户账号查询单个用户
     * @param account 用户账号
     * @return 返回用户对象
     */
    public SysUser selectByAccount(@NotBlank(message = "请指定用户账号") String account) {
        return userDao.selectByAccount(account);
    }

    /**
     * 根据主键查询单个用户安全对象,包含密码敏感信息
     * @param id 用户主键
     * @return 返回用户安全对象
     */
    public SysUserSecurity selectSecurityById(@NotNull(message = "请指定用户主键") Long id) {
        return userDao.selectSecurityById(id);
    }

    /**
     * 根据账号查询单个用户安全对象,包含密码敏感信息
     * @param account 用户账号
     * @return 返回用户安全对象
     */
    public SysUserSecurity selectSecurityByAccount(@NotBlank(message = "请指定用户账号") String account) {
        return userDao.selectSecurityByAccount(account);
    }

    /**
     * 查询用户列表
     * @param filter 过滤条件
     * @return 返回用户列表
     */
    @DataScope
    public List<SysUser> selectList(UserFilter filter) {
        return userDao.selectList(filter);
    }

    /**
     * 查询用户分页列表
     * @param filter 过滤条件
     * @return 返回用户分页列表
     */
    @DataScope
    public PageList<SysUser> selectPageList(UserFilter filter) {
        return PageList.of(userDao.selectPageList(filter));
    }

    /**
     * 根据机构主键查询用户列表
     * @param deptId 机构主键
     * @return 返回用户对象列表
     */
    public List<SysUser> selectListByDeptId(@NotNull(message = "请指定机构主键") Long deptId) {
        var filter = new UserFilter();
        filter.setDataScope(false);
        filter.setDeptId(deptId);
        filter.setDeptAllLower(false);
        filter.setVisible(true);
        filter.setStatus(true);
        return userDao.selectList(filter);
    }

    /**
     * 查询指定角色关联的用户主键列表
     * @param roleId 角色主键
     * @return 返回用户主键列表
     */
    public List<String> selectIdsByRoleId(@NotNull(message = "请指定角色主键") Long roleId) {
        return userDao.selectIdsByRoleId(roleId);
    }

    /**
     * 根据角色查询用户成员列表
     * @param roleId 角色主键
     * @return 返回用户对象列表
     */
    public List<SysUser> selectListByRoleId(@NotNull(message = "请指定角色主键") Long roleId) {
        return userDao.selectListByRoleId(roleId);
    }

    /**
     * 查询指定角色未关联的用户主键列表
     * @param roleId 角色主键
     * @return 返回用户主键列表
     */
    public List<String> selectUnIdsByRoleId(@NotNull(message = "请指定角色主键") Long roleId){
        return userDao.selectUnIdsByRoleId(roleId);
    }

    /**
     * 查询指定角色未关联的用户列表
     * @param roleId 角色主键
     * @return 返回用户对象列表
     */
    public List<SysUser> selectUnListByRoleId(@NotNull(message = "请指定角色主键") Long roleId) {
        return userDao.selectUnListByRoleId(roleId);
    }

    /**
     * 刷新用户缓存
     */
    public void refresh() {
        userCache.clear();
        userDao.selectList(new UserFilter()).forEach(p -> userCache.put(p.getId(), p));
        log.info("刷新系统用户缓存");
    }

    //region 账户

    /**
     * 用户登录
     * @param account  账号
     * @param password 密码
     * @param captcha  验证码
     * @return 登录成功返回true
     */
    public RestResult<SysUserLogin> login(@NotBlank(message = "请输入账号") String account, @NotBlank(message = "请输入密码") String password, String captcha) {
        var lockUserService = SysService.me().lockUserService();
        var onlineUserService = SysService.me().onlineUserService();
        if (lockUserService.isLock(account)) {
            LockUserModel lockUserModel = lockUserService.get(account);
            return RestResult.fail(XCI.format("账号密码输入错误次数达到上限,暂时被锁定,将于 {} 解锁", XCI.formatDateHasMinute(lockUserModel.getDisLockTime())));
        }

        if (lockUserService.requireCaptcha(account)) {
            String code = captchaCache.get(account, String.class);
            if ((XCI.isBlank(captcha)) || (XCI.isBlank(code)) || (!code.equalsIgnoreCase(captcha))) {
                captchaCache.evict(account);
                return RestResult.fail("输入的验证码无效");
            }
        }
        captchaCache.evict(account);

        RestResult<SysUser> result = valid(account, password);
        if (result.isOk()) {
            lockUserService.remove(account);
            SysUser user = result.getData();

            OnlineUserModel onlineUser = onlineUserService.get(user.getId());
            if (!Params.sysUserAllowRepeatLogin() && onlineUser != null) {
                var msg = XCI.format("用户在 {} 在IP:{} 已经登录,系统不允许重复登录!", XCI.formatDateTime(onlineUser.getLoginTime()), onlineUser.getIp());
                saveLog(1, account, false, msg);
                return RestResult.fail(msg);
            }

            //region 更新账号登录状态
            Date firstVisitDateTime = null;
            Date lastVisitDateTime = new Date();
            if (user.getFirstVisitTime() == null) {
                firstVisitDateTime = new Date();
            }
            userDao.updateLoginStatus(user.getId(), firstVisitDateTime, lastVisitDateTime);
            SysUser cacheUser = XCI.getCacheValue(userCache, user.getId());
            if (cacheUser != null) {
                cacheUser.setFirstVisitTime(firstVisitDateTime);
                cacheUser.setLastVisitTime(lastVisitDateTime);
                cacheUser.setLoginTimes(cacheUser.getLoginTimes() + 1);
            }
            //endregion

            onlineUserService.active(user.getId()); //激活在线用户
            clearUserModuleCache(user.getId());//清除用户权限缓存
            String token = SysService.me().buildUserToken(user.getId());
            saveLog(1, account, true, "登陆成功");
            var loginUser = new SysUserLogin().from(user);
            loginUser.setToken(token);
            loginUser.setModules(selectUserModuleCacheListByUser(user));
            return RestResult.ok(loginUser);
        } else {
            lockUserService.add(account);
            int limitCount = lockUserService.limtCount(account);
            if (limitCount == 0) {
                var msg = XCI.format("账号{}多次登录失败被锁定", account);
                saveLog(1, account, false, msg);
            }
            String msg = limitCount > 0 ? XCI.format("{},还可以重试{}次", result.getMsg(), limitCount) : "账号错误次数达到上限,暂时被锁定";
            return RestResult.fail(msg);
        }
    }

    /**
     * 用户注销
     * @param userId 用户主键
     */
    public RestResult logout(@NotNull(message = "请指定用户主键") Long userId) {
        var user = selectById(userId);
        if (user != null) {
            SysService.me().onlineUserService().logoff(userId);//注销在线用户
            clearUserModuleCache(userId);//清除用户权限缓存
            saveLog(2, user.getAccount(), true, "注销成功");
            return RestResult.ok();
        }
        return RestResult.ok();//错误的用户注解,不做任何处理
    }

    /**
     * 验证用户账号和密码
     * @param account  账号
     * @param password 密码
     * @return 验证成功返回true
     */
    public RestResult<SysUser> valid(@NotBlank(message = "请输入账号") String account, @NotBlank(message = "请输入密码") String password) {
        //region 检测密码强度
        RestResult result = validPwdStrong(password);
        if (result.isFail()) {
            return RestResult.fail(result.getMsg());
        }
        //endregion

        account = account.trim();
        SysUserSecurity user = userDao.selectSecurityByAccount(account);

        //region 检测条件

        if (user == null) {
            return RestResult.fail("账号密码错误");
        }

        if (XCI.isBlank(user.getPwdSalt())) {
            return RestResult.fail("账号配置无效");
        }
        String encryptedPwd = XCI.encryptPassword(account, password, user.getPwdSalt());
        if (!user.getPwd().equals(encryptedPwd)) {
            return RestResult.fail("账号密码错误");
        }

        if (user.getExpire()) {
            return RestResult.fail("账号已过期");
        }

        if (!user.getStatus()) {
            return RestResult.fail("账号已被禁用");
        }
        var msg = R.Empty;
        if (!user.getPwdNeverExpire() && user.getPwdExpireTime() != null) {
            if (new Date().getTime() >= user.getPwdExpireTime().getTime()) {
                //表示当前日期大于过期日期，提示已过期
                return RestResult.fail("密码已过期");
            }
            int days = Long.valueOf(DateUtil.betweenDay(user.getPwdExpireTime(), new Date(), true)).intValue();
            if (days <= Params.sysUserPwdExpireRemindDays()) {
                msg = XCI.format("密码将于 {} 天后过期,请及时修改密码.", days);
            }
        }
        //endregion
        return RestResult.ok(msg, user);
    }

    /**
     * 修改用户密码
     * @param id              用户主键
     * @param currentPassword 当前密码
     * @param newPassword     新密码
     */
    @OperateLog(tag = R.Module.User, msg = "修改用户密码")
    @Transactional(rollbackFor = Exception.class)
    public RestResult modifyPassword(@NotNull(message = "请指定用户主键") Long id,
                                     @NotBlank(message = "请指定当前密码") String currentPassword,
                                     @NotBlank(message = "请指定新密码") String newPassword) {
        //验证用户主键
        SysUserSecurity entity = userDao.selectSecurityById(id);
        if (entity == null) {
            return RestResult.fail("无效的用户主键");
        }
        if (!entity.getPwdAllowModify()) {
            return RestResult.fail("不允许用户修改密码");
        }

        //验证密码强度
        RestResult result = validPwdStrong(newPassword);
        if (result.isFail()) {
            return RestResult.fail(result.getMsg());
        }

        //验证当前密码
        var epwd = XCI.encryptPassword(entity.getAccount(), currentPassword, entity.getPwdSalt());
        if (!entity.getPwd().equals(epwd)) {
            return RestResult.fail("当前密码输入不正确,请重新输入");
        }

        //生成新的密钥和密码
        String account = entity.getAccount();
        String salt = XCI.createSalt();
        String newPwd = XCI.encryptPassword(account, newPassword, salt);

        //密码过期时间
        if (entity.getPwdNeverExpire()) {
            entity.setPwdExpireTime(null);
        } else {
            entity.setPwdExpireTime(DateUtil.offsetDay(new Date(), Params.sysUserPwdAvailableDays()));
        }

        userDao.modifyPassword(id, salt, newPwd, entity.getPwdExpireTime());
        return RestResult.ok();
    }

    /**
     * 重置用户登录密码
     * @param ids         用户主键数组
     * @param newPassword 新密码
     */
    @OperateLog(tag = R.Module.User, msg = "重置用户密码")
    @Transactional(rollbackFor = Exception.class)
    public RestResult revisePassword(@NotBlank(message = "请指定用户主键字符串,多个用逗号隔开") String ids, @NotBlank(message = "请指定新密码") String newPassword) {
        String[] idList = XCI.splitToArray(ids);
        //验证密码强度
        RestResult result = validPwdStrong(newPassword);
        if (result.isFail()) {
            return RestResult.fail(result.getMsg());
        }

        for (String idStr : idList) {
            var id = Long.valueOf(idStr);
            SysUserSecurity user = userDao.selectSecurityById(id);
            if (user == null) {
                continue;
            }
            String account = user.getAccount();
            String salt = XCI.createSalt();
            String newPwd = XCI.encryptPassword(account, newPassword, salt);

            userDao.revisePassword(id, salt, newPwd);
            // String msg = Helper.format("修改用户密码 主键: {} 账号: {}", id, account);
        }
        return RestResult.ok();
    }

    /**
     * 根据用户主键查询用户拥有的机构权限列表
     * @param user 用户对象
     * @return 返回机构权限列表
     */
    public List<SysDept> selectUserDeptDataListByUserId(SysUser user) {
        if (user == null) return Lists.newArrayList();
        if (user.getAdmin()) {
            var filter = new DeptFilter();
            filter.setStatus(true);
            filter.setDataScope(false);
            return SysService.me().deptService().selectList(filter);
        }
        return userDao.selectUserDeptListByUserId(user.getId());
    }

    /**
     * 根据用户主键查询用户拥有的模块权限列表
     * @param user 用户对象
     * @return 返回模块列表
     */
    public List<SysModule> selectUserModuleListByUser(SysUser user) {
        if (user == null) return Lists.newArrayList();
        if (user.getAdmin()) {
            return SysService.me().moduleService().selectList(ModuleFilter.builder().status(true).build());
        }
        return userDao.selectUserModuleListByUserId(user.getId());
    }

    /**
     * 根据用户主键查询用户拥有的模块权限列表(从缓存)
     * @param user 用户对象
     * @return 返回模块列表
     */
    public List<SysModule> selectUserModuleCacheListByUser(SysUser user) {
        if (user == null) return Lists.newArrayList();
        return XCI.getCacheValue(userModuleCache, "list:" + user.getId(), () -> selectUserModuleListByUser(user));
    }

    /**
     * 从缓存中根据用户查询模块键值对
     * @param user    用户对象
     * @param codeStr 编码字符串,多个编码逗号隔开,and关系
     * @return 返回模块Map
     */
    public boolean isAuthorize(SysUser user, String codeStr) {
        if (user == null || XCI.isBlank(codeStr)) return false;
        if (user.getAdmin()) return true;

        var codes = XCI.splitToArray(codeStr);
        Map<String, Object> moduleMap = XCI.getCacheValue(userModuleCache, "map:" + user.getId(), () -> {
            List<SysModule> list = selectUserModuleCacheListByUser(user);
            Map<String, Object> map = new HashMap<>(list.size());
            for (SysModule entity : list) {
                map.put(entity.getCode(), 1);
            }
            return map;
        });
        for (String code : codes) {
            boolean has = moduleMap.containsKey(code);
            if (!has) {
                //任何一个为false,则直接返回false;
                return false;
            }
        }
        return true;
    }

    /**
     * 清除用户模块缓存
     * @param userId 用户主键
     */
    public void clearUserModuleCache(Long userId) {
        userModuleCache.evict("list:" + userId);
        userModuleCache.evict("map:" + userId);
        userModuleCache.evict("deptScope:" + userId);
    }

    /**
     * 查询用户机构权限(从缓存)
     * @param user 用户对象
     * @return 机构数据权限 [1-全部, 2-自定义, 3-所在部门, 4-所在部门及所有下级, 5-仅本人]
     */
    public int selectDeptScopeCacheByUserId(SysUser user) {
        return XCI.getCacheValue(userModuleCache, "deptScope:" + user.getId(), () -> selectDeptScopeByUserId(user));
    }

    /**
     * 查询用户机构权限
     * @param user 用户对象
     * @return 机构数据权限 [1-全部, 2-自定义, 3-所在部门, 4-所在部门及所有下级, 5-仅本人]
     */
    public int selectDeptScopeByUserId(SysUser user) {
        if (user.getAdmin()) {
            return 1;
        }
        List<SysRole> roles = SysService.me().roleService().selectListByUserId(user.getId());
        var deptScopes = roles.stream().map(SysRole::getDeptScope).collect(Collectors.toList());
        return mergeDeptScope(deptScopes);
    }

    /**
     * 合并机构权限(合并策略:)
     * @param deptScopes 机构权限集合
     */
    private int mergeDeptScope(List<Integer> deptScopes) {
        //[1-全部, 2-自定义, 3-所在部门, 4-所在部门及所有下级, 5-仅本人]
        // 合并策略:
        // 1.有更大的权限时优先采用更大的权限
        // 2.有更小的权限时优先采用更小的权限
        var strategy = Params.sysDeptScopeMergeStrategy();
        Integer current = null;
        for (int scope : deptScopes) {
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

    /**
     * 保存登录日志
     * @param category 类型 [1-登录, 2-注销]
     * @param account  账号
     * @param status   登录状态 [true-成功, false-失败]
     * @param msg      消息
     */
    private void saveLog(Integer category, String account, Boolean status, String msg) {
        var loginLog = SysService.me().buildLoginLog(category, account, status, msg);
        SysService.me().loginLogService().insertAsync(loginLog);
    }
    //endregion

    /**
     * 保存数据,在insert和update之前和之后的校验和处理
     * @param created 是否新建
     * @param entity  用户实体
     */
    RestResult save(boolean created, SysUserSave entity) {
        if (created) {
            //检测是否设置密码
            if (XCI.isBlank(entity.getPwd())) {
                return RestResult.fail("请指定用户密码");
            }
            //检测密码强度
            RestResult strongResult = validPwdStrong(entity.getPwd());
            if (strongResult.isFail()) {
                return strongResult;
            }
        }

        //新建时如果主键未指定,设置主键
        XCI.ifTrueAction(XCI.invalidId(created, entity.getId()), () -> entity.setId(XCI.nextId()));

        //如果简拼为空,设置简拼
        XCI.ifBlankAction(entity.getSpell(), () -> entity.setSpell(XCI.getSpell(entity.getName())));

        String[] roleIds = XCI.splitToArray(entity.getRoleIds());
        if (roleIds != null && roleIds.length > 20) {
            return RestResult.fail("用户拥有的角色不能超过20个");
        }

        var currentUser = SysService.me().getCurrentUser();
        if (!created && !currentUser.getAdmin() && entity.getId().equals(currentUser.getId())) {
            //修改数据时,当前用户不能修改自己的信息,只有上级账号才能修改(超管除外)
            return RestResult.fail("修改用户[" + entity.getAccount() + "]失败，当前用户不能修改自身信息,只有上级机构账号才能修改");
        }

        //只有当前用户是管理员才有权限设置用户的管理员状态
        entity.setAdmin(currentUser.getAdmin() && entity.getAdmin());

        //如果密码不过期,则清空密码过期时间
        if (entity.getPwdNeverExpire()) {
            entity.setPwdExpireTime(null);
        }

        //新建并且密码过期,设置过期时间
        if (created && !entity.getPwdNeverExpire()) {
            entity.setPwdExpireTime(DateUtil.offsetDay(new Date(), Params.sysUserPwdAvailableDays()));
        }

        //账号验证
        if (userDao.existByAccount(entity.getAccount(), entity.getId())) {
            return RestResult.fail(XCI.format("用户账号[{}]已经存在", entity.getAccount()));
        }

        //更新数据库
        XCI.ifAction(created,
                () -> SysService.me().userService().insertCore(entity, roleIds),
                () -> SysService.me().userService().updateCore(entity, roleIds));
        return RestResult.ok();
    }

    /**
     * 内部新建用户,由_save方法调用
     * @param entity  用户实体
     * @param roleIds 角色主键数组
     */
    @OperateLog(tag = R.Module.User, msg = "新增用户", param = true)
    void insertCore(SysUserSave entity, String[] roleIds) {
        entity.setLoginTimes(0L);
        entity.setPwdSalt(XCI.createSalt());
        entity.setPwd(XCI.encryptPassword(entity.getAccount(), entity.getPwd(), entity.getPwdSalt()));
        userDao.insert(entity);//插入用户
        saveUserRoleMaps(entity.getId(), roleIds);//用户角色关系
        SysService.me().saveInsertHistory(entity.getId(), userDao.selectById(entity.getId()));
    }

    /**
     * 内部修改用户,由_save方法调用
     * @param entity  用户实体
     * @param roleIds 角色主键数组
     */
    @OperateLog(tag = R.Module.User, msg = "修改用户", param = true)
    void updateCore(SysUserSave entity, String[] roleIds) {
        var before = userDao.selectById(entity.getId());

        //判断账号是否修改,由于账号和密码有绑定关系,如果账号被修改则无法登陆,所以不允许修改账号
        if (!before.getAccount().equals(entity.getAccount())) {
            XCI.appThrow("由于账号和密码有绑定关系,所以不允许修改账号");
        }
        userDao.update(entity);//更新用户
        userCache.evict(entity.getId());//清除缓存
        saveUserRoleMaps(entity.getId(), roleIds);//用户角色关系
        SysService.me().saveUpdateHistory(entity.getId(), before, userDao.selectById(entity.getId()));
    }

    /**
     * 内部删除用户
     * @param id 用户主键
     */
    @OperateLog(tag = R.Module.User, msg = "删除用户", param = true)
    void deleteCore(Long id) {
        var entity = selectById(id);
        XCI.ifNullThrow(entity, () -> RestResult.fail(XCI.format("无效的用户主键:[{}]", id)));

        SysUser currentUser = SysService.me().getCurrentUser();
        //检测当前账号是否是自身,不允许自己删除自己
        if (entity.getId().equals(currentUser.getId())) {
            XCI.appThrow(XCI.format("账号:[{}]删除失败,不允许删除自己", entity.getAccount()));
        }

        userDao.deleteById(entity.getId(), XCI.nextId());
        //清除当前删除的用户缓存
        userCache.evict(entity.getId());
        //保存删除历史日志
        SysService.me().saveDeleteHistory(entity.getId(), entity);
    }

    /**
     * 保存用户角色关联
     * @param userId  用户主键
     * @param roleIds 角色主键数组
     */
    private void saveUserRoleMaps(Long userId, String[] roleIds) {
        userDao.deleteUserRoleMapByUserId(userId);
        if (XCI.isEmpty(roleIds)) return;

        for (String roleId : roleIds) {
            var entity = new SysUserRoleMap();
            entity.setUserId(userId);
            entity.setRoleId(Long.valueOf(roleId));
            userDao.insertUserRoleMap(entity);
        }
    }

    /**
     * 验证用户密码强度
     * @param pwd 密码
     * @return 密码符合强度要求返回true
     */
    public static RestResult validPwdStrong(String pwd) {
        if (pwd.length()>20) {
            return RestResult.fail("密码长度不能超过20位");
        }
        if (PASSWORD_STRONG_PATTERN == null) {
            String regex = Params.sysUserPasswordStrongPattern();
            PASSWORD_STRONG_PATTERN = Pattern.compile(regex);
        }
        if (PASSWORD_STRONG_PATTERN.matcher(pwd).matches()) {
            return RestResult.ok();
        }
        return RestResult.fail(Params.sysUserPasswordStrongErrorMsg());
    }
}