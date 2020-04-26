package com.xci.sys.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xci.core.BaseEntity;
import com.xci.core.R;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 系统登录日志
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统登录日志")
public class SysLoginLog extends BaseEntity {
    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 登录类型 [1-登录, 2-注销]
     */
    @NotNull(message = "登录类型不能为空")
    @ApiModelProperty(value = "登录类型 [1-登录, 2-注销]", required = true)
    private Integer category;

    /**
     * 登录类型名称
     */
    @ApiModelProperty(value = "登录类型名称")
    public String getCategoryName() {
        switch(category) {
            case 1:
                return "登录";
            case 2:
                return "注销";
        }
        return R.Empty;
    }

    /**
     * 登录账号
     */
    @NotBlank(message = "请输入登录账号")
    @Length(max = 100, message = "登录账号长度不能超过{max}")
    @ApiModelProperty(value = "登录账号", required = true)
    private String account;

    /**
     * 浏览器标识
     */
    @Length(max = 100, message = "浏览器标识长度不能超过{max}")
    @ApiModelProperty(value = "浏览器标识")
    private String userAgent;

    /**
     * 登录状态 [true-成功, false-失败]
     */
    @NotNull(message = "请输入登录状态")
    @ApiModelProperty(value = "登录状态 [true-成功, false-失败]", required = true)
    private Boolean status;

    /**
     * 操作信息
     */
    @Length(max = 1000, message = "操作信息长度不能超过{max}")
    @ApiModelProperty(value = "操作信息")
    private String msg;

    /**
     * 应用主键
     */
    @Length(max = 100, message = "应用主键长度不能超过{max}")
    @ApiModelProperty(value = "应用主键")
    private String appId;

    /**
     * 应用名称
     */
    @Length(max = 100, message = "应用名称长度不能超过{max}")
    @ApiModelProperty(value = "应用名称")
    private String appName;

    /**
     * IP地址
     */
    @Length(max = 100, message = "IP地址长度不能超过{max}")
    @ApiModelProperty(value = "IP地址")
    private String ip;

    /**
     * 登录时间
     */
    @ApiModelProperty(value = "登录时间")
    private Date operateDateTime;
}
