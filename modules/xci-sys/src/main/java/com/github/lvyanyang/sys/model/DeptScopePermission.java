/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.model;

import com.github.lvyanyang.sys.entity.SysDept;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 机构权限
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-05-03 21:30
 */
@Data
@ApiModel(description = "机构权限")
public class DeptScopePermission {
    /**
     * 机构权限值
     */
    @ApiModelProperty(value = "机构权限值")
    private DeptScopeEnum deptScope;

    /**
     * 自定义机构列表
     */
    @ApiModelProperty(value = "自定义机构列表")
    private List<SysDept> customs;
}
