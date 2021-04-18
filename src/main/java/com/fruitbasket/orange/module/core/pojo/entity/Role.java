package com.fruitbasket.orange.module.core.pojo.entity;

import com.fruitbasket.orange.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Set;

/**
 * 用户角色
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
@Entity
@SQLDelete(sql = "UPDATE user_role SET deleted = 1 WHERE id = ?")
public class Role extends BaseDO {

    /**
     * 父节点ID，0-无父节点
     */
    @Column(nullable = false)
    private Integer pid;

    /**
     * 角色代号
     */
    @Column(length = 20, nullable = false, unique = true)
    private String roleCode;

    /**
     * 角色名称
     */
    @Column(length = 20, nullable = false)
    private String roleName;

    /**
     * 角色层级
     */
    @Column(nullable = false)
    private Integer roleLevel;

    /**
     * 角色层级轨迹（格式如：/pid/pid/id）
     */
    @Column(length = 50, nullable = false, unique = true)
    private String rolePath;

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

//    @JoinTable(name = "user_role_rel",
//            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    @ManyToMany
    private Set<User> users;

    @ManyToMany(mappedBy = "roles")
    private Set<Permission> permissions;
}
