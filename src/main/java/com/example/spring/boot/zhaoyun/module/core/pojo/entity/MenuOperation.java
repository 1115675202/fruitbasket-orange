package com.example.spring.boot.zhaoyun.module.core.pojo.entity;

import com.example.spring.boot.zhaoyun.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

/**
 * 权限/菜单下的操作/按钮
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
@Entity
@SQLDelete(sql = "UPDATE user_menu_operation SET deleted = 1 WHERE id = ?")
public class MenuOperation extends BaseDO {

	/**
	 * 操作/按钮代号
	 */
	@Column(length = 20, nullable = false, unique = true)
	private String operationCode;

	/**
	 * 操作/按钮名称
	 */
	@Column(length = 20, nullable = false)
	private String operationName;

	/**
	 * 操作地址
	 */
	@Column(length = 100, nullable = false)
	private String operationUrl;

	/**
	 * 备注
	 */
	@Column(length = 100)
	private String description;

	@JoinColumn(name = "menu_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Menu menu;
}
