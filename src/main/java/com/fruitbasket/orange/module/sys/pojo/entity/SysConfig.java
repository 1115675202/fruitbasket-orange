package com.fruitbasket.orange.module.sys.pojo.entity;

import com.fruitbasket.orange.module.common.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 普通配置
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
@Entity
@SQLDelete(sql = "UPDATE sys_config SET deleted = 1 WHERE id = ?")
public class SysConfig extends BaseDO {

	/**
	 * 配置键
	 */
	@Column(length = 50, nullable = false, unique = true)
	private String configKey;

	/**
	 * 配置值
	 */
	@Column(length = 100, nullable = false)
	private String configValue;

	/**
	 * 备注
	 */
	@Column(length = 100)
	private String description;
}
