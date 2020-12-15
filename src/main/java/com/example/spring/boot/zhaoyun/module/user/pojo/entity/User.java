package com.example.spring.boot.zhaoyun.module.user.pojo.entity;

import com.example.spring.boot.zhaoyun.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

	@Column(length = 50, nullable = false, unique = true)
	private Integer sex;

	@Column(length = 20, nullable = false, unique = true)
	private String realName;

	@Column(nullable = false)
	private String idCardNo;

	@Column(nullable = false)
	private LocalDate birthday;

	@Column(nullable = false)
	private String avatarLink;

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<UserAccount> userAccountList;
}
