package com.example.spring.boot.zhaoyun.module.user.pojo.entity;

import com.example.spring.boot.zhaoyun.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * 用户角色
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
@Entity
@SQLDelete(sql = "UPDATE user_role SET deleted = 1 WHERE id = ?")
public class UserRole extends BaseDO {

	/**
	 * 父节点ID，0-无父节点
	 */
	@Column(nullable = false)
	private Integer pid;

	/**
	 * 角色代号
	 */
	@Column(length = 20, nullable = false, unique = true)
	private String roleCode;

	/**
	 * 角色名称
	 */
	@Column(length = 20, nullable = false)
	private String roleName;

	/**
	 * 角色层级
	 */
	@Column(nullable = false)
	private Integer roleLevel;

	/**
	 * 角色层级轨迹（格式如：/pid/pid/id）
	 */
	@Column(length = 50, nullable = false, unique = true)
	private String rolePath;

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

	@ManyToMany(mappedBy = "roles")
	private Set<UserPermission> permissions;
}
