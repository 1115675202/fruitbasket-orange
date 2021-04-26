package cn.fruitbasket.orange.module.rbac.pojo.vo;

import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.List;

/**
 * 分页返回
 *
 * @author LiuBing
 * @date 2021/4/21
 */
@Data
@Accessors(chain = true)
public class RoleDetailVO {

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色显示名称
     */
    private String roleShowName;

    /**
     * 权限以及绑定信息
     */
    private List<RolePermissionTreeNodeVO> permissions;

    /**
     * 生成角色详情
     *
     * @param rbacRole       角色信息
     * @param allPermissions 所有权限
     * @return -
     */
    public static RoleDetailVO of(RbacRole rbacRole, Collection<RbacPermission> allPermissions) {
        RoleDetailVO roleDetail = new RoleDetailVO()
                .setRoleId(rbacRole.getId())
                .setRoleShowName(rbacRole.getRoleShowName());
        RolePermissionTreeNodeVO root = RolePermissionTreeNodeVO.treeOf(allPermissions, rbacRole.getPermissions());
        roleDetail.setPermissions(root.getChildren());
        return roleDetail;
    }
}
