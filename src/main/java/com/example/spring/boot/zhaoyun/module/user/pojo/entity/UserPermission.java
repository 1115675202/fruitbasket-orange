package com.example.spring.boot.zhaoyun.module.user.pojo.entity;

import com.example.spring.boot.zhaoyun.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Set;

/**
 * 权限/菜单
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
@Entity
@SQLDelete(sql = "UPDATE user_permission SET deleted = 1 WHERE id = ?")
public class UserPermission extends BaseDO {

	/**
	 * 接口/地址
	 */
	@Column(length = 100, nullable = false)
	private String permissionUrl;

	/**
	 * 权限类型
	 */
	@Column(nullable = false)
	private Integer permissionType;

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

	@JoinTable(name = "role_permission_rel",
	joinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")},
	inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
	@ManyToMany
	@OneToOne(mappedBy = "")
	@JoinColumn(name = "xx_id", unique = true)
	@OneToOne(fetch = FetchType.LAZY)
	private Set<UserRole> roles;
}
