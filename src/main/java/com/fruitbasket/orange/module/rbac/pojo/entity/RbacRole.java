package com.fruitbasket.orange.module.rbac.pojo.entity;

import com.fruitbasket.orange.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

import static javax.persistence.CascadeType.*;

/**
 * 用户角色
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
@Entity
@SQLDelete(sql = "UPDATE rbac_role SET deleted = 1 WHERE id = ?")
public class RbacRole extends BaseDO {

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
    private Byte sortValue;

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
