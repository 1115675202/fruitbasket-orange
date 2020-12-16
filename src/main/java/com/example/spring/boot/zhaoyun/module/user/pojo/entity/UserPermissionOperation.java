package com.example.spring.boot.zhaoyun.module.user.pojo.entity;

import com.example.spring.boot.zhaoyun.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 权限/菜单下的操作/按钮
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
@Entity
@SQLDelete(sql = "UPDATE user_permission_operation SET deleted = 1 WHERE id = ?")
public class UserPermissionOperation extends BaseDO {

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
	 * 接口/地址
	 */
	@Column(length = 100, nullable = false)
	private String operationUrl;

	/**
	 * 备注
	 */
	private String description;
}
