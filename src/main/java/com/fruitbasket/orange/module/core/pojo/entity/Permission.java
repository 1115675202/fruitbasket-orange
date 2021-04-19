package com.fruitbasket.orange.module.core.pojo.entity;

import com.fruitbasket.orange.dict.PermissionType;
import com.fruitbasket.orange.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

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
    private Integer pid = ROOT_ID;

    /**
     * 权限名称
     */
    @Column(nullable = false)
    private String permissionName;

    /**
     * 显示在界面上的名称
     */
    @Column(nullable = false)
    private String permissionShowName;

    /**
     * 接口/地址
     */
    @Column(length = 100, nullable = false)
    private String permissionLink;

    /**
     * 权限类型，用来区分菜单、接口、按钮等
     */
    @Column(nullable = false)
    private PermissionType permissionType;

    /**
     * 排序值
     */
    @Column(nullable = false)
    private Byte sortValue = 0;

    /**
     * 菜单层级，从 1 开始
     */
    @Column(nullable = false)
    private Byte permissionLevel;

    /**
     * 备注
     */
    @Column(length = 100)
    private String description;

    /**
     * 面包屑（格式如：/pid/pid/pid）
     */
    @Column(length = 100, nullable = false)
    private String breadcrumbs;

    @ManyToMany(cascade = ALL)
    private List<Role> roles;
}
