package com.xci.sys.service;

import com.xci.annotation.OperateLog;
import com.xci.annotation.Valid;
import com.xci.core.*;
import com.xci.model.IdValue;
import com.xci.sys.dao.DicCategoryDao;
import com.xci.sys.entity.SysDicCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统字典类型服务
 * @author 吕艳阳
 */
@Service
public class DicCategoryService extends BaseService {
    /** 字典类型数据层对象 */
    @Resource
    private DicCategoryDao dicCategoryDao;

    /**
     * 是否存在指定编码的字典类型
     * @param code 字典类型编码
     * @param excludeId 排除的主键,可为空
     * @return 如果存在返回true
     */
    public boolean existByCode(@NotBlank(message = "请指定字典类型编码") String code, Long excludeId) {
        return dicCategoryDao.existByCode(code, excludeId);
    }

    /**
     * 是否存在指定名称的字典类型
     * @param name 字典类型名称
     * @param excludeId 排除的主键，如果为null则不指定排除的主键
     * @return 如果存在返回true
     */
    public boolean existByName(@NotBlank(message = "请指定字典类型名称") String name, Long excludeId) {
        return dicCategoryDao.existByName(name, excludeId);
    }

    /**
     * 新建字典类型
     * @param entity 字典类型实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult insert(@Valid SysDicCategory entity) {
        return SysService.me().dicCategoryService().save(true, entity);
    }

    /**
     * 修改字典类型
     * @param entity 字典类型实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult update(@Valid SysDicCategory entity) {
        return SysService.me().dicCategoryService().save(false, entity);
    }

    /**
     * 根据主键修改字典类型父节点
     * @param IdValues 修改集合
     */
    @OperateLog(tag = R.Module.DicCategory, msg = "根据主键修改字典类型父节点")
    @Transactional(rollbackFor = Exception.class)
    public RestResult updateParentId(List<IdValue> IdValues) {
        for(IdValue idv : IdValues) {
            var parentId = XCI.toLong(idv.getValue(), -9999L);
            if(parentId == -9999L) continue;
            dicCategoryDao.updateParentId(idv.getId(), parentId);
        }
        return RestResult.ok();
    }

    /**
     * 根据主键修改字典类型排序路径
     * @param IdValues 修改集合
     */
    @OperateLog(tag = R.Module.DicCategory, msg = "根据主键修改字典类型排序路径")
    @Transactional(rollbackFor = Exception.class)
    public RestResult updatePath(List<IdValue> IdValues) {
        for(IdValue idv : IdValues) {
            var path = XCI.toInt(idv.getValue(), -9999);
            if(path == -9999) continue;
            dicCategoryDao.updatePath(idv.getId(), path);
        }
        return RestResult.ok();
    }

    /**
     * 删除字典类型
     * @param ids 字典类型主键字符串
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NotBlank(message = "请指定字典类型主键字符串") String ids) {
        for(var id : XCI.splitToArray(ids)) {
            SysService.me().dicCategoryService().deleteCore(Long.valueOf(id));
        }
    }

    /**
     * 根据主键查询单个字典类型
     * @param id 字典类型主键
     * @return 返回字典类型实体
     */
    public SysDicCategory selectById(@NotNull(message = "请指定字典类型主键") Long id) {
        return dicCategoryDao.selectById(id);
    }

    /**
     * 根据编码查询单个字典类型
     * @param code 字典类型编码
     * @return 返回字典类型实体
     */
    public SysDicCategory selectByCode(@NotBlank(message = "请指定字典类型编码") String code) {
        return dicCategoryDao.selectByCode(code);
    }

    /**
     * 查询字典类型列表
     * @return 返回字典类型列表
     */
    public List<SysDicCategory> selectList() {
        return dicCategoryDao.selectList();
    }

    /**
     * 获取指定节点的直接子节点数
     * @param id 主键
     * @return 返回直接子节点数量
     */
    public Integer selectChildCount(@NotNull(message = "请指定字典类型主键") Long id) {
        return dicCategoryDao.selectChildCount(id);
    }

    /**
     * 保存数据,在insert和update之前和之后的校验和处理
     * @param created 是否新建
     * @param entity 字典类型实体
     */
    RestResult save(boolean created, SysDicCategory entity) {
        //新建时如果主键未指定,设置主键
        XCI.ifTrueAction(XCI.invalidId(created, entity.getId()), () -> entity.setId(XCI.nextId()));

        //如果简拼为空,设置简拼
        XCI.ifBlankAction(entity.getSpell(), () -> entity.setSpell(XCI.getSpell(entity.getName())));

        //设置父节点默认值
        XCI.ifNullAction(entity.getParentId(), () -> entity.setParentId(R.ROOT_NODE_ID));

        //todo:检测:修改时,上级节点不能是自己及所有下级节点
        if(entity.getParentId().equals(entity.getId())) {
            return RestResult.fail("上级不能是自己");
        }

        //检查字典类型编码是否存在
        if(dicCategoryDao.existByCode(entity.getCode(), XCI.excludeId(created, entity.getId()))) {
            return RestResult.fail(XCI.format("字典类型编码[{}]已经存在", entity.getCode()));
        }

        //检查字典类型名称是否存在
        if(dicCategoryDao.existByName(entity.getName(), XCI.excludeId(created, entity.getId()))) {
            return RestResult.fail(XCI.format("字典类型名称[{}]已经存在", entity.getName()));
        }

        //更新数据库
        XCI.ifAction(created, () -> SysService.me().dicCategoryService().insertCore(entity),
                     () -> SysService.me().dicCategoryService().updateCore(entity));
        return RestResult.ok();
    }

    /**
     * 内部新建字典类型,由_save方法调用
     * @param entity 字典类型实体
     */
    @OperateLog(tag = R.Module.DicCategory, msg = "新增字典类型", param = true)
    void insertCore(SysDicCategory entity) {
        entity.setPath(dicCategoryDao.selectChildCount(entity.getParentId()));//自动获取序号
        dicCategoryDao.insert(entity);
        SysService.me().saveInsertHistory(entity.getId(), entity);
    }

    /**
     * 内部修改字典类型,由_save方法调用
     * @param entity 字典类型实体
     */
    @OperateLog(tag = R.Module.Dic, msg = "修改字典类型", param = true)
    void updateCore(SysDicCategory entity) {
        var before = dicCategoryDao.selectById(entity.getId());
        dicCategoryDao.update(entity);
        //当类型编码发生变化时
        if(!before.getCode().equals(entity.getCode())) {
            //更新字典项的类型编码,并从缓存中的旧编码
            SysService.me().dicService().updateCategoryCode(before.getCode(), entity.getCode());
        }
        SysService.me().saveUpdateHistory(entity.getId(), before, entity);
    }

    /**
     * 内部删除字典类型
     * @param id 字典类型主键
     */
    @OperateLog(tag = R.Module.DicCategory, msg = "删除字典类型", param = true)
    void deleteCore(Long id) {
        SysDicCategory entity = dicCategoryDao.selectById(id);
        XCI.ifNullThrow(entity, () -> RestResult.fail(XCI.format("无效的字典类型主键:[{}]", id)));

        if(dicCategoryDao.selectChildCount(id) > 0)
            XCI.appThrow(XCI.format("字典类型[{}]存在下级字典类型,不允许删除", entity.getName()));

        dicCategoryDao.deleteById(id);                  //数据库删除
        // 移除所有字典项并从缓存移除
        SysService.me().dicService().deleteByCategoryCode(entity.getCode());
        SysService.me().saveDeleteHistory(id, entity);  //保存历史日志
    }
}