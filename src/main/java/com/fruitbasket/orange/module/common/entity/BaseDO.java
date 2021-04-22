package com.fruitbasket.orange.module.common.entity;

import com.fruitbasket.orange.config.CustomJpaEntityListener;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author LiuBing
 * @date 2020/12/15
 */
@Getter
@Setter
@Accessors(chain = true)
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class, CustomJpaEntityListener.class})
public class BaseDO {

	public static final String NOT_DELETE_CONDITION  = "deleted = false";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * true-已删除
	 */
	@Column(nullable = false)
	private Boolean deleted;

	/**
	 * 创建时间
	 */
	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime gmtCreate;

	/**
	 * 最后修改时间
	 */
	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime gmtModified;
}
