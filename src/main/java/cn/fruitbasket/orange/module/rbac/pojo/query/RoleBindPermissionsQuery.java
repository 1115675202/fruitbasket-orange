package cn.fruitbasket.orange.module.rbac.pojo.query;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 角色绑定权限
 *
 * @author LiuBing
 * @date 2021/4/21
 */
@Data
@Accessors(chain = true)
public class RoleBindPermissionsQuery {

    /**
     * 角色ID
     */
    @NotNull(message = "角色ID[roleId]：不能为空")
    private Integer roleId;

    /**
     * 权限ID
     */
    @NotEmpty(message = "权限ID[permissionIds]：不能为空")
    private Set<Integer> permissionIds;
}
