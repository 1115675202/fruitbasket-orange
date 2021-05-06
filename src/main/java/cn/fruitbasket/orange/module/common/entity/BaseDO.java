package cn.fruitbasket.orange.module.common.entity;

import cn.fruitbasket.orange.config.CustomJpaEntityListener;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NaturalId;
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
@EntityListeners({AuditingEntityListener.class, CustomJpaEntityListener.class})
public class BaseDO {

    /**
     * 查询默认未删除条件
     */
    public static final String NOT_DELETE_CONDITION = "deleted = false";

    /**
     * 默认排序值
     */
    public static final Integer DEFAULT_SORT_VALUE = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NaturalId
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
