package com.fruitbasket.orange.module.core.pojo.entity;

import com.fruitbasket.orange.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/15
 */
@Data
@Accessors(chain = true)
@Entity
@SQLDelete(sql = "UPDATE user SET deleted = 1 WHERE id = ?")
public class User extends BaseDO {

	@Column(length = 50, nullable = false)
	private Integer sex;

	@Column(length = 20, nullable = false, unique = true)
	private String realName;

	@Column(nullable = false, unique = true)
	private String idCardNo;

	@Column(nullable = false)
	private LocalDate birthday;

	@Column(nullable = false)
	private String avatarLink;

	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE}, mappedBy = "user")
	private Set<UserAccount> userAccounts;

	@ManyToMany(mappedBy = "users")
	private Set<Role> roles;
}
