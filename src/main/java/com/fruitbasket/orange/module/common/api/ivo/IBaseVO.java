package com.fruitbasket.orange.module.common.api.ivo;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * 基础返回视图接口
 *
 * @author LiuBing
 * @date 2020/12/16
 */
public interface IBaseVO {

	/**
	 * 主键
	 *
	 * @return
	 */
	@ApiModelProperty(value = "主键")
	Integer getId();

	/**
	 * 创建时间
	 *
	 * @return
	 */
	@ApiModelProperty(value = "创建时间")
	LocalDateTime getGmtCreate();

	/**
	 * 最后修改时间
	 *
	 * @return
	 */
	@ApiModelProperty(value = "最后修改时间")
	LocalDateTime getGmtModified();
}
