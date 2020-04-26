package com.xci.sys.dao;

import com.github.pagehelper.Page;
import com.xci.annotation.Paging;
import com.xci.sys.entity.SysLoginLog;
import com.xci.sys.filter.LoginLogFilter;
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
