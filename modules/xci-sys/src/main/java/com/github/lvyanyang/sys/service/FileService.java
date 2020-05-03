/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.service;

import com.github.lvyanyang.annotation.OperateLog;
import com.github.lvyanyang.annotation.Valid;
import com.github.lvyanyang.core.BaseService;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.model.PageList;
import com.github.lvyanyang.sys.dao.FileDao;
import com.github.lvyanyang.sys.entity.SysFile;
import com.github.lvyanyang.sys.filter.FileFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统文件服务
 * @author 吕艳阳
 */
@Service
public class FileService extends BaseService {
    /** 文件数据访问对象 */
    @Resource private FileDao fileDao;

    /**
     * 检查记录主键是否存在
     * @param recordId 记录主键
     * @return 如果存在返回true
     */
    public boolean existByRecordId(@NotNull(message = "请指定记录主键") Long recordId){
        return fileDao.existxByRecordId(recordId);
    }

    /**
     * 新建文件
     * @param entity 文件实体
     */
    @OperateLog(tag = R.Module.File, msg = "新增文件")
    @Transactional(rollbackFor = Exception.class)
    public RestResult insert(@Valid SysFile entity) {
        //新建时如果主键未指定,设置主键
        XCI.ifTrueAction(XCI.invalidId(true, entity.getId()), () -> entity.setId(XCI.nextId()));

        //更新数据库
        fileDao.insert(entity);
        return RestResult.ok();
    }

    /**
     * 删除文件
     * @param ids 文件主键字符串
     */
    @OperateLog(tag = R.Module.File, msg = "根据主键删除文件", param = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NotBlank(message = "请指定文件主键字符串") String ids) {
        for (var idStr : XCI.splitToArray(ids)) {
            var id = Long.valueOf(idStr);
            fileDao.deleteById(id);
        }
    }

    /**
     * 根据记录主键删除关联文件
     * @param recordId 记录主键
     */
    @OperateLog(tag = R.Module.File, msg = "根据记录主键删除文件",param = true)
    @Transactional(rollbackFor = Exception.class)
    public void deleteByRecordId(@NotBlank(message = "请指定记录主键") Long recordId) {
        fileDao.deleteByRecordId(recordId);
    }

    /**
     * 根据主键查询单个文件
     * @param id 文件主键
     * @return 返回文件实体
     */
    public SysFile selectById(@NotNull(message = "请指定文件主键") Long id) {
        return fileDao.selectById(id);
    }

    /**
     * 根据记录主键获取第一个关联文件对象
     * @param recordId 记录主键
     * @return 返回关联文件对象
     */
    public SysFile selectByRecordId(@NotNull(message = "请指定记录主键") Long recordId) {
        return fileDao.selectByRecordId(recordId);
    }
    
    /**
     * 查询文件列表
     * @param filter 过滤条件
     * @return 返回文件列表
     */
    public List<SysFile> selectList(FileFilter filter) {
        return fileDao.selectList(filter);
    }

    /**
     * 查询文件分页列表
     * @param filter 过滤条件
     * @return 返回文件分页列表
     */
    public PageList<SysFile> selectPageList(FileFilter filter) {
        return PageList.of(fileDao.selectPageList(filter));
    }
}