package com.fruitbasket.orange.module.core.pojo.entity;

import com.fruitbasket.orange.module.common.entity.BaseDO;
import com.fruitbasket.orange.dict.IdentityType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import static com.fruitbasket.orange.dict.IdentityType.CURRENT_SYSTEM;
import static javax.persistence.CascadeType.ALL;

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
	 * 账户标识/账号
	 */
	@Column(length = 20, nullable = false, unique = true)
	private String identifier;

	/**
	 * 凭证/密码
	 */
	@Column(length = 20, nullable = false)
	private String credential;

	/**
	 * 账户类型
	 */
	@Column(nullable = false)
	private IdentityType identityType = CURRENT_SYSTEM;

	@ManyToOne
	private User user;
}
