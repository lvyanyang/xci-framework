/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.entity;

import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统用户(保存)
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统用户(保存)")
public class SysUserSave extends SysUserSecurity {
    /**
     * 角色主键字符串,多个逗号隔开
     */
    @ExcelIgnore
    @ApiModelProperty(value = "角色主键字符串,多个逗号隔开", position = 200)
    private String roleIds;
}