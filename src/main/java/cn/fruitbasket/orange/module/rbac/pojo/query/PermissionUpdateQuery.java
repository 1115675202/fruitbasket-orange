package cn.fruitbasket.orange.module.rbac.pojo.query;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 权限/菜单新增
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
public class PermissionUpdateQuery {

    /**
     * 权限 ID
     */
    @NotNull
    private Integer id;

    /**
     * 权限名称
     */
    @Size(min = 1, max = 50, message = "权限名称[permissionName]：长度为 1 ~ 50")
    private String permissionName;

    /**
     * 接口/地址
     */
    @Size(min = 1, max = 100, message = "接口/地址[permissionLink]：长度为 1 ~ 100")
    private String permissionLink;

    /**
     * 图标
     */
    @Size(min = 1, max = 50, message = "图标[icon]：长度为 1 ~ 50")
    private String icon;

    /**
     * 排序值
     */
    private Integer sortValue;

    /**
     * 备注
     */
    private String description;
}
