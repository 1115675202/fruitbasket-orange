package com.fruitbasket.orange.module.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author LiuBing
 * @date 2020/12/15
 */
@Data
@Accessors(chain = true)
@MappedSuperclass
@Where(clause = "deleted = 0")
public class BaseDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private Boolean deleted;

	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime gmtCreate;

	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime gmtModified;

	public BaseDO() {
		this.deleted = Boolean.FALSE;;
	}
}
