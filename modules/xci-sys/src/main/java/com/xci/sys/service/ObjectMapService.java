package com.xci.sys.service;

import com.xci.core.XCI;
import com.xci.sys.dao.ObjectMapDao;
import com.xci.sys.entity.SysObjectMap;
import com.xci.core.BaseService;
import com.xci.core.RestResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统对象关联服务
 * @author 吕艳阳
 */
@Service
public class ObjectMapService extends BaseService {
    @Resource private ObjectMapDao objectMapDao;//系统对象关联数据层

    /**
     * 保存对象数据关联
     * @param objectName 对象名称
     * @param objectId   对象主键
     * @param targetName 目标名称
     */
    public RestResult saveTargets(@NotEmpty(message = "请指定对象名称") String objectName,
            @NotNull(message = "请指定对象主键") Long objectId, @NotEmpty(message = "请指定目标名称") String targetName,
            String[] targetIds) {
        objectMapDao.deleteByObject(objectName, objectId, targetName);
        if(!XCI.isEmpty(targetIds)) {
            for(String targetId : targetIds) {
                SysObjectMap entity = new SysObjectMap();
                entity.setObjectName(objectName);
                entity.setObjectId(objectId);
                entity.setTargetName(targetName);
                entity.setTargetId(Long.valueOf(targetId.trim()));
                objectMapDao.insert(entity);
            }
        }
        return RestResult.ok();
    }

    /**
     * 根据对象删除关联
     * @param objectName 对象名称
     * @param objectId   对象主键
     * @param targetName 目标名称
     */
    public RestResult deleteByObject(@NotEmpty(message = "请指定对象名称") String objectName,
            @NotNull(message = "请指定对象主键") Long objectId, @NotEmpty(message = "请指定目标名称") String targetName) {
        objectMapDao.deleteByObject(objectName, objectId, targetName);
        return RestResult.ok();
    }

    /**
     * 根据目标删除关联
     * @param targetName 目标名称
     * @param targetId   目标主键
     * @param objectName 对象名称
     */
    public RestResult deleteByTarget(@NotEmpty(message = "请指定目标名称") String targetName,
            @NotNull(message = "请指定目标主键") Long targetId, @NotEmpty(message = "请指定对象名称") String objectName) {
        objectMapDao.deleteByTarget(targetName, targetId, objectName);
        return RestResult.ok();
    }

    /**
     * 获取目标主键列表
     * @param objectName 对象名称
     * @param objectId   对象主键
     * @param targetName 目标名称
     */
    public List<String> selectTargetList(@NotEmpty(message = "请指定对象名称") String objectName,
            @NotNull(message = "请指定对象主键") Long objectId, @NotEmpty(message = "请指定目标名称") String targetName) {
        return objectMapDao.selectTargetList(objectName, objectId, targetName);
    }

    /**
     * 获取对象主键列表
     * @param targetName 目标名称
     * @param targetId   目标主键
     * @param objectName 对象名称
     */
    public List<String> selectObjectList(@NotEmpty(message = "请指定目标名称") String targetName,
            @NotNull(message = "请指定目标主键") Long targetId, @NotEmpty(message = "请指定对象名称") String objectName) {
        return objectMapDao.selectObjectList(targetName, targetId, objectName);
    }
}
