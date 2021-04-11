package com.fruitbasket.orange.module.core.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 菜单/权限
 *
 * @author LiuBing
 * @date 2021/4/11
 */
@Data
@Accessors(chain = true)
public class PermissionVO {

    /**
     * ID
     */
    private Integer id;

    /**
     * 菜单/权限名称
     */
    private String permissionName;

    /**
     * 菜单/权限地址
     */
    private String permissionUrl;

    /**
     * 子菜单/权限
     */
    private List<PermissionVO> children;
}
