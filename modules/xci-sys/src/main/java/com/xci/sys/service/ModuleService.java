package com.xci.sys.service;

import com.xci.annotation.OperateLog;
import com.xci.annotation.Valid;
import com.xci.core.*;
import com.xci.model.IdValue;
import com.xci.sys.dao.ModuleDao;
import com.xci.sys.entity.SysModule;
import com.xci.sys.filter.ModuleFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统模块服务
 * @author 吕艳阳
 */
@Service
public class ModuleService extends BaseService {
    /** 模块数据层对象 */
    @Resource private ModuleDao moduleDao;

    /**
     * 是否存在指定编码的模块
     * @param code      模块编码
     * @param excludeId 排除的主键,可为空
     * @return 如果存在返回true
     */
    public boolean existByCode(@NotBlank(message = "请指定模块编码") String code, Long excludeId) {
        return moduleDao.existByCode(code, excludeId);
    }

    /**
     * 新建模块
     * @param entity 模块实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult insert(@Valid SysModule entity) {
        return SysService.me().moduleService().save(true, entity);
    }

    /**
     * 修改模块
     * @param entity 模块实体
     */
    @Transactional(rollbackFor = Exception.class)
    public RestResult update(@Valid SysModule entity) {
        return SysService.me().moduleService().save(false, entity);
    }

    /**
     * 批量保存模块
     * @param entities 模块实体集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void batchSave(@Valid List<SysModule> entities) {
        for (SysModule module : entities) {
            if (module.getId() == null || !moduleDao.existById(module.getId())) {
                //无主键或者主键在数据库中不存在,则新增;
                SysService.me().moduleService().save(true, module).ifFailThrow();
            } else {
                //其他情况,则修改
                SysService.me().moduleService().save(false, module).ifFailThrow();
            }
        }
    }

    /**
     * 修改模块状态
     * @param ids    主键字符串
     * @param status 模块状态
     */
    @OperateLog(tag = R.Module.Dic, msg = "修改模块状态", param = true, result = true)
    @Transactional(rollbackFor = Exception.class)
    public RestResult updateStatus(@NotBlank(message = "请指定模块主键") String ids,
                                   @NotNull(message = "请指定模块状态") Boolean status) {
        String[] idList = XCI.splitToArray(ids);
        for (String idStr : idList) {
            var id = Long.valueOf(idStr);
            moduleDao.updateStatus(id, status);
        }
        return RestResult.ok();
    }

    /**
     * 根据主键修改模块父节点
     * @param IdValues 修改集合
     */
    @OperateLog(tag = R.Module.Module, msg = "根据主键修改模块父节点")
    @Transactional(rollbackFor = Exception.class)
    public RestResult updateParentId(List<IdValue> IdValues) {
        for (IdValue idv : IdValues) {
            var parentId = XCI.toLong(idv.getValue(), -9999L);
            if (parentId == -9999L) continue;
            moduleDao.updateParentId(idv.getId(), parentId);
        }
        return RestResult.ok();
    }

    /**
     * 根据主键修改模块排序路径
     * @param IdValues 修改集合
     */
    @OperateLog(tag = R.Module.Module, msg = "根据主键修改模块排序路径")
    @Transactional(rollbackFor = Exception.class)
    public RestResult updatePath(List<IdValue> IdValues) {
        for (IdValue idv : IdValues) {
            var path = XCI.toInt(idv.getValue(), -9999);
            if (path == -9999) continue;
            moduleDao.updatePath(idv.getId(), path);
        }
        return RestResult.ok();
    }

    /**
     * 根据主键修改模块公共状态
     * @param ids          模块主键字符串
     * @param publicStatus 模块公共状态
     * @return 返回影响的行数
     */
    @OperateLog(tag = R.Module.Module, msg = "修改模块公共状态")
    @Transactional(rollbackFor = Exception.class)
    public RestResult updatePublicStatus(@NotBlank(message = "请指定模块主键") String ids,
                                          @NotNull(message = "请指定模块公共状态") Boolean publicStatus) {
        String[] idList = XCI.splitToArray(ids);
        for (String idStr : idList) {
            var id = Long.valueOf(idStr);
            moduleDao.updatePublicStatus(id, publicStatus);
        }
        return RestResult.ok();
    }

    /**
     * 根据主键修改模块展开状态
     * @param ids          模块主键字符串
     * @param expandStatus 模块展开状态
     * @return 返回影响的行数
     */
    @OperateLog(tag = R.Module.Module, msg = "修改模块展开状态")
    @Transactional(rollbackFor = Exception.class)
    public RestResult updateExpandStatus(@NotBlank(message = "请指定模块主键字符串") String ids,
                                          @NotNull(message = "请指定模块展开状态") Boolean expandStatus) {
        String[] idList = XCI.splitToArray(ids);
        for (String idStr : idList) {
            var id = Long.valueOf(idStr);
            moduleDao.updateExpandStatus(id, expandStatus);
        }
        return RestResult.ok();
    }

    /**
     * 删除模块
     * @param ids 模块主键字符串
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NotBlank(message = "请指定模块主键字符串") String ids) {
        for (var id : XCI.splitToArray(ids)) {
            SysService.me().moduleService().deleteCore(Long.valueOf(id));
        }
    }

    /**
     * 根据主键查询单个模块
     * @param id 模块主键
     * @return 返回模块实体
     */
    public SysModule selectById(@NotNull(message = "请指定模块主键") Long id) {
        return moduleDao.selectById(id);
    }

    /**
     * 根据编码查询单个模块
     * @param code 模块编码
     * @return 返回模块实体
     */
    public SysModule selectByCode(@NotBlank(message = "请指定模块编码") String code) {
        return moduleDao.selectByCode(code);
    }

    /**
     * 查询模块列表
     * @param filter 过滤条件对象
     * @return 返回模块列表
     */
    public List<SysModule> selectList(ModuleFilter filter) {
        return moduleDao.selectList(filter);
    }

    /**
     * 获取指定节点的直接子节点数
     * @param id 主键
     * @return 返回直接子节点数量
     */
    public Integer selectChildCount(@NotNull(message = "请指定模块主键") Long id) {
        return moduleDao.selectChildCount(id);
    }

    /**
     * 保存数据,在insert和update之前和之后的校验和处理
     * @param created 是否新建
     * @param entity  模块实体
     */
    RestResult save(boolean created, SysModule entity) {
        //新建时如果主键未指定,设置主键
        XCI.ifTrueAction(XCI.invalidId(created, entity.getId()), () -> entity.setId(XCI.nextId()));

        //如果简拼为空,设置简拼
        XCI.ifBlankAction(entity.getSpell(), () -> entity.setSpell(XCI.getSpell(entity.getName())));

        //设置父节点默认值
        XCI.ifNullAction(entity.getParentId(), () -> entity.setParentId(R.ROOT_NODE_ID));

        //todo:检测:修改时,上级节点不能是自己及所有下级节点
        if (entity.getParentId().equals(entity.getId())) {
            return RestResult.fail("上级不能是自己");
        }

        //检查模块编码是否存在
        if (moduleDao.existByCode(entity.getCode(), XCI.excludeId(created, entity.getId()))) {
            return RestResult.fail(XCI.format("模块编码[{}]已经存在", entity.getCode()));
        }

        //更新数据库
        XCI.ifAction(created,
                () -> SysService.me().moduleService().insertCore(entity),
                () -> SysService.me().moduleService().updateCore(entity));
        return RestResult.ok();
    }

    /**
     * 内部新建模块,由_save方法调用
     * @param entity 模块实体
     */
    @OperateLog(tag = R.Module.Module, msg = "新增模块", param = true)
    void insertCore(SysModule entity) {
        entity.setPath(moduleDao.selectChildCount(entity.getParentId()));//自动获取序号
        moduleDao.insert(entity);
        SysService.me().saveInsertHistory(entity.getId(), entity);
    }

    /**
     * 内部修改模块,由_save方法调用
     * @param entity 模块实体
     */
    @OperateLog(tag = R.Module.Dic, msg = "修改模块", param = true)
    void updateCore(SysModule entity) {
        var before = moduleDao.selectById(entity.getId());
        moduleDao.update(entity);
        SysService.me().saveUpdateHistory(entity.getId(), before, entity);
    }

    /**
     * 内部删除模块
     * @param id 模块主键
     */
    @OperateLog(tag = R.Module.Module, msg = "删除模块", param = true)
    void deleteCore(Long id) {
        SysModule entity = moduleDao.selectById(id);
        XCI.ifNullThrow(entity, () -> RestResult.fail(XCI.format("无效的模块主键:[{}]", id)));

        if (moduleDao.selectChildCount(id) > 0)
            XCI.appThrow(XCI.format("模块[{}]存在下级模块,不允许删除", entity.getName()));

        moduleDao.deleteById(id);                       //数据库删除
        SysService.me().saveDeleteHistory(id, entity);  //保存历史日志
    }
}