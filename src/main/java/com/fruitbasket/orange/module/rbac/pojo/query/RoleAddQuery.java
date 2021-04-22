package com.fruitbasket.orange.module.rbac.pojo.query;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 角色新增
 *
 * @author LiuBing
 * @date 2021/4/21
 */
@Data
@Accessors(chain = true)
public class RoleAddQuery {


    /**
     * 角色名称
     */
    @NotNull(message = "角色名称[roleName]：不能为空")
    @Size(min = 1,  max = 50, message = "角色名称[roleName]：长度为 1 ~ 50")
    private String roleName;

    /**
     * 角色显示名称
     */
    @NotNull(message = "角色显示名称[roleShowName]：不能为空")
    @Size(min = 1,  max = 50, message = "角色显示名称[roleShowName]：长度为 1 ~ 50")
    private String roleShowName;

    /**
     * 排序值
     */
    private Byte sortValue;

    /**
     * 备注
     */
    @Size(max = 100, message = "备注[description]：长度为 0 ~ 100")
    private String description;
}
