/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.dao;

import com.github.lvyanyang.sys.entity.SysUserSecurity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 系统账户数据访问层
 * @author 吕艳阳
 */
public interface AccountDao {
    /**
     * 根据主键查询单个用户安全对象,包含密码敏感信息
     * @param id 用户主键
     * @return 返回用户安全对象
     */
    SysUserSecurity selectSecurityById(@Param("id") Long id);

    /**
     * 根据账号查询单个用户安全对象,包含密码敏感信息
     * @param account 用户账号
     * @return 返回用户安全对象
     */
    SysUserSecurity selectSecurityByAccount(@Param("account") String account);

    /**
     * 更新用户登录状态
     * @param id             用户主键
     * @param firstVisitTime 首次登录时间
     * @param lastVisitTime  最后登录时间
     */
    Integer updateLoginStatus(@Param("id") Long id, @Param("firstVisitTime") Date firstVisitTime, @Param("lastVisitTime") Date lastVisitTime);

    /**
     * 修改用户密码
     * @param id       用户主键
     * @param salt     新密码盐
     * @param pwd 新密码
     */
    Integer modifyPassword(@Param("id") Long id, @Param("salt") String salt, @Param("pwd") String pwd, @Param("pwdExpireTime") Date pwdExpireTime);

    /**
     * 重置用户密码
     * @param id       用户主键
     * @param salt     新密码盐
     * @param pwd 新密码
     */
    Integer revisePassword(@Param("id") Long id, @Param("salt") String salt, @Param("pwd") String pwd);
}