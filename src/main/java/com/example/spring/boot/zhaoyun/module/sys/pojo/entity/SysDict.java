package com.example.spring.boot.zhaoyun.module.sys.pojo.entity;

import com.example.spring.boot.zhaoyun.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 字典
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
@Entity
@SQLDelete(sql = "UPDATE sys_dict SET deleted = 1 WHERE id = ?")
public class SysDict extends BaseDO {

	/**
	 * 字典代号
	 */
	@Column(length = 20, nullable = false)
	private String dictCode;

	/**
	 * 字典选项值
	 */
	@Column(length = 20, nullable = false)
	private String optionValue;

	/**
	 * 字典选项名称
	 */
	@Column(length = 20, nullable = false)
	private String optionName;

	/**
	 * 排序值
	 */
	@Column(nullable = false)
	private Integer sortValue;
}
