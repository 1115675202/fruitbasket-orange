package cn.fruitbasket.orange.module.rbac.pojo.query;

import cn.fruitbasket.orange.module.common.query.PageableQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色分页查询
 *
 * @author LiuBing
 * @date 2021/4/21
 */
@Data
@Accessors(chain = true)
public class RolePageableQuery extends PageableQuery {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色显示名称
     */
    private String roleShowName;
}
