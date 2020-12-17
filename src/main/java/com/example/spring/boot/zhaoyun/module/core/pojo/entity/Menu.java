package com.example.spring.boot.zhaoyun.module.core.pojo.entity;

import com.example.spring.boot.zhaoyun.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Set;

/**
 * 菜单/菜单
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
@Entity
@SQLDelete(sql = "UPDATE user_menu SET deleted = 1 WHERE id = ?")
public class Menu extends BaseDO {

	/**
	 * 父节点ID，0-无父节点
	 */
	@Column(nullable = false)
	private Integer pid;

	/**
	 * 菜单代号
	 */
	@Column(length = 20, nullable = false, unique = true)
	private String menuCode;

	/**
	 * 菜单名称
	 */
	@Column(length = 20, nullable = false)
	private String menuName;

	/**
	 * 菜单地址
	 */
	@Column(length = 100, nullable = false)
	private String menuUrl;

	/**
	 * 菜单层级
	 */
	@Column(nullable = false)
	private Integer menuLevel;

	/**
	 * 菜单层级轨迹（格式如：/pid/pid/id）
	 */
	@Column(length = 50, nullable = false, unique = true)
	private String menuPath;

	/**
	 * 排序值
	 */
	@Column(nullable = false)
	private Integer sortValue;

	/**
	 * 备注
	 */
	@Column(length = 100)
	private String description;

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE}, mappedBy = "menu")
	private Set<MenuOperation> menuOperations;

	@JoinTable(name = "role_menu_rel",
			joinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
	@ManyToMany
	private Set<Role> roles;
}
