package com.xci.sys.service;

import com.xci.annotation.OperateLog;
import com.xci.annotation.Valid;
import com.xci.core.*;
import com.xci.model.PageList;
import com.xci.sys.dao.SeqDao;
import com.xci.sys.entity.SysSeq;
import com.xci.sys.filter.SeqFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统序列服务
 * @author 吕艳阳
 */
@Service
public class SeqService extends BaseService {
    /** 序列数据访问对象 */
    @Resource private SeqDao seqDao;

    /**
     * 是否存在指定编码的序列
     * @param code      序列编码
     * @param excludeId 排除的主键,可为空
     * @return 如果存在返回true
     */
    public boolean existByCode(@NotBlank(message = "请指定序列编码") String code, Long excludeId) {
        return seqDao.existByCode(code, excludeId);
    }

    /**
     * 新建序列
     * @param entity 序列实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult insert(@Valid SysSeq entity) {
        return SysService.me().seqService().save(true, entity);
    }

    /**
     * 修改序列
     * @param entity 序列实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult update(@Valid SysSeq entity) {
        return SysService.me().seqService().save(false, entity);
    }

    /**
     * 删除序列
     * @param ids 序列主键字符串
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NotBlank(message = "请指定序列主键字符串") String ids) {
        for (var id : XCI.splitToArray(ids)) {
            SysService.me().seqService().deleteCore(Long.valueOf(id));
        }
    }

    /**
     * 根据主键查询单个序列
     * @param id 序列主键
     * @return 返回序列实体
     */
    public SysSeq selectById(@NotNull(message = "请指定序列主键") Long id) {
        return seqDao.selectById(id);
    }

    /**
     * 根据编码查询单个序列
     * @param code 序列编码
     * @return 返回序列实体
     */
    public SysSeq selectByCode(@NotBlank(message = "请指定序列编码") String code) {
        return seqDao.selectByCode(code);
    }

    /**
     * 查询当前序列值
     * @param code 序列编码
     */
    public Long selectCurrentValue(@NotBlank(message = "请指定序列编码") String code) {
        return seqDao.currentValue(code);
    }

    /**
     * 查询下一个序列值
     * @param code 序列编码
     */
    @Transactional(rollbackFor = Exception.class)
    public Long selectNextValue(@NotBlank(message = "请指定序列编码") String code) {
        return nextValueCore(code);
    }

    /**
     * 查询序列列表
     * @param filter 过滤条件
     * @return 返回序列列表
     */
    public List<SysSeq> selectList(SeqFilter filter) {
        return seqDao.selectList(filter);
    }

    /**
     * 查询序列分页列表
     * @param filter 过滤条件
     * @return 返回序列分页列表
     */
    public PageList<SysSeq> selectPageList(SeqFilter filter) {
        return PageList.of(seqDao.selectPageList(filter));
    }

    /**
     * 保存数据,在insert和update之前和之后的校验和处理
     * @param created 是否新建
     * @param entity  序列实体
     */
    RestResult save(boolean created, SysSeq entity) {
        //新建时如果主键未指定,设置主键
        XCI.ifTrueAction(XCI.invalidId(created, entity.getId()), () -> entity.setId(XCI.nextId()));

        //检查序列编码是否存在
        if (seqDao.existByCode(entity.getCode(), XCI.excludeId(created, entity.getId()))) {
            return RestResult.fail(XCI.format("序列编码[{}]已经存在", entity.getCode()));
        }

        //如果序列当前值为空，则设置为0
        XCI.ifTrueAction(entity.getCurrentValue()==null, () -> entity.setCurrentValue(0L));

        //更新数据库
        XCI.ifAction(created,
                () -> SysService.me().seqService().insertCore(entity),
                () -> SysService.me().seqService().updateCore(entity));
        return RestResult.ok();
    }

    /**
     * 内部新建序列,由_save方法调用
     * @param entity 序列实体
     */
    @OperateLog(tag = R.Module.Seq, msg = "新增序列", param = true)
    void insertCore(SysSeq entity) {
        seqDao.insert(entity);
        //保存插入历史日志
        SysService.me().saveInsertHistory(entity.getId(), entity);
    }

    /**
     * 内部修改序列,由_save方法调用
     * @param entity 序列实体
     */
    @OperateLog(tag = R.Module.Seq, msg = "修改序列", param = true)
    void updateCore(SysSeq entity) {
        var before = seqDao.selectById(entity.getId());
        seqDao.update(entity);
        //保存修改历史日志
        SysService.me().saveUpdateHistory(entity.getId(), before, entity);
    }

    /**
     * 内部删除序列
     * @param id 序列主键
     */
    @OperateLog(tag = R.Module.Seq, msg = "删除序列", param = true)
    void deleteCore(Long id) {
        var entity = seqDao.selectById(id);
        XCI.ifNullThrow(entity, () -> RestResult.fail(XCI.format("无效的序列主键:[{}]", id)));

        seqDao.deleteById(entity.getId());
        //保存删除历史日志
        SysService.me().saveDeleteHistory(entity.getId(), entity);
    }

    /**
     * 获取当前序列值并更新
     * @param code 序列编码
     */
    private Long nextValueCore(String code) {
        Long nextValue = seqDao.currentValue(code);
        if (nextValue == null) {//新建
            nextValue = 1L;
            SysSeq entity = new SysSeq();
            entity.setId(XCI.nextId());
            entity.setName(code);
            entity.setCode(code);
            entity.setStartWith(1L);
            entity.setCurrentValue(nextValue);
            entity.setIncrementBy(1);
            entity.setRemark("auto");
            seqDao.insert(entity);
        }
        seqDao.incrementValue(code);
        return nextValue;
    }
}