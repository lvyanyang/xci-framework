package com.xci.sys.dao;

import com.xci.sys.entity.SysObjectMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统对象关联数据层
 * @author 吕艳阳
 */
public interface ObjectMapDao {
    /**
     * 新建对象关联
     * @param entity 对象关联实体
     */
    int insert(@Param("entity") SysObjectMap entity);

    /**
     * 根据目标删除
     * @param targetName 目标名称
     * @param targetId   目标主键
     * @param objectName 对象名称
     */
    int deleteByTarget(@Param("targetName") String targetName, @Param("targetId") Long targetId,
                       @Param("objectName") String objectName);

    /**
     * 获取目标列表
     * @param objectName 对象名称
     * @param objectId   对象主键
     * @param targetName 目标名称
     */
    List<String> selectTargetList(@Param("objectName") String objectName, @Param("objectId") Long objectId,
                                  @Param("targetName") String targetName);


    /**
     * 根据对象删除
     * @param objectName 对象名称
     * @param objectId   对象主键
     * @param targetName 目标名称
     */
    int deleteByObject(@Param("objectName") String objectName, @Param("objectId") Long objectId,
                       @Param("targetName") String targetName);

    /**
     * 获取对象列表
     * @param targetName 目标名称
     * @param targetId   目标主键
     * @param objectName 对象名称
     */
    List<String> selectObjectList(@Param("targetName") String targetName, @Param("targetId") Long targetId,
                                  @Param("objectName") String objectName);
}