package com.example.spring.boot.zhaoyun.module.user.pojo.entity;

import com.example.spring.boot.zhaoyun.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;

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
	 * 父权限
	 */
	@Column(nullable = false)
	private Integer pid;

	/**
	 * 权限代号
	 */
	@Column(length = 20, nullable = false, unique = true)
	private String permissionCode;

	/**
	 * 权限名称
	 */
	@Column(length = 20, nullable = false)
	private String permissionName;

	/**
	 * 接口/地址
	 */
	@Column(length = 100, nullable = false)
	private String permissionUrl;

	/**
	 * 权限层级
	 */
	@Column(nullable = false)
	private Integer permissionLevel;

	/**
	 * 权限层级轨迹（格式如：/pid/pid/id）
	 */
	@Column(length = 50, nullable = false, unique = true)
	private String permissionPath;

	/**
	 * 备注
	 */
	private String description;

	/**
	 * 排序值
	 */
	@Column(nullable = false)
	private Integer sortValue;
}
