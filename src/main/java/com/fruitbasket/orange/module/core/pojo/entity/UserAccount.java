package com.fruitbasket.orange.module.core.pojo.entity;

import com.fruitbasket.orange.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

/**
 * 账户
 *
 * @author LiuBing
 * @date 2020/12/15
 */

@Data
@Accessors(chain = true)
@Entity
@SQLDelete(sql = "UPDATE user_account SET deleted = 1 WHERE id = ?")
public class UserAccount extends BaseDO {

	/**
	 * 账户类型
	 */
	@Column(nullable = false)
	private Integer identityType;

	/**
	 * 账户标识/账号
	 */
	@Column(nullable = false, unique = true)
	private String identifier;

	/**
	 * 凭证/密码
	 */
	@Column(nullable = false)
	private String credential;

//	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
}
