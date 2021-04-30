package cn.fruitbasket.orange.module.rbac.pojo.query;

import cn.fruitbasket.orange.dict.PermissionType;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
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
public class PermissionAddQuery {

    /**
     * 父节点ID，0-无父节点
     */
    @NotNull(message = "父节点ID[pid]：不能为空")
    private Integer pid;

    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称[permissionName]：不能为空")
    @Size(min = 1, max = 50, message = "权限名称[permissionName]：长度为 1 ~ 50")
    private String permissionName;

    /**
     * 接口/地址
     */
    @Size(min = 1, max = 100, message = "接口/地址[permissionLink]：长度为 1 ~ 100")
    private String permissionLink;

    /**
     * 权限类型，用来区分菜单、接口、按钮等
     */
    @NotNull(message = "权限类型[permissionType]：不能为空")
    private PermissionType permissionType;

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
    @Size(max = 100, message = "备注[description]：长度为 0 ~ 100")
    private String description;
}
