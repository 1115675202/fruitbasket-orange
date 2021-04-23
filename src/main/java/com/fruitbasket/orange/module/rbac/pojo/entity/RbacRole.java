package com.fruitbasket.orange.module.rbac.pojo.entity;

import com.fruitbasket.orange.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

import static com.fruitbasket.orange.module.common.entity.BaseDO.NOT_DELETE_CONDITION;
import static com.fruitbasket.orange.module.rbac.pojo.entity.RbacRole.TABLE_NAME;
import static javax.persistence.CascadeType.ALL;

/**
 * 用户角色
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
@Entity
@Where(clause = NOT_DELETE_CONDITION)
@SQLDelete(sql = "UPDATE " + TABLE_NAME + " SET deleted = true WHERE id = ?")
@SQLDeleteAll(sql = "UPDATE " + TABLE_NAME + " SET deleted = true WHERE id = ?")
public class RbacRole extends BaseDO {

    static final String TABLE_NAME = "rbac_role";

    /**
     * 默认排序值
     */
    public static final Integer DEFAULT_SORT_VALUE = 0;

    /**
     * 角色名称
     */
    @Column(length = 50, nullable = false, unique = true)
    private String roleName;

    /**
     * 显示在界面上的名称
     */
    @Column(length = 50, nullable = false)
    private String roleShowName;

    /**
     * 排序值
     */
    @Column(nullable = false)
    private Integer sortValue;

    /**
     * 备注
     */
    @Column(length = 100)
    private String description;

    @ManyToMany
    private List<RbacUser> users;

    @ManyToMany(mappedBy = "roles", cascade = ALL)
    private List<RbacPermission> permissions;
}
