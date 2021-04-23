package com.fruitbasket.orange.module.rbac.pojo.query;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

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
    private String permissionName;

    /**
     * 显示在界面上的名称
     */
    private String permissionShowName;

    /**
     * 接口/地址
     */
    private String permissionLink;

    /**
     * 排序值
     */
    private Integer sortValue;

    /**
     * 备注
     */
    private String description;
}
