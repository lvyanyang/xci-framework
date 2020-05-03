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
import com.github.lvyanyang.sys.annotation.DeptScope;
import com.github.lvyanyang.sys.component.SysService;
import com.github.lvyanyang.sys.core.SysParams;
import com.github.lvyanyang.sys.dao.UserDao;
import com.github.lvyanyang.sys.entity.SysUser;
import com.github.lvyanyang.sys.entity.SysUserSave;
import com.github.lvyanyang.sys.filter.UserFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 系统用户服务
 * @author 吕艳阳
 */
@Slf4j
@Service
public class UserService extends BaseService {
    /** 系统用户数据访问对象 */
    @Resource private UserDao userDao;
    /** 用户缓存对象 */
    @Resource private Cache userCache;

    /**
     * 是否存在指定机构主键的用户
     * @param deptId 机构主键
     * @return 如果存在返回true
     */
    public boolean existByDeptId(@NotNull(message = "请指定机构主键") Long deptId) {
        return userDao.existxByDeptId(deptId);
    }

    /**
     * 是否存在指定角色主键的用户
     * @param roleId 角色主键
     * @return 如果存在返回true
     */
    public boolean existByRoleId(@NotNull(message = "请指定角色主键") Long roleId) {
        return userDao.existxByRoleId(roleId);
    }

    /**
     * 是否存在指定账号的用户
     * @param account   用户账号
     * @param excludeId 排除的主键,可为空
     * @return 如果存在返回true
     */
    public boolean existByAccount(@NotBlank(message = "请指定用户账号") String account, Long excludeId) {
        return userDao.existxByAccount(account, excludeId);
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
            if (entity.getId() == null || !userDao.existxById(entity.getId())) {
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
    public void updateStatus(@NotBlank(message = "请指定用户主键") String ids, @NotNull(message = "请指定用户状态") Boolean status) {
        String[] idList = XCI.splitToArray(ids);
        for (String idStr : idList) {
            var id = Long.valueOf(idStr);
            userDao.updateStatus(id, status);
            userCache.evict(id);
        }
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
     * 根据机构主键查询机构成员列表
     * @param deptId 机构主键
     * @return 返回用户对象列表
     */
    public List<SysUser> selectListByDeptId(@NotNull(message = "请指定机构主键") Long deptId) {
        var filter = new UserFilter();
        filter.setEnableDeptScope(false);
        filter.setDeptId(deptId);
        filter.setDeptAllLower(false);
        filter.setVisible(true);
        filter.setStatus(true);
        return userDao.selectList(filter);
    }

    /**
     * 查询用户列表
     * @param filter 过滤条件
     * @return 返回用户列表
     */
    @DeptScope
    public List<SysUser> selectList(UserFilter filter) {
        return userDao.selectList(filter);
    }

    /**
     * 查询用户分页列表
     * @param filter 过滤条件
     * @return 返回用户分页列表
     */
    @DeptScope
    public PageList<SysUser> selectPageList(UserFilter filter) {
        return PageList.of(userDao.selectPageList(filter));
    }

    /**
     * 重新加载用户缓存
     */
    public void refresh() {
        userCache.clear();
        userDao.selectList(new UserFilter()).forEach(p -> userCache.put(p.getId(), p));
        log.info("重新加载系统用户缓存");
    }

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
            RestResult strongResult = SysService.me().validPasswordStrong(entity.getPwd());
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
            entity.setPwdExpireTime(DateUtil.offsetDay(new Date(), SysParams.SysUserPwdAvailableDays.getInt()));
        }

        //账号验证
        if (userDao.existxByAccount(entity.getAccount(), entity.getId())) {
            return RestResult.fail(XCI.format("用户账号[{}]已经存在", entity.getAccount()));
        }

        //更新数据库
        XCI.ifAction(created,
                () -> SysService.me().userService().insertCore(entity, entity.getRoleIds()),
                () -> SysService.me().userService().updateCore(entity, entity.getRoleIds()));
        return RestResult.ok();
    }

    /**
     * 内部新建用户,由_save方法调用
     * @param entity  用户实体
     * @param roleIds 角色主键字符串
     */
    @OperateLog(tag = R.Module.User, msg = "新增用户", param = true)
    void insertCore(SysUserSave entity, String roleIds) {
        entity.setLoginTimes(0L);
        entity.setPwdSalt(XCI.createSalt());
        entity.setPwd(XCI.encryptPassword(entity.getAccount(), entity.getPwd(), entity.getPwdSalt()));
        userDao.insert(entity);//插入用户
        SysService.me().userRoleMapService().saveMapRole(entity.getId(), roleIds);//保存用户对应角色
        SysService.me().saveInsertHistory(entity.getId(), userDao.selectById(entity.getId()));
    }

    /**
     * 内部修改用户,由_save方法调用
     * @param entity  用户实体
     * @param roleIds 角色主键数组
     */
    @OperateLog(tag = R.Module.User, msg = "修改用户", param = true)
    void updateCore(SysUserSave entity, String roleIds) {
        var before = userDao.selectById(entity.getId());

        //判断账号是否修改,由于账号和密码有绑定关系,如果账号被修改则无法登陆,所以不允许修改账号
        if (!before.getAccount().equals(entity.getAccount())) {
            XCI.appThrow("由于账号和密码有绑定关系,所以不允许修改账号");
        }
        userDao.update(entity);//更新用户
        userCache.evict(entity.getId());//清除缓存
        SysService.me().userRoleMapService().saveMapRole(entity.getId(), roleIds);//保存用户对应角色
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
}