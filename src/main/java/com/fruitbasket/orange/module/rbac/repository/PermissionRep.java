package com.fruitbasket.orange.module.rbac.repository;

import com.fruitbasket.orange.dict.PermissionType;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 权限
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface PermissionRep extends JpaRepository<RbacPermission, Integer> {

    /**
     * 根据角色列表查询权限
     *
     * @param roles 角色列表
     * @return 权限列表
     */
    List<RbacPermission> queryAllByRolesInOrderBySortValueDesc(List<RbacRole> roles);


    List<RbacPermission> queryAllByRolesInAndPermissionTypeIn(List<RbacRole> roles, List<PermissionType> types);
}
