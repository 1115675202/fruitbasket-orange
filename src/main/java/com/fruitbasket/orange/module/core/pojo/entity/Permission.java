package com.fruitbasket.orange.module.core.pojo.entity;

import com.fruitbasket.orange.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Set;

/**
 * 权限/菜单
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
@Entity
@SQLDelete(sql = "UPDATE user_permission SET deleted = 1 WHERE id = ?")
public class Permission extends BaseDO {

    public static final Integer ROOT_ID = 0;

    /**
     * 父节点ID，0-无父节点
     */
    @Column(nullable = false)
    private Integer pid;

    /**
     * 权限名称
     */
    @Column(nullable = false)
    private String permissionName;

    /**
     * 接口/地址
     */
    @Column(length = 100, nullable = false)
    private String permissionUrl;

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

    /**
     * 菜单层级
     */
    @Column(nullable = false)
    private Integer level;

    /**
     * 面包屑（格式如：/pid/pid/id）
     */
    @Column(length = 50, nullable = false, unique = true)
    private String breadcrumbs;

//    @JoinTable(name = "role_permission_rel",
//            joinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    @ManyToMany
    private Set<Role> roles;
}
