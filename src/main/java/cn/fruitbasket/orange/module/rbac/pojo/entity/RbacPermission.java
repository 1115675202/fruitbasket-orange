package cn.fruitbasket.orange.module.rbac.pojo.entity;

import cn.fruitbasket.orange.dict.PermissionType;
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
import java.util.Set;

import static cn.fruitbasket.orange.dict.PermissionType.MENU;
import static cn.fruitbasket.orange.module.common.entity.BaseDO.NOT_DELETE_CONDITION;
import static cn.fruitbasket.orange.module.rbac.pojo.entity.RbacRole.TABLE_NAME;
import static javax.persistence.CascadeType.ALL;

/**
 * 权限/菜单
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(exclude = "roles")
@Entity
@Where(clause = NOT_DELETE_CONDITION)
@SQLDelete(sql = "UPDATE " + TABLE_NAME + " SET deleted = true WHERE id = ?")
@SQLDeleteAll(sql = "UPDATE " + TABLE_NAME + " SET deleted = true WHERE id = ?")
public class RbacPermission extends BaseDO {

    static final String TABLE_NAME = "rbac_permission";

    /**
     * 最上级 ID / 顶级权限的 pid
     */
    public static final Integer ROOT_ID = 0;

    /**
     * 权限初始层级
     */
    public static final Integer FIRST_LEVEL = 1;

    /**
     * 默认排序值
     */
    public static final Integer DEFAULT_SORT_VALUE = 0;

    /**
     * 根权限/顶级权限，只是为了业务代码实现简单
     */
    public static final RbacPermission ROOT_PERMISSION = new RbacPermission()
            .setPid(ROOT_ID).setPermissionLevel(FIRST_LEVEL).setPermissionType(MENU).setBreadcrumbs("");

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
     * 权限显示名称
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
    private Integer sortValue;

    /**
     * 备注
     */
    @Column(length = 100)
    private String description;

    /**
     * 菜单层级，从 1 开始
     */
    @Column(nullable = false)
    private Integer permissionLevel;

    /**
     * 面包屑（格式如：/pid/pid/pid）
     */
    @Column(length = 100, nullable = false)
    private String breadcrumbs;

    @ManyToMany(mappedBy = "permissions",cascade = ALL)
    private Set<RbacRole> roles;

    /**
     * 根据父权限生成面包屑
     *
     * @param parent -
     * @return 面包屑
     */
    public static String breadcrumbsBy(RbacPermission parent) {
        return parent.getBreadcrumbs() + "/" + parent.getPid();
    }

    /**
     * 根据父层级生成自己的层级
     *
     * @param parentLevel -
     * @return 层级
     */
    public static Integer permissionLevelBy(Integer parentLevel) {
        return ++parentLevel;
    }
}