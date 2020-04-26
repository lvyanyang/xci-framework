package com.xci.sys.filter;

import com.xci.core.BasePageFilter;
import com.xci.core.XCI;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 系统定时任务日志过滤条件
 * @author 吕艳阳
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统定时任务日志过滤条件")
public class JobLogFilter extends BasePageFilter {
	/**
	 * 任务主键
	 */
	@ApiModelProperty(value = "任务主键", position = 1)
	private String jobId;

	/**
	 * 任务名称
	 */
	@ApiModelProperty(value = "任务名称", position = 2)
	private String jobName;

	/**
	 * 任务表达式
	 */
	@ApiModelProperty(value = "任务表达式", position = 3)
	private String jobExpression;

	/**
	 * 执行类型 [1-手动触发, 0-系统触发]
	 */
	@ApiModelProperty(value = "执行类型 [1-手动触发, 0-系统触发]", position = 4)
	private Integer triggerCategory;

	/**
	 * 执行状态 [true-成功, false-失败]
	 */
	@ApiModelProperty(value = "执行状态 [true-成功, false-失败]")
	private Boolean status;

	/**
	 * 操作开始时间
	 */
	@ApiModelProperty(value = "操作开始时间", position = 6)
	private Date operateStartDateTime;

	/**
	 * 操作结束时间
	 */
	@ApiModelProperty(value = "操作结束时间", position = 7)
	private Date operateEndDateTime;

	/// <summary>
	/// 处理结束日期,结束日期增加一天
	/// </summary>
	public void PlusEndDateTime() {
		setOperateEndDateTime(XCI.PlusEndDateTime(operateEndDateTime));
	}
}