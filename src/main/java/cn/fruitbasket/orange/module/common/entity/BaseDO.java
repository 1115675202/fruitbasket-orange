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

import static org.springframework.data.util.CastUtils.cast;

/**
 * @param <C> 子类型调用本类中的set()方法后返回子类型，实现 set() 链式调用
 * @author LiuBing
 * @date 2020/12/15
 */
@Data
@Accessors(chain = true)
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class, CustomJpaEntityListener.class})
public class BaseDO<C extends BaseDO<?>> {

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

    public C setId(Integer id) {
        this.id = id;
        return cast(this);
    }

    public C setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return cast(this);
    }

    public C setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
        return cast(this);
    }

    public C setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
        return cast(this);
    }
}
