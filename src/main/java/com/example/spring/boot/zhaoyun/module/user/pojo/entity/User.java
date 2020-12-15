package com.example.spring.boot.zhaoyun.module.user.pojo.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/15
 */
@Data
@Entity
@SQLDelete(sql = "UPDATE user SET deleted = 1 WHERE id = ?")
@Where(clause = "deleted = 0")
public class User {

	@Id
	private Integer id;

	@Column(nullable = false)
	private Boolean deleted;

	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false)
	private LocalDateTime gmtCreate;

	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
	private LocalDateTime gmtModified;
}
