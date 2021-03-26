package com.fruitbasket.orange.module.sys.pojo.vo;

import com.fruitbasket.orange.module.sys.api.ivo.ISysConfigVO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 普通配置
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
public class SysConfigVO implements ISysConfigVO {

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 配置键
	 */
	private String configKey;

	/**
	 * 配置值
	 */
	private String configValue;

	/**
	 * 备注
	 */
	private String description;

	/**
	 * 创建时间
	 */
	private LocalDateTime gmtCreate;

	/**
	 * 修改时间
	 */
	private LocalDateTime gmtModified;
}
