package com.example.spring.boot.zhaoyun.module.sys.api.am;

import io.swagger.annotations.ApiModelProperty;

/**
 * 配置
 *
 * @author LiuBing
 * @date 2020/12/16
 */
public interface ISysConfigAM {

	/**
	 * 配置键
	 *
	 * @return
	 */
	@ApiModelProperty(value = "配置键")
	String getConfigKey();

	/**
	 * 配置值
	 *
	 * @return
	 */
	@ApiModelProperty(value = "配置值")
	String getConfigValue();

	/**
	 * 备注
	 *
	 * @return
	 */
	@ApiModelProperty(value = "备注")
	String getDescription();
}
