package cn.fruitbasket.orange.module.rbac.pojo.entity;

import cn.fruitbasket.orange.module.common.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import java.util.List;

import static cn.fruitbasket.orange.module.rbac.pojo.entity.RbacRole.TABLE_NAME;
import static javax.persistence.CascadeType.ALL;

/**
 * 用户角色
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(exclude = "permissions")
@Entity
@Where(clause = BaseDO.NOT_DELETE_CONDITION)
@SQLDelete(sql = "UPDATE " + TABLE_NAME + " SET deleted = true WHERE id = ?")
@SQLDeleteAll(sql = "UPDATE " + TABLE_NAME + " SET deleted = true WHERE id = ?")
public class RbacRole extends BaseDO<RbacRole> {

    static final String TABLE_NAME = "rbac_role";

    /**
     * 角色名称
     */
    @Column(length = 50, nullable = false, unique = true)
    private String roleName;

    /**
     * 角色显示名称
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

    @ManyToMany(mappedBy = "roles", cascade = ALL)
    private List<RbacUser> users;

    @ManyToMany(cascade = ALL)
    private List<RbacPermission> permissions;
}
