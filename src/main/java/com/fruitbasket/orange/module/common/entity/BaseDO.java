package com.fruitbasket.orange.module.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.lang.Boolean.FALSE;

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
	private Boolean deleted = FALSE;

	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime gmtCreate = LocalDateTime.now();

	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime gmtModified = LocalDateTime.now();
}
