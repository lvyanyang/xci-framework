/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.dao;

import com.github.lvyanyang.annotation.Paging;
import com.github.lvyanyang.sys.entity.SysLoginLog;
import com.github.lvyanyang.sys.filter.LoginLogFilter;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 系统登录日志数据层
 * @author 吕艳阳
 */
public interface LoginLogDao {
	/**
	 * 新建登录日志
	 * @param entity 登录日志实体
	 * @return 返回影响的行数
	 */
	int insert(@Param("entity") SysLoginLog entity);

	/**
	 * 根据主键查询单个登录日志
	 * @param id 主键
	 * @return 返回登录日志实体
	 */
	SysLoginLog selectById(@Param("id") Long id);

	/**
	 * 查询登录日志分页列表
	 * @param filter 过滤条件
	 * @return 返回登录日志分页列表
	 */
	@Paging
	Page<SysLoginLog> selectPageList(@Param("filter") LoginLogFilter filter);
}
