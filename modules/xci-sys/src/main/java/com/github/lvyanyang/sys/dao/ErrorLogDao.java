/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.dao;

import com.github.pagehelper.Page;
import com.github.lvyanyang.annotation.Paging;
import com.github.lvyanyang.sys.entity.SysErrorLog;
import com.github.lvyanyang.sys.filter.ErrorLogFilter;
import org.apache.ibatis.annotations.Param;

/**
 * 系统错误日志数据层
 * @author 吕艳阳
 */
public interface ErrorLogDao {
	/**
	 * 新建错误日志
	 * @param entity 错误日志实体
	 * @return 返回影响的行数
	 */
	Integer insert(@Param("entity") SysErrorLog entity);

	/**
	 * 根据主键查询单个错误日志
	 * @param id 错误日志主键
	 * @return 返回错误日志实体
	 */
	SysErrorLog selectById(@Param("id") Long id);

	/**
	 * 查询错误日志分页列表
	 * @param filter 过滤条件
	 * @return 返回错误日志分页列表
	 */
	@Paging
	Page<SysErrorLog> selectPageList(@Param("filter") ErrorLogFilter filter);
}
