package com.xci.sys.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xci.sys.model.BaseOperateUserEntity;
import com.xci.core.R;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 系统历史日志
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统历史日志")
public class SysHistoryLog extends BaseOperateUserEntity {
    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 操作类型 [1-新增, 2-修改, 3-删除]
     */
    @NotNull(message = "操作类型不能为空")
    @ApiModelProperty(value = "操作类型 [1-新增, 2-修改, 3-删除]", required = true)
    private Integer category;

    /**
     * 操作类型名称
     */
    @ApiModelProperty(value = "操作类型名称")
    public String getCategoryName() {
        switch (category) {
            case 1:
                return "新增";
            case 2:
                return "修改";
            case 3:
                return "删除";
        }
        return R.Empty;
    }

    /**
     * 表名
     */
    @NotBlank(message = "表名不能为空")
    @Length(max = 100, message = "表名不能超过{max}个字符")
    @ApiModelProperty(value = "表名", required = true)
    private String tableName;

    /**
     * 记录主键
     */
    @NotNull(message = "记录主键不能为空")
    @Length(max = 100, message = "记录主键不能超过{max}个字符")
    @ApiModelProperty(value = "记录主键", required = true)
    private String primaryKey;

    /**
     * 操作前数据
     */
    @ApiModelProperty(value = "操作前数据")
    private String beforeData;

    /**
     * 操作后数据
     */
    @ApiModelProperty(value = "操作后数据")
    private String afterData;

    /**
     * 差异信息
     */
    @ApiModelProperty(value = "差异信息")
    private String diff;
}