package cn.fruitbasket.orange.module.rbac.pojo.query;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 用户绑定角色
 *
 * @author LiuBing
 * @date 2021/4/21
 */
@Data
@Accessors(chain = true)
public class UserBindRolesQuery {

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID[userId]：不能为空")
    private Integer userId;

    /**
     * 多个角色ID
     */
    @NotEmpty(message = "角色ID[roleIds]：不能为空")
    private Set<Integer> roleIds;
}
