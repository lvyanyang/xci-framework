package com.xci.sys.dao;

import com.github.pagehelper.Page;
import com.xci.annotation.Paging;
import com.xci.sys.entity.SysErrorLog;
import com.xci.sys.filter.ErrorLogFilter;
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
